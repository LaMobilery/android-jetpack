package fr.lamobilery.android.jetpack.emoji

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.provider.FontRequest
import androidx.emoji.bundled.BundledEmojiCompatConfig
import androidx.emoji.text.EmojiCompat

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fontRequest = FontRequest(
            "com.google.android.gms.fonts",
            "com.google.android.gms",
            "Noto Color Emoji Compat",
            R.array.com_google_android_gms_fonts_certs
        )

        val fontRequestConfig =
        // Switch here if you want to use FontRequest or BundledEmoji
            // FontRequestEmojiCompatConfig(applicationContext, fontRequest)
            BundledEmojiCompatConfig(this)
                .registerInitCallback(object : EmojiCompat.InitCallback() {
                    override fun onInitialized() {
                        super.onInitialized()
                        goToEmojiScreen(true)
                    }

                    override fun onFailed(throwable: Throwable?) {
                        super.onFailed(throwable)
                        goToEmojiScreen(false)
                    }
                })

        EmojiCompat.init(
            fontRequestConfig
                .setReplaceAll(true)
                .setUseEmojiAsDefaultStyle(true)
        )
    }

    /**
     * Start the emoji screen and finishes the Splash
     */
    private fun goToEmojiScreen(hasEmojiLoaded: Boolean) {
        Toast
            .makeText(
                baseContext,
                if (hasEmojiLoaded) "With emoji !" else "Without emoji :(",
                Toast.LENGTH_SHORT
            )
            .show()
        startActivity(Intent(baseContext, EmojiActivity::class.java))
        finish()
    }
}