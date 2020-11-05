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
import javax.swing.JOptionPane;

/**
 *
 * @author Shadowkiller
 */
public class Server {
    //methods
    public Server(){
        this.connection = null;
    }
    public void adminSearchInfo(String password, String username){
        //metodo encargado de buscar en un archivo local el administrador
    }
    public void medicSearchInfo(String password, String username){
        //metodo encargado de buscar en la base de datos un médico
    }
    public void changeInfoAdmin(String password){
        //cambio en la información (contraseña del administrador)
    }
    public void openConecction(){
        //apertura de conexión a la base de datos por tipo de usuario
        /*if(this.typeUser == 1){//administador
            admin = new Administrator();
            admin.openConnection();
        }
        else if(this.typeUser == 2){//medico
            medic = new Medic();
        }*/
        this.connection = new GestorDB();
        this.connection.openConection();
    }
    public List<Pacient> getPacients(){
        List<Pacient> pacients = this.connection.getPacients();
        for(Pacient p:pacients){
            System.out.println(p.getId());
        }
        return pacients;
    }
    public void addPacient(Pacient p, java.sql.Date date){
        //nota debe de detectar el tipo de sesión y agregar
        this.connection.addPacient(p, date);
    }
    public void changeInfoMedic(String password){
        //cambio en la contraseña del médico
    }
    public void changeInfoMedic(Medic medic){
        //cambio en la información del médico
    }
    //propieties
    GestorDB connection;//nota dicha propiedad solamente sera usada para buscar medicos
    Administrator admin;
    Medic medic;
    int typeUser = 0;//detección del tipo de usuario
}
