package manager.model;

import java.util.Objects;

public class Task {
  protected String title;
  protected String description;
  protected int id = 0;
  protected TaskStatus status;

  public Task(String title, String description, TaskStatus status) {
    this.title = title;
    this.description = description;
    this.status = status;
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

  public TaskStatus getStatus() {
    return status;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Task task)) return false;
    return id == task.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Задача {"
        + "Название='"
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
