package com.mcarving.cardviewexamples

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mcarving.cardviewexamples.data.model.AppInfo

class AppInfoAdapter(private val context : Context,
                     private var appInfoList : List<AppInfo>,
                     private val itemListener: AppListFragment.AppInfoItemListener) :
        RecyclerView.Adapter<AppInfoAdapter.AppInfoViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppInfoViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_horizontal, parent, false) as View
        return AppInfoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return appInfoList.size
    }

    fun replaceData(appInfos : List<AppInfo>){
        appInfoList = appInfos
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: AppInfoViewHolder, position: Int) {
        val TAG = "AppInfoAdapter"
        Log.d(TAG, "onBindViewHolder: item @ $position = ${appInfoList[position].appName}")
        holder.textViewAppName?.text = appInfoList[position].appName
        holder.textViewAppRating?.text =
            "${appInfoList[position].appRating.toString()}${context.resources.getString(R.string.star)}"
        val resId = context.resources.getIdentifier(appInfoList[position].appUrl,
                "drawable", context.packageName)
        holder.imageView?.setImageResource(resId)

        holder.imageView?.setOnClickListener {
            itemListener.onItemClick(appInfoList[position].appName)
        }
    }
    inner class AppInfoViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        val textViewAppName : TextView? = view.findViewById<TextView>(R.id.tv_app_name)
        val textViewAppRating : TextView? = view.findViewById<TextView>(R.id.tv_app_rating)
        val imageView : ImageView? = view.findViewById<ImageView>(R.id.iv_app_image)

    }
}