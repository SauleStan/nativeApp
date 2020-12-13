package eif.viko.lt.focustimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.emoji.bundled.BundledEmojiCompatConfig
import androidx.emoji.text.EmojiCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val config = BundledEmojiCompatConfig(this)
        EmojiCompat.init(config)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottom_nav)
            .setupWithNavController(navController)

    }
}