package eif.viko.lt.focustimer.Repositories

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import eif.viko.lt.focustimer.Models.Item

object TodoListRepository {

    val db = FirebaseFirestore.getInstance()
    val user = FirebaseAuth.getInstance().currentUser

    fun addToTodoList(item: Item): Task<Void> {
        val document = db.
        collection("users").
        document(user!!.uid).
        collection("todo_list").
        document("${item.id}")

        return document.set(item)
    }

    fun getTodoList():CollectionReference{
        return db.collection("users/${user!!.uid}/todo_list")
    }

    fun removeItemFromTodoList(item: Item):Task<Void>{
        val document = db.
        collection("users/${user!!.uid}/todo_list").
        document("${item.id}")

        return document.delete()
    }

}