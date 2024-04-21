package com.yaman.multiplemoduleapp

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yaman.multiplemoduleapp.databinding.TextItemBinding
import com.yaman.pdf_viewer.ui.PdfViewerActivity
import com.yaman.recycler_view.generic_recycler_views.core.adapters.GenericAdapter
import com.yaman.recycler_view.generic_recycler_views.core.adapters.GenericAdapterPagination
import com.yaman.recycler_view.generic_recycler_views.core.view_holders.BaseViewHolder
import com.yaman.runtime_permissions.coroutine_permissions.PermissionManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var genericAdapterPagination: GenericAdapterPagination<String>
    private lateinit var genericAdapter: GenericAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
//            demoPdfViewer()
//            demoRuntimePermissions()
//            demoRecyclerViewLongList()
            demoRecyclerView()
        }
    }

    private fun demoRecyclerView() {
        val rv = findViewById<RecyclerView>(R.id.recyclerView)
        rv.visibility = View.VISIBLE

        val demoArr = ArrayList<String>()

        for (i in 1..100) {
            demoArr.add("Date ${i}")
        }

        Log.e("TAG", "demoRecyclerViewLongList: $demoArr")

        val layoutManager = LinearLayoutManager(this) // or GridLayoutManager(this, spanCount)
        rv.layoutManager = layoutManager

        genericAdapter = object : GenericAdapter<String>(R.layout.text_item){
            override fun onBindViewHold(holder: BaseViewHolder<String>, position: Int) {
                val binding = holder.binding as TextItemBinding
                binding.textId.text = getItem(position)
                Log.e("TAG", "onBindViewHold: $position - ${getItem(position)}")
            }

        }

        rv.adapter = genericAdapter

        genericAdapter.updateList(demoArr)
    }

    private fun demoRuntimePermissions() {
        CoroutineScope(Dispatchers.IO).launch {
            PermissionManager.requestPermissions(
                this@MainActivity,
                123,
                Manifest.permission.CAMERA,
                Manifest.permission.BLUETOOTH
            )
        }
    }

    private fun demoPdfViewer() {
        startActivity(Intent(this, PdfViewerActivity::class.java))
    }

    private fun demoRecyclerViewLongList() {
        val rv = findViewById<RecyclerView>(R.id.recyclerView)
        rv.visibility = View.VISIBLE

        val demoArr = ArrayList<String>()

        for (i in 1..1000) {
            demoArr.add("Date ${i}")
        }

        Log.e("TAG", "demoRecyclerViewLongList: $demoArr")

        val layoutManager = LinearLayoutManager(this) // or GridLayoutManager(this, spanCount)
        rv.layoutManager = layoutManager

        val pageSize = 50 // Number of items per page
        genericAdapterPagination = object : GenericAdapterPagination<String>(
            R.layout.text_item, {
                // Load more data
                genericAdapterPagination.addMoreList(demoArr, pageSize, it)
                Log.e("TAG", "demoRecyclerViewLongList: addMoreList called")
            }) {

            override fun onBindViewHold(holder: BaseViewHolder<String>, position: Int) {
                val binding = holder.binding as TextItemBinding
                binding.textId.text = getItem(position)
                Log.e("TAG", "onBindViewHold: $position - ${getItem(position)}")
            }
        }

        rv.adapter = genericAdapterPagination

        genericAdapterPagination.setRecyclerViewScrollListener(rv)

        // Load initial data (first page)
        genericAdapterPagination.updateInitialData(demoArr, pageSize)
    }

}




