package Controller.code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@SessionScoped
public class logIn {
    private final HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    //String username= session.setAtribute("Username" ,uname);
    public logIn(String uname, String pass) {
        faceContext=FacesContext.getCurrentInstance();
        httpServletRequest=(HttpServletRequest)faceContext.getExternalContext().getRequest();
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
    //public static SessionScoped session = null;
    //String pass1 = encode.OnewayEncrypt((pass.getBytes()));


    public String check() {
        //ArrayList<logIn> lst = new ArrayList<logIn>();
        if (uname.equals("") || pass.equals("")) {
            return "khong ";
        } else {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Eproject", "sa", "sa");
                PreparedStatement ps = con.prepareStatement("SELECT * FROM ADMIN WHERE Username like '" + uname + "' and Password like '" + pass + "'");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    if ((uname.equals(uname)) && (pass.equals(pass))) {
                        httpServletRequest.getSession().setAttribute("session", uname);
                        return "thanh cong";
                    } else {
                        return "that bai";
                    }
                }
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return "khong dang nhap thanh cong";

        }
    }

    public logIn() {
        faceContext=FacesContext.getCurrentInstance();
        httpServletRequest=(HttpServletRequest)faceContext.getExternalContext().getRequest();
    }

}
