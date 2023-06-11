package com.remedios.activity6

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.remedios.activity6.databinding.ActivityItemBinding

class DataAdapter(var ListToDo: MutableList<BindingData>): RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ActivityItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        var binding = ActivityItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataAdapter.ViewHolder, position: Int) {
        holder.binding.apply {
            tvTitle.text = ListToDo[position].title
            cbToDo.apply {
                isChecked = ListToDo[position].isChecked
                setOnClickListener{
                    ListToDo[position].isChecked = !ListToDo[position].isChecked
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return ListToDo.size
    }
}