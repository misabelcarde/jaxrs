/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import beans.AppBean;
import beans.SessionBean;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author MIsabelCarde
 */
@Path("/hello")
public class TestRest {

    @Inject
    private SessionBean sessionBean;

    @GET
    @Path("/{param}")
    public Response getMsg(@PathParam("param") String msg, @Context HttpServletRequest req) {
        HttpSession session= req.getSession(true);
    	Object foo = session.getAttribute("foo");
    	if (foo!=null) {
            System.out.println(foo.toString());
    	} else {
            foo = "bar";
            session.setAttribute("foo", "bar");
    	}

        String output = "Jersey say : " + msg;
        sessionBean.setMsg(msg);
        return Response.status(200).entity(output).build();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello(@Context HttpServletRequest req) {
        HttpSession session= req.getSession(true);
    	Object foo = session.getAttribute("foo");
    	if (foo!=null) {
            System.out.println(foo.toString());
    	} else {
            foo = "bar";
            session.setAttribute("foo", "bar");
    	}
        sessionBean.getMsg();
        return "<html> " + "<title>" + "Hello Jersey" + "</title>"
                + "<body><h1>" + "Hello Jersey, " + foo.toString() + "</body></h1>" + "</html> ";
    }
}
