package com.example.a9726446.a5_2p;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

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

    Calendar aCalendar = Calendar.getInstance();
    EditText et = (EditText) findViewById(R.id.etDate);
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            aCalendar.set(Calendar.YEAR, year);
            aCalendar.set(Calendar.MONTH, month);
            aCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    private void updateLabel(){
        String formatStr = "dd/MM/yy"; //Calendar Format
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr, Locale.ENGLISH);
        et.setText(sdf.format(aCalendar.getTime()));
    }

    private void initaliseUI() {
        try {
            //Bundle b = getIntent().getExtras().getBundle("bundle");
            //if (b == null) return;
            /*((ImageView)(findViewById(R.id.ivBigFood))).setImageResource(b.getInt("imageID"));
            findViewById(R.id.ivBigFood).setContentDescription(getString(b.getInt("captionID")));
            ((TextView)(findViewById(R.id.tvHeader))).setText(b.getInt("captionID"));
            ((TextView)(findViewById(R.id.tvSource))).setText(b.getInt("srcID"));*/
        } catch (NullPointerException npe) {
            Log.e("Bundle Error (View)", npe.toString());
        }
        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ViewActivity.this, date,
                        aCalendar.get(Calendar.YEAR),
                        aCalendar.get(Calendar.MONTH),
                        aCalendar.get(Calendar.DAY_OF_MONTH)
                ).show();
            }
        });
    }

}
