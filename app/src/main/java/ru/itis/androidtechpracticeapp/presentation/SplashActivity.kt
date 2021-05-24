package ru.itis.androidtechpracticeapp.presentation

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.utils.Consts
import ru.itis.androidtechpracticeapp.utils.Key
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        sp = getSharedPreferences(Consts.SP_NAME, MODE_PRIVATE)

        if (!sp.contains(Key.TOKEN)) {
            startActivity(
                Intent(this, AuthActivity::class.java)
            )
            finish()
        } else {
            startActivity(
                Intent(this, MainActivity::class.java)
            )
            finish()
        }

    }
}