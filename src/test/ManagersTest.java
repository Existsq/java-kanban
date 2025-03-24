package test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import manager.history.HistoryManager;
import manager.service.TaskManager;
import manager.util.Managers;
import org.junit.jupiter.api.Test;

public class ManagersTest {

  private final HistoryManager historyManager = Managers.getDefaultHistory();
  private final TaskManager taskManager = Managers.getDefault();

  @Test
  public void notNullManagers() {
    assertNotNull(historyManager);
    assertNotNull(taskManager);
  }
}
