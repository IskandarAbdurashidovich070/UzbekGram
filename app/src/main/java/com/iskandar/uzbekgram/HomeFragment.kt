package com.iskandar.uzbekgram

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.iskandar.uzbekgram.databinding.FragmentAuthBinding
import com.iskandar.uzbekgram.databinding.FragmentHomeBinding
import java.util.concurrent.RecursiveTask

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var path: String
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private lateinit var firebaseStorage: FirebaseStorage
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("posts")
        firebaseStorage = FirebaseStorage.getInstance()
        storageReference = firebaseStorage.getReference("image")


        binding.menu.setOnItemSelectedListener {
            if (it.itemId == R.id.add){

            }
            true
        }


        return binding.root
    }



    @SuppressLint("CheckResult")
    private var getImageContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->

            val hours = System.currentTimeMillis()

            val tesk = storageReference.child(hours.toString()).putFile(uri!!)

            tesk.addOnSuccessListener {


                val downloadUrl = it.metadata?.reference?.downloadUrl

                downloadUrl?.addOnSuccessListener { uri ->
                    path = uri.toString()
                    Glide.with(binding.root.context).load(path)
                }

            }.addOnFailureListener {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
        }

}