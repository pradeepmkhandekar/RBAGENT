package com.rupeeboss.rba.core.request.requestbuilder;

import com.rupeeboss.rba.core.WCFRetroRequestBuilder;
import com.rupeeboss.rba.core.response.DialerResponse;
import com.rupeeboss.rba.core.response.ValidateMobileResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * Created by IN-RB on 02-02-2017.
 */
public class DialerRequestBuilder  extends WCFRetroRequestBuilder{

    public DialerNetworkService getService() {

        return super.build().create(DialerRequestBuilder.DialerNetworkService.class);
    }

    public interface DialerNetworkService {


        @POST("/LoginDtls.svc/XMLService/dsplyLeadDataRBA")
        Call<DialerResponse> getLeadData(@Body HashMap<String, String> bodyParameter);

        @POST("/LoginDtls.svc/xmlservice/chkLead")
        Call<ValidateMobileResponse> validateMobile(@Body HashMap<String, String> bodyParameter);
    }
}
