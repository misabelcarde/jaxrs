/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author MIsabelCarde
 */
@Named
@SessionScoped
public class SessionBean implements Serializable{
    
    private String msg;

    @PostConstruct
    public void init(){
        if(msg == null){
            msg = "init msg session";
        }
    }
    
    public void edit(){
        msg = "edited";
    }
    
    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
