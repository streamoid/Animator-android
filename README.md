# Animator Android

[![N|Solid](http://www.streamoid.com/images/logo-white.png)](http://www.streamoid.com/)

This repository contains binary distributions of Animator-android framework.

If you have any questions, comments, or issues related to Animator, Please contact the team by emailing streamoid.support@streamoid.com.


### Animator

**Animator-android** framework provided by Streamoid Technologies, acts as a bridge between offline advertisements and purchasing those products online

**TestAnimator** is an example application which uses Animator-android framework

Imagine a scenario where you see some product advertisement in a newspaper ad or in a product catalogue, feel like knowing more about that product or buying it online. Animator iOS SDK solves the exact same problem.

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
        compile 'com.streamoid:animatorsdk:0.0.2'
    ...
}
```

Add following to manifest application


```
 <application
  ...
  tools:replace="android:theme"
  ...
  >
```

### Verifying Animator Configuration

Once you have finished adding Animator framework to your project, you can test your configuration by importing the dependencies and connecting a client to the Animator cloud. To do so, add following code to your Application class. (note that you must substitute the client name and client token placeholder text with your actual values, in order to get these values please contact us at streamoid.support@streamoid.com):

### Simple Intialization
```sh
        AnimatorClient.initialize(getBaseContext(),
                CLIENT_NAME, CLIENT_TOKEN, new RequestCallback() {
                    @Override
                    public void onSuccess(RequestItem requestItem) {
                        // Success Block
                    }

                    @Override
                    public void onError(RequestItem requestItem) {
                        // Failure Block
                    }
                });

```

Launch your application and verify that the connection is successful. You are now ready to unlock the features of Animator.


### To Start Image Recognition by Animator

Below method can be called only after initialization of `AnimatorClient`

```
 AnimatorClient.openCamera(MainActivity.this, new RequestCallback() {
                    @Override
                    public void onSuccess(RequestItem requestItem) {
                        // Success Block
                    }

                    @Override
                    public void onError(RequestItem requestItem) {
                       // Failure Block
                    }
                });
```


### Note

Following Permissions are mandatory:

```sh
Manifest.permission.CAMERA // To use Device Camera
Manifest.permission.INTERNET // To connect Webservice and fetch matches
```
### Contact

You can reach the Streamoid team at any time by emailing streamoid.support@streamoid.com.

