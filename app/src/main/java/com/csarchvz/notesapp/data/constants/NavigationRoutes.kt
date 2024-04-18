package com.csarchvz.notesapp.data.constants

object NavigationRoutes {

    const val NAVIGATION_HOME = "HomeScreen"
    const val NAVIGATION_CREATE_NOTE = "CreateNoteScreen"
    const val NAVIGATION_DETAIL_NOTE = "DetailNote"
    const val NAVIGATION_EDIT_NOTE = "EditNote"
    const val NOTE_ARGUMENT = "noteId"
    const val NAVIGATION_DETAIL_NOTE_ROUTE = "$NAVIGATION_DETAIL_NOTE/{$NOTE_ARGUMENT}"

    const val NAVIGATION_EDIT_NOTE_ROUTE = "$NAVIGATION_EDIT_NOTE/{$NOTE_ARGUMENT}"

    fun noteDetailNavigation(noteId: Int): String = "$NAVIGATION_DETAIL_NOTE/$noteId"
    fun noteEditNavigation(noteId: Int): String = "$NAVIGATION_EDIT_NOTE/$noteId"
}