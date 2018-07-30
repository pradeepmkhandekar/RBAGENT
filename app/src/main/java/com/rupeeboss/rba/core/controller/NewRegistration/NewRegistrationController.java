package com.rupeeboss.rba.core.controller.NewRegistration;

import android.content.Context;

import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestbuilder.NewRegistrationRequestBuilder;
import com.rupeeboss.rba.core.request.requestentity.RegisterRequestEntity;
import com.rupeeboss.rba.core.response.FollowUpResponse;
import com.rupeeboss.rba.core.response.NewRegistrationResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.InputMismatchException;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by IN-RB on 14-02-2017.
 */

public class NewRegistrationController  implements INewRegistration{

    Context mContext;
   NewRegistrationRequestBuilder.NewRegistrationNetworkService newRegistrationNetworkService;

    public static NewRegistrationController newRegistrationController;

    public NewRegistrationController(Context context)
    {
        mContext = context;
        newRegistrationNetworkService = new NewRegistrationRequestBuilder().getService();
    }

    @Override
    public void Register(RegisterRequestEntity entity, final IResponseSubcriber iResponseSubcriber) {

//        HashMap<String, String> bodyParameter = new HashMap<String, String>();
//        bodyParameter.put("first_Name", entity.getFirst_Name());
//        bodyParameter.put("last_Name",  entity.getLast_Name());
//        bodyParameter.put("contact_No",  entity.getContact_No());
//        bodyParameter.put("Email_Id",  entity.getEmail_Id());
//        bodyParameter.put("UserPassword",  entity.getUserPassword());
//        bodyParameter.put("PAN_No",  entity.getPAN_No());
//        bodyParameter.put("City",  entity.getCity());
//        bodyParameter.put("parentBrokerId",  entity.getParentBrokerId());
//        bodyParameter.put("parentEmpCode",  entity.getParentEmpCode());

        newRegistrationNetworkService.saveNewRegestration(entity).enqueue(new Callback<NewRegistrationResponse>() {
            @Override
            public void onResponse(Response<NewRegistrationResponse> response, Retrofit retrofit) {

                if(response.isSuccess()){
                    if(iResponseSubcriber != null){
                        if(response.body().getStatus_Id() == 0){
                            try {
                                iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
                        }
                    }
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
                }else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });

    }


}
