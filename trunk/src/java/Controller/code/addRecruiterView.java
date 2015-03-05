/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.code;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Rosse Tran
 */
@ManagedBean
@SessionScoped
public class addRecruiterView {

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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public UploadedFile getLogo() {
        return logo;
    }

    public void setLogo(UploadedFile logo) {
        this.logo = logo;
    }

    private String uname;
    private String pass;
    private String fname;
    private String address;
    private String introducted;
    private String identity;
    private String phone;
    private String email;
    private String website;
    private String sector;
    private UploadedFile logo;

    public void insertRecruiter() {

    }

    public void closePage() {

    }

    public addRecruiterView() {
    }

}
