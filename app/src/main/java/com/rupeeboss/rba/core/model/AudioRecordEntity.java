package com.rupeeboss.rba.core.model;

import com.orm.SugarRecord;

public class AudioRecordEntity extends SugarRecord {

    private Long id;
    private String empID;
    private String filePath;
    private String mobileNumber;
    private String customerName;
    private int isSync;

    public AudioRecordEntity(AudioRecordEntity audioRecordEntity) {
        this.empID = audioRecordEntity.getEmpID();
        this.filePath = audioRecordEntity.getFilePath();
        this.mobileNumber = audioRecordEntity.getMobileNumber();
        this.customerName = audioRecordEntity.getCustomerName();
        this.isSync = audioRecordEntity.isSync();
    }

    public AudioRecordEntity() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int isSync() {
        return isSync;
    }

    public void setSync(int sync) {
        isSync = sync;
    }
}

