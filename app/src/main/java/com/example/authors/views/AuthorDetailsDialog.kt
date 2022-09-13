package com.example.authors.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.fragment.app.DialogFragment
import com.example.authors.R
import com.example.delivery.Request.Items.AuthorItem
import com.squareup.picasso.Picasso

class AuthorDetailsDialog (val authorItem: AuthorItem, context: Context): DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context).inflate(R.layout.author_details, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ConstraintLayout>(R.id.author_details_root).children.forEach {
            when(it.id) {
                R.id.author_name -> {
                    if (!authorItem.authorName.isNullOrEmpty()) view.findViewById<TextView>(R.id.author_name)!!
                        .text = authorItem.authorName
                }
                R.id.author_img -> {
                    if (!authorItem.imgUri.isNullOrEmpty()) Picasso.get().load(authorItem.imgUri)
                        .into(view.findViewById<ImageView>(R.id.author_img))
                }
            }
        }
    }
}