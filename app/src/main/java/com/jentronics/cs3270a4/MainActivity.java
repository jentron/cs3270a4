package com.jentronics.cs3270a4;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity implements TaxFragment.OnTaxRateChanged {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.taxFragment, new TaxFragment(), "FragTax")
                .add(R.id.totalsFragment, new TotalsFragment(), "FragTotal")
                .commit();
    }

    @Override
    public void onSeekUpdate(BigDecimal taxRate) {
        Log.d("test", getString(R.string.msg_tax_rate) + taxRate.toPlainString() );
    }
}
