/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package providers;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.glassfish.hk2.api.DynamicConfiguration;
import org.glassfish.hk2.api.DynamicConfigurationService;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.api.PerLookup;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.binding.BindingBuilderFactory;
import org.glassfish.jersey.server.spi.ComponentProvider;
import resources.PerSessionResource;
/**
 *
 * @author MIsabelCarde
 */ 
/*
TODO determinate these parameters -> "we do not consider a mechanism that would clean-up any obsolete 
resources for closed, expired or otherwise invalidated HTTP client sessions. We have omitted those 
considerations here for the sake of brevity of our example". 
*/
@javax.ws.rs.ext.Provider
public class PerSessionComponentProvider implements ComponentProvider {
    
    private ServiceLocator locator;
 
    static class PerSessionFactory implements Factory<PerSessionResource>{
        static ConcurrentHashMap<String, PerSessionResource> perSessionMap = new ConcurrentHashMap<>();
 
        private final Provider<HttpServletRequest> requestProvider;
        private final ServiceLocator locator;
 
        @Inject
        public PerSessionFactory( Provider<HttpServletRequest> request, ServiceLocator locator) {
            this.requestProvider = request;
            this.locator = locator;
        }
 
        @Override
        @PerLookup
        public PerSessionResource provide() {
            final HttpSession session = requestProvider.get().getSession();

            if (session.isNew() || !perSessionMap.keySet().contains(session.getId())) {
                PerSessionResource newInstance = createNewPerSessionResource();
                perSessionMap.put(session.getId(), newInstance);
                newInstance.setAll(perSessionMap.keySet().size());
                return newInstance;
            } else {
                return perSessionMap.get(session.getId()); 
            }
        }
 
        @Override
        public void dispose(PerSessionResource r) {
        }
 
        private PerSessionResource createNewPerSessionResource() {
            final PerSessionResource perSessionResource = new PerSessionResource();
            locator.inject(perSessionResource);
            return perSessionResource;
        }
    }
 
    @Override
    public void initialize(ServiceLocator locator) {
        this.locator = locator;
    }
 
    @Override
    public boolean bind(Class<?> component, Set<Class<?>> providerContracts) {
        if (component == PerSessionResource.class) {
            final DynamicConfigurationService dynamicConfigService =
                locator.getService(DynamicConfigurationService.class);
            final DynamicConfiguration dynamicConfiguration =
                dynamicConfigService.createDynamicConfiguration();
 
            BindingBuilderFactory
                .addBinding(BindingBuilderFactory.newFactoryBinder(PerSessionFactory.class)
                .to(PerSessionResource.class), dynamicConfiguration);
            dynamicConfiguration.commit();
 
            return true;
        }
        return false;
    }
 
    @Override
    public void done() {
    }
}