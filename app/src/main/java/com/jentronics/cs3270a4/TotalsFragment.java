package com.jentronics.cs3270a4;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TotalsFragment extends Fragment {

    private View root;
    private TextView tv_totalAmount;

    public TotalsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return root = inflater.inflate(R.layout.fragment_totals, container, false);
    }

    @Override
    public void onResume(){
        super.onResume();

        tv_totalAmount = (TextView) root.findViewById(R.id.totalAmount);
    }
}
