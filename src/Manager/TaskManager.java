package Manager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public final class TaskManager {
  public static int incrementValue = 0;
  private final HashMap<Integer, Task> tasks = new HashMap<>();
  private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
  private final HashMap<Integer, Epic> epics = new HashMap<>();

  static int generateId() {
    return ++incrementValue;
  }

  public List<Task> getAllTasks() {
    return tasks.values().stream().toList();
  }

  public List<Subtask> getAllSubtasks() {
    return subtasks.values().stream().toList();
  }

  public List<Epic> getAllEpics() {
    return epics.values().stream().toList();
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
    task.setId(generateId());
    tasks.put(task.getId(), task);
  }

  public void createSubtask(Subtask subtask) {
    subtask.setId(generateId());
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
    epic.setId(generateId());
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

  public void updateSubtask(Subtask subtask) {
    if (subtasks.containsKey(subtask.getId())) {
      subtasks.put(subtask.getId(), subtask);
      subtask.getEpic().updateSubtask(subtask);
      updateEpicStatus(subtask.getEpic());
    }
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
    Iterator<Epic> iterator = epics.values().iterator();
    while (iterator.hasNext()) {
      Epic inListEpic = iterator.next();
      if (inListEpic.getId() == id) {
        iterator.remove();
        return;
      }
    }
  }

  public List<Subtask> getAllSubtasksByEpic(int epicId) {
    return subtasks.values().stream()
        .filter(subtask -> subtask.getEpic().getId() == epicId)
        .toList();
  }
}
