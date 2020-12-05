package eif.viko.lt.focustimer.onboarding.screens

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import eif.viko.lt.focustimer.R

class SplashScreenFragment : Fragment(R.layout.fragment_splash_screen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed({
            if(onBoarding()){
                findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
            }else{
                findNavController().navigate(R.id.action_splashScreenFragment_to_viewPagerFragment)
            }
        }, 2000)
    }

    private fun onBoarding():Boolean {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished3", false)
    }
}