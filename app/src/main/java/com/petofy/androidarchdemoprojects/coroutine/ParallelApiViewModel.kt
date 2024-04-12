package com.petofy.androidarchdemoprojects.coroutine

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aman.kotlinapi.model.CartCountResponse
import com.cynoteck.kotlinapi.models.d2v.ProductListResponse
import com.cynoteck.kotlinapi.models.d2v.ProductListResponseData
import com.petofy.androidarchdemoprojects.utils.UiState
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch

class ParallelApiViewModel : ViewModel() {
    val api = KotlinNetworkClient.publicApi
    val authApi = KotlinNetworkClient.authApi

    private val _productList =
        MutableStateFlow<UiState<List<ProductListResponseData>>>(UiState.Loading)
    val productList: StateFlow<UiState<List<ProductListResponseData>>> = _productList

    private val _cartCount = MutableStateFlow<Int?>(null)
    val cartCount: StateFlow<Int?> = _cartCount


    private val _isApiCalling = MutableStateFlow<Boolean>(false)
    val isApiCalling: StateFlow<Boolean> = _isApiCalling

    fun fetchParrelNetworkcallViaZipOperator() {    // Zip operator is good only if 2 API returns same Data type whom we can combine & return a new flow which then will be collect{ }
                                                    // working like actual zip (https://www.geeksforgeeks.org/kotlin-flow-zip-operator-for-multiple-parallel-network-calls/)
        viewModelScope.launch {
            val flow1 = flow<ProductListResponse> {
                val response = authApi.getD2VProductList()
                emit(response.body()!!)
            }
            val flow2 = flow<CartCountResponse> {
                delay(5000)
                val response = authApi.getCartCount()
                emit(response.body()!!)
            }

            flow1.zip(flow2) { productList, cartCount ->
                _productList.value = UiState.Success(productList.data!!)
                _cartCount.value = cartCount?.data

                return@zip true
            }
                .catch { e ->
                    _productList.value = UiState.Error(e.toString())
                    Log.e(CoroutineHomeActivity.TAG, "some error occur")
                }.collect {
                    _isApiCalling.value = true
                }
        }
    }


    fun fetchParrelNetworkcallViaAsync() {
        viewModelScope.launch {
            val job1 = async {
                flow<ProductListResponse> {
                    val response = authApi.getD2VProductList()
                    emit(response.body()!!)
                }.catch { e ->
                    _productList.value = UiState.Error(e.toString())
                    Log.e(CoroutineHomeActivity.TAG, "some error occur")
                }.collect {
                    Log.d(CoroutineHomeActivity.TAG, "got the DATA: ${it.data}")
                    _productList.value = UiState.Success(it.data!!)
                }
            }

            val job2 = async {
                Log.d(CoroutineHomeActivity.TAG, "start 5sec delay:::: ")
                delay(5000)
                Log.d(CoroutineHomeActivity.TAG, "delay Stopped!!!")

                flow<CartCountResponse> {
                    val response = authApi.getCartCount()
                    emit(response.body()!!)
                }.catch {
                    Log.e(CoroutineHomeActivity.TAG, "some error occur")
                }.collect {
                    _cartCount.value = it?.data
                }
            }

            try {
                job1.await()
                job2.await()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isApiCalling.value = true
            }

        }


    }
}