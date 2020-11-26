/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Program;

import AppInterface.Configuration;
import AppInterface.Login;
import java.io.File;
/**
 *
 * @author Shadowkiller
 */
public class Preloader {
    public static void main(String args[]){
        File file = new File("credentials.cy");
        if(!file.exists()){
            Configuration config = new Configuration();
            config.setVisible(true);
        }
        else{
            Login login = new Login();
            login.setVisible(true);
        }
    }
}
