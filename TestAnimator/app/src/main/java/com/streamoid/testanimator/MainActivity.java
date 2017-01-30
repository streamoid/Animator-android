package com.streamoid.testanimator;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.streamoid.animatorsdk.external.AnimatorClient;
import com.streamoid.animatorsdk.external.ConstantValues;
import com.streamoid.animatorsdk.external.RequestCallback;
import com.streamoid.animatorsdk.external.RequestItem;
import com.streamoid.animatorsdk.misc.general.Logger;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String LANGUAGE_PREFERENCES = "app_language";
    private static final String SPANISH_LANG = "Spanish";
    private static final String RUSSIAN_LANG = "Russian";
    private static String DEFAULT_LANG = "";
    private static final String STORE_PREFERENCES = "test_animator_sdk";

    private static SharedPreferences sSharedPreferences;

    private Button spanish;
    private Button russian;
    private Button  defaultLang;
    private TextView languageText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DEFAULT_LANG = Locale.getDefault().getLanguage();
        setContentView(R.layout.activity_main);
        setupView();
        setupStore();

    }



    private void setupView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Test Animator");
        setSupportActionBar(toolbar);

        spanish = (Button)findViewById(R.id.spanish);
        russian = (Button)findViewById(R.id.russian);
        defaultLang = (Button)findViewById(R.id.defaultLang);
        languageText = (TextView) findViewById(R.id.lang);
        setupListeners();
    }

    private void setupListeners() {
        spanish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                languageText.setText(SPANISH_LANG);
                AnimatorClient.setAnimatorLocale(MainActivity.this, ConstantValues.SPANISH_CODE);
                addLanguageToStore(SPANISH_LANG);
            }
        });

        russian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                languageText.setText(RUSSIAN_LANG);
                AnimatorClient.setAnimatorLocale(MainActivity.this, ConstantValues.RUSSIAN_CODE);
                addLanguageToStore(RUSSIAN_LANG);
            }
        });

        defaultLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                languageText.setText("Default");
                AnimatorClient.setAnimatorLocale(MainActivity.this, DEFAULT_LANG);
                addLanguageToStore(DEFAULT_LANG);
            }
        });
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

    private void addLanguageToStore(String language) {
        if(null != sSharedPreferences && null != language) {
            sSharedPreferences.edit().putString(LANGUAGE_PREFERENCES, language).apply();
        }
    }

    private void setupStore() {
        if(null == sSharedPreferences) {
            sSharedPreferences = MainActivity.this.getSharedPreferences(STORE_PREFERENCES, Context.MODE_PRIVATE);
            sSharedPreferences.getString(LANGUAGE_PREFERENCES, "");
        }
        checkIfLanguageIsSet();
    }

    private void checkIfLanguageIsSet() {
        if(null != sSharedPreferences){
            String languageSelected = sSharedPreferences.getString(LANGUAGE_PREFERENCES, "");
            switch (languageSelected){
                case SPANISH_LANG:
                    AnimatorClient.setAnimatorLocale(this, ConstantValues.SPANISH_CODE);
                    languageText.setText(SPANISH_LANG);
                    break;
                case RUSSIAN_LANG:
                    AnimatorClient.setAnimatorLocale(this, ConstantValues.RUSSIAN_CODE);
                    languageText.setText(RUSSIAN_LANG);
                    break;
                default:
                    AnimatorClient.setAnimatorLocale(this, DEFAULT_LANG);
                    languageText.setText(Locale.getDefault().getDisplayLanguage());
                    break;
            }
        }
    }
}
