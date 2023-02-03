package com.example.register


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.register.databinding.ActivityRegisterBinding
import com.google.android.material.snackbar.Snackbar

class RegisterActivity : AppCompatActivity(), OnClickListener  {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerFinishAdapter: RegisterAdapter
    private lateinit var database: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        database = DatabaseHelper(this)

        registerFinishAdapter = RegisterAdapter(mutableListOf(), this)
        binding.rvFinishCard.apply {
            layoutManager = LinearLayoutManager(this@RegisterActivity)
            adapter = registerFinishAdapter
        }

    }
    override fun onStart() {
        super.onStart()
        getData()
    }

    private fun getData(){
        val data = database.getAllRegister()

        data.forEach { note ->
            addNoteAuto(note)
        }
    }

    private fun addNoteAuto(register: Register) {
        this.registerFinishAdapter.add(register)
    }



    override fun onLongClick(register: Register, currentAdapter:RegisterAdapter) {
        val builder = AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_title))
            .setPositiveButton(getString(R.string.dialog_ok), { dialogInterface, i ->
                if(database.deleteRegister(register)){
                    currentAdapter.remove(register)
                    showMessage(R.string.message_write_db_success)
                }else{
                    showMessage(R.string.message_write_db_error)
                }
            })
            .setNegativeButton(getString(R.string.dialog_cancel), null)
        builder.create().show()

    }

    //menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_register, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_save){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showMessage(msgRes: Int){
        Snackbar.make(binding.root, getString(msgRes), Snackbar.LENGTH_SHORT).show()
    }

}