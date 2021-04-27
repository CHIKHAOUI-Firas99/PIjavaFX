/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author LENOVO
 */
import Alert.AlertDialog;
import org.mindrot.jbcrypt.BCrypt;
public class enc {
    public static boolean checkPassword(String passwordText, String DbHash) {
        
        boolean password_verified = false;
        DbHash = DbHash.substring(0,2)+'a'+DbHash.substring(3);
        if (null == DbHash || !DbHash.startsWith("$2a$")) {
            AlertDialog.showNotification("Error !","vous-devez verifier votre mot de passe ",AlertDialog.image_cross);
        }
        password_verified = BCrypt.checkpw(passwordText, DbHash);
        return (password_verified);
    }
    public static String encryptPassword(String password){
        String pw = BCrypt.hashpw(password, BCrypt.gensalt());
        pw = pw.substring(0,2)+'y'+pw.substring(3);
        return pw;
        
    }
    
}