package com.rupeeboss.rba.core_loan_fm;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rupeeboss.rba.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Rajeev Ranjan on 22/01/2018.
 */

public class FinmartRetroRequestBuilder {

    //protected String url = "http://services.rupeeboss.com/LoginDtls.svc/";
    public static Retrofit restAdapter = null;
    // production url
     public static String FINMART_URL = "http://api.magicfinmart.com";
    // Test Environment url
    //   public static String URL = "http://qa.mgfm.in";

    //UAT
    //public static String FINMART_URL = "http://qa.mgfm.in";


    public static final String token = "1234567890";


    protected Retrofit build() {
        if (restAdapter == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            Gson gson = new GsonBuilder()
                    .serializeNulls()
                    // .setLenient()
                    .create();

            okhttp3.OkHttpClient okHttpClient = new okhttp3.OkHttpClient.Builder()
                    .connectTimeout(3, TimeUnit.MINUTES)
                    .writeTimeout(3, TimeUnit.MINUTES)
                    .readTimeout(3, TimeUnit.MINUTES)
                    .addInterceptor(interceptor)
                    .build();

            restAdapter = new Retrofit.Builder()
                    .baseUrl(FINMART_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

        }
        return restAdapter;
    }

}
