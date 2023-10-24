//package com.yaman.paging
//
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//
//class BasePagingSource<V : Any>(
//    private val totalPages: Int? = null,
//    private val block: suspend (Int) -> List<V>
//) : PagingSource<Int, V>() {
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, V> {
//
//        return try {
//            // Start refresh at page 1 if undefined.
//            val page = params.key ?: 1
//            val response = block(page)
//
//            //Success
//            LoadResult.Page(
//                data = response,
//                prevKey = if (page == 1) null else page - 1,
//                nextKey = if (totalPages != null && page == totalPages) null else page + 1
//            )
//        } catch (e: Exception) {
//            // Handle errors in this block and return LoadResult.Error if it is an
//            // expected error (such as a network failure).
//            //Failed
//            LoadResult.Error(e)
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, V>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
//        }
//    }
//
//}