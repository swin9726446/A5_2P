package com.example.a9726446.a5_2p;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a9726446.a4_2p.R;

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

    private void initaliseUI() {
        try {
            Bundle b = getIntent().getExtras().getBundle("bundle");
            if (b == null) return;
            ((ImageView)(findViewById(R.id.ivBigFood))).setImageResource(b.getInt("imageID"));
            findViewById(R.id.ivBigFood).setContentDescription(getString(b.getInt("captionID")));
            ((TextView)(findViewById(R.id.tvHeader))).setText(b.getInt("captionID"));
            ((TextView)(findViewById(R.id.tvSource))).setText(b.getInt("srcID"));
        } catch (NullPointerException npe) {
            Log.e("Bundle Error (View)", npe.toString());
        }
    }

}
