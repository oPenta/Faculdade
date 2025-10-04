package com.example.ListaDeTarefas.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

enum class EffortLevel(val xp: Int, val gold: Int, val label: String, val color: Color) {
    EASY(10, 5, "Fácil", Color(0xFF4CAF50)),
    MEDIUM(30, 15, "Médio", Color(0xFFFF9800)),
    HARD(50, 25, "Difícil", Color(0xFFF44336)),
    CRITICAL(100, 50, "Crítico", Color(0xFF9C27B0))
}

data class Task(
    val id: Long,
    val description: String,
    val details: String = "",
    val isCompleted: Boolean = false,
    val effort: EffortLevel = EffortLevel.MEDIUM
)

data class HeroStatus(
    val level: Int = 1,
    val currentXp: Int = 0,
    val xpToNextLevel: Int = 100,
    val gold: Int = 0,
    val heroName: String = "Herói"
)

class BottomAppBarItem(val icon: ImageVector, val label: String)
class TopAppBarItem(var title: String)

sealed class ScreenItem(val route: String, val topAppBarItem: TopAppBarItem, val bottomAppBarItem: BottomAppBarItem? = null) {
    data object Home : ScreenItem("home", TopAppBarItem("Perfil do Herói"), BottomAppBarItem(Icons.Default.Home, "Início"))
    data object TaskList : ScreenItem("taskList", TopAppBarItem("Grimório de Missões"), BottomAppBarItem(Icons.Default.List, "Missões"))
    data object CompletedTasks : ScreenItem("completedTasks", TopAppBarItem("Missões Concluídas"), BottomAppBarItem(Icons.Default.CheckCircle, "Concluídas"))

    data object Login : ScreenItem(
        route = "login",
        topAppBarItem = TopAppBarItem(title = "Login / Cadastro"),
        bottomAppBarItem = BottomAppBarItem(icon = Icons.Default.Person, label = "Login")
    )

    data object Register : ScreenItem(route = "register", topAppBarItem = TopAppBarItem(title = "Cadastro"))
    data object TaskDetails : ScreenItem("taskDetails", TopAppBarItem("Detalhes da Missão"))

    companion object {
        fun getScreens(): List<ScreenItem> {
            return listOf(Home, TaskList, CompletedTasks, Login, Register, TaskDetails)
        }
    }
}