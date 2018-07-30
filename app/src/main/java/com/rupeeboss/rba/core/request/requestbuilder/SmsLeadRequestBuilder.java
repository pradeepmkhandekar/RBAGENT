package com.rupeeboss.rba.core.request.requestbuilder;

import com.rupeeboss.rba.core.WCFRetroRequestBuilder;
import com.rupeeboss.rba.core.request.requestentity.MyLeadRequestEntity;
import com.rupeeboss.rba.core.request.requestentity.SendSmsRequestEntity;
import com.rupeeboss.rba.core.request.requestentity.SmsLeadRequestEntity;
import com.rupeeboss.rba.core.response.MyLeadResponse;
import com.rupeeboss.rba.core.response.SendSmsMobileResponse;
import com.rupeeboss.rba.core.response.SmsLeadResponse;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Rajeev Ranjan on 27/02/2017.
 */

public class SmsLeadRequestBuilder extends WCFRetroRequestBuilder {
    public SmsLeadRequestBuilder.SmsLeadNetworkService getService() {

        return super.build().create(SmsLeadRequestBuilder.SmsLeadNetworkService.class);
    }

    public interface SmsLeadNetworkService {
        @POST("/LoginDtls.svc/XMLService/dsplyLeadsForSMS")
        Call<SmsLeadResponse> getShareMessage(@Body SmsLeadRequestEntity smsLeadRequestEntity);

        @POST("/LoginDtls.svc/XMLService/sendSMSToLeads")
        Call<SendSmsMobileResponse> sendSmsMobile(@Body SendSmsRequestEntity sendSmsRequestEntity);

        @POST("/LoginDtls.svc/XMLService/dsplyBrokerLeads")
        Call<MyLeadResponse> getMyLead(@Body MyLeadRequestEntity sendSmsRequestEntity);


    }
}
