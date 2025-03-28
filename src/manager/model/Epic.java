package manager.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Epic extends Task {

  private final List<Subtask> subtasks = new ArrayList<>();

  public Epic(String title, String description) {
    super(title, description, TaskStatus.NEW);
  }

  public Epic(String title, String description, int id) {
    super(title, description, TaskStatus.NEW, id);
  }

  public void setStatus(TaskStatus status) {
    this.status = status;
  }

  public void addSubtask(Subtask subtask) {
    if (subtask.getEpic() == this) {
      return;
    }
    subtasks.add(subtask);
  }

  public void removeSubtask(Subtask subtask) {
    Iterator<Subtask> iterator = subtasks.iterator();
    while (iterator.hasNext()) {
      Subtask inListSubtask = iterator.next();
      if (inListSubtask.equals(subtask)) {
        iterator.remove();
        return;
      }
    }
  }

  public void deleteAllSubtasks() {
    subtasks.clear();
  }

  public List<Subtask> getAllSubtasks() {
    return subtasks;
  }

  public void updateSubtask(Subtask subtask) {
    Iterator<Subtask> iterator = subtasks.iterator();
    while (iterator.hasNext()) {
      Subtask inListSubtask = iterator.next();
      if (inListSubtask.equals(subtask)) {
        iterator.remove();
        subtasks.add(subtask);
        return;
      }
    }
  }

  @Override
  public String toString() {
    return "Эпик {"
        + "Подзадачи="
        + subtasks
        + ", название='"
        + title
        + '\''
        + ", описание='"
        + description
        + '\''
        + ", id="
        + id
        + ", статус="
        + status
        + '}';
  }
}
