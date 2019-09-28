package com.mcarving.cardviewexamples

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mcarving.cardviewexamples.data.local.AppStore
import com.mcarving.cardviewexamples.data.model.AppInfo

class AppListFragment : Fragment() {
    val TAG = "AppListFragment"

    private lateinit var mRecommendedAdapter: AppInfoAdapter
    private lateinit var mWorkoutAdapter: AppInfoAdapter

    private lateinit var mRecommendedRecyclerView: RecyclerView
    private lateinit var mRecommendedViewManager: RecyclerView.LayoutManager

    private lateinit var mWorkoutRecyclerView: RecyclerView
    private lateinit var mWorkoutViewManager: RecyclerView.LayoutManager

    private var mRecommendedItemListener = object : AppInfoItemListener {
        override fun onItemClick(appName: String) {
            Toast.makeText(requireContext(), "clicked on $appName", Toast.LENGTH_SHORT).show()
        }
    }

    private var mWorkoutItemListener = object : AppInfoItemListener {
        override fun onItemClick(appName: String) {
            Toast.makeText(requireContext(), "clicked on $appName", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_app_list, container, false)

        mRecommendedViewManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        mWorkoutViewManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        mRecommendedRecyclerView = root.findViewById<RecyclerView>(R.id.rv_recommended).apply {
            setHasFixedSize(true)
        }
        mWorkoutRecyclerView = root.findViewById<RecyclerView>(R.id.rv_workout).apply {
            setHasFixedSize(true)
        }

        mRecommendedRecyclerView.layoutManager = mRecommendedViewManager
        mWorkoutRecyclerView.layoutManager = mWorkoutViewManager

        initializeData()

        mRecommendedRecyclerView.adapter = mRecommendedAdapter
        mWorkoutRecyclerView.adapter = mWorkoutAdapter

        return root
    }


    fun initializeData() {
        mRecommendedAdapter =
            AppInfoAdapter(requireContext(), listOf<AppInfo>(), mRecommendedItemListener)

        val recommendedAppStore = AppStore(requireContext(), "jsons/recommended_json.txt")
        Log.d(
            TAG, "recommendedAppStore's size = "
                    + recommendedAppStore.appInfoList.size
        )
        mRecommendedAdapter.replaceData(recommendedAppStore.appInfoList)

        mWorkoutAdapter = AppInfoAdapter(requireContext(), listOf<AppInfo>(), mWorkoutItemListener)

        val workoutAppStore = AppStore(requireContext(), "jsons/workout_json.txt")

        Log.d(
            TAG, "workoutAppStore's size = "
                    + workoutAppStore.appInfoList.size
        )
        mWorkoutAdapter.replaceData(workoutAppStore.appInfoList)
    }


    interface AppInfoItemListener {
        fun onItemClick(appName: String)
    }

}