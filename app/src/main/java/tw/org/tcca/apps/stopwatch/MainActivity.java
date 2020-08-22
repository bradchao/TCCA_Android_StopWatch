package tw.org.tcca.apps.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Button leftBtn, rightBtn;
    private boolean isRunning;  // false
    private TextView clock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clock = findViewById(R.id.clock);
        leftBtn = findViewById(R.id.leftBtn);
        rightBtn = findViewById(R.id.rightBtn);

        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickLeft();
            }
        });

        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickRight();
            }
        });
    }

    private void clickLeft(){

    }

    private void clickRight(){
        isRunning = !isRunning;
        rightBtn.setText(isRunning?"Stop":"Start");
        leftBtn.setText(isRunning?"Lap":"Reset");
        if (isRunning){
            startClock();
        }else{
            stopClock();
        }
    }

    private int counter;    // 0
    private Timer timer = new Timer();
    private class MyTask extends TimerTask {
        @Override
        public void run() {
            counter++;
            clock.setText("counter = " + counter);
            Log.v("bradlog", "counter = " + counter);
        }
    }

    private MyTask myTask;
    private void startClock(){
        myTask = new MyTask();
        timer.schedule(myTask, 100, 100);
    }

    private void stopClock(){
        myTask.cancel();
    }

    @Override
    public void finish() {
        if (timer != null){
            timer.cancel();
            timer.purge();
            timer = null;
        }
        super.finish();
    }
}