/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Database.GestorDB;

/**
 *
 * @author Shadowkiller
 */
public class Medic {
    //methods
    public Medic(){
        this.database = null;
    }
    public void setTelephone(String data){}
    public String getTelephone(){return this.telephone;}
    public void setPassword(String data){}
    public String getPassword(){return this.password;}
    public String getHistoricalMedic(int id){return this.password;}
    public void updateHistoricalMedic(int id, History history){}
    //propieties
    private String telephone;
    private String password;
    private GestorDB database;
}
