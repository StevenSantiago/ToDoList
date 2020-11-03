package io.github.stevensantiago.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoListAdapter(private val lists: ArrayList<TaskList>, val clickListener: TodoListClickListener): RecyclerView.Adapter<ToDoListViewHolder>() {

    interface TodoListClickListener {
        fun listItemClicked(list: TaskList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_list_viewholder, parent, false)
        return ToDoListViewHolder(view)
    }

    override fun getItemCount(): Int {
       return lists.size
    }

    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {
        holder.listTitleTextView.text = lists[position].name
        holder.listPositionTextView.text = (position + 1).toString()
        holder.itemView.setOnClickListener {
            clickListener.listItemClicked(lists[position])
        }
    }

    fun addList(list: TaskList) {
       lists.add(list)
        // Call below function to reload recycle view
        notifyItemInserted(lists.size - 1)
    }
}
class ToDoListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    var listPositionTextView = itemView.findViewById<TextView>(R.id.itemNumber)
    var listTitleTextView = itemView.findViewById<TextView>(R.id.itemString)
}