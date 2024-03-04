package com.yaman.pagination//package com.yaman.paging
//
import androidx.paging.Pager
import androidx.paging.PagingConfig

class Pagination {

    fun <V : Any> createPager(
        totalPages: Int? = null,
        pageSize: Int = 30,//default size
        maxSize: Int? = PagingConfig.MAX_SIZE_UNBOUNDED,
        enablePlaceholders: Boolean = false,
        block: suspend (Int) -> List<V>
    ): Pager<Int, V> = Pager(
        config = PagingConfig(
            enablePlaceholders = enablePlaceholders,
            pageSize = pageSize,
            maxSize = maxSize!!,
        ),
        pagingSourceFactory = { BasePagingSource(totalPages, block) }
    )

}