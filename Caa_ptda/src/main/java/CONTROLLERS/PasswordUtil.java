/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLLERS;
import org.mindrot.jbcrypt.BCrypt;
/**
 *
 * @author filip
 */
public class PasswordUtil {
    public static String hashPassword(String passwordPlana) {
        return BCrypt.hashpw(passwordPlana, BCrypt.gensalt());
    }

    public static boolean verificarPassword(String passwordPlana, String hashed) {
        return BCrypt.checkpw(passwordPlana, hashed);
    }
}
