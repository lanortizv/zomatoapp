package com.ortiz.zomatoapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView

import com.ortiz.zomatoapp.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.ortiz.zomatoapp.adapters.RestaurantAdapter
import com.ortiz.zomatoapp.instrumentation.PreferencesUtils
import com.ortiz.zomatoapp.models.Restaurant
import com.ortiz.zomatoapp.rest.RestaurantService
import com.ortiz.zomatoapp.rest.Restaurants
import kotlinx.android.synthetic.main.fragment_search.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.content.Context
import android.view.inputmethod.InputMethodManager


class SearchFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RestaurantAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getActivity()!!.window.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        )
        val rootView = inflater.inflate(R.layout.fragment_search, container, false)
        searchRestaurant(rootView.searchButton, rootView.edit_text_restaurant,rootView)

// Obtener el Recycler
        recyclerView = rootView.findViewById(R.id.restaurantRecycler) as RecyclerView
        recyclerView.setHasFixedSize(true)

// Usar un administrador para LinearLayout
        viewManager = LinearLayoutManager(rootView.context)
        recyclerView.setLayoutManager(viewManager)

// Crear un nuevo adaptador
        viewAdapter = RestaurantAdapter()
        recyclerView.setAdapter(viewAdapter)
        // Inflate the layout for this fragment
        return rootView
    }

    private fun searchRestaurant(
        searchButton: ImageButton,
        editTextRestaurant: EditText,
        rootView: View
    ) {
        searchButton.setOnClickListener { view ->
            if (editTextRestaurant.text.isNullOrEmpty()) {
                editTextRestaurant.error = "Llene este espacio"
            } else {
                searchRequest(editTextRestaurant.text.toString())
                val inputManager =
                    activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(rootView.getWindowToken(), 0)
            }
        }
    }

    fun searchRequest(search: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(PreferencesUtils.getServerUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(RestaurantService::class.java)
        val call = service.getRestaurants(search)
        call.enqueue(object : Callback<Restaurants> {
            override fun onResponse(call: Call<Restaurants>, response: Response<Restaurants>) {
                val d = response.body()?.restaurants
                if (!d.isNullOrEmpty()) {
                    var restaurants = mutableListOf<Restaurant>()
                    for (restaurant in d) {
                        Log.d("cat", restaurant.restaurant!!.name)
                        restaurants.add(restaurant.restaurant!!)
                    }
                    Log.d("cat", "salio del for")
                    Log.d("cat", restaurants?.size.toString())
                    viewAdapter.items = restaurants
                    viewAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<Restaurants>, t: Throwable) {
                Log.e("CAT", "ERROR")
            }
        })
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            SearchFragment().apply {
                arguments = Bundle()
            }
    }
}
