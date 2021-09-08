package com.example.roomex2.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.roomex2.R
import com.example.roomex2.dao.ContactDao
import com.example.roomex2.model.Contact
import com.example.roomex2.viewModel.ContactViewmodel

class CustomDialog(private var contact: Contact, val contactViewmodel: ContactViewmodel) : DialogFragment()  {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;
            val view = inflater.inflate(R.layout.custom_dialog_item, null)
            val editTextName: EditText = view.findViewById(R.id.name)
            editTextName.setText(contact.name)
            val editTextEmail: EditText = view.findViewById(R.id.email)
            editTextEmail.setText(contact.email)
            val editTextUsername: EditText = view.findViewById(R.id.username)
            editTextUsername.setText(contact.username)


            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(view)
                // Add action buttons
                .setPositiveButton("Edit",
                    DialogInterface.OnClickListener { dialog, id ->
                        contact.username = editTextUsername.text.toString()
                        contact.name = editTextName.text.toString()
                        contact.email = editTextEmail.text.toString()
                        contactViewmodel.updateContact(contact)
                    })
                .setNegativeButton("Exit",
                    DialogInterface.OnClickListener { dialog, id ->
                        getDialog()?.cancel()
                    })
                .setTitle("Changing item name")
                .setMessage("Are you sure you want to edit the item?")
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }


}