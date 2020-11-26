/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Program;

import Models.Administrator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel Santiago
 */
public class Encripter {
    //methods
    //propieties
    //generate a key for the process to encrypt
    private SecretKeySpec createKey(String key){
        SecretKeySpec secretKey = null;
        try {
            byte[] keyEncripter = key.getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            keyEncripter = sha.digest(keyEncripter);
            keyEncripter = Arrays.copyOf(keyEncripter, 16);
            secretKey = new SecretKeySpec(keyEncripter, "AES");
            
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo generar la clave");
        }
        return secretKey; 
    }
    public String encrypt(String data, String secretKey){
        String textEncrypt = "";
        try {
            SecretKeySpec key = this.createKey(secretKey);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] dataToEncrypt = data.getBytes("UTF-8");
            byte[] byteEncrypt = cipher.doFinal(dataToEncrypt);
            textEncrypt = Base64.getEncoder().encodeToString(byteEncrypt);
            
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo encriptar el contenido");
        }
        return textEncrypt;
    }
    public Administrator getAdmin(){
        Administrator admin = null;
        File file = new File("credentials.cy");
        try {
            BufferedReader input = new BufferedReader(new FileReader(file));
            String username = input.readLine();
            String password = input.readLine();
            admin = new Administrator();
            admin = new Administrator();
            admin.setUsername(username);
            admin.setPassword(password);
            input.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo encontrar el archivo del administrador");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo encontrar leer el archivo del administrador");
        }
        return admin;
    }
    public void updateAdmin(Administrator a){
        File file = new File("credentials.cy");
        try {
            PrintWriter output = new PrintWriter(file);
            output.println(a.getUsername());
            output.println(a.getPassword());
            output.close();
            JOptionPane.showMessageDialog(null, "Administrador modificado correctamente");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo encontrar el archivo del administrador");
        }
    }
}
