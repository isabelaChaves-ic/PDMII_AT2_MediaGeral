package com.caio.atv2_mediageral.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

enum class StatusAluno(val descricao: String) {
    REPROVADO("Reprovado"),
    APROVADO("Aprovado"),
    OTIMO_APROVEITAMENTO("Ótimo Aproveitamento"),
    PENDENTE("Aguardando cálculo...")
}

data class Aluno(
    var nomeCompleto: String,
    val notas: MutableList<Double> = mutableListOf()
) {

    fun calcularMedia(): Double {
        if (notas.isEmpty()) {
            return 0.0
        }
        return notas.average()
    }

    fun obterStatus(): StatusAluno {
        val media = calcularMedia()

        return when {
            media > 9.0 -> StatusAluno.OTIMO_APROVEITAMENTO
            media >= 6.0 -> StatusAluno.APROVADO
            else -> StatusAluno.REPROVADO
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaScreen() {

    var nome by remember { mutableStateOf("") }
    var tp1Input by remember { mutableStateOf("") }
    var tp2Input by remember { mutableStateOf("") }
    var tp3Input by remember { mutableStateOf("") }

    var alunoCalculado by remember { mutableStateOf<Aluno?>(null) }
    var mediaFinal by remember { mutableStateOf<Double?>(null) }
    var statusFinal by remember { mutableStateOf(StatusAluno.PENDENTE) }

    val numberRegex = remember { Regex("^\\d*[,.]?\\d*\$") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("T2 | Média Geral") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Controle de Notas do Aluno",
                style = MaterialTheme.typography.headlineSmall
            )

            TextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome Completo do Aluno") },
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                NotaInputField(
                    label = "TP1",
                    value = tp1Input,
                    onValueChange = { novaString ->
                        if (novaString.isEmpty() || novaString.matches(numberRegex)) {
                            tp1Input = novaString
                        }
                    },
                    modifier = Modifier.weight(1f)
                )
                NotaInputField(
                    label = "TP2",
                    value = tp2Input,
                    onValueChange = { novaString ->
                        if (novaString.isEmpty() || novaString.matches(numberRegex)) {
                            tp2Input = novaString
                        }
                    },
                    modifier = Modifier.weight(1f)
                )
                NotaInputField(
                    label = "TP3",
                    value = tp3Input,
                    onValueChange = { novaString ->
                        if (novaString.isEmpty() || novaString.matches(numberRegex)) {
                            tp3Input = novaString
                        }
                    },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val nota1 = tp1Input.replace(",", ".").toDoubleOrNull() ?: 0.0
                    val nota2 = tp2Input.replace(",", ".").toDoubleOrNull() ?: 0.0
                    val nota3 = tp3Input.replace(",", ".").toDoubleOrNull() ?: 0.0

                    val aluno = Aluno(nomeCompleto = nome)

                    aluno.notas.clear()
                    aluno.notas.add(nota1)
                    aluno.notas.add(nota2)
                    aluno.notas.add(nota3)

                    mediaFinal = aluno.calcularMedia()
                    statusFinal = aluno.obterStatus()
                    alunoCalculado = aluno

                    nome = ""
                    tp1Input = ""
                    tp2Input = ""
                    tp3Input = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calcular Média")
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (mediaFinal != null && alunoCalculado != null) {
                ResultadoCard(
                    nome = alunoCalculado!!.nomeCompleto,
                    media = mediaFinal!!,
                    status = statusFinal
                )
            }
        }
    }
}

@Composable
fun NotaInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier
    )
}

@Composable
fun ResultadoCard(nome: String, media: Double, status: StatusAluno) {
    val statusColor = when (status) {
        StatusAluno.REPROVADO -> Color(0xFFEF5350)
        StatusAluno.APROVADO -> Color.Blue.copy(alpha = 0.7f)
        StatusAluno.OTIMO_APROVEITAMENTO -> Color(0xFFE8F5E9)
        StatusAluno.PENDENTE -> Color.Gray
    }

    val statusTextColor = when (status) {
        StatusAluno.OTIMO_APROVEITAMENTO -> Color.Black
        else -> Color.White
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Resultado para:",
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = nome,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Média Geral: ${String.format("%.2f", media)}",
                style = MaterialTheme.typography.headlineMedium
            )
            Box(
                modifier = Modifier
                    .background(color = statusColor, shape = RoundedCornerShape(4.dp))
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(
                    text = status.descricao,
                    color = statusTextColor,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}