package Manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Epic extends Task {

  private final List<Subtask> subtasks = new ArrayList<>();

  public Epic(String title) {
    super(title, "", null);
  }

  public Epic(String title, String description, TaskStatus status, int id) {
    super(title, "", null, id);
  }

  public void setStatus(TaskStatus status) {
    this.status = status;
  }

  protected void addSubtask(Subtask subtask) {
    subtasks.add(subtask);
  }

  protected void removeSubtask(Subtask subtask) {
    Iterator<Subtask> iterator = subtasks.iterator();
    while (iterator.hasNext()) {
      Subtask inListSubtask = iterator.next();
      if (inListSubtask.equals(subtask)) {
        iterator.remove();
        return;
      }
    }
  }

  protected void deleteAllSubtasks() {
    subtasks.clear();
  }

  public List<Subtask> getAllSubtasks() {
    return subtasks;
  }

  @Override
  public String toString() {
    return "Эпик {"
        + "title='"
        + title
        + '\''
        + ", id="
        + id
        + ", status="
        + status
        + ", subtasks="
        + subtasks
        + '}';
  }
}
