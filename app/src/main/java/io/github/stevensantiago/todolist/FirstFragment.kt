package io.github.stevensantiago.todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    lateinit var toDoListRecyclerView: RecyclerView
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
            val adapter = toDoListRecyclerView.adapter as ToDoListAdapter
            adapter.addNewItem()
        }

    }
}
