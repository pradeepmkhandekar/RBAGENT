package com.rupeeboss.rba.core.request.requestbuilder;

import com.rupeeboss.rba.core.WCFRetroRequestBuilder;
import com.rupeeboss.rba.core.response.ShareMessageResponse;

import java.util.HashMap;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by IN-RB on 02-02-2017.
 */

public class ShareMessageRequestBuilder extends WCFRetroRequestBuilder{



    public ShareMessageRequestBuilder.ShareMessageNetworkService getService() {

        return super.build().create(ShareMessageRequestBuilder.ShareMessageNetworkService.class);
    }

    public interface ShareMessageNetworkService {
        @POST("/LoginDtls.svc/XMLService/dsplyMsgLnkDtls")
        Call<ShareMessageResponse> getShareMessage(@Body HashMap<String, String> bodyParameter);


    }
}
