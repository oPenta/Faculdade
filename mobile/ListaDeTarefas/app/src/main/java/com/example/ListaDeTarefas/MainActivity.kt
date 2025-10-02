package com.example.ListaDeTarefas

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ListaDeTarefas.ui.theme.AulaTelasTheme
import com.example.ListaDeTarefas.ui.theme.green

// CLASSE DE DADOS
data class Task(
    val id: Long,
    val description: String,
    val details: String = "",
    val isCompleted: Boolean = false
)

// CLASSE PRINCIPAL
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AulaTelasTheme {
                App()
            }
        }
    }
}

// CLASSES AUXILIARES DE NAVEGAÇÃO
class BottomAppBarItem(
    val icon: ImageVector,
    val label: String
)

class TopAppBarItem(
    var title: String,
    val icons: List<ImageVector> = emptyList()
)

// As 4 Telas
sealed class ScreenItem(
    val topAppBarItem: TopAppBarItem,
    val bottomAppBarItem: BottomAppBarItem

) {
    data object Home : ScreenItem( // Tela 0: HOME
        topAppBarItem = TopAppBarItem(
            title = "Dashboard",
            icons = listOf(Icons.Default.AccountCircle, Icons.Default.MoreVert)
        ),
        bottomAppBarItem = BottomAppBarItem(icon = Icons.Default.Home, label = "Início")
    )

    data object TaskList : ScreenItem( // TELA 1: LISTA DE TAREFAS PENDENTES
        topAppBarItem = TopAppBarItem(
            title = "Tarefas Pendentes",
            icons = listOf(Icons.Default.Add, Icons.Default.MoreVert)
        ),
        bottomAppBarItem = BottomAppBarItem(icon = Icons.Default.List, label = "Pendentes")
    )

    data object TaskDetails : ScreenItem( // TELA 2: DETALHES/EDIÇÃO DA TAREFA
        topAppBarItem = TopAppBarItem(
            title = "Detalhes da Tarefa",
            icons = listOf(Icons.Default.MoreVert)
        ),
        bottomAppBarItem = BottomAppBarItem(
            icon = Icons.Default.Description,
            label = "Detalhes"
        )
    )

    data object CompletedTasks : ScreenItem( // TELA 3: TAREFAS CONCLUÍDAS
        topAppBarItem = TopAppBarItem(
            title = "Tarefas Concluídas",
            icons = listOf(Icons.Default.CheckCircle, Icons.Default.MoreVert)
        ),
        bottomAppBarItem = BottomAppBarItem(
            icon = Icons.Default.CheckCircle,
            label = "Concluídas"
        )
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun App() {
    val screens = remember {
        listOf(
            ScreenItem.Home,
            ScreenItem.TaskList,
            ScreenItem.TaskDetails,
            ScreenItem.CompletedTasks
        )
    }

    var currentScreen by remember {
        mutableStateOf(screens.first())
    }

    // Lista de Tarefas
    var tasks by remember {
        mutableStateOf(
            listOf(
                Task(1, "Fazer Trabalho do Baião", "Entregar até sexta", isCompleted = true),
                Task(2, "Beber na faculdade ", "Ir pra faculdade, cabular aula e tomar uma bebidinha pra alegrar", isCompleted = false),
                Task(3, "Jogar sinuca", "Ir pra faculdade, cabular aula e amassar os patos na sinuca", isCompleted = false),
                Task(4, "Aprender a programar", "Sei la, as IA faz tudo", isCompleted = false),
                Task(5, "Ir para academia", "Faz anos que isso ta aqui", isCompleted = false),
                Task(6, "Responder a namorada", "Sei la, coloquei so pelo meme, nem namorada eu tenho", isCompleted = false),
                Task(7, "Finalizar o jogos", "Se é loco da mo trabalho fazer isso, sem contar que eu nem sei fazer", isCompleted = false),
                Task(8, "Desistalar lol", "Essa é importante pff faça", isCompleted = false),
            )
        )
    }

    var selectedTask by remember {
        mutableStateOf<Task?>(null)
    }

    val addTask: (String) -> Unit = { description ->
        val newId = (tasks.maxOfOrNull { it.id } ?: 0L) + 1
        val newTask = Task(id = newId, description = description, details = "Coloca os detalhes.")
        tasks = tasks + newTask
        Log.d("TASK", "Tarefa adicionada: ${newTask.description}")
    }

    val toggleTaskCompletion: (Task) -> Unit = { taskToToggle ->
        tasks = tasks.map {
            if (it.id == taskToToggle.id) {
                it.copy(isCompleted = !it.isCompleted)
            } else {
                it
            }
        }
        Log.d("TASK", "Status alterado para: ${taskToToggle.description}. Novo status: ${!taskToToggle.isCompleted}")
    }

    val navigateToDetails: (Task) -> Unit = { task ->
        selectedTask = task
        currentScreen = ScreenItem.TaskDetails
    }

    val updateTaskDescription: (Task, String) -> Unit = { taskToUpdate, newDescription ->
        tasks = tasks.map {
            if (it.id == taskToUpdate.id) {
                it.copy(description = newDescription)
            } else {
                it
            }
        }
        selectedTask = selectedTask?.copy(description = newDescription)
        Log.d("TASK", "Descrição principal atualizada para: $newDescription")
    }

    val updateTaskDetails: (Task, String) -> Unit = { taskToUpdate, newDetails ->
        tasks = tasks.map {
            if (it.id == taskToUpdate.id) {
                it.copy(details = newDetails)
            } else {
                it
            }
        }
        selectedTask = selectedTask?.copy(details = newDetails)
        Log.d("TASK", "Detalhes da tarefa atualizados.")
    }

    val pagerState = rememberPagerState {
        screens.size
    }

    LaunchedEffect(currentScreen) {
        pagerState.animateScrollToPage(screens.indexOf(currentScreen))
    }

    LaunchedEffect(pagerState.targetPage) {
        currentScreen = screens[pagerState.targetPage]
        if (currentScreen != ScreenItem.TaskDetails) {
            selectedTask = null
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = {
                Text(currentScreen.topAppBarItem.title)
            }, actions = {
                Row(
                    Modifier.padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    currentScreen.topAppBarItem.icons.forEach { icon ->
                        Icon(icon, contentDescription = null)
                    }
                }
            })
        },
        bottomBar = {
            BottomAppBar {
                screens.forEach { screen ->
                    with(screen.bottomAppBarItem) {
                        NavigationBarItem(
                            selected = screen == currentScreen,
                            onClick = {
                                currentScreen = screen
                                if (screen != ScreenItem.TaskDetails) {
                                    selectedTask = null
                                }
                            },
                            icon = { Icon(icon, contentDescription = null) },
                            label = { Text(label) }
                        )
                    }
                }
            }
        }

    ) { innerPadding ->

        HorizontalPager(
            pagerState,
            Modifier.padding(innerPadding)
        ) { page ->
            when (screens[page]) {
                ScreenItem.TaskList -> TaskListScreen(
                    tasks = tasks.filter { !it.isCompleted },
                    onTaskClick = navigateToDetails,
                    onAddTask = addTask
                )
                ScreenItem.TaskDetails -> TaskDetailsScreen(
                    task = selectedTask,
                    onToggleCompletion = toggleTaskCompletion,
                    onUpdateDescription = updateTaskDescription,
                    onUpdateDetails = updateTaskDetails
                )
                ScreenItem.Home -> HomeScreen(tasks = tasks)

                ScreenItem.CompletedTasks -> CompletedTasksScreen(
                    tasks = tasks.filter { it.isCompleted },
                    onToggleCompletion = toggleTaskCompletion
                )
            }
        }
    }
}

// 0. TELA INICIAL (DASHBOARD)
@Composable
fun HomeScreen(tasks: List<Task>, modifier: Modifier = Modifier) {
    val totalTasks = tasks.size
    val completedTasks = tasks.count { it.isCompleted }
    val pendingTasks = totalTasks - completedTasks

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            "Resumo de Tarefas",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        TaskStatusCard(
            title = "Pendentes",
            count = pendingTasks,
            icon = Icons.Default.List,
            color = Color(0xFFC62828)
        )

        Spacer(Modifier.height(16.dp))

        TaskStatusCard(
            title = "Concluídas",
            count = completedTasks,
            icon = Icons.Default.CheckCircle,
            color = Color(0xFF2E7D32)
        )

        Spacer(Modifier.height(16.dp))

        TaskStatusCard(
            title = "Total",
            count = totalTasks,
            icon = Icons.Default.Description,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(Modifier.height(32.dp))

        Text(
            "Mantenha o foco e conclua seus objetivos!",
            style = MaterialTheme.typography.titleMedium,
            color = Color.Gray
        )
    }
}

@Composable
fun TaskStatusCard(title: String, count: Int, icon: ImageVector, color: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.1f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(48.dp)
            )
            Spacer(Modifier.width(16.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = color
                )
            }
            Text(
                text = count.toString(),
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.ExtraBold,
                color = color
            )
        }
    }
}

