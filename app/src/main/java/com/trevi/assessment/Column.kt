package com.trevi.assessment

import android.view.View
import android.view.ViewGroup

interface Column {
    fun add(cell: Cell)
    fun setResetButton(button: ResetButton)
    fun attachToStage()
    fun showRandom(index: Int)
    fun clear()

    interface Cell {
        fun attachToStage(parent: ViewGroup)
        fun showRandom()
        fun clear()
    }

    interface ResetButton {
        fun attachToStage(parent: ViewGroup)
        fun enable()
        fun disable()
        fun setClickListener(listener: View.OnClickListener)
    }
}