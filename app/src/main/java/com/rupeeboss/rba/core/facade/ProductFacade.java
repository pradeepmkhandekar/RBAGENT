package com.rupeeboss.rba.core.facade;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rupeeboss.rba.core.model.ProductEntity;
import com.rupeeboss.rba.utility.Constants;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IN-RB on 23-01-2017.
 */
public class ProductFacade {
    Context mContext;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public ProductFacade(Context context) {
        mContext = context;
        sharedPreferences = mContext.getSharedPreferences(Constants.SHAREDPREFERENCE_TITLE, mContext.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    public boolean clearCache() {
        editor.remove(Constants.PRODUCT_FACADE);
        return editor.commit();
    }

    public boolean storeProductList(List<ProductEntity> list) {

        Gson gson = new Gson();
        editor.putString(Constants.PRODUCT_FACADE, gson.toJson(list));
        return editor.commit();
    }

    public List<ProductEntity> getProductList() {
        String prod = sharedPreferences.getString(Constants.PRODUCT_FACADE, "");
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<ProductEntity>>() {
        }.getType();
        return gson.fromJson(prod, listType);
    }

    public int getProductId(String prodName) {
        int id = 0;
        List<ProductEntity> list = getProductList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getProduct_Name().matches(prodName)) {
                return id = list.get(i).getProduct_Id();
            }
        }
        return id;
    }

    public String getProductType(int prodId) {
        String type = "";
        List<ProductEntity> list = getProductList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getProduct_Id() == prodId) {
                return type = list.get(i).getProduct_Name();
            }
        }
        return type;
    }

}
