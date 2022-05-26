package com.project_f1

import android.content.Intent
import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project_f1.placeList.PlaceView
import com.project_f1.placeList.placeListScreen
import com.project_f1.ui.theme.Project_f1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            placeListScreen()
        }
    }
}

@Composable
fun placeListScreen(
){
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                text = "LUGARES TURÍSTICOS DE AREQUIPA"
            )

            SearchBar(
                hint = "Buscar...",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ){ }
            Cuerpo()
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit ={}
){
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier){
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = it.isFocused != true
                }
        )
        if (isHintDisplayed){
            Text(
                text = hint,
                color = Color.Gray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}

@Composable
fun Cuerpo(){
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Column(Modifier.verticalScroll(state = scrollState)){
        for (index in listaLugares.indices) {
            Button(
                onClick = {
                    val intent = Intent(context, detailTuristPlace::class.java)
                    intent.putExtra("name", listaLugares[index].name)
                    intent.putExtra("description", listaLugares[index].description)
                    intent.putExtra("image", listaLugares[index].image)
                    context.startActivity(intent)

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)

                // Uses ButtonDefaults.ContentPadding by default

            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Image(
                        modifier = Modifier
                            .size(80.dp)
                            .padding(8.dp),
                        painter = painterResource(id = listaLugares[index].image),
                        contentDescription = "Imagen de lugar turistico"
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .weight(1f)
                    ) {
                        Text(
                            modifier = Modifier.padding(4.dp),
                            text = listaLugares[index].name,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            modifier = Modifier.padding(4.dp),
                            text = "Arequipa")
                    }
                }
            }
        }
    }

}

data class lugarTuristico (
    val name: String,
    val description: String,
    @DrawableRes val image: Int
    )

public val listaLugares = listOf(
    lugarTuristico(
        name = "Convento de santa catalina",
        description= "El Monasterio de Santa Catalina de Siena, o Convento de Santa Catalina, es un complejo turístico religioso ubicado en el centro histórico de Arequipa, departamento de Arequipa, Perú",
        R.drawable.convento_santa_catalina
    ),
    lugarTuristico(
        name = "Molino de Sabandía",
        description = "fue construido aproximadamente durante el año 1875. Hace uso de las aguas de un manantial, representa la arquitectura típica de la región en la que predominó el uso del sillar, constituyendo un monumento histórico que trae a la memoria épocas antiguas y el ingenio y trabajo de la gente arequipeña.",
        R.drawable.molino_de_sabandia
    ),
    lugarTuristico(
        name = "Cataratas de Capua",
        description = "La Catarata de Corontorio es también conocida con el nombre de Capua, o Ccapua. Esta catarata se ubica en el distrito de Yura, en la provincia y región de Arequipa. El nombre de Capua que presenta esta caída de agua, se debe al nombre del Río Capua, que nutre la catarata. La Catarata de Corontorio, cuenta con una pendiente de aproximadamente treinta metros y sus aguas se precipitan en medio de enormes formaciones rocosas. La mejor época para poder apreciar el espectáculo que ofrece esta caída de agua, es entre los meses de  diciembre a abril, cuando las constantes lluvias hacen que el caudal aumente.",
        R.drawable.catarata_capua
    ),
    lugarTuristico(
        name = "Ascenso  Al Volcan Misti",
        description = "El volcán Misti es el símbolo mas popular de la ciudad de Arequipa hablar de este volcán es hablar de los arequipeños y de su rica historia. Gracias a este volcán y a sus erupciones acidas (cenizas), actualmente la ciudad de Arequipa cuenta con una de las mas ricas tierras para la actividad agrícola, al pie del volcán Misti se encuentran ricos valles, como el valle de chilina, habitado desde antes de la llegada de los españoles en el año de 1540. Escalar este volcán es hacer solo un trekking de altura. La única dificultad que tendremos al escalar este volcán son las cenizas volcánicas  y la arena que en algunos casos forman dunas. El hecho de que encontremos estos elementos en el volcán hace nuestro ascenso un poco más difícil y forzado. Salimos desde la ciudad de Arequipa a las 8 am y nos dirigimos en camioneta 4x4 hasta los 3300 metros de altura durante 2 horas.Desde aquí caminamos hasta el campamento base (nido de águilas situado a 4800 metros  durante 6 horas de caminata.Noche en carpas.Luego del desayuno partimos a las 3 de la mañana para atacar la cima 5825 metros de altura durante 7 horas y luego descendemos desde la cumbre 5825 metros de altura hasta los 3300 metros de altura donde nos espera nuestro vihuculo. Retorno a la ciudad de Arequipa.",
        R.drawable.misti
    )
)