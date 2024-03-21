import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("\nInput: ");
            String input = scan.nextLine();
            System.out.println("\nOutput: ");
            System.out.println(calc(input));
            System.out.println("\n---------------");
        }
    }

    public static String calc(String input) throws IOException {

        boolean arabicQ;
        int num1;
        int num2;

        input = input.replaceAll(" ", ""); // Удаляем пробелы

        String[] nums = input.split("[+\\-*/]"); // Разделяем числа

        if (nums.length != 2) { // Проверяем, что получено строго два числа
            throw new IOException();
        }

        if (String.join("", nums).replaceAll("[0-9]", "").isEmpty()) { // Проверяем полученную запись
            arabicQ = true; // Используются только арабские цифры
            num1 = Integer.parseInt(nums[0]);
            num2 = Integer.parseInt(nums[1]);
        } else if (String.join("", nums).replaceAll("[IVX]", "").isEmpty()) {
            arabicQ = false; // Используются только римские цифры
            num1 = romanToArabic(nums[0]);
            num2 = romanToArabic(nums[1]);
        } else {
            throw new IOException(); // Встречены другие символы или цифры из обеих систем счисления
        }

        if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) { // Числа должны быть в диапазоне от 1 до 10
            throw new IOException();
        }

        int result = switch (input.replaceAll("[^+\\-*/]", "")) { // Калькуляция
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> num1 / num2;
            default -> throw new IOException();
        };

        if (arabicQ) { // Выводим результат
            return Integer.toString(result);
        } else {
            if (result < 1) {
                throw new IOException();
            }
            return arabicToRoman(result);
        }
    }

    static int romanToArabic(String roman) { // Ковертируем римские цифры в арабские
        return roman.replaceAll("IX", "VIV")
                .replaceAll("X", "VV")
                .replaceAll("IV", "IIII")
                .replaceAll("V", "IIIII")
                .length();
    }

    static String arabicToRoman(int arabic) { // Ковертируем арабские цифры в римские
        return "I".repeat(arabic)
                .replaceAll("IIIII", "V")
                .replaceAll("IIII", "IV")
                .replaceAll("VV", "X")
                .replaceAll("VIV", "IX")
                .replaceAll("XXXXX", "L")
                .replaceAll("XXXX", "XL")
                .replaceAll("LL", "C")
                .replaceAll("LXL", "XC");
    }
}