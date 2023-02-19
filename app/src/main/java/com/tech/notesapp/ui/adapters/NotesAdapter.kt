package com.tech.notesapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.tech.notesapp.Model.NotesModel
import com.tech.notesapp.R
import com.tech.notesapp.databinding.ItemNotesBinding
import com.tech.notesapp.ui.fragments.HomeFragmentDirections

class NotesAdapter(val requireContext: Context, private var notesList: List<NotesModel>) : RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {

    fun filteringForSearch(newFilteredList: ArrayList<NotesModel>) {
        notesList = newFilteredList
        notifyDataSetChanged()
    }
//    class notesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class notesViewHolder(val binding:ItemNotesBinding) : RecyclerView.ViewHolder(binding.root)   //i'm using binding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(ItemNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {

        val data = notesList[position]
       holder.binding.notesTitle.text = data.title
        holder.binding.notesSubtitle.text = data.subtitle
        holder.binding.notesDate.text = data.date
        holder.binding.notesView.text = data.notes

        when(data.priority){
            "1"->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.green_dot)
            }
            "2"->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.yellow_dot)
            }
            "3"->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.red_dot)
            }
        }

        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(data)
            Navigation.findNavController(it).navigate(action)
        }

    }
}