package com.example.appnotes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.Colors
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.Scaffold
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appnotes.data.Note
import kotlinx.coroutines.launch
import java.time.format.TextStyle

@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: NoteViewModel,
    navController: NavController
){

    val snackMessage = remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    if(id != 0L){
        val note = viewModel.getNoteById(id).collectAsState(initial = Note(0L, "", ""))
        viewModel.noteTitleState = note.value.title
        viewModel.noteDescriptionState = note.value.description
    }else{
        viewModel.noteTitleState = ""
        viewModel.noteDescriptionState = ""
    }

    Scaffold(
        topBar = { AppBarView(title =
        if(id != 0L ) stringResource(id = R.string.update_note)
        else stringResource(id = R.string.add_note)
        ){navController.navigateUp()}

        },
        scaffoldState = scaffoldState,

    ) {
        Column (modifier = Modifier
            .padding(it)
            .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(10.dp))

            NoteTextField(label = "Title", value = viewModel.noteTitleState,
                onValueChange = {
                    viewModel.onNoteTitleChanged(it)
                } )

            Spacer(modifier = Modifier.height(10.dp))

            NoteTextField(label = "Description", value = viewModel.noteDescriptionState,
                onValueChange = {
                    viewModel.onNoteDescriptionState(it)
                } )

            Spacer(modifier = Modifier.height(10.dp))
            Button( colors = ButtonDefaults.buttonColors(colorResource(id = R.color.backgroundSwipe)), onClick = {
                if(viewModel.noteTitleState.isNotEmpty() && viewModel.noteDescriptionState.isNotEmpty()){

                if(id != 0L){
                    viewModel.updateNote(
                        Note(
                            id=id,
                            title = viewModel.noteTitleState.trim(),
                            description = viewModel.noteDescriptionState.trim())
                    )
                    snackMessage.value = "Note has been updated"
                }else{
                    //TODO AddNote
                    viewModel.addNote(
                        Note(
                            title = viewModel.noteTitleState.trim(),
                            description = viewModel.noteDescriptionState.trim()
                        ))
                    snackMessage.value = "Note has been created"
                }
                }else{
                    //enter fields for note to be created
                    snackMessage.value = "Enter fields to create a new note"
                }
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                    navController.navigateUp()
                }

            } ){
                    androidx.compose.material3.Text(text = if (id !=0L) stringResource(id = R.string.update_note)
                    else stringResource(id = R.string.add_note
                    ),
                    style = androidx.compose.ui.text.TextStyle(fontSize = 18.sp)
                    )

            }
        }
    }
}


@Composable
fun NoteTextField(
    label:String,
    value: String,
    onValueChange: (String) -> Unit
){
    OutlinedTextField(value = value, onValueChange = onValueChange, label = {Text(text = label, color = Color.Black)},
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 10.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors =  TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            focusedBorderColor = colorResource(id = R.color.backgroundSwipe),
            unfocusedBorderColor = colorResource(id = R.color.unfocused),
            cursorColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.black),
            unfocusedLabelColor = colorResource(id = R.color.black),
        )
        )
}

@Preview
@Composable
fun NoteTestFieldPrev(){
    NoteTextField(label = "text", value ="text", onValueChange = {} )
}