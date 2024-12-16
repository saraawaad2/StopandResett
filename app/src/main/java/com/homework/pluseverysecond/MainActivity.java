package com.homework.pluseverysecond;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
private int minute,second;
Button Start,Stop,Reset;
private TextView tvcount ;
private Thread counterThread;
Boolean flag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvcount=findViewById(R.id.minSec);
        Start=findViewById(R.id.Start);
        Stop=findViewById(R.id.stop);
        Reset=findViewById(R.id.reset);
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCount();
            }
        });
        Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopCount();
            }
        });
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetCount();
            }
        });
    }
        public void startCount (){
        if(flag) {
            flag=false;
            counterThread = new Thread(() -> {
                try {
                    while (true) {
                        second++;
                        if (second == 59) {
                            second = 0;
                            minute++;
                        }
                        tvcount.setText(minute + ":" + second);
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                }
            });
           counterThread.start();
        }
    }
    public void stopCount(){
        counterThread.interrupt();
        flag=true;
    }
    public void resetCount(){
        minute=0;
        second=0;
        tvcount.setText("00:00");
    }
    }
