/* ... */
public class MyFirstSimpleProgram {
	// ...
	public void start (String name) {
		System.out.println("Hello " + name);
	}
	public static void main(String[] args) {
		new MyFirstSimpleProgram().start(args[0]);
	}
}