package org.MyTesting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/*
Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления с двумя числами: a + b, a - b, a * b, a / b.
Данные передаются в одну строку (смотри пример)!
Решения, в которых каждое число и арифмитеческая операция передаются с новой строки считаются неверными.
Калькулятор умеет работать как с арабскими (1,2,3,4,5…), так и с римскими (I,II,III,IV,V…) числами.
Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более. На выходе числа не ограничиваются по величине и могут быть любыми.
Калькулятор умеет работать только с целыми числами.
Калькулятор умеет работать только с арабскими или римскими цифрами одновременно,
при вводе пользователем строки вроде 3 + II калькулятор должен выбросить исключение и прекратить свою работу.
При вводе римских чисел, ответ должен быть выведен римскими цифрами, соответственно, при вводе арабских - ответ ожидается арабскими.
При вводе пользователем неподходящих чисел приложение выбрасывает исключение и завершает свою работу.
При вводе пользователем строки, не соответствующей одной из вышеописанных арифметических операций,
приложение выбрасывает исключение и завершает свою работу.
Результатом операции деления является целое число, остаток отбрасывается.
Результатом работы калькулятора с арабскими числами могут быть отрицательные числа и ноль.
Результатом работы калькулятора с римскими числами могут быть только положительные числа, если результат работы меньше единицы, выбрасывается исключение
 */

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner console = new Scanner(System.in);

        while (console.hasNextLine())
            System.out.println(calc(console.nextLine()));

    }

    public static String calc(String input) throws Exception {
        boolean inputHasRoman = input.contains("I") || input.contains("V") || input.contains("X");
        boolean inputHasNumbers = input.contains("1") || input.contains("2") || input.contains("3") || input.contains("4") || input.contains("5") || input.contains("6") || input.contains("7") || input.contains("8") || input.contains("9") || input.contains("0");
        boolean hasOperator = input.contains(" - ") || input.contains(" + ") || input.contains(" / ") || input.contains(" * ");

        if (!inputHasRoman && inputHasNumbers) {
            if (input.contains(" * ") && input.split(" \\* ").length == 2) {
                String[] mathsArray = input.split(" \\* ");
                int num1 = Integer.parseInt(mathsArray[0]);
                int num2 = Integer.parseInt(mathsArray[1]);

                if (num1 < 11 && num2 < 11)
                    return "" + (num1 * num2);
                else
                    throw new Exception();
            } else if (input.contains(" + ") && input.split(" \\+ ").length == 2) {
                String[] mathsArray = input.split(" \\+ ");
                int num1 = Integer.parseInt(mathsArray[0]);
                int num2 = Integer.parseInt(mathsArray[1]);

                if (num1 < 11 && num2 < 11)
                    return "" + (num1 + num2);
                else
                    throw new Exception();
            } else if (input.contains(" / ") && input.split(" \\/ ").length == 2) {
                String[] mathsArray = input.split(" \\/ ");
                int num1 = Integer.parseInt(mathsArray[0]);
                int num2 = Integer.parseInt(mathsArray[1]);

                if (num1 < 11 && num2 < 11)
                    return "" + (num1 / num2);
                else
                    throw new Exception();
            } else if (input.contains(" - ") && input.split(" \\- ").length == 2) {
                String[] mathsArray = input.split(" \\- ");
                int num1 = Integer.parseInt(mathsArray[0]);
                int num2 = Integer.parseInt(mathsArray[1]);
                if (num1 < 11 && num2 < 11)
                    return "" + (num1 - num2);
                else
                    throw new Exception();
            }
        }
        else if (inputHasRoman && !inputHasNumbers){
            if (input.contains(" * ") && input.split(" \\* ").length == 2) {
                String[] romanArray = input.split(" \\* ");
                int[] romanInt = doLoopForRoman(romanArray);
                int result = romanInt[0] * romanInt [1];
                if (result > 0)
                    return arabicToRoman(result);
                else throw new Exception("в римской системе нет отрицательных чисел и 0");
            }
            else if (input.contains(" + ") && input.split(" \\+ ").length == 2) {
                String[] romanArray = input.split(" \\+ ");
                int[] romanInt = doLoopForRoman(romanArray);
                int result = romanInt[0] + romanInt [1];
                if (result > 0)
                    return arabicToRoman(result);
                else throw new Exception("в римской системе нет отрицательных чисел и 0");
            }
            else if (input.contains(" / ") && input.split(" \\/ ").length == 2) {
                String[] romanArray = input.split(" \\/ ");
                int[] romanInt = doLoopForRoman(romanArray);
                int result = (romanInt[0] / romanInt [1]);
                if (result > 0)
                    return arabicToRoman(result);
                else throw new Exception("в римской системе нет отрицательных чисел и 0");
            }
            else if (input.contains(" - ") && input.split(" \\- ").length == 2) {
                String[] romanArray = input.split(" \\- ");
                int[] romanInt = doLoopForRoman(romanArray);
                int result = (romanInt[0] - romanInt [1]);
                if (result > 0)
                    return arabicToRoman(result);
                else throw new Exception("в римской системе нет отрицательных чисел и 0");
            }
        }
        else if (inputHasNumbers && inputHasRoman){
            throw new Exception("используются одновременно разные системы счисления");
        }
        else if (hasOperator) {
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        throw new Exception("строка не является математической операцией");
    }
    static int[] doLoopForRoman(String[] romans) throws Exception {
        int[] numbers = new int[romans.length];
        for (int i = 0; i < numbers.length; i++) {
            if (romans[i].equals("I")) {
                numbers[i] = 1;
            }
            else if (romans[i].equals("II")){
                numbers[i] = 2;
            }
            else if (romans[i].equals("III")){
                numbers[i] = 3;
            }
            else if (romans[i].equals("IV")){
                numbers[i] = 4;
            }
            else if (romans[i].equals("V")){
                numbers[i] = 5;
            }
            else if (romans[i].equals("VI")){
                numbers[i] = 6;
            }
            else if (romans[i].equals("VII")){
                numbers[i] = 7;
            }
            else if (romans[i].equals("VIII")){
                numbers[i] = 8;
            }
            else if (romans[i].equals("IX")){
                numbers[i] = 9;
            }
            else if (romans[i].equals("X")){
                numbers[i] = 10;
            }
        }
        if (numbers != null && numbers[0] != 0 && numbers[1] != 0)
            return numbers;
        else
            throw new Exception();
    }
    enum RomanNumeral {
        I(1), IV(4), V(5), IX(9), X(10),
        XL(40), L(50), XC(90), C(100),
        CD(400), D(500), CM(900), M(1000);

        private int value;

        RomanNumeral(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static List<RomanNumeral> getReverseSortedValues() {
            return Arrays.stream(values())
                    .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                    .collect(Collectors.toList());
        }
    }
    static String arabicToRoman(int number) throws Exception {
        if ((number <= 0) || (number > 4000)) {
            throw new Exception(number + "не в значениях 0-4000");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }
}