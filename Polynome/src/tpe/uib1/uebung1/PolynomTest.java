package tpe.uib1.uebung1;

import static org.junit.Assert.*;
import org.junit.Test;


public class PolynomTest {
	
	@Test
	public void constructorTest(){
		Polynom p1 = new Polynom(1.0, 1.0, 1.0);
		
		//TODO
		//fail( );
	}
	
	@Test
	public void getGradTestGrad3(){
		Polynom p1 = new Polynom(1.0, 0.0, 25.0, 300);
		assertEquals(3, p1.getGrad());
	}
	
	@Test
	public void getGradTestGrad4(){
		Polynom p1 = new Polynom(1.0, 0.0, 25.0, 300, 42.0);
		assertEquals(4, p1.getGrad());
	}
	
	@Test
	public void differenziereTest(){
		Polynom p1 = new Polynom(-10, 0.0, 0.5, -1, 2);
		Polynom p1_dif = p1.differenziere();
		Polynom p1_ref = new Polynom(0.0, 1, -3, 8);
		
		assertEquals(p1_ref.toString(), p1_dif.toString());
	}
	
	@Test
	public void integriereTest(){
		Polynom p1 = new Polynom(0.0, 1, -3, 8);
		Polynom p1_int = p1.integriere();
		Polynom p1_ref = new Polynom(0.0, 0.0, 0.5, -1, 2);
		
		assertEquals(p1_ref.toString(), p1_int.toString());
	}
	
	
	@Test
	public void toStringTest(){
		Polynom p1 = new Polynom(3.256, -2, 5, 0, -0.5, -4, 0, 0, -9);
		String ref = "Grad 8: -9x^8 -4x^5 -0.5x^4 + 5x^2 -2x + 3.26";
		System.out.println(p1.toString());
		assertEquals(ref, p1.toString());
	}
	
	@Test
	public void equalsTest(){
		Polynom p1 = new Polynom(3.0, -2, 5, 0, -0.5, -4, 0, 0, -9);
		Polynom p2 = new Polynom(3.0, -2, 5, 0, -0.5, -4, 0, 0, -9);
		assertTrue(p1.equals(p2));
	}
	
	@Test
	public void berechneTest() {
	    Polynom p1 = new Polynom(5, 0, -2, 0, 9.5, -8, 5);
	    assertEquals(9.5, p1.berechne(1), 0);
	}
	
	@Test
	public void berechneArrayTest() {
	    Polynom p1 = new Polynom(5, 0, -2, 0, 9.5, -8, 5);
	    double[] results = {9.5, 25.5, 59017.5, 109017.5};
	    assertArrayEquals(results, p1.berechne(1, -1, 5, -5), 0);
	}
	
	@Test
	public void addiereTest(){
	    Polynom p1 = new Polynom(5, 4, -2, 3, 9.5, -8);
	    Polynom p2 = new Polynom(3, 7, 6, -6, 0, 4, 7, 11);
	    Polynom p1p2 = new Polynom(8, 11, 4, -3, 9.5, -4, 7, 11);
	    assertEquals(p1p2.toString(), p1.addiere(p2).toString());
	}
	
	@Test
	public void subtrahiereTest() {
	    Polynom p1 = new Polynom(5, 4, -2, 3, 9.5, -8);
	    Polynom p2 = new Polynom(3, 7, 6, -6, 0, 4, 7, 11);
	    Polynom p1p2 = new Polynom(2, -3, -8, 9, 9.5, -12, -7, -11);
	    assertEquals(p1p2.toString(), p1.subtrahiere(p2).toString());
	}
	
	@Test
	public void subtrahiereTest2() {
		Polynom p1 = new Polynom(3, 7, 6, -6, 0, 4, 7, 11);
	    Polynom p2 = new Polynom(5, 4, -2, 3, 9.5, -8);
    
	    Polynom p1p2 = new Polynom(-2, +3, +8, -9, -9.5, +12, 7, 11);
	    assertEquals(p1p2.toString(), p1.subtrahiere(p2).toString());
	}


}
