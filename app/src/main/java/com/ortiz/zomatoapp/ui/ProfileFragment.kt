package com.ortiz.zomatoapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.textfield.TextInputEditText

import com.ortiz.zomatoapp.R
import android.content.SharedPreferences
import android.widget.Toast
import com.ortiz.zomatoapp.instrumentation.PreferencesUtils
import kotlinx.android.synthetic.main.fragment_profile.view.*


class ProfileFragment : Fragment() {
    var pref: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_profile, container, false)
        pref = rootView.context.getSharedPreferences("ZomatoPref", 0)
        loadPreferences(
            rootView.nameProfile,
            rootView.lastNameProfile,
            rootView.phoneProfile,
            rootView.emailProfile
        )
        rootView.buttonSaveProfile.setOnClickListener { view ->
            saveProfile(
                rootView.nameProfile,
                rootView.lastNameProfile,
                rootView.phoneProfile,
                rootView.emailProfile, rootView
            )
        }
        return rootView
    }

    private fun loadPreferences(
        nameProfile: TextInputEditText,
        lastNameProfile: TextInputEditText,
        phoneProfile: TextInputEditText,
        emailProfile: TextInputEditText
    ) {
        nameProfile.setText(pref?.getString(PreferencesUtils.PREF_USER_NAME, ""))
        lastNameProfile.setText(pref?.getString(PreferencesUtils.PREF_USER_LAST_NAME, ""))
        phoneProfile.setText(pref?.getString(PreferencesUtils.PREF_USER_PHONE, ""))
        emailProfile.setText(pref?.getString(PreferencesUtils.PREF_USER_EMAIL, ""))
    }

    private fun saveProfile(
        nameProfile: TextInputEditText,
        lastNameProfile: TextInputEditText,
        phoneProfile: TextInputEditText,
        emailProfile: TextInputEditText,
        rootView: View
    ) {
        var switch = false
        if (nameProfile.text.isNullOrBlank()) {
            nameProfile.error = "Llene este campo"
            switch = true
        }
        if (lastNameProfile.text.isNullOrBlank()) {
            lastNameProfile.error = "Llene este campo"
            switch = true
        }
        if (phoneProfile.text.isNullOrBlank()) {
            phoneProfile.error = "Llene este campo"
            switch = true
        }
        if (emailProfile.text.isNullOrBlank()) {
            emailProfile.error = "Llene este campo"
            switch = true
        }
        if (!switch) {
            val editor = pref?.edit()
            editor?.putString(PreferencesUtils.PREF_USER_NAME, nameProfile.text.toString())
            editor?.putString(PreferencesUtils.PREF_USER_LAST_NAME, lastNameProfile.text.toString())
            editor?.putString(PreferencesUtils.PREF_USER_PHONE, phoneProfile.text.toString())
            editor?.putString(PreferencesUtils.PREF_USER_EMAIL, emailProfile.text.toString())
            editor?.commit()
            Toast.makeText(rootView.context, "Datos actualizados", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ProfileFragment().apply {
                arguments = Bundle()
            }
    }
}
