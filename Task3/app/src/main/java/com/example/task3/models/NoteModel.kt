package com.example.task3.models

import java.util.Calendar
import java.util.Date

/**
 *  This class data models the properties of a Note.
 *
 *  @property title the title of this card.
 *  @property description the description of this card.
 *  @property content the content of this card.
 *  @property createdAt the creation date of this card.
 * */
data class NoteModel(
    val title:String = "",
    val description: String = "",
    val content: String = "",
    val createdAt: Date = Calendar.getInstance().time
)