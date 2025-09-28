//Harini M
//2117240070105
// Immutable class (all fields final, no setters)
final class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}

public class SafePublicationDemo {
    private static volatile Person sharedPerson; // safe publication
    private static final Person constantPerson = new Person("Alice", 30); // also safe

    public static void main(String[] args) {
        // Thread 1: Publisher
        Thread publisher = new Thread(() -> {
            Person p = new Person("Bob", 25);
            sharedPerson = p;
            System.out.println("Published: " + p);
        });

        // Thread 2: Reader
        Thread reader = new Thread(() -> {
            while (sharedPerson == null) {
                // wait until published
            }
            System.out.println("Read safely: " + sharedPerson);
        });

        publisher.start();
        reader.start();
    }
}
