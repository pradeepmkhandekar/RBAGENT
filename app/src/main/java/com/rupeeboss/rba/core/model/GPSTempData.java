package com.rupeeboss.rba.core.model;

import com.orm.SugarRecord;

/**
 * Created by Nilesh Birhade on 30-11-2016.
 */

public class GPSTempData extends SugarRecord {

    private String deviceID;
    private String lattitude;
    private String longitude;
    private String flag;
    private String employeeDateTime;
    private String status;


    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getLattitude() {
        return lattitude;
    }

    public void setLattitude(String lattitude) {
        this.lattitude = lattitude;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getEmployeeDateTime() {
        return employeeDateTime;
    }

    public void setEmployeeDateTime(String employeeDateTime) {
        this.employeeDateTime = employeeDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public GPSTempData() {

    }
}
