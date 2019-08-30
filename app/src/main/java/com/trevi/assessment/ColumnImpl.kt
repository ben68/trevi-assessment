package com.trevi.assessment

import android.view.View
import android.view.ViewGroup

class ColumnImpl(root: ViewGroup) : Column {

    val container: ViewGroup by lazy {
        root.findViewById<ViewGroup>(R.id.colContainer)
    }

    override fun attachToStage() {
        for (cell in list) {
            cell.attachToStage(container)
        }
        resetButton?.attachToStage(container)
    }

    private val list = mutableListOf<Column.Cell>()
    override fun add(cell: Column.Cell) {
        list += cell
    }

    private var resetButton: Column.ResetButton? = null
    override fun setResetButton(button: Column.ResetButton) {
        resetButton = button
            .also {
                it.disable()
                it.setClickListener(View.OnClickListener { clear() })
            }
    }

    override fun clear() {
        randomCell?.clear()
        highlight(false)
    }

    private var randomCell: Column.Cell? = null
    override fun showRandom(index: Int) {
        clear()
        list[index]
            .apply {
                showRandom()
                randomCell = this
                highlight()
            }
    }

    private val bg = root.findViewById<View>(R.id.bg)

    private fun highlight(isEnabled: Boolean = true) {
        if (isEnabled) {
            resetButton?.enable()
            bg.visibility = View.VISIBLE
        } else {
            resetButton?.disable()
            bg.visibility = View.INVISIBLE
        }
    }
}