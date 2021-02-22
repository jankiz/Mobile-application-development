package com.example.implicitintentexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openWebsite(View view) {
        EditText website = findViewById(R.id.website_edittext);
        String url = website.getText().toString();
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void openLocation(View view) {
        EditText location = findViewById(R.id.location_edittext);
        String url = location.getText().toString();


        Uri uri = Uri.parse("geo:0,0?q=" + url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }

    public void shareText(View view) {
        EditText sharable_text = findViewById(R.id.share_edittext);
        String textToShare = sharable_text.getText().toString();

        ShareCompat.IntentBuilder
                .from(this)
                .setType("text/plain")
                .setChooserTitle("Mobil alkfejl megosztás")
                .setText(textToShare)
                .startChooser();
    }
}