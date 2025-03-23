package manager.model;

public class Subtask extends Task {
  private Epic epic;

  public Subtask(String title, String description, TaskStatus status, Epic epic) {
    super(title, description, status);
    this.epic = epic;
  }

  public Subtask(String title, String description, TaskStatus status, int id, Epic epic) {
    super(title, description, status, id);
    this.epic = epic;
  }

  public Epic getEpic() {
    return epic;
  }

  public void setEpic(Epic subtaskEpic) {
    if (subtaskEpic == this.getEpic()) {
      return;
    }
    this.epic = subtaskEpic;
  }

  @Override
  public String toString() {
    return "Подзадача {"
        + "id Эпика="
        + epic.getId()
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
