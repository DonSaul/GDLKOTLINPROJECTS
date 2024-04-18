package com.example.notepad_josue.data

object DataProv{
    private var note =  Note(
        id = 1,
        title = "Note 1",
        note = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin vitae eros " +
                "et eros viverra ultricies. Donec eget mauris id neque vestibulum ultrices. " +
                "Donec venenatis, nisl ullamcorper euismod interdum, enim ex iaculis urna, non " +
                "semper elit tortor a felis. Nulla semper semper orci ut tincidunt. Aliquam ut " +
                "ligula libero."
        )
    var noteList = mutableListOf(note,
        Note(
            id = 2,
            title = "Note 2",
            note = "Suspendisse eu metus nulla. Aenean a felis at elit placerat rutrum " +
                    "rhoncus condimentum urna. Integer quis viverra justo, pretium blandit ipsum."
        ),
        Note(
            id = 3,
            title = "Note 3",
            note = "Morbi tempor mattis purus, in mattis eros fermentum at. Interdum et " +
                    "malesuada fames ac ante ipsum primis in faucibus. Vestibulum nec velit purus."
        ),
        Note(
            id = 4,
            title = "Note 4",
            note = "In dapibus, elit in gravida blandit, velit nunc volutpat velit, nec euismod " +
                    "turpis nunc sed arcu. Nunc enim elit, rhoncus ut elementum a, faucibus vitae " +
                    "ligula. Sed vehicula dignissim erat eu tristique. Nunc elementum neque a magna " +
                    "dignissim tempor. Fusce convallis nulla quis purus pulvinar, molestie maximus leo fermentum."
        )
    )
}

object IdNote{
    var idNote = 5
}