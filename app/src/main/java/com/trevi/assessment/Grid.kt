package com.trevi.assessment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class Grid(private val root: ViewGroup) {

    class LinearCell(private val view: View) : Column.Cell {

        private val textView by lazy {
            view.findViewById<TextView>(R.id.textView)
        }

        override fun attachToStage(parent: ViewGroup) {
            parent.addView(view)
        }

        override fun showRandom() {
            textView.visibility = View.VISIBLE
        }

        override fun clear() {
            textView.visibility = View.INVISIBLE
        }

    }

    class LinearButton(private val root: ViewGroup) : Column.ResetButton {

        private val button by lazy {
            root.findViewById<Button>(R.id.button)
        }

        override fun attachToStage(parent: ViewGroup) {
            parent.addView(root)
        }

        override fun enable() {
            button.isEnabled = true
        }

        override fun disable() {
            button.isEnabled = false
        }

        override fun setClickListener(listener: View.OnClickListener) {
            button.setOnClickListener(listener)
        }
    }

    private fun createCell(parent: ViewGroup): Column.Cell {
        return LinearCell(
            LayoutInflater.from(root.context).inflate(R.layout.cell, parent, false)
        )
    }

    private fun createColumnRoot(parent: ViewGroup): ViewGroup {
        return LayoutInflater.from(parent.context).inflate(R.layout.column_linear, parent, false) as ViewGroup
    }

    private var row: Int = 0
    private var column: Int = 0

    private val columnList = mutableListOf<Column>()
    fun create(row: Int, column: Int) {
        this.row = row
        this.column = column
        repeat(column) {
            columnList +=
                ColumnImpl(
                    createColumnRoot(root)
                        .also {
                            root.addView(it)
                        }
                ).apply {
                    repeat(row) {
                        add(createCell(container))
                    }
                    setResetButton(
                        LinearButton(
                            LayoutInflater.from(root.context).inflate(
                                R.layout.button,
                                root,
                                false
                            ) as ViewGroup
                        )
                    )
                    clear()
                    attachToStage()
                }
        }
    }

    private var highlightedColumn: Column? = null
    fun showRandom() {
        val x = Random.nextInt(0, column)
        val y = Random.nextInt(0, row)
        highlightedColumn?.clear()
        columnList[x]
            .apply {
                showRandom(y)
                highlightedColumn = this
            }
    }
}