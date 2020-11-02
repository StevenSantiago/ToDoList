package io.github.stevensantiago.todolist

import android.content.Context
import androidx.preference.PreferenceManager

class ListDataManager(val context: Context) {
    fun saveList(list: TaskList) {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPref.putStringSet(list.name, list.tasks.toHashSet())
        sharedPref.apply()
    }

    fun readLists(): ArrayList<TaskList> {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        val contents = sharedPref.all
        val taskLists = ArrayList<TaskList>()

        for (taskList in contents) {
            val taskItems = ArrayList(taskList.value as HashSet<String>)
            val list = TaskList(taskList.key, taskItems)
            taskLists.add(list)
        }
        return taskLists
    }

}