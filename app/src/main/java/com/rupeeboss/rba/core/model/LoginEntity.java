package com.rupeeboss.rba.core.model;

import java.util.List;

public class LoginEntity {
    /**
     * Assignee : [{"assigneeId":"190","assigneeName":"Rakesh Azad Yadav"},{"assigneeId":"113","assigneeName":"Santosh Galande"},{"assigneeId":"52","assigneeName":"Suresh Gupta"}]
     * BreakDtls : [{"breakKey":1,"breakType":"In Time","time":60},{"breakKey":2,"breakType":"Tea Break","time":600},{"breakKey":3,"breakType":"Lunch","time":1800},{"breakKey":4,"breakType":"Meeting","time":3600},{"breakKey":5,"breakType":"Out Time","time":60},{"breakKey":1002,"breakType":"Meeting At AC mkt","time":1300}]
     * Message : null
     * Products : [{"prodId":1,"prodName":"USED CAR Loan"},{"prodId":2,"prodName":"LAP BT"},{"prodId":3,"prodName":"LRD"},{"prodId":4,"prodName":"Car Loan"},{"prodId":5,"prodName":"Home Loan BT"},{"prodId":6,"prodName":"MERCHANT BUSINESS"},{"prodId":7,"prodName":"LAP"},{"prodId":8,"prodName":"Car Refinance"},{"prodId":9,"prodName":"Personal Loan"},{"prodId":10,"prodName":"Credit Card"},{"prodId":11,"prodName":"WORKING CAPITAL"},{"prodId":12,"prodName":"Home Loan"},{"prodId":13,"prodName":"Business Loan"},{"prodId":1003,"prodName":"Commercial Purchase"},{"prodId":1002,"prodName":"Term Loan"},{"prodId":1004,"prodName":"INSURANCE"},{"prodId":1005,"prodName":"Loan Against Share"},{"prodId":1006,"prodName":"SF"},{"prodId":1007,"prodName":"NIL"},{"prodId":1008,"prodName":"NIL"},{"prodId":1009,"prodName":"MACHINERY LOAN"},{"prodId":1010,"prodName":"LAP-TOP UP"}]
     * brokerId : 4
     * brokerName : Nilesh Madhukar Desale
     * empCode : RB40000085
     * status : [{"statusId":1,"statusName":"Different City"},{"statusId":43,"statusName":"New"},{"statusId":3,"statusName":"Not Contactable"},{"statusId":5,"statusName":"Future Requirement"},{"statusId":6,"statusName":"Visited But Not Interested"},{"statusId":7,"statusName":"Application Form Pending"},{"statusId":8,"statusName":"Property Doc Pending"},{"statusId":9,"statusName":"Login"},{"statusId":10,"statusName":"Meeting Done and To Pick Doc"},{"statusId":12,"statusName":"Pre Login"},{"statusId":13,"statusName":"Disbursed"},{"statusId":15,"statusName":"Disconnecting Call"},{"statusId":16,"statusName":"Switched Off"},{"statusId":17,"statusName":"Cheque Pending"},{"statusId":18,"statusName":"Not Eligible Income"},{"statusId":19,"statusName":"Meeting Done and Customer Will Tell"},{"statusId":20,"statusName":"Call Again"},{"statusId":21,"statusName":"Partial Doc Picked"},{"statusId":22,"statusName":"not called"},{"statusId":23,"statusName":"Follow Meeting"},{"statusId":24,"statusName":"Not Interested"},{"statusId":44,"statusName":"DND-Dont Call Again"},{"statusId":26,"statusName":"Property Not Identified"},{"statusId":27,"statusName":"Not Eligible Property"},{"statusId":28,"statusName":"Login Reject"},{"statusId":29,"statusName":"Meeting Scheduled"},{"statusId":30,"statusName":"Appointment Cancelled"},{"statusId":31,"statusName":"Already Taken"},{"statusId":33,"statusName":"Ringing"},{"statusId":34,"statusName":"Enquiry Not Made"},{"statusId":35,"statusName":"Just Enquiry"},{"statusId":36,"statusName":"Pre Disbursal Stage"},{"statusId":37,"statusName":"Meeting Done and Meet Again"},{"statusId":38,"statusName":"Sanctioned"},{"statusId":39,"statusName":"Wrong Number"},{"statusId":40,"statusName":"Low Loan Amount"},{"statusId":41,"statusName":"Refer to Bank"},{"statusId":42,"statusName":"Follow Mail Sent"},{"statusId":45,"statusName":"Number Not Exist"},{"statusId":46,"statusName":"Lead "},{"statusId":52,"statusName":"Field Visit"},{"statusId":53,"statusName":"Docs Picked Up"},{"statusId":64,"statusName":"not reachable"},{"statusId":55,"statusName":"Appointment Done Not Eligible"},{"statusId":56,"statusName":"Document Picked Not Eligible"},{"statusId":57,"statusName":"Language Barrier"},{"statusId":58,"statusName":"PSU Bank "},{"statusId":59,"statusName":"not intrested"},{"statusId":60,"statusName":"Clash"},{"statusId":61,"statusName":"Follow Up"},{"statusId":65,"statusName":"number does not exist"},{"statusId":63,"statusName":"RBA Leads"},{"statusId":66,"statusName":"Sanctioned but Not Interested"},{"statusId":67,"statusName":"call"},{"statusId":48,"statusName":"Demo Given"},{"statusId":49,"statusName":"Duplicate"}]
     * uName : Suresh Gupta
     * versionCode : 1
     */

