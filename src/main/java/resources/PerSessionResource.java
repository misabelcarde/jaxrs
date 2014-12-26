/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import annotations.SessionInject;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author MIsabelCarde
 */
/*
"This class allows to count the number of requests made within a single client session and 
provides a handy sub-resource method to obtain the number via a HTTP GET method call.
For each request a new instance of our PerSessionResource class would get created with a fresh 
instance counter field set to 0. It's needed a custom Jersey ComponentProvider SPI".
*/
@Path("session")
public class PerSessionResource {
 
    @SessionInject HttpSession httpSession;
    AtomicInteger counter = new AtomicInteger();
    
    private Integer all;
 
    @GET
    @Path("id")
    public String getSession() {
        counter.incrementAndGet();
        return httpSession.getId();
    }
 
    @GET
    @Path("count")
    public int getSessionRequestCount() {
        return counter.incrementAndGet();
    }
    
    @GET
    @Path("all")
    public int getSessionRequestAll() {
        return all;
    }

    /**
     * @return the all
     */
    public Integer getAll() {
        return all;
    }

    /**
     * @param all the all to set
     */
    public void setAll(Integer all) {
        this.all = all;
    }
}