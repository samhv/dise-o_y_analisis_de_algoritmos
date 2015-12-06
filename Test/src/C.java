public class C {

	public static void main(String[] args) {
		A a = new A();
		A b = new A();
		B c = new B();
		a.a = c;
		

		System.out.println(a.getObjectSize());
		System.out.println(b.getObjectSize());
		System.out.println(c.getObjectSize());
		
	

	}
}