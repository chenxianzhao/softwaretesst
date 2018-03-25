package cn.tju.scs;
import static org.junit.Assert.*;

import org.junit.Test;
public class TriangleTest {
	@Test
	public void test(){
		assertEquals(0, new Triangle().check(1, 2, -1));
		assertEquals(1, new Triangle().check(3, 4, 5));
		assertEquals(2, new Triangle().check(2, 2, 3));
		assertEquals(3, new Triangle().check(2, 2, 2));
	}
}
