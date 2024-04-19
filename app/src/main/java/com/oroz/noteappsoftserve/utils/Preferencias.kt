package com.oroz.noteappsoftserve.utils

import android.content.Context
import com.oroz.noteappsoftserve.screens.NotaClass
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class Preferencias {
    companion object {
        const val prefeNotas = "PREFE_USUARIO"

        fun guardarNota(activity: Context, nota: NotaClass) {
            val sharedPreferences = activity.getSharedPreferences(prefeNotas, Context.MODE_PRIVATE)
            val notas = obtenerNotas(activity)
            notas.add(nota) // Add the new note to the existing list
            val editor = sharedPreferences.edit()
            val jsonArray = JSONArray()
            for (nota in notas) {
                val jsonObject = JSONObject()
                jsonObject.put("titulo", nota.titulo)
                jsonObject.put("contenido", nota.contenido)
                jsonArray.put(jsonObject)
            }
            editor.putString(prefeNotas, jsonArray.toString())
            editor.apply()
        }


        fun obtenerNotas(activity: Context): ArrayList<NotaClass> {
            val sharedPreferences = activity.getSharedPreferences(prefeNotas, Context.MODE_PRIVATE)
            val notasJson = sharedPreferences.getString(prefeNotas, null)
            val notas = mutableListOf<NotaClass>()

            notasJson?.let {
                try {
                    val jsonArray = JSONArray(it)
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val titulo = jsonObject.getString("titulo")
                        val contenido = jsonObject.getString("contenido")
                        notas.add(NotaClass(titulo, contenido))
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            return ArrayList(notas)
        }
    }
}
