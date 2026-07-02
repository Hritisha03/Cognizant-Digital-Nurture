class Task {

    int taskId;
    String taskName;
    String status;
    Task next;

    Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
        this.next = null;
    }
}

public class ExFive {

    static Task head = null;

    // Add Task
    static void addTask(int id, String name, String status) {

        Task newTask = new Task(id, name, status);

        if (head == null) {
            head = newTask;
        } else {
            Task temp = head;

            while (temp.next != null)
                temp = temp.next;

            temp.next = newTask;
        }

        System.out.println("Task Added.");
    }

    // Search Task
    static void searchTask(int id) {

        Task temp = head;

        while (temp != null) {

            if (temp.taskId == id) {

                System.out.println("\nTask Found:");
                System.out.println(temp.taskId + " | " + temp.taskName + " | " + temp.status);
                return;
            }

            temp = temp.next;
        }

        System.out.println("Task Not Found.");
    }

    // Traverse Tasks
    static void traverseTasks() {

        System.out.println("\nTask List:");

        Task temp = head;

        while (temp != null) {

            System.out.println(temp.taskId + " | " + temp.taskName + " | " + temp.status);

            temp = temp.next;
        }
    }

    // Delete Task
    static void deleteTask(int id) {

        if (head == null) {
            System.out.println("List is Empty.");
            return;
        }

        if (head.taskId == id) {
            head = head.next;
            System.out.println("Task Deleted.");
            return;
        }

        Task temp = head;

        while (temp.next != null && temp.next.taskId != id) {
            temp = temp.next;
        }

        if (temp.next == null) {
            System.out.println("Task Not Found.");
        } else {
            temp.next = temp.next.next;
            System.out.println("Task Deleted.");
        }
    }

    public static void main(String[] args) {

        addTask(101, "Complete Report", "Pending");
        addTask(102, "Attend Meeting", "Completed");
        addTask(103, "Submit Assignment", "Pending");

        traverseTasks();

        searchTask(102);

        deleteTask(101);

        traverseTasks();
    }
}