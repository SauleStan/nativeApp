package eif.viko.lt.focustimer.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import eif.viko.lt.focustimer.R
import eif.viko.lt.focustimer.ViewModels.QuoteViewModel
import eif.viko.lt.focustimer.adapters.QuotesListAdapter
import kotlinx.android.synthetic.main.fragment_timer.*


class TimerFragment : Fragment(R.layout.fragment_timer), QuotesListAdapter.Interaction {

    private lateinit var quoteViewModel: QuoteViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quoteViewModel = ViewModelProvider(this).get(QuoteViewModel::class.java)
        val quotesListAdapter: QuotesListAdapter by lazy { QuotesListAdapter(this) }

        quoteViewModel.list.toString()

        quoteViewModel.getQuoteList().observe(viewLifecycleOwner, {
            quotesListAdapter.swapData(it)
        })

        quote_recycleView.apply{
            // Set a LinearLayoutManager to handle Android
            // RecycleView behaviour
            layoutManager = LinearLayoutManager(context)
            // Set the custom adapter to the RecycleView
            adapter = quotesListAdapter
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timer, container, false)
    }


}