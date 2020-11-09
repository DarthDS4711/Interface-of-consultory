/*
 * En esta sección irá la conexión de la base de datos y todas las operaciones
 * Que se necesitarán a lo largo del programa
 * Por lo que la mayoria de funciones debe ir aqui
 */
package Program;

import Database.GestorDB;
import Models.Medic;
import Models.Administrator;
import Models.Cites;
import Models.History;
import Models.Pacient;
import java.util.*;

/**
 *
 * @author Shadowkiller
 */
public class Server {

    //methods
    public Server() {
        this.connection = null;
    }

    public void adminUpdateInfo(String password, String username) {
        Encripter encrypter = new Encripter();
        Administrator admin = new Administrator();
        admin.setUsername(encrypter.encrypt(username, "9123457"));
        admin.setPassword(encrypter.encrypt(password, "9123457"));
        encrypter.updateAdmin(admin);

    }

    public boolean adminSearchInfo(String password, String username) {
        boolean state;
        Administrator a = null;
        Administrator admin = new Administrator();
        Encripter encrypter = new Encripter();
        admin.setUsername(encrypter.encrypt(username, "9123457"));
        admin.setPassword(encrypter.encrypt(password, "9123457"));
        a = encrypter.getAdmin();
        if (a != null) {
            if (a.getPassword().equals(admin.getPassword())) {
                if (a.getUsername().equals(admin.getUsername())) {
                    state = true;
                } else {
                    state = false;
                }
            } else {
                state = false;
            }
        } else {
            state = false;
        }
        return state;
    }

    public boolean medicSearchInfo(String password, String username) {
        //metodo encargado de buscar en la base de datos un médico
        boolean state = false;
        List<Medic> medics = this.getMedics();
        if (medics.size() > 0) {
            for (Medic m : medics) {
                if (m.getMedicId().equals(username)) {
                    if (m.getPassword().equals(password)) {
                        state = true;
                        break;
                    }
                }
            }
        }

        return state;
    }

    //apertura y cierre de conexión
    public void openConecction() {
        this.connection = new GestorDB();
        this.connection.openConection();
    }

    public void closeConnection() {
        this.connection.closeConnection();
    }

    //metodos de pacientes
    //obtener todos los pacientes
    public List<Pacient> getPacients() {
        List<Pacient> pacients = this.connection.getPacients();
        return pacients;
    }

    public void addPacient(Pacient p, java.sql.Date date) {
        //nota debe de detectar el tipo de sesión y agregar
        this.connection.addPacient(p, date);
    }

    public void updatePacient(Pacient p, int type) {
        this.connection.modifyPacient(p, type);
    }

    public int getIDPacient(int id) {
        return this.connection.getPacient(id);
    }

    public void deletePacient(int id) {
        this.connection.deletePacient(id);
    }

    // metodos de medicos
    public List<Medic> getMedics() {
        List<Medic> medics = this.connection.getMedics();
        return medics;
    }

    //agregar médico
    public void addMedic(Medic m) {
        this.connection.addMedic(m);
    }

    public int getMedic(int id) {
        return this.connection.getMedic(id);
    }

    public void updateMedic(Medic m, int type) {
        this.connection.updateMedic(type, m);
    }

    public void deleteMedic(int id) {
        this.connection.deleteMedic(id);
    }

    //metodos de citas
    public void addCite(Cites c) {
        this.connection.addCite(c);
    }

    public List<Cites> getCites() {
        return this.connection.getCites();
    }

    public List<Cites> getCites(int id) {
        return this.connection.getCiteOfMedic(id);
    }

    public int getCite(int id) {
        return this.connection.getCite(id);
    }

    public void updateCite(Cites c) {
        this.connection.updateCite(c);
    }

    public void deleteCite(int id) {
        this.connection.deleteCite(id);
    }

    public void changeInfoMedic(String password) {
        //cambio en la contraseña del médico
    }

    public void changeInfoMedic(Medic medic) {
        //cambio en la información del médico
    }

    //historial medico
    public List<History> getHistory(int id) {
        return this.connection.getHistoryMedical(id);
    }

    public void addHistory(History h) {
        connection.addHistory(h);
    }
    //propieties
    GestorDB connection;//nota dicha propiedad solamente sera usada para buscar medicos
    int typeUser = 0;//detección del tipo de usuario
}
