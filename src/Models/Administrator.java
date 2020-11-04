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
public class Administrator {
    //methods
    public Administrator(){
        this.database = null;
        this.pacient = null;
    }
    public void openConnection(){
        //metodo para la apertura de la base de datos
        this.database = new GestorDB();
        database.openConection();
    }
    public void addPacient(Pacient pacient, java.sql.Date date){}
    public void deletePacient(int id){}
    public void updatePacient(Pacient pacient){}
    public void addMedic(){}
    public void deleteMedic(int id){}
    public void updateMedic(Medic medic){}
    public void addCite(){}
    public void deleteCite(int id){}
    public void updateInfo(String username, String password)
    {
        this.password = password;
        this.username = username;
    }
    //propieties
    private String username;
    private String password;
    private Pacient pacient;
    private GestorDB database;
}
