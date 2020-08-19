package com.example.livedatademotow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.Executors

class NoteAdapter(val onClicked: OnClicked) :
    ListAdapter<Note, NoteAdapter.ViewHolder>(AsyncDifferConfig.Builder<Note>(NoteDiffCallBack())
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor()).build()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return ViewHolder(itemView, this)
    }

    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {
//        holder.setIsRecyclable(false)
        holder.bind(onClicked)
    }


    class ViewHolder(itemView: View, val adapter: NoteAdapter) : RecyclerView.ViewHolder(itemView) {

        val tvAvatar: TextView = itemView.findViewById(R.id.tv_avatar)
        val lItem: RelativeLayout = itemView.findViewById(R.id.rl_item)
        fun bind(onClicked: OnClicked) {
            val note = adapter.getItem(adapterPosition)
            tvAvatar.setText(note.title)

            if (!note.status) {
                tvAvatar.visibility = View.GONE
            }else{
                tvAvatar.visibility = View.VISIBLE
            }

            lItem.setOnClickListener { view ->
                onClicked.onClicked(adapterPosition)
            }
        }
    }
}
