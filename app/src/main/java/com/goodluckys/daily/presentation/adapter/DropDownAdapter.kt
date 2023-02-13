package com.goodluckys.daily.presentation.adapter

import android.content.Context
import android.widget.ArrayAdapter
import com.goodluckys.daily.domain.category.Category

class DropDownAdapter(
    context: Context,
    private val res: Int,
    private val tvId : Int,
    private val data: MutableList<Category> = mutableListOf(),
) :
    ArrayAdapter<Category>(
        context,
        res,
        tvId,
        data
    ) {

    fun addAll(newData: List<Category>) {
        this.data.addAll(newData)
    }
}