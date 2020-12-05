package eif.viko.lt.focustimer.onboarding

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import eif.viko.lt.focustimer.R
import eif.viko.lt.focustimer.onboarding.screens.Screen1Fragment
import eif.viko.lt.focustimer.onboarding.screens.Screen2Fragment
import kotlinx.android.synthetic.main.fragment_view_pager.view.*


class ViewPagerFragment : Fragment(R.layout.fragment_view_pager) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentList = arrayListOf(
            Screen1Fragment(),
            Screen2Fragment()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        view.viewPagerOnboarding.adapter = adapter
    }
}