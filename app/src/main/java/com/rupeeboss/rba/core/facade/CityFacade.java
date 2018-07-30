package com.rupeeboss.rba.core.facade;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rupeeboss.rba.core.model.CityEntity;
import com.rupeeboss.rba.utility.Constants;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nilesh Birhade on 23-01-2017.
 */

public class CityFacade {

    Context mContext;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public CityFacade(Context context) {
        mContext = context;
        sharedPreferences = mContext.getSharedPreferences(Constants.SHAREDPREFERENCE_TITLE, mContext.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public boolean storeCityList(List<CityEntity> list) {

        Gson gson = new Gson();
        editor.putString(Constants.CITY_FACADE, gson.toJson(list));
        return editor.commit();
    }


    public List<CityEntity> getCityList() {
        String city = sharedPreferences.getString(Constants.CITY_FACADE, "");
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<CityEntity>>() {
        }.getType();
        return gson.fromJson(city, listType);
    }

    public int getCityId(String cityName) {
        int id = 0;
        List<CityEntity> list = getCityList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCity_Name().matches(cityName)) {
                return id = list.get(i).getCity_Id();
            }
        }
        return id;
    }

    public boolean clearCache() {
        editor.remove(Constants.CITY_FACADE);
        return editor.commit();
    }

}
