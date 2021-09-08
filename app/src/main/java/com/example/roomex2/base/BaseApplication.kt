package com.example.roomex2.base

import android.app.Application
import com.example.roomex2.dao.ContactDatabase
import com.example.roomex2.repo.ContactRepository

class BaseApplication : Application() {
    val contactDatabase by lazy { ContactDatabase.getDatabase(this) }
    val repository by lazy { ContactRepository(contactDatabase.contactDao()) }
}