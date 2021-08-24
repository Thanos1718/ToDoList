package com.example.todolist

import Database_Handler.task
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class ItemAdapter(private val context: Context, private val list: ArrayList<task>, private val listner: onItemClickListner,private val checkListner:OnChecked) :RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    interface onItemClickListner
   {
       fun onClick(position: Int)
   }
    interface OnChecked
    {
        fun checked(position: Int)
    }
//    interface  OnDeleteClickListner{
//        fun delete()
//    }
    inner class ItemViewHolder(private val view: View, private val listner:onItemClickListner) :RecyclerView.ViewHolder(view) ,
         View.OnClickListener,CompoundButton.OnCheckedChangeListener
    {
        val no :TextView= view.findViewById<TextView>(R.id.sr_no)
        var note:TextView=view.findViewById(R.id.note)
        var checked=view.findViewById<CheckBox>(R.id.checkBox)

        init {
            view.setOnClickListener(this)
           // checked.setOnClickListener(CompoundButton.OnCheckedChangeListener(onCheck()))
        }

        override fun onClick(v: View?) {
              val position=adapterPosition
              Log.e("adapter","Adapter$position")
              if(position!=RecyclerView.NO_POSITION)
                  listner.onClick(position)
        }

        override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
             val position=adapterPosition
            Log.e("Clicked","Checked $position")
            //checprivate fun onCheck(): (buttonView: CompoundButton, isChecked: Boolean) -> Unit {
//    }kListner.checked(position)
        }
    }

//

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
      //  Log.e("rv","rv")
        return ItemViewHolder(layoutInflater,listner)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val curret_task=list[position]
        val task_val = curret_task.task_val
       curret_task.task_id=position

        holder.no.text = (position+1).toString()
        holder.note.text =task_val
        //Log.e("Eoor","error")

//        holder.checked.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, b ->
//            holder.checked.isChecked()
//
//        })

    }

    override fun getItemCount(): Int {
      return list.size
    }
}

private fun CheckBox.setOnClickListener(onCheckedChangeListener: CompoundButton.OnCheckedChangeListener) {

}
