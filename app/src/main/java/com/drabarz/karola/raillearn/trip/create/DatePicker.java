package com.drabarz.karola.raillearn.trip.create;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.EditText;
import android.support.v4.app.DialogFragment;

import com.drabarz.karola.raillearn.R;

import java.util.Calendar;

public class DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        EditText inputStationDateEditView = (EditText) getActivity().findViewById(R.id.inputStationDateEditText);
        inputStationDateEditView.setText(view.getDayOfMonth() + "." + view.getMonth() + "." + view.getYear());
    }
}
