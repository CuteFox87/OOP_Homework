class ABC {
	public void m() {
		System.out.println("ABC");
	}
}

class XYZ extends ABC {
	public void m() {
		System.out.println("Haha, I am XYZ");
	}
	
	public void m2() {
		System.out.println("XYZ");
	}
}

public class PolyDemo {
	public static void main(String args[]) {
		
		// ABC obj = new ABC();
		// obj.m();
		// XYZ obj2 = new XYZ();
		// obj2.m();
		// obj2.m2();
		
		
		// ABC obj3 = new XYZ();
		// obj3.m();
		// obj3.m2();
		
		// XYZ obj4 = new ABC();
		
		// ABC obj5 = new XYZ();
		// obj5.m();
		// //obj5.m2();
		// ((XYZ) obj5).m2();
		
		
		
		ABC obj6 = new ABC();
		((XYZ) obj6).m();
		
		
		XYZ obj7 = new XYZ();
		((ABC) obj7).m2();
		
	}
}