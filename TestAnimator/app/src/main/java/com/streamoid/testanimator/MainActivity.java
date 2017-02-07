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
import com.streamoid.animatorsdk.external.AnimatorLanguageCodes;
import com.streamoid.animatorsdk.external.RequestCallback;
import com.streamoid.animatorsdk.external.RequestItem;
import com.streamoid.animatorsdk.misc.general.Logger;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String LANGUAGE_PREFERENCES = "app_language";
    private static final String SPANISH_LANG = "Spanish";
    private static final String ENGLISH_LANG = "English";
    private static final String RUSSIAN_LANG = "Russian";
    private static String DEFAULT_LANG = "";
    private static final String STORE_PREFERENCES = "test_animator_sdk";

    private static SharedPreferences sSharedPreferences;

    private Button spanish;
    private Button russian;
    private Button english;
    private Button phoneLocale;
    private TextView languageText;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
        setupStore();
    }

    private void setupView() {
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        english = (Button)findViewById(R.id.english);
        phoneLocale = (Button)findViewById(R.id.phone_locale);
        spanish = (Button)findViewById(R.id.spanish);
        russian = (Button)findViewById(R.id.russian);
        languageText = (TextView) findViewById(R.id.lang);
        mToolbar.setTitle("Test Animator");
        setupListeners();

    }

    private void setupListeners() {

        spanish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupLanguage(SPANISH_LANG, AnimatorLanguageCodes.SPANISH_CODE);
                addLanguageToStore(SPANISH_LANG);
            }
        });

        russian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupLanguage(RUSSIAN_LANG, AnimatorLanguageCodes.RUSSIAN_CODE);
                addLanguageToStore(RUSSIAN_LANG);
            }
        });
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupLanguage(ENGLISH_LANG, AnimatorLanguageCodes.ENGLISH_CODE);
                addLanguageToStore(ENGLISH_LANG);
            }
        });
        phoneLocale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupLanguage("Default", AnimatorLanguageCodes.DEFAULT_CODE);
                addLanguageToStore("");
                AnimatorClient.registerForUserEvents(MainActivity.this, null);
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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


    private void setupLanguage(String language, AnimatorLanguageCodes code) {
        languageText.setText(language);
        AnimatorClient.setAnimatorLanguage(MainActivity.this, code);
    }

    private void addLanguageToStore(String language) {
        if(null != sSharedPreferences && null != language) {
            sSharedPreferences.edit().putString(LANGUAGE_PREFERENCES, language).apply();
        }
    }

    private void setupStore() {
        if(null == sSharedPreferences) {
            sSharedPreferences = MainActivity.this.
                    getSharedPreferences(STORE_PREFERENCES, Context.MODE_PRIVATE);
            /**
             * Initialising the shared preferences to the default language.
             */
            sSharedPreferences.edit().putString(LANGUAGE_PREFERENCES, "empty").apply();
        }
        checkIfLanguageIsSet();
    }

    private void checkIfLanguageIsSet() {
        if(null != sSharedPreferences){
            String languageSelected = sSharedPreferences.getString(LANGUAGE_PREFERENCES, "");
            switch (languageSelected){
                case SPANISH_LANG:
                    AnimatorClient.setAnimatorLanguage(this, AnimatorLanguageCodes.SPANISH_CODE);
                    languageText.setText(SPANISH_LANG);
                    break;
                case RUSSIAN_LANG:
                    AnimatorClient.setAnimatorLanguage(this, AnimatorLanguageCodes.RUSSIAN_CODE);
                    languageText.setText(RUSSIAN_LANG);
                    break;
                case ENGLISH_LANG:
                    AnimatorClient.setAnimatorLanguage(this, AnimatorLanguageCodes.ENGLISH_CODE);
                    languageText.setText(ENGLISH_LANG);
                    break;
                case "empty":
                    languageText.setText("Default");
                    break;
                default:
                    AnimatorClient.setAnimatorLanguage(this, AnimatorLanguageCodes.DEFAULT_CODE);
                    languageText.setText(DEFAULT_LANG);
                    break;
            }
        }
    }
}
