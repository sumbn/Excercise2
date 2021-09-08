package com.example.roomex2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomex2.R
import com.example.roomex2.adapter.AdapterRcvContact
import com.example.roomex2.base.BaseApplication
import com.example.roomex2.databinding.FrmContactBinding
import com.example.roomex2.model.Contact
import com.example.roomex2.network.RetrofitClient
import com.example.roomex2.utils.CustomDialog
import com.example.roomex2.viewModel.ContactViewmodel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FrmContact : Fragment() {
    private val contactViewmodel: ContactViewmodel by lazy {
        ContactViewmodel((activity?.application as BaseApplication).repository)
    }
    private var frmContactBinding: FrmContactBinding? = null
    private val mutableList = mutableListOf<Contact>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        frmContactBinding =
            DataBindingUtil.inflate(inflater, R.layout.frm_contact, container, false)
        return frmContactBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        callApi()
        initController()


    }

    private fun callApi() {
        if (mutableList.size == 0) {
            contactViewmodel.getApi.observe(viewLifecycleOwner){
                contactViewmodel.insertList(it)
            }
        }
    }

    private fun initController() {
        val adapterRcvContact = AdapterRcvContact()
        adapterRcvContact?.setOnclick(onclick)
        adapterRcvContact?.setList(mutableList)
        frmContactBinding?.rcvContact?.adapter = adapterRcvContact
        frmContactBinding?.rcvContact?.layoutManager = LinearLayoutManager(context)
        contactViewmodel.allContact.observe(viewLifecycleOwner) {
            mutableList.addAll(it)
            adapterRcvContact?.setList(mutableList)
        }

    }

    val onclick: (contact: Contact) -> Unit = {
        CustomDialog(it, contactViewmodel).show(requireActivity().supportFragmentManager, "A")
    }

    override fun onDestroy() {
        frmContactBinding = null
        super.onDestroy()
    }


}