package com.example.a9726446.a5_2p;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by 9726446 on 21/09/2018 at EN302-018
 */

public class ViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        initaliseUI();
    }

    private final Calendar aCalendar = Calendar.getInstance();

    private void initaliseUI() {
        try {
            startCalendar();
            Bundle b = getIntent().getExtras().getBundle("bundle");
            if (b == null) return;
            ((ImageView)(findViewById(R.id.ivBigFood))).setImageResource(b.getInt("imageID"));
            findViewById(R.id.ivBigFood).setContentDescription(getString(b.getInt("captionID")));
            ((TextView)(findViewById(R.id.etTitle))).setText(b.getInt("captionID"));
            ((TextView)(findViewById(R.id.etSrc))).setText(b.getInt("srcID"));

        } catch (NullPointerException npe) {
            Log.e("Bundle Error (View)", npe.toString());
        }
    }

    /**
     * With guidance from https://stackoverflow.com/questions/14933330/datepicker-how-to-popup-datepicker-when-click-on-edittext#
     */
    private void startCalendar() {
        final EditText editText = (EditText) findViewById(R.id.etDate);
        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                aCalendar.set(Calendar.YEAR, year);
                aCalendar.set(Calendar.MONTH, month);
                aCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String formatStr = "dd/MM/yy"; //Calendar Format
                SimpleDateFormat sdf = new SimpleDateFormat(formatStr, Locale.ENGLISH);
                editText.setText(sdf.format(aCalendar.getTime()));
            }
        };

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ViewActivity.this, dateSetListener,
                        aCalendar.get(Calendar.YEAR),
                        aCalendar.get(Calendar.MONTH),
                        aCalendar.get(Calendar.DAY_OF_MONTH)
                ).show();
            }
        });

    }

}

/*
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.Spinner;


public class FormActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String grade;
    private boolean complete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Spinner spinner = findViewById(R.id.grade_spinner);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        grade = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        grade = parent.getItemAtPosition(0).toString();
    }

    public void onCheckboxClicked(View view) {
        complete = ((CheckBox) view).isChecked();
    }

    // TODO #5 add in some simple form validation

    @Override
    public void onBackPressed() {
        // TODO #3 handle returning the form details when back pressed
        // TODO #3a create intent and task
        // TODO #3b create list for task to be attached to parcelable
        // TODO #3c add list to intent, set result
        // TODO #3d return
    }


}
 */