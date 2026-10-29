package br.edu.fatecpg.appKotlin

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class loginActivity : AppCompatActivity(R.layout.activity_login) {
    private lateinit var auth: FirebaseAuth;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val edtEmailLogin=findViewById<EditText>(R.id.edt_email_login)
        val edtSenhaLogin=findViewById<EditText>(R.id.edt_senha_login)
        val btnLogar=findViewById<Button>(R.id.btnLogin)


        auth.signInWithEmailAndPassword("mary@fatec.br","mary12345")
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Log.i("FIREBASE","LOGADO COM SUCESSO")

                }else{
                    Log.i("FIREBASE", "ERRO AO LOGAR ${task.exception}")                }

            }
    }
    val btnEsqueciSenha = findViewById<Button>(R.id.btnEsqueciSenha)

    btnEsqueciSenha.setOnClickListener {
        startActivity(Intent(this, EsqueciSenhaActivity::class.java))
    }
}