package com.example.todolist

import Database_Handler.task
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RVAdapter(private val context: Context, private  val list :ArrayList<task>) : RecyclerView.Adapter<RVAdapter.ItemViewHolder1>() {

         inner class ItemViewHolder1(private val view: View) :RecyclerView.ViewHolder(view)
        {

            val no : TextView = view.findViewById<TextView>(R.id.sr_no)
            var note: TextView =view.findViewById(R.id.note)
            var checked=view.findViewById<CheckBox>(R.id.checkBox)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder1 {
        val layOutInflater=LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return ItemViewHolder1(layOutInflater)
    }

    override fun onBindViewHolder(holder: ItemViewHolder1, position: Int) {
        val curret_task=list[position]
        val task_val = curret_task.task_val
        curret_task.task_id=position
        holder.no.text = (position+1).toString()
        holder.note.text =task_val
    }

    override fun getItemCount(): Int {
       return list.size
    }
}