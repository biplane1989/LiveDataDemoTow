package com.example.livedatademotow

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.livedatademotow.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnClicked {
    val TAG = "001"
    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: NoteAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    fun init() {
        setUpUI()
        initScrollListener()
    }

    fun setUpUI() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        adapter = NoteAdapter(this)
        rv_main.layoutManager = GridLayoutManager(this, 3, RecyclerView.VERTICAL, false)
        rv_main.setHasFixedSize(true)
        rv_main.adapter = adapter

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)


        viewModel.getData().observe(this, Observer { listItemViewModels ->
            adapter.submitList(ArrayList(listItemViewModels))
        })

        binding.lifecycleOwner = this
        binding.main = viewModel

    }

    override fun onClicked(position: Int) {
        Log.d(TAG, "onClicked: " + position)
        viewModel.setStatus(position)
//        adapter.notifyItemChanged(position)
    }

    fun initScrollListener() {
        binding.rvMain.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val gridLayoutManager: GridLayoutManager = binding.rvMain.layoutManager as GridLayoutManager

                if (gridLayoutManager.findLastCompletelyVisibleItemPosition() == viewModel.getListSize() - 1) {
                    Log.d(TAG, "onScrolled: list size: " + viewModel.getListSize())
                    viewModel.loadMore()
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }

}


