package com.example.task3.viewmodels

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.task3.models.NoteModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 *  This class models the ViewModel that handle the state management of the notes taken in
 *  this app.
 * */
@SuppressLint("MutableCollectionMutableState")
class NoteViewModel:ViewModel(){
    private val _notes  by mutableStateOf(emptyList<NoteModel>().toMutableList())
    val notes = _notes
    var state by mutableStateOf(NoteModel())

    /**
     *  Updates the [field] of the note to be added according to the input [value]
     *
     *  @param[field] a String
     *  @param[value] a String
     *  @return Unit
     * */
    fun onValueChange(field:String, value:String){
        when(field){
            "title" -> {
                if (value.length<=24){
                    state = state.copy(title = value)
                }
            }
            "description" -> {
                if( value.length<=30){
                    state = state.copy(description = value)
                }
            }
            "content" ->  state = state.copy(content = value)
        }
    }

    /**
     *  Adds a note to the current state
     *
     *  @return Unit
     * */
    fun addNote(){

        _notes.add(
            NoteModel(
                title = state.title,
                description = state.description,
                content = state.content,
                createdAt = Calendar.getInstance().time
            )
        )
    }

    /**
     *  Formats a Date object [currentDate] and returns its String representation
     *  in format "dd/MM/yyyy"
     *
     *  @param[currentDate] a Date
     *  @return String
     * */
    fun formatDate(currentDate: Date): String{
        val res = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return res.format(currentDate)
    }


    /**
     *  Resets the state of the note to be added, cleaning all text inputs
     *
     *  @return Unit
     * */
    fun clearState(){
        state = state.copy(
            title = "",
            description = "",
            content = "",
            createdAt = Calendar.getInstance().time
        )
    }
}