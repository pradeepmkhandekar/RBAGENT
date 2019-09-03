package com.rupeeboss.rba.core_loan_fm.model;

import java.util.List;

public  class LstCitywiseBankLoanEntity {

    /**
         * Bank_Id : 33
         * Bank_Name : KOTAK MAHINDRA BANK
         * Bank_URL : http://erp.rupeeboss.com/Banklogo/kotak.png
         * lstCityProdBank : [{"Age":"22-60 years","Bank_Id":null,"Bank_Name":null,"Bank_URL":null,"Best_ROI":"8.95%","Foreclosure_charges":"NIL","LTV":"Below 50Lakhs-85%  Above 50Lakhs- 75%","Loan_Amt":"Rs.30 lakhs-30 crore","Min_Cibil_Score":"650","Min_Salary":"Rs.35000 net pm","Min_Work_Exp":"2 year","Per_Lac_EMI":"Rs.897","Prepayment_charges":"NIL","Processing_Fees":"Rs.10000+ GST Rs.5000(Women applicants)","Product_Id":null,"Product_Name":"Home Loan","Product_Type":"Normal","Seqment":"Salaried","Tenure":"3-20 years"},{"Age":"22-65 years","Bank_Id":null,"Bank_Name":null,"Bank_URL":null,"Best_ROI":"8.95%-9.10%","Foreclosure_charges":"Lock in period is 6 month  4% of Principal Outstanding +4% on amount prepaid in last 12 months","LTV":"75%","Loan_Amt":"Rs.30 lakhs-30 crore","Min_Cibil_Score":"650","Min_Salary":"Rs.75000 net pm","Min_Work_Exp":"3 year","Per_Lac_EMI":"Rs.1011 - Rs.1020","Prepayment_charges":"Lock in period is 6 month  4% of Principal Outstanding +4% on amount prepaid in last 12 months","Processing_Fees":"0.50% + GST Rs.5000 (Women applicants)","Product_Id":null,"Product_Name":"Home Loan","Product_Type":"Normal","Seqment":"Self Employed","Tenure":"3-15 years"}]
         */
        private String  Bank_Form_URL;
        private String Bank_Id;
        private String Bank_Name;
        private String Bank_URL;
      private  Boolean is_Cash;


    private List<LstCityBankdetailEntity> lstCityProdBank;

    public String getBank_Form_URL() {
        return Bank_Form_URL;
    }

    public void setBank_Form_URL(String bank_Form_URL) {
        Bank_Form_URL = bank_Form_URL;
    }

            public Boolean getIs_Cash() {
                return is_Cash;
            }

            public void setIs_Cash(Boolean is_Cash) {
                this.is_Cash = is_Cash;
            }

    public String getBank_Id() {
            return Bank_Id;
        }

        public void setBank_Id(String Bank_Id) {
            this.Bank_Id = Bank_Id;
        }

        public String getBank_Name() {
            return Bank_Name;
        }

        public void setBank_Name(String Bank_Name) {
            this.Bank_Name = Bank_Name;
        }

        public String getBank_URL() {
            return Bank_URL;
        }

        public void setBank_URL(String Bank_URL) {
            this.Bank_URL = Bank_URL;
        }

        public List<LstCityBankdetailEntity> getLstCityProdBank() {
            return lstCityProdBank;
        }

        public void setLstCityProdBank(List<LstCityBankdetailEntity> lstCityProdBank) {
            this.lstCityProdBank = lstCityProdBank;
        }

    }