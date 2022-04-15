package com.android.simpleasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private ProgressBar pbXuLy;
    private static final String TEXT_STATE = "currentText";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textView1);
        pbXuLy= findViewById(R.id.progressBar);

        if(savedInstanceState!=null){
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
        }
    }

    public void startTask(View view) {
        mTextView.setText(R.string.napping);
        new SimpleAsyncTask(mTextView).execute();
        //60000 là tổng thời gian đếm ngược và 6000 là mỗi 6 giây sẽ nhảy processbar 1 lần
        CountDownTimer countDownTimer = new CountDownTimer(60000,6000) {
            @Override
            public void onTick(long l) {
                int current = pbXuLy.getProgress();
                if (current>=pbXuLy.getMax()) current=0;
                pbXuLy.setProgress(current+10);
            }

            @Override
            public void onFinish() {
                pbXuLy.setProgress(0);
            }
        }.start();

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the state of the TextView
        outState.putString(TEXT_STATE, mTextView.getText().toString());
    }
}