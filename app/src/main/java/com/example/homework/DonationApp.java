package com.example.homework;

import android.app.Application;
import android.widget.Toast;

import com.example.homework.Database.DBManger;
import com.example.homework.Model.Donation;

public class DonationApp extends Application {
    public final int target = 10000;
    public int totalDonated = 0;
    public DBManger  dbManger;
    public boolean newDonation(Donation donation){
        boolean targetAchieved =  totalDonated > target;
        if(!targetAchieved){
            dbManger.add(donation);
            totalDonated +=donation.amount;
        }else {
            Toast.makeText(this,"Target Exceeded",Toast.LENGTH_LONG).show();    
        }
        return targetAchieved;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        dbManger = new DBManger(this);
    }
}
