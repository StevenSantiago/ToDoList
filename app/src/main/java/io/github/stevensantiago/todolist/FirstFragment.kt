package io.github.stevensantiago.todolist

import android.content.Context
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
import kotlinx.android.synthetic.main.activity_main.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    lateinit var toDoListRecyclerView: RecyclerView
    lateinit var parentContext: Context
    var actionButton: FloatingActionButton? = null


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }



        toDoListRecyclerView = view.findViewById(R.id.recycleView)
        toDoListRecyclerView.adapter = ToDoListAdapter()

        actionButton = activity?.findViewById(R.id.fab)

        actionButton?.setOnClickListener {
            showCreateTodoListDialog(parentContext)
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentContext = context
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
            adapter.addNewItem(todoTitleEditText.text.toString())
            dialog.dismiss()
        }
        myDialog.create().show()
    }
}
