
package com.rupeeboss.rba.core.response;

import com.rupeeboss.rba.core.model.FestivalCompaignEntity;


import java.util.List;

public class MasterData {

    private List<FestivalCompaignEntity> Loan;

    public List<FestivalCompaignEntity> getLoan() {
        return Loan;
    }

    public void setLoan(List<FestivalCompaignEntity> loan) {
        Loan = loan;
    }

}
