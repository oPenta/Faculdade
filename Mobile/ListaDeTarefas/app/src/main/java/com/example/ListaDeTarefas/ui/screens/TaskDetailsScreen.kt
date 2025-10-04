package com.example.ListaDeTarefas.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ListaDeTarefas.data.Task
import com.example.ListaDeTarefas.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun TaskDetailsScreen(
    task: Task?,
    onCompleteTask: (Task) -> Unit,
    onUpdateDescription: (Task, String) -> Unit,
    onUpdateDetails: (Task, String) -> Unit
) {
    if (task == null) {
        Box(
            Modifier
                .fillMaxSize()
                .background(BackgroundLight), contentAlignment = Alignment.Center) {
            Text("Selecione uma missão.", fontSize = 20.sp, color = Color.Gray)
        }
        return
    }

    var currentDescription by remember(task.id) { mutableStateOf(task.description) }
    var currentDetails by remember(task.id) { mutableStateOf(task.details) }
    var justCompleted by remember(task.id) { mutableStateOf(false) }
    val haptics = LocalHapticFeedback.current

    val animatedBorderColor by animateColorAsState(
        targetValue = if (justCompleted) SuccessGreen else Color.Transparent,
        animationSpec = tween(durationMillis = 400),
        label = "borderColorAnimation"
    )

    LaunchedEffect(justCompleted) {
        if (justCompleted) {
            delay(1500)
            justCompleted = false
        }
    }

    Surface(modifier = Modifier.fillMaxSize(), color = BackgroundLight) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = CardSurfaceLight),
                border = BorderStroke(2.dp, animatedBorderColor)
            ) {
                Column(Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        val isCompleted = task.isCompleted
                        val statusColor = if (isCompleted) SuccessGreen else DangerRed
                        Icon(if (isCompleted) Icons.Default.CheckCircle else Icons.Default.HourglassTop, "Status", tint = statusColor)
                        Text(if (isCompleted) "MISSÃO CONCLUÍDA" else "MISSÃO PENDENTE", color = statusColor, fontWeight = FontWeight.ExtraBold)
                    }
                    Divider(Modifier.padding(vertical = 12.dp), color = DividerColorLight)
                    Text("Recompensas", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold, color = Color.Gray)
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp), horizontalArrangement = Arrangement.SpaceAround) {
                        RewardChip("+${task.effort.xp} XP", XpColor, Icons.Default.Star)
                        RewardChip("+${task.effort.gold} Ouro", GoldColor, Icons.Default.MonetizationOn)
                    }
                    Divider(Modifier.padding(vertical = 12.dp), color = DividerColorLight)
                    OutlinedTextField(
                        value = currentDescription,
                        onValueChange = { currentDescription = it },
                        label = { Text("Nome da Missão") },
                        leadingIcon = { Icon(Icons.Filled.Label, null) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        colors = getTextFieldColorsLight(),
                        enabled = !task.isCompleted
                    )
                    Spacer(Modifier.height(12.dp))
                    OutlinedTextField(
                        value = currentDetails,
                        onValueChange = { currentDetails = it },
                        placeholder = { Text("Adicione os passos ou informações aqui...") },
                        leadingIcon = { Icon(Icons.Filled.Notes, null) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 150.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = getTextFieldColorsLight(),
                        enabled = !task.isCompleted
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            val canSaveChanges = (currentDescription.isNotBlank() && currentDescription != task.description) || (currentDetails != task.details)
            Button(
                onClick = {
                    onUpdateDescription(task, currentDescription)
                    onUpdateDetails(task, currentDetails)
                },
                enabled = canSaveChanges && !task.isCompleted,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Icon(Icons.Default.Save, null, Modifier.size(ButtonDefaults.IconSize))
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Salvar Alterações")
            }
            Spacer(Modifier.height(8.dp))

            if (!task.isCompleted) {
                Button(
                    onClick = {
                        justCompleted = true
                        haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                        onCompleteTask(task)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen)
                ) {
                    Icon(Icons.Default.CheckCircle, null, Modifier.size(ButtonDefaults.IconSize))
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text("Completar Missão")
                }
            }
        }
    }
}

@Composable
private fun RewardChip(value: String, color: Color, icon: ImageVector) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(color.copy(alpha = 0.1f))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Icon(icon, null, tint = color, modifier = Modifier.size(18.dp))
        Spacer(Modifier.width(6.dp))
        Text(value, color = color, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }
}