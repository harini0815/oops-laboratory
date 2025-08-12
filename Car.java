public class Car {
String model;
String color;
Car(String model, String color){
	this.model=model;
	this.color=color;
	}
void start() {
	if(model!=null&&!model.isEmpty()) {
		System.out.println("Car Started");
	}else {
		System.out.println("Car started(but model not shown)");
	}
}
void stop() {
	System.out.println("Car Stopped");
}
public static void main(String[] args) {
	Car car1= new Car("Tesla","Red");
	car1.start();
	}
}
