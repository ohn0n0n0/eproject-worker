/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Rosse Tran
 */
@ManagedBean
@SessionScoped
public class logIn {

    public logIn(String uname, String pass) {
        this.uname = uname;
        this.pass = pass;
    }

    private String uname;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
    private String pass;

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
public void closePage(){
    
}
    public boolean check() {
        ArrayList<logIn> lst = new ArrayList<logIn>();
        boolean tmp = false;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Eproject", "rosse", "01071988");
            ResultSet rs = con.createStatement().executeQuery("select * from ADMIN where Username = '"+uname+"' and Password ='"+pass+"'");
            while (rs.next()) {
                
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

       return tmp;
    
    }
    public logIn() {
    }

}
