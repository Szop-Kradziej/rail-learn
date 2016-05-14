package com.drabarz.karola.raillearn.trip.list;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.drabarz.karola.raillearn.R;
import com.drabarz.karola.raillearn.model.Trip;

import java.util.ArrayList;
import java.util.List;

public class TripsGroupAdapter extends BaseAdapter {

    private final List<TripItem> tripsGroup = new ArrayList<TripItem>();
    private TripsActivity tripsActivity;

    public TripsGroupAdapter(TripsActivity tripsActivity) {
        this.tripsActivity = tripsActivity;
    }

    public void replaceTripItems(List<Trip> trips) {
        tripsGroup.clear();
        for (Trip trip : trips) {
            tripsGroup.add(new TripItem(trip));
        }
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
    public View getView(int position, final View convertView, ViewGroup viewGroup) {

        final TripItem tripItem = tripsGroup.get(position);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trip_field, viewGroup, false);
        tripItem.bindLayout(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isMyTrip(tripItem.getTrip())) {
                    tripsActivity.startSelectedMyFullTripActivity(tripItem.getTrip());
                } else {
                    tripsActivity.startSelectedOtherFullTripActivity(tripItem.getTrip());
                }
            }
        });

        return view;
    }

    private boolean isMyTrip(Trip trip) {
        String my_id = PreferenceManager.getDefaultSharedPreferences(tripsActivity).getString("user_id", null);
        String trip_user_id = trip.getUser().getId();

        return my_id.equals(trip_user_id);
    }
}
