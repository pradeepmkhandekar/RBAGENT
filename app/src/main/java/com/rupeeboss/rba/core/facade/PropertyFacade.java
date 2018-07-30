package com.rupeeboss.rba.core.facade;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rupeeboss.rba.core.model.ProductEntity;
import com.rupeeboss.rba.core.model.PropertyEntity;
import com.rupeeboss.rba.utility.Constants;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IN-RB on 23-01-2017.
 */
public class PropertyFacade {
    Context mContext;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public PropertyFacade(Context context) {
        mContext = context;
        sharedPreferences = mContext.getSharedPreferences(Constants.SHAREDPREFERENCE_TITLE, mContext.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    public boolean clearCache() {
        editor.remove(Constants.PROPERTY_FACADE);
        return editor.commit();
    }

    public boolean storePropertyList(List<PropertyEntity> list) {

        Gson gson = new Gson();
        editor.putString(Constants.PROPERTY_FACADE, gson.toJson(list));
        return editor.commit();
    }

    public List<PropertyEntity> getPropertyList() {
        String prod = sharedPreferences.getString(Constants.PROPERTY_FACADE, "");
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<PropertyEntity>>() {
        }.getType();
        return gson.fromJson(prod, listType);
    }

    public int getPropertyId(String propName) {
        int id = 0;
        List<PropertyEntity> list = getPropertyList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getProperty_Type().matches(propName)) {
                return id = list.get(i).getProperty_Id();
            }
        }
        return id;
    }

}
