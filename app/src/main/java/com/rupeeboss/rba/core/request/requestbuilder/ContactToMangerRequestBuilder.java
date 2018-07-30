package com.rupeeboss.rba.core.request.requestbuilder;

import com.rupeeboss.rba.core.RetroRequestBuilder;
import com.rupeeboss.rba.core.WCFRetroRequestBuilder;
import com.rupeeboss.rba.core.response.ContactToMangerResponse;
import com.rupeeboss.rba.core.response.EmiCalculatorResponse;
import com.rupeeboss.rba.core.response.FeedbackResponse;
import com.rupeeboss.rba.core.response.SendMailRespose;

import java.util.HashMap;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by IN-RB on 22-06-2017.
 */

public class ContactToMangerRequestBuilder extends WCFRetroRequestBuilder {

    public ContactToManagerNetworkService getService()
    {
        return super.build().create(ContactToMangerRequestBuilder.ContactToManagerNetworkService.class);
    }


    public interface  ContactToManagerNetworkService{

        @POST("/LoginDtls.svc/XMLService/dsplyEmpSupervisorDtls")
        Call<ContactToMangerResponse> getEmpSupervisorDtls(@Body HashMap<String, String> bodyParameter);

        @POST("/LoginDtls.svc/XMLService/sendEmail")
        Call<SendMailRespose> sendMail(@Body HashMap<String, String> bodyParameter);

    }

}
