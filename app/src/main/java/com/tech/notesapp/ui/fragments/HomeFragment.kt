package com.tech.notesapp.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.tech.notesapp.Backpressedlistener
import com.tech.notesapp.Model.NotesModel
import com.tech.notesapp.R
import com.tech.notesapp.ViewModel.NotesViewModel
import com.tech.notesapp.databinding.FragmentHomeBinding
import com.tech.notesapp.ui.adapters.NotesAdapter


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: NotesViewModel by viewModels() //no need to initialize viewModel because of by keyword
    private var oldMyNotes = arrayListOf<NotesModel>()
    lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
            //           binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
            val staggeredGridLayoutManager =
                StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            oldMyNotes = notesList as ArrayList<NotesModel>

            binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
            adapter = NotesAdapter(requireContext(), notesList)
            binding.rcvAllNotes.adapter = adapter
        }
        //get all notes
        binding.allNotes.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
                //           binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
                val staggeredGridLayoutManager =
                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                oldMyNotes = notesList as ArrayList<NotesModel>
                binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter //set adapter
            }
        }

        //get filter High priority
        binding.filterHigh.setOnClickListener {
            viewModel.getHighNotes().observe(viewLifecycleOwner) { notesList ->
                //           binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
                val staggeredGridLayoutManager =
                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                oldMyNotes = notesList as ArrayList<NotesModel>
                binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter
            }
        }
        //get filter low priority
        binding.filterLow.setOnClickListener {
            viewModel.getLowNotes().observe(viewLifecycleOwner) { notesList ->
                //           binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
                val staggeredGridLayoutManager =
                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                oldMyNotes = notesList as ArrayList<NotesModel>
                binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter
            }
        }
        //get filter Medium priority
        binding.filterMedium.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner) { notesList ->
                //           binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
                val staggeredGridLayoutManager =
                    StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                oldMyNotes = notesList as ArrayList<NotesModel>
                binding.rcvAllNotes.layoutManager = staggeredGridLayoutManager
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter
            }
        }
        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNoteFragment)
        }
        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val item = menu.findItem(R.id.menu_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Enter Note Here..."

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean { //when writing in the text then runtime search data
                NotesFiltering(p0)
                return true
            }

        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun NotesFiltering(p0: String?) {
        val newFilteredList = arrayListOf<NotesModel>()
        for (i in oldMyNotes) {
            if (i.title.contains(p0!!) || i.subtitle.contains(p0)) {
                newFilteredList.add(i)
            }
            adapter.filteringForSearch(newFilteredList)
        }
    }

}