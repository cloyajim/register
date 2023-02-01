package com.example.register

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.register.databinding.ItemRegisterBinding

class RegisterAdapter(var registerList: MutableList<Register>, private val listener: MainActivity):
    RecyclerView.Adapter<RegisterAdapter.ViewHolder>() {

    lateinit var context:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_register, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val register = registerList.get(position)

        holder.binding.tvName.text = register.name
        holder.binding.tvLastName.text = register.lastName
        holder.binding.tvPhone.text = register.phone
        holder.binding.tvAddress.text = register.address
        holder.binding.tvDescription.text = register.description

    }

    override fun getItemCount(): Int = registerList.size

    fun add(register: Register) {
        registerList.add(register)
        notifyDataSetChanged()
    }

    fun remove(register: Register) {
        registerList.remove(register)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemRegisterBinding.bind(view)

        /*fun setListener(register: Register){

            binding.root.setOnLongClickListener{
                listener.onLongClick(register, this@RegisterAdapter)
                true
            }
        }*/

    }

}