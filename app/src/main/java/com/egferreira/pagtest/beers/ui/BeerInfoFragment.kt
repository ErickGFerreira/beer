package com.egferreira.pagtest.beers.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.egferreira.pagtest.R
import com.egferreira.pagtest.beers.response.BeerResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_beer_info.*
import kotlinx.android.synthetic.main.fragment_beer_options.*
import kotlinx.android.synthetic.main.toolbar_layout.view.*

class BeerInfoFragment : Fragment() {

    private val args: BeerInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_beer_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar(args.beerInfo.name)
        setupInformations(args.beerInfo)
    }

    private fun setUpToolbar(beerName: String) {
        setupToolbarBehavior()
        setupToolbarClickListener()
        setupToolbarText(beerName)
    }

    private fun setupToolbarText(beerName: String) {
        beerInfoToolbar.toolbarTitleTextView.text = beerName
    }

    private fun setupToolbarClickListener() {
        (beerInfoToolbar as Toolbar).setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupToolbarBehavior() {
        (activity as BeerActivity).setSupportActionBar(beerInfoToolbar as Toolbar)
        val supportActionBar = (activity as BeerActivity).supportActionBar
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupInformations(beerInfo: BeerResponse) {
        beerInfo.apply {
            Picasso.with(requireContext()).load(imageUrl).into(beerInfoImageView)
            beerInfoNameTextView.text = name
            beerInfoTaglineTextView.text = tagline
            beerInfoAbvTextView.text = String.format("%s%% Vol", abv)
            beerInfoIbuTextView.text = String.format("IBU %s", ibu)
            beerInfoDescriptionTextView.text = description
        }
    }
}