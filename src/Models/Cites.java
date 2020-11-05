/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Shadowkiller
 */
public class Cites {
    //methods
    public Cites(){
        this.attention = false;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
     public boolean isAttention() {
        return attention;
    }

    public void setAttention(boolean Attention) {
        this.attention = Attention;
    }

    public int getIdMedic() {
        return idMedic;
    }

    public void setIdMedic(int idMedic) {
        this.idMedic = idMedic;
    }

    public int getIdPacient() {
        return idPacient;
    }

    public void setIdPacient(int idPacient) {
        this.idPacient = idPacient;
    }
    
    //propieties
    private int id;
    private int idMedic;
    private int idPacient;
    private boolean attention;
}
