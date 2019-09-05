package com.ortiz.zomatoapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.ortiz.zomatoapp.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.ortiz.zomatoapp.adapters.RestaurantAdapter
import com.ortiz.zomatoapp.models.Restaurant


class SearchFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_search, container, false)
        val items = ArrayList<Restaurant>()

        items.add(Restaurant("Res1", "San Pedro1", "http1"))
        items.add(Restaurant("Res2", "San Pedro2", "http2"))
        items.add(Restaurant("Res3", "San Pedro3", "http3"))
        items.add(Restaurant("Res4", "San Pedro4", "http4"))


// Obtener el Recycler
        recyclerView = rootView.findViewById(R.id.restaurantRecycler) as RecyclerView
        recyclerView.setHasFixedSize(true)

// Usar un administrador para LinearLayout
        viewManager = LinearLayoutManager(rootView.context)
        recyclerView.setLayoutManager(viewManager)

// Crear un nuevo adaptador
        viewAdapter = RestaurantAdapter(items)
        recyclerView.setAdapter(viewAdapter)
        // Inflate the layout for this fragment
        return rootView
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            SearchFragment().apply {
                arguments = Bundle()
            }
    }
}