    private Object Message;
    private int brokerId;
    private String brokerName;
    private String empCode;
    private String uName;
    private String versionCode;
    private List<AssigneeEntity> Assignee;
    private List<BreakDtlsEntity> BreakDtls;
    private List<ProductsEntity> Products;
    private List<StatusEntity> status;
    private boolean canShareLnk;

    private String Contact_No;

    private String Designation;
    private String Email_Id;


    /**
     * Assignee : []
     * FBAStatusDesc : null
     * Message : null
     * iosVersionCode : 10
     * ssid : 0
     * status : [{"disbstatusType":0,"statusId":1,"statusName":"Different City","statusType":3},{"disbstatusType":0,"statusId":43,"statusName":"New","statusType":0},{"disbstatusType":0,"statusId":84,"statusName":"Online Sanctioned","statusType":0},{"disbstatusType":0,"statusId":5,"statusName":"Future Requirement","statusType":2},{"disbstatusType":0,"statusId":74,"statusName":"Cibil Reject","statusType":3},{"disbstatusType":0,"statusId":7,"statusName":"Application Form Pending","statusType":2},{"disbstatusType":0,"statusId":8,"statusName":"Property Doc Pending","statusType":2},{"disbstatusType":1,"statusId":9,"statusName":"Login","statusType":2},{"disbstatusType":0,"statusId":10,"statusName":"Meeting Done and To Pick Doc","statusType":2},{"disbstatusType":0,"statusId":12,"statusName":"Pre Login","statusType":2},{"disbstatusType":0,"statusId":13,"statusName":"Disbursed","statusType":2},{"disbstatusType":0,"statusId":15,"statusName":"Disconnecting Call","statusType":0},{"disbstatusType":0,"statusId":18,"statusName":"Not Eligible Income","statusType":3},{"disbstatusType":0,"statusId":19,"statusName":"Meeting Done and Customer Will Tell","statusType":2},{"disbstatusType":0,"statusId":20,"statusName":"Call Again","statusType":2},{"disbstatusType":0,"statusId":21,"statusName":"Partial Doc Picked","statusType":2},{"disbstatusType":0,"statusId":23,"statusName":"Follow Meeting","statusType":2},{"disbstatusType":0,"statusId":24,"statusName":"Not Interested","statusType":3},{"disbstatusType":0,"statusId":44,"statusName":"DND-Dont Call Again","statusType":3},{"disbstatusType":0,"statusId":26,"statusName":"Property Not Identified","statusType":2},{"disbstatusType":0,"statusId":27,"statusName":"Not Eligible Property","statusType":3},{"disbstatusType":0,"statusId":28,"statusName":"Login Reject","statusType":3},{"disbstatusType":0,"statusId":29,"statusName":"Meeting Scheduled","statusType":2},{"disbstatusType":0,"statusId":30,"statusName":"Appointment Cancelled","statusType":2},{"disbstatusType":0,"statusId":34,"statusName":"Enquiry Not Made","statusType":3},{"disbstatusType":0,"statusId":35,"statusName":"Just Enquiry","statusType":3},{"disbstatusType":0,"statusId":36,"statusName":"Pre Disbursal Stage","statusType":2},{"disbstatusType":0,"statusId":37,"statusName":"Meeting Done and Meet Again","statusType":2},{"disbstatusType":1,"statusId":38,"statusName":"Sanctioned","statusType":2},{"disbstatusType":0,"statusId":40,"statusName":"Low Loan Amount","statusType":2},{"disbstatusType":0,"statusId":41,"statusName":"Refer to Bank","statusType":2},{"disbstatusType":0,"statusId":42,"statusName":"Follow Mail Sent","statusType":2},{"disbstatusType":0,"statusId":45,"statusName":"Number Not Exist","statusType":3},{"disbstatusType":1,"statusId":46,"statusName":"Lead ","statusType":2},{"disbstatusType":0,"statusId":52,"statusName":"Field Visit","statusType":0},{"disbstatusType":0,"statusId":53,"statusName":"Docs Picked Up","statusType":2},{"disbstatusType":0,"statusId":64,"statusName":"not reachable","statusType":0},{"disbstatusType":0,"statusId":55,"statusName":"Appointment Done Not Eligible","statusType":3},{"disbstatusType":0,"statusId":56,"statusName":"Document Picked Not Eligible","statusType":3},{"disbstatusType":0,"statusId":58,"statusName":"PSU Bank ","statusType":0},{"disbstatusType":0,"statusId":75,"statusName":"Verfication Negative","statusType":3},{"disbstatusType":0,"statusId":60,"statusName":"Clash","statusType":0},{"disbstatusType":0,"statusId":61,"statusName":"Follow Up","statusType":0},{"disbstatusType":0,"statusId":76,"statusName":"Sanction Reject","statusType":3},{"disbstatusType":0,"statusId":66,"statusName":"Sanctioned but Not Interested","statusType":0},{"disbstatusType":0,"statusId":77,"statusName":"Post Disbursal Reject","statusType":3},{"disbstatusType":0,"statusId":73,"statusName":"Application Form Received","statusType":2},{"disbstatusType":0,"statusId":78,"statusName":"Account Open","statusType":0},{"disbstatusType":0,"statusId":79,"statusName":"No Requirement","statusType":0},{"disbstatusType":0,"statusId":80,"statusName":"Pre Sanction","statusType":0},{"disbstatusType":0,"statusId":81,"statusName":"Meeting Done - Not Eligible","statusType":0},{"disbstatusType":0,"statusId":82,"statusName":"FBA Reject","statusType":0},{"disbstatusType":0,"statusId":83,"statusName":"FBA Assigned to field","statusType":0},{"disbstatusType":0,"statusId":49,"statusName":"Duplicate","statusType":0}]
     */

