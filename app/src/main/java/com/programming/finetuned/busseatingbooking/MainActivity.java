package com.programming.finetuned.busseatingbooking;

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


    private RecyclerView lowerSeatsRecyclerView;
    private RecyclerView upperSeatsRecyclerView;
    private TextView numberOfSeats;
    private TextView totalFare;
    private Button proceed;


    private ArrayList<Integer> bookedSeats = new ArrayList<Integer>();
    private ArrayList<Integer> ladiesSeats = new ArrayList<>();
    private ArrayList<Integer> seniorSeats = new ArrayList<>();

    private ArrayList<Integer> userBookedLowerSeats = new ArrayList<>();
    private ArrayList<Integer> userBookedUpperSeats = new ArrayList<>();

    private String lowerLabel = "LOWER";
    private String upperLabel = "UPPER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lowerSeatsRecyclerView = findViewById(R.id.recyclerview_lower_seats);
        upperSeatsRecyclerView = findViewById(R.id.recyclerview_upper_seats);
        numberOfSeats = findViewById(R.id.selected_seats);
        totalFare = findViewById(R.id.total_fare);
        proceed = findViewById(R.id.proceed);

        feedData();

        upperSeatsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        SeatBookingAdapter adapter1 = new SeatBookingAdapter();
        adapter1.setSeatLimit(4);
        adapter1.setSeat(4);
        adapter1.setLabel(upperLabel);
        upperSeatsRecyclerView.setAdapter(adapter1);
        upperSeatsRecyclerView.setNestedScrollingEnabled(false);

        lowerSeatsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        SeatBookingAdapter adapter = new SeatBookingAdapter();
        adapter.setBookedSeats(bookedSeats);
        adapter.setLadiesSeats(ladiesSeats);
        adapter.setSeniorsSeats(seniorSeats);
        adapter.setSeatLimit(4);
        adapter.setSeat(8);
        adapter.setLabel(lowerLabel);
        lowerSeatsRecyclerView.setAdapter(adapter);
        lowerSeatsRecyclerView.setNestedScrollingEnabled(false);




        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userBookedLowerSeats.size() + userBookedUpperSeats.size() > 0) {
                    String selectedSeats = getSelectedSeats(userBookedLowerSeats, userBookedUpperSeats);

                    Toast.makeText(MainActivity.this, "Seats booked are "+selectedSeats, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //For displaying seats selected
    private String getSelectedSeats(ArrayList<Integer> userBookedLowerSeats, ArrayList<Integer> userBookedUpperSeats) {
        String selectedSeats = "LOWER SEATS: ";
        for (int i = 0; i < userBookedLowerSeats.size(); i++) {
            selectedSeats += userBookedLowerSeats.get(i);
            if (i != userBookedLowerSeats.size() - 1) {
                selectedSeats += ",";
            }
        }
        selectedSeats += "\n";
        selectedSeats += "UPPER SEATS: ";
        for (int i = 0; i < userBookedUpperSeats.size(); i++) {
            selectedSeats += userBookedUpperSeats.get(i);
            if (i != userBookedUpperSeats.size() - 1) {
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
    public void onSeatsBooked(ArrayList<Integer> seats, String label) {

        if ( label.equals(lowerLabel)) {
            userBookedLowerSeats = seats;
        } else {
            userBookedUpperSeats = seats;
        }

        int num = userBookedLowerSeats.size() + userBookedUpperSeats.size();

        numberOfSeats.setText(num+"");
        totalFare.setText("$"+(num*50));
    }
}
