package com.example.automate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Vector;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder>  {
    private List<String> location = new Vector<String>();
    private List<Float> latitude = new Vector<Float>();
    private List<Float> longitude = new Vector<Float>();
    private String type;
    private Context context;
    public LocationAdapter(Context context, List<String> location, List<Float> latitude, List<Float> longitude, String type) {
        this.context = context;
        this.location=location;
        this.latitude=latitude;
        this.longitude=longitude;
        this.type = type;
    }
    @NonNull
    @Override
    public LocationAdapter.LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.location_blocks, parent, false);
        return new LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.LocationViewHolder holder, final int position) {
        String s = location.get(position);
        holder.locationTextView.setText(s);
        holder.locLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(AppUtils.source.equals(location.get(position)) || AppUtils.destination.equals(location.get(position)))) {
                    Intent intent = new Intent(context, MapActivity.class);
                    intent.putExtra("sourceLat", latitude.get(position));
                    intent.putExtra("sourceLng", longitude.get(position));
                    intent.putExtra("loc", location.get(position));
                    intent.putExtra("type", type);
                    context.startActivity(intent);
                }
                else
                    Toast.makeText(context,"Select a different location",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return location.size();
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder{
        TextView locationTextView;
        LinearLayout locLinearLayout;
        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);
            locationTextView = itemView.findViewById(R.id.location);
            locLinearLayout = itemView.findViewById(R.id.locblock);
        }
    }
}
