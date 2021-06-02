package fr.lamobilery.android.jetpack.emoji

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.emoji.text.EmojiCompat
import androidx.emoji.widget.EmojiAppCompatEditText
import androidx.emoji.widget.EmojiAppCompatTextView

class EmojiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emoji)

        val text = "J’\uD83E\uDD70 Android | Tu es un vrai \uD83E\uDD77"
        updateTexts(text)


        findViewById<AppCompatTextView>(R.id.tv_platform_version).text =
            getString(R.string.platform_version, android.os.Build.VERSION.SDK_INT)

        findViewById<EmojiAppCompatEditText>(R.id.et_emoji).addTextChangedListener(object :
            TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Nothing
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Nothing
            }

            override fun afterTextChanged(p0: Editable?) {
                updateTexts(p0.toString())
            }

        })
    }

    private fun updateTexts(text: String) {
        findViewById<AppCompatTextView>(R.id.tv_classic).text = text
        findViewById<AppCompatTextView>(R.id.tv_process).text = EmojiCompat.get().process(text)
        findViewById<EmojiAppCompatTextView>(R.id.tv_emoji).text = text
    }

}