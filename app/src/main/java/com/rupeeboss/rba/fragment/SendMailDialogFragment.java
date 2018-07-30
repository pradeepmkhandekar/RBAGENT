package com.rupeeboss.rba.fragment;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.contactmanager.ContactMangController;
import com.rupeeboss.rba.core.controller.smslead.SmsLead;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.model.ContactMangEntity;
import com.rupeeboss.rba.utility.Utility;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class SendMailDialogFragment extends DialogFragment implements View.OnClickListener, IResponseSubcriber {

    EditText etSubject, etBody;
    Button btnSend, btnClose;
    ContactMangEntity contactMangEntity;
    String strmsgBody = "";
//    StringBuilder strmsgBody =new StringBuilder();
    LoginFacade loginFacade;
    ProgressDialog dialog;


    public SendMailDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            contactMangEntity = getArguments().getParcelable(Utility.CONTACT_MANAGER);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_send_mail_dialog, null, false);
        loginFacade = new LoginFacade(getActivity());
        setCancelable(false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        initilize(view);

        return view;
    }

    private void initilize(View view) {
        etSubject = (EditText) view.findViewById(R.id.etSubject);
        etBody = (EditText) view.findViewById(R.id.etBody);
        btnSend = (Button) view.findViewById(R.id.btnSend);
        btnClose = (Button) view.findViewById(R.id.btnClose);

        btnSend.setOnClickListener(this);
        btnClose.setOnClickListener(this);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        // mListener = null;
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnSend) {

            sendMail();
        } else if (view.getId() == R.id.btnClose) {
            getDialog().dismiss();
        }


    }

    private void sendMail() {
        if (contactMangEntity != null) {
            if (TextUtils.isEmpty(etSubject.getText().toString())) {
                etSubject.setError("ENTER SUBJECT");
                etSubject.requestFocus();
                return;
            } else if (TextUtils.isEmpty(etBody.getText().toString())) {
                etBody.setError("ENTER BODY");
                etBody.requestFocus();
                return;
            }
            strmsgBody = "RBA name: " + loginFacade.getUser().getUName()
                    + "\n" +"RBA pan number: " + loginFacade.getPanNumber().toUpperCase() + "\n"
                    + "Message: " +etBody.getText().toString();


//            strmsgBody.append("RBA name :" + loginFacade.getUser().getUName());
//            strmsgBody.append("\r\n ");
//            strmsgBody.append("RBA pan number :" + loginFacade.getPanNumber().toUpperCase());
//            strmsgBody.append("\r\n ");
//            strmsgBody.append(etBody.getText().toString());
            showDialog();
            new ContactMangController(getActivity()).sendMail(contactMangEntity.getReportingEmpEmail().toString(), strmsgBody, etSubject.getText().toString(), contactMangEntity.getReportingEmpCompanyId().toString(), this);
        } else {

            Snackbar.make(etSubject, "Insufficient Data.", Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {

        cancelDialog();
        Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();
        getDialog().dismiss();


    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        getDialog().dismiss();
        Snackbar.make(etSubject, t.getMessage(), Snackbar.LENGTH_SHORT).show();
    }


    //    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }

    protected void cancelDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    protected void showDialog() {
        showDialog("Loading...");
    }

    protected void showDialog(String msg) {
        dialog = ProgressDialog.show(getActivity(), "", msg, true);
    }
}
