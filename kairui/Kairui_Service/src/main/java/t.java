
public class t {
	public int a=1;
	public void test(){
		test2();
		System.out.println(a);
		try {
			Thread.sleep(2000);
			test1();
			System.out.println(a);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void test1(){
		a=a-1;
	}
	public void test2(){
		a=a+1;
	}
	public static void main(String[] args) {
		t x=new t();
		x.test();
	}
}
