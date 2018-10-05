package com.example.a9726446.a5_2p;
//
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//
//public class MainActivity extends AppCompatActivity {
//
//    /**
//     * Click Listeners for each button.
//     * The char they pass is a key to tell which items viewFood needs to send.
//     */
//    private final View.OnClickListener anzacListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            viewFood('a');
//        }
//    };
//    private final View.OnClickListener bologneseListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            viewFood('b');
//        }
//    };
//    private final View.OnClickListener croissantListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            viewFood('c');
//        }
//    };
//    private final View.OnClickListener pizzaListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            viewFood('p');
//        }
//    };
//
//
//    /**
//     * Activity for launching the bigger food view activity.
//     * Switch statements are more fun to than nested if statements. (Subjective)
//     * @param item a char key used to identify which food to examine.
//     */
//    private void viewFood(char item) {
//        Intent i = new Intent();
//        i.setClass(getApplicationContext(), ViewActivity.class);
//        Bundle b = new Bundle();
//        switch (item){
//            case 'a':
//                b.putInt("imageID", R.drawable.anzac_320px);
//                b.putInt("captionID", R.string.strAnzac);
//                b.putInt("srcID", R.string.strSrcAnzac);
//                break;
//            case 'b':
//                b.putInt("imageID", R.drawable.bolognese_320px);
//                b.putInt("captionID", R.string.strBolognese);
//                b.putInt("srcID", R.string.strSrcBolognese);
//                break;
//            case 'c':
//                b.putInt("imageID", R.drawable.croissant_320px);
//                b.putInt("captionID", R.string.strCroissant);
//                b.putInt("srcID", R.string.strSrcCroissant);
//                break;
//            case 'p':
//                b.putInt("imageID", R.drawable.pizza_320px);
//                b.putInt("captionID", R.string.strPizza);
//                b.putInt("srcID", R.string.strSrcPizza);
//                break;
//            default:
//                //Stops the launch of the next activity if switch fails.
//                Log.e("Activity Launch", "Could not launch subsequent activity - Switch defaulted");
//                return;
//        }
//        i.putExtra("bundle", b);
//        startActivity(i);
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        initaliseUI();
//    }
//
//    private void initaliseUI() {
//        findViewById(R.id.ibtnAnzac).setOnClickListener(anzacListener);
//        findViewById(R.id.ibtnBolognese).setOnClickListener(bologneseListener);
//        findViewById(R.id.ibtnCroissant).setOnClickListener(croissantListener);
//        findViewById(R.id.ibtnPizza).setOnClickListener(pizzaListener);
//    }
//}

//*

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //TO DO #1 set up intent to get a result
    public void buttonHandler(View v) {
        Intent i = new Intent();
        i.setClass(getApplicationContext(), com.example.a9726446.a5_2p.ViewActivity.class);
        startActivityForResult(i, 1);
    }

    // TO DO #4 get result back and process/update UI
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // TO DO #4a check for result code
        if (resultCode == RESULT_OK && requestCode == 1){
            // TO DO #4b if result ok then get data and update UI
            Image i = intent.getParcelableExtra("List");
            ((TextView) findViewById(R.id.task_view)).setText(i.toString());
        } else {
            // TO DO #4c add elses/Logs for result not okay
            Log.e("Parcel Failed",String.format("Result Code: %d, Request Code: %d", resultCode, requestCode));
        }
    }
}

// */