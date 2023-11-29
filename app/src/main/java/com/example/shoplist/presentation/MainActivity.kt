package com.example.shoplist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.shoplist.R
import com.example.shoplist.domain.ShopItem

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var LLshopList: LinearLayout




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LLshopList = findViewById(R.id.ll_shop_list)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
        showList(it)
        }


    }
    private fun showList(list: List<ShopItem>){
        LLshopList.removeAllViews()
    for (shopItem in list){
        val layoutId = if (shopItem.enabled) {
            R.layout.item_shop_enabled
        } else {
            R.layout.item_shop_disabled
        }
        val view = LayoutInflater.from(this).inflate(layoutId, LLshopList, false)
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)

        tvName.text = shopItem.name
        tvCount.text = shopItem.count.toString()
        view.setOnLongClickListener{
            viewModel.changeEnableState(shopItem)
            true
        }
        LLshopList.addView(view)
    }

    }


}