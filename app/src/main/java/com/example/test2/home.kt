package com.example.test2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class home : AppCompatActivity(), CoroutineScope, EditClickListener, DeleteClickListener{

    private lateinit var rvMessageList: RecyclerView
    private lateinit var ivAdd: ImageView
    private lateinit var contactAdapter: ContactsAdapter
    private var job = Job()

    override val coroutineContext : CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Apptheme_NormalCustomer)
        setContentView(R.layout.activity_home)

        val txtName = findViewById<TextView>(R.id.welcome)
        val kuser = intent.extras?.getParcelable<User>("User")

        txtName.text = "Hello " + kuser?.Name

        ivAdd = findViewById(R.id.ivAdd)

        initializeList()

        ivAdd.setOnClickListener {
            addContact()
        }

    }

    private fun addContact(){
        val contacts = Contacts(name = "Fahad Ali", mobileNumber = "+92-345-2992002")

        launch {
            val id = AppDatabase.getDatabase(this@home).contactDAO().insertOrUpdate(contacts)
            refreshContacts()
            showMessage("Contact added -> Id = $id")
        }

    }

    private fun initializeList(){
        launch {
            val list = AppDatabase.getDatabase(this@home).contactDAO().getAllContacts()
            renderList(list)
        }
    }

    private fun renderList(list:List<Contacts>){
        rvMessageList = findViewById(R.id.recyclerview)
        contactAdapter = ContactsAdapter(list, this, this)
        rvMessageList.adapter = contactAdapter
    }

    private fun refreshContacts(){
        launch {
            val list = AppDatabase.getDatabase(this@home).contactDAO().getAllContacts()
            showList(list)
        }
    }

    private fun showList(list:List<Contacts>){
        contactAdapter.data = list
        contactAdapter.notifyDataSetChanged()
    }

    private fun showMessage(message: String){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }

    override fun onDeleteClicked(contacts: Contacts) {
        launch {
            AppDatabase.getDatabase(this@home).contactDAO().delete(contacts)
            refreshContacts()
        }
    }

    override fun onEditClicked(contacts: Contacts) {
        contacts.name = "Jamal Ali"
        contacts.mobileNumber = "+92-346-2992066"

        launch {
            val id = AppDatabase.getDatabase(this@home).contactDAO().insertOrUpdate(contacts)
            showMessage("Contact updated -> Id = $id")
            refreshContacts()
        }
    }
}