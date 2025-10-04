package com.example.ListaDeTarefas.ui

import androidx.lifecycle.ViewModel
import com.example.ListaDeTarefas.data.EffortLevel
import com.example.ListaDeTarefas.data.HeroStatus
import com.example.ListaDeTarefas.data.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class GameUiState(
    val heroStatus: HeroStatus = HeroStatus(),
    val tasks: List<Task> = listOf(
        Task(1, "Finalizar projeto baiao", "Entregar a versão final", isCompleted = false, effort = EffortLevel.HARD),
        Task(2, "Ir no psicologo", "To ficando maluco", isCompleted = false, effort = EffortLevel.EASY),
        Task(3, "Ir ao mercado", "Comprar cafe", isCompleted = false, effort = EffortLevel.MEDIUM),
        Task(8, "Fazer API", "Assistir as aulas e fazer a API", isCompleted = false, effort = EffortLevel.CRITICAL),
    ),
    val selectedTask: Task? = null
)

class GameViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    fun updateHeroName(newName: String) {
        _uiState.update { currentState ->
            val updatedHeroStatus = if (newName.isNotBlank()) {
                currentState.heroStatus.copy(heroName = newName)
            } else {
                currentState.heroStatus.copy(heroName = "Herói")
            }
            currentState.copy(heroStatus = updatedHeroStatus)
        }
    }

    fun selectTask(task: Task?) {
        _uiState.update { it.copy(selectedTask = task) }
    }

    fun addTask(description: String, effort: EffortLevel) {
        val newId = (_uiState.value.tasks.maxOfOrNull { it.id } ?: 0L) + 1
        val newTask = Task(id = newId, description = description, effort = effort)
        _uiState.update { currentState ->
            currentState.copy(tasks = currentState.tasks + newTask)
        }
    }

    fun completeTask(taskToComplete: Task): Task? {
        if (taskToComplete.isCompleted) return null

        val updatedTask = taskToComplete.copy(isCompleted = true)
        _uiState.update { currentState ->
            currentState.copy(
                tasks = currentState.tasks.map { if (it.id == updatedTask.id) updatedTask else it },
                selectedTask = if (currentState.selectedTask?.id == updatedTask.id) updatedTask else currentState.selectedTask
            )
        }
        earnReward(updatedTask)
        return updatedTask
    }

    fun updateTaskDescription(taskToUpdate: Task, newDescription: String) {
        if (taskToUpdate.isCompleted) return
        val updatedTask = taskToUpdate.copy(description = newDescription)
        _uiState.update { currentState ->
            currentState.copy(
                tasks = currentState.tasks.map { if (it.id == updatedTask.id) updatedTask else it },
                selectedTask = if (currentState.selectedTask?.id == updatedTask.id) updatedTask else currentState.selectedTask
            )
        }
    }

    fun updateTaskDetails(taskToUpdate: Task, newDetails: String) {
        if (taskToUpdate.isCompleted) return
        val updatedTask = taskToUpdate.copy(details = newDetails)
        _uiState.update { currentState ->
            currentState.copy(
                tasks = currentState.tasks.map { if (it.id == updatedTask.id) updatedTask else it },
                selectedTask = if (currentState.selectedTask?.id == updatedTask.id) updatedTask else currentState.selectedTask
            )
        }
    }

    private fun earnReward(task: Task) {
        _uiState.update { currentState ->
            var newXp = currentState.heroStatus.currentXp + task.effort.xp
            var newLevel = currentState.heroStatus.level
            var newXpToNextLevel = currentState.heroStatus.xpToNextLevel
            var newGold = currentState.heroStatus.gold + task.effort.gold

            while (newXp >= newXpToNextLevel) {
                newLevel += 1
                newXp -= newXpToNextLevel
                newXpToNextLevel = (newXpToNextLevel * 1.25).toInt()
            }
            val updatedHeroStatus = currentState.heroStatus.copy(
                level = newLevel,
                currentXp = newXp,
                xpToNextLevel = newXpToNextLevel,
                gold = newGold
            )
            currentState.copy(heroStatus = updatedHeroStatus)
        }
    }
}