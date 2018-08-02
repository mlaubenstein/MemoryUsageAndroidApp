package com.example.marvin.memoryusage;

import android.app.ActivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener {

    private String[][] activityString = new String[1000][];
    private int index = 0;
    private Runtime runtime = Runtime.getRuntime();
    private TextView textView;
    private ActivityManager activityManager;
    private int memoryClass;
    private Animation rotate;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.8F);
    private String[] star;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        assert activityManager != null;
        memoryClass = activityManager.getMemoryClass();
        rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);
        showMemory();
        setHasOptionMenu(true);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));


        if (getIntent().getBooleanExtra("crash", false))
            Toast.makeText( this,
                            "Warte kurz, wird gemacht !", Toast.LENGTH_LONG)
                            .show();

    }

    private void setHasOptionMenu(boolean b) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_restart,menu);
        getMenuInflater().inflate(R.menu.menu_exit, menu);
        return true;

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.exit)
        {

            System.exit(0);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showMemory() {
        long max = runtime.maxMemory();
        long total = runtime.totalMemory();
        long free = runtime.freeMemory();
        long consumed = (runtime.totalMemory()-runtime.freeMemory())/1024;

        textView.setText(
                String.format(  "Memory Class (MB): %s\n\n" +
                                "Max Memory (KB): %s\n" +
                                "Total Memory (KB): %s\n" +
                                "Free Memory (KB): %s\n\n" +
                                "Memory consumed (KB): %s",
                                Long.toString(memoryClass),
                                Long.toString(max),
                                Long.toString(total),
                                Long.toString(free),
                                Long.toString(consumed)));
    }

    @Override
    public void onClick(View view) {

        makingStrings(0);
        showMemory();
        view.startAnimation(buttonClick);
        //view.startAnimation(rotate);
    }


    public void restart(MenuItem item) {
        makingStrings(1);
        throw new NullPointerException();

    }

    public void makingStrings(int resetNumber){
        if(resetNumber==0 ){
            star = new String[100_000];
            for (counter = 0; counter<100_000; counter++) star[counter] = new String();
            activityString[index++] = star;
        }
        else if (resetNumber == 1){

            for( counter = index; activityString.length>counter; counter--){
                activityString[counter] = null;
            }
            //onTrimMemory(ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL);


        }
    }
}
