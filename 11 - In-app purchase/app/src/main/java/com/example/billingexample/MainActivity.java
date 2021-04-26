package com.example.billingexample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BillingClient billingClient;
    private List<SkuDetails> skuDetails;
    private PurchasesUpdatedListener updatedListener = (billingResult, list) -> {
        // BillingResult billingResult, List<Purchase> list
        // TODO

        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
            for (Purchase purchase: list) {
                Log.i("MAIN", "sikeres vasarlas");
            }
        } else {
            Log.i("MAIN", "sikertelen vasarlas");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.billingClient = BillingClient.newBuilder(this)
                .setListener(updatedListener)
                .enablePendingPurchases()
                .build();

        startConnection();

        BillingFlowParams flowParams = BillingFlowParams.newBuilder()
                .setSkuDetails(skuDetails.get(0))
                .build();

        int responseCode = billingClient.launchBillingFlow(this, flowParams).getResponseCode();
        if (responseCode == BillingClient.BillingResponseCode.OK) {
            // TODO: Engedelyezni a filtert a Photoshop-Szeged appban.
        }

        Purchase.PurchasesResult purchasesResult = billingClient.queryPurchases(BillingClient.SkuType.INAPP);

        ConsumeParams consumeParams = ConsumeParams.newBuilder()
                .setPurchaseToken(purchasesResult.getPurchasesList().get(0).getPurchaseToken())
                .build();

        billingClient.consumeAsync(consumeParams, new ConsumeResponseListener() {
            @Override
            public void onConsumeResponse(@NonNull BillingResult billingResult, @NonNull String s) {
                int code = billingResult.getResponseCode();

                if (code == BillingClient.BillingResponseCode.OK) {
                    // OK.
                }
            }
        });
    }

    private void startConnection() {
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    List<String> items = new ArrayList<>();
                    items.add("test sword");

                    SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
                    params.setSkusList(items).setType(BillingClient.SkuType.INAPP);

                    billingClient.querySkuDetailsAsync(params.build(), (billingResult1, list) -> {
                        if (billingResult1.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                            for (SkuDetails details: list) {
                                Log.i("MAIN", details.getTitle());
                                Log.i("MAIN", details.getPrice());
                                Log.i("MAIN", details.getDescription());
                            }
                            updateUI(list);
                        }
                    });
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                // TODO: Valamilyen logika kellene, hogy ne legyen vegtelen rekurzio.
                startConnection();
            }
        });
    }

    private void updateUI(List<SkuDetails> list) {
        this.skuDetails = list;
    }
}
