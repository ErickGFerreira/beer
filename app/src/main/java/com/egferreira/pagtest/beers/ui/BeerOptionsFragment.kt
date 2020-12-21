package com.egferreira.pagtest.beers.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.egferreira.pagtest.R
import com.egferreira.pagtest.beers.adapter.BeerAdapter
import com.egferreira.pagtest.beers.response.BeerResponse
import com.egferreira.pagtest.beers.viewmodel.BeerViewModel
import kotlinx.android.synthetic.main.fragment_beer_options.*
import kotlinx.android.synthetic.main.toolbar_layout.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class BeerOptionsFragment : Fragment() {

    private val viewModel: BeerViewModel by viewModel()
    private val beerClickListener: (BeerResponse) -> Unit = { beerInfo ->
        goToBeerInfoScreen(beerInfo)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_beer_options, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setUpRecyclerView()
    }

    private fun setUpObservers() {
        viewModel.beersLiveData.observe(viewLifecycleOwner, Observer { beerList ->
            beerOptionsRecyclerView.adapter = BeerAdapter(beerList, beerClickListener)
        })

        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer { })

        viewModel.loadingLiveData.observe(viewLifecycleOwner, Observer { onLoading ->
            beerOptionsProgressBar.isVisible = onLoading
        })
    }

    private fun setupToolbar(){
        beerOptionsToolbar.toolbarTitleTextView.text = resources.getString(R.string.beer_options_toolbar_text)
    }

    private fun setUpRecyclerView() {
        beerOptionsRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun goToBeerInfoScreen(beerInfo: BeerResponse) {
        findNavController().navigate(BeerOptionsFragmentDirections.goToBeerInfoFragment(beerInfo))
    }

}