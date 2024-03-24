package pl.kurs.services;

import java.util.Arrays;

public class MethodLibrary {

//    Zadanie 01:
// napisz metode boolean isArithmetic(int[] sequence), ktora zwroci informacje o tym czy dany jako argument ciag jest
// arytmetyczny czy nie.

    public boolean isArithmetic(int[] sequence) {
        int subtractionDifference = sequence[1] - sequence[0];
        for (int i = 0; i < sequence.length - 1; i++) {
            if (sequence[i + 1] - sequence[i] != subtractionDifference) {
                return false;
            }
        }
        return true;
    }

// Zadanie 02:
// napisz metode String getSequenceName(int[] sequence) ktora zwroci:
// - GEOMETRYCZNY - jesli podany ciag jest geometryczny
// - ARYTMETYCZNY - jesli podany ciag jest arytmetyczny
// - INNY - w przypadku gdy zadne z powyzszych

    public String getSequenceName(int[] sequence) {
        boolean isGeometric = true;
        boolean isArithmetic = true;
        int difference = sequence[1] - sequence[0];
        int ratio = sequence[1] / sequence[0];
        for (int i = 1; i < sequence.length - 1; i++) {
            if ((sequence[i + 1] / ratio) != sequence[i]) {
                isGeometric = false;
            }
            if ((sequence[i + 1] - sequence[i]) != difference) {
                isArithmetic = false;
            }
        }
        if (isGeometric) {
            return "GEOMETRYCZNY";
        } else if (isArithmetic) {
            return "ARYTMETYCZNY";
        } else {
            return "INNY";
        }
    }

// Zadanie 03:
// napisz metodę int[] superPrimes(int from, int to) ktora zwroci wszystkie liczby super-pierwsze z zakresu <from, to>
// taką liczbą jest np: 11, dlaczego? Bo liczba super pierwsza to taka która sama jest liczbą pierwszą oraz jej suma cyfr
// też jest liczbą pierwszą. (a liczba pierwsza to taka ktora ma dokładnie dwa dzielniki, nie wiecej, nie mniej)

    public int[] superPrimes(int from, int to) {
        int arrayLength = (to - from);
        int[] temporaryArray = new int[arrayLength + 1];
        for (int i = 0; i < temporaryArray.length; i++) {
            temporaryArray[i] = from;
            from++;
        }
        int counter = 0;
        for (int i = 0; i < temporaryArray.length; i++) {
            if (temporaryArray[i] % 2 != 0 && temporaryArray[i] % 3 != 0) {
                int sum = 0;
                int isSuperPrime = temporaryArray[i];
                while (temporaryArray[i] > 0) {
                    sum += temporaryArray[i] % 10;
                    temporaryArray[i] /= 10;
                }
                if (sum % 2 != 0 && sum % 3 != 0) {
                    temporaryArray[counter] = isSuperPrime;
                    counter++;
                }
            }
        }
        System.out.println(Arrays.toString(Arrays.copyOf(temporaryArray, counter))); //dla ułatwienia podglądu przy weryfikacji testu
        return Arrays.copyOf(temporaryArray, counter);
    }

// Zadanie 04:
// dany jest plik liczby.txt
// w ktorym w kazdym wierszu sa liczby rozdzielone spacja.
// np:
// 1 2 3 4
// 10 20 30 40 50 70
// 1 3 2 55 4 3 222 4 6 7
// itp
// dla kazdej linii nalezy stworzyc raport w postaci:
// - okreslic monotonicznosc ciagu
// - znalezc mina, maxa w ciagu
// - znalezc najpopularniejsza liczbe z ciagu
// - stwierdzic czy miedzy min i max znajduja sie wszystkie mozliwe liczby naturalne.
//
// Raport moze byc wypisany na standardowej konsoli.

