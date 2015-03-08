
package Controller.code;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.UploadedFile;


@ManagedBean
@SessionScoped
public class manageProfileWorker {

    private String uname;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
    private String sector;
    private String time;
    private String city;
    private String exp;
    private String education;
    private String salary;
    private String jp;
    private String proCer;
    private String title;
    private String marial;
    private UploadedFile image;

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getJp() {
        return jp;
    }

    public void setJp(String jp) {
        this.jp = jp;
    }

    public String getProCer() {
        return proCer;
    }

    public void setProCer(String proCer) {
        this.proCer = proCer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMarial() {
        return marial;
    }

    public void setMarial(String marial) {
        this.marial = marial;
    }

    public UploadedFile getImage() {
        return image;
    }

    public void setImage(UploadedFile image) {
        this.image = image;
    }
    
    public void addProfile() {

    }

    public void closePage() {

    }
    public manageProfileWorker() {
    }
    
}
