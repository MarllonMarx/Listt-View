package com.example.examplelistview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.examplelistview.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private  var position = -1

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

       val listUsers = ArrayList<User>()
       val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,listUsers)


        binding.listaItens.adapter = adapter
        binding.listaItens.setOnItemClickListener { _, _, position, _ ->

            binding.username.setText(listUsers.get(position).username)
            binding.pass.setText(listUsers.get(position).pass)
            this.position = position
        }


        binding.buttonCadastrar.setOnClickListener {

            if (!binding.username.text.toString().trim().isEmpty() && !binding.pass.text.toString().trim().isEmpty()){

                listUsers.add(User(binding.username.text.toString(),binding.pass.text.toString()))

                adapter.notifyDataSetChanged()
                binding.username.setText("")
                binding.pass.setText("")
            }else{
                Snackbar.make(binding.root, "ERRO! Campos Vazios", Snackbar.LENGTH_LONG)
                    .setBackgroundTint(resources.getColor(R.color.black))
                    .setTextColor(resources.getColor(R.color.white))
                    .show()
            }
        }

        binding.buttonAtualizar.setOnClickListener {

            if (position >= 0) {
                listUsers.get(position).username = binding.username.text.toString().trim()
                listUsers.get(position).pass = binding.pass.text.toString().trim()

                adapter.notifyDataSetChanged()
                binding.username.setText("")
                binding.pass.setText("")

                position = -1

            }
        }

            binding.buttonExcluir.setOnClickListener {

                if (!binding.username.text.toString().trim().isEmpty() && !binding.pass.text.toString().trim().isEmpty()){

                    listUsers.removeAt(position)

                    adapter.notifyDataSetChanged()
                    binding.username.setText("")
                    binding.pass.setText("")
                }


            }

    }
}