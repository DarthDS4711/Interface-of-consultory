/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Timestamp;

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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
    
    //propieties
    private int id;
    private int idMedic;
    private int idPacient;
    private java.sql.Timestamp date;
    private boolean attention;
}
