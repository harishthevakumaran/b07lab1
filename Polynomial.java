import java.util.Arrays;

public class Polynomial{

	double[] coefficients_poly;
	
	public Polynomial(){
		coefficients_poly = new double[1];
		coefficients_poly[0] = 0.0;
	}
	
	public Polynomial(double[] poly_initialize){
		coefficients_poly = new double[poly_initialize.length];
		for(int i = 0; i < coefficients_poly.length; i++){
			coefficients_poly[i] = poly_initialize[i];
		}
		
		
	}
	
	public Polynomial add(Polynomial second_poly){
		int length1;
		int length2;
		int final_length;
		Polynomial cpy;
		length1 = this.coefficients_poly.length;
		length2 = second_poly.coefficients_poly.length;
		if(length1 > length2){
			cpy = new Polynomial(this.coefficients_poly);
			final_length = length1;
			
		}
		else{
			cpy = second_poly;
			final_length = length2;
		}
		double[] sumCoefficient = new double[final_length];
		for (int i = 0; i < final_length; i++){
			double first_co = 0.0;
			double second_co = 0.0;
			if( i < length1){
				first_co = this.coefficients_poly[i];
			}
			if(i < length2){
				second_co = second_poly.coefficients_poly[i];
				
			}
			sumCoefficient[i] = first_co + second_co;
			
		}
		
		return new Polynomial(sumCoefficient);
		
		
		
		
		
		
		
	}
	
	public double evaluate(double x_val){
		double output = 0.0;
		for(int i =0; i < coefficients_poly.length; i++){
			output = output + coefficients_poly[i] * Math.pow(x_val, i);
		}
		return output;
		
	}
	
	public boolean hasRoot(double x_val){
		double hasaroot;
		hasaroot = this.evaluate(x_val);
		if(hasaroot ==0){
			return true;
		}
		else{
			return false;
		}
	}
	
}	
	
	
	
	
	
	
	





























