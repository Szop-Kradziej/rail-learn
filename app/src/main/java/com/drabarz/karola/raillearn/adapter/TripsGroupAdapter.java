package com.drabarz.karola.raillearn.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.drabarz.karola.raillearn.R;
import com.drabarz.karola.raillearn.TripItem;

import java.util.ArrayList;
import java.util.List;

public class TripsGroupAdapter extends BaseAdapter {

    private final List<TripItem> tripsGroup = new ArrayList<TripItem>();

    public TripsGroupAdapter() {
    }

    public void addTripItem(TripItem tripItem) {
        tripsGroup.add(tripItem);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return tripsGroup.size();
    }

    @Override
    public TripItem getItem(int position) {
        return tripsGroup.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        final TripItem tripItem = tripsGroup.get(position);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trip_field, viewGroup, false);

        return view;
    }
}
