import java.io.File;
import java.io.IOException;

public class Driver {
    public static void main(String[] args) {

        Polynomial p = new Polynomial();

        System.out.println("p(3) = " + p.evaluate(3));

   
        double[] c1 = {6, 5};
        int[] e1 = {0, 3}; 
        Polynomial p1 = new Polynomial(c1, e1);

        double[] c2 = {-2, -9};
        int[] e2 = {1, 4}; 
        Polynomial p2 = new Polynomial(c2, e2);


        Polynomial s1 = p1.add(p2);
        System.out.println("s1(0.1) = " + s1.evaluate(0.1));

        double[] c3 = {6, 5};
        int[] e3 = {0, 3}; 
        Polynomial p3 = new Polynomial(c3, e3);

        double[] c4 = {-6, -5};
        int[] e4 = {0, 3}; 
        Polynomial p4 = new Polynomial(c4, e4);


        Polynomial s2 = p3.add(p4);
        System.out.println("s2(0.1) = " + s2.evaluate(0.1));


        if (s1.hasRoot(1)) {
            System.out.println("1 is a root of s1");
        } else {

            System.out.println("1 is not a root of s1");
        }

        if (s1.hasRoot(2)) {
            System.out.println("2 is a root of s1");
        } else {

            System.out.println("2 is not a root of s1");
        }
        if (s2.hasRoot(1)) {
            System.out.println("1 is a root of s2");
        } else {

            System.out.println("1 is not a root of s2");
        }


        Polynomial product = p1.multiply(p2);
        System.out.println("product(2) = " + product.evaluate(2));


        try {
            File input = new File("input.txt");
            Polynomial polyFile = new Polynomial(input);

            System.out.println("polyFile(0.1) (should be same as s(0.1))= " + polyFile.evaluate(0.1));

            polyFile.saveToFile("output.txt");

            System.out.println("Saved poly to output.txt");
        } catch (IOException e) {


            System.out.println("File error: " + e.getMessage());
        }
    }
}