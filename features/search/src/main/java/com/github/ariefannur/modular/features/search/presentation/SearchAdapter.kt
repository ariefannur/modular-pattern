package com.github.ariefannur.modular.features.search.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.ariefannur.modular.core.extension.displayAvatar
import com.github.ariefannur.modular.core.extension.gone
import com.github.ariefannur.modular.core.extension.visible
import com.github.ariefannur.modular.features.search.databinding.ItemSearchBinding
import com.github.ariefannur.modular.features.search.domain.User

class SearchAdapter(val users: List<User>): RecyclerView.Adapter<SearchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.setValue(users[position])
    }

    override fun getItemCount(): Int = users.size
}

class SearchViewHolder(private val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root) {
    fun setValue(user: User) {
       with(binding) {
           tvName.text = user.name
           tvUsername.text = "@${user.username}"
           tvAddress.text = user.address
           tvEmail.text = user.email
           imgAvatar.displayAvatar(user.avatar)
           if (user.description.isEmpty()) {
               tvDesc.gone()
           } else {
               tvDesc.visible()
               tvDesc.text = user.description
           }
       }
    }


}