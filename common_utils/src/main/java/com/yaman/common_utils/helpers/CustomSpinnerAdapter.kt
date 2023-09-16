package com.yaman.common_utils.helpers

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import com.google.android.material.textview.MaterialTextView

class CustomSpinnerAdapter<T>(context: Context, @LayoutRes val spinnerLayout: Int) :
    ArrayAdapter<T>(context, spinnerLayout) {

    private val values = ArrayList<T>()
    private var isFirst = true
    private var textColor: Int? = null
    private var bgColor : Int? = null

    override fun getCount(): Int {
        return values.size
    }

    override fun getItem(position: Int): T {
        return values[position]
    }

    override fun isEnabled(position: Int): Boolean {
        return if (isFirst) position != 0 else super.isEnabled(position)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent)
        Log.e("view: ", "getDropDownView: $view" )
        Log.e("parent: ", "getDropDownView: $parent" )
        if(isFirst){
            if(position == 0){
                val textview = view as MaterialTextView
                if(textColor != null) textview.setBackgroundColor(bgColor!!) else textview.setBackgroundColor(Color.GRAY)
                if(textColor != null) textview.setTextColor(textColor!!) else textview.setTextColor(Color.WHITE)

            }
        }
        return view
    }

    fun updateAdapter(values: ArrayList<T>) {
        this.values.clear()
        this.values.addAll(values)
        notifyDataSetChanged()
    }

    fun addDataAdapter(values: ArrayList<T>) {
        this.values.addAll(values)
        notifyDataSetChanged()
    }

    fun setFirstAsTitle(isFirst: Boolean, textColor: Int?, bgColor: Int?) {
        this.isFirst = isFirst
        this.textColor = textColor
        this.bgColor = bgColor
    }

}