package com.jentronics.cs3270a4;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.math.BigDecimal;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemFragment extends Fragment {
    private BigDecimal itemAmount = new BigDecimal(0);
    private EditText item1;
    private EditText item2;
    private EditText item3;
    private EditText item4;

    private View root;
    private OnAmountChanged mCallback;


    interface OnAmountChanged {
        void onAmountUpdate(BigDecimal i);
    }
    public ItemFragment() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{
            mCallback = (OnAmountChanged) activity;
        }
        catch (ClassCastException e){
            throw new ClassCastException(getString(R.string.amount_frag_error_msg));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return root = inflater.inflate(R.layout.fragment_item, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        item1 = (EditText) root.findViewById(R.id.item1);
        item2 = (EditText) root.findViewById(R.id.item2);
        item3 = (EditText) root.findViewById(R.id.item3);
        item4 = (EditText) root.findViewById(R.id.item4);

        TextWatcher amountWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                itemAmount = BigDecimal.ZERO;
                try { // silently don't add if invalid number
                    itemAmount = itemAmount.add(new BigDecimal(item1.getText().toString()));
                } catch (NumberFormatException e) {}
                try { // silently don't add if invalid number
                    itemAmount = itemAmount.add(new BigDecimal(item2.getText().toString()));
                } catch (NumberFormatException e) {}
                try { // silently don't add if invalid number
                    itemAmount = itemAmount.add(new BigDecimal(item3.getText().toString()));
                } catch (NumberFormatException e) {}
                try { // silently don't add if invalid number
                    itemAmount = itemAmount.add(new BigDecimal(item4.getText().toString()));
                } catch (NumberFormatException e) {}

                mCallback.onAmountUpdate(itemAmount);
                Log.d("test", "Text change to: " + s); }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d("test", "afterTextChanged: " + editable.toString());
            }
        };

        item1.addTextChangedListener(amountWatcher);
        item2.addTextChangedListener(amountWatcher);
        item3.addTextChangedListener(amountWatcher);
        item4.addTextChangedListener(amountWatcher);
    }

    public BigDecimal getItemAmount() {
        return itemAmount;
    }
}
