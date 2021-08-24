package com.example.todolist

import Database_Handler.task
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import crud_operations.Crud
import kotlinx.coroutines.runBlocking

class OnTouchActions(private val adapter: ItemAdapter,private val context: Context):ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        TODO("Not yet implemented")
    }
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position=viewHolder.adapterPosition
        val task_item=MainActivity.list.get(position)
        Log.e("List of 14",task_item.task_id .toString()+"ui"+task_item.task_val)
        delete_task(task_item,position)
        adapter.notifyDataSetChanged()
        Log.e("List of",MainActivity.list.toString())
    }
    fun delete_task(t:task,position:Int)
    {
        val obj=Crud()
        runBlocking {
            obj.deleteTask(t,context)
        }
        MainActivity.list.removeAt(position)
        Toast.makeText(context,"${t.task_val} removed",Toast.LENGTH_SHORT).show()
    }
}