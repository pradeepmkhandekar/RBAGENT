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
import com.rupeeboss.rba.core.model.QuoteDisplayEntity;
import com.rupeeboss.rba.utility.DividerItemDecoration;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuotesFragment extends Fragment {
    public static final String QUOTES = "Quoteslist";
   // public static final String APPLICANTION = "Application";
    RecyclerView recyclerQuotes;
    QuotesAdapter mAdapter;
    List<QuoteDisplayEntity> listQuoteDisp;
    View view;

    public QuotesFragment() {
        // Required empty public constructor


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_quotes, container, false);
        initialise_widget(view);
        if (getArguments() != null) {
            listQuoteDisp = getArguments().getParcelableArrayList(QUOTES);
            if(listQuoteDisp!=null){
                mAdapter = new QuotesAdapter(getActivity(), listQuoteDisp);
                recyclerQuotes.setAdapter(mAdapter);
            }else{
                recyclerQuotes.setAdapter(null);
            }

        }
        return view;
    }

    private void initialise_widget(View view) {
        recyclerQuotes = (RecyclerView) view.findViewById(R.id.recyclerQuotes);
        recyclerQuotes.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerQuotes.addItemDecoration(
                new DividerItemDecoration(ContextCompat.getDrawable(getActivity(),
                        R.drawable.item_decor)));
    }

}
