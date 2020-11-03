package io.github.stevensantiago.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DetailActivity : AppCompatActivity() {

    lateinit var list: TaskList
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        list = intent.getParcelableExtra(FirstFragment.INTENT_LIST_KEY) as TaskList
        title = list.name
    }
}