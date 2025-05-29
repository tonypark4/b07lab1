
import java.io.*;

public class Polynomial{
	private double[] coefficients;
	private int[] exponents;

	public Polynomial(){

		this.coefficients = new double[0];
		this.exponents = new int[0];
	
	}

    	
	public Polynomial(double[] coefficients, int [] exponents){ 
        
        	int length = coefficients.length;
        	this.coefficients = new double[length];
			this.exponents = new int[length];

        	for (int i = 0; i < length; i++) {
            		this.coefficients[i] = coefficients[i];
					this.exponents[i] = exponents[i];
        	}

			
    	}

	public Polynomial(File poly) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(poly));

		String line = reader.readLine();
		reader.close();

		int length = 1;

		for(int i=1; i<line.length(); i++){
			if(line.charAt(i) == '+' || line.charAt(i) == '-'){
				length++;
			}
		}

		String[] terms = new String[length];

		int start = 0;
		int counter = 0;

		for (int i =1; i<line.length(); i++) {
			if(line.charAt(i) == '+' || line.charAt(i) == '-'){
				terms[counter] = line.substring(start, i);

				start = i;
				counter++;
			}
		}

		terms[counter] = line.substring(start);


		this.coefficients = new double[length];
		this.exponents = new int[length];

		for (int i=0; i< length; i++){
			String term = terms[i];
			double coeff;
			int exp;

			int xPos = term.indexOf('x');

			if(xPos == -1){
				coeff = Double.parseDouble(term);
				exp = 0;

			}
			else{
				String coeffStr = term.substring(0, xPos);
				String expStr = term.substring(xPos + 1);

				if(coeffStr.equals("") || coeffStr.equals("+")){
					coeff = 1.0;
				}
				else if(coeffStr.equals("-")){
					coeff = -1.0;
				}
				else{
					coeff = Double.parseDouble(coeffStr);
				}

				if (expStr.equals("")){
					exp =1;
				}
				else{
					exp = Integer.parseInt(expStr);
				}

			}

			this.coefficients[i] = coeff;

			this.exponents[i] = exp;
		}


	}


	public Polynomial add(Polynomial p1){

		int max;
		int lengthThis= this.exponents[this.exponents.length-1];
		int lengthP1 = p1.exponents[p1.exponents.length-1];

		if(lengthThis <= lengthP1){
			max = lengthP1;

		}
		
		else{
			max = lengthThis;
		}


		double[] addedCoeff = new double[max+1];

		

		for(int i=0; i<this.coefficients.length; i++){
			addedCoeff[this.exponents[i]] += this.coefficients[i];

		}



		for(int i=0; i<p1.coefficients.length; i++){
			addedCoeff[p1.exponents[i]] += p1.coefficients[i];

		
		}


	



		int lastlength = 0;

		for(int i=0; i<addedCoeff.length; i++){
			if(addedCoeff[i] != 0){
				lastlength++;
			}
		}

		double[] finalCoeff = new double[lastlength];
		int[] finalExp = new int[lastlength];


		int tracker = 0;
		for(int i=0; i<addedCoeff.length; i++){
			if(addedCoeff[i] != 0){
				finalCoeff[tracker] = addedCoeff[i];
				finalExp[tracker] = i;
				tracker++;
			}
		}

 
		
		return new Polynomial(finalCoeff, finalExp);
	}
	

	public double evaluate(double x){
		int length = this.coefficients.length;
		double result = 0;

		


		for(int i=0; i<length; i++){

			result += this.coefficients[i] * Math.pow(x, this.exponents[i]);

		}

		return result;


	}

	public boolean hasRoot(double x){

		return Math.abs(evaluate(x)) < 0.0000000001;

	}

	public Polynomial multiply(Polynomial p1){

		int max = this.exponents[this.exponents.length-1] + p1.exponents[p1.exponents.length-1];
		double[] resultCoeff = new double[max + 1];

		for(int i = 0; i < this.coefficients.length; i++){
			for(int j = 0; j < p1.coefficients.length; j++){
				int newExp = this.exponents[i] + p1.exponents[j];

				resultCoeff[newExp] += this.coefficients[i] * p1.coefficients[j];
			}
		}

		int lastlength = 0;

		for(int i=0; i<resultCoeff.length; i++){
			if(resultCoeff[i] != 0){
				lastlength++;
			}
		}


		double[] finalCoeff = new double[lastlength];
		int[] finalExp = new int[lastlength];


		int tracker = 0;
		for(int i=0; i<resultCoeff.length; i++){
			if(resultCoeff[i] != 0){
				finalCoeff[tracker] = resultCoeff[i];
				finalExp[tracker] = i;
				tracker++;
			}
		}


		return new Polynomial(finalCoeff, finalExp);
	}

	public void saveToFile(String file) throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));


		StringBuilder sb = new StringBuilder();


		for(int i=0; i< this.coefficients.length; i++){

			if(i > 0 && this.coefficients[i]>= 0){
				sb.append("+");

			}

			sb.append(this.coefficients[i]);


			if(this.exponents[i]> 0){

				sb.append("x");



				if(this.exponents[i] > 0){ 

					sb.append(this.exponents[i]);
				}
			}
		}

		writer.write(sb.toString());


		writer.close();

	}



}