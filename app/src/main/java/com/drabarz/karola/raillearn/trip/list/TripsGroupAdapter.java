package com.drabarz.karola.raillearn.trip.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.drabarz.karola.raillearn.R;
import com.drabarz.karola.raillearn.trip.list.TripItem;

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

    public TripItem getSelectedTripItem() {
        for(TripItem tripItem : tripsGroup) {
            if(tripItem.isSelected()) {
                tripItem.setIsSelected(false);
                return tripItem;
            }
        }
        return null;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup viewGroup) {

        final TripItem tripItem = tripsGroup.get(position);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trip_field, viewGroup, false);
        tripItem.bindLayout(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTripItemClicked(tripItem);
            }
        });

        return view;
    }

    private void onTripItemClicked(TripItem tripItem) {
        tripItem.setIsSelected(true);
        notifyDataSetChanged();
    }
}
