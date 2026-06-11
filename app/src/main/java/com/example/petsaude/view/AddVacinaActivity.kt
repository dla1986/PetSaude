package com.example.petsaude.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petsaude.ui.theme.*

class AddVacinaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PetSaudeTheme {
                AddVacinaPage(onBackClick = { finish() })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddVacinaPage(onBackClick: () -> Unit = {}) {
    var nome by rememberSaveable { mutableStateOf("") }
    var data by rememberSaveable { mutableStateOf("") }
    var proxima by rememberSaveable { mutableStateOf("") }
    var lote by rememberSaveable { mutableStateOf("") }
    var veterinario by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Teal50, White)))
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar", tint = Navy900)
            }
            Text("Registrar Vacina", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Navy900)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
        ) {
            Spacer(Modifier.height(16.dp))
            
            SectionLabel("INFORMAÇÕES DA VACINA")
            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome da Vacina") },
                leadingIcon = { Icon(Icons.Default.Vaccines, null, tint = Teal500) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = petFieldColors()
            )

            Spacer(Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = data,
                    onValueChange = { data = it },
                    label = { Text("Data Aplicação") },
                    placeholder = { Text("DD/MM/AAAA") },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = petFieldColors()
                )
                OutlinedTextField(
                    value = proxima,
                    onValueChange = { proxima = it },
                    label = { Text("Próxima Dose") },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = petFieldColors()
                )
            }

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = lote,
                onValueChange = { lote = it },
                label = { Text("Lote (Opcional)") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = petFieldColors()
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = veterinario,
                onValueChange = { veterinario = it },
                label = { Text("Veterinário Responsável") },
                leadingIcon = { Icon(Icons.Default.Person, null, tint = Teal500) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = petFieldColors()
            )

            Spacer(Modifier.height(32.dp))

            Button(
                onClick = { onBackClick() },
                modifier = Modifier.fillMaxWidth().height(52.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = GreenApplied)
            ) {
                Icon(Icons.Default.Check, null)
                Spacer(Modifier.width(8.dp))
                Text("Salvar Vacina", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}
