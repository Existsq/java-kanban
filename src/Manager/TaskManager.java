package Manager;

import java.util.HashMap;

public final class TaskManager {
  public static int incrementValue = 1;
  private final HashMap<Integer, Task> tasks = new HashMap<>();
  private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
  private final HashMap<Integer, Epic> epics = new HashMap<>();

  public void getAllTasks() {
    System.out.println("Текущие задачи:");
    for (Task task : tasks.values()) {
      System.out.println(task);
    }
    System.out.println();
  }

  public void getAllSubtasks() {
    System.out.println("Текущие подзадачи:");
    for (Subtask subtask : subtasks.values()) {
      System.out.println(subtask);
    }
    System.out.println();
  }

  public void getAllEpics() {
    System.out.println("Текущие эпики:");
    for (Epic epic : epics.values()) {
      System.out.println(epic);
    }
    System.out.println();
  }

  public Task getTaskById(int id) {
    return tasks.get(id);
  }

  public Subtask getSubtaskById(int id) {
    return subtasks.get(id);
  }

  public Epic getEpicById(int id) {
    return epics.get(id);
  }

  public void deleteAllTasks() {
    tasks.clear();
  }

  public void deleteAllSubtasks() {
    for (Subtask subtask : subtasks.values()) {
      Epic subtaskEpic = subtask.getEpic();
      subtaskEpic.deleteAllSubtasks();
      updateEpicStatus(subtaskEpic);
    }
    subtasks.clear();
  }

  public void deleteAllEpics() {
    for (Epic epic : epics.values()) {
      epic.deleteAllSubtasks();
    }
    epics.clear();
    subtasks.clear();
  }

  public void createTask(Task task) {
    tasks.put(task.getId(), task);
  }

  public void createSubtask(Subtask subtask) {
    subtasks.put(subtask.getId(), subtask);
    Epic subtaskEpic = subtask.getEpic();
    if (epics.containsKey(subtaskEpic.getId())) {
      epics.get(subtaskEpic.getId()).addSubtask(subtask);
      updateEpicStatus(subtaskEpic);
    } else {
      createEpic(subtaskEpic);
      subtaskEpic.addSubtask(subtask);
    }
  }

  public void createEpic(Epic epic) {
    epics.put(epic.getId(), epic);
    updateEpicStatus(epic);
  }

  private void updateEpicStatus(Epic epic) {
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

  public void updateTask(Task task) {
    tasks.put(task.getId(), task);
  }

  public void updateSubtask(Subtask newSubtask) {
    subtasks.put(newSubtask.getId(), newSubtask);
    Epic newSubtaskEpic = newSubtask.getEpic();
    newSubtaskEpic.addSubtask(newSubtask);
    updateEpicStatus(newSubtaskEpic);
  }

  public void updateEpic(Epic epic) {
    epics.put(epic.getId(), epic);
    updateEpicStatus(epic);
  }

  public void deleteTaskById(int id) {
    tasks.remove(id);
  }

  public void deleteSubtaskById(int id) {
    Subtask subtask = subtasks.get(id);
    if (subtask != null) {
      Epic subtaskEpic = subtask.getEpic();
      subtaskEpic.removeSubtask(subtask);
      updateEpicStatus(subtaskEpic);
      subtasks.remove(id);
    } else {
      System.out.println("Подзадача с id " + id + " не найдена.");
    }
  }

  public void deleteEpicById(int id) {
    epics.get(id).deleteAllSubtasks();
    epics.remove(id);
    for (Subtask subtask : subtasks.values()) {
      if (subtask.getEpic().getId() == id) {
        subtasks.remove(subtask.getId());
      }
    }
  }

  public void getAllSubtasksByEpic(int epicId) {
    System.out.println("Подзадачи для Эпика с id - " + epicId);
    for (Subtask subtask : subtasks.values()) {
      if (subtask.getEpic().getId() == epicId) {
        System.out.println(subtask);
      }
    }
  }
}
