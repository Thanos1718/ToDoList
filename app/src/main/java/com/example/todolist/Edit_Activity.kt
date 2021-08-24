package com.example.todolist

import Database_Handler.task
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import crud_operations.Crud
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Edit_Activity : AppCompatActivity() {
    var task_editText: EditText? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        getInit()
    }

    private fun getInit() {
        val intent=intent
        val position=intent.getIntExtra("position",0)
        val t=intent.getParcelableExtra<task>("task_object")
        val id= t?.task_id
        task_editText=findViewById<EditText>(R.id.my_task)
        if (t != null) {
            task_editText?.setText(t.task_val)
        }
        val save=findViewById<Button>(R.id.save)
        save.setOnClickListener(
            View.OnClickListener {
                if (t != null) {
                    updateData(t,position,id)
                }
            }
        )
    }
    private fun updateData(t:task, position:Int, id: Int?) {
        val data: String = task_editText?.text.toString()
        if (data.equals(t.task_val))
            Toast.makeText(this, "Nothings changed!!", Toast.LENGTH_SHORT).show()
        else if (data.equals("") || data.equals(null))
            Toast.makeText(this, "Task cannot be empty!!", Toast.LENGTH_SHORT).show()
        else {
            t.task_val = data
//        val new_task=task(data,id)
            GlobalScope.launch {
                val obj = Crud()
                obj.updateTask(applicationContext, t)
            }
            MainActivity.list.set(position, t)
            MainActivity.adapter.notifyItemChanged(position)
            Toast.makeText(this, "Your task updated", Toast.LENGTH_SHORT).show()
        }
    }
}
//
/*
Adapter callback
service for timer:notification
checkbox completed
Deadline
priority
 */