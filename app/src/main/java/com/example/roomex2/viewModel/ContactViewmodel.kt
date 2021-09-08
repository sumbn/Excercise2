package com.example.roomex2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.roomex2.model.Contact
import com.example.roomex2.repo.ContactRepository


class ContactViewmodel(private val contactRepository: ContactRepository) : ViewModel() {
    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allContact: LiveData<List<Contact>> = contactRepository.allContact.asLiveData()
    val getApi: LiveData<List<Contact>> = contactRepository.getDataApi()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(contact: Contact){
        contactRepository.insertItem(contact)
    }
    fun insertList(contacts: List<Contact>){
        contactRepository.insertList(contacts)
    }
    fun updateContact(contact: Contact){
        contactRepository.updateContact(contact)
    }
    fun callApi(){
        contactRepository.getDataApi()
    }

}

