package com.rupeeboss.rba.core.controller.sync;

import android.content.Context;

import com.rupeeboss.rba.core.facade.CityFacade;
import com.rupeeboss.rba.core.facade.ProductFacade;
import com.rupeeboss.rba.core.facade.PropertyFacade;
import com.rupeeboss.rba.core.request.requestbuilder.SyncRequestBuilder;
import com.rupeeboss.rba.core.response.CityResponse;
import com.rupeeboss.rba.core.response.LeadResponse;
import com.rupeeboss.rba.core.response.ProductResponse;
import com.rupeeboss.rba.core.response.PropertyResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Nilesh Birhade on 23-01-2017.
 */

public class SyncController implements ISyncController {


    Context mContext;

    SyncRequestBuilder.SyncNetworkService syncNetworkService;

    public SyncController(Context context) {
        this.mContext = context;
        syncNetworkService = new SyncRequestBuilder().getService();
    }


    @Override
    public void getCity() {

        syncNetworkService.getCity().enqueue(new Callback<CityResponse>() {
            @Override
            public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {

                try {
                    if (response.body() != null) {

                        if (response.body().getStatus_Id() == 0) {
                            new CityFacade(mContext).storeCityList(response.body().getData());
                        }
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {


            }
        });

    }



    @Override
    public void getProducts() {

        syncNetworkService.getProduct().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {

                try {
                    if (response.body() != null) {

                        if (response.body().getStatus_Id() == 0) {
                            new ProductFacade(mContext).storeProductList(response.body().getData());
                        }
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {


            }
        });


    }

    @Override
    public void getProperty() {

        syncNetworkService.getProperty().enqueue(new Callback<PropertyResponse>() {
            @Override
            public void onResponse(Call<PropertyResponse> call, Response<PropertyResponse> response) {

                try {
                    if (response.body() != null) {

                        if (response.body().getStatus_Id() == 0) {
                            new PropertyFacade(mContext).storePropertyList(response.body().getData());
                        }
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<PropertyResponse> call, Throwable t) {


            }
        });


    }
}
