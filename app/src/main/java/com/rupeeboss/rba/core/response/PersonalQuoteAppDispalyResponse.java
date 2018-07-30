package com.rupeeboss.rba.core.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.rupeeboss.rba.core.APIResponse;

import com.rupeeboss.rba.core.model.ApplicationDisplayEntity;
import com.rupeeboss.rba.core.model.PersonalApplicationDisplayEntity;
import com.rupeeboss.rba.core.model.PersonalQuoteDisplayEntity;

import java.util.List;

/**
 * Created by IN-RB on 14-02-2017.
 */
public class PersonalQuoteAppDispalyResponse extends APIResponse implements Parcelable {


    /**
     * data : [{"ID":1694,"ApplicantNme":"pratik","LoanRequired":"19000000","ApplicantIncome":"100000","Turnover":"1000000","status":"Failure","ProductId":7,"url":"http://beta.erp.rupeeboss.com/homeloan/home_loan_application_form.aspx?qoutid=1694"},{"ID":1845,"ApplicantNme":"Nisha","LoanRequired":null,"ApplicantIncome":"250000","Turnover":"0","status":"Failure","ProductId":7,"url":"http://beta.erp.rupeeboss.com/homeloan/home_loan_application_form.aspx?qoutid=1845"},{"ID":2111,"ApplicantNme":"Nisha","LoanRequired":"2000000","ApplicantIncome":"250000","Turnover":"0","status":"Failure","ProductId":7,"url":"http://beta.erp.rupeeboss.com/homeloan/home_loan_application_form.aspx?qoutid=2111"},{"ID":2112,"ApplicantNme":"Nisha","LoanRequired":"2000000","ApplicantIncome":"250000","Turnover":"0","status":"Failure","ProductId":7,"url":"http://beta.erp.rupeeboss.com/homeloan/home_loan_application_form.aspx?qoutid=2112"},{"ID":2123,"ApplicantNme":"Nisha","LoanRequired":"2000000","ApplicantIncome":"250000","Turnover":"0","status":"Failure","ProductId":7,"url":"http://beta.erp.rupeeboss.com/homeloan/home_loan_application_form.aspx?qoutid=2123"},{"ID":2124,"ApplicantNme":"Nisha","LoanRequired":"2000000","ApplicantIncome":"250000","Turnover":"0","status":"Failure","ProductId":7,"url":"http://beta.erp.rupeeboss.com/homeloan/home_loan_application_form.aspx?qoutid=2124"},{"ID":2151,"ApplicantNme":"pratik","LoanRequired":"2900000","ApplicantIncome":"100000","Turnover":"1000000","status":"Success","ProductId":12,"url":"http://beta.erp.rupeeboss.com/homeloan/home_loan_application_form.aspx?qoutid=2151"},{"ID":2152,"ApplicantNme":"pratik","LoanRequired":"2900000","ApplicantIncome":"100000","Turnover":"1000000","status":"Success","ProductId":12,"url":"http://beta.erp.rupeeboss.com/homeloan/home_loan_application_form.aspx?qoutid=2152"},{"ID":2154,"ApplicantNme":"pratik","LoanRequired":"2900000","ApplicantIncome":"100000","Turnover":"1000000","status":"Success","ProductId":12,"url":"http://beta.erp.rupeeboss.com/homeloan/home_loan_application_form.aspx?qoutid=2154"},{"ID":2184,"ApplicantNme":"pratik","LoanRequired":"2900000","ApplicantIncome":"100000","Turnover":"1000000","status":"Success","ProductId":12,"url":"http://beta.erp.rupeeboss.com/homeloan/home_loan_application_form.aspx?qoutid=2184"}]
     * application : null
     */


    private List<PersonalQuoteDisplayEntity> data;
    private List<PersonalApplicationDisplayEntity> application;

    public List<PersonalApplicationDisplayEntity> getApplicationList() {
        return application;
    }

    public void setApplication(List<PersonalApplicationDisplayEntity> application) {
        this.application = application;
    }


    public List<PersonalQuoteDisplayEntity> getQuoteList() {
        return data;
    }

    public void setData(List<PersonalQuoteDisplayEntity> data) {
        this.data = data;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeTypedList(this.data);
        dest.writeTypedList(this.application);

    }

    protected PersonalQuoteAppDispalyResponse(Parcel in){

        this.data = in.createTypedArrayList(PersonalQuoteDisplayEntity.CREATOR);
        this.application = in.createTypedArrayList(PersonalApplicationDisplayEntity.CREATOR);
    }

    public static final Parcelable.Creator<PersonalQuoteAppDispalyResponse> CREATOR = new Creator<PersonalQuoteAppDispalyResponse>() {
        @Override
        public PersonalQuoteAppDispalyResponse createFromParcel(Parcel source) {
            return new PersonalQuoteAppDispalyResponse(source);
        }

        @Override
        public PersonalQuoteAppDispalyResponse[] newArray(int size) {
            return new PersonalQuoteAppDispalyResponse[size];
        }
    };


}
