package pl.kurs.services;

import java.util.Arrays;

public class MethodLibrary {

//    Zadanie 01:
// napisz metode boolean isArithmetic(int[] sequence), ktora zwroci informacje o tym czy dany jako argument ciag jest
// arytmetyczny czy nie.
    public boolean isSequenceArithmetic(int[] sequence) {
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
    private boolean isSequenceGeometric(int[] sequence) {
        int ratio = sequence[1] / sequence[0];
        for (int i = 1; i < sequence.length - 1; i++) {
            if ((sequence[i + 1] / ratio) != sequence[i]) {
                return false;
            }
        }
        return true;
    }
    public String getSequenceName(int[] sequence) {
        if (isSequenceGeometric(sequence)) {
            return "GEOMETRYCZNY";
        } else if (isSequenceArithmetic(sequence)) {
            return "ARYTMETYCZNY";
        } else {
            return "INNY";
        }
    }

// Zadanie 03:
// napisz metodę int[] superPrimes(int from, int to) ktora zwroci wszystkie liczby super-pierwsze z zakresu <from, to>
// taką liczbą jest np: 11, dlaczego? Bo liczba super pierwsza to taka która sama jest liczbą pierwszą oraz jej suma cyfr
// też jest liczbą pierwszą. (a liczba pierwsza to taka ktora ma dokładnie dwa dzielniki, nie wiecej, nie mniej)
    private int[] arrayFillUp(int from, int to) {
        int arrayLength = (to - from);
        int[] temporaryArray = new int[arrayLength + 1];
        for (int i = 0; i < temporaryArray.length; i++) {
            temporaryArray[i] = from;
            from++;
        } return temporaryArray;
    }
    public int[] superPrimes(int from, int to) {
        int[] temporaryArray = arrayFillUp(from, to);
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
    private int[] convertStringToIntArray(String input) {
        String[] array = input.split(" ");
        int[] temporaryArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            int k = Integer.parseInt(array[i]);
            temporaryArray[i] = k;
        } return temporaryArray;
    }
    private boolean isSequenceAscending(int[] sequence) {
        boolean isAscending = true;
        for (int i = 0; i < sequence.length - 1; i++) {
            if (sequence[i] > sequence[i + 1]) {
                isAscending = false;
                break;
            }
        } return isAscending;
    }
    private boolean isSequenceDescending(int[] sequence) {
        boolean isDescending = true;
        for (int i = 0; i < sequence.length - 1; i++) {
            if (sequence[i] < sequence[i + 1]) {
                isDescending = false;
                break;
            }
        } return isDescending;
    }
    private boolean isSequenceStatic(int[] sequence) {
        for (int i = 0; i < sequence.length; i++) {
            if (sequence[0] - sequence[1] != 0) {
                return false;
            }
        } return true;
    }
    private int findMin(int[] array) {
        int min = array[0];
        for (int k : array) {
            if (k < min) {
                min = k;
            }
        } return min;
    }
    private int findMax(int[] array) {
        int max = array[0];
        for (int k : array) {
            if (k > max) {
                max = k;
            }
        } return max;
    }
    private int findMostFrequent(int[] array) {
        int maxCounter = 0;
        int maxFrequency = 0;
        for (int j : array) {
            int counter = 0;
            for (int i : array) {
                if (j == i) {
                    counter++;
                }
            }
            if (counter > maxCounter && counter > 1) {
                maxCounter = counter;
                maxFrequency = j;
            }
        } return maxFrequency;
    }
    private boolean areNumbersNatural(int[] array) {
        int min = findMin(array);
        int max = findMax(array);
        int[] temporaryArray = arrayFillUp(min, max);
        boolean areNumbersNatural = true;
        for (int j : temporaryArray) {
            if (j <= 0) {
                areNumbersNatural = false;
                break;
            }
        } return areNumbersNatural;
    }
    public String verifyAndReport(String input) {
        int[] temporaryArray = convertStringToIntArray(input);
        if (isSequenceAscending(temporaryArray)) {
            System.out.println("Monotoniczność ciągu jest rosnąca.");
            System.out.println("Największa liczba z ciągu to: " + findMax(temporaryArray) + ", a najmniejsza to: " + findMin(temporaryArray));
            if (findMostFrequent(temporaryArray) > 1) {
                System.out.println("Najczęściej występująca liczba: " + findMostFrequent(temporaryArray));
            } else {
                System.out.println("Każda z liczb zawartych w ciągu występuje tylko raz");
            }
            System.out.println("Czy wszystkie liczby są naturalne: " + areNumbersNatural(temporaryArray));
            System.out.println();
            return "NIESTETY NIE WIEM, DLACZEGO NIE DZIAŁA OUTPUT Z RETURN :(";
        } else if (isSequenceStatic(temporaryArray)) {
            System.out.println("Monotoniczność ciągu jest stała.");
            System.out.println("Największa liczba z ciągu to: " + findMax(temporaryArray) + ", a najmniejsza to: " + findMin(temporaryArray));
            if (findMostFrequent(temporaryArray) > 1) {
                System.out.println("Najczęściej występująca liczba: " + findMostFrequent(temporaryArray));
            } else {
                System.out.println("Każda z liczb zawartych w ciągu występuje tylko raz");
            }
            System.out.println("Czy wszystkie liczby są naturalne: " + areNumbersNatural(temporaryArray));
            System.out.println();
            return "NIESTETY NIE WIEM, DLACZEGO NIE DZIAŁA OUTPUT Z RETURN :(";
        } else if (isSequenceDescending(temporaryArray)) {
            System.out.println("Monotoniczność ciągu jest malejąca.");
            System.out.println("Największa liczba z ciągu to: " + findMax(temporaryArray) + ", a najmniejsza to: " + findMin(temporaryArray));
            if (findMostFrequent(temporaryArray) > 1) {
                System.out.println("Najczęściej występująca liczba: " + findMostFrequent(temporaryArray));
            } else {
                System.out.println("Każda z liczb zawartych w ciągu występuje tylko raz");
            }
            System.out.println("Czy wszystkie liczby są naturalne: " + areNumbersNatural(temporaryArray));
            System.out.println();
            return "NIESTETY NIE WIEM, DLACZEGO NIE DZIAŁA OUTPUT Z RETURN :(";
        } else {
            System.out.println("Ciąg nie jest monotoniczny.");
            System.out.println("Największa liczba z ciągu to: " + findMax(temporaryArray) + ", a najmniejsza to: " + findMin(temporaryArray));
            if (findMostFrequent(temporaryArray) > 1) {
                System.out.println("Najczęściej występująca liczba: " + findMostFrequent(temporaryArray));
            } else {
                System.out.println("Każda z liczb zawartych w ciągu występuje tylko raz");
            }
            System.out.println("Czy wszystkie liczby są naturalne: " + areNumbersNatural(temporaryArray));
            System.out.println();
            return "NIESTETY NIE WIEM, DLACZEGO NIE DZIAŁA OUTPUT Z RETURN :(";
        }
    }
}

