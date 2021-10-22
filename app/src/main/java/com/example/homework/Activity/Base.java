package com.example.homework.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.service.voice.VoiceInteractionSession;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homework.DonationApp;
import com.example.homework.R;

public class Base extends AppCompatActivity {
    public DonationApp donationApp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        donationApp = (DonationApp) getApplication();
        donationApp.dbManger.open();
        donationApp.dbManger.setTotalDonated(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        donationApp.dbManger.cloes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_donate,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem report = menu.findItem(R.id.report);
        MenuItem donate = menu.findItem(R.id.donate);
        MenuItem reset = menu.findItem(R.id.Reset);
        if(donationApp.dbManger.getAll().isEmpty()){
            report.setEnabled(false);
            reset.setEnabled(false);
        }else {
            report.setEnabled(true);
            reset.setEnabled(true);
        }
        if(this instanceof MainActivity){
            donate.setVisible(true);
            if(!donationApp.dbManger.getAll().isEmpty()){
                report.setVisible(true);
                reset.setVisible(true);
            }
        } else{
            report.setVisible(false);
            reset.setVisible(false);
            donate.setVisible(true);
        }
        return  true;
    }
    public void report(MenuItem menuItem){
        startActivity(new Intent(this, Report.class));
    }
    public  void  donate(MenuItem menuItem){
        startActivity(new Intent(this, MainActivity.class));
    }
    public void reset(MenuItem menuItem){}
}
