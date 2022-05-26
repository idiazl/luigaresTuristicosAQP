package com.project_f1

import android.content.Intent
import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project_f1.placeList.PlaceView
import com.project_f1.ui.theme.Project_f1Theme

class detailTuristPlace : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                backActivity()
                TuristDetail()
            }

        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TuristDetail(){
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val intent = (context as detailTuristPlace).intent
    val name = intent.getStringExtra("name")
    val description = intent.getStringExtra("description")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(7.dp)
            .verticalScroll(state = scrollState)
    ) {
        if (name == "Convento de santa catalina"){
            Image(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                painter = painterResource(R.drawable.convento_santa_catalina),
                contentDescription ="Lugar turistico de Arequipa"
            )
        }
        if (name == "Molino de Sabandía"){
            Image(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                painter = painterResource(R.drawable.molino_de_sabandia),
                contentDescription ="Lugar turistico de Arequipa"
            )
        }
        if (name == "Cataratas de Capua"){
            Image(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                painter = painterResource(R.drawable.catarata_capua),
                contentDescription ="Lugar turistico de Arequipa"
            )
        }
        if (name == "Ascenso  Al Volcan Misti"){
            Image(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                painter = painterResource(R.drawable.misti),
                contentDescription ="Lugar turistico de Arequipa"
            )
        }

        Text(
            modifier = Modifier.padding(4.dp).align(CenterHorizontally),
            text = name.toString(),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Text(text = description.toString())
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