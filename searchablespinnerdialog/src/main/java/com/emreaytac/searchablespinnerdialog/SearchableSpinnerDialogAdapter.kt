package com.emreaytac.searchablespinnerdialog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale


internal class SearchableSpinnerDialogAdapter<G> (private val ctx: Context,
                                                  private val separatorColor: Int,
                                                  private val textColor: Int,
                                                  private val itemsList: MutableList<SpinnerTuple<G>>, private val onItemClicked: (SpinnerTuple<G>) -> Unit) :
    RecyclerView.Adapter<SearchableSpinnerDialogAdapter<G>.SingleItemRowHolder>(), Filterable {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): SingleItemRowHolder {
        val v: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_item, viewGroup, false)
        return SingleItemRowHolder(v)
    }

    override fun onBindViewHolder(holder: SingleItemRowHolder, i: Int) {
        holder.bind(itemsList[i])
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    inner class SingleItemRowHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var itemText: TextView
        private var itemDivider: View
        private var itemLl: LinearLayout

        init {
            itemText = view.findViewById<View>(R.id.item_tv) as TextView
            itemDivider = view.findViewById(R.id.item_divider) as View
            itemLl = view.findViewById(R.id.item_ll) as LinearLayout
        }

        fun bind(res: SpinnerTuple<G>){
            itemText.text = res.text
            itemLl.setOnClickListener {
                onItemClicked(res)
            }
            itemText.setTextColor(ResourcesCompat.getColor(ctx.resources, textColor, null))
            itemDivider.setBackgroundColor(ResourcesCompat.getColor(ctx.resources, separatorColor, null))
        }

    }

    override fun getFilter(): Filter {
        return resFilter
    }

    private val resFilter: Filter = object : Filter() {
        override fun performFiltering(charSequence: CharSequence): FilterResults {
            val resFiltered: MutableList<Any> = ArrayList()
            if (charSequence.isEmpty()) {
                resFiltered.addAll(itemsList)
            } else {
                val filterPattern =
                    charSequence.toString().lowercase(Locale.getDefault()).trim { it <= ' ' }
                for (item in itemsList) {
                    if (item.text.lowercase(Locale.ROOT).contains(filterPattern)) {
                        resFiltered.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = resFiltered
            return results
        }

        @Suppress("UNCHECKED_CAST")
        override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
            itemsList.clear()
            itemsList.addAll(filterResults.values as MutableList<SpinnerTuple<G>>)
            notifyDataSetChanged()
            //res.notifyAll();
        }
    }
}
