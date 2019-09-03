package com.rupeeboss.rba.core_loan_fm.controller.mainloan;

import android.content.Context;

import com.rupeeboss.rba.core_loan_fm.IResponseSubcriberFM;
import com.rupeeboss.rba.core_loan_fm.requestbuilder.LoanMainRequestBuilder;
import com.rupeeboss.rba.core_loan_fm.requestentity.BankSaveRequest;
import com.rupeeboss.rba.core_loan_fm.requestentity.FmBalanceLoanRequest;
import com.rupeeboss.rba.core_loan_fm.requestentity.FmHomeLoanRequest;
import com.rupeeboss.rba.core_loan_fm.requestentity.FmPersonalLoanRequest;
import com.rupeeboss.rba.core_loan_fm.response.BankForNodeResponse;
import com.rupeeboss.rba.core_loan_fm.response.FmBalanceLoanResponse;
import com.rupeeboss.rba.core_loan_fm.response.FmHomelLoanResponse;
import com.rupeeboss.rba.core_loan_fm.response.FmPersonalLoanResponse;
import com.rupeeboss.rba.core_loan_fm.response.FmSaveQuoteBLResponse;
import com.rupeeboss.rba.core_loan_fm.response.FmSaveQuoteHomeLoanResponse;
import com.rupeeboss.rba.core_loan_fm.response.FmSaveQuotePersonalLoanResponse;
import com.rupeeboss.rba.core_loan_fm.response.NewLoanApplicationResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by IN-RB on 31-01-2018.
 */

public class MainLoanController implements IMainLoan {

    LoanMainRequestBuilder.LoanMainNetworkService loanMainNetworkService;
    Context mContext;


    public MainLoanController(Context mContext) {
        loanMainNetworkService = new LoanMainRequestBuilder().getService();
        this.mContext = mContext;
    }

