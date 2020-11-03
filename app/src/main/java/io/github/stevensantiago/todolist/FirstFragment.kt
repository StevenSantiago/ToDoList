package io.github.stevensantiago.todolist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.github.stevensantiago.todolist.FirstFragment.Companion.INTENT_LIST_KEY
import kotlinx.android.synthetic.main.activity_main.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), ToDoListAdapter.TodoListClickListener {

    lateinit var toDoListRecyclerView: RecyclerView
    lateinit var parentContext: Context

    lateinit var listManager: ListDataManager
    lateinit var list:  ArrayList<TaskList>
    var actionButton: FloatingActionButton? = null

    companion object {
        const val INTENT_LIST_KEY = "list"
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        list = listManager.readLists()
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }



        toDoListRecyclerView = view.findViewById(R.id.recycleView)
        toDoListRecyclerView.adapter = ToDoListAdapter(list, this)

        actionButton = activity?.findViewById(R.id.fab)

        actionButton?.setOnClickListener {
            showCreateTodoListDialog(parentContext)
        }


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentContext = context
        listManager = ListDataManager(context)
    }

    private fun showCreateTodoListDialog(context: Context) {
        val dialogTitle = getString(R.string.name_list)
        val buttonTitle = getString(R.string.create_button_title)
        val myDialog = AlertDialog.Builder(context)
        val todoTitleEditText = EditText(context)

        todoTitleEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS
        myDialog.setTitle(dialogTitle)
        myDialog.setView(todoTitleEditText)
        myDialog.setPositiveButton(buttonTitle) {
                dialog, _ ->
            val adapter = toDoListRecyclerView.adapter as ToDoListAdapter
            val list = TaskList(todoTitleEditText.text.toString())
            listManager.saveList(list)
            adapter.addList(list)
            dialog.dismiss()
            showTaskListItems(list)
        }
        myDialog.create().show()
    }

    private fun showTaskListItems(list: TaskList) {
        val taskListItem = Intent(parentContext, DetailActivity::class.java)//Intent(this, DetailActivity::class.java)
        taskListItem.putExtra(INTENT_LIST_KEY, list)
        startActivity(taskListItem)
    }

    override fun listItemClicked(list: TaskList) {
        showTaskListItems(list)
    }
}
