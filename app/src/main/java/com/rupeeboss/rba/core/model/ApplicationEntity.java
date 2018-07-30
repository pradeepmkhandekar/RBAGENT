package com.rupeeboss.rba.core.model;

import java.util.List;

public class ApplicationEntity {
    private List<HomeLoanApplicationEntity> lstHomeLoanDtls;

    public List<HomeLoanApplicationEntity> getLstHomeLoanDtls() {
        return lstHomeLoanDtls;
    }

    public void setLstHomeLoanDtls(List<HomeLoanApplicationEntity> lstHomeLoanDtls) {
        this.lstHomeLoanDtls = lstHomeLoanDtls;
    }


}