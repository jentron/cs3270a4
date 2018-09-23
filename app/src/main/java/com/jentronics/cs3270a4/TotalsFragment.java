package com.jentronics.cs3270a4;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class TotalsFragment extends Fragment {

    private View root;
    private TextView tv_totalAmount;
    private NumberFormat numFormat = NumberFormat.getCurrencyInstance(Locale.US);;

    private BigDecimal total = new BigDecimal(0);

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
        tv_totalAmount.setText(numFormat.format(total.doubleValue()));
    }

    public void updateTotal(BigDecimal itemAmount, BigDecimal taxAmount) {

        total = itemAmount.add(taxAmount);
        if(tv_totalAmount != null) tv_totalAmount.setText(numFormat.format(total.doubleValue()));
    }
}
