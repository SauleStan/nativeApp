package eif.viko.lt.focustimer.Fragments

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import eif.viko.lt.focustimer.Models.Item
import eif.viko.lt.focustimer.R
import eif.viko.lt.focustimer.ViewModels.TodoListViewModel
import kotlinx.android.synthetic.main.fragment_delete_dialog.view.*

class DeleteDialogFragment(itemList: TodoListViewModel, item: Item): DialogFragment() {

    val todoListViewModel = itemList
    val item = item

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.fragment_delete_dialog, container, false)
        var mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.woosh)
        rootView.cancel_button.setOnClickListener {
            dismiss()
        }

        rootView.delete_button.setOnClickListener {
            todoListViewModel.removeItemFromTodoList(item)
            dismiss()
            mediaPlayer.start()
        }


        return rootView
    }
}