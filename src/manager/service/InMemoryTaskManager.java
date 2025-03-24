package manager.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import manager.history.HistoryManager;
import manager.model.Epic;
import manager.model.Subtask;
import manager.model.Task;
import manager.util.Managers;

public final class InMemoryTaskManager implements TaskManager {
  public static int incrementValue = 0;
  private final Map<Integer, Task> tasks = new HashMap<>();
  private final Map<Integer, Subtask> subtasks = new HashMap<>();
  private final Map<Integer, Epic> epics = new HashMap<>();
  private final HistoryManager historyManager = Managers.getDefaultHistory();

  static int generateId() {
    return ++InMemoryTaskManager.incrementValue;
  }

  public List<Task> getHistory() {
    return historyManager.getHistory();
  }

  @Override
  public List<Task> getAllTasks() {
    return tasks.values().stream().toList();
  }

  @Override
  public List<Subtask> getAllSubtasks() {
    return subtasks.values().stream().toList();
  }

  @Override
  public List<Epic> getAllEpics() {
    return epics.values().stream().toList();
  }

  @Override
  public Task getTaskById(int id) {
    historyManager.add(tasks.get(id));
    return tasks.get(id);
  }

  @Override
  public Subtask getSubtaskById(int id) {
    historyManager.add(subtasks.get(id));
    return subtasks.get(id);
  }

  @Override
  public Epic getEpicById(int id) {
    historyManager.add(epics.get(id));
    return epics.get(id);
  }

  @Override
  public void deleteAllTasks() {
    tasks.clear();
  }

  @Override
  public void deleteAllSubtasks() {
    for (Subtask subtask : subtasks.values()) {
      Epic subtaskEpic = subtask.getEpic();
      subtaskEpic.deleteAllSubtasks();
      updateEpicStatus(subtaskEpic);
    }
    subtasks.clear();
  }

  @Override
  public void deleteAllEpics() {
    for (Epic epic : epics.values()) {
      epic.deleteAllSubtasks();
    }
    epics.clear();
    subtasks.clear();
  }

  @Override
  public int createTask(Task task) {
    if (task.getId() == 0) {
      task.setId(generateId());
    }
    tasks.put(task.getId(), task);
    return task.getId();
  }

  @Override
  public int createSubtask(Subtask subtask) {
    if (subtask.getId() == 0) {
      subtask.setId(generateId());
    }
    subtasks.put(subtask.getId(), subtask);

    Epic subtaskEpic = subtask.getEpic();
    if (epics.containsKey(subtaskEpic.getId())) {
      epics.get(subtaskEpic.getId()).addSubtask(subtask);
      updateEpicStatus(subtaskEpic);
    } else {
      createEpic(subtaskEpic);
      subtaskEpic.addSubtask(subtask);
    }
    return subtask.getId();
  }

  @Override
  public int createEpic(Epic epic) {
    if (epic.getId() == 0) {
      epic.setId(generateId());
    }
    epics.put(epic.getId(), epic);
    updateEpicStatus(epic);
    return epic.getId();
  }

  @Override
  public void updateTask(Task task) {
    tasks.put(task.getId(), task);
  }

  @Override
  public void updateSubtask(Subtask subtask) {
    if (subtasks.containsKey(subtask.getId())) {
      subtasks.put(subtask.getId(), subtask);
      subtask.getEpic().updateSubtask(subtask);
      updateEpicStatus(subtask.getEpic());
    }
  }

  @Override
  public void updateEpic(Epic epic) {
    epics.put(epic.getId(), epic);
    updateEpicStatus(epic);
  }

  @Override
  public void deleteTaskById(int id) {
    tasks.remove(id);
  }

  @Override
  public void deleteSubtaskById(int id) {
    Subtask subtask = subtasks.get(id);
    if (subtask != null) {
      Epic subtaskEpic = subtask.getEpic();
      subtaskEpic.removeSubtask(subtask);
      updateEpicStatus(subtaskEpic);
      subtasks.remove(id);
    }
  }

  @Override
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

  @Override
  public List<Subtask> getAllSubtasksByEpic(int epicId) {
    return subtasks.values().stream()
        .filter(subtask -> subtask.getEpic().getId() == epicId)
        .toList();
  }
}
