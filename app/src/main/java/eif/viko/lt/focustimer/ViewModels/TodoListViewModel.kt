package eif.viko.lt.focustimer.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import eif.viko.lt.focustimer.Models.Item
import eif.viko.lt.focustimer.Repositories.TodoListRepository
import kotlin.math.log

const val TAG = "TODOLISTVIEWMODEL"

class TodoListViewModel: ViewModel() {

    private val repository = TodoListRepository
    private val todoList: MutableLiveData<List<Item>> = MutableLiveData()

    fun addToTodoList(item: Item){
        repository.addToTodoList(item).addOnSuccessListener {
            Log.w(TAG, "ITEM ADDED!")
        }.addOnFailureListener{
            Log.w(TAG, "SOME PROBLEMS: $it")
        }
    }

    fun getTodoList(): LiveData<List<Item>> {
        repository.getTodoList().addSnapshotListener{ value, error ->
            val tempList: MutableList<Item> = mutableListOf()
            value!!.forEach { document ->
                val item = document.toObject(Item::class.java)
                tempList.add(item)
            }
            todoList.value = tempList
        }

        return todoList
    }

    fun removeItemFromTodoList(item: Item){
        repository.removeItemFromTodoList(item).addOnFailureListener{
            Log.w(TAG, "SOME PROBLEMS: $it")
        }
    }
}