// 1. TELA DE LISTA DE TAREFAS PENDENTES
@Composable
fun TaskListScreen(
    tasks: List<Task>,
    onTaskClick: (Task) -> Unit,
    onAddTask: (String) -> Unit
) {
    var newTaskText by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(
                value = newTaskText,
                onValueChange = { newTaskText = it },
                label = { Text("Adicionar nova tarefa") },
                modifier = Modifier.weight(1f),
                singleLine = true
            )

            Button(
                onClick = {
                    if (newTaskText.isNotBlank()) {
                        onAddTask(newTaskText)
                        newTaskText = ""
                    }
                },
                enabled = newTaskText.isNotBlank()
            ) {
                Text("ADD")
            }
        }

        Spacer(Modifier.height(16.dp))

        if (tasks.isEmpty()) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(1f), contentAlignment = Alignment.Center) {
                Text("Nenhuma tarefa ainda.", fontSize = 20.sp, color = Color.Gray)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(top = 4.dp, bottom = 4.dp)
            ) {
                items(tasks) { task ->
                    TaskListItem(task, onTaskClick = onTaskClick)
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
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.List, contentDescription = null, modifier = Modifier.size(40.dp))
            Column(Modifier
                .padding(start = 16.dp)
                .weight(1f)) {
                Text(
                    text = task.description,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "ID: ${task.id}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

// 2. TELA DE DETALHES DA TAREFA
@Composable
fun TaskDetailsScreen(
    task: Task?,
    onToggleCompletion: (Task) -> Unit,
    onUpdateDescription: (Task, String) -> Unit,
    onUpdateDetails: (Task, String) -> Unit
) {
    if (task == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Selecione uma tarefa.", fontSize = 20.sp, color = Color.Gray)
        }
        return
    }

    val currentTask = task

    var currentDescription by remember { mutableStateOf(currentTask.description) }
    var currentDetails by remember { mutableStateOf(currentTask.details) }
    val isTaskCompleted = currentTask.isCompleted

    LaunchedEffect(currentTask) {
        currentDescription = currentTask.description
        currentDetails = currentTask.details
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = if (isTaskCompleted) "CONCLUÍDA" else "PENDENTE",
            style = MaterialTheme.typography.titleLarge,
            color = if (isTaskCompleted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = currentDescription,
            onValueChange = { currentDescription = it },
            label = { Text("Descrição Principal (Nome)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(Modifier.height(8.dp))

        TextField(
            value = currentDetails,
            onValueChange = { currentDetails = it },
            label = { Text("Detalhes da Tarefa") },
            placeholder = { Text("Adicione mais detalhes aqui...") },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
        )

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = {
                onUpdateDescription(currentTask, currentDescription)
                onUpdateDetails(currentTask, currentDetails)
            },
            enabled = (currentDescription.isNotBlank() && currentDescription != currentTask.description) ||
                    (currentDetails.isNotBlank() && currentDetails != currentTask.details),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar Alterações")
        }

        Spacer(Modifier.weight(1f))

        Button(
            onClick = { onToggleCompletion(currentTask) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(if (isTaskCompleted) "Marcar como PENDENTE" else "Marcar como CONCLUÍDA")
        }
    }
}

// 3. TELA DE TAREFAS CONCLUÍDAS
@Composable
fun CompletedTasksScreen(tasks: List<Task>, onToggleCompletion: (Task) -> Unit) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        if (tasks.isEmpty()) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .weight(1f), contentAlignment = Alignment.Center) {
                Text("Nenhuma tarefa concluída.", fontSize = 24.sp, color = Color.Gray)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(tasks) { task ->
                    CompletedTaskItemRow(item = task, onToggleCompletion = onToggleCompletion)
                }
            }
        }
    }
}

@Composable
fun CompletedTaskItemRow(item: Task, onToggleCompletion: (Task) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .clickable { onToggleCompletion(item) },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                item.description,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray,
                textDecoration = TextDecoration.LineThrough,
                modifier = Modifier.weight(1f)
            )

            IconButton(onClick = { onToggleCompletion(item) }) {
                Icon(Icons.Default.CheckCircle, contentDescription = "Marcar como pendente", tint = Color.Green)
            }
        }
    }
}


@Composable
fun Example(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() },
        containerColor = green,
        contentColor = Color.White,
    ) {
        Icon(Icons.Filled.Add, "Floating action button.")
    }
}


@Preview
@Composable
private fun AppPreview() {
    AulaTelasTheme {
        App()
    }
}