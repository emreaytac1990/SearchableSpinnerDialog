package com.emreaytac.searchablespinnerdialog

data class SpinnerTuple<G>(val text: String, val data: G)

internal fun <G> Pair<List<String>, List<G>>.concatToList(): MutableList<SpinnerTuple<G>>{
    val sList: ArrayList<SpinnerTuple<G>> = ArrayList()
    if(this.first.size == this.second.size){
        this.first.forEachIndexed { index, s ->
            sList.add(SpinnerTuple(s, this.second[index]))
        }
    }
    return sList
}
