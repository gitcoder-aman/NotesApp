package com.tech.notesapp.ui.fragments

import android.app.ActionBar
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.tech.notesapp.Model.NotesModel
import com.tech.notesapp.R
import com.tech.notesapp.ViewModel.NotesViewModel
import com.tech.notesapp.databinding.FragmentCreateNoteBinding

import java.util.*

class CreateNoteFragment : Fragment() {

    private lateinit var binding: FragmentCreateNoteBinding
     private var priority: String = "1"
     val viewModel: NotesViewModel by viewModels() //no need to initialize viewModel because of by keyword


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentCreateNoteBinding.inflate(layoutInflater, container, false)

        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)


        binding.pGreen.setImageResource(R.drawable.ic_done)

        binding.pGreen.setOnClickListener {
            priority = "1"
            binding.pGreen.setImageResource(R.drawable.ic_done)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }
        binding.pYellow.setOnClickListener {
            priority = "2"
            binding.pYellow.setImageResource(R.drawable.ic_done)
            binding.pGreen.setImageResource(0)
            binding.pRed.setImageResource(0)
        }

        binding.pRed.setOnClickListener {
            priority = "3"
            binding.pRed.setImageResource(R.drawable.ic_done)
            binding.pGreen.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }

        binding.btnSaveNotes.setOnClickListener {
            createNotes(it)
        }
        return binding.root
    }

    private fun createNotes(it: View?) {
        val title = binding.edtTitle.text.toString()
        val subtitle = binding.edtSubtitle.text.toString()
        val notes = binding.edtNotes.text.toString()

        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.getTime())

        val data = NotesModel(
            null,
            title = title,
            subtitle = subtitle,
            notes = notes,
            date = notesDate.toString(),
            priority
        )
        viewModel.addNotes(data)

        Toast.makeText(context, "Notes create successfully.",Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_createNoteFragment_to_homeFragment)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

}