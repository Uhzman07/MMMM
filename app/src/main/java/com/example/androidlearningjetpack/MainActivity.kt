package com.example.androidlearningjetpack

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModel
import com.example.androidlearningjetpack.ui.theme.AndroidLearningJetpackTheme

class MainActivity : ComponentActivity() {

    /**
     * Interpolator
     * Interpolators in Android are used to define the rate of change of an animation.
     * They control the acceleration and deceleration of the animation, providing a specific timing behavior.

      The OvershootInterpolator is a type of interpolator that causes the animated values to overshoot the target value and then spring back into place.
      It adds a bouncing effect to the animation, making it go slightly beyond the target value before settling.
     */
    /*
     But for the XML for example, we can make use of the external file
     */

    private val viewModel by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Then to apply the Theme change
        // "apply" is used to configure
        // Then we might want to use a view model
        installSplashScreen().apply {
            // This is to control the appearance of the SplashScreen
            setKeepOnScreenCondition{
                !viewModel.isReady.value // The Splash Screen is expected to show as long as this value is true
            }



            // Then for the exit animation
            setOnExitAnimationListener{ screen ->
                // This is used to create a Screen Animation for the page
                // For the Scale X animation
                // The main aim of this is to take the initial dimension and then turn it to a dimension 0.0F that is to make it smaller for a given number of time
                val zoomX = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_X,
                    0.4f,
                    0.0f
                )
                zoomX.interpolator = OvershootInterpolator()
                zoomX.duration = 500L // This is the desired duration for the animation
                zoomX.doOnEnd {  // This is when the Screen is about to leave that is about to be cleared away
                    screen.remove()

                }

                // For the scale Y animation
                val zoomY = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_Y,
                    0.4f,
                    0.0f
                )
                zoomY.interpolator = OvershootInterpolator()
                zoomY.duration = 500L // This is the desired duration for the animation
                zoomY.doOnEnd {  // This is when the Screen is about to leave that is about to be cleared away
                    screen.remove()
                }

                // Then to start the animators
                zoomX.start()
                zoomY.start()


            }

        }


        setContent {
            AndroidLearningJetpackTheme{

                        // A surface container using the 'background' color from the theme
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            Greeting("Android")
                        }



            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidLearningJetpackTheme {
        Greeting("Android")
    }
}