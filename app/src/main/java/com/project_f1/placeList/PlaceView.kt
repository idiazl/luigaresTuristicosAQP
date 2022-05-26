package com.project_f1.placeList

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.project_f1.MainActivity

class PlaceView: ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            personalData ()
        }
    }
}

@Composable
private fun personalData(){
    val platosArequipa = arrayOf("Chaque", "Adobo", "Chairo")
    val platosCusco= arrayOf("Chiri Uchu", "Trucha Frita", "Chairo")

    val context = LocalContext.current
    val intent = (context as PlaceView).intent
    val name = intent.getStringExtra("name")
    Column(Modifier.padding(4.dp) ) {
        if (name == "Arequipa"){
            for (index in platosArequipa.indices){
                Text(text = platosArequipa.get(index))
            }
        }
        if (name == "Cusco"){
            for (index in platosCusco.indices){
                Text(text = platosCusco.get(index))
            }
        }
        backActivity()
    }

}

@Composable
fun backActivity(){
    val context = LocalContext.current
    Button(onClick = {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }) {
        Text(text = "Atras")
    }
}