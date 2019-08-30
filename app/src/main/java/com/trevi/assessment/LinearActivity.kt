package com.trevi.assessment

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.trevi.assessment.InputActivity.Companion.PARA_COL_NUMBER
import com.trevi.assessment.InputActivity.Companion.PARA_ROW_NUMBER
import kotlinx.android.synthetic.main.activity_linear.*

class LinearActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linear)

        intent?.run {
            grid = Grid(content)
                .apply {
                    create(
                        getIntExtra(PARA_ROW_NUMBER, 1),
                        getIntExtra(PARA_COL_NUMBER, 1)
                    )
                }
            showRandom(10)
        }
    }

    private var grid: Grid? = null
    private fun showRandom(interval: Int) {
        grid?.showRandom()
        Handler().postDelayed({
            showRandom(interval)
        }, interval * 1000L)
    }
}
