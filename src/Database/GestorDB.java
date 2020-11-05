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
    public GestorDB() {
        //carga de conexión
        this.password = "STR27z";
        this.url = "jdbc:mysql://localhost:3306/hospitaldb?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        this.user = "root";
    }

    public void openConection() {
        //apertura de conexión
        try {
            this.conection = DriverManager.getConnection(this.url, this.user, this.password);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexión con mysql");
        }
    }

    public List<Pacient> getPacients() {
        try {
            this.instruction = conection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(GestorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "SELECT id, Nombre, Apellido1, Apellido2 FROM paciente WHERE Status=0";
        List<Pacient> pacients = new ArrayList<>();
        try {
            ResultSet result = this.instruction.executeQuery(sql);
            while (result.next()) {
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
        closeInstruction();
        return pacients;
    }

    public void addPacient(Pacient pacient, java.sql.Date date) {
        String sql_insert = "INSERT INTO paciente(Nombre, Apellido1, Apellido2, Telefono, FechaNac, Sexo, Peso, Estatura, Status, Edad) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            this.instruction1 = this.conection.prepareStatement(sql_insert);
            this.instruction1.setString(1, pacient.getNombre());
            this.instruction1.setString(2, pacient.getApellido1());
            this.instruction1.setString(3, pacient.getApellido2());
            this.instruction1.setString(4, pacient.getTelefono());
            this.instruction1.setDate(5, date);
            String sex = pacient.getSexo() + "";
            this.instruction1.setString(6, sex);
            this.instruction1.setDouble(7, pacient.getPeso());
            this.instruction1.setDouble(8, pacient.getEstatura());
            this.instruction1.setBoolean(9, pacient.isStatus());
            this.instruction1.setInt(10, pacient.getEdad());
            this.instruction1.executeUpdate();
            JOptionPane.showMessageDialog(null, "Paciente agregado");
        } catch (SQLException ex) {
            Logger.getLogger(GestorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeInstruction1();
    }

    public void addHistoicalMedic(String data) {
    }

    public void addMedic(Medic medic) {
        String sql_insert = "INSERT INTO medico(ID, Nombre, Password, Telefono, Especialidad, Status) VALUES(?, ?, ?, ?, ?, ?)";
        try {
            this.instruction1 = this.conection.prepareStatement(sql_insert);
            this.instruction1.setInt(1, Integer.parseInt(medic.getMedicId()));
            this.instruction1.setString(2, medic.getName());
            this.instruction1.setString(3, medic.getPassword());
            this.instruction1.setString(4, medic.getTelephone());
            this.instruction1.setString(5, medic.getEspeciality());
            this.instruction1.setBoolean(6, medic.isStatus());
            this.instruction1.executeUpdate();
            JOptionPane.showMessageDialog(null, "Medico agregado");
        } catch (SQLException ex) {
            Logger.getLogger(GestorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeInstruction1();
    }

    public List<Medic> getMedics() {
        List<Medic> medics = new ArrayList<>();
        try {
            this.instruction = conection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(GestorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "SELECT ID, Nombre, Telefono, Especialidad FROM medico WHERE Status=0";
        try {
            ResultSet result = this.instruction.executeQuery(sql);
            while (result.next()) {
                Medic m = new Medic();
                m.setMedicId(String.valueOf(result.getInt("ID")));
                m.setName(result.getString("Nombre"));
                m.setTelephone(result.getString("Telefono"));
                m.setEspeciality(result.getString("Especialidad"));
                medics.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeInstruction();
        return medics;
    }

    public void closeInstruction() {
        try {
            this.instruction.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeInstruction1() {
        try {
            this.instruction1.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getPacient(int id) {
        return "test";
    }

    public String getMedic(int id) {
        return "test";
    }

    public String getHistoricalMedic(int idPacient) {
        return "test";
    }

    public void modifyPacient(Pacient pacient, int type) {
        this.instruction1 = null;
        String sql_update = "UPDATE paciente SET Nombre = ?, Apellido1 = ?, Apellido2 = ?, Telefono = ? WHERE id = ?";
        if (type == 1) {
            sql_update = "UPDATE paciente SET Telefono = ? WHERE id = ?";
        }
        try {
            if (type == 1) {
                this.instruction1 = this.conection.prepareStatement(sql_update);
                this.instruction1.setString(1, pacient.getTelefono());
                this.instruction1.setInt(2, Integer.parseInt(pacient.getId()));
                this.instruction1.executeUpdate();
                JOptionPane.showMessageDialog(null, "paciente modificado");
            } else {
                this.instruction1 = this.conection.prepareStatement(sql_update);
                this.instruction1.setString(1, pacient.getNombre());
                this.instruction1.setString(2, pacient.getApellido1());
                this.instruction1.setString(3, pacient.getApellido2());
                this.instruction1.setString(4, pacient.getTelefono());
                this.instruction1.setInt(5, Integer.parseInt(pacient.getId()));
                this.instruction1.executeUpdate();
                JOptionPane.showMessageDialog(null, "paciente modificado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifyMedic(int id, Medic medic) {
    }
    //propieties
    private String driver;
    private String user;
    private String password;
    private String url;
    private Connection conection = null;
    private Statement instruction = null;
    private PreparedStatement instruction1 = null;
}
