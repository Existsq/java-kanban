package Manager;

public class Task {
  protected String title;
  protected String description;
  protected final int id;
  protected TaskStatus status;

  public Task(String title, String description, TaskStatus status) {
    this.title = title;
    this.description = description;
    this.status = status;
    id = this.hashCode();
  }

  public Task(String title, String description, TaskStatus status, int id) {
    this.title = title;
    this.description = description;
    this.status = status;
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public int getId() {
    return id;
  }

  public TaskStatus getStatus() {
    return status;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Task task = (Task) o;
    return id == task.id;
  }

  @Override
  public int hashCode() {
    return ++TaskManager.incrementValue;
  }

  @Override
  public String toString() {
    return "Задача {"
        + "title='"
        + title
        + '\''
        + ", description='"
        + description
        + '\''
        + ", id="
        + id
        + ", status="
        + status
        + '}';
  }
}
