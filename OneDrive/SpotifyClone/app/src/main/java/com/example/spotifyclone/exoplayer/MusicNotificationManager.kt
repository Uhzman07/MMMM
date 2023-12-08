package com.example.spotifyclone.exoplayer

import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.MediaSessionCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.spotifyclone.R
import com.example.spotifyclone.other.Constants.NOTIFICATION_CHANNEL_ID
import com.example.spotifyclone.other.Constants.NOTIFICATION_ID
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import com.google.android.exoplayer2.ui.PlayerNotificationManager.NotificationListener

@Suppress("DEPRECATION")
class MusicNotificationManager(
    private val context: Context,
    sessionToken : MediaSessionCompat.Token,
    notificationListener: NotificationListener, // This is the function that will be called when the notification is created
    private val newSongCallBack: () -> Unit // This is a lambda function that does not take any parameters
) {
    // For the custom notification for exoplayer
    private val notificationManager : PlayerNotificationManager

    init {
        val mediaController = MediaControllerCompat(context,sessionToken)

        notificationManager = PlayerNotificationManager.Builder(
            context,
            NOTIFICATION_CHANNEL_ID,
            //R.string.notification_channel_name,
           // R.string.notification_channel_description,
            NOTIFICATION_ID.toString()

        )
            .setChannelNameResourceId(R.string.notification_channel_name)
            .setChannelDescriptionResourceId(R.string.notification_channel_description)
            .setMediaDescriptionAdapter(DescriptionAdapter(mediaController))
            .setNotificationListener(notificationListener)
            .build()

    }
    // Note that we are planning to call this function our service class
    fun showNotification(player: Player){ // The player is the exoplayer that we have
        notificationManager.setPlayer(player)
    }

    // Then to create the Description Adapter
    private inner class DescriptionAdapter(
        private val mediaController : MediaControllerCompat
    ) : PlayerNotificationManager.MediaDescriptionAdapter{
        // Note Ctrl + I for the functions
        override fun getCurrentContentTitle(player: Player): CharSequence {
            TODO("Not yet implemented")
            return mediaController.metadata.description.title.toString()
        }

        override fun createCurrentContentIntent(player: Player): PendingIntent? {
            TODO("Not yet implemented")
            return mediaController.sessionActivity
        }

        override fun getCurrentContentText(player: Player): CharSequence? {
            TODO("Not yet implemented")
            return mediaController.metadata.description.subtitle.toString()

        }
        override fun getCurrentLargeIcon(
            player: Player,
            callback: PlayerNotificationManager.BitmapCallback
        ): Bitmap? {
            Glide.with(context).asBitmap()
                .load(mediaController.metadata.description.iconUri)
                .into(object : CustomTarget<Bitmap>(){ // Then Ctrl + I
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        callback.onBitmap(resource) // This is activated when our image has finally loaded
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        TODO("Not yet implemented")

                    }
                })
            return null
        }

    }
}