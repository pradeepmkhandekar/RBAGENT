package com.rupeeboss.rba.core.controller.quote;

import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.request.requestentity.HomeLoanRequest;

/**
 * Created by Nilesh Birhade on 25-01-2017.
 */

public interface IDisplayQuote {

    void getQuote(int productId,String BrokerID, IResponseSubcriber iResponseSubcriber);

    void sendSelectedQuoteInfo(String quoteId, String bankId, String roiType, String loanEligible, String processingFees);
}
