package com.websarva.wings.android.cameraintentsample

import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {

    private var _imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 200 && resultCode == RESULT_OK){
//            val bitmap = data?.getParcelableExtra<Bitmap>("data")
            val ivCamera = findViewById<ImageView>(R.id.ivCamera)
//            ivCamera .setImageBitmap(bitmap)
            ivCamera.setImageURI(_imageUri)
        }
    }

    fun onCameraImageClick(view: View){
        val dateFormat = SimpleDateFormat("yyyyMMddHHmmss")
        val now = Date()
        val nowStr = dateFormat.format(now)
        val fileName = "CameraIntentSamplePhoto_${nowStr}.jpg"
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE,fileName)
        values.put(MediaStore.Images.Media.MIME_TYPE,"image/jpeg")
        _imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values)
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT,_imageUri)
        startActivityForResult(intent, 200)
    }
}