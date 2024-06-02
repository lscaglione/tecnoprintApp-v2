package br.com.lucca.tecnprintapp.ui


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import br.com.lucca.tecnprintapp.R
import br.com.lucca.tecnprintapp.db.Producao
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class activity_login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            val username = findViewById<EditText>(R.id.etUsername).text.toString()
            val password = findViewById<EditText>(R.id.etPassword).text.toString()

            // Implementar lógica de login
            if (username.isNotEmpty() && password.isNotEmpty()) {
                // Exemplo: validação de login
                if (username == "user" && password == "password" || username == "lucca" && password == "1994") {
                    startActivity(Intent(this, activity_visualizacao_dados::class.java))
                    // Redirecionar para outra Activity ou funcionalidade
                } else {
                    Toast.makeText(this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}