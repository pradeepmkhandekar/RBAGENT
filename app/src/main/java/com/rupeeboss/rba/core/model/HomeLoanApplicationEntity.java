package com.rupeeboss.rba.core.model;

public class HomeLoanApplicationEntity {
            /**
             * AppId : 56
             * ApplnStatus : Not Completed
             * ApplntName : Ram Dgdh Satre
             * ProdName :
             * appLink : http://beta.erp.rupeeboss.com/homeloan/home_loan_application_form.aspx?appid=56
             */

            private int AppId;
            private String ApplnStatus;
            private String ApplntName;
            private String ProdName;
            private String appLink;

            public int getAppId() {
                return AppId;
            }

            public void setAppId(int AppId) {
                this.AppId = AppId;
            }

            public String getApplnStatus() {
                return ApplnStatus;
            }

            public void setApplnStatus(String ApplnStatus) {
                this.ApplnStatus = ApplnStatus;
            }

            public String getApplntName() {
                return ApplntName;
            }

            public void setApplntName(String ApplntName) {
                this.ApplntName = ApplntName;
            }

            public String getProdName() {
                return ProdName;
            }

            public void setProdName(String ProdName) {
                this.ProdName = ProdName;
            }

            public String getAppLink() {
                return appLink;
            }

            public void setAppLink(String appLink) {
                this.appLink = appLink;
            }
        }