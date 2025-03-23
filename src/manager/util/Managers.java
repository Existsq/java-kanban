package manager.util;

import manager.history.HistoryManager;
import manager.history.InMemoryHistoryManager;
import manager.service.InMemoryTaskManager;
import manager.service.TaskManager;

public class Managers {

  public static TaskManager getDefault() {
    return new InMemoryTaskManager();
  }

  public static HistoryManager getDefaultHistory() {
    return new InMemoryHistoryManager();
  }
}
