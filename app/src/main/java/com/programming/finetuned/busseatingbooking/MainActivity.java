package com.programming.finetuned.busseatingbooking;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SeatBookingAdapter.BookingInterface {


    private RecyclerView seatsRecyclerview;
    private TextView numberOfSeats;
    private TextView totalFare;
    private Button proceed;


    private ArrayList<Integer> bookedSeats = new ArrayList<Integer>();
    private ArrayList<Integer> ladiesSeats = new ArrayList<>();
    private ArrayList<Integer> seniorSeats = new ArrayList<>();

    private ArrayList<Integer> userBookedSeats = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seatsRecyclerview = findViewById(R.id.recyclerview_seats);
        numberOfSeats = findViewById(R.id.selected_seats);
        totalFare = findViewById(R.id.total_fare);
        proceed = findViewById(R.id.proceed);

        feedData();


        seatsRecyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        SeatBookingAdapter adapter = new SeatBookingAdapter();
        adapter.setBookedSeats(bookedSeats);
        adapter.setLadiesSeats(ladiesSeats);
        adapter.setSeniorsSeats(seniorSeats);
        adapter.setSeatLimit(4);
        seatsRecyclerview.setAdapter(adapter);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userBookedSeats.size() > 0) {
                    String selectedSeats = getSelectedSeats(userBookedSeats);

                    Toast.makeText(MainActivity.this, "Seats booked are "+selectedSeats, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //For displaying seats selected
    private String getSelectedSeats(ArrayList<Integer> userBookedSeats) {
        String selectedSeats = "";
        for (int i = 0; i < userBookedSeats.size(); i++) {
            selectedSeats += userBookedSeats.get(i);
            if (i != userBookedSeats.size() - 1) {
                selectedSeats += ",";
            }
        }
        return selectedSeats;
    }

    private void feedData() {

        //adding booked seats
        bookedSeats.add(1);
        bookedSeats.add(8);
        bookedSeats.add(11);
        bookedSeats.add(13);

        //adding ladies seats
        ladiesSeats.add(2);
        ladiesSeats.add(3);
        ladiesSeats.add(6);
        ladiesSeats.add(7);

        //adding senior seats
        seniorSeats.add(15);
        seniorSeats.add(17);
    }

    @Override
    public void onSeatsBooked(ArrayList<Integer> seats) {
        int num = seats.size();
        int fare = 50;
        userBookedSeats = seats;
        numberOfSeats.setText(num+"");
        totalFare.setText("$"+(num*fare));
    }
}
