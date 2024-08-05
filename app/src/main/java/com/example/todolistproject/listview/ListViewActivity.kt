package com.example.todolistproject.listview

import android.app.Dialog
import android.nfc.Tag
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.todolistproject.R
import com.example.todolistproject.databinding.ActivityListViewBinding
import com.example.todolistproject.listview.databaseFiles.NotesDatabase
import com.example.todolistproject.listview.databaseFiles.NotesEntity

class ListViewActivity : AppCompatActivity() {

    lateinit var binding: ActivityListViewBinding


    var array= arrayListOf("one","two","three")

    var notesList= arrayListOf<NotesEntity>()
    lateinit var notesDatabase: NotesDatabase


    var arrayAdapter:ArrayAdapter<String>?=null
    private val TAG="ListViewActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding=ActivityListViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }





        arrayAdapter=ArrayAdapter(this,android.R.layout.simple_list_item_activated_1,array)
        binding.ListView.adapter=arrayAdapter















        //setting up toast for item being clicked in the list
        binding.ListView.setOnItemClickListener { parent, view, position, id ->

            Toast.makeText(this,"$position", Toast.LENGTH_SHORT).show()

            //code for updating the clicked data
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.list_update_dialogbox)
            val updated=dialog.findViewById<EditText>(R.id.Update_box)
            val btn=dialog.findViewById<Button>(R.id.Save_btn_list)

            btn.setOnClickListener {
                array.set(position,updated.text.toString())
                arrayAdapter?.notifyDataSetChanged()
                dialog.dismiss()
            }
            dialog.show()
        }



        //setting up toast for item being long clicked in the list
        binding.ListView.setOnItemLongClickListener { parent, view, position, id ->
            //code for deleting the longclocked data
            val dialog=Dialog(this)
            dialog.setContentView(R.layout.list_delete_dialogbox)
            val btn1=dialog.findViewById<Button>(R.id.yes_btn_list)
            val btn2=dialog.findViewById<Button>(R.id.no_btn_list)
            dialog.show()
            btn1.setOnClickListener {
                array.removeAt(position)
                arrayAdapter?.notifyDataSetChanged()
                dialog.dismiss()
            }
            btn2.setOnClickListener {
                Toast.makeText(this,"deletion Canceled",Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            return@setOnItemLongClickListener true

        }



        //code for adding new data
        binding.FabBtn.setOnClickListener {                //adding alert dialog box to the floating action button
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.list_add_dialogbox)
            val entered_data = dialog.findViewById<EditText>(R.id.EnteredData)   //val entered_data=Entered data
            val btn = dialog.findViewById<Button>(R.id.Enterbtn)  //val btn = Enterbtn

            btn.setOnClickListener {             //adding functionality to the button click
                array.add(entered_data.text.toString())  //adding entered data in the list
                arrayAdapter?.notifyDataSetChanged()     //update the array when new element added
                dialog.dismiss()                         //close the dialog box
            }
            dialog.show()                                //open the dialog box
        }






    }
}