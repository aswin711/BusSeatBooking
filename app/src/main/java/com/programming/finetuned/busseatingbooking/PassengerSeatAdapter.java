package com.programming.finetuned.busseatingbooking;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class PassengerSeatAdapter extends RecyclerView.Adapter<PassengerSeatAdapter.ViewHolder>{

    private int size;
    private ToggleButton booked = null;
    private int checked = -1;

    public PassengerSeatAdapter() {
        this.size = 5;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_passenger_seat,null);
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
        private ToggleButton seat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            seat = itemView.findViewById(R.id.button_seat);
        }

        public void onBind(final int pos) {

            seat.setText((pos+1)+"");
            seat.setTextOn((pos+1)+"");
            seat.setTextOff((pos+1)+"");

            seat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToggleButton t = (ToggleButton)view;
                    if (t.isChecked()) {
                        if (booked != null) {
                            booked.setChecked(false);
                        }

                        booked = t;
                        checked = pos;
                    } else {
                        booked = null;
                        checked = -1;
                    }
                }
            });
        }
    }

    public int getChecked() {
        return checked + 1;
    }
}
