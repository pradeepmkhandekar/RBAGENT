package com.rupeeboss.rba.fragment;


import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.BaseFragment;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;

import com.rupeeboss.rba.core.controller.leadcapture.LeadCapture;

import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.model.AssigneeEntity;
import com.rupeeboss.rba.core.model.AudioEntity;
import com.rupeeboss.rba.core.model.LeadRequest;
import com.rupeeboss.rba.core.model.ProductsEntity;
import com.rupeeboss.rba.core.model.StatusEntity;
import com.rupeeboss.rba.core.response.LeadResponse;
import com.rupeeboss.rba.utility.DateTimePicker;
import com.rupeeboss.rba.utility.Utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddLeadFragment extends BaseFragment implements IResponseSubcriber, View.OnClickListener {

    EditText etName, etEmail, etMobile, etLoanAmt, etMonthlyIncome, etRemark, etDate, etTime, etExpctDisbsDate;
    Spinner spStatus, spProduct, spAssign;
    Button btnSubmit;
    ArrayList<String> arrayStatus, arrayProduct, arrayAssignee;
    ArrayAdapter<String> statusAdapter;
    ArrayAdapter<String> productAdapter;
    ArrayAdapter<String> assigneeAdapter;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
    SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm");
    AudioEntity audioEntity;
    String mobileNumber;

    public AddLeadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.content_rb_add_lead, container, false);

        arrayStatus = new ArrayList<String>();
        arrayProduct = new ArrayList<String>();
        arrayAssignee = new ArrayList<String>();
        initialize(view);
        loadSpinner();

        Calendar calendar = Calendar.getInstance();

        etDate.setText(simpleDateFormat.format(calendar.getTime()));
        etTime.setText(simpleTimeFormat.format(calendar.getTime()));
        etExpctDisbsDate.setText(simpleDateFormat.format(calendar.getTime()));
        mobileNumber = getActivity().getIntent().getStringExtra("PHONE_DIAL_NUMBER");
        etMobile.setText(mobileNumber);


        return view;
    }

    protected View.OnClickListener datePickerDialog = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Utility.hideKeyBoard(view, getActivity());
            DateTimePicker.showDataPickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, monthOfYear, dayOfMonth);
                    String currentDay = simpleDateFormat.format(calendar.getTime());
                    etDate.setText(currentDay);
                    //etDate.setTag(R.id.et_date, calendar.getTime());
                }
            });
        }
    };
    protected View.OnClickListener nextDatePickerDialog = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Utility.hideKeyBoard(view, getActivity());
            DateTimePicker.showNextDataPickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, monthOfYear, dayOfMonth);
                    String currentDay = simpleDateFormat.format(calendar.getTime());
                    etExpctDisbsDate.setText(currentDay);
                    //etDate.setTag(R.id.et_date, calendar.getTime());
                }
            });
        }
    };

    protected View.OnClickListener timePickerDialog = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Utility.hideKeyBoard(view, getActivity());
            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    etTime.setText(selectedHour + ":" + selectedMinute);
                }
            }, hour, minute, false);//Yes 24 hour time
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();
        }
    };

    private void initialize(View view) {
        spAssign = (Spinner) view.findViewById(R.id.sp_lead_assign);
        etName = (EditText) view.findViewById(R.id.et_lead_name);
        etEmail = (EditText) view.findViewById(R.id.et_lead_email);
        etMobile = (EditText) view.findViewById(R.id.et_lead_mobile);
        etRemark = (EditText) view.findViewById(R.id.etRemark);
        etLoanAmt = (EditText) view.findViewById(R.id.et_lead_loan_amount);
        etMonthlyIncome = (EditText) view.findViewById(R.id.et_lead_monthly_income);
        etDate = (EditText) view.findViewById(R.id.et_lead_date);
        etTime = (EditText) view.findViewById(R.id.et_lead_time);
        spStatus = (Spinner) view.findViewById(R.id.sp_lead_status);

        spProduct = (Spinner) view.findViewById(R.id.sp_lead_product);
        btnSubmit = (Button) view.findViewById(R.id.btn_lead_submit);
        btnSubmit.setOnClickListener(this);
        etDate.setOnClickListener(datePickerDialog);
        etTime.setOnClickListener(timePickerDialog);
        etExpctDisbsDate = (EditText) view.findViewById(R.id.etExpctDisbsDate);
        etExpctDisbsDate.setOnClickListener(nextDatePickerDialog);

    }

    private void loadSpinner() {
        statusAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, getStatusList());
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStatus.setAdapter(statusAdapter);

        assigneeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, getAssigneeList());

        assigneeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAssign.setAdapter(assigneeAdapter);

        productAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, getProductList());
        productAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProduct.setAdapter(productAdapter);
    }

    private ArrayList<String> getProductList() {

        List<ProductsEntity> productEntities = new LoginFacade(getActivity()).getProductList();
        for (ProductsEntity entity : productEntities) {
            arrayProduct.add(entity.getProdName());
        }

        return arrayProduct;
    }

    private ArrayList<String> getStatusList() {

        List<StatusEntity> statusEntityList = new LoginFacade(getActivity()).getStatusList();
        for (StatusEntity entity : statusEntityList) {
            arrayStatus.add(entity.getStatusName());
        }

        return arrayStatus;
    }

    private ArrayList<String> getAssigneeList() {
        List<AssigneeEntity> assigneeEntities = new LoginFacade(getActivity()).getAssigneeList();
        for (AssigneeEntity entity : assigneeEntities) {
            arrayAssignee.add(entity.getAssigneeName());
        }

        return arrayAssignee;
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_lead_submit) {
            Utility.hideKeyBoard(view, getActivity());

            if (etName.getText().toString().matches("")) {
                Snackbar.make(etRemark, "Enter Name", Snackbar.LENGTH_LONG).show();
                return;
            }
            if (etEmail.getText().toString().matches("")) {
                Snackbar.make(etRemark, "Enter Email", Snackbar.LENGTH_LONG).show();
                return;
            }
            if (etMobile.getText().toString().matches("")) {
                Snackbar.make(etRemark, "Enter Mobile", Snackbar.LENGTH_LONG).show();
                return;
            }
            if (etLoanAmt.getText().toString().matches("")) {
                Snackbar.make(etRemark, "Enter Loan Amount", Snackbar.LENGTH_LONG).show();
                return;
            }
            if (etMonthlyIncome.getText().toString().matches("")) {
                Snackbar.make(etRemark, "Enter Monthly Income", Snackbar.LENGTH_LONG).show();
                return;
            }

            if (spAssign.getSelectedItem() != null
                    && spProduct.getSelectedItem() != null
                    && spAssign.getSelectedItem() != null) {

                LeadRequest leadRequest = new LeadRequest();
                leadRequest.setName(etName.getText().toString());
                leadRequest.setMobile(etMobile.getText().toString());
                leadRequest.setCity("");
                leadRequest.setCompany("");
                leadRequest.setDesignation("");
                leadRequest.setProfession("");
                leadRequest.setAssgnId("" + new LoginFacade(getActivity()).getAssigneeId((spAssign.getSelectedItem().toString())));
                leadRequest.setFollowupDate(etDate.getText().toString());
                leadRequest.setEMail(etEmail.getText().toString());
                leadRequest.setLoan_amnt(etLoanAmt.getText().toString());
                leadRequest.setMonthly_income(etMonthlyIncome.getText().toString());
                leadRequest.setProduct(spProduct.getSelectedItem().toString());
                leadRequest.setStatus(new LoginFacade(getActivity()).getStatusId((spStatus.getSelectedItem().toString())));
                leadRequest.setEmpCode(String.valueOf(new LoginFacade(getActivity()).getPanNumber()));
                leadRequest.setRemark(etRemark.getText().toString());
                leadRequest.setBrokerId(new LoginFacade(getActivity()).getUser().getBrokerId());
                leadRequest.setExpctDisbsDate(etExpctDisbsDate.getText().toString());
                showDialog();
                new LeadCapture().insertLead(leadRequest, this);
            } else {
                Snackbar.make(etName, "You are not authorize person, please contact your administrator.", Snackbar.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {
        cancelDialog();
        if (response instanceof LeadResponse) {
            if (response.getStatusId() == 0) {
                etEmail.setText("");
                etMobile.setText("");
                etLoanAmt.setText("");
                etMonthlyIncome.setText("");
                etRemark.setText("");
                etName.setText("");
            }
            Snackbar.make(etRemark, response.getMessage(), Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Snackbar.make(etRemark, t.getMessage(), Snackbar.LENGTH_SHORT).show();
    }



}
