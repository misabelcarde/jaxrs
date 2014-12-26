/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
/**
 *
 * @author MIsabelCarde
 */
@ApplicationScoped
public class AppBean implements Serializable{
    
    private Map<String, String> sessions;
    
    @PostConstruct
    public void init(){
        setSessions(new HashMap<String, String>());
    }

    /**
     * @return the sessions
     */
    //@Produces
    public Map<String, String> getSessions() {
        return sessions;
    }

    /**
     * @param sessions the sessions to set
     */
    public void setSessions(Map<String, String> sessions) {
        this.sessions = sessions;
    }
}
