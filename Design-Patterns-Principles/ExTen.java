class Student {

    private String name;
    private int id;
    private String grade;

    Student(String name, int id, String grade) {
        this.name = name;
        this.id = id;
        this.grade = grade;
    }

    String getName() {
        return name;
    }

    int getId() {
        return id;
    }

    String getGrade() {
        return grade;
    }

    void setName(String name) {
        this.name = name;
    }

    void setGrade(String grade) {
        this.grade = grade;
    }
}

class StudentView {

    void displayStudentDetails(String name, int id, String grade) {
        System.out.println("Student Details");
        System.out.println("Name  : " + name);
        System.out.println("ID    : " + id);
        System.out.println("Grade : " + grade);
    }
}

class StudentController {

    private Student model;
    private StudentView view;

    StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }

    void setStudentName(String name) {
        model.setName(name);
    }

    void setStudentGrade(String grade) {
        model.setGrade(grade);
    }

    void updateView() {
        view.displayStudentDetails(
                model.getName(),
                model.getId(),
                model.getGrade());
    }
}

public class ExTen {

    public static void main(String[] args) {

        Student model = new Student("Rahul", 101, "A");
        StudentView view = new StudentView();

        StudentController controller =
                new StudentController(model, view);

        controller.updateView();

        System.out.println();

        controller.setStudentName("Priya");
        controller.setStudentGrade("A+");

        controller.updateView();
    }
}