package com.tech.notesapp.ui.fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tech.notesapp.Model.NotesModel
import com.tech.notesapp.R
import com.tech.notesapp.ViewModel.NotesViewModel
import com.tech.notesapp.databinding.FragmentEditNotesBinding
import java.util.*

class EditNotesFragment : Fragment() {

    private val oldNotes by navArgs<EditNotesFragmentArgs>()
    private lateinit var binding: FragmentEditNotesBinding
    private var priority: String = "1"
    private val viewModel: NotesViewModel by viewModels() //no need to initialize viewModel because of by keyword

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEditNotesBinding.inflate(layoutInflater, container, false)

        setHasOptionsMenu(true)
        binding.edtTitle.setText(oldNotes.data.title)
        binding.edtSubtitle.setText(oldNotes.data.subtitle)
        binding.edtNotes.setText(oldNotes.data.notes)

        when (oldNotes.data.priority) {
            "1" -> {
                priority = "1"
                binding.pGreen.setImageResource(R.drawable.ic_done)
                binding.pRed.setImageResource(0)
                binding.pYellow.setImageResource(0)
            }
            "2" -> {
                priority = "2"
                binding.pYellow.setImageResource(R.drawable.ic_done)
                binding.pRed.setImageResource(0)
                binding.pGreen.setImageResource(0)
            }
            "3" -> {
                priority = "3"
                binding.pRed.setImageResource(R.drawable.ic_done)
                binding.pGreen.setImageResource(0)
                binding.pYellow.setImageResource(0)
            }
        }

        //if user change priority
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
            updateNotes(it)
        }
        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title = binding.edtTitle.text.toString()
        val subtitle = binding.edtSubtitle.text.toString()
        val notes = binding.edtNotes.text.toString()

        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)

        val data = NotesModel(
            oldNotes.data.id,
            title = title,
            subtitle = subtitle,
            notes = notes,
            date = notesDate.toString(),
            priority
        )
        Log.e("@@@@","update Title : $title Subtitle : $subtitle notes : $notes")
        viewModel.updateNotes(data)

        Toast.makeText(context, "Notes Updated successfully.", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)       //change to fragment

    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.menu_delete){

            val bottomSheet : BottomSheetDialog = BottomSheetDialog(requireContext(),R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete)

            val textViewYes = bottomSheet.findViewById<TextView>(R.id.dialogYes)
            val textViewNo = bottomSheet.findViewById<TextView>(R.id.dialogNo)

            textViewYes?.setOnClickListener{
                viewModel.deleteNotes(oldNotes.data.id!!)  //not null insertion

//                Navigation.findNavController(itView).navigate(R.id.action_editNotesFragment_to_homeFragment)       //change to fragment

                bottomSheet.dismiss()
            }
            textViewNo?.setOnClickListener{
                bottomSheet.dismiss()
            }
            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }

}