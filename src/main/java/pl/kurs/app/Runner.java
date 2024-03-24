package pl.kurs.app;

import pl.kurs.services.MethodLibrary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) throws FileNotFoundException {

        MethodLibrary verify = new MethodLibrary();

        int[] array1 = {4, 8, 12, 16, 20};
        int[] array2 = {-2, -4, -6, -8, -10};
        int[] array3 = {2, 3, 6, 7, 10};
        int[] array4 = {3, 9, 27, 81, 243};

        System.out.println(verify.isArithmetic(array1));
        System.out.println(verify.isArithmetic(array2));
        System.out.println(verify.isArithmetic(array3));
        System.out.println(verify.isArithmetic(array4));

        System.out.println("\n");

        System.out.println(verify.getSequenceName(array1));
        System.out.println(verify.getSequenceName(array2));
        System.out.println(verify.getSequenceName(array3));
        System.out.println(verify.getSequenceName(array4));

        System.out.println("\n");

        int[] someArray = verify.superPrimes(1, 111);

        System.out.println("\n");

        Scanner scan = new Scanner(new File("liczby.txt"));
        while (scan.hasNext()) {
            String line = scan.nextLine();
            verify.verifyAndReport(line);
        }
        scan.close();
    }
}
