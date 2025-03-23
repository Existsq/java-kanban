package manager.history;

import java.util.ArrayList;
import java.util.List;
import manager.model.Task;

public class InMemoryHistoryManager implements HistoryManager {

  private final List<Task> history = new ArrayList<>();

  @Override
  public void add(Task task) {
    if (history.size() == 10) {
      history.removeFirst();
      history.add(task);
    }
    history.add(task);
  }

  @Override
  public List<Task> getHistory() {
    return history;
  }
}
