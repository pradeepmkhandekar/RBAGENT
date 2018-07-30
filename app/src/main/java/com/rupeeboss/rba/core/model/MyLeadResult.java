package com.rupeeboss.rba.core.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public  class MyLeadResult implements Parcelable {
        /**
         * empCode :
         * lstLeads : [{"custName":"Rajeev","leadId":253736,"mobNo":"87075407764","status":"Property Doc Pending"},{"custName":"sghh","leadId":253972,"mobNo":"958627555","status":"New"},{"custName":"sumit broker1","leadId":253123,"mobNo":"9874361452","status":"Property Doc Pending"},{"custName":"sumit2 broker","leadId":253126,"mobNo":"9780521365","status":"Different City"},{"custName":"sumit3 broker","leadId":253732,"mobNo":"9412548752","status":"Different City"}]
         * totalCount : 4
         */

        private String empCode;
        private int totalCount;
        private List<LeadDetailsEntity> lstLeads;

        public String getEmpCode() {
            return empCode;
        }

        public void setEmpCode(String empCode) {
            this.empCode = empCode;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public List<LeadDetailsEntity> getLstLeads() {
            return lstLeads;
        }

        public void setLstLeads(List<LeadDetailsEntity> lstLeads) {
            this.lstLeads = lstLeads;
        }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.empCode);
        dest.writeInt(this.totalCount);
        dest.writeTypedList(this.lstLeads);
    }

    public MyLeadResult() {
    }

    protected MyLeadResult(Parcel in) {
        this.empCode = in.readString();
        this.totalCount = in.readInt();
        this.lstLeads = in.createTypedArrayList(LeadDetailsEntity.CREATOR);
    }

    public static final Parcelable.Creator<MyLeadResult> CREATOR = new Parcelable.Creator<MyLeadResult>() {
        @Override
        public MyLeadResult createFromParcel(Parcel source) {
            return new MyLeadResult(source);
        }

        @Override
        public MyLeadResult[] newArray(int size) {
            return new MyLeadResult[size];
        }
    };
}