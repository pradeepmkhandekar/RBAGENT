package com.rupeeboss.rba.core.controller.sync;

import android.content.Context;

import com.rupeeboss.rba.core.facade.CityFacade;
import com.rupeeboss.rba.core.facade.ProductFacade;
import com.rupeeboss.rba.core.facade.PropertyFacade;
import com.rupeeboss.rba.core.request.requestbuilder.SyncRequestBuilder;
import com.rupeeboss.rba.core.response.CityResponse;
import com.rupeeboss.rba.core.response.ProductResponse;
import com.rupeeboss.rba.core.response.PropertyResponse;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

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
            public void onResponse(Response<CityResponse> response, Retrofit retrofit) {
                try {
                    if (response.body().getStatus_Id() == 0) {
                        // iResponseSubcriber.OnSuccess(response.body(), response.body().getMsg());
                        new CityFacade(mContext).storeCityList(response.body().getData());
                    } else {
                        //   iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMsg()));
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    @Override
    public void getProducts() {
        syncNetworkService.getProduct().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Response<ProductResponse> response, Retrofit retrofit) {

                try {
                    if (response.body().getStatus_Id() == 0) {
                        // iResponseSubcriber.OnSuccess(response.body(), response.body().getMsg());
                        new ProductFacade(mContext).storeProductList(response.body().getData());
                    } else {
                        //  iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMsg()));
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    @Override
    public void getProperty() {

        syncNetworkService.getProperty().enqueue(new Callback<PropertyResponse>() {
            @Override
            public void onResponse(Response<PropertyResponse> response, Retrofit retrofit) {
                try {
                    if (response.body().getStatus_Id() == 0) {
                        new PropertyFacade(mContext).storePropertyList(response.body().getData());
                    } else {

                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }
}
