package com.websarva.wings.android.menusample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var _menuList: MutableList<MutableMap<String,Any>> = mutableListOf()
    private val _from = arrayOf("name","price")
    private val _to = intArrayOf(R.id.tvMenuNameRow,R.id.tvMenuPriceRow)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _menuList = createTeishokuList()
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        val adapter = SimpleAdapter(this@MainActivity,_menuList,R.layout.row,_from,_to)
        lvMenu.adapter = adapter
        lvMenu.onItemClickListener = ListItemClickListener()
        registerForContextMenu(lvMenu)
    }

    private fun order(menu: MutableMap<String,Any>){
        val menuName = menu["name"] as String
        val menuPrice = menu["price"] as Int
        val intent2MenuThanks = Intent(this@MainActivity,MenuThanksActivity::class.java)
        intent2MenuThanks.putExtra("menuName",menuName)
        intent2MenuThanks.putExtra("menuPrice","${menuPrice}円")
        startActivity(intent2MenuThanks)
    }

    private fun createTeishokuList(): MutableList<MutableMap<String, Any>>{
        val menuList: MutableList<MutableMap<String,Any>> = mutableListOf()
        var menu = mutableMapOf<String,Any>("name" to "唐揚げ定食","price" to 800,"desc" to "若鳥のから揚げにサラダ、ごはんとお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf<String,Any>("name" to "ハンバーグ定食","price" to 850,"desc" to "手ごねハンバーグにサラダ、ごはんとお味噌汁が付きます。")
        menuList.add(menu)

        return menuList
    }

    private fun createCurryList(): MutableList<MutableMap<String,Any>>{
        val menuList: MutableList<MutableMap<String,Any>> = mutableListOf()
        var menu = mutableMapOf<String,Any>("name" to "ビーフカレー","price" to 520,"desc" to "特性スパイスをきかせた国産ビーフ100％のカレーです。")
        menuList.add(menu)
        menu = mutableMapOf<String,Any>("name" to "ポークカレー","price" to 420,"desc" to "特性スパイスをきかせた国産ポーク100％のカレーです。")
        menuList.add(menu)

        return menuList

    }

    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            val item = parent.getItemAtPosition(position) as MutableMap<String, Any>
            order(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_options_menu_list,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var returnVal = true
        when(item.itemId){
            R.id.menuListOptionTeishoku ->
                _menuList = createTeishokuList()
            R.id.menuListOptionCurry ->
                _menuList = createCurryList()
            else ->
                returnVal = super.onOptionsItemSelected(item)
        }
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        val adapter = SimpleAdapter(this@MainActivity,_menuList,R.layout.row,_from,_to)
        lvMenu.adapter = adapter
        return returnVal
    }

    override fun onCreateContextMenu(menu: ContextMenu, view: View, menuInfo: ContextMenu.ContextMenuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo)
        menuInflater.inflate(R.menu.menu_context_menu_list,menu)
        menu.setHeaderTitle(R.string.menu_list_context_header)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var returnVal = true
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val listPosition = info.position
        val menu = _menuList[listPosition]

        when(item.itemId){
            R.id.menuListContextDesc -> {
                val desc = menu["desc"] as String
                Toast.makeText(this@MainActivity,desc,Toast.LENGTH_LONG).show()
            }
            R.id.menuListContextOrder ->
                order(menu)
            else ->
                return super.onContextItemSelected(item)
        }
        return returnVal
    }
}