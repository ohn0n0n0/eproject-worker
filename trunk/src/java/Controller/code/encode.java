/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.code;

import java.security.MessageDigest;

/**
 *
 * @author Rosse Tran
 */
public class encode {

       // dùng cho mã hóa mật khẩu

    public static String OnewayEncrypt(byte [] data) {
        try {
            MessageDigest dig = MessageDigest.getInstance("MD5");
            dig.update((new String(data).getBytes("UTF8")));
            String str = new String(dig.digest());
            return str;
        } catch (Exception ex) {
            System.out.println("ERR: " + ex.toString());
        }
        return null;
    }
}
