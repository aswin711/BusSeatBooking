package com.programming.finetuned.busseatingbooking;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SelectorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);
    }

    public void onSeatBookingClicked(View view) {
        Intent i = new Intent(SelectorActivity.this, MainActivity.class);
        startActivity(i);
    }

    public void onPassengerActivityClicked(View view) {
        Intent i = new Intent(SelectorActivity.this, PassengerSelectionActivity.class);
        startActivity(i);
    }
}
