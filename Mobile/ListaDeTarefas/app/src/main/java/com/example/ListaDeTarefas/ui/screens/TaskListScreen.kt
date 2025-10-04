package com.example.ListaDeTarefas.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ListaDeTarefas.data.EffortLevel
import com.example.ListaDeTarefas.data.Task
import com.example.ListaDeTarefas.ui.theme.*

@Composable
fun TaskListScreen(
    tasks: List<Task>,
    onTaskClick: (Task) -> Unit,
    onAddTask: (String, EffortLevel) -> Unit
) {
    var newTaskText by remember { mutableStateOf("") }
    var selectedEffort by remember { mutableStateOf(EffortLevel.MEDIUM) }
    val focusRequester = remember { FocusRequester() }

    Surface(modifier = Modifier.fillMaxSize(), color = BackgroundLight) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            Text("Criar Nova Missão", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.ExtraBold, color = TextColorLight)
            Spacer(Modifier.height(16.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                EffortLevel.entries.forEach { effort ->
                    val isSelected = selectedEffort == effort
                    Button(
                        onClick = { selectedEffort = effort },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSelected) effort.color else CardSurfaceLight,
                            contentColor = if (isSelected) Color.White else effort.color
                        ),
                        border = if (!isSelected) BorderStroke(1.dp, effort.color) else null,
                        elevation = ButtonDefaults.buttonElevation(if (isSelected) 4.dp else 1.dp)
                    ) {
                        Text(effort.label, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = newTaskText,
                    onValueChange = { newTaskText = it },
                    label = { Text("Nome da Missão") },
                    colors = getTextFieldColorsLight(),
                    shape = RoundedCornerShape(12.dp),
                    textStyle = TextStyle(color = TextColorLight),
                    modifier = Modifier
                        .weight(1f)
                        .focusRequester(focusRequester),
                    singleLine = true
                )
                Button(
                    onClick = {
                        if (newTaskText.isNotBlank()) {
                            onAddTask(newTaskText, selectedEffort)
                            newTaskText = ""
                            focusRequester.requestFocus()
                        }
                    },
                    enabled = newTaskText.isNotBlank(),
                    modifier = Modifier.size(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Adicionar Missão")
                }
            }

            if (tasks.isEmpty()) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), contentAlignment = Alignment.Center) {
                    Text("Nenhuma missão pendente.", fontSize = 18.sp, color = Color.Gray, textAlign = TextAlign.Center)
                }
            } else {
                LazyColumn(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(tasks) { task ->
                        TaskListItem(task = task, onTaskClick = onTaskClick)
                    }
                }
            }
        }
    }
}


@Composable
fun TaskListItem(task: Task, onTaskClick: (Task) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onTaskClick(task) },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardSurfaceLight),
        border = BorderStroke(1.dp, DividerColorLight)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(task.effort.color.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Star, "Esforço", tint = task.effort.color, modifier = Modifier.size(26.dp))
            }
            Column(Modifier
                .padding(horizontal = 16.dp)
                .weight(1f)) {
                Text(task.description, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = TextColorLight)
                Row(Modifier.padding(top = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("+${task.effort.xp} XP", fontWeight = FontWeight.SemiBold, color = XpColor, fontSize = 14.sp)
                    Spacer(Modifier.width(12.dp))
                    Text("+${task.effort.gold} Ouro", fontWeight = FontWeight.SemiBold, color = GoldColor, fontSize = 14.sp)
                }
            }
            Icon(Icons.Default.ChevronRight, "Ver detalhes", tint = Color.Gray.copy(alpha = 0.7f))
        }
    }
}