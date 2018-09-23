package com.jentronics.cs3270a4;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.math.BigDecimal;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemFragment extends Fragment {
    private BigDecimal itemAmount = new BigDecimal(100);

    public ItemFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item, container, false);
    }

    public BigDecimal getItemAmount() {
        return itemAmount;
    }
}
