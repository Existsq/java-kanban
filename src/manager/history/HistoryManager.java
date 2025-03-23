package manager.history;

import java.util.List;
import manager.model.Task;

public interface HistoryManager {

  void add(Task task);

  List<Task> getHistory();
}
