/*
 * En esta sección ira solamente la conexión a la base de datos MySql
 *Junto con sus acciones
 */
package Database;

import Models.Medic;
import Models.Pacient;
import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author Shadowkiller
 */
public class GestorDB {
    //methods
    public GestorDB(){
        this.driver = "Test";
        this.password = "Test";
        this.url = "Test";
        this.user = "Test";
    }
    public void openConection(){}
    public void addPacient(Pacient pacient){}
    public void addHistoicalMedic(String data){}
    public void addMedic(Medic medic){}
    public String getPacient(int id){return "test";}
    public String getMedic(int id){return "test";}
    public String getHistoricalMedic(int idPacient){return "test";}
    public void modifyPacient(int id, Pacient pacient){}
    public void modifyMedic(int id, Medic medic){}
    //propieties
    private String driver;
    private String user;
    private String password;
    private String url;
    private Connection conection = null;
    private Statement instruction = null;
}
