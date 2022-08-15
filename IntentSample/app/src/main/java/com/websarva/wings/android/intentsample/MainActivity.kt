package com.websarva.wings.android.intentsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewParent
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        val menuList: MutableList<MutableMap<String, String>> = mutableListOf()
        var menu = mutableMapOf("name" to "唐揚げ定食" , "price" to "800円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ハンバーグ定食" , "price" to "850円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "生姜焼き定食" , "price" to "850円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ステーキ定食" , "price" to "1000円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "野菜炒め定食" , "price" to "750円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "とんかつ定食" , "price" to "900円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ミンチかつ定食" , "price" to "850円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "チキンかつ定食" , "price" to "900円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "コロッケ定食" , "price" to "850円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "回鍋肉定食" , "price" to "750円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "麻婆豆腐定食" , "price" to "800円")
        menuList.add(menu)
        menu = mutableMapOf("name" to "青椒肉絲定食" , "price" to "850円")
        menuList.add(menu)

        val from = arrayOf("name","price")
        val to = intArrayOf(android.R.id.text1,android.R.id.text2)
        val adapter = SimpleAdapter(this@MainActivity,menuList,android.R.layout.simple_list_item_2,from,to)
        lvMenu.adapter = adapter

        lvMenu.onItemClickListener = ListItemClickListener()
    }

    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            val item = parent.getItemAtPosition(position) as MutableMap<String, String>
            val menuName = item["name"]
            val menuPrice = item["price"]
            val intent2MenuThanks = Intent(this@MainActivity,MenuThanksActivity::class.java)
            intent2MenuThanks.putExtra("menuName",menuName)
            intent2MenuThanks.putExtra("menuPrice",menuPrice)
            startActivity(intent2MenuThanks)
        }
    }
}