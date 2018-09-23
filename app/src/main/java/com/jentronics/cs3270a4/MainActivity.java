package com.jentronics.cs3270a4;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity implements TaxFragment.OnTaxRateChanged {
    private FragmentManager fm;
    private TaxFragment taxFragment;
    private TotalsFragment totalsFragment;
    private ItemFragment itemFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.taxFragment, new TaxFragment(), "FragTax")
                .replace(R.id.totalsFragment, new TotalsFragment(), "FragTotal")
                .replace(R.id.itemFragment, new ItemFragment(), "FragItem")
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        taxFragment    = (TaxFragment)    fm.findFragmentByTag("FragTax");
        totalsFragment = (TotalsFragment) fm.findFragmentByTag("FragTotal");
        itemFragment   = (ItemFragment)   fm.findFragmentByTag("FragItem");
    }

    @Override
    public void onSeekUpdate(BigDecimal taxRate) {
        updateAll();
        Log.d("test", getString(R.string.msg_tax_rate) + taxRate.toPlainString() );
    }

    private void updateAll(){
        BigDecimal itemAmount = itemFragment.getItemAmount();
        taxFragment.updateTotal(itemAmount);
        BigDecimal taxAmount  = taxFragment.getTaxAmount();
        totalsFragment.updateTotal(itemAmount, taxAmount);

    }
}
