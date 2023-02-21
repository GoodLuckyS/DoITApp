package com.goodluckys.daily.presentation.adapter

import android.content.Context
import android.widget.ArrayAdapter
import com.goodluckys.daily.R
import com.goodluckys.daily.domain.category.Category
import com.goodluckys.daily.presentation.entity.CategoryUI

class DropDownAdapter(
    context: Context,
    private val res: Int = R.layout.soap,
    private val tvId : Int = R.id.superSoap,
    private val data: List<CategoryUI> = mutableListOf(),
) :
    ArrayAdapter<CategoryUI>(
        context,
        res,
        tvId,
        data
    ) {
}