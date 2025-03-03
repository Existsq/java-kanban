import Manager.Epic;
import Manager.Subtask;
import Manager.Task;
import Manager.TaskManager;
import Manager.TaskStatus;

public class Main {
  public static void main(String[] args) {
    TaskManager taskManager = new TaskManager();

    Task task1 = new Task("Задача 1", "Описание задачи 1", TaskStatus.NEW);
    Task task2 = new Task("Задача 2", "Описание задачи 2", TaskStatus.IN_PROGRESS);
    taskManager.createTask(task1);
    taskManager.createTask(task2);

    Epic epic1 = new Epic("Эпик 1", "описание");
    Subtask subtask1_1 =
        new Subtask("Подзадача 1-1", "Описание подзадачи 1-1", TaskStatus.NEW, epic1);
    Subtask subtask1_2 =
        new Subtask("Подзадача 1-2", "Описание подзадачи 1-2", TaskStatus.DONE, epic1);
    taskManager.createEpic(epic1);
    taskManager.createSubtask(subtask1_1);
    taskManager.createSubtask(subtask1_2);

    Epic epic2 = new Epic("Эпик 2", "описание");
    Subtask subtask2_1 =
        new Subtask("Подзадача 2-1", "Описание подзадачи 2-1", TaskStatus.NEW, epic2);
    taskManager.createEpic(epic2);
    taskManager.createSubtask(subtask2_1);

    System.out.println("Все задачи:");
    System.out.println(taskManager.getAllTasks());

    System.out.println("Все эпики:");
    System.out.println(taskManager.getAllEpics());

    System.out.println("Все подзадачи:");
    System.out.println(taskManager.getAllSubtasks());

    task1 = new Task("Задача 1", "Обновленное описание задачи 1", TaskStatus.DONE, task1.getId());
    taskManager.updateTask(task1);

    subtask1_1 =
        new Subtask(
            "Подзадача 1-1",
            "Обновленное описание подзадачи 1-1",
            TaskStatus.DONE,
            subtask1_1.getId(),
            epic1);
    taskManager.updateSubtask(subtask1_1);

    System.out.println("Обновленные задачи:");
    System.out.println(taskManager.getAllTasks());

    System.out.println("Обновленные эпики:");
    System.out.println(taskManager.getAllEpics());

    System.out.println("Обновленные подзадачи:");
    System.out.println(taskManager.getAllSubtasks());

    System.out.println("Статус эпиков после обновления подзадач:");
    System.out.println(taskManager.getAllEpics());

    taskManager.deleteTaskById(task2.getId());
    taskManager.deleteEpicById(epic2.getId());

    System.out.println("Задачи после удаления:");
    System.out.println(taskManager.getAllTasks());

    System.out.println("Эпики после удаления:");
    System.out.println(taskManager.getAllEpics());

    System.out.println("Подзадачи после удаления:");
    System.out.println(taskManager.getAllSubtasks());
  }
}
