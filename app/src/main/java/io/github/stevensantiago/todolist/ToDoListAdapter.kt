package io.github.stevensantiago.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoListAdapter: RecyclerView.Adapter<ToDoListViewHolder>() {


    var sampleList  = mutableListOf("Number One", "Number Two", "Number Three")
    fun addNewItem() {
        sampleList.add("Number " + (sampleList.size + 1))
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_list_viewholder, parent, false)
        return ToDoListViewHolder(view)
    }

    override fun getItemCount(): Int {
       return sampleList.size
    }

    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {
        holder.listTitleTextView.text = sampleList[position]
        holder.listPositionTextView.text = (position + 1).toString()
    }
}
class ToDoListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    var listPositionTextView = itemView.findViewById<TextView>(R.id.itemNumber)
    var listTitleTextView = itemView.findViewById<TextView>(R.id.itemString)
}