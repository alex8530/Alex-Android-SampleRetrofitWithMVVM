package com.example.alexandroidsampleretrofitwithmvvm.views.StoreScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.alexandroidsampleretrofitwithmvvm.NetworkState
import com.example.alexandroidsampleretrofitwithmvvm.R
import com.example.alexandroidsampleretrofitwithmvvm.Status
import com.example.alexandroidsampleretrofitwithmvvm.classes.Store
import kotlinx.android.synthetic.main.row_item_recyle.view.*

class StoreAdapter  : PagedListAdapter<Store, RecyclerView.ViewHolder>(REPO_COMPARATOR) {


    private var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
//        return RepoViewHolder(view)

        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View

        if (viewType == R.layout.row_item_recyle) {
            view = layoutInflater.inflate(R.layout.row_item_recyle, parent, false)
            return StoreViewHolder(view)
        } else if (viewType == R.layout.network_state_item) {
            view = layoutInflater.inflate(R.layout.network_state_item, parent, false)
            return NetworkStateItemViewHolder(view)
        } else {
            throw IllegalArgumentException("unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        val repo = getItem(position)
//        repo?.let {
//            holder.setData(repo)
//        }

        when (getItemViewType(position)) {
            R.layout.row_item_recyle -> (holder as StoreViewHolder).setData(getItem(position)!!)
            R.layout.network_state_item -> (holder as NetworkStateItemViewHolder).bindView(
                networkState
            )
        }

    }

    class StoreViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun setData(Store: Store) {
            view.txt_name.text = Store.name
        }
    }

    private fun hasExtraRow(): Boolean {
        return networkState != null && networkState !== NetworkState.LOADED
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.network_state_item
        } else {
            R.layout.row_item_recyle
        }
    }

    fun setNetworkState(newNetworkState: NetworkState) {
        val previousState = this.networkState
        val previousExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val newExtraRow = hasExtraRow()
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(itemCount)
            } else {
                notifyItemInserted(itemCount)
            }
        } else if (newExtraRow && previousState !== newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    internal class NetworkStateItemViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val progressBar: ProgressBar
        private val errorMsg: TextView
        private val button: Button

        init {
            progressBar = itemView.findViewById(R.id.progress_bar)
            errorMsg = itemView.findViewById(R.id.error_msg)
            button = itemView.findViewById(R.id.retry_button)

//            button.setOnClickListener { view ->
//                listItemClickListener.onRetryClick(
//                    view,
//                    adapterPosition
//                )
//            }
        }


        fun bindView(networkState: NetworkState?) {
            if (networkState != null && networkState.status === Status.RUNNING) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }

            if (networkState != null && networkState.status === Status.FAILED) {
                errorMsg.visibility = View.VISIBLE
                errorMsg.text = networkState.msg
            } else {
                errorMsg.visibility = View.GONE
            }
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Store>() {
            override fun areItemsTheSame(oldStore: Store, newStore: Store): Boolean =
                oldStore.id == newStore.id

            override fun areContentsTheSame(oldStore: Store, newStore: Store): Boolean =
                oldStore.equals(newStore)
        }
    }
}