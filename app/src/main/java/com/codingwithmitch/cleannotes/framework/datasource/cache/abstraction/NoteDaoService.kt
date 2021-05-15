package com.codingwithmitch.cleannotes.framework.datasource.cache.abstraction

import com.codingwithmitch.cleannotes.business.domain.model.Note
import com.codingwithmitch.cleannotes.framework.datasource.cache.database.NOTE_PAGINATION_PAGE_SIZE

interface NoteDaoService {
    suspend fun insertNote(note: Note): Long
    suspend fun deleteNote(primary: String): Int
    suspend fun deleteNotes(note: List<Note>): Int
    suspend fun updateNote(primary: String, newTitle: String, newBody: String, timestamp: String?): Int
    suspend fun searchNotes(): List<Note>
    suspend fun searchNoteById(primaryKey: String): Note?
    suspend fun getNumNotes(): Int
    suspend fun insertNotes(notes: List<Note>): LongArray
    suspend fun getAllNotes(): List<Note>

    suspend fun searchNotesOrderByDateDESC(
        query: String,
        page: Int,
        pageSize: Int = NOTE_PAGINATION_PAGE_SIZE
    ): List<Note>

    suspend fun searchNotesOrderByDateASC(
        query: String,
        page: Int,
        pageSize: Int = NOTE_PAGINATION_PAGE_SIZE
    ): List<Note>

    suspend fun searchNotesOrderByTitleDESC(
        query: String,
        page: Int,
        pageSize: Int = NOTE_PAGINATION_PAGE_SIZE
    ): List<Note>

    suspend fun searchNotesOrderByTitleASC(
        query: String,
        page: Int,
        pageSize: Int = NOTE_PAGINATION_PAGE_SIZE
    ): List<Note>

    suspend fun returnOrderedQuery(
        query: String,
        filterAndOrder: String,
        page: Int
    ): List<Note>

}