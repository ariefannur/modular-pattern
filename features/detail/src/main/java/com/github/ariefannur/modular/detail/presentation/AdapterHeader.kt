package com.github.ariefannur.modular.detail.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.ariefannur.modular.core.extension.displayAvatar
import com.github.ariefannur.modular.detail.R
import com.github.ariefannur.modular.detail.databinding.ItemHeaderBinding
import com.github.ariefannur.modular.detail.domain.DetailUser

class AdapterHeader(var data: DetailUser? = null): RecyclerView.Adapter<ViewHolderHeader>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHeader {
        val holder = ItemHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderHeader(holder)
    }

    override fun onBindViewHolder(holder: ViewHolderHeader, position: Int) {
        holder.bind(data)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_header
    }

    override fun getItemCount(): Int = if (data == null) 0 else 1
}

class ViewHolderHeader(private val binding: ItemHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: DetailUser?) {
        with(binding) {
            tvName.text = data?.name
            tvUsername.text = "@${data?.username}"
            tvDescription.apply {
                text = data?.description
                visibility = if (data?.description.isNullOrEmpty()) View.GONE else View.VISIBLE
            }
            data?.avatar?.let {
                imgAvatarHeader.displayAvatar(it)
            }
            tvEmail.text = data?.email
            tvLocation.text = data?.address
            tvFollower.text = "${data?.follower} Follower"
            tvFollowing.text = "${data?.following} Following"
        }
    }

}