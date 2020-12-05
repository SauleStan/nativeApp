package eif.viko.lt.focustimer.onboarding.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import eif.viko.lt.focustimer.R
import kotlinx.android.synthetic.main.fragment_screen1.*

class Screen1Fragment : Fragment(R.layout.fragment_screen1) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPagerOnboarding)
        button.setOnClickListener{
            viewPager?.currentItem = 1
        }
    }
}