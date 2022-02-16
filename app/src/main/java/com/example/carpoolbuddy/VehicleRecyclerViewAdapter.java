package com.example.carpoolbuddy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class VehicleRecyclerViewAdapter extends RecyclerView.Adapter<VehicleRecyclerViewAdapter.MyViewHolder> {
    private ArrayList<Vehicle> vehiclesList;
    private RecyclerViewClickListener listener;

    public VehicleRecyclerViewAdapter(ArrayList<Vehicle> vehiclesList, RecyclerViewClickListener listener) {
        this.vehiclesList = vehiclesList;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nameTxt;
        private TextView capacityTxt;
        private TextView priceTxt;

        public MyViewHolder(final View view) {
            super(view);
            nameTxt = view.findViewById(R.id.textView4);
            capacityTxt = view.findViewById(R.id.textView6);
            priceTxt = view.findViewById(R.id.textView8);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public VehicleRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleRecyclerViewAdapter.MyViewHolder holder, int position) {
        String name = vehiclesList.get(position).getModel();
        String capacity = vehiclesList.get(position).getCapacity();
        String price = vehiclesList.get(position).getBasePrice();

        holder.nameTxt.setText(name);
        holder.capacityTxt.setText(capacity);
        holder.priceTxt.setText(price);
    }

    @Override
    public int getItemCount() {
        return vehiclesList.size();
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }
}
