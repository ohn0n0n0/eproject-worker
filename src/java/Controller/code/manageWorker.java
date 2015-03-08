/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.code;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.event.ActionEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Rosse Tran
 */
@ManagedBean
@SessionScoped
public class manageWorker {

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

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private String uname;
    private String pass;
    private String fname;
    private boolean gender;
    private String address;
    private Date birth;
    private String identity;
    private String phone;
    private String email;
    private String city;
    private UploadedFile image;

    public manageWorker(String uname, String pass, String fname, boolean gender, String address, Date birth, String identity, String phone, String email, String city, UploadedFile image) {
        this.uname = uname;
        this.pass = pass;
        this.fname = fname;
        this.gender = gender;
        this.address = address;
        this.birth = birth;
        this.identity = identity;
        this.phone = phone;
        this.email = email;
        this.city = city;
        this.image = image;
    }

    public UploadedFile getImage() {
        return image;
    }

    public void setImage(UploadedFile image) {
        this.image = image;
    }

    public String insertWorker() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Eproject", "sa", "sa");
            String sql = "insert into WORKER(Username, Password, Name, Gender, Birthdate, Address, Identity_Cart, Phone, "
                    + "City , Email, Image )values(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, uname);
            ps.setString(2, pass);
            ps.setString(3, fname);
            ps.setBoolean(4, gender);
            ps.setString(5, address);
            ps.setString(6,birth.toString());
            ps.setString(7, identity);
            ps.setString(8, phone);
            ps.setString(9, email);
            ps.setInt(10,city.indexOf(getCity()));
            ps.setString(11, image.toString());
            int x = ps.executeUpdate();
            if (x > 0) {
                return "manageWorker";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "failed";

    }

    public void closePage() {

    }

    public manageWorker() {
    }

    public void handleFileUpload(FileUploadEvent event) {
        try {
            File targetFolder = new File("/images");
            InputStream inputStream = event.getFile().getInputstream();
            OutputStream out = new FileOutputStream(new File(targetFolder,
                    event.getFile().getFileName()));
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            inputStream.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Action dùng để chọn mẫu tin cần cập nhật
     *
     * @param evt
     */
    public void editAdmin(ActionEvent evt) {
        // We get the table object
        HtmlDataTable table = getParentDatatable((UIComponent) evt.getSource());
        // We get the object on the selected line.
        Object o = table.getRowData();
        manageAdmin lh = (manageAdmin) o;
        //this.uname = lh.uname;
        //this.pass = lh.pass;
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
     * @return
     */
    public String update() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Eproject", "sa", "sa");
            String sql = "update WORKER set Password= ? where Username = ?";
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
     * @param evt
     */
    public void deleteAdmin(ActionEvent evt) {
        // We get the table object
        HtmlDataTable table = getParentDatatable((UIComponent) evt.getSource());
        // We get the object on the selected line.
        Object o = table.getRowData();
        manageWorker selLh = (manageWorker) o;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Eproject", "sa", "sa");
            String sql = "delete WORKER where Username=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, selLh.uname);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
