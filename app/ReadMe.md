We have to add the splash screen dependency in the build.gradle file implementation 'androidx.core:core-splashscreen:1.0.1'
Note that I also had to add the Shape.kt file to the project and then I added some necessary dependency implementation in the build.gradle file
I changed from material3 to Material (Look in the Build.gradle file)
Then we create a new value resource file known as Splash.kt 
// And then we also have to changed the activity compat to App Compat so as to follow the tutorial learning
// IMPORTANT 
if we are trying to create a Splash Screen Theme for night mode, we go to values -> new -> Values Resource File -> Night mode -> Night // This will apply just for night mode
// And for different UI mode like watches, and all we will just have to go to UI MODE

// Then we go our manifest so as to change the theme of both the application and activity to the new Styles created in the splash.xml file
// Then we then proceed to work in the MainActivity class, here we can implement the right conditions for the SplashScreen

// Then to make the Image at the beginning fit well, we have to make use of the drawable vector file that is we have to make some changes to it
<group android:pivotX="12"
android:pivotY="12"
android:scaleX="0.5"
android:scaleY="0.5"/> I added this to make the placement perfect