package com.programming.finetuned.busseatingbooking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class PassengerSelectionActivity extends AppCompatActivity {

    private ToggleButton t1;
    private ToggleButton t2;
    private ToggleButton t3;
    private ToggleButton t4;
    private ToggleButton t5;

    private int booked = 0;


    private RecyclerView passengerSeats;

    private Button activityOK;
    private Button recyclerviewOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_selection);

        t1 = findViewById(R.id._1);
        t2 = findViewById(R.id._2);
        t3 = findViewById(R.id._3);
        t4 = findViewById(R.id._4);
        t5 = findViewById(R.id._5);
        activityOK = findViewById(R.id.activity_ok);
        recyclerviewOK = findViewById(R.id.recyclerview_ok);

        /* Recyclerview Implementation starts here */
        passengerSeats = findViewById(R.id.recyclerview_passenger_seat);
        passengerSeats.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        final PassengerSeatAdapter passengerSeatAdapter = new PassengerSeatAdapter();
        passengerSeats.setAdapter(passengerSeatAdapter);

        recyclerviewOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PassengerSelectionActivity.this, passengerSeatAdapter.getChecked()+"", Toast.LENGTH_SHORT).show();
            }
        });
        /* Recyclerview implementation ends here */


        /*Activity Implementatin starts here */


        t1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (booked != 1) {
                        unCheck(booked);
                        booked = 1;
                    }
                }else {
                    booked = 0;
                }
            }
        });

        t2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (booked != 2) {
                        unCheck(booked);
                        booked = 2;
                    }
                }else {
                    booked = 0;
                }
            }
        });

        t3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (booked != 3) {
                        unCheck(booked);
                        booked = 3;
                    }
                }else {
                    booked = 0;
                }
            }
        });

        t4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (booked != 4) {
                        unCheck(booked);
                        booked = 4;
                    }
                }else {
                    booked = 0;
                }
            }
        });

        t5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (booked != 5) {
                        unCheck(booked);
                        booked = 5;
                    }
                }else {
                    booked = 0;
                }
            }
        });

        activityOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PassengerSelectionActivity.this, booked+"", Toast.LENGTH_SHORT).show();
            }
        });


        /*Activity Implementatin ends here */



    }

    private void unCheck(int booked) {
        switch (booked) {
            case 1:
                t1.setChecked(false);
                break;
            case 2:
                t2.setChecked(false);
                break;
            case 3:
                t3.setChecked(false);
                break;
            case 4:
                t4.setChecked(false);
                break;
            case 5:
                t5.setChecked(false);
                break;
        }
    }
}
