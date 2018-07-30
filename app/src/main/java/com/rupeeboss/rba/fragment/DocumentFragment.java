package com.rupeeboss.rba.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rupeeboss.rba.BaseFragment;
import com.rupeeboss.rba.R;
import com.rupeeboss.rba.adapter.DocumentAdapter;
import com.rupeeboss.rba.core.APIResponse;
import com.rupeeboss.rba.core.IResponseSubcriber;
import com.rupeeboss.rba.core.controller.document.DocumentController;
import com.rupeeboss.rba.core.response.DocumentResponse;

/**
 * Created by Rajeev Ranjan on 15/05/2017.
 */

public class DocumentFragment extends BaseFragment implements IResponseSubcriber, View.OnClickListener {

    RecyclerView recyclerDocument;
    DocumentResponse.DocumentResult documentResult;
    DocumentAdapter documentAdapter;

    public DocumentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_document, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Document Checklist");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.transparent_white));
        initilize(view);
        //   setHasOptionsMenu(true);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        //server hit to fetch latest data
        getActivity().setTitle(R.string.title_activity_share_message);

        showDialog();
        new DocumentController(getActivity()).getDocumentRequired(this);
        //   new ShareMessageController().getShareMessage("rb40000401",this);
    }

    private void initilize(View view) {
        recyclerDocument = (RecyclerView) view.findViewById(R.id.recyclerDocument);
        recyclerDocument.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerDocument.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {
        cancelDialog();
        if (response instanceof DocumentResponse) {
            if (response.getStatusId() == 0) {
                documentResult = ((DocumentResponse) response).getResult();
                documentAdapter = new DocumentAdapter(getActivity(), documentResult.getLstprodReqDoc());
                recyclerDocument.setAdapter(documentAdapter);
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
