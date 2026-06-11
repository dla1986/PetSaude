package com.example.petsaude.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import android.content.Intent
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petsaude.ui.theme.*

data class Consulta(
    val titulo: String,
    val veterinario: String,
    val data: String,
    val hora: String,
    val local: String,
    val status: String
)

class ConsultasActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PetSaudeTheme {
                ConsultasPage(onBackClick = { finish() })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConsultasPage(onBackClick: () -> Unit = {}) {
    val context = LocalContext.current
    val consultas = listOf(
        Consulta("Consulta de rotina",   "Dr. João Silva",    "14/05/2026", "14:00", "Clínica PetVida",            "Agendada"),
        Consulta("Exame de sangue",      "Dra. Maria Santos", "09/04/2026", "10:30", "Hospital Veterinário Central","Concluída"),
        Consulta("Vacinação anual",      "Dr. João Silva",    "19/06/2026", "16:00", "Clínica PetVida",            "Agendada"),
        Consulta("Consulta dermatológica","Dra. Ana Lima",    "22/03/2026", "09:00", "Clínica AnimaVet",           "Concluída"),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBg)
            .statusBarsPadding()
    ) {
        // Top bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(White)
                .padding(horizontal = 8.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar", tint = Navy900)
            }
            Text(
                text = "Consultas",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Navy900,
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = { context.startActivity(Intent(context, AddConsultaActivity::class.java)) },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Teal500),
                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
                modifier = Modifier.height(36.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(4.dp))
                Text("Nova", fontSize = 13.sp)
            }
            Spacer(Modifier.width(8.dp))
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(consultas) { c ->
                ConsultaCard(c)
            }
        }
    }
}

@Composable
fun ConsultaCard(c: Consulta) {
    val (statusColor, statusBg) = when (c.status) {
        "Agendada" -> Teal500 to Teal100
        "Concluída" -> GrayText to GrayBorder
        else        -> OrangeWarning to Color(0xFFFEF3C7)
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(c.titulo, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = Navy900)
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(statusBg)
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(c.status, fontSize = 11.sp, fontWeight = FontWeight.Medium, color = statusColor)
                }
            }
            Text(c.veterinario, fontSize = 13.sp, color = GrayText)
            Spacer(Modifier.height(10.dp))
            InfoRow(Icons.Default.CalendarMonth, c.data)
            Spacer(Modifier.height(4.dp))
            InfoRow(Icons.Default.Schedule, c.hora)
            Spacer(Modifier.height(4.dp))
            InfoRow(Icons.Default.LocationOn, c.local)
            Spacer(Modifier.height(10.dp))
            TextButton(
                onClick = {},
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(Icons.Default.Map, contentDescription = null, tint = Teal500, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(4.dp))
                Text("Ver no mapa", color = Teal500, fontSize = 13.sp)
            }
        }
    }
}

@Composable
fun InfoRow(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = Teal500, modifier = Modifier.size(15.dp))
        Spacer(Modifier.width(6.dp))
        Text(text, fontSize = 13.sp, color = GrayText)
    }
}
