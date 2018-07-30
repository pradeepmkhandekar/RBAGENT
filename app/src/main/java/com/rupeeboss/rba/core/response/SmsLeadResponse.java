package com.rupeeboss.rba.core.response;

import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.model.SmsLeadEntity;
import com.rupeeboss.rba.core.model.SmsLeadResult;

import java.util.List;

/**
 * Created by Rajeev Ranjan on 27/02/2017.
 */

public class SmsLeadResponse extends APIResponse {
    /**
     * result : {"empCode":"rb40000401","lstLeads":[{"custName":" Asma  Afreen","leadId":191111,"mobNo":"9052217429"},{"custName":" asma  juveria","leadId":191112,"mobNo":"7386429979"},{"custName":" ASMITA  TAMBE","leadId":191114,"mobNo":"9833780842"},{"custName":" Astha  NULL","leadId":191115,"mobNo":"8587945206"},{"custName":" Asvita  Nair","leadId":191116,"mobNo":"7738779192"},{"custName":" aswathi  ","leadId":191117,"mobNo":"9845696931"},{"custName":" Athira  Omanakuttan","leadId":191118,"mobNo":"9895121077"},{"custName":" atisha  ","leadId":191119,"mobNo":"9816020098"},{"custName":" Atrayee  Majumder","leadId":191121,"mobNo":"9830805444"},{"custName":" Atreyee  ","leadId":191122,"mobNo":"8336998626"},{"custName":" automatio  NULL","leadId":191124,"mobNo":"1565464543"},{"custName":" Avadesh  Singh","leadId":191125,"mobNo":"9036330977"},{"custName":" Avani   Bairagi","leadId":191126,"mobNo":"9425970003"},{"custName":" Avani  Bhagwat","leadId":191127,"mobNo":"9922555454"},{"custName":" Avanti  ","leadId":191128,"mobNo":"7411389561"},{"custName":" Avibo  NULL","leadId":191129,"mobNo":"7838143686"},{"custName":" Avijit   Chatterjee","leadId":191130,"mobNo":"8016327238"},{"custName":" avilash  mishra","leadId":191132,"mobNo":"9691577950"},{"custName":" Avishek  ","leadId":191135,"mobNo":"9830008547"},{"custName":" Ayang  Jamir","leadId":191136,"mobNo":"9774748994"}]}
     */

    private SmsLeadResult result;

    public SmsLeadResult getResult() {
        return result;
    }

    public void setResult(SmsLeadResult result) {
        this.result = result;
    }

}
