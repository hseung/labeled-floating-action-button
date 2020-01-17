package com.buzzvil

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.buzzvil.labeledfab.LabelPosition
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var isDialFAB = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            if (isDialFAB) {
                fab.setImageResourceWithLabel(
                    android.R.drawable.ic_dialog_email,
                    "Mail",
                    dpToPx(18f),
                    Color.WHITE
                )
                fab.labelPosition = LabelPosition.BOTTOM
                fab.labelTextPadding = 0

                this.isDialFAB = false
            } else {
                fab.setImageResourceWithLabel(
                    android.R.drawable.ic_dialog_dialer,
                    "Dialer",
                    dpToPx(18f),
                    Color.LTGRAY
                )
                fab.labelPosition = LabelPosition.TOP
                fab.labelTextPadding = dpToPx(5f)

                this.isDialFAB = true
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun dpToPx(dpValue: Float): Int {
        val scale = resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}
