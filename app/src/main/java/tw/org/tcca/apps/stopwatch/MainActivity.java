package tw.org.tcca.apps.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Contacts;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Button leftBtn, rightBtn;
    private boolean isRunning;  // false
    private TextView clock;

    private ListView lapList;
    private SimpleAdapter adapter;
    private LinkedList<HashMap<String,String>> data = new LinkedList<>();
    private String[] from = {"No", "Lap"};
    private int[] to = {R.id.itemNo, R.id.itemLap};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lapList = findViewById(R.id.lapList);
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

        initList();
    }

    private void initList(){
        adapter = new SimpleAdapter(this, data, R.layout.item, from, to);
        lapList.setAdapter(adapter);
    }

    private void clickLeft(){
        if (isRunning){
            // Lap
            doLap();
        }else{
            // Reset
            doReset();
        }
    }

    private void doReset(){
        counter = 0;
        uiHandler.sendEmptyMessage(0);
    }

    private void doLap(){
        HashMap<String,String> row = new HashMap<>();
        row.put(from[0], "" + (data.size()+1));
        row.put(from[1], clock.getText().toString());
        data.add(row);
        adapter.notifyDataSetChanged();
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
    private UIHandler uiHandler = new UIHandler();
    private class MyTask extends TimerTask {
        @Override
        public void run() {
            counter++;
            uiHandler.sendEmptyMessage(0);
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

    private class UIHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            clock.setText("counter = " + counter);
        }
    }

}