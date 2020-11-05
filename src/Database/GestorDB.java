/*
 * En esta sección ira solamente la conexión a la base de datos MySql
 *Junto con sus acciones
 */
package Database;

import Models.Medic;
import Models.Pacient;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Shadowkiller
 */
public class GestorDB {
    //methods
    public GestorDB(){
        //carga de conexión
        this.password = "STR27z";
        this.url = "jdbc:mysql://localhost:3306/hospitaldb?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        this.user = "root";
    }
    public void openConection(){
        //apertura de conexión
        try {
            this.conection = DriverManager.getConnection(this.url, this.user, this.password);
            JOptionPane.showMessageDialog(null, "Mysql Funcionando");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión con mysql");
        }
    }
    public List<Pacient> getPacients(){
        try {
            this.instruction = conection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(GestorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "SELECT id, Nombre, Apellido1, Apellido2 FROM paciente";
        List<Pacient> pacients = new ArrayList<>();
        try {
            ResultSet result = this.instruction.executeQuery(sql);
            while(result.next()){
                Pacient p = new Pacient();
                p.setId(result.getString("id"));
                p.setApellido1(result.getString("Apellido1"));
                p.setApellido2(result.getString("Apellido2"));
                p.setNombre(result.getString("Nombre"));
                pacients.add(p);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de consulta");
        }
        this.instruction = null;
        return pacients;
    }
    public void addPacient(Pacient pacient, java.sql.Date date){
         String sql_insert = "INSERT INTO paciente(Nombre, Apellido1, Apellido2, Telefono, FechaNac, Sexo, Peso, Estatura, Status) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            this.instruction1 = this.conection.prepareStatement(sql_insert);
            this.instruction1.setString(1,pacient.getNombre());
            this.instruction1.setString(2,pacient.getApellido1());
            this.instruction1.setString(3,pacient.getApellido2());
            this.instruction1.setString(4,pacient.getTelefono());
            this.instruction1.setDate(5, date);
            String sex = pacient.getSexo() + "";
            this.instruction1.setString(6, sex);
            this.instruction1.setDouble(7, pacient.getPeso());
            this.instruction1.setDouble(8, pacient.getEstatura());
            this.instruction1.setBoolean(9, pacient.isStatus());
            this.instruction1.executeUpdate();
            JOptionPane.showMessageDialog(null, "Paciente agregado");
        } catch (SQLException ex) {
            Logger.getLogger(GestorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
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
    private PreparedStatement instruction1 = null;
}
