package com.example.ListaDeTarefas.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ListaDeTarefas.data.HeroStatus
import com.example.ListaDeTarefas.data.Task
import com.example.ListaDeTarefas.ui.theme.*

@Composable
fun HomeScreen(heroStatus: HeroStatus, tasks: List<Task>, modifier: Modifier = Modifier) {
    val completedTasks = tasks.count { it.isCompleted }
    val pendingTasks = tasks.size - completedTasks
    val xpPercentage = (heroStatus.currentXp.toFloat() / heroStatus.xpToNextLevel.toFloat()).coerceIn(0f, 1f)

    Surface(modifier = modifier.fillMaxSize(), color = BackgroundLight) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                "Herói: ${heroStatus.heroName}",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = TextColorLight
            )

            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(CardSurfaceLight)
                    .padding(8.dp)
                    .clip(CircleShape)
                    .background(XpColor.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.AccountCircle,
                    contentDescription = "Avatar do Herói",
                    modifier = Modifier.size(100.dp),
                    tint = XpColor
                )
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = CardSurfaceLight),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, DividerColorLight)
            ) {
                Column(Modifier.padding(16.dp)) {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Nível ${heroStatus.level}", fontWeight = FontWeight.ExtraBold, color = XpColor, fontSize = 20.sp)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Filled.MonetizationOn, contentDescription = "Ouro", tint = GoldColor, modifier = Modifier.size(20.dp))
                            Spacer(Modifier.width(6.dp))
                            Text("${heroStatus.gold}", fontWeight = FontWeight.ExtraBold, color = GoldColor)
                        }
                    }
                    Spacer(Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(22.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Color.Gray.copy(alpha = 0.2f))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(xpPercentage)
                                .height(22.dp)
                                .background(XpColor)
                                .animateContentSize()
                        )
                        Text(
                            "${heroStatus.currentXp}/${heroStatus.xpToNextLevel} XP",
                            modifier = Modifier.align(Alignment.Center),
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("Resumo de Missões", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = TextColorLight, modifier = Modifier.fillMaxWidth())
                TaskStatusCard("Missões Pendentes", pendingTasks, Icons.Default.Warning, DangerRed)
                TaskStatusCard("Missões Concluídas", completedTasks, Icons.Default.CheckCircle, SuccessGreen)
            }
        }
    }
}


@Composable
fun TaskStatusCard(title: String, count: Int, icon: ImageVector, color: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardSurfaceLight),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(1.dp, DividerColorLight)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(color.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(26.dp))
            }
            Text(title, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold, color = TextColorLight, modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp))
            Text(count.toString(), style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = color)
        }
    }
}