
public class A {

	B a;

	public long getObjectSize(){
		return ObjectSizeFetcher.getObjectSize(this) + (a == null ? 0 : a.getObjectSize());
	}
	
}
