package com.tech.notesapp.Repository

import androidx.lifecycle.LiveData
import com.tech.notesapp.Dao.NotesDao
import com.tech.notesapp.Model.NotesModel

class NotesRepository(private val dao:NotesDao) {

    fun getAllNotes():LiveData<List<NotesModel>>{
        return dao.getNotes()
    }
    fun getLowNotes():LiveData<List<NotesModel>>{
        return dao.getLowNotes()
    }
    fun getMediumNotes():LiveData<List<NotesModel>>{
        return dao.getMediumNotes()
    }
    fun getHighNotes():LiveData<List<NotesModel>> = dao.getHighNotes()

    fun insertNotes(notesModel: NotesModel){
        dao.insertNotes(notesModel)
    }
    fun deleteNotes(id: Int){
        dao.deleteNotes(id)
    }
    fun updateNotes(notesModel: NotesModel){
        dao.updateNotes(notesModel)
    }

}