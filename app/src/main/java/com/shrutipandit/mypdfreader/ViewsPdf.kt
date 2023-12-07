package com.shrutipandit.mypdfreader

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.shrutipandit.mypdfreader.databinding.ActivityViewsPdfBinding

class ViewsPdf : AppCompatActivity() {
    private lateinit var binding: ActivityViewsPdfBinding
    val PDF_SELECTION_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewsPdfBinding.inflate(layoutInflater)
        setContentView(binding.root)
        selectedpdffromsstorage()



    }
    private fun selectedpdffromsstorage(){
        Toast.makeText(this , "pdf chooser", Toast.LENGTH_SHORT).show()
        val pdfStorage = Intent(Intent.ACTION_GET_CONTENT)
        pdfStorage.type = "applicationpdf"
        pdfStorage.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(Intent.createChooser(pdfStorage,"selectedPdf"),PDF_SELECTION_CODE)

    }

    fun showPdfFromUri(uri:uri){
        binding.pdfView.fromUri(uri)
            .defaultpage(0)
            .spacing(10)
            .load()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==PDF_SELECTION_CODE && resultCode == Activity.RESULT_OK && data !=null){
            val selectedpdf = data.data
            showPdfFromUri(selectedpdf)
        }
    }



}