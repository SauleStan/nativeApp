package eif.viko.lt.focustimer.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import eif.viko.lt.focustimer.Repositories.ItemRepository

class ItemViewModel : ViewModel() {
    fun getItems() = ItemRepository.getItems()
}