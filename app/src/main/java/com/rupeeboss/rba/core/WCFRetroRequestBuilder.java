package com.rupeeboss.rba.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



import java.util.concurrent.TimeUnit;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public abstract class WCFRetroRequestBuilder {

    // production url
 //   protected String LOAN_URL = "http://beta.services.rupeeboss.com/LoginDtls.svc/";
      protected String LOAN_URL = "http://services.rupeeboss.com/LoginDtls.svc/";


    static Retrofit restAdapter = null;

    protected Retrofit build() {
        if (restAdapter == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            okhttp3.OkHttpClient okHttpClient = new okhttp3.OkHttpClient.Builder()
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .writeTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(1, TimeUnit.MINUTES)
                    .addInterceptor(interceptor)
                    .build();

            restAdapter = new Retrofit.Builder()
                    .baseUrl(LOAN_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

        }
        return restAdapter;
    }

}