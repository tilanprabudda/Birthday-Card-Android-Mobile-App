package com.example.happybirthday

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity(), MediaPlayer.OnCompletionListener {

    private lateinit var birthdayMessageTextView: TextView
    private lateinit var wishButton: Button
    private lateinit var specialButton: Button
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        birthdayMessageTextView = findViewById(R.id.birthdayMessageTextView)
        wishButton = findViewById(R.id.wishButton)

        specialButton = findViewById(R.id.specialButton)

        // Initialize MediaPlayer with the music track
        mediaPlayer = MediaPlayer.create(this, R.raw.audio)

        // Set OnCompletionListener to know when the music finishes playing
        mediaPlayer.setOnCompletionListener(this)

        wishButton.setOnClickListener {
            val birthdayMessage = generateBirthdayMessage()
            birthdayMessageTextView.text = birthdayMessage

            // Start playing the music track
            mediaPlayer.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Release the MediaPlayer object when the activity is destroyed
        mediaPlayer.release()
    }

    override fun onCompletion(mp: MediaPlayer?) {
        // Music track has finished playing, redirect to another activity
        specialButton.visibility = View.VISIBLE

        specialButton.setOnClickListener {
            // Redirect to another activity when the special button is clicked
            val intent = Intent(this, page::class.java)
            startActivity(intent)
        }
    }

    private fun generateBirthdayMessage(): String {
        val messages = arrayOf(
            "උපන් දින යනු නව ආරම්භයක්, නැවුම් ආරම්භයක් සහ නව අරමුණු සමඟ නව උත්සාහයන් ලුහුබැඳීමට කාලයකි. විශ්වාසයෙන් හා ධෛර්යයෙන් ඉදිරියට යන්න. අද සහ ඔබගේ සියලු දින විශ්මය ජනක වේවා!"        )
        val randomIndex = Random().nextInt(messages.size)
        return messages[randomIndex]
    }
}
