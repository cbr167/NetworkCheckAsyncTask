package com.example.bhagy.incrementdecrementhandlers;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView numberText;
    private Button plusButton;
    private Button minusButton;
    private float presentValue = 0;
    private String updatedNumber;
    private Boolean autoIncrement = false;
    private Boolean autoDecrement = false;

    Handler handler = new Handler();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberText = (TextView) findViewById(R.id.intUpdate);
        minusButton = (Button) findViewById(R.id.minusButton);
        plusButton = (Button) findViewById(R.id.plusButton);


//minusButton.setOnClickListener(view -> clickMinus());
//plusButton.setOnClickListener(view -> clickPlus());

        plusButton.setOnLongClickListener(view -> {
            autoIncrement = true;
            handler.post(new RptUpdater());
            return false;
        });
        minusButton.setOnLongClickListener(view -> {
            autoDecrement = true;
            handler.post(new RptUpdater());
            return false;
        });
        plusButton.setOnTouchListener((view, motionEvent) -> {

            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                autoIncrement = false;
            } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                clickPlus();
            }
            return false;
        });


        
        minusButton.setOnTouchListener((view, motionEvent) -> {

            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                autoDecrement = false;
            } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                clickMinus();
            }
            return false;
        });
    }

    private void clickMinus() {

        if (presentValue > 0) {
            presentValue =  presentValue + 0.1f;
            presentValue--;
            updatedNumber = String.valueOf(presentValue);
            numberText.setText(updatedNumber);
        }

    }




    private void clickPlus() {

        presentValue = presentValue + 1;
        updatedNumber = String.valueOf(presentValue);
        numberText.setText(updatedNumber);
        Log.d("incrnumber", String.valueOf(presentValue));
    }

    class RptUpdater implements Runnable {
        public void run() {
            if (autoIncrement) {
                clickPlus();
                handler.postDelayed(this, 50);
            } else if (autoDecrement) {
                clickMinus();
                handler.postDelayed(new RptUpdater(), 50);
            }
        }
    }
}
