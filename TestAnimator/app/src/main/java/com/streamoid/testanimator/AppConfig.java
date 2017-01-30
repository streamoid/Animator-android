package com.streamoid.testanimator;

import android.app.Application;

import com.streamoid.animatorsdk.external.AnimatorClient;
import com.streamoid.animatorsdk.external.RequestCallback;
import com.streamoid.animatorsdk.external.RequestItem;
import com.streamoid.animatorsdk.misc.general.Logger;

/**
 * Created by veena on 18/1/17.
 */

public class AppConfig extends Application {
    private String CLIENT_TOKEN = "";
    private String CLIENT_NAME = "";

    @Override
    public void onCreate() {
        super.onCreate();
/**
 * This is the initialization entry point to the SDK
 * @param context the current context
 * @param clientName your unique name
 * @param clientToken unique token provided to access data
 * @param callback  access any root causes of error / error / success cases using this callback
 * @param parameters parameters to customize the app. If null, the default settings will be used.
 */
        AnimatorClient.initialize(getBaseContext(),
                CLIENT_NAME, CLIENT_TOKEN, new RequestCallback() {
                    @Override
                    public void onSuccess(RequestItem requestItem) {
                        Logger.errorLogs("App Config", "Success");

                    }

                    @Override
                    public void onError(RequestItem requestItem) {
                        Logger.errorLogs("App Config", "Error: " + requestItem.getResponse().string);

                    }
                });

    }
}