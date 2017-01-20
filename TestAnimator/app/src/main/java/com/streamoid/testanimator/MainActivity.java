package com.streamoid.testanimator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.streamoid.animatorsdk.external.AnimatorClient;
import com.streamoid.animatorsdk.external.RequestCallback;
import com.streamoid.animatorsdk.external.RequestItem;
import com.streamoid.animatorsdk.misc.general.Logger;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**
                 * Entry point to open the camera
                 * @param context pass the current context
                 * @param callback receive any success / error cases here
                 */
                AnimatorClient.openCamera(MainActivity.this, new RequestCallback() {
                    @Override
                    public void onSuccess(RequestItem requestItem) {
                        Logger.errorLogs(TAG, "Success");
                    }

                    @Override
                    public void onError(RequestItem requestItem) {
                        Logger.errorLogs(TAG, "Error: " + requestItem.getResponse().string);
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
