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
public class History {

    public int getId_medic() {
        return id_medic;
    }

    public void setId_medic(int id_medic) {
        this.id_medic = id_medic;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTreatment() {
        return treatment;
    }

    //methods
    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public int getId_pacient() {
        return id_pacient;
    }

    public void setId_pacient(int id_pacient) {
        this.id_pacient = id_pacient;
    }

    @Override
    public String toString() {
        return "History{" + "id_medic=" + id_medic + ", reason=" + reason + ", treatment=" + treatment + ", id_pacient=" + id_pacient + '}';
    }
    
    //propieties
    private int id_medic;
    private String reason;
    private String treatment;
    private int id_pacient;
}
