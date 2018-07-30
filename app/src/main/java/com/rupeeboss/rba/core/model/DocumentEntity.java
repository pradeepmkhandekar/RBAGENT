package com.rupeeboss.rba.core.model;

import java.util.List;

public  class DocumentEntity {
            /**
             * ProdName : Home Loan
             * docDtls : ["Passport Size photographs","licence/ IT PAN card","Proof of identify"]
             * prodId : 12
             */

            private String ProdName;
            private int prodId;
            private List<String> docDtls;

            public String getProdName() {
                return ProdName;
            }

            public void setProdName(String ProdName) {
                this.ProdName = ProdName;
            }

            public int getProdId() {
                return prodId;
            }

            public void setProdId(int prodId) {
                this.prodId = prodId;
            }

            public List<String> getDocDtls() {
                return docDtls;
            }

            public void setDocDtls(List<String> docDtls) {
                this.docDtls = docDtls;
            }
        }