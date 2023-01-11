package com.iskandar.uzbekgram

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.iskandar.uzbekgram.databinding.FragmentAuthBinding
import java.sql.DataTruncation

class AuthFragment : Fragment() {
    private lateinit var binding: FragmentAuthBinding
    private lateinit var auth:FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentAuthBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()

        if (auth.currentUser != null){
            findNavController().popBackStack()
            findNavController().navigate(R.id.homeFragment)
            return binding.root
        }
        binding.btnEnter.setOnClickListener {
            val email = binding.email.text.toString().trim()
            val password = binding.password.text.toString().trim()
            if (email.isNotBlank() && password.isNotBlank()){
                registration(email, password)
            }else{
                Toast.makeText(context, "Empty", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun registration(gmail:String, password:String) {
        auth.createUserWithEmailAndPassword(gmail, password)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    findNavController().navigate(R.id.homeFragment)
                }else{
                    Toast.makeText(context, "Problem with sign in", Toast.LENGTH_SHORT).show()
                }
            }
    }
}