package com.example.facegram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.facegram.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.textView.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.button.setOnClickListener{
            val email = binding.email.text.toString()
            val mdp = binding.mdp.text.toString()
            val confirmMdp = binding.confirmMdp.text.toString()

            if (email.isNotEmpty() && mdp.isNotEmpty() && confirmMdp.isNotEmpty()){
                if(mdp == confirmMdp){
                    firebaseAuth.createUserWithEmailAndPassword(email, mdp).addOnCompleteListener {
                        if(it.isSuccessful){
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                        }
                    }
                }else{
                    Toast.makeText(this, "Les mot de passe ne correspondent pas!", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Tous les champs doivent être remplient!", Toast.LENGTH_SHORT).show()

            }


        }


    }
}