package Database_Handler

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.todolist.MainActivity
import java.util.*
import java.util.concurrent.BlockingQueue
import java.util.concurrent.PriorityBlockingQueue
import kotlin.Comparator
import kotlin.collections.ArrayList

class db_handler(context:Context): SQLiteOpenHelper(context,Database_name,null,Database_version) {
    val applContext=context
    companion object{
        private const val Database_name="Tasks_db"
        private const val Database_version=1
        private const val Table_name="Tasks"
        private const val id="id"
        private const val task_name="task_val"
        private const val task_priority="task_priority"
        private const val isCompleted="task_completed"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val create_table="create table if not exists $Table_name  ($id integer primary key, $task_name varchar(50), $isCompleted Boolean,$task_priority Int) "
            db?.execSQL(create_table)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dbHelper=this.writableDatabase
        dbHelper.delete(Table_name,null, null)
    }
    fun add_Task(t:task)
    {
        Log.e("Addes task",t.toString()+"id"+t.task_id+t.task_val+t.priority_val)
        val dbHelper=this.writableDatabase
        val contents=ContentValues()
        contents.put(task_name,t.task_val)
        contents.put(task_priority,t.priority_val)
        dbHelper.insert(Table_name,null,contents)
        dbHelper.close()
    }
    fun update_Task(t:task)
    {
        val dbHelper=this.writableDatabase
        val contents=ContentValues()
        Log.e("TAG",t.toString()+" "+t.task_val)
        contents.put(task_name,t.task_val)
        dbHelper.update(Table_name,contents,"id="+t.task_id,null)
        dbHelper.close()
    }
    fun delete_Task(t:task)
    {
        val dbHelper=this.writableDatabase
        dbHelper.delete(Table_name,"id="+t.task_id,null)
        Log.e("log",t.toString())
        dbHelper.close()
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun getAllTask():ArrayList<task>
    {
       //Log.e("DB called","DB called")
        val dbHelper=this.readableDatabase
       val list=ArrayList<task>()
        val tmp= PriorityBlockingQueue<task>()
        var cursor: Cursor? =null
        val query="select * from $Table_name order by $task_priority"

        var task_id:Int
        var task_val:String
        var task_priority:Int
        var task_completed:Boolean
        try {
            cursor= dbHelper.rawQuery(query,null)
        }
        catch (e:Exception)
        {
            dbHelper.execSQL(query)
            return ArrayList()
        }
        if(cursor.moveToFirst())
        {
           do {
               task_id=cursor.getInt(0)
               task_val= cursor.getString(1)
              // task_priority=cursor.getInt(2)
               val task_obj=task(task_val,task_id)
               list.add(task_obj)
           } while (cursor.moveToNext())
        }

        Log.e("priority queue", list.toString())
//        while(!tmp.isEmpty())
//            list.add(tmp.poll())
//        Log.e("Db handler list", MainActivity.list.toString())
        return list
    }
}