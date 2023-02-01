package com.example.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.register.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseHelper
    private lateinit var registerFinishAdapter: RegisterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = DatabaseHelper(this)

        binding.btnAdd.setOnClickListener {
            if(binding.etName.text.toString().isNotBlank() ||
                binding.etLastName.text.toString().isNotBlank() ||
                binding.etPhone.text.toString().isNotBlank() ||
                binding.etAddress.text.toString().isNotBlank() ||
                binding.etDescription.text.toString().isNotBlank()){

                val register = Register(name = binding.etName.text.toString().trim(),
                    lastName = binding.etLastName.text.toString().trim(),
                    phone = binding.etPhone.text.toString().trim(),
                    address = binding.etAddress.text.toString().trim(),
                    description = binding.etDescription.text.toString().trim())
                register.id = database.insertRegister(register)

                if(register.id != Constants.ID_ERROR) {
                    addNoteAuto(register)
                    binding.etDescription.text?.clear()
                    showMessage(R.string.message_write_db_success)
                }else{
                    showMessage(R.string.message_write_db_error)
                }

            } else {
                binding.etDescription.error = getString(R.string.validation_field_required)
            }
        }

    }

    private fun addNoteAuto(register: Register) {
        this.registerFinishAdapter.add(register)
    }


    private fun showMessage(msgRes:Int){
        Snackbar.make(binding.root,getString(msgRes),Snackbar.LENGTH_SHORT).show()
    }

}