    // region Home Loan
    @Override
    public void getHLQuoteApplicationData(int count, int QA, String fbaid, String type, final IResponseSubcriberFM iResponseSubcriber) {

        //"count":0,
        // "QandAType":"0"
        HashMap<String, String> body = new HashMap<>();
        body.put("fbaid", fbaid);
        body.put("type", type);
        body.put("count", "" + count);
        body.put("QandAType", "" + QA);

        loanMainNetworkService.getHLQuoteApplication(body).enqueue(new Callback<FmHomelLoanResponse>() {
            @Override
            public void onResponse(Call<FmHomelLoanResponse> call, Response<FmHomelLoanResponse> response) {
                if (response.body() != null) {

                    //callback of data
                    iResponseSubcriber.OnSuccessFM(response.body(), "");

                } else {
                    //failure
                    iResponseSubcriber.OnFailure(new RuntimeException("Enable to reach server, Try again later"));
                }
            }

            @Override
            public void onFailure(Call<FmHomelLoanResponse> call, Throwable t) {
                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof NumberFormatException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Unexpected server response"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });
    }

    @Override
    public void saveHLQuoteData(FmHomeLoanRequest fmHomeLoanRequest, final IResponseSubcriberFM iResponseSubcriber) {

        loanMainNetworkService.saveHLQuote(fmHomeLoanRequest).enqueue(new Callback<FmSaveQuoteHomeLoanResponse>() {
            @Override
            public void onResponse(Call<FmSaveQuoteHomeLoanResponse> call, Response<FmSaveQuoteHomeLoanResponse> response) {
                if (response.body() != null) {

                    //callback of data
                    iResponseSubcriber.OnSuccessFM(response.body(), "");

                } else {
                    //failure
                    iResponseSubcriber.OnFailure(new RuntimeException("Enable to reach server, Try again later"));
                }

            }

            @Override
            public void onFailure(Call<FmSaveQuoteHomeLoanResponse> call, Throwable t) {
                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof NumberFormatException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Unexpected server response"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });


    }
    //endregion

    // region Personal Loan
    @Override
    public void savePLQuoteData(FmPersonalLoanRequest fmPersonalLoanRequest, final IResponseSubcriberFM iResponseSubcriber) {

        loanMainNetworkService.savePLQuote(fmPersonalLoanRequest).enqueue(new Callback<FmSaveQuotePersonalLoanResponse>() {
            @Override
            public void onResponse(Call<FmSaveQuotePersonalLoanResponse> call, Response<FmSaveQuotePersonalLoanResponse> response) {
                if (response.body() != null) {

                    //callback of data
                    iResponseSubcriber.OnSuccessFM(response.body(), "");

                } else {
                    //failure
                    iResponseSubcriber.OnFailure(new RuntimeException("Enable to reach server, Try again later"));
                }

            }

            @Override
            public void onFailure(Call<FmSaveQuotePersonalLoanResponse> call, Throwable t) {
                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof NumberFormatException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Unexpected server response"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });

    }



    @Override
    public void savebankFbABuyData(BankSaveRequest bankSaveRequest, final IResponseSubcriberFM iResponseSubcriber) {


        loanMainNetworkService.savebankFbABuy(bankSaveRequest).enqueue(new Callback<BankForNodeResponse>() {
            @Override
            public void onResponse(Call<BankForNodeResponse> call, Response<BankForNodeResponse> response) {
                if (response.body() != null) {

                    //callback of data
                    iResponseSubcriber.OnSuccessFM(response.body(), "");

                } else {
                    //failure
                    iResponseSubcriber.OnFailure(new RuntimeException("Enable to reach server, Try again later"));
                }

            }

            @Override
            public void onFailure(Call<BankForNodeResponse> call, Throwable t) {
                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof NumberFormatException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Unexpected server response"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });
    }


    // region BL Loan
    @Override
    public void saveBLQuoteData(FmBalanceLoanRequest fmBalanceLoanRequest, final IResponseSubcriberFM iResponseSubcriber) {

        loanMainNetworkService.saveBLQuote(fmBalanceLoanRequest).enqueue(new Callback<FmSaveQuoteBLResponse>() {
            @Override
            public void onResponse(Call<FmSaveQuoteBLResponse> call, Response<FmSaveQuoteBLResponse> response) {
                if (response.body() != null) {

                    //callback of data
                    iResponseSubcriber.OnSuccessFM(response.body(), "");

                } else {
                    //failure
                    iResponseSubcriber.OnFailure(new RuntimeException("Enable to reach server, Try again later"));
                }

            }

            @Override
            public void onFailure(Call<FmSaveQuoteBLResponse> call, Throwable t) {
                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof NumberFormatException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Unexpected server response"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });

    }


    @Override
    public void getBLQuoteApplication(int count, int type, String fbaid, final IResponseSubcriberFM iResponseSubcriber) {

        HashMap<String, String> body = new HashMap<>();
        body.put("FBA_id", fbaid);
        body.put("count", "" + count);
        body.put("type", "" + type);

        loanMainNetworkService.getBLQuoteApplication(body).enqueue(new Callback<FmBalanceLoanResponse>() {
            @Override
            public void onResponse(Call<FmBalanceLoanResponse> call, Response<FmBalanceLoanResponse> response) {
                if (response.body() != null) {

                    //callback of data
                    iResponseSubcriber.OnSuccessFM(response.body(), "");

                } else {
                    //failure
                    iResponseSubcriber.OnFailure(new RuntimeException("Enable to reach server, Try again later"));
                }
            }

            @Override
            public void onFailure(Call<FmBalanceLoanResponse> call, Throwable t) {
                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof NumberFormatException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Unexpected server response"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });
    }

    //delete

    @Override
    public void getdelete_loanrequest(String loan_requestID, final IResponseSubcriberFM iResponseSubcriber) {

        HashMap<String, String> body = new HashMap<>();
        body.put("loan_requestID", loan_requestID);

        loanMainNetworkService.getdelete_loanrequest(body).enqueue(new Callback<FmSaveQuoteHomeLoanResponse>() {
            @Override
            public void onResponse(Call<FmSaveQuoteHomeLoanResponse> call, Response<FmSaveQuoteHomeLoanResponse> response) {
                if (response.body() != null) {

                    //callback of data
                    iResponseSubcriber.OnSuccessFM(response.body(), "");

                } else {
                    //failure
                    iResponseSubcriber.OnFailure(new RuntimeException("Enable to reach server, Try again later"));
                }
            }

            @Override
            public void onFailure(Call<FmSaveQuoteHomeLoanResponse> call, Throwable t) {
                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof NumberFormatException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Unexpected server response"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });
    }

    @Override
    public void getdelete_personalrequest(String loan_requestID, final IResponseSubcriberFM iResponseSubcriber) {

        HashMap<String, String> body = new HashMap<>();
        body.put("loan_requestID", loan_requestID);

        loanMainNetworkService.getdelete_personalrequest(body).enqueue(new Callback<FmSaveQuotePersonalLoanResponse>() {
            @Override
            public void onResponse(Call<FmSaveQuotePersonalLoanResponse> call, Response<FmSaveQuotePersonalLoanResponse> response) {
                if (response.body() != null) {

                    //callback of data
                    iResponseSubcriber.OnSuccessFM(response.body(), "");

                } else {
                    //failure
                    iResponseSubcriber.OnFailure(new RuntimeException("Enable to reach server, Try again later"));
                }
            }

            @Override
            public void onFailure(Call<FmSaveQuotePersonalLoanResponse> call, Throwable t) {
                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof NumberFormatException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Unexpected server response"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });
    }

    @Override
    public void getdelete_balancerequest(String BalanceTransferId, final IResponseSubcriberFM iResponseSubcriber) {

        HashMap<String, String> body = new HashMap<>();
        body.put("BalanceTransferId", BalanceTransferId);

        loanMainNetworkService.getdelete_balancerequest(body).enqueue(new Callback<FmSaveQuoteBLResponse>() {
            @Override
            public void onResponse(Call<FmSaveQuoteBLResponse> call, Response<FmSaveQuoteBLResponse> response) {
                if (response.body() != null) {

                    //callback of data
                    iResponseSubcriber.OnSuccessFM(response.body(), "");

                } else {
                    //failure
                    iResponseSubcriber.OnFailure(new RuntimeException("Enable to reach server, Try again later"));
                }
            }

            @Override
            public void onFailure(Call<FmSaveQuoteBLResponse> call, Throwable t) {
                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof NumberFormatException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Unexpected server response"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });
    }



    //endregion


    @Override
    public void getPLQuoteApplication(int count, int type, String fbaid, final IResponseSubcriberFM iResponseSubcriber) {

        HashMap<String, String> body = new HashMap<>();
        body.put("FBA_id", fbaid);
        body.put("count", "" + count);
        body.put("type", "" + type);

        loanMainNetworkService.getPLQuoteApplication(body).enqueue(new Callback<FmPersonalLoanResponse>() {
            @Override
            public void onResponse(Call<FmPersonalLoanResponse> call, Response<FmPersonalLoanResponse> response) {
                if (response.body() != null) {

                    //callback of data
                    iResponseSubcriber.OnSuccessFM(response.body(), "");

                } else {
                    //failure
                    iResponseSubcriber.OnFailure(new RuntimeException("Enable to reach server, Try again later"));
                }
            }

            @Override
            public void onFailure(Call<FmPersonalLoanResponse> call, Throwable t) {
                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof NumberFormatException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Unexpected server response"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });
    }


    @Override
    public void getLoanApplication(int count, String type, String fbaid,final IResponseSubcriberFM iResponseSubcriber) {
        HashMap<String, String> body = new HashMap<>();
        body.put("FBAID", fbaid);
        body.put("Count", "" + count);
        body.put("Type", "" + type);

        loanMainNetworkService.getLoanApplication(body).enqueue(new Callback<NewLoanApplicationResponse>() {
            @Override
            public void onResponse(Call<NewLoanApplicationResponse> call, Response<NewLoanApplicationResponse> response) {
                if (response.body() != null) {

                    //callback of data
                    iResponseSubcriber.OnSuccessFM(response.body(), "");

                } else {
                    //failure
                    iResponseSubcriber.OnFailure(new RuntimeException("Enable to reach server, Try again later"));
                }
            }

            @Override
            public void onFailure(Call<NewLoanApplicationResponse> call, Throwable t) {
                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof NumberFormatException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Unexpected server response"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });
    }
}

