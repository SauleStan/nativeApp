package eif.viko.lt.focustimer.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import eif.viko.lt.focustimer.R
import kotlinx.android.synthetic.main.fragment_empty_dialog.view.*

class EmptyDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.fragment_empty_dialog, container, false)

        rootView.ok_button.setOnClickListener {
            dismiss()
        }

        return rootView
    }
}