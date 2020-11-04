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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTreatment() {
        return Treatment;
    }

    public void setTreatment(String Treatment) {
        this.Treatment = Treatment;
    }
     public boolean isAttention() {
        return attention;
    }

    public void setAttention(boolean Attention) {
        this.attention = Attention;
    }
    //propieties
    private int id;
    private String reason;
    private String Treatment;
    private boolean attention;

   
}
