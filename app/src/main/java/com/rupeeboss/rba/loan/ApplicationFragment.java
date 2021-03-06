package com.rupeeboss.rba.loan;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.model.ApplicationDisplayEntity;
import com.rupeeboss.rba.utility.DividerItemDecoration;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ApplicationFragment extends Fragment {

    public static final String APPLICANTION = "Application";
    RecyclerView recyclerApplication;
    ApplicationAdapter mAdapter;
    List<ApplicationDisplayEntity> listApplicationDisp;
    public ApplicationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_application, container, false);
        initialise_widget(view);
//        mAdapter = new ApplicationAdapter(ApplicationFragment.this,listApplicationDisp);
//        recyclerApplication.setAdapter(mAdapter);

        if (getArguments() != null) {
            listApplicationDisp = getArguments().getParcelableArrayList(APPLICANTION);
            if(listApplicationDisp!=null){
                mAdapter = new ApplicationAdapter(getActivity(), listApplicationDisp);
                recyclerApplication.setAdapter(mAdapter);
            }else{
                recyclerApplication.setAdapter(null);
            }
        }
        return view;
    }

    private void initialise_widget(View view) {
        recyclerApplication = (RecyclerView) view.findViewById(R.id.recyclerApplication);
        recyclerApplication.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerApplication.addItemDecoration(
                new DividerItemDecoration(ContextCompat.getDrawable(getActivity(),
                        R.drawable.item_decor)));
    }
}
