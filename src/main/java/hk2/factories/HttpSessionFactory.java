/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hk2.factories;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.glassfish.hk2.api.Factory;

/**
 *
 * @author MIsabelCarde
 */
/*
Implementing Custom Injection Provider
*/
public class HttpSessionFactory implements Factory<HttpSession> {

    private final HttpServletRequest request;

    @Inject
    public HttpSessionFactory(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public HttpSession provide() {
        return request.getSession();
    }

    @Override
    public void dispose(HttpSession t) {
    }
}
