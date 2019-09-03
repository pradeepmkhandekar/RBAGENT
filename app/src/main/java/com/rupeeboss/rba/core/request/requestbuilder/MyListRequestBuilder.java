package com.rupeeboss.rba.core.request.requestbuilder;

import com.rupeeboss.rba.core.RetroRequestBuilder;
import com.rupeeboss.rba.core.WCFRetroRequestBuilder;
import com.rupeeboss.rba.core.response.MyListResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * Created by Rajeev Ranjan on 23/08/2017.
 */

public class MyListRequestBuilder extends WCFRetroRequestBuilder {
    public MyListRequestBuilder.MyListNetworkService getService() {
        return super.build().create(MyListRequestBuilder.MyListNetworkService.class);
    }

    public interface MyListNetworkService {

        @POST("/LoginDtls.svc/XMLService/dsplyRBAHierarchyList")
        Call<MyListResponse> getParentList(@Body HashMap<String, String> bodyParameter);


    }
}
