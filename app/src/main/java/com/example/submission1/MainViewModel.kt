package com.example.submission1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Response

class MainViewModel : ViewModel() {


    private val _githubresponse = MutableLiveData<GithubResponse>()
    val githubresponse: LiveData<GithubResponse> = _githubresponse


    private val _itemsitem = MutableLiveData<List<ItemsItem>>()
    val itemsitem: LiveData<List<ItemsItem>> = _itemsitem




    private val _login = MutableLiveData<ItemsItem>()
    val login: LiveData<ItemsItem> = _login





    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val TAG = "MainViewModel"
        private const val URL = "a"
    }
    init{
        findUser()
    }

    private fun findUser() {
        _isLoading.value = true
        val user = ApiConfig.getApiService().getGithub(URL)
        user.enqueue(object : retrofit2.Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                     _itemsitem.value = response.body()?.itema

                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
}
}