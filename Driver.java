import java.util.Arrays;
import java.io.File;
public class Driver {
	public static void main(String [] args) {
		/*Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double [] c1 = {6,0,0,5};
		Polynomial p1 = new Polynomial(c1);
		double [] c2 = {0,-2,0,0,-9};
		Polynomial p2 = new Polynomial(c2);
		Polynomial s = p1.add(p2);
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1))
		System.out.println("1 is a root of s");
		else
		System.out.println("1 is not a root of s");
		*/
		double[] coefficients_test1 = {1,2,3};
		int[]exponents_test1={0,1,2};
		Polynomial polynomial_test1 = new Polynomial(coefficients_test1,exponents_test1);
		double[] coefficients_test2 = {4,5};
		int[] exponents_test2 = {0,1};
		Polynomial polynomial_test2 = new Polynomial(coefficients_test2, exponents_test2);
		
		Polynomial product_poly = polynomial_test1.multiply(polynomial_test2);
		System.out.println("Coefficient array is : " + Arrays.toString(product_poly.coefficients_poly));
		System.out.println("Exponent Array is: "+ Arrays.toString(product_poly.exponent));
		
		File newFile = new File("polynomial_tester.txt");
		Polynomial polynomialRead = new Polynomial(newFile);
		
		System.out.println("Coefficent Array of Polynomial from text file: " + Arrays.toString(polynomialRead.coefficients_poly));
		System.out.println("Exponent Array of Polynomial from text file: " + Arrays.toString(polynomialRead.exponent));
		
		Polynomial polynomialSaving = new Polynomial(new double[] {3.0,5.0,5.0}, new int[] {9,3,5});
		String nameofFile = "output_of_polynomial.txt";
		polynomialSaving.saveToFile(nameofFile);
		
		File outputofFile = new File(nameofFile);
		Polynomial polynomialLoading = new Polynomial(outputofFile);
	
	}
}