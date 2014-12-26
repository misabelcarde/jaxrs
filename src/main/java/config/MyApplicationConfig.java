/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import annotations.SessionInject;
import beans.AppBean;
import beans.SessionBean;
import hk2.factories.HttpSessionFactory;
import hk2.lifecycle.SessionInjectResolver;
import javax.inject.Singleton;
import javax.servlet.http.HttpSession;
import org.glassfish.hk2.api.InjectionResolver;
import org.glassfish.hk2.api.TypeLiteral;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 *
 * @author MIsabelCarde
 */
public class MyApplicationConfig extends ResourceConfig{
    public MyApplicationConfig() {
        //Package "beans" is only for test the behaviour of JEE scopes using Jersey
        packages("annotations", "beans", "config", "hk2.lifecycle", "hk2.factories", "providers", "resources");
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(SessionBean.class).to(SessionBean.class);
                bind(AppBean.class).to(AppBean.class);
                bindFactory(HttpSessionFactory.class).to(HttpSession.class);
                bind(SessionInjectResolver.class)
                    .to(new TypeLiteral<InjectionResolver<SessionInject>>(){})
                    .in(Singleton.class);
            }
        });
        
        
    }
}
