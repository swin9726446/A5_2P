package com.example.a9726446.a4_2p;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

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
     * Activity for launching the biggerer food view activity.
     * Switch statements are fun to code. (Subjective)
     * @param item a char key used to identify which food to examine.
     */
    private void viewFood(char item) {
        Intent i = new Intent();
        i.setClass(getApplicationContext(), com.example.a9726446.a4_2p.ViewActivity.class);
        Bundle b = new Bundle();
        switch (item){
            case 'a':
                b.putInt("imageID", R.drawable.anzac_1024px);
                b.putInt("captionID", R.string.strAnzac);
                b.putInt("srcID", R.string.strSrcAnzac);
                break;
            case 'b':
                b.putInt("imageID", R.drawable.bolognese_1024px);
                b.putInt("captionID", R.string.strBolognese);
                b.putInt("srcID", R.string.strSrcBolognese);
                break;
            case 'c':
                b.putInt("imageID", R.drawable.croissant_1024px);
                b.putInt("captionID", R.string.strCroissant);
                b.putInt("srcID", R.string.strSrcCroissant);
                break;
            case 'p':
                b.putInt("imageID", R.drawable.pizza_1024px);
                b.putInt("captionID", R.string.strPizza);
                b.putInt("srcID", R.string.strSrcPizza);
                break;
            default:
                Log.e("Activity Launch", "Could not launch subsequent activity - Switch defaulted");
                return;
        }
        i.putExtra("bundle", b);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initaliseUI();
    }

    private void initaliseUI() {
        findViewById(R.id.ibtnAnzac).setOnClickListener(anzacListener);
        findViewById(R.id.ibtnBolognese).setOnClickListener(bologneseListener);
        findViewById(R.id.ibtnCroissant).setOnClickListener(croissantListener);
        findViewById(R.id.ibtnPizza).setOnClickListener(pizzaListener);
    }
}
