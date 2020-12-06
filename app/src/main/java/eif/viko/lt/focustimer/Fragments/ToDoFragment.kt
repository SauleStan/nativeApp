package eif.viko.lt.focustimer.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import eif.viko.lt.focustimer.Adapters.ItemListAdapter
import eif.viko.lt.focustimer.Models.Item
import eif.viko.lt.focustimer.R
import eif.viko.lt.focustimer.ViewModels.ItemViewModel
import eif.viko.lt.focustimer.ViewModels.TodoListViewModel
import kotlinx.android.synthetic.main.fragment_todo.*
import kotlinx.android.synthetic.main.list_item_layout.*
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ToDoFragment : Fragment(), ItemListAdapter.Interaction {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var itemViewModel: ItemViewModel
    private lateinit var todoListViewModel: TodoListViewModel
    private val user = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        todoListViewModel = ViewModelProvider(this).get(TodoListViewModel::class.java)


        // Add new to do
        val button: Button? = activity?.findViewById(R.id.add_button)
        val titleText: EditText? = activity?.findViewById(R.id.edit_text_title)
        val descrText: EditText? = activity?.findViewById(R.id.edit_text_descr)
        button?.setOnClickListener {
            Toast.makeText(context, "Add Button clicked", Toast.LENGTH_LONG).show()
            val title = titleText?.text.toString()
            val description = descrText?.text.toString()
            todoListViewModel.addToTodoList(Item(Random().nextLong(), title, description))
            titleText?.text?.clear()
            descrText?.text?.clear()
        }

        val itemListAdapter: ItemListAdapter by lazy { ItemListAdapter(this)}

//        todoListViewModel.addToTodoList(Item(Random().nextLong() ,"Thingie to do", "Yous gots to dos what yous gots to dos", "Sometime"))
//        itemListAdapter.submitList(itemViewModel.getItems().value)
        if(user != null) {
            todoListViewModel.getTodoList().observe(viewLifecycleOwner, Observer {
                itemListAdapter.submitList(it)
            })
        }

        item_recycleView.apply{
            // Set a LinearLayoutManager to handle Android
            // RecycleView behaviour
            layoutManager = LinearLayoutManager(context)
            // Set the custom adapter to the RecycleView
            adapter = itemListAdapter
        }


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ToDoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ToDoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun click_item(item: Item) {
        todoListViewModel.removeItemFromTodoList(item)
    }
}