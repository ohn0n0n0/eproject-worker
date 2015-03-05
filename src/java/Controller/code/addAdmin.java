/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
public class addAdmin {

    public addAdmin(String uname, String pass) {
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

    public void closePage() {
        
    }

    public String insertAdmin() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Eproject", "sa", "sa");
            String sql = "insert into ADMIN values(?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, uname);
            ps.setString(2, pass);
            int x = ps.executeUpdate();
            if (x > 0) {
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "failed";
    }

    public addAdmin() {
    }

}
