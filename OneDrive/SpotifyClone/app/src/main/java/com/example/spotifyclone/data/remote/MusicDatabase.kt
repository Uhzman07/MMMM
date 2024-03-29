package com.example.spotifyclone.data.remote

import com.example.spotifyclone.data.entities.Song
import com.example.spotifyclone.other.Constants.SONG_COLLECTION
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class MusicDatabase{

    private val firestore = FirebaseFirestore.getInstance()
    private val songCollection = firestore.collection(SONG_COLLECTION)

    // Since we do not want to execute this on our main thread
    suspend fun getAllSongs() : List<Song>{
        return try {
            // Note that ".get()" returns all the contents of the collection
            // Note that we are also passing the song to the object
            songCollection.get().await().toObjects(Song::class.java)

        } catch (e : Exception){
            emptyList()
        }

    }


}