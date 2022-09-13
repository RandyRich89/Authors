package com.example.topalbums.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.authors.R
import com.example.authors.views.AuthorDetailsDialog
import com.example.delivery.Request.Items.AuthorItem
import com.squareup.picasso.Picasso

class ListAdapter(val items: List<AuthorItem>, val fragmentManager: FragmentManager, val context: Context): RecyclerView.Adapter<AuthorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.author_list_item, parent)
        return AuthorViewHolder(view)
    }

    override fun onBindViewHolder(holder: AuthorViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.authorName.text = items.get(position).authorName
        Picasso.get().load(items!!.get(position).imgUri)
            .into(holder.authorImg)
        holder.itemView.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                val albumDetailsDialog = AuthorDetailsDialog(items.get(position), context)
                albumDetailsDialog.setStyle(DialogFragment.STYLE_NO_FRAME, R.style.CustomDialog)
                albumDetailsDialog.show(fragmentManager, null)
            }
        })
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class AuthorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val authorName: TextView
    val authorImg: ImageView

    init {
        authorName = view.findViewById(R.id.author_name)
        authorImg = view.findViewById(R.id.author_img)
    }
}


