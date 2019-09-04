package com.ortiz.zomatoapp

import android.content.Context
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    open var menu: Menu? = null
    val PERMISSION_REQUEST_CODE = 8


    fun setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()

            /*R.id.action_notification -> {
                NotificationActivity.launch(this)
            }*/
        }
        return super.onOptionsItemSelected(item)
    }

  /*  fun setupToolbar(toolbar: Int = R.id.toolbar) {
        setSupportActionBar(findViewById(toolbar))
    }*/

    fun hideKeyboard(view: View? = this.currentFocus) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        if (view != null && imm != null) {
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

  /*  var materialProgressDialog: MaterialDialog? = null
    open fun showLoader(message: String = getString(R.string.loading)) {
        materialProgressDialog = MaterialDialog.Builder(this)
            .content(message)
            .cancelable(false)
            .progress(true, 0)
            .build()

        materialProgressDialog?.show()
    }*/




    /*open fun dismissProgressDialog() {
        if (materialProgressDialog != null && materialProgressDialog!!.isShowing) {
            materialProgressDialog?.dismiss()
        }
    }*/

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menu = menu
       //    menuInflater.inflate(R.menu.menu_watch, menu)
        return super.onCreateOptionsMenu(menu)
    }

}