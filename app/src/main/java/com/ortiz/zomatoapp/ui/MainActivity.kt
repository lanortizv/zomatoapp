package com.ortiz.zomatoapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ortiz.zomatoapp.R
import com.ortiz.zomatoapp.instrumentation.PreferencesUtils
import com.ortiz.zomatoapp.rest.Categories
import com.ortiz.zomatoapp.rest.CommonService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBottomNavigationView(supportFragmentManager, bottomNavigationView)
    }
}

private fun initBottomNavigationView(
    supportFragmentManager: FragmentManager,
    bottomNavigationView: BottomNavigationView
) {
    bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
        var fragment: Fragment? = null
        Log.d("cat", menuItem.itemId.toString())
        when (menuItem.itemId) {
            R.id.action_search -> fragment = SearchFragment.newInstance()
            R.id.action_favorites -> fragment = FavoritesFragment.newInstance()
            R.id.action_profile -> fragment = ProfileFragment.newInstance()
            else -> fragment = SearchFragment.newInstance()
        }
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment!!)
            .commit()
        true
    }
    bottomNavigationView.selectedItemId = R.id.action_search
}

fun CategoriesRequest() {
    val retrofit = Retrofit.Builder()
        .baseUrl(PreferencesUtils.getServerUrl())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service = retrofit.create(CommonService::class.java)
    val call = service.getCategories()
    call.enqueue(object : Callback<Categories> {
        override fun onResponse(
            call: Call<Categories>,
            response: Response<Categories>
        ) {
            Log.d("CAT", response.code().toString())
            var a: String = ""
            val d = response.body()?.categories
            Log.d("cat", d?.size.toString())
            if (!d.isNullOrEmpty()) {
                for (categories in d) {
                    a = a + categories.categories?.name + "\n"
                    Log.d("CAT", categories.categories?.name)
                }
            }
            Log.d("cat", a)
        }

        override fun onFailure(call: Call<Categories>, t: Throwable) {
            Log.e("CAT", "ERROR")
        }
    })
}

