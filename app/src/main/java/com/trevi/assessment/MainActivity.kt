package com.trevi.assessment

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.button.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE_INPUT = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            startActivityForResult(
                Intent(this, InputActivity::class.java),
                REQUEST_CODE_INPUT
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQUEST_CODE_INPUT -> {
                startActivity(
                    Intent(
                        this,
                        LinearActivity::class.java
                    ).apply {
                        putExtra(InputActivity.PARA_ROW_NUMBER, data?.getIntExtra(InputActivity.PARA_ROW_NUMBER, 0))
                        putExtra(InputActivity.PARA_COL_NUMBER, data?.getIntExtra(InputActivity.PARA_COL_NUMBER, 0))
                    }
                )
            }
        }
    }
}
