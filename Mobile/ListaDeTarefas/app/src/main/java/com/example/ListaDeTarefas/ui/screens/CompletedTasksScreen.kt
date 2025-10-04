package com.example.ListaDeTarefas.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ListaDeTarefas.data.Task
import com.example.ListaDeTarefas.ui.theme.BackgroundLight
import com.example.ListaDeTarefas.ui.theme.DividerColorLight
import com.example.ListaDeTarefas.ui.theme.TextColorLight

@Composable
fun CompletedTasksScreen(
    tasks: List<Task>,
    onTaskClick: (Task) -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize(), color = BackgroundLight) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            if (tasks.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Nenhuma missão concluída ainda.", fontSize = 18.sp, color = Color.Gray)
                }
            } else {
                Text("Histórico de Conquistas", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = TextColorLight, modifier = Modifier.padding(bottom = 16.dp))
                LazyColumn(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(tasks) { task ->
                        CompletedTaskItemRow(task = task, onTaskClick = onTaskClick)
                    }
                }
            }
        }
    }
}

@Composable
fun CompletedTaskItemRow(task: Task, onTaskClick: (Task) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onTaskClick(task) },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EAED)),
        border = BorderStroke(1.dp, DividerColorLight)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Check, null, tint = Color.Gray)
            Spacer(Modifier.width(12.dp))
            Text(
                task.description,
                color = Color.Gray,
                textDecoration = TextDecoration.LineThrough,
                modifier = Modifier.weight(1f)
            )
            Icon(Icons.Default.ChevronRight, "Ver detalhes", tint = Color.Gray)
        }
    }
}