    public String verifyAndReport(String input) {
        String[] array = input.split(" ");
        int[] temporaryArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            int k = Integer.parseInt(array[i]);
            temporaryArray[i] = k;
        }
        boolean sortedAscending = true;
        boolean sortedDescending = true;
        boolean isStatic = true;
        for (int i = 0; i < temporaryArray.length - 1; i++) {
            if (temporaryArray[i] < temporaryArray[i + 1]) {
                sortedDescending = false;
                isStatic = false;
            } else if (temporaryArray[i] > temporaryArray[i + 1]) {
                sortedAscending = false;
                isStatic = false;
            } else if (temporaryArray[i] == temporaryArray[i + 1]) {
                sortedDescending = false;
                sortedAscending = false;
            }
        }
        int min = temporaryArray[0];
        int max = temporaryArray[0];
        for (int k : temporaryArray) {
            if (k < min) {
                min = k;
            }
            if (k > max) {
                max = k;
            }
        }
        int maxCounter = 0;
        int maxFrequency = 0;
        for (int j : temporaryArray) {
            int counter = 0;
            for (int i : temporaryArray) {
                if (j == i) {
                    counter++;
                }
            }
            if (counter > maxCounter && counter > 1) {
                maxCounter = counter;
                maxFrequency = j;
            }
        }
        boolean areNumbersNatural = true;
        for (int j : temporaryArray) {
            if (j <= 0) {
                areNumbersNatural = false;
                break;
            }
        }
        if (sortedAscending) {
            System.out.println("Monotoniczność ciągu jest rosnąca. Największa liczba z ciągu to: " + max + ", a najmniejsza to: " + min);
            if (maxFrequency > 1) {
                System.out.println("Najczęściej występująca liczba: " + maxFrequency);
            } else {
                System.out.println("Każda z liczb zawartych w ciągu występuje tylko raz");
            }
            System.out.println("Czy wszystkie liczby są naturalne: " + areNumbersNatural);
            System.out.println();
            return "NIESTETY NIE WIEM, DLACZEGO NIE DZIAŁA OUTPUT Z RETURN :(";
        } else if (sortedDescending) {
            System.out.println("Monotoniczność ciągu jest malejąca. Największa liczba z ciągu to: " + max + ", a najmniejsza to: " + min);
            if (maxFrequency > 1) {
                System.out.println("Najczęściej występująca liczba: " + maxFrequency);
            } else {
                System.out.println("Każda z liczb zawartych w ciągu występuje tylko raz");
            }
            System.out.println("Czy wszystkie liczby są naturalne: " + areNumbersNatural);
            System.out.println();
            return "NIESTETY NIE WIEM, DLACZEGO NIE DZIAŁA OUTPUT Z RETURN :(";
        } else if (isStatic) {
            System.out.println("Monotoniczność ciągu jest stała. Największa liczba z ciągu to: " + max + ", a najmniejsza to: " + min);
            if (maxFrequency > 1) {
                System.out.println("Najczęściej występująca liczba: " + maxFrequency);
            } else {
                System.out.println("Każda z liczb zawartych w ciągu występuje tylko raz");
            }
            System.out.println("Czy wszystkie liczby są naturalne: " + areNumbersNatural);
            System.out.println();
            return "NIESTETY NIE WIEM, DLACZEGO NIE DZIAŁA OUTPUT Z RETURN :(";
        } else {
            System.out.println("Ciąg nie jest monotoniczny. Największa liczba z ciągu to: " + max + ", a najmniejsza to: " + min);
            if (maxFrequency > 1) {
                System.out.println("Najczęściej występująca liczba: " + maxFrequency);
            } else {
                System.out.println("Każda z liczb zawartych w ciągu występuje tylko raz");
            }
            System.out.println("Czy wszystkie liczby są naturalne: " + areNumbersNatural);
            System.out.println();
            return "NIESTETY NIE WIEM, DLACZEGO NIE DZIAŁA OUTPUT Z RETURN :(";
        }
    }
}

