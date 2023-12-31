package com.yaman.multiplemoduleapp

import com.yaman.common_ui_tools.generic_recycler_views.basic.adapters.GenericMultipleViewAdapter
import com.yaman.common_ui_tools.generic_recycler_views.basic.model.RecyclerViewData
import com.yaman.library_tools.app_utils.generic_recycler_view.recycler_view_homogenous.view_holders.BaseViewHolder

class MultiViewRV : GenericMultipleViewAdapter<DataModel>(
    mutableListOf(
        RecyclerViewData(R.layout.image_item, 100),
        RecyclerViewData(R.layout.video_item, 200),
        RecyclerViewData(R.layout.text_item, 300)
    )
) {
    override fun onBindViewHold(holder: BaseViewHolder<DataModel>, position: Int, viewType: Int) {
        /*if (getItem(position).contentType.equals("image",true)) {
            holder as
        } else if(getItem(position).contentType.equals("video",true)){

        } else{

        }*/
    }

    override fun getViewType(position: Int): Int {
        return if (getItem(position).contentType.equals("image",true)) {
            100
        } else if(getItem(position).contentType.equals("video",true)){
            200
        } else{
            300
        }
    }


}

data class DataModel(val contentType: String, val data: String)
