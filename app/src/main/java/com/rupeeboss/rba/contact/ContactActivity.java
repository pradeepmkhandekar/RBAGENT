package com.rupeeboss.rba.contact;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import com.google.android.material.snackbar.Snackbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.FrameLayout;

import com.rupeeboss.rba.BaseActivity;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.dialer.Dialer;
import com.rupeeboss.rba.core.facade.LoginFacade;
import com.rupeeboss.rba.core.model.AudioEntity;
import com.rupeeboss.rba.core.response.ValidateMobileResponse;
import com.rupeeboss.rba.rbaddlead.RbAddLeadActivity;
import com.rupeeboss.rba.rbdialerpad.AudioRecorder;
import com.rupeeboss.rba.utility.Constants;
import com.rupeeboss.rba.utility.Utility;

import java.io.IOException;
import java.util.ArrayList;


public class ContactActivity extends BaseActivity implements IResponseSubcriber {

    FrameLayout frlContactNo;
    RecyclerView recyclerContact;
    ArrayList<SelectUser> selectUsers;

    Cursor phones;

    private SearchView.OnQueryTextListener queryTextListener;
    ContactAdapter mAdapter;

    TelephonyManager telephonyManager;
    PhoneStateListener callStateListener;
    AudioRecorder audioRecorder;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    AudioEntity pkAudioEntity;
    String mbNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Contacts");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initilize();
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        sharedPreferences = getSharedPreferences(Constants.SHAREDPREFERENCE_TITLE, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        displayContact();

    }

    private void initilize() {
        selectUsers = new ArrayList<SelectUser>();

        frlContactNo = (FrameLayout) findViewById(R.id.frlContactNo);
        recyclerContact = (RecyclerView) findViewById(R.id.recyclerContact);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ContactActivity.this);
        recyclerContact.setLayoutManager(mLayoutManager);
//        search = (SearchView) findViewById(R.id.searchView);

    }

    private void displayContact() {


        String[] PROJECTION = new String[]{
                ContactsContract.RawContacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.PHOTO_URI,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Photo.CONTACT_ID};

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String filter = "" + ContactsContract.Contacts.HAS_PHONE_NUMBER + " > 0 and " + ContactsContract.CommonDataKinds.Phone.TYPE + "=" + ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE;
        String order = ContactsContract.Contacts.DISPLAY_NAME + " ASC";// LIMIT " + limit + " offset " + lastId + "";

        phones = this.getContentResolver().query(uri, PROJECTION, filter, null, order);


        if (phones == null || phones.getCount() == 0) {
            Log.e("count", "" + phones.getCount());
            frlContactNo.setVisibility(View.VISIBLE);
            recyclerContact.setVisibility(View.GONE);
        } else {
            recyclerContact.setVisibility(View.VISIBLE);
            frlContactNo.setVisibility(View.GONE);
            new LoadContact().execute();

        }


    }

    public void dialCall(final String mbNumber) {
        this.mbNumber = mbNumber;
        Constants.hideKeyBoard(recyclerContact, ContactActivity.this);
        callStateListener = new PhoneStateListener() {
            public void onCallStateChanged(int state, String incomingNumber) {

                if (state == TelephonyManager.CALL_STATE_RINGING) {

                }
                if (state == TelephonyManager.CALL_STATE_OFFHOOK) {

                    try {
                        if (audioRecorder == null) {
                            audioRecorder = new AudioRecorder();
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                                audioRecorder.startRecording(mbNumber, MediaRecorder.AudioSource.MIC, ContactActivity.this);
                            else
                                audioRecorder.startRecording(mbNumber, MediaRecorder.AudioSource.VOICE_CALL, ContactActivity.this);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


                if (state == TelephonyManager.CALL_STATE_IDLE) {

                    if (sharedPreferences.getString(Utility.CALL_STATUS_CONTACT, "").matches("YES")) {

                        telephonyManager.listen(callStateListener, PhoneStateListener.LISTEN_NONE);

                        editor.putString(Utility.CALL_STATUS_CONTACT, "NO");

                        try {
                            if (audioRecorder != null) {
                                audioRecorder.stopRecording();
                                pkAudioEntity = saveRecordingToDb(audioRecorder.getFilePath(), new LoginFacade(ContactActivity.this).getPanNumber(), mbNumber);
                                audioRecorder = null;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        // service hit
                        //  new Dialer().validateMobile(Utility.EmpCode, mbNumber, RbDialerPadActivity.this);   //  comment

                        new Dialer().validateMobile(new LoginFacade(ContactActivity.this).getUser().getEmpCode(), mbNumber, ContactActivity.this);

                    } else {
                        editor.putString(Utility.CALL_STATUS_CONTACT, "YES");
                    }
                    editor.commit();
                }
            }
        };
        telephonyManager.listen(callStateListener, PhoneStateListener.LISTEN_CALL_STATE);
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + mbNumber));
        startActivity(intent);
    }

    public AudioEntity saveRecordingToDb(String filePath, String empCode, String mobile) {
        AudioEntity audioEntity = new AudioEntity();
        try {

            audioEntity.setFile_name(filePath);
            audioEntity.setUser_id(empCode);
            audioEntity.setUploaded(false);
            audioEntity.setCreatedDate();
            audioEntity.setMobile(mobile);
            audioEntity.setLead_id(0);
            // new AudioRecorderFacade(this).insertAudioRecord(audioEntity);    // comment
            return audioEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) ContactActivity.this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(ContactActivity.this.getComponentName()));
        }
        if (searchView != null && phones.getCount() > 0) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(ContactActivity.this.getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {

                    mAdapter.filter(newText);
                    return true;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                // Not implemented here
                return false;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {
        if (response instanceof ValidateMobileResponse) {
            if (response.getStatus_Id() == 0) {
                if (((ValidateMobileResponse) response).getResult().getLeadId() == 0) {
                    startActivity(new Intent(ContactActivity.this, RbAddLeadActivity.class)
                            .putExtra("DEMO", true)
                            .putExtra("PHONE_DIAL_NUMBER", mbNumber));
                } else {

                    startActivity(new Intent(ContactActivity.this, RbAddLeadActivity.class)
                            .putExtra("PHONE_DIAL_NUMBER", mbNumber)
                            .putExtra("LEAD_ID", ((ValidateMobileResponse) response).getResult().getLeadId())
                            .putExtra("NAME", ((ValidateMobileResponse) response).getResult().getName()));
                }
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        Utility.hideKeyBoard(recyclerContact, ContactActivity.this);
        Snackbar.make(recyclerContact, t.getMessage(), Snackbar.LENGTH_SHORT).show();

    }


    class LoadContact extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Get Contact list from Phone

            if (phones != null && phones.getCount() > 0) {


                while (phones.moveToNext()) {

                    String name = "" + phones.getString(phones.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    String phoneNumber = "" + phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    String image_thumb = "" + phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));
                    String id = "" + phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Photo.CONTACT_ID));

                    Bitmap bit_thumb = null;
                    try {
                        if (image_thumb != null) {
                            bit_thumb = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(image_thumb));
                        } else {

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    SelectUser selectUser = new SelectUser();
                    selectUser.setThumb(bit_thumb);
                    selectUser.setName(name);
                    selectUser.setPhone(phoneNumber);
                    selectUser.setEmail(id);
                    selectUser.setCheckedBox(false);
                    selectUsers.add(selectUser);
                }
            } else {

            }
            //phones.close();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dismissDialog();
            mAdapter = new ContactAdapter(ContactActivity.this, selectUsers);
            recyclerContact.setAdapter(mAdapter);


        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        phones.close();
    }


}
