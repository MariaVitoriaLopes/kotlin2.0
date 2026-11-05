package br.edu.fatecpg.appKotlin

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val db = Firebase.firestore

        val edtNome = findViewById<EditText>(R.id.edtNome)
        val edtCpf = findViewById<EditText>(R.id.edtCpf)
        val btnCadastrar = findViewById<Button>(R.id.btnCadastrar)

        btnCadastrar.setOnClickListener {
            val nome = edtNome.text.toString().trim()
            val cpf = edtCpf.text.toString().trim()

            if (nome.isEmpty() || cpf.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val cliente = hashMapOf(
                "nome" to nome,
                "cpf" to cpf,
                "data_compra" to FieldValue.serverTimestamp()
            )

            db.collection("colecao_cliente")
                .add(cliente)
                .addOnSuccessListener { documentReference ->
                    Log.d("FIRESTORE", "Cliente cadastrado com ID: ${documentReference.id}")
                    Toast.makeText(this, "Cliente cadastrado com sucesso!", Toast.LENGTH_SHORT).show()

                    edtNome.text.clear()
                    edtCpf.text.clear()
                }
                .addOnFailureListener { e ->
                    Log.e("FIRESTORE", "Erro ao cadastrar", e)
                    Toast.makeText(this, "Falha ao cadastrar cliente.", Toast.LENGTH_SHORT).show()
                }
        }
    }
}