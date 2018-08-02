package com.example.marvin.memoryusage;

import android.app.ActivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String[][] activityString = new String[1000][];
    private int index = 0;
    private Runtime runtime = Runtime.getRuntime();
    private TextView textView;
    private ActivityManager activityManager;
    private int memoryClass;
    private Animation rotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);


        activityManager = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
        memoryClass = activityManager.getMemoryClass();
        showMemory();

        rotate = AnimationUtils.loadAnimation(this,R.anim.rotate);

    }

    private void showMemory() {
        long max = runtime.maxMemory();
        long total = runtime.totalMemory();
        long free = runtime.freeMemory();
        long consumed = (runtime.totalMemory()-runtime.freeMemory())/1024;

        textView.setText(
                            "Memory Class (MB): "   + Long.toString(memoryClass) +
                            "\n\nMax Memory (KB): "     + Long.toString(max) +
                            "\nTotal Memory (KB): "   + Long.toString(total) +
                            "\nFree Memory (KB): "    + Long.toString(free) +
                            "\n\nMemory consumed (KB): "+ Long.toString(consumed));
    }

    @Override
    public void onClick(View view) {
        String[] star = new String[100_000];
        for (int i = 0; i<100_000; i++)
            star[i] = new String();
        activityString[index++] = star;
        showMemory();

        view.startAnimation(rotate);
    }
}
