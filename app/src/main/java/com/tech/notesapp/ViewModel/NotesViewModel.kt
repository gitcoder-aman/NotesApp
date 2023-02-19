package com.tech.notesapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.tech.notesapp.Database.NotesDatabase
import com.tech.notesapp.Model.NotesModel
import com.tech.notesapp.Repository.NotesRepository

class NotesViewModel(application: Application) : AndroidViewModel(application){

    val repository : NotesRepository

    init {
        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository = NotesRepository(dao)
    }
    fun addNotes(notesModel: NotesModel){
        repository.insertNotes(notesModel)
    }
    fun getNotes():LiveData<List<NotesModel>> = repository.getAllNotes()

    fun getLowNotes():LiveData<List<NotesModel>>{
        return repository.getLowNotes()
    }
    fun getMediumNotes():LiveData<List<NotesModel>>{
        return repository.getMediumNotes()
    }
    fun getHighNotes():LiveData<List<NotesModel>> = repository.getHighNotes()


    fun deleteNotes(id:Int){
        repository.deleteNotes(id)
    }
    fun updateNotes(notesModel: NotesModel){
        repository.updateNotes(notesModel)
    }

}