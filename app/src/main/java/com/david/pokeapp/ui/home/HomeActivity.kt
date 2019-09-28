package com.david.pokeapp.ui.home

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.david.pokeapp.R
import com.david.pokeapp.base.BaseActivity
import com.david.pokeapp.viewmodel.HomeViewModel
import com.david.pokeapp.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity(), SearchView.OnQueryTextListener {
    private val TAG = HomeActivity::class.java.simpleName

    private val searchViewModel : SearchViewModel by viewModel()
    private val homeViewModel : HomeViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.activity_home

    override fun initView(savedInstanceState: Bundle?) {
        val host = nav_host_fragment as NavHostFragment? ?: return
        val navController = host.navController
        setupActionBarWithNavController(navController)
    }

    override fun initObservers() {
        searchViewModel.showSearch.observe(this, Observer {
            setMenuItemVisibility(R.id.search, it)
        })
    }
    override fun destroyView() {
        searchViewModel.showSearch.removeObservers(this)
    }

    private fun setMenuItemVisibility(menuId: Int, visible: Boolean) {
        val item = toolbar.menu.findItem(menuId)
        if (item != null) {
            item.isVisible = visible
        } else {
            Log.w(TAG, "Menu id does not exist: $menuId")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actionbar_tools, menu)
        setMenuItemVisibility(R.id.search, this.searchViewModel.showSearch.value!!)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.search -> {
                val searchItem = getSearchMenuItem()
                val searchView = getSearchView(getSearchMenuItem())
                searchItem.expandActionView()
                searchView.setOnQueryTextListener(this)
                val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
                val componentName = ComponentName(this, HomeActivity::class.java)
                searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getSearchMenuItem(): MenuItem {
        return toolbar.menu.findItem(R.id.search)
    }

    private fun getSearchView(searchItem: MenuItem): SearchView {
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = resources.getString(R.string.search_hint)
        return searchView
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        this.searchViewModel.doSearch(newText, homeViewModel)
        return false
    }
    override fun onBackPressed() {
        super.onBackPressed()
        supportStartPostponedEnterTransition()
    }
}