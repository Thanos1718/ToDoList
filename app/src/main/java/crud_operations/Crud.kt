package crud_operations

import Database_Handler.db_handler
import Database_Handler.task
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.todolist.GlobalApplicationContext

class Crud {
    suspend fun addTask(t:task,context: Context)
    {
        val dbHandler=db_handler(context)
        dbHandler.add_Task(t)
    }
   suspend fun updateTask(context:Context,t:task)
    {
        val dbHandler=db_handler(context)
        dbHandler.update_Task(t)
    }
  suspend fun deleteTask(t:task,context: Context)
    {
        val dbHandler =db_handler(context)
        dbHandler.delete_Task(t)
    }
   @RequiresApi(Build.VERSION_CODES.N)
   suspend fun getTasks(context:Context):ArrayList<task>
    {
        val db= db_handler(context)
        val tasks=db.getAllTask()
        return tasks
    }
}