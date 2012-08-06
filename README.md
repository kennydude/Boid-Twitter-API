### Android Notes:

We use Ant to build for Android, as we remove libraries that are not required and are built into Android itself. However, people tend to install the SDK in different locations, so before you do anything, you will need a file called local.properties with the following contents:

	sdk-folder=/opt/android/

(Obviously changing the value to where you store the Android SDK. You will also need to install Android API Level 8 from the `android` SDK package manager)

Then to build Boid's Twitter Library for Android, you can run

	ant android

And it if you get `Build Successful` then that's awesome, and you should have a ~200kb file in the `bin` directory called `twitter-android.jar`

If you want to be even more lazy, you can make an shell script:

	ant android
	mv bin/twitter-android.jar ../amazing-new-app/libs/twitter-android.jar
	notify-send "Boid Twitter Lib" "Built Succesfully! Refresh Eclipse"

(Ubuntu only with the libnotify utils installed)
