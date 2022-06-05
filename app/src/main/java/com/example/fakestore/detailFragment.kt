package com.example.fakestore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fakestore.databinding.FragmentDetailBinding
import com.example.fakestore.remote.RetrofitBuilder
import com.example.fakestore.remote.ItemEntry
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class detailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.goBack.setOnClickListener{
            val origin = arguments?.getString("origen")
            if (origin.equals("home")){
                val directionD = detailFragmentDirections.actionDetailFragmentToHomeFragment()
                findNavController().navigate(directionD)
            }
            else if (origin.equals("search")){
                val directionD = detailFragmentDirections.actionDetailFragmentToSearchFragment()
                findNavController().navigate(directionD)
            }
            else if (origin.equals("profile")){
                val directionD = detailFragmentDirections.actionDetailFragmentToProfileFragment()
                findNavController().navigate(directionD)
            }

        }

        val retrofit = RetrofitBuilder.create()
        val response = retrofit.getProductById(arguments?.getString("datos_producto").toString())
        Log.d("popo", arguments?.getString("datos_producto").toString())

        response.enqueue(object: Callback<ItemEntry>{
            override fun onResponse(call: Call<ItemEntry>, response: Response<ItemEntry>) {
                val resp = response.body()
                if(resp != null){
                    binding.TVNombreDelProducto.text = resp.title.toString()
                    binding.TVPrecioDelProducto.text = resp.price.toString()
                    binding.TVDetalles.text = resp.description.toString()
                    val image = binding.IVImagenDelProducto

                    Picasso.get().load(resp.image.toString()).into(image)
                }
            }


            override fun onFailure(call: Call<ItemEntry>, t: Throwable) {
                Log.e("error", "Error de xDDDD")
            }
        })
        return binding.root

    }

}