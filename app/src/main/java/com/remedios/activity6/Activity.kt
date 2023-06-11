package com.remedios.activity6


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.remedios.activity6.databinding.ActivityListBinding


class Activity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView()
    }

    private fun recyclerView(){
        val dataAdapterToDo = DataAdapter(firstToDoList())
        binding.rvTodoList.apply {
            adapter = dataAdapterToDo
            layoutManager = LinearLayoutManager(this@Activity)
            itemAnimator = null
        }
        addButton(dataAdapterToDo)
        setupSwipeLeftDelete(dataAdapterToDo)
    }

    private fun firstToDoList():MutableList<BindingData>{
        return mutableListOf()
    }

    private fun addButton(dataAdapter: DataAdapter){
        binding.addBtn.setOnClickListener{
            val doTitle = binding.etAddToDo.text.toString()
            if(doTitle.isNotEmpty()){
                dataAdapter.ListToDo.add(BindingData(doTitle))
                dataAdapter.notifyItemInserted(dataAdapter.ListToDo.size-1)
                binding.etAddToDo.text?.clear()
            }
        }
    }

    private fun setupSwipeLeftDelete(dataAdapter: DataAdapter){
        ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val removeItem = dataAdapter.ListToDo.removeAt(position)
                dataAdapter.notifyItemRemoved(position)

                Snackbar.make(binding.root, "Item Removed", Snackbar.LENGTH_LONG)
                    .setAction("Undo"){
                        dataAdapter.ListToDo.add(position, removeItem)
                        dataAdapter.notifyItemInserted(position)
                    }.show()
            }
            
        }).attachToRecyclerView(binding.rvTodoList)
    }
}