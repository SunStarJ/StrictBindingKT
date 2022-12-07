package com.github.strict_databinding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

abstract class StrictBindingAdapter<T>(private val context: Context) :
    Adapter<StrictBindingAdapter<T>.MYViewHolder>() {

    var dataList: ObservableArrayList<T>? = null
        private set

    var onItemClickListener: ((position: Int) -> Unit)? = null

    inner class MYViewHolder(itemView: View) : ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MYViewHolder {
        val db = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(context),
            setLayoutId(viewType),
            parent,
            false
        )
        return MYViewHolder(db.root).apply {
            itemView.setOnClickListener { onItemClickListener?.invoke(adapterPosition) }
        }
    }

    override fun getItemCount(): Int = dataList?.size ?: 0

    override fun onBindViewHolder(holder: MYViewHolder, position: Int) {
        val db = DataBindingUtil.getBinding<ViewDataBinding>(holder.itemView)
        dataList?.let {
            setItemConfig(it[holder.adapterPosition]).forEach {
                db?.setVariable(it.id, it.value)
            }
        }
    }

    fun initDataList(dataList: ObservableArrayList<*>) {
        this.dataList = dataList as ObservableArrayList<T>
        this.dataList?.addOnListChangedCallback(object :
            ObservableList.OnListChangedCallback<ObservableArrayList<T>>() {
            override fun onChanged(sender: ObservableArrayList<T>?) {
                notifyDataSetChanged()
            }

            override fun onItemRangeChanged(
                sender: ObservableArrayList<T>?,
                positionStart: Int,
                itemCount: Int
            ) {
                notifyDataSetChanged()
            }

            override fun onItemRangeInserted(
                sender: ObservableArrayList<T>?,
                positionStart: Int,
                itemCount: Int
            ) {
                notifyDataSetChanged()
            }

            override fun onItemRangeMoved(
                sender: ObservableArrayList<T>?,
                fromPosition: Int,
                toPosition: Int,
                itemCount: Int
            ) {
                notifyDataSetChanged()
            }

            override fun onItemRangeRemoved(
                sender: ObservableArrayList<T>?,
                positionStart: Int,
                itemCount: Int
            ) {
                notifyDataSetChanged()
            }
        })
        kotlin.runCatching { notifyDataSetChanged() }

    }

    abstract fun setItemConfig(positionData: T): List<XMLBindingData>

    @LayoutRes
    abstract fun setLayoutId(viewType: Int): Int
}