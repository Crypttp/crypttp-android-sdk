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

## Usage

SDK methods are called through the Crypttp class.

Example of deeplink parsing method

```Java
Crypttp.parseCrypttpDeepLink(intent) { params: CrypttpTransactions? ->
   if (params != null) {
       //TODO handle params
   }
}
```

#### returned value
```Kotlin
data class CrypttpTransactions(
    val transactionId: String,
    val transactions: List<CrypttpTransactionInfo>
)

data class CrypttpTransactionInfo(
    val coin: String,
    val amount: String,
    val to: String,
    val payload: String,
    val memo: String,
    val onSuccessUrl: String,
    val onFailureUrl: String
)
```

## IMPORTANT

In your successfull transaction handler add the following code:

```Java
Crypttp.sendTransactionHashAsync(
   "transactionId",
   "transactionHash"
) {
   if (it is Response.Success) {

   }
}
```

This method help us track paid transactions and provide statistics to our merchants. 

Which in turn helps attract new merchants.

## Get more avareness for your wallet app

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
