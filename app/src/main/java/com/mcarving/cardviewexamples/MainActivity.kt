package com.mcarving.cardviewexamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.tabs.TabLayout
import com.mcarving.cardviewexamples.data.local.AppStore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recommendedAppStore = AppStore(this, "jsons/recommended_json.txt")
        val workoutAppStore = AppStore(this, "jsons/workout_json.txt")

        Log.d(
            "MainActivity", "recommendedAppStore's size = "
                    + recommendedAppStore.appInfoList.size
        )
        Log.d(
            "MainActivity", "workoutAppStore's size = "
                    + workoutAppStore.appInfoList.size
        )

        //https://github.com/KarimRedaHassan/Navigation-Component-TabLayout-with-Custom-Transition-Animation-and-Navigation-Listener
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment? ?: return

        val navController = host.navController

        val options = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setPopUpTo(navController.graph.startDestination, false)
            .setEnterAnim(R.anim.slide_in_right)
            .setExitAnim(R.anim.slide_out_left)
            .setPopEnterAnim(R.anim.slide_in_left)
            .setPopExitAnim(R.anim.slide_out_right)
            .build()

        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
                //("not implemented")
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
                //("not implemented")
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {

                tab?.let {
                    when (it.position) {
                        //  start SingleCardViewFragment
                        0 -> navController.navigate(R.id.singleCardViewFragment, null, options)
                        // start AppListFragment
                        1 -> navController.navigate(R.id.appListFragment, null, options)
                        // start TopChartsFragment
                        2 -> navController.navigate(R.id.topChartsFragment, null, options)
                    }
                }
            }
        })
    }


    override fun onBackPressed() {
        // go back to SingleCardViewFragment when user presses back button
        tab_layout.getTabAt(0)?.apply {
            select()
        }
        //super.onBackPressed()
    }

}
