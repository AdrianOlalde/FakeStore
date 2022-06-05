package com.example.fakestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fakestore.databinding.FragmentProfileBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.json.JSONObject


class profileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        val database = Firebase.database
        val dataRef = database.reference

        dataRef.child("users").child("0").get().addOnSuccessListener {response ->
            val respuesta = JSONObject(response.value.toString())
            binding.TVNombreValor.text = respuesta.getString("name")
            binding.TVApellidosValor.text = respuesta.getString("lastName")
            val fecha = respuesta.getString("year")+"/"+respuesta.getString("month")+"/"+respuesta.getString("day")
            binding.TVFechaValor.text = fecha
        }

        binding.botonPruebaPerfil.setOnClickListener{
            val directionP = profileFragmentDirections.actionProfileFragmentToDetailFragment("1231", "profile")
            findNavController().navigate(directionP)
        }
        return binding.root
    }

}