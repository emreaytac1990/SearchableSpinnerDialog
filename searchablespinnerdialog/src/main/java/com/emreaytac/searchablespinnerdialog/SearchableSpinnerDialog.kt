package com.emreaytac.searchablespinnerdialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SearchableSpinnerDialog<T> (private val ctx: Context,
                                private val searchList: Pair<List<String>, List<T>>,
                                private val widthRate: Double = 0.8,
                                private val heightRate: Double = 0.8,
                                  private val textColor: Int = R.color.black,
                                  private val separatorColor: Int = R.color.separator,
                                  private val backgroundColor: Int = R.color.background_main,
                                  private val viewsBackgroundColor: Int = R.color.background_second,
                                private val searchHintText: String = "Search..") {

    private var dialog: Dialog? = null

    private val _selectedItem: MutableLiveData<SpinnerTuple<T>> by lazy{ MutableLiveData() }
    val selectedItem: LiveData<SpinnerTuple<T>> = _selectedItem

    private lateinit var searchEditText: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var mainLayout: LinearLayout
    private lateinit var arrayAdapter: SearchableSpinnerDialogAdapter<T>


    init {
        build()
    }

    private fun build(){
        dialog = Dialog(ctx).apply {
            setContentView(R.layout.searchable_spinner_dialog)
            window!!.setLayout(
                ((ctx.resources.displayMetrics.widthPixels * widthRate).toInt()),
                ((ctx.resources.displayMetrics.heightPixels * heightRate).toInt())
            )
            window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        dialog?.let {
            searchEditText = it.findViewById(R.id.edit_text)
            recyclerView = it.findViewById(R.id.list_lv)
            mainLayout = it.findViewById(R.id.spinner_dialog_ll)

            searchEditText.hint = searchHintText
            searchEditText.setBackgroundColor(ResourcesCompat.getColor(ctx.resources, viewsBackgroundColor, null))
            mainLayout.setBackgroundColor(ResourcesCompat.getColor(ctx.resources, backgroundColor, null))

            val list = searchList.concatToList()
            arrayAdapter = SearchableSpinnerDialogAdapter(ctx, separatorColor, textColor, list){ item ->
                _selectedItem.value = item
                it.dismiss()
            }
            recyclerView.adapter = arrayAdapter
            recyclerView.layoutManager = LinearLayoutManager(ctx);

            searchEditText.addTextChangedListener( object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    arrayAdapter.filter.filter(p0)
                }

                override fun afterTextChanged(p0: Editable?) {
                }
            })
        }

    }

    fun openDialog(){
        dialog?.show()
    }

    fun destroyDialog(){
        dialog = null
    }

}
