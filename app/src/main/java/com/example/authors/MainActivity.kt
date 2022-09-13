package com.example.authors

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.topalbums.Adapters.ListAdapter
import com.example.topalbums.ViewModels.AuthorVM

class MainActivity : AppCompatActivity() {

    private lateinit var authorVMCallback: ViewModelCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val authorVM: AuthorVM by viewModels()
        authorVMCallback = authorVM.getVMCallback()
        authorVM.authorItems.observe(this, Observer {
            val adapter = ListAdapter(it, this)
            findViewById<RecyclerView>(R.id.author_list).adapter = adapter
        })
    }

    interface ViewModelCallbacks {
        fun isActive(activityActive: Boolean)
        fun retryRequest()
    }
}