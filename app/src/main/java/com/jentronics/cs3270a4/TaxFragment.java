package com.jentronics.cs3270a4;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.math.BigDecimal;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaxFragment extends Fragment {
    private SeekBar taxBar;
    private TextView tv_taxRate;
    private BigDecimal taxRate = new BigDecimal(0);
    private View root;
    private OnTaxRateChanged mCallback;

    interface OnTaxRateChanged {
        void onSeekUpdate(BigDecimal i);
    }

    private String buildTaxRateString(BigDecimal taxRate) {
        return getString(R.string.msg_tax_rate) + taxRate.multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString() +"%";
    }
    public TaxFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{
            mCallback = (OnTaxRateChanged) activity;
        }
        catch (ClassCastException e){
            throw new ClassCastException(getString(R.string.tax_frag_error_msg));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return root = inflater.inflate(R.layout.fragment_tax, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        tv_taxRate = (TextView) root.findViewById(R.id.taxRate);
        tv_taxRate.setText(buildTaxRateString(taxRate));

        taxBar = (SeekBar) root.findViewById(R.id.taxBar);
        taxBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar taxBar, int i, boolean b) {
                taxRate = new BigDecimal(i);
                taxRate = taxRate.multiply(new BigDecimal("0.0025"));

                tv_taxRate.setText(buildTaxRateString(taxRate));
                mCallback.onSeekUpdate(taxRate);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
