package com.example.todolist

import Database_Handler.task
import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.annotation.UiThread
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import crud_operations.Crud
import kotlinx.coroutines.*
import java.io.Serializable
import java.lang.System.exit
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(),ItemAdapter.onItemClickListner,ItemAdapter.OnChecked,
    Serializable {
    private lateinit var add_task :ImageView
    private  var position: Int=0
   private lateinit var obj:Crud

    companion object
    {
        @SuppressLint("StaticFieldLeak")
        lateinit var adapter:ItemAdapter
        var list:ArrayList<task> = ArrayList()
    }
    override fun onBackPressed() {
        val alert=AlertDialog.Builder(this)
        alert.setIcon(android.R.drawable.dialog_holo_dark_frame)
        alert.setNegativeButton("No",null)
        alert.setMessage("You want to exit?")
        alert.setPositiveButton("Yes"){dialogInterface ,which->
            exit(1)
        }
        alert.show()
    }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        obj=Crud()
        setContentView(R.layout.activity_main)
        add_task=findViewById(R.id.add_task)
        add_task.setOnClickListener {
            add_Task()
        }

//        var tasks : List<task>?=null
//        GlobalScope.launch {
//            tasks=getTasks()
//        }

        getTasks()
        Log.e("jk", list.toString())
        for(t:task in list)
            Log.e("taskid",t.task_id.toString() +" "+t.priority_val)
        initRecyclerView()
/*
recyclerView.adapter= tasks?.let {
ItemAdapter(this, it) }
*/
    }


     fun delete(list:ArrayList<task>)
    {

    }

    override fun onClick(position: Int) {
        val obj_task=list.get(position)
        intent=Intent(this,Edit_Activity::class.java)
        intent.putExtra("position",position)
        intent.putExtra("task_object",obj_task)
        startActivity(intent)
    }

    fun add_Task() {
        val intent =Intent(this,Add_Task::class.java)
        try {
            val intent =Intent(this,Add_Task::class.java)

           startActivity(intent)
            adapter.notifyDataSetChanged()
        }
        catch (e:Exception)
        {
            println("error add${e.printStackTrace()}")
        }
    }

    override fun checked(position: Int) {
       Toast.makeText(this,"$position clicked by checkbox",Toast.LENGTH_LONG).show()
    }

    @RequiresApi(Build.VERSION_CODES.N)
   fun getTasks()= runBlocking{
        list= ArrayList()
        try
        {
//            GlobalScope.launch {
//                val tmp_list = obj.getTasks(applicationContext)
//                runOnUiThread()
//                {
//                    list.addAll(tmp_list)
//                    // Log.e("list",list.toString())
//                }
//            }

         val defferedJob:Deferred<ArrayList<task>> = async(Dispatchers.IO){
                val tmp_list = obj.getTasks(applicationContext)
              Log.e("Mainactivity list",tmp_list.toString())
            tmp_list
            }
            list=defferedJob.await()
           // Log.e("list 1",list.toString())
        } catch(e:Exception)
        {
            Log.e("Error while getting ", e.printStackTrace().toString())
        }
    }
    fun initRecyclerView()
    {
        val recyclerView =findViewById<RecyclerView>(R.id.recyclerView)
        //val check=R.id.checkBox
        // list.addAll(tmp_list)
        // Log.e("list",tmp_list.toString())
        adapter= ItemAdapter(this,list,this,this)
        ItemTouchHelper(OnTouchActions(adapter,applicationContext)).attachToRecyclerView(recyclerView)
        recyclerView.adapter =adapter
    }
}
