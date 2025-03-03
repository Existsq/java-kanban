package Manager;

public class Subtask extends Task {
  private final Epic epic;

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
