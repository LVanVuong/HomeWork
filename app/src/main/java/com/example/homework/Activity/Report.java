package com.example.homework.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homework.Model.Donation;
import com.example.homework.R;

import java.util.List;

public class Report extends Base implements AdapterView.OnItemClickListener {
    ListView listView;
    DonationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        listView = findViewById(R.id.listView);
        adapter = new DonationAdapter(this,donationApp.dbManger.getAll());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long id) {

        Toast.makeText(this, "You Selected Row [ " + pos + "]\n" +
                "For Donation Data [ " + adapter.donationList.get(pos) + "]\n " +
                "With ID of [" + id + "]", Toast.LENGTH_LONG).show();

    }
}
    class DonationAdapter extends ArrayAdapter<Donation>{
        private Context mContext;
        public List<Donation> donationList;

        public DonationAdapter (Context context , List<Donation> donationList){
            super(context , R.layout.row_donate,donationList);
            this.mContext =context;
            this.donationList = donationList;
        }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View  view       = inflater.inflate(R.layout.row_donate, parent, false);
        Donation donation   = donationList.get(position);
        TextView amountView = (TextView) view.findViewById(R.id.row_amount);
        TextView methodView = (TextView) view.findViewById(R.id.row_method);

        amountView.setText("$" + donation.amount);
        methodView.setText(donation.method);

        return view;
    }

        @Override
        public int getCount()
        {
            return donationList.size();
        }
}