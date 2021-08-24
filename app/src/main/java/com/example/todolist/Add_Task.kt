package com.example.todolist

import Database_Handler.task
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import crud_operations.Crud
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Add_Task : AppCompatActivity() {
    private var priority_add=-1
    private lateinit var numberPicker:NumberPicker
    val madapter:AdapterListner
        get() {
           return madapter
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
        numberPicker=findViewById(R.id.priority)
        setNumberPicker()
       val fab = findViewById<FloatingActionButton>(R.id.add_button)
        try{
            fab.setOnClickListener {
                add_task(applicationContext)
            }
        }
        catch (e:Exception)
        {
            Log.e("error", e.printStackTrace().toString())
        }
    }

    private fun setNumberPicker() {
        numberPicker.maxValue=9
        numberPicker.minValue=0
        numberPicker.setOnClickListener(View.OnClickListener {
            priority_add= Int.MAX_VALUE
            Log.e("Hello",priority_add.toString())
        })
    }

    private fun add_task(context: Context) {
        try {
            val editText = findViewById<EditText>(R.id.add_val)
            val task_val = editText.text.toString()
            if(task_val.equals(null) || task_val.equals(""))
                Toast.makeText(this,"Task cannot be empty",Toast.LENGTH_SHORT).show()
            else {
                priority_add=numberPicker.value
                val t = task(task_val,priority=priority_add)
                t.priority_val=priority_add
                Log.e("task msg",t.toString())
                val obj = Crud()
                GlobalScope.launch {
                    obj.addTask(t, context)
//                val list=obj.getTasks(context)
//                MainActivity.list.clear()
//                MainActivity.list.addAll(list)
                }
                Toast.makeText(this, "$task_val added", Toast.LENGTH_LONG).show()
                MainActivity.list.add(t)
//                for(i:task in MainActivity.list) {
//                    if(t.priority_val!! >= i!!.priority_val!!)
//                    {
//                        MainActivity.list.add(t)
//                        break
//                    }
//                }
                MainActivity.adapter.notifyDataSetChanged()
            }
        }
        catch (e:Exception)
        {
            Log.e("error",e.printStackTrace().toString())
        }
    }
}
/*
set adapter callback
set service
priority
checkbox listner
set undo
 */