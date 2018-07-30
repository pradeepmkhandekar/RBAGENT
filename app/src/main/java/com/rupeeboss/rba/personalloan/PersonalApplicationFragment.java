package com.rupeeboss.rba.personalloan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rupeeboss.rba.R;
import com.rupeeboss.rba.core.model.PersonalApplicationDisplayEntity;
import com.rupeeboss.rba.utility.DividerItemDecoration;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalApplicationFragment extends Fragment {
    public static final String APPLICANTION = "PersonalApplication";
    RecyclerView recyclerApplication;
    PersonalApplicationAdapter mAdapter;
    List<PersonalApplicationDisplayEntity> listApplicationDisp;


    public PersonalApplicationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal_application, container, false);
        initialise_widget(view);

        if (getArguments() != null) {
            listApplicationDisp = getArguments().getParcelableArrayList(APPLICANTION);
            if (listApplicationDisp != null) {

                mAdapter = new PersonalApplicationAdapter(getActivity(), listApplicationDisp);
                recyclerApplication.setAdapter(mAdapter);
            } else {
                recyclerApplication.setAdapter(null);
            }
        }
        return view;
    }

    private void initialise_widget(View view) {
        recyclerApplication = (RecyclerView) view.findViewById(R.id.recyclerPersApplication);
        recyclerApplication.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerApplication.addItemDecoration(
                new DividerItemDecoration(ContextCompat.getDrawable(getActivity(),
                        R.drawable.item_decor)));
    }
}
