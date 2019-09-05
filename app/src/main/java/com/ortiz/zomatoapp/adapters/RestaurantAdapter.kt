package com.ortiz.zomatoapp.adapters

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ortiz.zomatoapp.models.Restaurant
import android.widget.TextView
import com.ortiz.zomatoapp.R
import android.view.LayoutInflater


class RestaurantAdapter(items: ArrayList<Restaurant>) :
    RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {
    var items: List<Restaurant> = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaurant_card, parent, false)
        return RestaurantViewHolder(v)
    }

    override fun getItemCount(): Int {
        Log.d("cat", items.size.toString())
        return items.size
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        //Log.d("bind", items.)
        holder.name.text = items[position].name
        holder.location.text = items[position].location
        holder.url.text = items[position].url
    }


    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name)
        var location: TextView = itemView.findViewById(R.id.location)
        var url: TextView = itemView.findViewById(R.id.url)

    }
}