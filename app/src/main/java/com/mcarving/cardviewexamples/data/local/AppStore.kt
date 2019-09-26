package com.mcarving.cardviewexamples.data.local

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import com.mcarving.cardviewexamples.data.model.AppInfo
import org.json.JSONArray
import org.json.JSONException
import java.lang.RuntimeException

class AppStore(val context : Context, val fileName : String) {
    var appInfoList = arrayListOf<AppInfo>()
    var appInfoMap = HashMap<String, AppInfo>()

    init {
        val jsonStr = context.assets.readAssetsFile(fileName)

        jsonStr?.let {
            getAppInfoFromJsonString(it)
        }
    }


    fun AssetManager.readAssetsFile(fileName : String) : String? =
        open(fileName).bufferedReader().use{it.readText()}

    fun getAppInfoFromJsonString(jsonString : String) {
        try {
            val jArray = JSONArray(jsonString)

            for (i in 0 until jArray.length()){
                val item = jArray.getJSONObject(i)

                val appName = item.getString("app_name")
                val appRating = item.getDouble("rating")
                val imageUrl = item.getString("image_url")

                Log.d("AppStore", "App name = " + appName)
                Log.d("AppStore", "App rating = " + appRating)
                Log.d("AppStore", "App imageUrl = " + imageUrl)

                val newAppInfo = AppInfo(appName, appRating, imageUrl)
                appInfoList.add(newAppInfo)

                appInfoMap.put(appName, newAppInfo)
            }
        } catch (e: JSONException) {
            throw RuntimeException(e)
        }
    }
}