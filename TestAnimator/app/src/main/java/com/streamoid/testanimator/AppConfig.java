package com.streamoid.testanimator;

import android.app.Application;
import android.util.Log;

import com.streamoid.animatorsdk.external.AnimatorClient;
import com.streamoid.animatorsdk.external.AnimatorLanguageCodes;
import com.streamoid.animatorsdk.external.RequestCallback;
import com.streamoid.animatorsdk.external.RequestItem;
import com.streamoid.animatorsdk.external.UserEvent;
import com.streamoid.animatorsdk.external.UserEventCallback;
import com.streamoid.animatorsdk.misc.general.Logger;

/**
 * Created by veena on 18/1/17.
 */

public class AppConfig extends Application {
    /**
     * set the values of CLIENT_NAME, CLIENT_TOKEN that you have received from us.
     */
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
        AnimatorClient.initialize(new AnimatorClient.Configuration.Builder(getApplicationContext())
                .setClientName(CLIENT_NAME)
                .setClientToken(CLIENT_TOKEN)
                .setLanguage(AnimatorLanguageCodes.ENGLISH_CODE)
                .setLogLevel(Log.VERBOSE)
                .setRequestCallback(new RequestCallback() {
                    @Override
                    public void onSuccess(RequestItem requestItem) {
                        Logger.errorLogs("App Config", "Success");
                    }

                    @Override
                    public void onError(RequestItem requestItem) {
                        Logger.errorLogs("App Config", "Error: " + requestItem.getResponse().string);
                    }
                })
                .setUserEventCallback(new UserEventCallback() {
                    @Override
                    public void onEventReceived(UserEvent event) {
                        Logger.errorLogs("App Config", event.toString());
                    }
                })
                .setEnbaleDirectAction(true) // set to false if Direct Action feature has to be disabled.
                .build());
    }
}