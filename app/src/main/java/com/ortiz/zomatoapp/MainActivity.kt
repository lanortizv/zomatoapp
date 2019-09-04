package com.ortiz.zomatoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        button.setOnClickListener { CategoriesRequest() }

    }

    private fun CategoriesRequest() {
        Log.d("button", PreferencesUtils.getServerUrl())
        val retrofit = Retrofit.Builder()
            .baseUrl(PreferencesUtils.getServerUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(CommonService::class.java)
        val call = service.getCategories()
        Log.d("CAT",call.request().toString())
        call.enqueue(object : Callback<Categories> {
            override fun onResponse(
                call: Call<Categories>,
                response: Response<Categories>
            ) {
                Log.d("CAT", response.code().toString())
                var a: String = ""
                val d=response.body()?.categories
                Log.d("cat",d?.size.toString())
                if(!d.isNullOrEmpty()){
                    for (categories in d) {
                        a = a + categories.categories?.name + "\n"
                        Log.d("CAT", categories.categories?.name)
                    }
                }

                test.text = a
            }

            override fun onFailure(call: Call<Categories>, t: Throwable) {
                Log.e("CAT", "ERROR")
            }
        })

    }
}
