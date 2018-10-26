import java.io.File;

import soln.poly.Term;
import soln.opt.Minimizer;
import soln.poly.Polynomial;
import soln.util.Vector;

/** This is a small example of test cases.  To test correctness of your implementation,  
 *  see if the output on your tests matches in TestOpt match the results of the same tests 
 *  on the solution provided here.  Note the soln imports in this file!
 *  
 * @author ssanner@mie.utoronto.ca
 *
 */
public class TestOptSoln {

	public static void main(String[] args) throws Exception {
		// You must run more test cases than this!
		RunMinimizer("files/poly1.txt", 0.001, 200, 0.10, "{ x=1.0 }");
		RunMinimizer("files/poly2.txt", 0.001, 200, 0.10, "{ x=1.0 y=1.0 }");
	}	

	public static void RunMinimizer(String polyfile, double eps, int max_iter, double alpha, String sx0) 
			throws Exception {
		
		Minimizer m = new Minimizer();

		// If the following file does not load, verify that it exists,
		// and check that it is the correct path relative to your
		// NetBeans/Eclipse project settings for working directory
		Polynomial p = Polynomial.ReadPolynomial(new File(polyfile));
		
		m.setEps(eps);
		m.setMaxIter(max_iter);
		m.setStepSize(alpha);
		m.setX0(new Vector(sx0));
		
		System.out.println("========================================");
		System.out.println("OPTIMIZING: " + p);
		System.out.println("========================================");
		m.printParams(System.out);
		m.minimize(p);
		m.printResults(System.out);
	}
}
