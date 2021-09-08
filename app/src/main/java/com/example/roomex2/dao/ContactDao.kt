package com.example.roomex2.dao

import androidx.room.*
import com.example.roomex2.model.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Query("SELECT * FROM contact")
    fun getAll(): Flow<List<Contact>>

    @Query("SELECT * FROM contact WHERE name IN (:name)")
    fun loadAllByIds(name: String): List<Contact>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(contacts: List<Contact>)

    @Delete
    fun delete(contact: Contact)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(contact: Contact)

    @Update
    fun updateContact(contact: Contact)
}