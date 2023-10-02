import java.util.Arrays;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.*;

public class Polynomial{

	double[] coefficients_poly;
	int[] exponent;
	
	public Polynomial(){
		coefficients_poly = new double[1];
		exponent = new int[1];
		coefficients_poly[0] = 0.0;
		exponent[0] = 0;
	}
	
	public Polynomial(double[] poly_initialize, int[] exponent_intialize){
		this.coefficients_poly = poly_initialize;
		this.exponent = exponent_intialize;
		
		
	}
	public Polynomial(double[] poly_initialize){
		coefficients_poly = new double[poly_initialize.length];
		for(int i = 0; i < coefficients_poly.length; i++){
			coefficients_poly[i] = poly_initialize[i];
		}
		for(int i = 0; i < coefficients_poly.length; i++){
			exponent[i] = i;
		}
		
	}
	
	public Polynomial(File poly_file){
		coefficients_poly = new double[0];
		exponent = new int[0];
		
		
		try(BufferedReader readerObj = new BufferedReader(new FileReader(poly_file))){
			String lineRead = readerObj.readLine();
			if(lineRead != null){
				String[] coefficients_text = lineRead.split("(?=[+-])");
				coefficients_poly = new double[coefficients_text.length];
				exponent = new int[coefficients_text.length];
				for(int i = 0; i <coefficients_text.length; i++){
					String texts = coefficients_text[i].trim();
					if(!texts.isEmpty()){
						String[] index_poly = texts.split("x");
						double new_coefficient = Double.parseDouble(index_poly[0]);
						int new_exponent = 0;
						if(index_poly.length >= 2){
							new_exponent = Integer.parseInt(index_poly[1]);
						}
						coefficients_poly[i] = new_coefficient;
						exponent[i] = new_exponent;
					}
					
				}
				
			}
			else{
				coefficients_poly = new double[0];
				exponent = new int[0];
				
			}
			
			
		}
		catch(IOException exceptionObj){
			System.err.println("Error from gathering input from file: "+ exceptionObj.getMessage());
		}
		
		
		
	}
	
	
	
	public Polynomial multiply(Polynomial second_poly){
		int maxExpo1 = this.currentExponent();
		int maxExpo2 = second_poly.currentExponent();
		double[] newCoefficient = new double[maxExpo1 + maxExpo2 + 1];
		int[] newExponents = new int[maxExpo1 + maxExpo2 + 1];
		for(int i = 0; i<= maxExpo1; i++){
			for(int j = 0; j <= maxExpo2; j++){
				double changedCoefficient = this.coefficients_poly[i] * second_poly.coefficients_poly[j];
				int changedExpo = this.exponent[i] + second_poly.exponent[j];
				if(newExponents[changedExpo] == 0){
					newCoefficient[changedExpo] = changedCoefficient;
					newExponents[changedExpo]= changedExpo;
				}
				else{
					newCoefficient[changedExpo] = newCoefficient[changedExpo] + changedCoefficient;
				}
				
				
			}
			
		}
		
		return new Polynomial(newCoefficient, newExponents).polynomialSimplify();
		
		
	}
	
	public int currentExponent(){
		int currentExpo = 0;
		for (int i = 0; i < exponent.length; i++){
			if(exponent[i] > currentExpo){
				currentExpo = exponent[i];
				
			}
			
		}
		return currentExpo;
		
		
	}
	
	public Polynomial polynomialSimplify(){
		int changed_size = 0;
		
		for(int i = 0; i< coefficients_poly.length; i++){
			if(coefficients_poly[i] != 0){
				changed_size = changed_size + 1;
				
			}
			
		}
		double[] finalCoefficient = new double[changed_size];
		int [] finalExpo = new int[changed_size];
		for(int i = 0; i < changed_size; i++){
			finalExpo[i] = exponent[i];
			finalCoefficient[i] = coefficients_poly[i];
			
		}
		return new Polynomial(finalCoefficient, finalExpo);
		
		
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
	
	public void saveToFile(String fileString){
		try(FileWriter writerObj = new FileWriter(fileString)){
			for(int i = 0; i < coefficients_poly.length; i++){
				double current_coefficient = coefficients_poly[i];
				int current_expo = exponent[i];
				if(i > 0 && current_coefficient > 0){
					writerObj.write("+");
				}
				if(current_coefficient > 0 || current_coefficient < 0){
					if(current_expo == 0){
						writerObj.write(Double.toString(current_coefficient));
					}
					else{
						if(current_coefficient > 1 || current_coefficient < 1){
							writerObj.write(Double.toString(current_coefficient));
						}
						writerObj.write("x");
						if(current_expo != 1){
							writerObj.write(Integer.toString(current_expo));
						}
						
					}
					
				}
				
				
			}
		}
		catch(IOException exceptionObj1){
			System.err.println("Error in editing the file " + exceptionObj1.getMessage());
		}
		
	}
	
	
	public double evaluate(double x_val){
		double output = 0.0;
		for(int i =0; i < coefficients_poly.length; i++){
			output = output + coefficients_poly[i] * Math.pow(x_val, exponent[i]);
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
	
	
	
	
	
	
	





























