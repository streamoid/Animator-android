# Animator Android

[![N|Solid](http://www.streamoid.com/images/logo.png)](http://www.streamoid.com/)

This repository contains binary distributions of Animator-android framework.

If you have any questions, comments, or issues related to Animator, Please contact the team by emailing streamoid.support@streamoid.com.


### Animator

**Animator-android** framework (Api level 21 and above) provided by Streamoid Technologies, acts as a bridge between offline advertisements and purchasing those products online.

**TestAnimator** is an example application which uses Animator-android framework.

Imagine a scenario where you see some product advertisement in a newspaper ad or in a product catalogue, feel like knowing more about that product or buying it online. Animator-android SDK solves the exact same problem.

If the offline advertisement is Animator enabled, user can click on the product image using the camera screen provided by Animator framework, unlock many features like : 
- Watching a video related to that product
- Buy that product online by navigating to the product's webpage
- Call the shop/brand directly in 1 click
- Listen to a promo audio clip
- View more images of the product


### Installation

### Binary

You may [download AAR releases here.](https://github.com/streamoid/Animator-android/releases)

### JCenter

Add JCenter to your build file's list of repositories.

```groovy
repositories {
    jcenter()
}
```

to use the JCenter Repository

```groovy
dependencies {
    ...
        compile 'com.streamoid:animatorsdk:1.1.4'
    ...
}
```

Add following to manifest application


```
 <application
  ...
  tools:replace="android:theme, icon, label"
  ...
  >
```

### Verifying Animator Configuration

Once you have finished adding Animator framework to your project, you can test your configuration by importing the dependencies and connecting a client to the Animator cloud. To do so, add following code to your Application class. (note that you must substitute the CLIENT_NAME and CLIENT_TOKEN placeholder text with your actual values, in order to get these values please contact us at streamoid.support@streamoid.com):

### Simple Intialization
```sh
     AnimatorClient.initialize(new AnimatorClient.Configuration.Builder(getApplicationContext())
                .setClientName(CLIENT_NAME)
                .setClientToken(CLIENT_TOKEN)
                .setLanguage(AnimatorLanguageCodes.ENGLISH_CODE)
                .setRequestCallback(new RequestCallback() {
                    @Override
                    public void onSuccess(RequestItem requestItem) {
                        // Success Block
                    }

                    @Override
                    public void onError(RequestItem requestItem) {
                        // Failure Block
                    }
                })
                .setUserEventCallback(new UserEventCallback() {
                    @Override
                    public void onEventReceived(UserEvent event) {
                        // Use event for analytics if required
                    }
                })
                .setEnbaleDirectAction(true) // Set to false if Direct Action feature has to be disabled.
                .build());
```

Launch your application and verify that the connection is successful. You are now ready to unlock the features of Animator.


### To Start Image Recognition by Animator

Below method can be called only after initialization of `AnimatorClient`

```sh
    AnimatorClient.openCamera(context);
```

### To use your preferred language

Animator Framework supports multiple languages. As of now, English, Spanish and Russian are supported. More languages will be supported in the future... It is optional to set the language. If no language is set, we use the default language used by app. After initializing an animator client in your application, you can specify the language you need to see the framework by using the following method in framework:

```sh
    AnimatorClient.setAnimatorLanguage(context, AnimatorLanguageCodes.ENGLISH_CODE);
```

ConstantValues will list out the languages supported by the SDK. Your app can use one of the language codes specified in ConstantValues to indicate the language that has to be used by the SDK.

### Note

Following Permissions are mandatory:

```sh
Manifest.permission.CAMERA // To use Device Camera
Manifest.permission.INTERNET // To connect Webservice and fetch matches
Manifest.permission.ACCESS_NETWORK_STATE // To check Network Availibility
```

### Customize the framework

To Update/modify icons and colors please create animator_styles.xml in your Application style folder and override styles in the following pattern which is self explanatory:

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <!--
    *****************************************************
    CAMERA SCREEN
    *****************************************************
    -->

    <!--SHUTTER/CAPTURE ICON-->
    <style name="anim_shutter_style">
        <item name="android:src">@drawable/ic_shutter</item>
    </style>
    <!--CLOSE(TOP LEFT) ICON-->
    <style name="anim_close_style">
        <item name="android:src">@drawable/close_navigator_w</item>
    </style>
    <!--WHY(TOP RIGHT) ICON-->
    <style name="anim_why_style">
        <item name="android:src">@drawable/ic_why</item>
    </style>
    <!--RECENT(BOTTOM LEFT) ICON-->
    <style name="anim_recent_style">
        <item name="android:src">@drawable/ic_recent</item>
    </style>

    <!--SELECTOR TEMPLATE FOR FLASH(BOTTOM RIGHT) ICON -->
    <!--
    <selector xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto">
        <item
            app:state_auto="true"
            android:drawable="@drawable/camera_flash_auto" />
        <item
            app:state_on="true"
            android:drawable="@drawable/camera_flash_on" />
        <item
            app:state_off="true"
            android:drawable="@drawable/camera_flash_off" />
    </selector>
    -->

    <style name="anim_flash_selector_style">
        <item name="android:src">@drawable/anim_flash_selector</item>
    </style>

    <!--
    *****************************************************
    AD SCREEN
    *****************************************************
    -->

    <!--SELECTOR TEMPLATE FOR AD ICONS(ON HOTSPOT POPUP) ICON -->
    <!--<selector xmlns:app="http://schemas.android.com/apk/res-auto" xmlns:android="http://schemas.android.com/apk/res/android">
        <item app:adOptionInfo="true"
            android:drawable="@drawable/anim_ad_info" />
        <item app:adOptionPlay="true"
            android:drawable="@drawable/anim_ad_video" />
        <item app:adOptionAudio="true"
            android:drawable="@drawable/anim_ad_audio" />
        <item app:adOptionCall="true"
            android:drawable="@drawable/anim_ad_call" />
        <item app:adOptionGallery="true"
            android:drawable="@drawable/anim_ad_gallery" />
        <item app:adOptionBuy="true"
            android:drawable="@drawable/anim_ad_buy" />
    </selector>-->
    <style name="anim_ads_list_icon_style">
        <item name="android:background">@drawable/anim_ad_icons_selector</item>
    </style>



    <!--HOTSPOT OUTER CIRCLE ICON-->
    <style name="anim_hotspot_outer_circle_style">
        <item name="android:src">@drawable/splash_fourth</item>
    </style>
    <!--HOTSPOT INNER CIRCLE ICON-->
    <style name="anim_hotspot_inner_circle_style">
        <item name="android:src">@drawable/splash_second</item>
    </style>
</resources>

```
Note: It is not mandatory to update all styles 


### Contact

You can reach the Streamoid team at any time by emailing streamoid.support@streamoid.com.

