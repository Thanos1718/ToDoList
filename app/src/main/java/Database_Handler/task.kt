package Database_Handler

import android.os.Parcel
import android.os.Parcelable

data class task(private var task: String?,private val id: Int?=0,private var completed:Boolean=false,private var priority:Int=0):Parcelable
{
    var task_id: Int? =0
        get() {
            return id
        }
        set(value) {
            field=value
        }
    var task_val : String?=task
        get() {
            return field
        }
        set(value) {
            field=value
        }
    var priority_val:Int?=0
    get()
    {
        return field
    }
    set(value)
    {
        field=value
    }
    var is_completed:Boolean?=false
    get()
    {
        return completed
    }
    set(value)
    {
        field=value
    }
    constructor(parcel: Parcel) : this(parcel.readString(),parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(task)
        if (id != null) {
            parcel.writeInt(id)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<task> {
        override fun createFromParcel(parcel: Parcel): task {
            return task(parcel)
        }

        override fun newArray(size: Int): Array<task?> {
            return arrayOfNulls(size)
        }
    }


}
