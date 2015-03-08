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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Rosse Tran
 */
@ManagedBean
@SessionScoped
public class manageAdmin {
    
    private String uname;
    private String oldPass;

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    private String pass;
    public manageAdmin() {
    }
    public manageAdmin(String uname, String pass ) {
        this.uname = uname;
        this.pass = pass;
    }
    /**
     * Liệt kê các record của bảng lớp học để hiển thị lên table
     * 
     */
    public ArrayList<manageAdmin> getAllAdmin() {
        ArrayList<manageAdmin> lst = new ArrayList<manageAdmin>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Eproject", "sa", "sa");
            ResultSet rs = con.createStatement().executeQuery("select * from ADMIN");
            while (rs.next()) {
                manageAdmin lh = new manageAdmin( 
                        rs.getString(1),
                        rs.getString(2));
                lst.add(lh);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        return lst;
    }
    
    public String insertAdmin() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Eproject", "sa", "sa");
            String sql = "insert into ADMIN values(?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, uname);
            ps.setString(2,encode.OnewayEncrypt(pass.getBytes()));
            int x = ps.executeUpdate();
            if (x > 0) {
                return "manageAdmin";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "failed";
    }
    
     public void closePage() {

    }
     
     /**
     * Action dùng để chọn mẫu tin cần cập nhật
     * @param evt
     */
    public void editAdmin(ActionEvent evt) {
        // We get the table object
        HtmlDataTable table = getParentDatatable((UIComponent) evt.getSource());
        // We get the object on the selected line.
        Object o = table.getRowData();
        manageAdmin lh = (manageAdmin) o;
        this.uname = lh.uname;
        this.pass = lh.pass;
    }
 
    // Method to get the HtmlDataTable.
    private HtmlDataTable getParentDatatable(UIComponent compo) {
        if (compo == null) {
            return null;
        }
        if (compo instanceof HtmlDataTable) {
            return (HtmlDataTable) compo;
        }
        return getParentDatatable(compo.getParent());
    }
 
    /**
     * Cập nhật dữ liệu được chọn lên cơ sở dữ liệu.
     * @return
     */
    public String update() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Eproject", "sa", "sa");       
            String sql = "update ADMIN set Password= ? where Username = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,encode.OnewayEncrypt((pass.getBytes())));
            ps.setString(2, uname);
            int x = ps.executeUpdate();
            if (x > 0) {
                return "manageAdmin";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "failed";
    }
    
    /**
     * Action delete dùng để xóa mẫu tin được chọn
     * @param evt
     */
    public void deleteAdmin(ActionEvent evt) {
        // We get the table object
        HtmlDataTable table = getParentDatatable((UIComponent) evt.getSource());
        // We get the object on the selected line.
        Object o = table.getRowData();
        manageAdmin selLh = (manageAdmin) o;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Eproject", "sa", "sa");
            String sql = "delete ADMIN where Username=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, selLh.uname);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
