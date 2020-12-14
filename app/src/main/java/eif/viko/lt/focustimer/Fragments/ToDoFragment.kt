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
import java.util.*

class ToDoFragment : Fragment(), ItemListAdapter.Interaction {

    private lateinit var itemViewModel: ItemViewModel
    private lateinit var todoListViewModel: TodoListViewModel
    private val user = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        todoListViewModel = ViewModelProvider(this).get(TodoListViewModel::class.java)

        // Add new to do
        val buttonDelete: Button? = activity?.findViewById(R.id.add_button)
        val titleText: EditText? = activity?.findViewById(R.id.edit_text_title)
        val descrText: EditText? = activity?.findViewById(R.id.edit_text_descr)

        buttonDelete?.setOnClickListener {
            Toast.makeText(context, "Add Button clicked", Toast.LENGTH_LONG).show()
            val title = titleText?.text.toString()
            val description = descrText?.text.toString()

            if (title.isBlank() && description.isBlank() || title.isBlank() || description.isBlank()){
                var emptyFieldsDialog = EmptyDialogFragment()
                emptyFieldsDialog.show(activity?.supportFragmentManager!!, "emptyFields")
            } else {
                todoListViewModel.addToTodoList(Item(Random().nextLong(), title, description))
                titleText?.text?.clear()
                descrText?.text?.clear()
            }
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

    override fun click_item(item: Item) {
        var customDialog = DeleteDialogFragment(todoListViewModel, item)
        customDialog.show(activity?.supportFragmentManager!!, "customDialog")

    }
}