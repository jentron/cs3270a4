package com.jentronics.cs3270a4;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaxFragment extends Fragment {
    private SeekBar taxBar;

    private TextView tv_taxRate;
    private TextView tv_taxAmount;
    private NumberFormat moneyFormat   = NumberFormat.getCurrencyInstance(Locale.US);
    private NumberFormat percentFormat = NumberFormat.getPercentInstance(Locale.US);
    private BigDecimal taxRate = new BigDecimal(0);
    private BigDecimal totalAmount = new BigDecimal(0); // this is a cached value
    private BigDecimal taxAmount = new BigDecimal(0);

    private View root;
    private OnTaxRateChanged mCallback;

    interface OnTaxRateChanged {
        void onSeekUpdate(BigDecimal i);
    }

    private String buildTaxRateString(BigDecimal taxRate) {
        return getString(R.string.msg_tax_rate) + percentFormat.format(taxRate.doubleValue());
    }

    private String buildTaxAmountString(BigDecimal taxAmount) {
        return getString(R.string.msg_tax_amount) + moneyFormat.format(taxAmount.doubleValue());
    }
    public TaxFragment() {
        // Required empty public constructor
    }

    public void updateTotal(BigDecimal total){
        totalAmount = total;
        taxAmount = totalAmount.multiply(taxRate).setScale(2, BigDecimal.ROUND_UP);
        tv_taxAmount.setText(buildTaxAmountString(taxAmount));
    }

    public BigDecimal getTaxAmount(){
        return(taxAmount);
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
        percentFormat.setMinimumFractionDigits(2);
        tv_taxRate = (TextView) root.findViewById(R.id.taxRate);
        tv_taxRate.setText(buildTaxRateString(taxRate));
        tv_taxAmount = (TextView) root.findViewById(R.id.taxAmount);
        tv_taxAmount.setText(buildTaxAmountString(taxAmount));

        taxBar = (SeekBar) root.findViewById(R.id.taxBar);

        taxBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar taxBar, int i, boolean b) {
                taxRate = new BigDecimal(i);
                taxRate = taxRate.multiply(new BigDecimal("0.0025")); // this is a string to avoid conversion errors in BigNumber
                taxAmount = totalAmount.multiply(taxRate).setScale(2, BigDecimal.ROUND_UP);
                tv_taxAmount.setText(buildTaxAmountString(taxAmount));
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

        SharedPreferences sp = getActivity().getPreferences(Context.MODE_PRIVATE);
        int taxBarInt = sp.getInt("taxSeekBar", 0);
        taxBar.setProgress(taxBarInt);

    }

    @Override
    public void onPause() {
        super.onPause();

        SharedPreferences sp = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor spEdit = sp.edit();
        spEdit.putInt("taxSeekBar", taxBar.getProgress());
        spEdit.commit();
    }
}
