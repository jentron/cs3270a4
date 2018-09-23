package com.jentronics.cs3270a4;

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
    private BigDecimal itemAmount = new BigDecimal(100);
    private View root;
    private EditText item1;

    public ItemFragment() {
        // Required empty public constructor
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
        TextWatcher amountWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                Log.d("test", "Text change to: " + s);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d("test", "afterTextChanged: " + editable.toString());
            }
        };

        item1.addTextChangedListener(amountWatcher);
    }

    public BigDecimal getItemAmount() {
        return itemAmount;
    }
}
