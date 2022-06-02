package com.github.ariefannur.modular.detail.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.ariefannur.modular.core.extension.displayAvatar
import com.github.ariefannur.modular.detail.R
import com.github.ariefannur.modular.detail.databinding.ItemHeaderBinding
import com.github.ariefannur.modular.detail.databinding.ItemListBinding
import com.github.ariefannur.modular.detail.domain.DetailUser
import com.github.ariefannur.modular.detail.domain.Repository
import java.text.SimpleDateFormat
import java.util.*

class AdapterListDetail(var list: List<Repository>, var avatar: String): RecyclerView.Adapter<ViewHolderList>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderList {
        val holder = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderList(holder)
    }

    override fun onBindViewHolder(holder: ViewHolderList, position: Int) {
        holder.bind(list[position], avatar)
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_list
    }
}

class ViewHolderList(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(repository: Repository, avatar: String) {
        with(binding) {
            tvRepoName.text = repository.name
            tvRepoDesc.text = repository.description
            tvStar.text = repository.star.toString()
            imgAvatarRepo.displayAvatar(avatar)
            tvUpdate.text = exportDate(repository.updateAt)
        }
    }

    fun exportDate(time: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val date = dateFormat.parse(time) //You will get date object relative to server/client timezone wherever it is parsed
        return printDifference(date)
    }

    fun printDifference(updateAt: Date): String {
        val current = Calendar.getInstance().timeInMillis
        val periodSeconds = (current - updateAt.time) / 1000
        val elapsedDays = periodSeconds / 60 / 60 / 24
        val value = elapsedDays/30
        return if (value > 12) {
            val valueMonth = value/12
            String.format("Updated %d year${if (valueMonth > 1) "s" else ""} ago", valueMonth)
        } else {
            String.format("Updated %d month${if (value > 1) "s" else ""} ago", value)
        }
    }

}