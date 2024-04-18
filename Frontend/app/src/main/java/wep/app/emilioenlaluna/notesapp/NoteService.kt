import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import wep.app.emilioenlaluna.notesapp.Note
import wep.app.emilioenlaluna.notesapp.NoteDto

interface NoteService {
    companion object {
        private const val BASE_URL = "http://10.0.2.2:3000/"
        private const val AUTH_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiaWF0IjoxNzEzMjMwNjE1fQ.i12x88SLsoTaWOp0ldFwaxeFM2ed85uzespi71nqSBI"

        private val gson: Gson = GsonBuilder().create()

        private val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $AUTH_TOKEN")
                    .build()
                chain.proceed(request)
            }
            .build()

        val instance = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create(NoteService::class.java)
    }

    @GET("notes")
    suspend fun getNotes(): List<Note>

    @POST("notes")
    suspend fun insertNote(@Body note: NoteDto): Note

    @PUT("notes/{id}")
    suspend fun updateNote(@Body note: NoteDto, @Path("id") id: Int)

    @DELETE("notes/{id}")
    suspend fun deleteNote(@Path("id") id: Int)
}