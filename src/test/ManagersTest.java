package test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import manager.history.HistoryManager;
import manager.model.Epic;
import manager.model.Subtask;
import manager.model.Task;
import manager.model.TaskStatus;
import manager.service.TaskManager;
import manager.util.Managers;
import org.junit.jupiter.api.Test;

public class ManagersTest {

  private final HistoryManager historyManager = Managers.getDefaultHistory();
  private final TaskManager taskManager = Managers.getDefault();

  @Test
  public void notNullManagers() {
    assertNotNull(historyManager);
    assertNotNull(taskManager);
  }

  @Test
  public void addingTaskAndFindingById() {
    final int taskId = taskManager.createTask(new Task("Task", "desc", TaskStatus.NEW));
    final int subtaskId =
        taskManager.createSubtask(
            new Subtask("Task", "desc", TaskStatus.NEW, new Epic("Epic", "desc")));
    assertNotNull(taskManager.getTaskById(taskId));
    assertNotNull(taskManager.getSubtaskById(subtaskId));
  }
}
