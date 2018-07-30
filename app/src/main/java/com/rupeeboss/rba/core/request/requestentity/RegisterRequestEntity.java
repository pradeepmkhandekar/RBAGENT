package com.rupeeboss.rba.core.request.requestentity;

/**
 * Created by IN-RB on 15-02-2017.
 */

public class RegisterRequestEntity {

    /**
     * first_Name : Shubhangi
     * last_Name : G
     * contact_No : 689056565
     * Email_Id : shubhangi.g@gmail.com
     * UserPassword : ryryrtyrty=005
     * PAN_No : ALFDF5666ttg
     * City : 677
     * parentBrokerId : 7
     * parentEmpCode : rb40000441
     */

    private String source;
    private String first_Name;
    private String last_Name;
    private String contact_No;
    private String Email_Id;
    private String UserPassword;
    private String PAN_No;
    private String City;
    private String parentBrokerId;
    private String parentEmpCode;
    private String NewEmpCode;

    public String getNewEmpCode() {
        return NewEmpCode;
    }

    public void setNewEmpCode(String newEmpCode) {
        NewEmpCode = newEmpCode;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public String getLast_Name() {
        return last_Name;
    }

    public void setLast_Name(String last_Name) {
        this.last_Name = last_Name;
    }

    public String getContact_No() {
        return contact_No;
    }

    public void setContact_No(String contact_No) {
        this.contact_No = contact_No;
    }

    public String getEmail_Id() {
        return Email_Id;
    }

    public void setEmail_Id(String Email_Id) {
        this.Email_Id = Email_Id;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String UserPassword) {
        this.UserPassword = UserPassword;
    }

    public String getPAN_No() {
        return PAN_No;
    }

    public void setPAN_No(String PAN_No) {
        this.PAN_No = PAN_No;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getParentBrokerId() {
        return parentBrokerId;
    }

    public void setParentBrokerId(String parentBrokerId) {
        this.parentBrokerId = parentBrokerId;
    }

    public String getParentEmpCode() {
        return parentEmpCode;
    }

    public void setParentEmpCode(String parentEmpCode) {
        this.parentEmpCode = parentEmpCode;
    }
}
