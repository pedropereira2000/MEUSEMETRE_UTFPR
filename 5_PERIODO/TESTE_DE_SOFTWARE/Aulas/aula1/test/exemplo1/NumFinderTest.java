package exemplo1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NumFinderTest {

	@Test
	void testSimplesComDoisNumeros() {
		var numFinder = new NumFinder();
		int[] arr = {1, 10};
		numFinder.find(arr);
		assertEquals(1, numFinder.getSmallest());
		assertEquals(10, numFinder.getLargest());
	}

	
	@Test
	void testComUmVetorDesordenado() {
		var numFinder = new NumFinder();
		int[] arr = {10, -10, 200, 5, 4, 2};
		numFinder.find(arr);
		assertEquals(-10, numFinder.getSmallest());
		assertEquals(200, numFinder.getLargest());
	}
	
	@Test
	void testComUmVetorDesordenado_2() {
		var numFinder = new NumFinder();
		int[] arr = {50, 1, 10, 5};
		numFinder.find(arr);
		assertEquals(1, numFinder.getSmallest());
		assertEquals(50, numFinder.getLargest());
	}

}
