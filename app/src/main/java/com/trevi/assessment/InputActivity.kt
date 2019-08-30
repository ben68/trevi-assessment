package com.trevi.assessment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_input.*

class InputActivity : AppCompatActivity() {

    companion object {
        const val PARA_ROW_NUMBER = "row number parameter"
        const val PARA_COL_NUMBER = "column number parameter"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        buttonSubmit.setOnClickListener {
            var rowNumber = 0
            inputRow.editText?.editableText.let {
                if (!TextUtils.isEmpty(it))
                    rowNumber = it.toString().toInt()
            }
            var colNumber = 0
            inputColumn.editText?.editableText.let {
                if (!TextUtils.isEmpty(it))
                    colNumber = inputColumn.editText?.editableText.toString().toInt()
            }
            if (rowNumber == 0) {
                inputRow.error = "must be greater than 0"
            }
            if (colNumber == 0) {
                inputColumn.error = "must be greater than 0"
            }
            if (colNumber > 0 && rowNumber > 0) {
                setResult(
                    Activity.RESULT_OK,
                    Intent().run {
                        putExtra(PARA_ROW_NUMBER, rowNumber)
                        putExtra(PARA_COL_NUMBER, colNumber)
                    }
                )
                finish()
            }
        }
    }
}
