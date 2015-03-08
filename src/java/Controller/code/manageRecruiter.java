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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.event.ActionEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Rosse Tran
 */
@ManagedBean
@SessionScoped
public class manageRecruiter {

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

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntroducted() {
        return introducted;
    }

    public void setIntroducted(String introducted) {
        this.introducted = introducted;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    
    public UploadedFile getLogo() {
        return logo;
    }

    public void setLogo(UploadedFile logo) {
        this.logo = logo;
    }

    public manageRecruiter(String uname, String pass, String fname, int sector, String address, String introducted, String phone, 
            String email, String website) {
        this.uname = uname;
        this.pass = pass;
        this.fname = fname;
        this.sector = sector;
        this.address = address;
        this.introducted = introducted;

        this.phone = phone;
        this.email = email;
        this.website = website;
        
        //this.logo = logo;
        
    }

    private String uname;
    private String pass;
    private String fname;
    private String address;
    private String introducted;
    private String phone;
    private String email;
    private String website;
    private int sector;

    public int getSector() {
        return sector;
    }

    public void setSector(int sector) {
        this.sector = sector;
    }
    private UploadedFile logo;

    public void closePage() {

    }

    public manageRecruiter() {
    }

    public ArrayList<manageRecruiter> getAllRecruiter() {
        ArrayList<manageRecruiter> lst = new ArrayList<manageRecruiter>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Eproject", "sa", "sa");
            ResultSet rs = con.createStatement().executeQuery("select Username, Password, Fullname, Sector, Address, "
                    + "Phone, Email, Introduced, Website from RECRUITER");
            while (rs.next()) {
                manageRecruiter lh = new manageRecruiter(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9));
                        rs.getString(10);
                lst.add(lh);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lst;
    }

    public String insertRecruiter() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Eproject", "sa", "sa");
            String sql = "insert into RECRUITER values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, uname);
            ps.setString(2, encode.OnewayEncrypt(pass.getBytes()));
            ps.setString(3, fname);
            //ps.setInt(4, sector.indexOf(getSector()));
            ps.setString(5, address);
            ps.setString(6, fname);
            ps.setString(7, phone);
            ps.setString(8, email);
            ps.setString(9, introducted);
            ps.setString(10, website);
            InsertImage();
            int x = ps.executeUpdate();
            if (x > 0) {
                return "manageRecruiter";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "failed";
    }

    /**
     * Action dùng để chọn mẫu tin cần cập nhật
     *
     */
    public void editAdmin(ActionEvent evt) {
        // We get the table object
        HtmlDataTable table = getParentDatatable((UIComponent) evt.getSource());
        // We get the object on the selected line.
        Object o = table.getRowData();
        manageRecruiter lh = (manageRecruiter) o;
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
     *
     */
    public String update() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Eproject", "sa", "sa");
            String sql = "update RECRUITER set Password= ? where Username = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, encode.OnewayEncrypt((pass.getBytes())));
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
     *
     */
    public void deleteRecruiter(ActionEvent evt) {
        // We get the table object
        HtmlDataTable table = getParentDatatable((UIComponent) evt.getSource());
        // We get the object on the selected line.
        Object o = table.getRowData();
        manageAdmin selLh = (manageAdmin) o;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Eproject", "sa", "sa");
            String sql = "delete RECRUITER where Username=?";
            PreparedStatement ps = con.prepareStatement(sql);
            //ps.setString(1, selLh.);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String destination="../Image/";
    
    public void InsertImage() {  
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Eproject", "sa", "sa");
            String sql = "Insert into RECRUITER (Logo) values (?)";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setBinaryStream(1, logo.getInputstream());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }  
//    private List<City> cityList;
//
//   public List<city> getPhoneList() {
//        if (phoneList == null) {
//            emf = Persistence.createEntityManagerFactory("manager1");
//            em = emf.createEntityManager();
//            Query query = em.createQuery("select p from Phone p");
//            phoneList = query.getResultList();
//            em.close();
//        }

}
