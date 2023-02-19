package com.tech.notesapp.Dao

import androidx.lifecycle.LiveData
import androidx.room.*

import com.tech.notesapp.Model.NotesModel

@Dao
interface NotesDao {

    @Query("SELECT * FROM Notes")
    fun getNotes(): LiveData<List<NotesModel>>

    @Query("SELECT * FROM Notes WHERE priority=3")
    fun getHighNotes(): LiveData<List<NotesModel>>

    @Query("SELECT * FROM Notes WHERE priority=2")
    fun getMediumNotes(): LiveData<List<NotesModel>>

    @Query("SELECT * FROM Notes WHERE priority=1")
    fun getLowNotes(): LiveData<List<NotesModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notesModel : NotesModel)

    @Query("DELETE FROM Notes WHERE id=:id")
    fun deleteNotes(id:Int)

    @Update
    fun updateNotes(notesModel: NotesModel)
}
