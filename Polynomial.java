public class Polynomial{
	private double[] coefficients;

	public Polynomial(){

		this.coefficients = new double[]{0};
	}

    	
	public Polynomial(double[] coefficients) {
        
        	int last = coefficients.length - 1;

        	while (last > 0 && coefficients[last] == 0) {
            		last--; 
        	}

        	this.coefficients = new double[last + 1];

        	for (int i = 0; i <= last; i++) {
            		this.coefficients[i] = coefficients[i];
        	}
    	}


	public Polynomial add(Polynomial p1){
		int max = 0;

		if(this.coefficients.length <= p1.coefficients.length){
			max = p1.coefficients.length;

		}
		
		else{
			max = this.coefficients.length;
		}

		double[] result = new double[max];

		for(int i=0; i<max; i++){
			double a = 0;
			double b = 0;

			if(i<this.coefficients.length){
			
				a = this.coefficients[i];
			}
			if(i<p1.coefficients.length){
			
				b = p1.coefficients[i];
			}

			result[i] = a+b;

		}
		
		return new Polynomial(result);
	}
	

	public double evaluate(double x){
		double result = this.coefficients[0];
		double xPowered = x;

		int length = this.coefficients.length;
		for(int i=1; i<length; i++){

			result += this.coefficients[i] * xPowered;
			xPowered *= x;

		}

		return result;


	}

	public boolean hasRoot(double x){

		return Math.abs(evaluate(x)) < 0.0000000001;

	}
}