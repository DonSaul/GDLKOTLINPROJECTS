package com.example.notepad_josue.data

//Database simulation
object DataProv{
    private var note =  Note(
        id = 0,
        title = "Note 1",
        note = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vitae eros " +
                "et eros viverra ultricies. Donec eget mauris id neque vestibulum ultrices. " +
                "Donec venenatis, nisl ullamcorper euismod interdum, enim ex iaculis urna, non " +
                "semper elit tortor a felis. Nulla semper semper orci ut tincidunt. Aliquam ut " +
                "ligula libero."
        )
    var noteList = mutableListOf(note,
        Note(
            id = 1,
            title = "Note 2",
            note = "Suspendisse eu metus nulla. Aenean a felis at elit placerat rutrum " +
                    "rhoncus condimentum urna. Integer quis viverra justo, pretium blandit ipsum."
        )
    )
}

object IdNote{
    var idNote = 2
}