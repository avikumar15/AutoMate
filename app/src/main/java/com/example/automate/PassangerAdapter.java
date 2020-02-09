package com.example.automate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Vector;

public class PassangerAdapter extends RecyclerView.Adapter<PassangerAdapter.PassangerViewHolder> {
    private List<String> name = new Vector<String>();
    private String type;
    private Context context;
    public PassangerAdapter(Context context, List<String> name) {
        this.context = context;
        this.name=name;
        this.type = type;
    }
    @NonNull
    @Override
    public PassangerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.location_blocks, parent, false);
        return new PassangerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PassangerViewHolder holder, int position) {
        String s = name.get(position);
        holder.passangerTextView.setText(s);
        holder.rideFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //End Ride
            }
        });
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class PassangerViewHolder extends RecyclerView.ViewHolder{
        TextView passangerTextView;
        Button rideFinishButton;
        LinearLayout passengerBlock;
        public PassangerViewHolder(@NonNull View itemView) {
            super(itemView);
            passangerTextView = itemView.findViewById(R.id.passengerName);
            rideFinishButton = itemView.findViewById(R.id.passengerFinishButton);
            passengerBlock = itemView.findViewById(R.id.passengerBlock);
        }
    }
}
