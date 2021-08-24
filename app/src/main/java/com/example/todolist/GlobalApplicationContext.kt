package com.example.todolist

import android.app.Application
import android.content.Context

class GlobalApplicationContext : Application()  {
    lateinit var appContext : Context

    override fun onCreate() {
        super.onCreate()
        appContext= applicationContext
    }

    fun getContext(): Context {
        return appContext
    }
}