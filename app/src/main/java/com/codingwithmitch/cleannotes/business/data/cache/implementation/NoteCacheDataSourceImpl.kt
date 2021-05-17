package com.codingwithmitch.cleannotes.business.data.cache.implementation

import com.codingwithmitch.cleannotes.business.data.cache.abstraction.NoteCacheDataSource
import com.codingwithmitch.cleannotes.business.domain.model.Note
import com.codingwithmitch.cleannotes.framework.datasource.cache.abstraction.NoteDaoService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteCacheDataSourceImpl
@Inject
constructor(
    private val noteDaoService: NoteDaoService
): NoteCacheDataSource {

    override suspend fun insertNote(note: Note): Long {
        return noteDaoService.insertNote(note)
    }

    override suspend fun deleteNote(primary: String): Int {
        return noteDaoService.deleteNote(primary)
    }

    override suspend fun deleteNotes(notes: List<Note>): Int {
        return noteDaoService.deleteNotes(notes)
    }

    override suspend fun updateNote(primary: String, newTitle: String, newBody: String, timestamp: String?): Int {
        return noteDaoService.updateNote(primary, newTitle, newBody, timestamp)
    }

    override suspend fun searchNotes(
        query: String,
        filterAndOrder: String,
        page: Int
    ): List<Note> {
        return noteDaoService.returnOrderedQuery(
            query, filterAndOrder, page
        )
    }

    override suspend fun searchNoteById(primaryKey: String): Note? {
        return noteDaoService.searchNoteById(primaryKey)
    }

    override suspend fun getNumNotes(): Int {
        return noteDaoService.getNumNotes()
    }

    override suspend fun getAllNotes(): List<Note> {
        return noteDaoService.getAllNotes()
    }

    override suspend fun insertNotes(notes: List<Note>): LongArray{
        return noteDaoService.insertNotes(notes)
    }
}