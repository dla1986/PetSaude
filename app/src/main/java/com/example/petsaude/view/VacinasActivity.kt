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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petsaude.ui.theme.*

data class Vacina(
    val nome: String,
    val aplicacao: String,
    val proxima: String,
    val lote: String,
    val veterinario: String,
    val status: String
)

class VacinasActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PetSaudeTheme {
                VacinasPage(onBackClick = { finish() })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VacinasPage(onBackClick: () -> Unit = {}) {
    val context = LocalContext.current
    val vacinas = listOf(
        Vacina("V10 (Múltipla)",      "09/12/2025", "09/12/2026", "VAC-2025-1234", "Dra. Maria Santos", "Aplicada"),
        Vacina("Antirrábica",         "14/01/2026", "14/01/2027", "RAB-2026-5678", "Dr. João Silva",    "Aplicada"),
        Vacina("Leishmaniose",        "31/05/2026", "-",           "-",             "-",                 "Pendente"),
        Vacina("Gripe Canina",        "19/11/2023", "19/05/2026", "GRIP-2025-9012","Dra. Maria Santos", "Vencida"),
        Vacina("Giardia",             "27/02/2026", "27/02/2027", "GIA-2026-3344", "Dr. João Silva",    "Aplicada"),
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
                text = "Vacinas",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Navy900,
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = { context.startActivity(Intent(context, AddVacinaActivity::class.java)) },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = GreenApplied),
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
            items(vacinas) { v ->
                VacinaCard(v)
            }
        }
    }
}

@Composable
fun VacinaCard(v: Vacina) {
    val (statusColor, statusBg, statusIcon) = when (v.status) {
        "Aplicada" -> Triple(GreenApplied, Color(0xFFDCFCE7), Icons.Default.CheckCircle)
        "Pendente" -> Triple(OrangeWarning, Color(0xFFFEF3C7), Icons.Default.Warning)
        "Vencida"  -> Triple(RedExpired,   Color(0xFFFEE2E2),  Icons.Default.Cancel)
        else       -> Triple(GrayText,     GrayBorder,          Icons.Default.Info)
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(statusIcon, contentDescription = null, tint = statusColor, modifier = Modifier.size(20.dp))
                    Spacer(Modifier.width(8.dp))
                    Text(v.nome, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = Navy900)
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(statusBg)
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(v.status, fontSize = 11.sp, fontWeight = FontWeight.Medium, color = statusColor)
                }
            }

            Spacer(Modifier.height(10.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                Column {
                    Text("Aplicação:", fontSize = 11.sp, color = GrayText)
                    Text(v.aplicacao, fontSize = 13.sp, color = Navy900, fontWeight = FontWeight.Medium)
                }
                Column {
                    Text("Próxima:", fontSize = 11.sp, color = GrayText)
                    Text(v.proxima, fontSize = 13.sp, color = Navy900, fontWeight = FontWeight.Medium)
                }
            }

            if (v.lote != "-") {
                Spacer(Modifier.height(6.dp))
                Text("Lote: ${v.lote}", fontSize = 12.sp, color = GrayText)
            }
            if (v.veterinario != "-") {
                Text("Vet: ${v.veterinario}", fontSize = 12.sp, color = GrayText)
            }
        }
    }
}
