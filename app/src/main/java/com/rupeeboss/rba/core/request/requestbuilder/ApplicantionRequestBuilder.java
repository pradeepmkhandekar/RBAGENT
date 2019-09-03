package com.rupeeboss.rba.core.request.requestbuilder;

import com.rupeeboss.rba.core.WCFRetroRequestBuilder;
import com.rupeeboss.rba.core.response.ApplicantResponse;
import com.rupeeboss.rba.core.response.LoginResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * Created by Nilesh Birhade on 25-01-2017.
 */

public class ApplicantionRequestBuilder extends WCFRetroRequestBuilder {

    public ApplicantionNetworkService getService() {
        return super.build().create(ApplicantionNetworkService.class);
    }

    public interface ApplicantionNetworkService {

        @POST("/LoginDtls.svc/XMLService/dsplyHomePersonalLoanAppDtls")
        Call<ApplicantResponse> getApplications(@Body HashMap<String, String> bodyParameter);


    }
}
