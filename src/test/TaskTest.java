package test;

import static manager.model.TaskStatus.NEW;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import manager.history.HistoryManager;
import manager.model.Epic;
import manager.model.Subtask;
import manager.model.Task;
import manager.service.TaskManager;
import manager.util.Managers;
import org.junit.jupiter.api.Test;

public class TaskTest {

  private final TaskManager taskManager = Managers.getDefault();
  private final HistoryManager historyManager = Managers.getDefaultHistory();

  @Test
  public void tasksEquals() {
    Task task1 = new Task("test", "description", NEW, 12);
    Task task2 = new Task("test2", "description2", NEW, 12);
    assertEquals(task1, task2);
  }

  @Test
  public void inheritedTasksEquals() {
    Subtask task1 = new Subtask("test", "description", NEW, 12, new Epic("test", "description"));
    Task task2 = new Task("test2", "description2", NEW, 12);
    assertEquals(task1, task2);
  }

  @Test
  void addNewTask() {
    Task task = new Task("Test addNewTask", "Test addNewTask description", NEW);
    final int taskId = taskManager.createTask(task);

    final Task savedTask = taskManager.getTaskById(taskId);

    assertNotNull(savedTask, "Задача не найдена.");
    assertEquals(task, savedTask, "Задачи не совпадают.");

    final List<Task> tasks = taskManager.getAllTasks();

    assertNotNull(tasks, "Задачи не возвращаются.");
    assertEquals(1, tasks.size(), "Неверное количество задач.");
    assertEquals(task, tasks.getFirst(), "Задачи не совпадают.");
  }

  @Test
  void add() {
    Task task = new Task("test", "description", NEW);
    historyManager.add(task);
    final List<Task> history = historyManager.getHistory();
    assertNotNull(history, "История не пустая.");
    assertEquals(1, history.size(), "История не пустая.");
  }

  @Test
  public void taskImperminality() {
    Task task = new Task("Task", "desc", NEW, 12);
    final int taskId = taskManager.createTask(task);
    assertEquals(task.getTitle(), taskManager.getTaskById(taskId).getTitle());
    assertEquals(task.getDescription(), taskManager.getTaskById(taskId).getDescription());
    assertEquals(task.getStatus(), taskManager.getTaskById(taskId).getStatus());
    assertEquals(task.getId(), taskManager.getTaskById(taskId).getId());
  }

  @Test
  public void sameIdNoConflict() {
    Task task = new Task("Task", "desc", NEW);
    Task task1 = new Task("Task1", "desc", NEW, 1);
    taskManager.createTask(task);
    final int taskId1 = taskManager.createTask(task1);
    assertEquals(taskManager.getTaskById(taskId1), task1);
  }
}
