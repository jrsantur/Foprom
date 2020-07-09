package pe.juabsa.cipca.foprom

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val img = findViewById<ImageView>(R.id.img)
        img.animate().alpha(4000F).setDuration(0)
        val handler = Handler()
        handler.postDelayed(Runnable {
            val dsp = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(dsp)
            finish()
        }, 4000)
    }

}
