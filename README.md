# Vereafy Android Library

- Introduction
- Library Usage
- Initialization
- Completion
- Resend
- Get Balance
- Error Responses
----------

## Introduction:

Vereafy is an SMS based 2-factor authentication services that uses machine learning to understand the causes of OTP delivery failures and resolves them instantly to ensure your login and sign up OTPs deliver.

The Vereafy Android Library Project was created to enable android developers integrate seamlessly with the Vereafy API.

## Library Usage

To use the Vereafy Android library just follow the steps:
1. Download the Vereafy android .aar file.
2. Add the Vereafy android library to your project as follows:
   - Click File > New > New Module.
   - Click Import .JAR/.AAR Package then click Next.
   - Enter the location of the Vereafy android .aar file you downloaded then click Finish.
3. Make sure the library is listed at the top of your settings.gradle file, as shown here for "vereafyandroidlibrary-release":

          include ':app', ':vereafyandroidlibrary-release'
          
4. Open the app module's build.gradle file and add a new line to the dependencies block as shown in the following snippet:

          dependencies {
               implementation project(":vereafyandroidlibrary-release")
          }

5. Click Sync Project with Gradle Files.

## Initialization

 The Vereafy 2fa initialization can be as simple as the following lines of code:
 android - java

         Vereafy vereafy = new Vereafy("your_APIKEY");
         vereafy.initialization("234802*******");
         
 android - kotlin
 
         val vereafy = Vereafy("your_APIKEY")
         vereafy.initialization("234802*******")

The initialization method returns a response that should look like this:

             {
                "status":"success",
                 "pinRef": 1293488527
             }

## Completion

 The Vereafy 2fa completion can be as simple as the following lines of code:

android - java

         Vereafy vereafy = new Vereafy("your_APIKEY");
         vereafy.completion("pinRef","verification_code");
         
android  - kotlin
 
         val vereafy = Vereafy("your_APIKEY")
         vereafy.completion("pinRef","verification_code")

The completion method returns a response that should look like this if the parameters are correct:

             {
                "response":"success"
             }

## Resend

In a case where your app users get impatient and hits the retry link on your app form, just call the resend method this way:
 
 android - java
 
         Vereafy vereafy = new Vereafy("your_APIKEY");
         vereafy.resend("234802*******","pinRef");

android - kotlin

          val vereafy = Vereafy("your_APIKEY")
          vereafy.resend("234802*******","pinRef")
          
The resend method returns a response that should look like this:

             {
                 "status": "success",
                 "pinRef": 1293488527
             }

## Get Balance

To get your balance on Vereafy, the getbalance method is used this way:

android - java
            
            Vereafy vereafy = new Vereafy("your_APIKEY");
            vereafy.getBalance();
            
android - kotlin

            val vereafy = Vereafy("your_APIKEY")
            vereafy.balance
          
The method requires no parameter, and the returned response should look like this:

            {
                 "balance":355
            }

## Error Responses

In a case where the request fails due to one reason or another you should get an error response from the requested endpoint that looks like this:

            {
                "error":"Invalid PIN Ref",
                "code":"CE2000"
            }
            
The table below shows a list of error codes and their descriptions

|  Error Code                   |   Description        |    
|-------------------------------|----------------------|
| CE1001  | Missing Fields            |
| CE1002  | Empty Fields               | 
| CE1006  | Not a Nigerian Number               | 
| CE2000  | Invalid PIN Ref| 
| CE2002  | PIN does not reference any verification request| 
| CE2003  | Mobile number does not match original request| 
| CE2001  | Invalid PIN| 
| CE2004  | Request Not Found               | 
| CE7000  | Verification already succeeded     | 
| CE7001  | Verification already failed      | 
| CE6000  | Insufficient Balance     | 
| CE5000  | Invalid Template ID             | 
| CE5001  | Could not find referenced template                | 
