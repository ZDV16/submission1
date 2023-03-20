package com.example.submission1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.submission1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val mainViewModel by viewModels<MainViewModel>()
        mainViewModel.itemsitem.observe(this, { itemsItem ->
            setUserData(ItemsItem)
        })
    }

    private fun setUserData(item: List<ItemsItem>) {
        val listUser = ArrayList<String>()
       for (id in item) {
           listUser.add(
               """
                ${item.login}
                """.trimIndent()
           )
       }
        val adapter = UserAdapter(listUser)
        binding.rvListUser.adapter = adapter
    }
}