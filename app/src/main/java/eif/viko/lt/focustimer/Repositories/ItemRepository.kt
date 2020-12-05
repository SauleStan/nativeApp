package eif.viko.lt.focustimer.Repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import eif.viko.lt.focustimer.Models.Item

object ItemRepository {
    val db = mutableListOf<Item>();
    val mutableList = MutableLiveData<List<Item>>()

    fun getItems(): LiveData<List<Item>>{
        // No need to clear when using webserver
        db.clear()
        loadFromAnySource()
        mutableList.value = db

        return mutableList
    }

    fun loadFromAnySource(){
        db.add(Item("Walk doggie", "Give doggie some exercise", "Today"))
        db.add(Item("Wash Dishes", "Sink is FILLED >:(", "Today"))
        db.add(Item("Homework", "read ch.1-4", "Monday"))

    }
}