package com.rupeeboss.rba.core.controller.audio;

import android.content.Context;

import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.model.AudioEntity;
import com.rupeeboss.rba.core.request.requestbuilder.AudioRequestBuilder;
import com.rupeeboss.rba.core.response.AudioRecordResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit.Callback;
import retrofit.Retrofit;

/**
 * Created by IN-RB on 02-02-2017.
 */
public class AudioController implements IAudio {

    AudioRequestBuilder.AudioNetworkService audioNetworkService;
    Context mContext;

    public AudioController(Context context) {
        audioNetworkService = new AudioRequestBuilder().getService();
        mContext = context;
    }
    @Override
    public void uploadAudioRecord(AudioEntity audioEntity,  final IResponseSubcriber iResponseSubcriber) {

        HashMap<String, String> bodyParameter = new HashMap<String, String>();


        bodyParameter.put("extension", "3gp");
        bodyParameter.put("file_name", audioEntity.getFile_name().substring(78));
        bodyParameter.put("user_id", audioEntity.getUser_id());
        bodyParameter.put("base64string", audioEntity.getConvetedBase64());
        bodyParameter.put("localdb_id", "" + audioEntity.getId());
        bodyParameter.put("lead_id",""+audioEntity.getLead_id());


        audioNetworkService.uploadAudioRecord(bodyParameter).enqueue(new Callback<AudioRecordResponse>() {
            @Override
            public void onResponse(retrofit.Response<AudioRecordResponse> response, Retrofit retrofit) {
                try {
                    if (response.body().getStatus_Id() == 0) {
                        iResponseSubcriber.OnSuccess(response.body(), response.body().getMsg());
                    } else {
                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMsg()));
                    }

                } catch (InterruptedException e) {
                    iResponseSubcriber.OnFailure(new RuntimeException(e.getMessage()));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });

    
    }
}
