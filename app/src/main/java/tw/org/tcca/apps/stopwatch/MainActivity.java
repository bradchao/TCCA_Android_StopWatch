package tw.org.tcca.apps.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button leftBtn, rightBtn;
    private boolean isRunning;  // false

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    }

}