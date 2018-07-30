package com.rupeeboss.rba.core.model;

public class PropertyEntity {
        /**
         * Property_Id : 1
         * Property_Type : Property Identified & ready to occupy
         */

        private int Property_Id;
        private String Property_Type;

        public int getProperty_Id() {
            return Property_Id;
        }

        public void setProperty_Id(int Property_Id) {
            this.Property_Id = Property_Id;
        }

        public String getProperty_Type() {
            return Property_Type;
        }

        public void setProperty_Type(String Property_Type) {
            this.Property_Type = Property_Type;
        }
    }