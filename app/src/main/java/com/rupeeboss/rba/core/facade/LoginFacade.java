package com.rupeeboss.rba.core.facade;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.rupeeboss.rba.core.model.AssigneeEntity;
import com.rupeeboss.rba.core.model.BreakDtlsEntity;
import com.rupeeboss.rba.core.model.LoginEntity;
import com.rupeeboss.rba.core.model.ProductsEntity;
import com.rupeeboss.rba.core.model.StatusEntity;
import com.rupeeboss.rba.utility.Constants;

import java.util.List;

/**
 * Created by Rajeev Ranjan on 02/02/2017.
 */

public class LoginFacade {
    Context mContext;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public LoginFacade(Context context) {
        mContext = context;
        sharedPreferences = mContext.getSharedPreferences(Constants.SHAREDPREFERENCE_TITLE, mContext.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


    public boolean storeUser(LoginEntity LoginEntity) {
        try {
            Gson gson = new Gson();
            editor.putString(Constants.LOGIN_FACADE, gson.toJson(LoginEntity));
            return editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean storeUserProfile(String profilePic) {
        try {
            editor.putString(Constants.PROFILE_URL, profilePic);
            return editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getUserProfile() {
        try {
            return sharedPreferences.getString(Constants.PROFILE_URL, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public boolean storeLoginDetails(String panNumber, String password, String deviceId) {
        editor.putString(Constants.PAN_NUMBER, panNumber);
        editor.putString(Constants.PASSWORD, password);
        editor.putString(Constants.DEVICE_ID, deviceId);
        return editor.commit();
    }

    public String getPanNumber() {
        return sharedPreferences.getString(Constants.PAN_NUMBER, "");
    }

    public String getPassword() {
        return sharedPreferences.getString(Constants.PASSWORD, "");
    }

    public String getDeviceId() {
        return sharedPreferences.getString(Constants.DEVICE_ID, "");
    }

    public List<AssigneeEntity> getAssigneeList() {
        return getUser().getAssignee();
    }

    public int getAssigneeId(String assignee) {
        int assignId = 0;
        List<AssigneeEntity> assigneeEntities = getUser().getAssignee();
        for (AssigneeEntity entity : assigneeEntities) {
            if (entity.getAssigneeName().equals(assignee)) {
                assignId =  Integer.parseInt(entity.getAssigneeId());
                break;
            }
        }
        return assignId;

    }

    public List<BreakDtlsEntity> getBreakDetailsList() {
        return getUser().getBreakDtls();
    }

    public List<ProductsEntity> getProductList() {
        return getUser().getProducts();
    }

    public String getProductName(int prodId) {
        String prodName = "";
        List<ProductsEntity> propertyEntities = getUser().getProducts();
        for (ProductsEntity entity : propertyEntities) {
            if (entity.getProdId() == prodId) {
                prodName = entity.getProdName();
                break;
            }
        }
        return prodName;
    }

    public int getProductId(String product) {
        int productId = 0;
        List<ProductsEntity> propertyEntities = getUser().getProducts();
        for (ProductsEntity entity : propertyEntities) {
            if (entity.getProdName().equals(product)) {
                productId = entity.getProdId();
                break;
            }
        }
        return productId;
    }

    public List<StatusEntity> getStatusList() {
        return getUser().getStatus();
    }

    public int getStatusId(String status) {
        int statusId = 0;
        List<StatusEntity> statusEntityList = getUser().getStatus();
        for (StatusEntity entity : statusEntityList) {
            if (entity.getStatusName().equals(status)) {
                statusId = entity.getStatusId();
                break;
            }
        }
        return statusId;
    }

    public LoginEntity getUser() {
        String user = sharedPreferences.getString(Constants.LOGIN_FACADE, "");
        Gson gson = new Gson();
        LoginEntity loginEntity = gson.fromJson(user, LoginEntity.class);
        return gson.fromJson(user, LoginEntity.class);
    }

    public boolean clearLoginCache() {
        editor.remove(Constants.LOGIN_FACADE);
        editor.remove(Constants.PAN_NUMBER);
        editor.remove(Constants.PASSWORD);
        editor.remove(Constants.DEVICE_ID);
        return editor.commit();
    }
}
