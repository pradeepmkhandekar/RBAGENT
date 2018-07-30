package com.rupeeboss.rba.core.response;

import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.model.ProductEntity;

import java.util.List;

/**
 * Created by Nilesh Birhade on 23-01-2017.
 */

public class ProductResponse extends APIResponse {

    /**
     * data : [{"Product_Id":1,"Product_Name":"USED CAR Loan"},{"Product_Id":2,"Product_Name":"LAP BT"},{"Product_Id":3,"Product_Name":"LRD"},{"Product_Id":4,"Product_Name":"Car Loan"},{"Product_Id":5,"Product_Name":"Home Loan BT"},{"Product_Id":6,"Product_Name":"MERCHANT BUSINESS"},{"Product_Id":7,"Product_Name":"LAP"},{"Product_Id":8,"Product_Name":"Car Refinance"},{"Product_Id":9,"Product_Name":"Personal Loan"},{"Product_Id":10,"Product_Name":"Credit Card"},{"Product_Id":11,"Product_Name":"WORKING CAPITAL"},{"Product_Id":12,"Product_Name":"Home Loan"},{"Product_Id":13,"Product_Name":"Business Loan"}]
     * status_Id : 0
     * msg : data delievered
     */


    private List<ProductEntity> data;


    public List<ProductEntity> getData() {
        return data;
    }

    public void setData(List<ProductEntity> data) {
        this.data = data;
    }

}
