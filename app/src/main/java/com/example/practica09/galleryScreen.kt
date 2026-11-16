package com.example.practica09

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.Alignment

@Composable
fun GaleriaScreen() {

    var imagenSeleccionada by remember {
        mutableStateOf<String?>(null)
    }

    // Lista de URLs de imágenes de prueba
    val imagenes = listOf(
        "https://picsum.photos/id/1018/400/400",
        "https://picsum.photos/id/1015/400/400",
        "https://picsum.photos/id/1019/400/400",
        "https://picsum.photos/id/1020/400/400",
        "https://picsum.photos/id/1021/400/400",
        "https://picsum.photos/id/1022/400/400",
        "https://picsum.photos/id/1023/400/400",
        "https://picsum.photos/id/1024/400/400",
        "https://picsum.photos/id/1025/400/400",
        "https://picsum.photos/id/1026/400/400"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .padding(top = 32.dp)
    ) {

        Text(
            text = "Mi Galería",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(8.dp)
        )

        // Galería de imágenes en cuadrícula
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {

            items(imagenes) { url ->
                ImagenItem(
                    url = url,
                    onClick = {
                        imagenSeleccionada = url
                    }
                )
            }

        }
    }

    if (imagenSeleccionada != null) {
        Dialog(
            onDismissRequest = {
                imagenSeleccionada = null
            }
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = imagenSeleccionada,
                        contentDescription = "Imagen ampliada",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }

}

@Composable
fun ImagenItem(
    url: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        AsyncImage(
            model = url,
            contentDescription = "Imagen de la galería",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

