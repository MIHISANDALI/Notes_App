package com.example.notessqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.notessqlite.databinding.ActivityMainBinding
import com.example.notessqlite.databinding.ActivityUpdateBinding

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db: NotesDatabaseHelper
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotesDatabaseHelper(this)

        noteId = intent.getIntExtra("note_id", -1)
        if (noteId == -1)
            finish()
        return

        val note = db.getNoteByID(noteId)
        binding.updateTitleEditText.setText(note,title)
        binding.updateContentEditText.setText(note,content)

        binding.updatesaveButton.setOnClickListener {
            val newTitle = binding.updateTitleEditText.text.toString()
            val newContent = binding.updateContentEditText.text.toString()
            val updateNote = Note(noteId, newTitle, newContent)
            db.insertNote(updateNote)
            finish()
            Toast.makeText(this, "changes saved", Toast.LENGTH_SHORT).show()
        }
    }
}

private fun EditText.setText(note: Any, title: CharSequence?) {
    TODO("Not yet implemented")
}



