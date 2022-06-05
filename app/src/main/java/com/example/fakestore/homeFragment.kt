package com.example.fakestore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.fakestore.databinding.FragmentHomeBinding
import com.example.fakestore.remote.RetrofitBuilder
import com.example.fakestore.remote.ItemEntry
import org.json.JSONArray
import org.json.JSONObject
import kotlin.random.Random
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class homeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)


        val navControler = findNavController()
        val retrofit = RetrofitBuilder.create()
        var listaRandom = ArrayList<Int>(5)
        var x: Int
        for (i in 1..5){
            x= Random.nextInt(0,20)
            listaRandom.add(x)
        }
        val response = retrofit.getProductById(listaRandom[0].toString())
        val response1 = retrofit.getProductById(listaRandom[1].toString())
        val response2 = retrofit.getProductById(listaRandom[2].toString())
        val response3 = retrofit.getProductById(listaRandom[3].toString())
        val response4 = retrofit.getProductById(listaRandom[4].toString())

        var js1: String = ""
        var js2: String = ""
        var js3: String = ""
        var js4: String = ""
        var js5: String = ""
        response.enqueue(object: Callback<ItemEntry>{
            override fun onResponse(call: Call<ItemEntry>, response: Response<ItemEntry>) {
                val resp = response.body()
                if (resp != null){
                    var t1 = resp.toString()
                    val s1 = t1.substring(10)
                    val f1 = "{"+s1.dropLast(1)+"}"
                    js1 = f1
                    Log.d("popo", f1)
                }
            }
            override fun onFailure(call: Call<ItemEntry>, t: Throwable) {
                Log.d("popo", "xDDD")
            }
        })

        response1.enqueue(object: Callback<ItemEntry>{
            override fun onResponse(call: Call<ItemEntry>, response: Response<ItemEntry>) {
                val resp = response.body()
                if (resp != null){
                    var t2 = resp.toString()
                    val s2 = t2.substring(10)
                    val f2 = "{"+s2.dropLast(1)+"}"
                    js2 = f2
                    Log.d("popo", f2)
                }
            }
            override fun onFailure(call: Call<ItemEntry>, t: Throwable) {
                Log.d("popo", "xDDD")
            }
        })

        response2.enqueue(object: Callback<ItemEntry>{
            override fun onResponse(call: Call<ItemEntry>, response: Response<ItemEntry>) {
                val resp = response.body()
                if (resp != null){
                    var t3 = resp.toString()
                    val s3 = t3.substring(10)
                    val f3 = "{"+s3.dropLast(1)+"}"
                    js3 = f3
                    Log.d("popo", f3)
                }
            }
            override fun onFailure(call: Call<ItemEntry>, t: Throwable) {
                Log.d("popo", "xDDD")
            }
        })

        response3.enqueue(object: Callback<ItemEntry>{
            override fun onResponse(call: Call<ItemEntry>, response: Response<ItemEntry>) {
                val resp = response.body()
                if (resp != null){
                    var t4 = resp.toString()
                    val s4 = t4.substring(10)
                    val f4 = "{"+s4.dropLast(1)+"}"
                    js4 = f4
                    Log.d("popo", f4)
                }
            }
            override fun onFailure(call: Call<ItemEntry>, t: Throwable) {
                Log.d("popo", "xDDD")
            }
        })

        response4.enqueue(object: Callback<ItemEntry>{
            override fun onResponse(call: Call<ItemEntry>, response: Response<ItemEntry>) {
                val resp = response.body()
                if (resp != null){
                    var t5 = resp.toString()
                    val s5 = t5.substring(10)
                    val f5 = "{"+s5.dropLast(1)+"}"
                    js5 = f5
                    Log.d("popo", f5)
                }
            }
            override fun onFailure(call: Call<ItemEntry>, t: Throwable) {
                Log.d("popo", "xDDD")
            }
        })
        Log.d("hola1234", js1)
        if (js1 != "" && js2 != "" && js3 != "" && js4 != "" && js5 != "") {
            val top5: Array<JSONObject> = arrayOf(
                JSONObject(js1),
                JSONObject(js2),
                JSONObject(js3),
                JSONObject(js4),
                JSONObject(js5)
            )
            Log.d("popo", "ya lo mande")
            binding.RVRandomProducts.adapter = MainAdapter(top5)

        }
        else{
            Log.e("pop", "Error de conexion")
        }
        binding.botonPrueba.setOnClickListener{
            val direccion = homeFragmentDirections.actionHomeFragmentToDetailFragment("12", "home")

            findNavController().navigate(direccion)
        }

        return binding.root
    }
}