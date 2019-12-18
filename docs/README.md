# Crypttp-android

## Environment
```
Java 1.6+
min SDK - 16, target 29
```

## Installation
### 1. In the end of root build.gradle add the following code
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

### 2. In the app/build.gradle file add dependency
```
implementation 'com.github.Crypttp:crypttp-android-sdk:v0.0.1-alpha01'
implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.61"
```

The latest version you can find at project [GitHub](https://github.com/Crypttp/crypttp-android-sdk)

### 3. Register deeplink schema in AndroidManifest.xml

Add the following code between `<activity>` tags

```XML
<intent-filter>
   <action android:name="android.intent.action.VIEW" />
   <category android:name="android.intent.category.DEFAULT" />
   <category android:name="android.intent.category.BROWSABLE" />
   <data
        android:host="crypttp.com"
        android:pathPrefix="/crypttp"
        android:scheme="crypttp" />
    <data
        android:host="crypttp.com"
        android:pathPrefix="/crypttp"
        android:scheme="http" />
    <data
        android:host="crypttp.com"
        android:pathPrefix="/crypttp"
        android:scheme="https" />
</intent-filter>
```

Or if you already have `<intent-filter>` that handle your personal App deeplink add only new handlers

```XML
<data
    android:host="crypttp.com"
    android:pathPrefix="/crypttp"
    android:scheme="crypttp" />
<data
    android:host="crypttp.com"
    android:pathPrefix="/crypttp"
    android:scheme="http" />
<data
    android:host="crypttp.com"
    android:pathPrefix="/crypttp"
    android:scheme="https" />
```

### 4. Get more avareness for your wallet app

Signup at [Dashboard](https://crypttp.com/dashboard)

Navigate to Settings/Wallet App

Set:
* Name
* Discription
* Icon
* Available currencies
* Urls to AppStore and Google Play

This configuration will help you promote your wallet app. 

Every user that has no wallet installed while paying at Crypttp merchants will be redirected to a special page where user can find featured wallets