package com.mcarving.cardviewexamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mcarving.cardviewexamples.data.local.AppStore

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recommendedAppStore = AppStore(this, "jsons/recommended_json.txt")
        val workoutAppStore = AppStore(this, "jsons/workout_json.txt")

        Log.d("MainActivity", "recommendedAppStore's size = "
                + recommendedAppStore.appInfoList.size)
        Log.d("MainActivity", "workoutAppStore's size = "
                + workoutAppStore.appInfoList.size)

    }



}
