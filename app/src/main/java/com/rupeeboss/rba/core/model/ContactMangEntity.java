package com.rupeeboss.rba.core.model;

import android.os.Parcel;
import android.os.Parcelable;

public  class ContactMangEntity implements Parcelable {
        /**
         * reportingEmpCompanyId : 1000
         * reportingEmpDesignation : Manager
         * reportingEmpEmail : mahendra.thakare@rupeeboss.com
         * reportingEmpMobile : 9920638907
         * reportingEmpName : Mahendra Thakare
         */

        private String reportingEmpCompanyId;
        private String reportingEmpDesignation;
        private String reportingEmpEmail;
        private String reportingEmpMobile;
        private String reportingEmpName;

    protected ContactMangEntity(Parcel in) {
        reportingEmpCompanyId = in.readString();
        reportingEmpDesignation = in.readString();
        reportingEmpEmail = in.readString();
        reportingEmpMobile = in.readString();
        reportingEmpName = in.readString();
    }

    public static final Creator<ContactMangEntity> CREATOR = new Creator<ContactMangEntity>() {
        @Override
        public ContactMangEntity createFromParcel(Parcel in) {
            return new ContactMangEntity(in);
        }

        @Override
        public ContactMangEntity[] newArray(int size) {
            return new ContactMangEntity[size];
        }
    };

    public String getReportingEmpCompanyId() {
            return reportingEmpCompanyId;
        }

        public void setReportingEmpCompanyId(String reportingEmpCompanyId) {
            this.reportingEmpCompanyId = reportingEmpCompanyId;
        }

        public String getReportingEmpDesignation() {
            return reportingEmpDesignation;
        }

        public void setReportingEmpDesignation(String reportingEmpDesignation) {
            this.reportingEmpDesignation = reportingEmpDesignation;
        }

        public String getReportingEmpEmail() {
            return reportingEmpEmail;
        }

        public void setReportingEmpEmail(String reportingEmpEmail) {
            this.reportingEmpEmail = reportingEmpEmail;
        }

        public String getReportingEmpMobile() {
            return reportingEmpMobile;
        }

        public void setReportingEmpMobile(String reportingEmpMobile) {
            this.reportingEmpMobile = reportingEmpMobile;
        }

        public String getReportingEmpName() {
            return reportingEmpName;
        }

        public void setReportingEmpName(String reportingEmpName) {
            this.reportingEmpName = reportingEmpName;
        }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(reportingEmpCompanyId);
        dest.writeString(reportingEmpDesignation);
        dest.writeString(reportingEmpEmail);
        dest.writeString(reportingEmpMobile);
        dest.writeString(reportingEmpName);
    }
}