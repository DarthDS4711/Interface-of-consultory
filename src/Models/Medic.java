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
public class Medic {
    //methods
    public Medic(){
        this.status = false;
    }
    public void setTelephone(String data){
        this.telephone = data;
    }
    public String getTelephone(){return this.telephone;}
    public void setPassword(String data){
        this.password = data;
    }
    public String getPassword(){return this.password;}
    public String getMedicId() {
        return medicId;
    }

    public void setMedicId(String medicId) {
        this.medicId = medicId;
    }

    public String getEspeciality() {
        return especiality;
    }

    public void setEspeciality(String especiality) {
        this.especiality = especiality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    //propieties
    private String telephone;
    private String password;
    private String medicId;
    private String especiality;
    private String name;
    private String firstName;
    private String lastName;
    private boolean status;
}
