/*
 * En esta sección irá la conexión de la base de datos y todas las operaciones
 * Que se necesitarán a lo largo del programa
 * Por lo que la mayoria de funciones debe ir aqui
 */
package Program;

import Database.GestorDB;

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
    //propieties
    GestorDB connection;//nota dicha propiedad solamente sera usada para buscar medicos
}
