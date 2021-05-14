package com.codingwithmitch.cleannotes.framework.datasource.network
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.codingwithmitch.cleannotes.business.domain.model.NoteFactory
import com.codingwithmitch.cleannotes.di.TestAppComponent
import com.codingwithmitch.cleannotes.framework.BaseTest
import com.codingwithmitch.cleannotes.framework.datasource.data.NoteDataFactory
import com.codingwithmitch.cleannotes.framework.datasource.network.abstraction.NoteFirestoreService
import com.codingwithmitch.cleannotes.framework.datasource.network.implementation.NoteFirestoreServiceImpl
import com.codingwithmitch.cleannotes.framework.datasource.network.mappers.NetworkMapper
import com.codingwithmitch.cleannotes.framework.presentation.TestBaseApplication
import com.codingwithmitch.cleannotes.util.printLogD
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*
import javax.inject.Inject
import kotlin.test.assertEquals
import kotlin.test.assertTrue


@RunWith(AndroidJUnit4ClassRunner::class)
class NoteFirestoreServiceTests: BaseTest(){

    // system in test
    private lateinit var noteFirestoreService: NoteFirestoreService

    @Inject
    lateinit var firestore: FirebaseFirestore

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    @Inject
    lateinit var noteDataFactory: NoteDataFactory

    @Inject
    lateinit var networkMapper: NetworkMapper

    init {
        injectTest()
        signIn()
        insertTestData()
    }

    override fun injectTest() {
        (application.appComponent as TestAppComponent)
            .inject(this)
    }

    @Before
    fun before(){
        noteFirestoreService = NoteFirestoreServiceImpl(
            firebaseAuth = FirebaseAuth.getInstance(),
            firestore = firestore,
            networkMapper = networkMapper
        )
    }

    private fun signIn() = runBlocking{
        firebaseAuth.signInWithEmailAndPassword(
            EMAIL,
            PASSWORD
        ).await()
    }

    fun insertTestData() {
        val entityList = networkMapper.noteListToEntityList(
            noteDataFactory.produceListOfNotes()
        )
        for(entity in entityList){
            firestore
                .collection(NoteFirestoreServiceImpl.NOTES_COLLECTION)
                .document(NoteFirestoreServiceImpl.USER_ID)
                .collection(NoteFirestoreServiceImpl.NOTES_COLLECTION)
                .document(entity.id)
                .set(entity)
        }
    }

    @Test
    fun insertSingleNote_CBS() = runBlocking{
        val note = noteDataFactory.createSingleNote(
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString()
        )

        noteFirestoreService.insertOrUpdateNote(note)

        val searchResult = noteFirestoreService.searchNote(note)

        assertEquals(note, searchResult)
    }

    @Test
    fun queryAllNotes() = runBlocking {
        val notes = noteFirestoreService.getAllNotes()
        printLogD("FirestoreServiceTests", "notes: ${notes.size}")
        assertTrue { notes.size > 5 }
    }

    companion object{
        const val EMAIL = "1042332611@qq.com"
        const val PASSWORD = "123456"
    }
}
