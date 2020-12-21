package com.egferreira.pagtest.beers.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.egferreira.pagtest.R
import com.egferreira.pagtest.beers.response.BeerResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_beer_option.view.*

class BeerAdapter(
    val beerList: List<BeerResponse>,
    val listener: (BeerResponse) -> Unit
) : RecyclerView.Adapter<BeerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        val context = viewGroup.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_beer_option, viewGroup, false)
        return ViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val beer = beerList[position]
        Picasso.with(holder.itemView.context).load(beer.imageUrl).into(holder.beerImage)
        holder.beerName.text = beer.name
        holder.beerAbv.text = String.format("%s%% Vol", beer.abv)
        holder.itemView.setOnClickListener {
            listener.invoke(beer)
        }
    }

    override fun getItemCount(): Int {
        return beerList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val beerName = itemView.beerOptionNameTextView
        val beerAbv = itemView.beerOptionAbvTextView
        val beerImage = itemView.berrOptionImageView
    }
}
