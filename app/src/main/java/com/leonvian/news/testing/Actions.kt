package com.leonvian.news.testing

import android.util.Log

const val TAG = "MARCH"

class ActionOne : Action {

    override fun execute() {
        Log.i(TAG,"Action 1 Begin ${Thread.currentThread().name}")
        Thread.sleep(1000)
        Log.i(TAG,"Action 1 End")
    }
}


class ActionTwo : Action {

    override fun execute() {
        Log.i(TAG,"Action 2 Begin ${Thread.currentThread().name}")
        Thread.sleep(1000)
        Log.i(TAG,"Action 2 End")
    }
}


class ActionThree : Action {

    override fun execute() {
        Log.i(TAG,"Action 3 Begin ${Thread.currentThread().name}")
        Thread.sleep(1000)
        Log.i(TAG,"Action 3 End")
    }
}

class ActionFour : Action {

    override fun execute() {
        Log.i(TAG,"Action 4 Begin ${Thread.currentThread().name}")
        Thread.sleep(1000)
        Log.i(TAG,"Action 4 End")
    }
}


interface Action {

    fun execute()

}