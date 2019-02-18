package by.whiskarek.yandexlauncher.activity.profile

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NavUtils
import by.whiskarek.yandexlauncher.R
import by.whiskarek.yandexlauncher.activity.BaseActivity

class ProfileActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun launchEmail(view: View) {
        val email = findViewById<TextView>(R.id.text_email).text
        val makeCallIntent = Intent(Intent.ACTION_SENDTO)
        makeCallIntent.data = Uri.parse("mailto:$email")
        startActivity(makeCallIntent)
    }

    fun launchPhone(view: View) {
        val phone = findViewById<TextView>(R.id.text_phone).text
        val makeCallIntent = Intent(Intent.ACTION_DIAL)
        makeCallIntent.data = Uri.parse("tel:$phone")
        startActivity(makeCallIntent)
    }

    fun launchVk(view: View) {
        val nick = findViewById<TextView>(R.id.text_vk).text
        val openVkIntent = Intent(Intent.ACTION_VIEW)
        openVkIntent.data = Uri.parse("https://vk.com/$nick")
        startActivity(openVkIntent)
    }

    fun launchGitHub(view: View) {
        val nick = findViewById<TextView>(R.id.text_github).text
        val openGitHubIntent = Intent(Intent.ACTION_VIEW)
        openGitHubIntent.data = Uri.parse("https://github.com/$nick")
        startActivity(openGitHubIntent)
    }

    fun launchLinkedin(view: View) {
        val nick = findViewById<TextView>(R.id.text_linkedin).text
        val openLinkedinIntent = Intent(Intent.ACTION_VIEW)
        openLinkedinIntent.data = Uri.parse("https://www.linkedin.com/in/$nick")
        startActivity(openLinkedinIntent)
    }
}
