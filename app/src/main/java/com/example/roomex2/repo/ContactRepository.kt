package com.example.roomex2.repo

import androidx.lifecycle.MutableLiveData
import com.example.roomex2.dao.ContactDao
import com.example.roomex2.model.Contact
import com.example.roomex2.network.RetrofitClient
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContactRepository(private val contactDao: ContactDao) {
    val allContact: Flow<List<Contact>> = contactDao.getAll()

    fun insertList(contacts: List<Contact>) {
        contactDao.insertAll(contacts)
    }
    fun insertItem(contact: Contact){
        contactDao.insert(contact)
    }
    fun updateContact(contact: Contact){
        contactDao.updateContact(contact)
    }

    fun getDataApi() : MutableLiveData<List<Contact>>{
        val list : MutableLiveData<List<Contact>> = MutableLiveData<List<Contact>>()
        val service = RetrofitClient.getContactApi()
        service.listReponse()?.enqueue(object : Callback<List<Contact?>?> {
            override fun onResponse(
                call: Call<List<Contact?>?>,
                response: Response<List<Contact?>?>
            ) {
                list.postValue(((response.body() as MutableList<Contact>)))
            }

            override fun onFailure(call: Call<List<Contact?>?>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return list
    }
}