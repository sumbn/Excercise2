package com.example.roomex2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomex2.R
import com.example.roomex2.databinding.ItemContactBinding
import com.example.roomex2.model.Contact

class AdapterRcvContact : RecyclerView.Adapter<AdapterRcvContact.ContactHolder>() {

    private var list: MutableList<Contact>? = null
    private lateinit var onclick: (Contact) -> Unit
    fun setOnclick(onclick: (Contact) -> Unit) {
        this.onclick = onclick
    }

    fun setList(list: MutableList<Contact>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class ContactHolder(val mBinding: ItemContactBinding) : RecyclerView.ViewHolder(mBinding.root) {
        fun bind(contact: Contact) {
            mBinding.username.text = contact.username
            mBinding.name.text = contact.name
            mBinding.email.text = contact.email
            mBinding.tv.text = contact.name?.first().toString()
            mBinding.ln.setOnClickListener {
                onclick(contact)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val mBinding: ItemContactBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_contact,
            parent, false
        )
        return ContactHolder(mBinding)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        val contact = list?.get(position)
        if(contact != null) {
            holder.bind(contact)
        }
    }

    override fun getItemCount(): Int {
        if (list == null) {
            return 0
        } else {
            return list!!.size
        }
    }
}