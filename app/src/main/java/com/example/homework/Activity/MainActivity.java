package com.example.homework.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homework.R;

public class MainActivity extends AppCompatActivity {
    TextView donateTitle, donateSubTitle;
    Button donateButton;
    NumberPicker amountPicker;
    RadioGroup paymentMethod;
    RadioButton PayPal, Direct;
    ProgressBar progressBar;
    EditText amount;
    TextView textTotal;
    private int totalDonted = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Map();
        amountPicker.setMaxValue(1000);
        amountPicker.setMinValue(0);
        amountPicker.setValue(0);
        PayPal.setChecked(true);
        textTotal.setText("0$");
        progressBar.setMax(10000);
        ButtonPress();
    }

    private void ButtonPress() {
        donateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amount = amountPicker.getValue();
                int radioId = paymentMethod.getCheckedRadioButtonId();
                String method = "";
                if (radioId == R.id.PayPal) {
                    method = "PayPal";
                } else {
                    method = "Direct";
                }
                totalDonted += amountPicker.getValue();
                textTotal.setText(totalDonted + "$");
                progressBar.setProgress(totalDonted);
                Toast.makeText(MainActivity.this, String.valueOf(amountPicker.getValue()) + " " + method, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_donate,menu);
        return true;
    }

    private void Settings(MenuItem item){
        Toast.makeText(MainActivity.this,"Settings Selected",Toast.LENGTH_LONG).show();
    }

    private  void Report(MenuItem item){
        Toast.makeText(MainActivity.this,"Reports Selected",Toast.LENGTH_LONG).show();
    }
    private void Map() {
        donateTitle = (TextView) findViewById(R.id.donateTitle);
        donateSubTitle = (TextView) findViewById(R.id.donateSubTitle);
        donateButton = (Button) findViewById(R.id.donateButton);
        amountPicker = (NumberPicker) findViewById(R.id.amountPicker);
        paymentMethod = (RadioGroup) findViewById(R.id.paymentMethod);
        PayPal = (RadioButton) findViewById(R.id.PayPal);
        Direct = (RadioButton) findViewById(R.id.Direct);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        amount = (EditText) findViewById(R.id.amount);
        textTotal = (TextView) findViewById(R.id.textTotal);
    }
}