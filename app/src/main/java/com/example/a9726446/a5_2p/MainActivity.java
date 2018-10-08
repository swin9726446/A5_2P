package com.example.a9726446.a5_2p;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /**
     * Click Listeners for each button.
     * The char they pass is a key to tell which items viewFood needs to send.
     */
    private final View.OnClickListener anzacListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewFood('a');
        }
    };
    private final View.OnClickListener bologneseListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewFood('b');
        }
    };
    private final View.OnClickListener croissantListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewFood('c');
        }
    };
    private final View.OnClickListener pizzaListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewFood('p');
        }
    };

    /**
     * Array of the four images being displayed
     */
    private Image imageArray[];

    /**
     * Activity for launching the metadata food view activity.
     * Switch statements are more fun to than nested if statements. (Subjective)
     * @param item a char key used to identify which food to examine.
     */
    private void viewFood(char item) {
        Intent i = new Intent();
        i.setClass(getApplicationContext(), ViewActivity.class);
        //This is an alphanumeric to correspond to the item being updated.
        int request_code;
        switch (item){
            case 'a':
                i.putExtra("image", imageArray[0]);
                request_code = 1;
                break;
            case 'b':
                i.putExtra("image", imageArray[1]);
                request_code = 2;
                break;
            case 'c':
                i.putExtra("image", imageArray[2]);
                request_code = 3;
                break;
            case 'p':
                i.putExtra("image", imageArray[3]);
                request_code = 16;
                break;
            default:
                //Stops the launch of the next activity if switch fails.
                Log.e("Activity Launch", "Could not launch subsequent activity - Switch defaulted");
                return;
        }
        startActivityForResult(i,request_code);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initaliseUI();
    }

    private void initaliseUI() {
        int length = getResources().getStringArray(R.array.foods).length;
        imageArray = new Image[length];
        for (int i = 0; i < length; i += 1){
            setUpImageButton(i);
        }

        findViewById(R.id.ib_anzac).setOnClickListener(anzacListener);
        findViewById(R.id.ib_bolognese).setOnClickListener(bologneseListener);
        findViewById(R.id.ib_croissant).setOnClickListener(croissantListener);
        findViewById(R.id.ib_pizza).setOnClickListener(pizzaListener);
    }

    //This section loads each image's metadata from file.
    //  id, name, src, key, date, email, rating, shared
    private void setUpImageButton (int index){
        TypedArray typedArray = getResources().obtainTypedArray(getResources().getIdentifier(
                        getResources().getStringArray(R.array.foods)[index],
                        "array", getPackageName()
                ));
        int i = 0;
        imageArray[index] = new Image(
                getResources().getIdentifier(
                        getResources().getStringArray(R.array.foods)[index] + "_320px",
                        "drawable", getPackageName()
                ),
                typedArray.getString(i),
                typedArray.getString(i+=1),
                typedArray.getString(i+=1),
                typedArray.getString(i+=1),
                typedArray.getString(i+=1),
                typedArray.getString(i+=1),
                typedArray.getBoolean(i, false)
        );

        ((ImageButton) findViewById(getResources().getIdentifier(
                "ib_"+ getResources().getStringArray(R.array.foods)[index],
                "id", getPackageName())
        )).setImageResource(imageArray[index].getResourceID());

        findViewById(getResources().getIdentifier(
                "ib_"+ getResources().getStringArray(R.array.foods)[index],
                "id", getPackageName())
        ).setContentDescription(imageArray[index].getName());

        ((TextView) findViewById(getResources().getIdentifier(
                "tv_label_"+ getResources().getStringArray(R.array.foods)[index],
                "id", getPackageName())
        )).setText(imageArray[index].getName());

        ((TextView) findViewById(getResources().getIdentifier(
                "tv_date_"+ getResources().getStringArray(R.array.foods)[index],
                "id", getPackageName())
        )).setText(imageArray[index].getDate());

        typedArray.recycle();
    }

    /**
     * Handles updates to the display.
     * @param requestCode It's a cheesy way to do it, but switch statements are so much fun~
     * @param resultCode Determines if the child activity terminated properly
     * @param intent an intent with data to pass, hopefully!
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        //Get Errors out of the way first.
        if (resultCode != RESULT_OK) Log.e("Parcel Failed", String.format(
                "Result Code %d, Request Code: %d", resultCode, requestCode
        )); else {
            Image image = intent.getParcelableExtra("image");
            switch (requestCode){
                case 1: //anzac
                    ((TextView) findViewById(R.id.tv_label_anzac)).setText(image.getName());
                    ((TextView) findViewById(R.id.tv_date_anzac)).setText(image.getDate());
                    (findViewById(R.id.ib_anzac)).setContentDescription(image.getName());
                    break;
                case 2: //bolognese
                    ((TextView) findViewById(R.id.tv_label_bolognese)).setText(image.getName());
                    ((TextView) findViewById(R.id.tv_date_bolognese)).setText(image.getDate());
                    (findViewById(R.id.ib_bolognese)).setContentDescription(image.getName());
                    break;
                case 3: //croissant
                    ((TextView) findViewById(R.id.tv_label_croissant)).setText(image.getName());
                    ((TextView) findViewById(R.id.tv_date_croissant)).setText(image.getDate());
                    (findViewById(R.id.ib_croissant)).setContentDescription(image.getName());
                    break;
                case 16: //pizza
                    ((TextView) findViewById(R.id.tv_label_pizza)).setText(image.getName());
                    ((TextView) findViewById(R.id.tv_date_pizza)).setText(image.getDate());
                    (findViewById(R.id.ib_pizza)).setContentDescription(image.getName());
                    break;
                default:
                    //Stops the launch of the next activity if switch fails.
                    Log.e("Activity Launch", "Could not launch subsequent activity - Switch defaulted");
                    //return; //Last statement in void - not needed.
            }
        }
    }
}

//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//
//    //TO DO #1 set up intent to get a result
//    public void buttonHandler(View v) {
//        Intent i = new Intent();
//        i.setClass(getApplicationContext(), com.example.a9726446.a5_2p.ViewActivity.class);
//        startActivityForResult(i, 1);
//    }
//
//    // TO DO #4 get result back and process/update UI
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        // TO DO #4a check for result code
//        if (resultCode == RESULT_OK && requestCode == 1){
//            // TO DO #4b if result ok then get data and update UI
//            Image i = intent.getParcelableExtra("List");
//            ((TextView) findViewById(R.id.task_view)).setText(i.getName());
//        } else {
//            // TO DO #4c add elses/Logs for result not okay
//            Log.e("Parcel Failed",String.format(
//                  "Result Code: %d, Request Code: %d", resultCode, requestCode
//            ));
//        }
//    }
//}