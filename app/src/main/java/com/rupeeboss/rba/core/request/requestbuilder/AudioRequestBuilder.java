package com.rupeeboss.rba.core.request.requestbuilder;

import com.rupeeboss.rba.core.WCFRetroRequestBuilder;
import com.rupeeboss.rba.core.response.AudioRecordResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * Created by IN-RB on 02-02-2017.
 */
public class AudioRequestBuilder  extends WCFRetroRequestBuilder {
    public AudioRequestBuilder.AudioNetworkService getService() {

        return super.build().create(AudioRequestBuilder.AudioNetworkService.class);
    }

    public interface AudioNetworkService {
        @POST("/LoginDtls.svc/XMLService/uploadCallRecordData")
        Call<AudioRecordResponse> uploadAudioRecord(@Body HashMap<String, String> bodyParameter);


    }
}
