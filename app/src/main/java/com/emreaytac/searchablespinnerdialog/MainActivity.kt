package com.emreaytac.searchablespinnerdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.test_tv)

        tv.setOnClickListener {

            val mList = arrayListOf(User("John",12), User("Michael",13), User("Jose",36), User("Florent",33), User("Sergio",22), User("Amanda",24), User("Adele",31), User("Catherine",52), User("Chris",12), User("Josh",35))
            val strList: List<String> = mList.map { i->  i.name }.toList()
            val d = SearchableSpinnerDialog(ctx = this, searchList = Pair(strList, mList), widthRate = 0.8, heightRate = 0.8, separatorColor = R.color.black, searchHintText = "Ara")
            d.openDialog()

            d.selectedItem.observe(this) { v ->
                tv.text = v.text
            }
        }
    }
}

data class User(val name: String, val age: Int)