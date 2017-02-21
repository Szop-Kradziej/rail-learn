package com.drabarz.karola.raillearn.trip.list;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.drabarz.karola.raillearn.R;
import com.drabarz.karola.raillearn.model.Trip;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TripsGroupAdapter extends BaseAdapter {

    private final List<TripItem> currentTrips = new ArrayList<TripItem>();
    private final List<TripItem> allTrips = new ArrayList<TripItem>();

    private TripsActivity tripsActivity;

    public TripsGroupAdapter(TripsActivity tripsActivity) {
        this.tripsActivity = tripsActivity;
    }

    public void replaceTripItems(List<Trip> trips) {
        currentTrips.clear();
        allTrips.clear();
        for (Trip trip : trips) {
            allTrips.add(new TripItem(trip));
        }
        currentTrips.addAll(allTrips);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return currentTrips.size();
    }

    @Override
    public TripItem getItem(int position) {
        return currentTrips.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup viewGroup) {

        final TripItem tripItem = currentTrips.get(position);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trip_field, viewGroup, false);

        ImageView coverPicture = (ImageView) view.findViewById(R.id.coverPictureImageView);
        String coverPhotoURL = tripItem.getTrip().getUser().getCoverPhoto();
        Picasso.with(view.getContext()).load(coverPhotoURL).into(coverPicture);

        ImageView profilPicture = (ImageView) view.findViewById(R.id.profilePictureImageView);
        String profilPhotoURL = tripItem.getTrip().getUser().getProfilePhoto();
        Picasso.with(view.getContext()).load(profilPhotoURL).into(profilPicture);

        tripItem.bindLayout(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isMyTrip(tripItem.getTrip())) {
                    tripsActivity.startSelectedMyFullTripActivity(tripItem.getTrip());
                } else if (isMyJoinedTrip(tripItem.getTrip())) {
                    tripsActivity.startSelectedMyRequestedFullTripActivity(tripItem.getTrip());
                } else {
                    tripsActivity.startSelectedOtherFullTripActivity(tripItem.getTrip());
                }
            }
        });

        return view;
    }

    private boolean isMyTrip(Trip trip) {
        String myId = PreferenceManager.getDefaultSharedPreferences(tripsActivity).getString("user_id", null);
        String tripUserId = trip.getUser().getId();

        return myId.equals(tripUserId);
    }

    private boolean isMyJoinedTrip(Trip trip) {
        String myId = PreferenceManager.getDefaultSharedPreferences(tripsActivity).getString("user_id", null);
        String joinedUserId = trip.getJoinedUser();

        return myId.equals(joinedUserId);
    }

    public void showAll() {
        currentTrips.clear();
        currentTrips.addAll(allTrips);
        notifyDataSetChanged();
    }

    public void showMyOffers() {
        currentTrips.clear();
        for(TripItem tripItem: allTrips) {
            if(isMyTrip(tripItem.getTrip())) {
                currentTrips.add(tripItem);
            }
        }
        notifyDataSetChanged();
    }

    public void showMyRequests() {
        currentTrips.clear();
        for(TripItem tripItem: allTrips) {
            if(isMyJoinedTrip(tripItem.getTrip())) {
                currentTrips.add(tripItem);
            }
        }
        notifyDataSetChanged();
    }

    public void showOtherOffers() {
        currentTrips.clear();
        for (TripItem tripItem : allTrips) {
            if(!isMyTrip(tripItem.getTrip()) && !isMyJoinedTrip(tripItem.getTrip())) {
                currentTrips.add(tripItem);
            }
        }
        notifyDataSetChanged();
    }
}
