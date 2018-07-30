package com.rupeeboss.rba.incomeSimulator.fragment;


import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.rupeeboss.rba.BaseFragment;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.incomeSimulator.IncomeSimulatorActivity;
import com.rupeeboss.rba.utility.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class IncomeSimulatorFragment extends BaseFragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, TextWatcher {



    SeekBar skPtClient, skMedia, skWorkSpace, skFlate, skPhBook;
    EditText etPtClient, etMedia, etWorkSpace, etFlate, etPhBook;
    TextView txtTotal;
    Switch PtSwitch;
    LinearLayout lyMedia, lyPhoneBook, lyWorkSpace, lyFlate;
    int seekBarProgress = 1;

    public IncomeSimulatorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_income_simulator, container, false);
        initilize(view);
        return view;
    }

    private void initilize(View view) {

        txtTotal = (TextView) view.findViewById(R.id.txtTotal);
        PtSwitch = (Switch) view.findViewById(R.id.PtSwitch);

        lyMedia = (LinearLayout) view.findViewById(R.id.lyMedia);
        lyWorkSpace = (LinearLayout) view.findViewById(R.id.lyWorkSpace);
        lyFlate = (LinearLayout) view.findViewById(R.id.lyFlate);
        lyPhoneBook = (LinearLayout) view.findViewById(R.id.lyPhoneBook);

        skPtClient = (SeekBar) view.findViewById(R.id.skPtClient);
        skMedia = (SeekBar) view.findViewById(R.id.skMedia);
        skWorkSpace = (SeekBar) view.findViewById(R.id.skWorkSpace);
        skFlate = (SeekBar) view.findViewById(R.id.skFlate);
        skPhBook = (SeekBar) view.findViewById(R.id.skPhBook);

        etPtClient = (EditText) view.findViewById(R.id.etPtClient);
        etMedia = (EditText) view.findViewById(R.id.etMedia);
        etWorkSpace = (EditText) view.findViewById(R.id.etWorkSpace);
        etFlate = (EditText) view.findViewById(R.id.etFlate);
        etPhBook = (EditText) view.findViewById(R.id.etPhone);

        setPotClient(false);
        setChildLayout(true);

        skPtClient.setMax(100000);
        skPtClient.setProgress(1);

        skMedia.setMax(5000);
        skMedia.setProgress(1);

        skWorkSpace.setMax(20000);
        skWorkSpace.setProgress(1);

        skFlate.setMax(5000);
        skFlate.setProgress(1);

        //skPhBook
        skPhBook.setMax(1000);
        skPhBook.setProgress(1);

        setListener();
    }

    private void setPotClient(boolean bln) {
        etPtClient.setEnabled(bln);
        skPtClient.setEnabled(bln);

        if (bln) {
            etPtClient.getBackground().clearColorFilter();
            etPtClient.requestFocus();
            resetChild();
            setChildLayout(false);

        } else {

            etPtClient.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
            setChildLayout(true);

        }
    }

    private void setChildLayout(boolean bln) {
        if (bln) {
            lyMedia.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.login_background));
            lyWorkSpace.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.login_background));
            lyFlate.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.login_background));
            lyPhoneBook.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.login_background));

        } else {
            lyMedia.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.lightGrey));
            lyWorkSpace.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.lightGrey));
            lyFlate.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.lightGrey));
            lyPhoneBook.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.lightGrey));


        }


        etMedia.setEnabled(bln);
        etWorkSpace.setEnabled(bln);
        etFlate.setEnabled(bln);
        etPhBook.setEnabled(bln);

        skMedia.setEnabled(bln);
        skWorkSpace.setEnabled(bln);
        skFlate.setEnabled(bln);
        skPhBook.setEnabled(bln);


    }


    private void setTotal(String media, String workspace, String flat, String phone, String PtClient) {
        int num_media, num_workspace, num_flat, num_phone, num_PtClient = 0;
        int intTotal;

        //region Validation
        if (media.trim().equals("")) {
            num_media = 0;
        } else {
            num_media = Integer.parseInt(media);
        }

        if (workspace.trim().equals("")) {
            num_workspace = 0;
        } else {
            num_workspace = Integer.parseInt(workspace);
        }

        if (flat.trim().equals("")) {
            num_flat = 0;
        } else {
            num_flat = Integer.parseInt(flat);
        }

        if (phone.trim().equals("")) {
            num_phone = 0;
        } else {
            num_phone = Integer.parseInt(phone);
        }

        if (PtClient.trim().equals("")) {
            num_PtClient = 0;
        } else {
            num_PtClient = Integer.parseInt(PtClient);
        }
        //endregion

        if (PtSwitch.isChecked()) {
            intTotal = num_PtClient;
        } else {

            intTotal = num_media + num_workspace + num_flat + num_phone;


        }
        txtTotal.setText("" + intTotal);


    }

    private void setListener() {

        skPtClient.setOnSeekBarChangeListener(this);
        skMedia.setOnSeekBarChangeListener(this);
        skWorkSpace.setOnSeekBarChangeListener(this);
        skFlate.setOnSeekBarChangeListener(this);
        skPhBook.setOnSeekBarChangeListener(this);


        etPtClient.addTextChangedListener(this);
        etMedia.addTextChangedListener(this);
        etWorkSpace.addTextChangedListener(this);
        etFlate.addTextChangedListener(this);
        etPhBook.addTextChangedListener(this);

        PtSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                setPotClient(isChecked);

            }
        });

    }

    @Override
    public void onClick(View v) {

    }


    private void resetChild() {
        // Change OF Potential Client all child should be reset
        etMedia.setText("");
        etWorkSpace.setText("");
        etFlate.setText("");
        etPhBook.setText("");

        skMedia.setProgress(0);
        skWorkSpace.setProgress(0);
        skFlate.setProgress(0);
        skPhBook.setProgress(0);
    }

    // region seekEvent
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


        switch (seekBar.getId()) {
            case R.id.skPtClient:
                if (progress >= seekBarProgress) {
                    if (fromUser) {
                        etPtClient.setText(String.valueOf(((long) progress) * 1));
                        etPtClient.requestFocus();
                        etPtClient.setSelection(etPtClient.getText().length());
                        Constants.hideKeyBoard(txtTotal, getActivity());
                    }
                } else {
                    etPtClient.setText("0");
                    etPtClient.requestFocus();
                    etPtClient.setSelection(etPtClient.getText().length());
                }

                break;

            case R.id.skMedia:
                if (progress >= seekBarProgress) {
                    if (fromUser) {
                        etMedia.setText(String.valueOf((long) progress));
                        etMedia.requestFocus();
                        etMedia.setSelection(etMedia.getText().length());
                        Constants.hideKeyBoard(txtTotal, getActivity());
                    }
                } else {
                    etMedia.setText("0");
                    etMedia.requestFocus();
                    etMedia.setSelection(etMedia.getText().length());
                }
                break;

            case R.id.skWorkSpace:
                if (progress >= seekBarProgress) {
                    if (fromUser) {
                        etWorkSpace.setText(String.valueOf((long) progress));
                        etWorkSpace.requestFocus();
                        etWorkSpace.setSelection(etWorkSpace.getText().length());
                        Constants.hideKeyBoard(txtTotal, getActivity());
                    }
                } else {
                    etWorkSpace.setText("0");
                    etWorkSpace.requestFocus();
                    etWorkSpace.setSelection(etWorkSpace.getText().length());
                }
                break;

            case R.id.skFlate:
                if (progress >= seekBarProgress) {
                    if (fromUser) {
                        etFlate.setText(String.valueOf(((long) progress)));
                        etFlate.requestFocus();
                        etFlate.setSelection(etFlate.getText().length());
                        Constants.hideKeyBoard(txtTotal, getActivity());
                    }
                } else {
                    etFlate.setText("0");
                    etFlate.requestFocus();
                    etFlate.setSelection(etFlate.getText().length());
                }
                break;

            case R.id.skPhBook:
                if (progress >= seekBarProgress) {
                    if (fromUser) {
                        etPhBook.setText(String.valueOf(((long) progress)));
                        etPhBook.requestFocus();
                        etPhBook.setSelection(etPhBook.getText().length());
                        Constants.hideKeyBoard(txtTotal, getActivity());
                    }
                } else {
                    etPhBook.setText("0");
                    etPhBook.requestFocus();
                    etPhBook.setSelection(etPhBook.getText().length());
                }
                break;
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
    //endregion

    // region TextWatch
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        if (etPtClient.getText().hashCode() == s.hashCode()) {
            if (!etPtClient.getText().toString().equals("") && !etPtClient.getText().toString().equals(null)) {
                int client = Integer.parseInt(etPtClient.getText().toString());
                if (client > 1) {
                    skPtClient.setProgress(client / 1);
                } else {
                    //skPtClient.setProgress(0);
                    skPtClient.setProgress(client / 1);
                    etPtClient.setSelection(etPtClient.getText().length());
                }
                setTotal(etMedia.getText().toString(), etWorkSpace.getText().toString(), etFlate.getText().toString(), etPhBook.getText().toString(), etPtClient.getText().toString());
            }

        }
        if (etMedia.getText().hashCode() == s.hashCode()) {
            if (!etMedia.getText().toString().equals("") && !etMedia.getText().toString().equals(null)) {
                int mediac = Integer.parseInt(etMedia.getText().toString());
                if (mediac > 1) {
                    skMedia.setProgress(mediac);
                } else {
                    skMedia.setProgress(0);
                    etMedia.setSelection(etMedia.getText().length());
                }
                setTotal(etMedia.getText().toString(), etWorkSpace.getText().toString(), etFlate.getText().toString(), etPhBook.getText().toString(), etPtClient.getText().toString());
            }

        } else if (etWorkSpace.getText().hashCode() == s.hashCode()) {
            if (!etWorkSpace.getText().toString().equals("") && !etWorkSpace.getText().toString().equals(null)) {
                int workSpace = Integer.parseInt(etWorkSpace.getText().toString());
                if (workSpace > 1) {
                    skWorkSpace.setProgress(workSpace);
                } else {
                    skWorkSpace.setProgress(0);
                    etWorkSpace.setSelection(etWorkSpace.getText().length());
                }
                setTotal(etMedia.getText().toString(), etWorkSpace.getText().toString(), etFlate.getText().toString(), etPhBook.getText().toString(), etPtClient.getText().toString());
            }

        } else if (etFlate.getText().hashCode() == s.hashCode()) {
            if (!etFlate.getText().toString().equals("") && !etFlate.getText().toString().equals(null)) {
                int flate = Integer.parseInt(etFlate.getText().toString());
                if (flate > 1) {
                    skFlate.setProgress(flate);
                } else {
                    skFlate.setProgress(0);
                    etFlate.setSelection(etFlate.getText().length());
                }
                setTotal(etMedia.getText().toString(), etWorkSpace.getText().toString(), etFlate.getText().toString(), etPhBook.getText().toString(), etPtClient.getText().toString());
            }

        } else if (etPhBook.getText().hashCode() == s.hashCode()) {
            if (!etPhBook.getText().toString().equals("") && !etPhBook.getText().toString().equals(null)) {
                int phone = Integer.parseInt(etPhBook.getText().toString());
                if (phone > 1) {
                    skPhBook.setProgress(phone);
                } else {
                    skPhBook.setProgress(0);
                    etPhBook.setSelection(etPhBook.getText().length());
                }
                setTotal(etMedia.getText().toString(), etWorkSpace.getText().toString(), etFlate.getText().toString(), etPhBook.getText().toString(), etPtClient.getText().toString());
            }

        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    //endregion


    public int getTotalContact()
    {
        int tot;
        if (!txtTotal.getText().toString().trim().equals("")) {
            tot = Integer.parseInt(txtTotal.getText().toString());
            return  tot;
        }

        return 0;
    }





    }
