package com.leachchen.testview.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.leachchen.testview.R;
import com.leachchen.testview.View.BatteryView;

public class BatteryActivity extends AppCompatActivity {

    private BatteryView batteryview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);

        batteryview = (BatteryView)findViewById(R.id.batteryview);
    }

    public void process0(View v)
    {
        batteryview.updateProcess(0);
    }

    public void process50(View v)
    {
        batteryview.updateProcess(50);
    }


    public void process80(View v)
    {
        batteryview.updateProcess(80);
    }

    public void process100(View v)
    {
        batteryview.updateProcess(100);
    }
}
