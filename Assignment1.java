//Harini M
//2117240070105
class User {
    String name;
    User(String name) {
        this.name = name;
    }
    String getDetails() {
        return "User: " + name;
    }
}

class Student extends User {
    String course;
    Student(String name, String course) {
        super(name);
        this.course = course;
    }
    @Override
    String getDetails() {
        return "Student: " + name + ", Course: " + course;
    }
}

class Instructor extends User {
    String subject;
    Instructor(String name, String subject) {
        super(name);
        this.subject = subject;
    }
    @Override
    String getDetails() {
        return "Instructor: " + name + ", Subject: " + subject;
    }
}

public class PolymorphismDemo {
    public static void main(String[] args) {
        User[] users = {
            new Student("Alice", "Computer Science"),
            new Instructor("Bob", "Mathematics"),
            new Student("Charlie", "Physics")
        };

        for (User u : users) {
            System.out.println(u.getDetails());
        }
    }
}
Output 
  Student: Alice, Course: Computer Science
Instructor: Bob, Subject: Mathematics
Student: Charlie, Course: Physics
