package eif.viko.lt.focustimer.ViewModels

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import eif.viko.lt.focustimer.Models.Quote
import eif.viko.lt.focustimer.Repositories.QuoteRepository
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class QuoteViewModel : ViewModel() {
    val list: MutableLiveData<List<Quote>> = MutableLiveData()
    private val repository = QuoteRepository

    fun getQuoteList(): LiveData<List<Quote>> {
        repository.api.getQuoteList().enqueue(object : Callback, retrofit2.Callback<List<Quote>> {
            override fun onResponse(call: Call<List<Quote>>, response: Response<List<Quote>>) {
                if (response.isSuccessful) {
                    list.postValue(response.body())
                } else {
                    list.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<Quote>>, t: Throwable) {
                list.postValue(null)
            }
        })
        return list
    }
}