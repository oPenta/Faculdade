package com.example.ListaDeTarefas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ListaDeTarefas.data.ScreenItem
import com.example.ListaDeTarefas.ui.GameViewModel
import com.example.ListaDeTarefas.ui.screens.*
import com.example.ListaDeTarefas.ui.theme.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AulaTelasTheme {
                MainApp(viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MainApp(viewModel: GameViewModel) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val mainScreens = remember {
        listOf(ScreenItem.Home, ScreenItem.TaskList, ScreenItem.CompletedTasks, ScreenItem.Login)
    }

    val currentScreen = remember(currentRoute) {
        ScreenItem.getScreens().find { it.route == currentRoute } ?: ScreenItem.Home
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(currentScreen.topAppBarItem.title) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BackgroundLight,
                    titleContentColor = TextColorLight
                ),
                navigationIcon = {
                    if (currentRoute !in mainScreens.map { it.route }) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                        }
                    }
                }
            )
        },
        bottomBar = {
            if (currentRoute in mainScreens.map { it.route }) {
                BottomAppBar(containerColor = CardSurfaceLight) {
                    mainScreens.forEach { screen ->
                        screen.bottomAppBarItem?.let { bottomBarItem ->
                            NavigationBarItem(
                                selected = screen.route == currentRoute,
                                onClick = {
                                    navController.navigate(screen.route) {
                                        popUpTo(navController.graph.startDestinationId)
                                        launchSingleTop = true
                                    }
                                },
                                icon = { Icon(bottomBarItem.icon, contentDescription = null) },
                                label = { Text(bottomBarItem.label) },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = XpColor,
                                    selectedTextColor = XpColor,
                                    unselectedIconColor = Color.Gray,
                                    unselectedTextColor = Color.Gray
                                )
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ScreenItem.Home.route,
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
        ) {
            composable(ScreenItem.Home.route) {
                HomeScreen(heroStatus = uiState.heroStatus, tasks = uiState.tasks)
            }
            composable(ScreenItem.TaskList.route) {
                TaskListScreen(
                    tasks = uiState.tasks.filter { !it.isCompleted },
                    onTaskClick = { task ->
                        viewModel.selectTask(task)
                        navController.navigate(ScreenItem.TaskDetails.route)
                    },
                    onAddTask = viewModel::addTask
                )
            }
            composable(ScreenItem.CompletedTasks.route) {
                CompletedTasksScreen(
                    tasks = uiState.tasks.filter { it.isCompleted },
                    onTaskClick = { task ->
                        viewModel.selectTask(task)
                        navController.navigate(ScreenItem.TaskDetails.route)
                    }
                )
            }
            composable(ScreenItem.Login.route) {
                LoginScreen(
                    onLoginClick = { username ->
                        viewModel.updateHeroName(username)
                        scope.launch { snackbarHostState.showSnackbar("'Login' simulado! Nome do Herói atualizado.") }
                        navController.navigate(ScreenItem.Home.route) {
                            popUpTo(navController.graph.startDestinationId)
                        }
                    },
                    onNavigateToRegister = { navController.navigate(ScreenItem.Register.route) }
                )
            }
            composable(ScreenItem.Register.route) {
                RegisterScreen(
                    onRegisterClick = { heroName ->
                        viewModel.updateHeroName(heroName)
                        scope.launch { snackbarHostState.showSnackbar("'Cadastro' simulado! Bem-vindo, ${heroName}!") }
                        navController.navigate(ScreenItem.Home.route) {
                            popUpTo(navController.graph.startDestinationId)
                        }
                    },
                    onNavigateToLogin = { navController.popBackStack() }
                )
            }
            composable(ScreenItem.TaskDetails.route) {
                TaskDetailsScreen(
                    task = uiState.selectedTask,
                    onCompleteTask = { task ->
                        viewModel.completeTask(task)?.let { rewardedTask ->
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "Missão Concluída! +${rewardedTask.effort.xp} XP / +${rewardedTask.effort.gold} Ouro"
                                )
                            }
                        }
                    },
                    onUpdateDescription = viewModel::updateTaskDescription,
                    onUpdateDetails = viewModel::updateTaskDetails
                )
            }
        }
    }
}