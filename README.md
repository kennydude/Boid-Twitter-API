Boid Twitter API
================

This library is still a work in progress, when it's at production level it'll be implemented in Boid for Twitter (http://www.boidapp.com). Feel free to use this library in your own applications when it reaches a completely usable point. This library was influenced by Twitter4J, but was built from the ground up by me. It's meant to be easier to navigate and more lightweight.

Dependencies
----------------
I've included OAuth Signpost and Apache Commons Codec (both are libraries whose JAR files would usually need to be referenced in your project) in the code of the project, so the only JAR dependency you'll need is the Apache HTTP library for Java which can be obtained here: http://hc.apache.org/downloads.cgi. If you're using this library in an Android app, you won't need to download that because Android includes Apache HTTP in the SDK.

Adding the Library to Your Projects
------------------
The best way to add Boid-Twitter-API to your application is by exporting the project to a JAR and adding it as a library reference in your project.

You could also copy all of the library's source code into your project, but that's a little inefficient, especially for when the library gets updated. JARs also keep the size of your project down due to the fact the JARs are compressed packages.

First Time Authentication
------------------

```java
Authorizer auth = Authorizer.create(
    "5LvP1d0cOmkQleJlbKICtg", //Consumer key
    "j44kDQMIDuZZEvvCHy046HSurt8avLuGeip2QnOpHKI",  //Consumer secret
    "myapp://callback");  //Callback URL
String url = auth.getAuthorizeUrl();
/**
 * This URL should now be opened in the web browser, it will show Twitter's authentication page
 * where the user logs in and clicks "Authorize." The web browser will then redirect to your
 * callback URL. Your app should intercept this callback.
 *
 * An example of the callback: myapp://callback?oauth_token=[oauth_token]&oauth_verifier=[oauth_verifier]
 */

// 'oauth_verifier' should be replaced with the 'oauth_verifier' query parameter sent through
// the callback from the web browser. You now are logged in.
Twitter twitter = auth.getAuthorizedInstance("oauth_verifier");

//These values should be saved to local storage, on Android they can be saved using SharedPreferences.
//These values can be used later to login without going to the authorization page again.
String accessToken = twitter.getAccessToken();
String accessSecret = twitter.getAccessSecret();
```

Logging in with the Access Token and Secret
-----------------

Once you've saved the access token and secret strings shown in the end of the code block above, you can login later without going to the authentication page again (like the commented text says above).

```java
Authorizer auth = Authorizer.create(
    "5LvP1d0cOmkQleJlbKICtg", //Consumer key
    "j44kDQMIDuZZEvvCHy046HSurt8avLuGeip2QnOpHKI",  //Consumer secret
    "myapp://callback");  //Callback URL
Twitter twitter = auth.getAuthorizedInstance(
    accessToken,  //Insert your access token
    accessSecret);  //Insert your access secret
```

You are now authenticated again for the account you added in the first section.

Receiving Callbacks on Android
---------------------
If you're wondering how to receive callbacks from the web browser (see the first section, "First Time Authentication") in your Android app, here's a quick tutorial.

You need to define an intent filter for the activity that will receive the callback; in your AndroidManifest.xml, insert an intent filter inside of the <activity /> element representing the receiving activity:

```xml
<intent-filter>
    <action android:name="android.intent.action.VIEW" />
    <category android:name="android.intent.category.DEFAULT" />
    <category android:name="android.intent.category.BROWSABLE" />
    <data android:scheme="myapp" android:host="callback" />
</intent-filter>
```
This intent filter will receive a the URL myapp://callback which is what was passed in Authorizer.create(), you can define whatever callback URL you want. If you already have another intent-filter inside of the activity, don't merge the intent filters, just add another one next to it (it won't work if you combine two intent filters).

When the activity receives the callback, it can go to multiple places. If the activity wasn't previously running, it will be passed to onCreate(Bundle savedInstanceState), otherwise it will be passed to onNewIntent(Intent intent).

```java
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    processCallback(getIntent());
}

@Override
public void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    processCallback(intent);
}

public void processCallback(Intent callback) {
    if(callback.getData() != null) {
        /**
        * If the callback URL was myapp://callback?oauth_token=[oauth_token]&oauth_verifier=[oauth_verifier]
        * Then this function below will set the string to the value [oauth_verifier].
        *
        * callback.getData() returns the entire URL, getQueryParameter() extracts the oauth_verifier parameter.
        */
        String oauth_verifier = callback.getData().getQueryParameter("oauth_verifier");
        Twitter twitter = auth.getAuthorizedInstance(oauth_verifier);
    }
}
```

Retrieving the home timeline
------------------
Now that you're logged in, you can start requesting resources from the Twitter API. For an example, here's how you would retrieve your home timeline (list of Tweets from all the people you follow).

```java
//Retrieve page 1, get 50 tweets.
Status[] homeTimeline = twitter.getHomeTimeline(new Paging(50, 1));
for(Status status : homeTimeline) {
    User user = status.getUser();
    System.out.println("@" + user.getScreenName() + " -- " + status.getText());
}
```

This will retieve the home timeline, and print out each Tweet to the console.

Getting the current user
---------------------
Chances are, you'll wanna get information about the user after they log in.

```java
User user = twitter.verifyCredentials();
System.out.println("Successfully authenticated " + user.getName() + " (@" + user.getScreenName() + ")!");
```
Updating your Status
----------------------
One of the main functions of Twitter is tweeting, this is done by updating your status. There is two ways of doing this, one is very simple, and one is very detailed.

```java
twitter.updateStatus("I'm tweeting using Boid for Android's Twitter API library!");
```
As you can see, it only has one parameter and that parameter is a string, so it's very quick and easy. There's a more detailed version of this however.

```java
StatusUpdate update = StatusUpdate.create(
    "I'm tweeting using Boid for Android's Twitter API library!",  //Text
    226787752644079617l,  //In-reply-to status ID
    new GeoLocation(123424.0, 676424.0),  //Location
    true,   //Display exact coordinates
    null)  //Twitter place ID
twitter.updateStatus(update);
```
This more detailed version includes the text of the Tweet, the optional in_reply_to_status_id (the ID of another tweet that this tweet is in reply to), the current location of the user (e.g. from GPS on a phone), whether or not to display exact coordinates when location is attached (as opposed to the name of the place), and the Twitter place ID that corresponds with the current location of the user.

Attaching Media to Tweets
------------------------
Attaching a picture to a status update only requires one simple additional step to the section above. 

```java
//Replace 'path_to_file' with the full path to an image file on the current device's local storage.
update.addMedia(new File("path_to_file"));
```
This method returns a StatusUpdate object too, so you can use it like this if you want:
```java
StatusUpdate update = StatusUpdate.create(
    "I'm tweeting using Boid for Android's Twitter API library!")
    .addMedia(new File("path_to_file"));
```
The 'addMedia(File file)' function returns a StatusUpdate object so you can call the function like a chain. Now you just follow the section above to post the StatusUpdate to Twitter.

Searching for Tweets
-----------------------
Using location while searching isn't necessary, but it's kinda cool to use so it's included in this example:

```java
GeoCode location = new GeoCode(
    new GeoLocation(44.9541667, -93.1138889),  //Tweets near St. Paul, MN in the United States
    5, //Within 5 miles of St. Paul
    GeoCode.DistanceUnit.MI);  //Indicates 5 miles instead of 5 kilometers
    
SearchResult results = twitter.search(new SearchQuery(
    "#Boid",  //Searches for Tweets using the #Boid hashtag
    new Paging(25, 1),  //Gets page 1 of 25 Tweets
    location,  //With location parameters set above
    SearchQuery.ResultType.POPULAR,  //Gets popular Tweets
    null));  //The 'until' parameter, we're not gonna use it here

System.out.println("Completed in: " + results.getCompletedIn() + "\n");
for(Tweet tweet : results.getResults()) {
    System.out.println("@" + tweet.getFromUser() + ": " + tweet.getText());
}
```

Continuing On
----------------
Other methods in the library will be pretty easy to figure out once you understand all of the above in this read me. A good place to start is the Twitter interface, it contains all basic functions for using Twitter.