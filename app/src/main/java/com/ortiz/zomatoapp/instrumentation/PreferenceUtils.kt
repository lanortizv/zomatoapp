package com.ortiz.zomatoapp.instrumentation

class PreferencesUtils {
    companion object {
        var PREF_USER_KEY = "924296f640073404055d106770fcb261"
        var PREF_USER_NAME = "user_name"
        var PREF_USER_LAST_NAME = "user_last_name"
        var PREF_USER_PHONE = "user_phone"
        var PREF_USER_EMAIL = "user_email"
        fun getUserKey(): String = PREF_USER_KEY
        fun getServerUrl(): String {
            return "https://developers.zomato.com/api/v2.1/"
        }
    }
}