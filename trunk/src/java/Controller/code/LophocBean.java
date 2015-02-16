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
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.event.ActionEvent;

/**
 *
 * @author MR VU
 */
@ManagedBean
@RequestScoped
public class LophocBean {

    private String msLop;
    private String tenLop;
    private String tenGVCN;
 
    /** Creates a new instance of LophocBean */
    public LophocBean() {
    }
 
    public LophocBean(String msLop, String tenLop, String tenGVCN) {
        this.msLop = msLop;
        this.tenLop = tenLop;
        this.tenGVCN = tenGVCN;
    }
 
    public String getMsLop() {
        return msLop;
    }
 
    public void setMsLop(String msLop) {
        this.msLop = msLop;
    }
 
    public String getTenGVCN() {
        return tenGVCN;
    }
 
    public void setTenGVCN(String tenGVCN) {
        this.tenGVCN = tenGVCN;
    }
 
    public String getTenLop() {
        return tenLop;
    }
 
    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }
 
    /**
     * Liệt kê các record của bảng lớp học để hiển thị lên table
     * @return
     */
    public ArrayList<LophocBean> getAllLophoc() {
        ArrayList<LophocBean> lst = new ArrayList<LophocBean>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=demo", "sa", "sa");
            ResultSet rs = con.createStatement().executeQuery("select * from sinhvien");
            while (rs.next()) {
                LophocBean lh = new LophocBean(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3));
                lst.add(lh);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        return lst;
    }
 
    /**
     * Action dùng để insert 1 mẫu tin mới
     * @return success nếu thành công
     */
    public String InsertNew() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=demo", "sa", "sa");
            String sql = "insert into sinhvien values(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, msLop);
            ps.setString(2, tenLop);
            ps.setString(3, tenGVCN);
            int x = ps.executeUpdate();
            if (x > 0) {
                return "success";
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
    public void deleteAction(ActionEvent evt) {
        // We get the table object
        HtmlDataTable table = getParentDatatable((UIComponent) evt.getSource());
        // We get the object on the selected line.
        Object o = table.getRowData();
        LophocBean selLh = (LophocBean) o;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=demo", "sa", "sa");
            String sql = "delete sinhvien where masv=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, selLh.msLop);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    /**
     * Action dùng để chọn mẫu tin cần cập nhật
     * @param evt
     */
    public void editBook(ActionEvent evt) {
        // We get the table object
        HtmlDataTable table = getParentDatatable((UIComponent) evt.getSource());
        // We get the object on the selected line.
        Object o = table.getRowData();
        LophocBean lh = (LophocBean) o;
        this.msLop = lh.msLop;
        this.tenLop = lh.tenLop;
        this.tenGVCN = lh.tenGVCN;
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
            Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=GC0563", "sa", "");
            String sql = "update lophoc set tenLop=?, tenGVCN=? where msLop=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tenLop);
            ps.setString(2, tenGVCN);
            ps.setString(3, msLop);
            int x = ps.executeUpdate();
            if (x > 0) {
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "failed";
    }
    
}
