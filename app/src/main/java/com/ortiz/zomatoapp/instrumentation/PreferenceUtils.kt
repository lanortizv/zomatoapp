package com.ortiz.zomatoapp.instrumentation

class PreferencesUtils {
    companion object{
        var PREF_USER_KEY = "924296f640073404055d106770fcb261"
        fun getUserKey():String = PREF_USER_KEY
        fun getServerUrl(): String {
//            return parseServerUrl(getString(context, PREF_SERVER_ADDRESS))
            return "https://developers.zomato.com/api/v2.1/"
        }
    }
}