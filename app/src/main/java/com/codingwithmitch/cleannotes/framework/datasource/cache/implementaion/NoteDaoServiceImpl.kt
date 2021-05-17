package com.codingwithmitch.cleannotes.framework.datasource.cache.implementaion

import com.codingwithmitch.cleannotes.business.domain.model.Note
import com.codingwithmitch.cleannotes.business.domain.util.DateUtil
import com.codingwithmitch.cleannotes.framework.datasource.cache.abstraction.NoteDaoService

import com.codingwithmitch.cleannotes.framework.datasource.cache.database.NoteDao
import com.codingwithmitch.cleannotes.framework.datasource.cache.database.returnOrderedQuery
import com.codingwithmitch.cleannotes.framework.datasource.util.CacheMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteDaoServiceImpl
@Inject
constructor(
    private val noteDao: NoteDao,
    private val noteMapper: CacheMapper,
    private val dateUtil: DateUtil
): NoteDaoService {

    override suspend fun insertNote(note: Note): Long {
        return noteDao.insertNote(noteMapper.mapToEntity(note))
    }

    override suspend fun insertNotes(notes: List<Note>): LongArray {
        return noteDao.insertNotes(
            noteMapper.noteListToEntityList(notes)
        )
    }

    override suspend fun getAllNotes(): List<Note> {
        return noteMapper.entityListToNoteList(
            noteDao.searchNotes()
        )
    }

    override suspend fun searchNoteById(primaryKey: String): Note? {
        return noteDao.searchNoteById(primaryKey)?.let { note ->
            noteMapper.mapFromEntity(note)
        }
    }


    override suspend fun deleteNote(primary: String): Int {
        return noteDao.deleteNote(primary)
    }

    override suspend fun deleteNotes(notes: List<Note>): Int {
        val ids = notes.mapIndexed {_, value -> value.id}
        return noteDao.deleteNotes(ids)
    }

    override suspend fun updateNote(primary: String, newTitle: String, newBody: String, timestamp: String?): Int {
        return if(timestamp != null){
            noteDao.updateNote(
                primaryKey = primary,
                title = newTitle,
                body = newBody,
                updated_at = timestamp
            )
        }
        else{
            noteDao.updateNote(
                primaryKey = primary,
                title = newTitle,
                body = newBody,
                updated_at = dateUtil.getCurrentTimestamp()
            )
        }
    }

    override suspend fun searchNotes(): List<Note> {
        return noteMapper.entityListToNoteList(
            noteDao.searchNotes()
        )
    }

    override suspend fun searchNotesOrderByDateDESC(
        query: String,
        page: Int,
        pageSize: Int
    ): List<Note> {
        return noteMapper.entityListToNoteList(
            noteDao.searchNotesOrderByDateDESC(
                query = query,
                page = page,
                pageSize = pageSize
            )
        )
    }

    override suspend fun searchNotesOrderByDateASC(
        query: String,
        page: Int,
        pageSize: Int
    ): List<Note> {
        return noteMapper.entityListToNoteList(
            noteDao.searchNotesOrderByDateASC(
                query = query,
                page = page,
                pageSize = pageSize
            )
        )
    }

    override suspend fun searchNotesOrderByTitleDESC(
        query: String,
        page: Int,
        pageSize: Int
    ): List<Note> {
        return noteMapper.entityListToNoteList(
            noteDao.searchNotesOrderByTitleDESC(
                query = query,
                page = page,
                pageSize = pageSize
            )
        )
    }

    override suspend fun searchNotesOrderByTitleASC(
        query: String,
        page: Int,
        pageSize: Int
    ): List<Note> {
        return noteMapper.entityListToNoteList(
            noteDao.searchNotesOrderByTitleASC(
                query = query,
                page = page,
                pageSize = pageSize
            )
        )
    }

    override suspend fun getNumNotes(): Int {
        return noteDao.getNumNotes()
    }

    override suspend fun returnOrderedQuery(
        query: String,
        filterAndOrder: String,
        page: Int
    ): List<Note> {
        return noteMapper.entityListToNoteList(
            noteDao.returnOrderedQuery(
                query = query,
                page = page,
                filterAndOrder = filterAndOrder
            )
        )
    }
}
