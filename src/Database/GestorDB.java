/*
 * En esta sección ira solamente la conexión a la base de datos MySql
 *Junto con sus acciones
 */
package Database;

import Models.Cites;
import Models.History;
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

    //pacientes
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

    public int getPacient(int id) {
        this.instruction1 = null;
        int state = 0;
        try {
            String sql = "SELECT id FROM paciente WHERE Status = 0 and id = ?";
            this.instruction1 = this.conection.prepareStatement(sql);
            this.instruction1.setInt(1, id);
            ResultSet result = this.instruction1.executeQuery();
            if (result.next()) {
                state = 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeInstruction1();
        return state;
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

    public void deletePacient(int id) {
        this.instruction1 = null;
        String sql_delete = "UPDATE paciente SET Status = ? WHERE id = ?";
        try {
            this.instruction1 = this.conection.prepareStatement(sql_delete);
            this.instruction1.setBoolean(1, true);
            this.instruction1.setInt(2, id);
            this.instruction1.executeLargeUpdate();
            JOptionPane.showMessageDialog(null, "Paciente eliminado correctamente");
            closeInstruction1();
        } catch (SQLException ex) {
            Logger.getLogger(GestorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //medicos esta parte solo se agregan métodos de medicos
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
        String sql = "SELECT * FROM medico WHERE Status=0";
        try {
            ResultSet result = this.instruction.executeQuery(sql);
            while (result.next()) {
                Medic m = new Medic();
                m.setMedicId(String.valueOf(result.getInt("ID")));
                m.setName(result.getString("Nombre"));
                m.setPassword(result.getString("Password"));
                m.setTelephone(result.getString("Telefono"));
                m.setEspeciality(result.getString("Especialidad"));
                medics.add(m);
                System.out.println(m.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeInstruction();
        return medics;
    }

    public int getMedic(int id) {
        this.instruction1 = null;
        int state = 0;
        try {
            String sql = "SELECT id FROM medico WHERE Status = 0 and ID = ?";
            this.instruction1 = this.conection.prepareStatement(sql);
            this.instruction1.setInt(1, id);
            ResultSet result = this.instruction1.executeQuery();
            if (result.next()) {
                state = 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeInstruction1();
        return state;
    }

    public void updateMedic(int type, Medic m) {
        this.instruction1 = null;
        String sql_update = "UPDATE medico SET Nombre = ?, Telefono = ? WHERE id = ?";
        if (type == 1) {
            sql_update = "UPDATE medico SET Telefono = ? WHERE id = ?";
        }
        try {
            if (type == 1) {
                this.instruction1 = this.conection.prepareStatement(sql_update);
                this.instruction1.setString(1, m.getTelephone());
                this.instruction1.setInt(2, Integer.parseInt(m.getMedicId()));
                this.instruction1.executeUpdate();
                JOptionPane.showMessageDialog(null, "Medico modificado");
            } else {
                this.instruction1 = this.conection.prepareStatement(sql_update);
                this.instruction1.setString(1, m.getName());
                this.instruction1.setString(2, m.getTelephone());
                this.instruction1.setInt(3, Integer.parseInt(m.getMedicId()));
                this.instruction1.executeUpdate();
                JOptionPane.showMessageDialog(null, "Medico modificado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteMedic(int id) {
        this.instruction1 = null;
        String sql_delete = "UPDATE medico SET Status = ? WHERE ID = ?";
        try {
            this.instruction1 = this.conection.prepareStatement(sql_delete);
            this.instruction1.setBoolean(1, true);
            this.instruction1.setInt(2, id);
            this.instruction1.executeLargeUpdate();
            JOptionPane.showMessageDialog(null, "Medico eliminado correctamente");
            closeInstruction1();
        } catch (SQLException ex) {
            Logger.getLogger(GestorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //metodos de citas
    public void addCite(Cites c) {
        String sql_insert = "INSERT INTO cita(ID_Paciente, Status, ID_Medico, Fecha) VALUES(?, ?, ?, ?)";
        try {
            this.instruction1 = this.conection.prepareStatement(sql_insert);
            this.instruction1.setInt(1, c.getIdPacient());
            this.instruction1.setBoolean(2, c.isAttention());
            this.instruction1.setInt(3, c.getIdMedic());
            this.instruction1.setTimestamp(4, c.getDate());
            this.instruction1.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cita agregada correctamente");
        } catch (SQLException ex) {
            Logger.getLogger(GestorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeInstruction1();
    }

    public List<Cites> getCites() {
        List<Cites> list = new ArrayList<>();
        try {
            this.instruction = conection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(GestorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "SELECT ID, ID_Paciente, ID_Medico, Fecha FROM cita WHERE Status=0";
        try {
            ResultSet result = this.instruction.executeQuery(sql);
            while (result.next()) {
                Cites c = new Cites();
                c.setId(result.getInt("ID"));
                c.setIdPacient(result.getInt("ID_Paciente"));
                c.setIdMedic(result.getInt("ID_Medico"));
                c.setDate(result.getTimestamp("Fecha"));
                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeInstruction();
        return list;
    }
    public List<Cites> getCiteOfMedic(int id){
        List<Cites> cites = new ArrayList<>();
        try {
            String sql = "SELECT ID, ID_Paciente, Fecha FROM cita WHERE Status = 0 and ID_Medico = ?";
            this.instruction1 = this.conection.prepareStatement(sql);
            this.instruction1.setInt(1, id);
            ResultSet result = this.instruction1.executeQuery();
            while(result.next()) {
                Cites c = new Cites();
                c.setId(result.getInt("ID"));
                c.setIdPacient(result.getInt("ID_Paciente"));
                c.setDate(result.getTimestamp("Fecha"));
                cites.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeInstruction1();
        return cites;
    }
    public int getCite(int id) {
        int status = 0;
        try {
            String sql = "SELECT ID FROM cita WHERE Status = 0 and ID = ?";
            this.instruction1 = this.conection.prepareStatement(sql);
            this.instruction1.setInt(1, id);
            ResultSet result = this.instruction1.executeQuery();
            if (result.next()) {
                status = 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeInstruction1();
        return status;
    }

    public void updateCite(Cites c) {
        this.instruction1 = null;
        String sql_update = "UPDATE cita SET ID_Medico = ?, Fecha = ? WHERE id = ?";
        try {
            this.instruction1 = this.conection.prepareStatement(sql_update);
            this.instruction1.setInt(1, c.getIdMedic());
            this.instruction1.setTimestamp(2, c.getDate());
            this.instruction1.setInt(3, c.getId());
            this.instruction1.executeUpdate();
            JOptionPane.showMessageDialog(null, "cita modificado");
        } catch (SQLException ex) {
            Logger.getLogger(GestorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void deleteCite(int id){
        this.instruction1 = null;
        String sql_delete = "UPDATE cita SET Status = ? WHERE ID = ?";
        try {
            this.instruction1 = this.conection.prepareStatement(sql_delete);
            this.instruction1.setBoolean(1, true);
            this.instruction1.setInt(2, id);
            this.instruction1.executeLargeUpdate();
            JOptionPane.showMessageDialog(null, "cita eliminada correctamente");
            closeInstruction1();
        } catch (SQLException ex) {
            Logger.getLogger(GestorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //historial medico
    public List<History> getHistoryMedical(int id){
        List<History> list = new ArrayList<>();
        String sql = "SELECT Tratamiento, Motivo, ID_Medico FROM historial_medico WHERE ID_Paciente = ?";
        try {
            this.instruction1 = this.conection.prepareStatement(sql);
            this.instruction1.setInt(1, id);
            ResultSet result = this.instruction1.executeQuery();
            while(result.next()) {
                History h = new History();
                h.setTreatment(result.getString("Tratamiento"));
                h.setReason(result.getString("Motivo"));
                h.setId_medic(result.getInt("ID_Medico"));
                list.add(h);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeInstruction1();
        return list;
    }
    public void addHistory(History h){
        String sql_insert = "INSERT INTO historial_medico(ID_Paciente, Tratamiento, Motivo, ID_Medico) VALUES(?, ?, ?, ?)";
        try {
            this.instruction1 = this.conection.prepareStatement(sql_insert);
            this.instruction1.setInt(1, h.getId_pacient());
            this.instruction1.setString(2, h.getTreatment());
            this.instruction1.setString(3, h.getReason());
            this.instruction1.setInt(4, h.getId_medic());
            this.instruction1.executeUpdate();
            JOptionPane.showMessageDialog(null, "historial del paciente actualizado correctamente");
        } catch (SQLException ex) {
            Logger.getLogger(GestorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeInstruction1();
    }
    //cierre de conexiones
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

    public void closeConnection() {
        try {
            this.conection.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorDB.class.getName()).log(Level.SEVERE, null, ex);
        }
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
