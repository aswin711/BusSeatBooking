package com.programming.finetuned.busseatingbooking;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class SeatBookingAdapter extends RecyclerView.Adapter<SeatBookingAdapter.ViewHolder> {

    private ArrayList<Integer> bookedSeats;
    private ArrayList<Integer> userBookedSeats;
    private ArrayList<Integer> ladiesSeats;
    private ArrayList<Integer> seniorsSeats;
    private int seatLimit;
    private int size;
    private String label;

    public SeatBookingAdapter() {
        this.userBookedSeats = new ArrayList<>();
        bookedSeats = new ArrayList<>();
        ladiesSeats = new ArrayList<>();
        seniorsSeats = new ArrayList<>();
        this.seatLimit = 1;
        this.size = 1;
        this.label = null;
    }

    public void setBookedSeats(ArrayList<Integer> seats) {
        this.bookedSeats = seats;
        notifyDataSetChanged();
    }

    public void setLadiesSeats(ArrayList<Integer> seats) {
        this.ladiesSeats = seats;
        notifyDataSetChanged();
    }

    public void setSeniorsSeats(ArrayList<Integer> seats) {
        this.seniorsSeats = seats;
        notifyDataSetChanged();
    }

    public void setSeatLimit(int limit){
        this.seatLimit = limit;
    }

    public void setSeat(int size) {
        this.size = size;
        notifyDataSetChanged();
    }

    public void setLabel(String label) {
        this.label = label;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_seat,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.onBind(i);
    }

    @Override
    public int getItemCount() {
        return size;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ToggleButton L1;
        public ToggleButton L2;
        public ToggleButton R1;
        public ToggleButton R2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            L1 = itemView.findViewById(R.id.L1);
            L2 = itemView.findViewById(R.id.L2);
            R1 = itemView.findViewById(R.id.R1);
            R2 = itemView.findViewById(R.id.R2);
        }


        public void onBind(final int pos) {

            /*
            * L1 will be at the first position of left side; left most seat
            * L2 will be at the second position of left side
            * R2 will be at the second position of right side
            * R1 will be at the first position of right side; right most seat
            *
            * The following diagram shows the seat arrangment of a single list
            *
            * L1 L2        R2 R1
            *
            * */

            init(pos);

            L1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        if (userBookedSeats.size() == seatLimit) {
                            Toast.makeText(itemView.getContext(), "Cannot book more than "+seatLimit+" seat(s)", Toast.LENGTH_SHORT).show();
                            L1.setChecked(false);
                            return;
                        }
                    }
                    /*To find the seat number, we need
                       the position.
                       For example, consider the first row
                       position will be equal to 0
                       then the seat numbers are 0,1,2,3
                       for the second row
                       seat numbers are 4,5,6,7
                       .
                       .
                       .
                       then L1 = position * 4 + 0
                       L2 = position * 4 + 1
                       R2 = position * 4 + 2
                       R3 = position * 4 + 3
                     */

                    int seatNumber = pos * 4 + 0;

                    onSeatBookingChanged(pos*4,b,itemView.getContext());
                }
            });

            L2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        if (userBookedSeats.size() == seatLimit) {
                            Toast.makeText(itemView.getContext(), "Cannot book more than "+seatLimit+" seat(s)", Toast.LENGTH_SHORT).show();
                            L2.setChecked(false);
                            return;
                        }
                    }
                    onSeatBookingChanged(pos*4+1,b,itemView.getContext());
                }
            });

            R2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        if (userBookedSeats.size() == seatLimit) {
                            Toast.makeText(itemView.getContext(), "Cannot book more than "+seatLimit+" seat(s)", Toast.LENGTH_SHORT).show();
                            R2.setChecked(false);
                            return;
                        }
                    }
                    onSeatBookingChanged(pos*4+2,b,itemView.getContext());
                }
            });

            R1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        if (userBookedSeats.size() == seatLimit) {
                            Toast.makeText(itemView.getContext(), "Cannot book more than "+seatLimit+" seat(s)", Toast.LENGTH_SHORT).show();
                            R1.setChecked(false);
                            return;
                        }
                    }
                    onSeatBookingChanged(pos*4+3,b,itemView.getContext());
                }
            });

        }

        /*Initialise the row
          Check whether the seat is already booked, ladies seat,
          seniors seat or available seat
         */

        public void init(int pos) {


            int bookedSeat = getSectionNumber(bookedSeats,pos);
            if (bookedSeat > -1) {
                setBackground(bookedSeat,R.drawable.selecter_booked, false);
            }

            int ladiesSeat = getSectionNumber(ladiesSeats, pos);
            if (ladiesSeat > -1) {
                setBackground(ladiesSeat, R.drawable.selecter_ladies, true);
            }

            int seniorSeat = getSectionNumber(seniorsSeats, pos);
            if (seniorSeat > -1) {
                setBackground(seniorSeat, R.drawable.selecter_seniors, true);
            }

        }

        /*
        * Get the section number
        * What is section number?
        * For L1, the section number is 0
        * likewise for L2,R2,R1 it is 1,2,3 respectively
        * */
        public int getSectionNumber(ArrayList<Integer> list, int pos) {
            for (int i = pos * 4; i < pos * 4 + 4 ; i++ ) {
                if (list.contains(i)) {
                    return i % 4;
                }
            }
            return -1;
        }


        /*
        * Setting background of seat according to booked, ladies, senior or available
        * */
        public void setBackground(int sectionNumber, int drawable, boolean enabled) {
            /*
            * If section number is
            * 0 then the seat will be L1
            * 0 => L1
            * 1 => L2
            * 2 => R2
            * 3 => R3
            * */
            switch (sectionNumber) {
                case 0:
                    L1.setBackgroundResource(drawable);
                    L1.setEnabled(enabled);
                    L1.setChecked(!enabled);
                    break;
                case 1:
                    L2.setBackgroundResource(drawable);
                    L2.setEnabled(enabled);
                    L2.setChecked(!enabled);
                    break;
                case 2:
                    R2.setBackgroundResource(drawable);
                    R2.setEnabled(enabled);
                    R2.setChecked(!enabled);
                    break;
                case 3:
                    R1.setBackgroundResource(drawable);
                    R1.setEnabled(enabled);
                    R1.setChecked(!enabled);
                    break;
            }
        }

        /*
        * Adding or removing seats on check changed
        * @param
        * seatNumber is the number of the seat added or removed
        * @param
        * booked is a flag variable that indicated whether seat is booked or not
        * */
        public void onSeatBookingChanged(int seatNumber, boolean booked, Context context) {

            if (userBookedSeats.size() <= seatLimit ) {

                if (booked) {
                    userBookedSeats.add(seatNumber);
                    bookedSeats.add(seatNumber);
                } else {
                    if (userBookedSeats.contains(seatNumber)) {
                        int pos = userBookedSeats.indexOf(seatNumber);
                        userBookedSeats.remove(pos);
                    }

                    if (bookedSeats.contains(seatNumber)) {
                        int pos = bookedSeats.indexOf(seatNumber);
                        bookedSeats.remove(pos);
                    }

                }

                ((BookingInterface)context).onSeatsBooked(userBookedSeats, label);
            }

        }

    }


    public interface BookingInterface {
        void onSeatsBooked(ArrayList<Integer> seats, String label);
    }
}
