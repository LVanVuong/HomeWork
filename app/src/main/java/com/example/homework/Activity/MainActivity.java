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

import com.example.homework.Model.Donation;
import com.example.homework.R;

public class MainActivity extends Base {
    TextView donateTitle, donateSubTitle;
    Button donateButton;
    NumberPicker amountPicker;
    RadioGroup paymentMethod;
    RadioButton PayPal, Direct;
    ProgressBar progressBar;
    EditText amount;
    public static TextView textTotal;

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
                String method  = paymentMethod.getCheckedRadioButtonId() == R.id.PayPal?"PayPay" : "Driect";
                int donationAmount = amountPicker.getValue();
                if(donationAmount == 0){
                    String text = amount.getText().toString();
                    if(!text.equals("")){
                        donationAmount = Integer.parseInt(text);
                    }
                } else if(donationAmount >0){
                    donationApp.newDonation(new Donation(donationAmount,method));
                    progressBar.setProgress(donationApp.totalDonated);
                    String totalDonateStr =  donationApp.totalDonated + "$";
                    textTotal.setText(totalDonateStr);
                }
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