/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hk2.lifecycle;

import annotations.SessionInject;
import javax.inject.Inject;
import javax.inject.Named;
 
import javax.servlet.http.HttpSession;
import org.glassfish.hk2.api.Injectee;
 
import org.glassfish.hk2.api.InjectionResolver;
import org.glassfish.hk2.api.ServiceHandle;
/**
 *
 * @author MIsabelCarde
 */ 
public class SessionInjectResolver implements InjectionResolver<SessionInject> {
 
    @Inject
    @Named(InjectionResolver.SYSTEM_RESOLVER_NAME)
    InjectionResolver<Inject> systemInjectionResolver;
 
    @Override
    public Object resolve(Injectee injectee, ServiceHandle<?> handle) {
        if (HttpSession.class == injectee.getRequiredType()) {
            return systemInjectionResolver.resolve(injectee, handle);
        }
 
        return null;
    }
 
    @Override
    public boolean isConstructorParameterIndicator() {
        return false;
    }
 
    @Override
    public boolean isMethodParameterIndicator() {
        return false;
    }
}
