package manager.service;

import java.util.List;
import manager.model.Epic;
import manager.model.Subtask;
import manager.model.Task;
import manager.model.TaskStatus;

public interface TaskManager {

  List<Task> getHistory();

  List<Task> getAllTasks();

  List<Subtask> getAllSubtasks();

  List<Epic> getAllEpics();

  Task getTaskById(int id);

  Subtask getSubtaskById(int id);

  Epic getEpicById(int id);

  void deleteAllTasks();

  void deleteAllSubtasks();

  void deleteAllEpics();

  int createTask(Task task);

  int createSubtask(Subtask subtask);

  int createEpic(Epic epic);

  default void updateEpicStatus(Epic epic) {
    if (epic.getAllSubtasks().isEmpty()) {
      epic.setStatus(TaskStatus.NEW);
      return;
    }

    boolean isNew = true;
    boolean isDone = true;

    for (Subtask subtask : epic.getAllSubtasks()) {
      TaskStatus status = subtask.getStatus();

      if (status != TaskStatus.NEW) {
        isNew = false;
      }
      if (status != TaskStatus.DONE) {
        isDone = false;
      }

      if (!isNew && !isDone) {
        epic.setStatus(TaskStatus.IN_PROGRESS);
        return;
      }
    }

    epic.setStatus(isDone ? TaskStatus.DONE : TaskStatus.NEW);
  }

  void updateTask(Task task);

  void updateSubtask(Subtask subtask);

  void updateEpic(Epic epic);

  void deleteTaskById(int id);

  void deleteSubtaskById(int id);

  void deleteEpicById(int id);

  List<Subtask> getAllSubtasksByEpic(int epicId);
}
