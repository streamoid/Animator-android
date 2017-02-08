# Animator Android

[![N|Solid](http://www.streamoid.com/images/logo-white.png)](http://www.streamoid.com/)

This repository contains binary distributions of Animator-android framework.

If you have any questions, comments, or issues related to Animator, Please contact the team by emailing streamoid.support@streamoid.com.


### Animator

**Animator-android** framework provided by Streamoid Technologies, acts as a bridge between offline advertisements and purchasing those products online.

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
        compile 'com.streamoid:animatorsdk:0.0.8'
    ...
}
```

Add following to manifest application


```
 <application
  ...
  tools:replace="android:theme, icon"
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

To update icons in the Camera screen, use the following:

`Step 1: `

Add the below code to your styles and update the drawables as per your requirement. Do not modify the names in the style tag.

```
<style name="anim_shutter_style">
    <item name="android:src">@drawable/drawable1</item>
</style>
<style name="anim_gallery_style">
    <item name="android:src">@drawable/drawable2</item>
</style>
```

`Step 2:`

Create a selector drawable `flash_selector.xml` as shown below  and update only the drawables as per your requirement.

```
<selector xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <item
        app:state_auto="true"
        android:drawable="@drawable/flash_auto" />
    <item
        app:state_on="true"
        android:drawable="@drawable/flash_on" />
    <item
        app:state_off="true"
        android:drawable="@drawable/flash_off" />
</selector>
```

Add the below code to your styles. Do not modify the `name` in the style tag.

```
<style name="anim_flash_selector_style">
    <item name="android:background">@drawable/flash_selector</item>
</style>
```
    
    
To update icons in the Ads screen, use the follwing:

`Step 1: `

Create a selector drawable `ad_icons_selector.xml` as shown below and update only the drawables as per your requirement.

```
<selector xmlns:app="http://schemas.android.com/apk/res-auto" xmlns:android="http://schemas.android.com/apk/res/android">
    <item app:adOptionInfo="true"
        android:drawable="@drawable/option1" />
    <item app:adOptionPlay="true"
        android:drawable="@drawable/option2" />
    <item app:adOptionAudio="true"
        android:drawable="@drawable/option3" />
    <item app:adOptionCall="true"
        android:drawable="@drawable/option4" />
    <item app:adOptionGallery="true"
        android:drawable="@drawable/option5" />
    <item app:adOptionBuy="true"
        android:drawable="@drawable/option6" />
</selector>
```

Add the below code to your styles. Do not modify the `name` in the style tag.

```
<style name="anim_ads_list_icon_style">
    <item name="android:background">@drawable/ad_icons_selector</item>
</style>
```
    
### Contact

You can reach the Streamoid team at any time by emailing streamoid.support@streamoid.com.