    private int ssid;



    public String getContact_No() {
        return Contact_No;
    }

    public void setContact_No(String contact_No) {
        Contact_No = contact_No;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public String getEmail_Id() {
        return Email_Id;
    }

    public void setEmail_Id(String email_Id) {
        Email_Id = email_Id;
    }

    public int getSsid() {
        return ssid;
    }

    public void setSsid(int ssid) {
        this.ssid = ssid;
    }

    public boolean isCanShareLnk() {
        return canShareLnk;
    }

    public void setCanShareLnk(boolean canShareLnk) {
        this.canShareLnk = canShareLnk;
    }

    private String profilePic;

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public Object getMessage() {
        return Message;
    }

    public void setMessage(Object Message) {
        this.Message = Message;
    }

    public int getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(int brokerId) {
        this.brokerId = brokerId;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getUName() {
        return uName;
    }

    public void setUName(String uName) {
        this.uName = uName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public List<AssigneeEntity> getAssignee() {
        return Assignee;
    }

    public void setAssignee(List<AssigneeEntity> Assignee) {
        this.Assignee = Assignee;
    }

    public List<BreakDtlsEntity> getBreakDtls() {
        return BreakDtls;
    }

    public void setBreakDtls(List<BreakDtlsEntity> BreakDtls) {
        this.BreakDtls = BreakDtls;
    }

    public List<ProductsEntity> getProducts() {
        return Products;
    }

    public void setProducts(List<ProductsEntity> Products) {
        this.Products = Products;
    }

    public List<StatusEntity> getStatus() {
        return status;
    }

    public void setStatus(List<StatusEntity> status) {
        this.status = status;
    }

}