package util;

import java.util.HashMap;

import java.util.Set;



/** Implements a vector with *named* indices.  For example { x=1.0 y=2.0 } is a 2D
 *  vector with the first dimension named "x" and the second dimension named "y"
 *  and having respective values 1.0 and 2.0 in these dimensions.
 *  
 *  TODO: Implement all methods required to support the functionality of the project
 *        and that described in Vector.main(...) below.
 * 
 * @author ssanner@mie.utoronto.ca
 *
 */
public class Vector {

	private HashMap<String,Double> _hmVar2Value; // This maps dimension variable names to values
	
	/** Constructor of an initially empty Vector
	 * 
	 */
	public Vector() {
		// TODO: this method should not be empty! 
		// Hint: is there any memory you want to allocate?
		 _hmVar2Value = new HashMap<String,Double>();
	}

	/** Constructor that parses a String s like "{ x=-1 y=-2.0 z=3d }" into 
	 *  the internal HashMap representation of the Vector.  See usage in main().
	 * 
	 * @param s
	 */
	public Vector(String s) throws VectorException {
		// TODO: this method should not be empty! 
		// Hint: you're going to have use String.split used in Project2.
		// to split by " "
		String[] split1 = s.split("\\s");
		_hmVar2Value = new HashMap<String,Double>();
		
		if (!split1[0].equals("{") || !split1[split1.length-1].equals("}"))
			throw new VectorException("Malformed vector initialization: missing [ or ] in " + s);

		for (int i = 1; i < split1.length-1; i++) {
			// to split by "=" after original string being splited by " "
			String[] split2 = split1[i].split("=");
			_hmVar2Value.put(split2[0], Double.parseDouble(split2[1]));		
		}
		
 	}
	
	/** Removes (clears) all (key,value) pairs from the Vector representation
	 * 
	 */
	public void clear() {
		// TODO: this method should not be empty! 
		// Hint: look very carefully at the available methods of HashMap... this is a one liner!
		_hmVar2Value.clear();
	}

	/** Sets a specific var to the value val in *this*, i.e., var=val
	 * 
	 * @param var - label of Vector index to change
	 * @param val - value to change it to
	 */
	public void set(String var, double val) {
		// TODO: this method should not be empty! 
		// Hint: look very carefully at the available methods of HashMap... this is a one liner!
		_hmVar2Value.put(var, val);
	}

	/** Sets all entries in *this* Vector to match entries in x
	 *  (if additional variables are in *this*, they remain unchanged) 
	 * 
	 * @param x
	 */
	public void setAll(Vector x) {
		// TODO: this method should not be empty! 
		// Hint: look very carefully at the available methods of HashMap... this is a one liner!
		this._hmVar2Value.putAll(x._hmVar2Value);
	}

	///////////////////////////////////////////////////////////////////////////////
	// TODO: Add your methods here!  You'll need more than those above to make
	//       main() work below.
	///////////////////////////////////////////////////////////////////////////////
	public void replace(String var, double val) {
		_hmVar2Value.replace(var, val);
	}
	public double get(String s) {
		return this._hmVar2Value.get(s);
	}
	
	public Set<String> keySet() {
		return this._hmVar2Value.keySet();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		//foreach _hmVar2Value.key21
		for (String s : _hmVar2Value.keySet()) {
			sb.append(String.format(" %s=%6.4f", s, _hmVar2Value.get(s)));
		}
		sb.append(" }");
		return sb.toString();
	}
	
	public Vector sum(Vector v) throws VectorException {
		
		Vector addVector = new Vector();
		for (String s : this._hmVar2Value.keySet()) {
			if (!v._hmVar2Value.containsKey(s))
				throw new VectorException ("Vector index '" + s + "' not found in " + v);
			addVector.set(s, this.get(s) + v.get(s));
		}
		
		return addVector;
	}
	
	public Vector scalarMult(double d) {
		Vector multVector = new Vector();
		for (String s : this._hmVar2Value.keySet()) {
			multVector.set(s, this.get(s)*d);
		}
		return multVector;
	}

	public double computeL2Norm() {
		double sum = 0, norm = 0;
		for (String s : this._hmVar2Value.keySet()) {
			sum += Math.pow(this.get(s),2);
		}
		norm = Math.sqrt(sum);
		return norm;
	}
	
	public boolean equals(Object o) {
		if (o instanceof Vector) {
			Vector v = (Vector)o;
			if (this._hmVar2Value.keySet().equals(v._hmVar2Value.keySet())) {
				for (String s : this._hmVar2Value.keySet()) {
					if (!this._hmVar2Value.get(s).equals(v._hmVar2Value.get(s)))
						return false;
				}
				return true;
			}
			else return false;
		}
		return false;
	}
	/** Your Vector class should implement the core functionality below and produce
	 *  **all** of the expected outputs below.  **These will be tested for grading.**
	 * 
	 *  When initially developing the code, comment out lines below that you have
	 *  not implemented yet.  This will allow your code to compile for incremental
	 *  testing.
	 *  
	 * @param args (unused -- ignore)
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		// Make vector: vec1[x y z] = [1 2 3]
		Vector vec1 = new Vector();
		vec1.set("x", 1.0);
		vec1.set("y", 2.0);
		vec1.set("z", 3.0);
		
		// Make vector: vec2[x y z] = [-3 -2 -1]
		Vector vec2 = new Vector();
		vec2.set("x", -3.0);
		vec2.set("y", -2.0);
		vec2.set("z", -1.0);
		
		// Make vector: vec3[x y z] = vec4[x y z] = [-1 -2 -3]
		Vector vec3 = new Vector("{ x=-1 y=-2.0 z=3d }");
		Vector vec4 = new Vector(vec3.toString());
		
		// Hint: all numbers below are formatted with String.format("%s=%6.4f ", var, val)
		//       ... you may want to use this in your Vector.toString() implementation!
		
		// Test cases: 
		System.out.println(vec1); // Should print: { x=1.0000 y=2.0000 z=3.0000 }
		System.out.println(vec2); // Should print: { x=-3.0000 y=-2.0000 z=-1.0000 }
		System.out.println(vec3); // Should print: { x=-1.0000 y=-2.0000 z=3.0000 }
		System.out.println(vec4); // Should print: { x=-1.0000 y=-2.0000 z=3.0000 }
		System.out.println(vec1.sum(vec1));        // Should print: { x=2.0000 y=4.0000 z=6.0000 }
		System.out.println(vec1.sum(vec2));        // Should print: { x=-2.0000 y=0.0000 z=2.0000 }
		System.out.println(vec1.sum(vec3));        // Should print: { x=0.0000 y=0.0000 z=6.0000 }
		System.out.println(vec1.scalarMult(0.5));  // Should print: { x=0.5000 y=1.0000 z=1.5000 }
		System.out.println(vec2.scalarMult(-1.0)); // Should print: { x=3.0000 y=2.0000 z=1.0000 }
		System.out.println(vec1.sum(vec2.scalarMult(-1.0))); // Should print: { x=4.0000 y=4.0000 z=4.0000 }
		System.out.format("%01.3f\n", vec1.computeL2Norm());           // Should print: 3.742
		System.out.format("%01.3f\n", vec2.sum(vec3).computeL2Norm()); // Should print: 6.000
		
		// If the following don't work, did you override equals()?  See Project 2 Vector and Matrix.
		System.out.println(vec3.equals(vec1)); // Should print: false
		System.out.println(vec3.equals(vec3)); // Should print: true
		System.out.println(vec3.equals(vec4)); // Should print: true
		System.out.println(vec1.sum(vec2).equals(vec2.sum(vec1))); // Should print: true
	}

}

