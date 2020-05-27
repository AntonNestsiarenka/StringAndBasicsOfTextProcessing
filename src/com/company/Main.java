package com.company;

import Utils.Pair;
import java.io.IOException;

import static Utils.CharArrayUtils.*;
import static Utils.OtherUtils.*;

public class Main {

    /**********************************************************************************************************/
    /******************************* Работа со строкой как с массивом символов ********************************/
    /**********************************************************************************************************/

    public static void task1()
    {
        // Дан массив названий переменных в camelCase. Преобразовать названия в snake_case.
        String array[] = new String[]{"CamelCase", "DevourRi", "NamelessKing", "ND", "", "R", "string1ToChar1Array", };
        System.out.println("Имена переменных в регистре CamelCase.");
        printStringArray(array);
        System.out.println("Имена переменных в регистре SnakeCase.");
        for (String str : array)
        {
            printCharArray(camelCaseToSnakeCase(stringToCharArray(str)));
        }
    }

    public static void task2()
    {
        // Замените в строке все вхождения 'word' на 'letter'.
        String str = "Yraut Nbfd ds cx WorD kg wordword word letter nvbword !";
        System.out.println("Заданная строка.");
        System.out.println(str + "\n");
        char[] newStr = replaceWords(stringToCharArray(str), "word", "letter");
        System.out.println("Строка после внесения всех замен.");
        printCharArray(newStr);

    }

    public static void task3()
    {
        // В строке найти количество цифр.
        String str = "Intel Core i9 9900k";
        System.out.println("Заданная строка.");
        System.out.println(str + "\n");
        int countOfDigitsInStr = countOfDigitsIn1DArray(stringToCharArray(str));
        System.out.printf("Количество цифр в символьном массиве: %d\n", countOfDigitsInStr);
    }

    public static void task4()
    {
        // В строке найти количество чисел.
        String str = " +375-17-389-0100";
        System.out.println("Заданная строка.");
        System.out.println(str + "\n");
        int countOfNumbersInStr = countOfNumbersIn1DArray(stringToCharArray(str));
        System.out.printf("Количество чисел в символьном массиве: %d\n", countOfNumbersInStr);
    }

    public static void task5()
    {
        /* Удалить в строке все лишние пробелы, то есть серии подряд идущих пробелов заменить на одиночные пробелы.
           Крайние пробелы в строке удалить. */
        String str = "   Hello   Word !         ";
        System.out.println("Заданная строка.");
        System.out.println(str + "\n");
        char[] newStr = strip(stringToCharArray(str));
        System.out.println("Строка после удаления дубликатов на конце и замены всех остальных дубликатов одним уникальным.");
        printCharArray(newStr);
        newStr = replacingDuplicateCharactersWithAUnique(newStr, " ");
        printCharArray(newStr);
    }

    /**********************************************************************************************************/
    /********************* Работа со строкой как с объектом типа String или StringBuilder *********************/
    /**********************************************************************************************************/

    public static void task6()
    {
        // Дан текст (строка). Найдите наибольшее количество подряд идущих пробелов в нем.
        String line = "     Hello   Word !       ";
        System.out.println("Заданная строка: " + line);
        int countOfTabs = maxCountDuplicates(line, ' ');
        System.out.printf("Максимальное количество подряд идущих пробелов: %d\n", countOfTabs);
    }

    public static void task7()
    {
        // В строке вставить после каждого символа 'a' символ 'b'.
        String line = "The accurate and stable measurement of process pressure.";
        System.out.println("Заданная строка: " + line);
        String newLine = insertSymbolToString(line, 'a', 'b');
        System.out.println("Строка после вставки после буквы a буквы b.");
        System.out.println(newLine);
    }

    public static void task8()
    {
        // Проверить, является ли заданное слово палиндромом.
        String line = "Boob";
        System.out.println("Заданная строка: " + line);
        if (isPalindrom(line))
        {
            System.out.println("Строка является палиндромом");
        }
        else
        {
            System.out.println("Строка не является палиндромом");
        }
    }

    public static void task9()
    {
        // С помощью функции копирования и операции конкатенации составить из частей слова “информатика” слово “торт”.
        String word = "информатика";
        String newStr = word.charAt(7) + word.substring(3, 5) + word.charAt(7);
        System.out.println(newStr);
    }

    public static void task10()
    {
        // Подсчитать, сколько раз среди символов заданной строки встречается буква “а”.
        String line = "Cradle of Filth";
        System.out.println("Заданная строка: " + line);
        char symbol = 'l';
        int count = countSymbol(line, symbol);
        System.out.printf("Количество вхождений символа %c в строке: %d\n", symbol, count);
    }

    public static void task11()
    {
        // Из заданной строки получить новую, повторив каждый символ дважды.
        String line = "Combinations";
        System.out.println("Заданная строка: " + line);
        String newLine = doubleEachSymbol(line);
        System.out.println("Задвоеная строка: " + newLine);
    }

    public static void task12()
    {
        /* Вводится строка. Требуется удалить из нее повторяющиеся символы и все пробелы. Например, если было введено
           "abc cde def", то должно быть выведено "abcdef". */
        String line = "abc cde def";
        System.out.println("Заданная строка: " + line);
        String newLine = removeReplaysOfSymbolsAndAllTabs(line);
        System.out.println("Строка после удаления символов-повторо и пробелов: " + newLine);
    }

    public static void task13()
    {
        /* Вводится строка слов, разделенных пробелами. Найти самое длинное слово и вывести его на экран. Случай, когда
           самых длинных слов может быть несколько, не обрабатывать. */
        String words = "Introduction to Java Online";
        System.out.println("Заданная строка: " + words);
        String maxLengthWord = wordWithMaxLength(words);
        System.out.println("Максимально длинное слово: " + maxLengthWord);
    }

    public static void task14()
    {
        /* Посчитать количество строчных (маленьких) и прописных (больших) букв в введенной строке. Учитывать только
           английские буквы. */
        String line = "StringsAndBasicsOfTextProcessing";
        System.out.println("Заданная строка: " + line);
        Pair counts = countLowerCaseAndUpperCaseLetters(line);
        System.out.printf("Количество прописных букв в строке: %d\nКоличество строчных букв в строке: %d\n",
                            counts.getFirst(), counts.getSecond());
    }

    public static void task15()
    {
        /* Строка X состоит из нескольких предложений, каждое из которых кончается точкой, восклицательным или
           вопросительным знаком. Определить количество предложений в строке X. */
        String line = "Are you interested in Alice in Wonderland? Then you are at the right place!\n" +
                "\n" +
                "Whether you are a student, a scholar, looking to meet fellow Alice in Wonderland fans, or throwing a" +
                " theme party, here you can find everything you always wanted to know about Lewis Carroll’s books" +
                " “Alice’s Adventures in Wonderland” and “Through the Looking Glass and what Alice found there”, as" +
                " well as Disney’s Alice in Wonderland cartoon movie from 1951.\n" +
                "Enjoy your stay in Wonderland!";
        System.out.println("Заданная строка: " + line);
        int countOfSentences = countOfSentencesInLine(line);
        System.out.printf("Количество предложений: %d\n", countOfSentences);
    }

    /**********************************************************************************************************/
    /*************************** Работа с регулярными выражениями (Pattern, Matcher) **************************/
    /**********************************************************************************************************/

    public static void task16()
    {
        /* Cоздать приложение, разбирающее текст (текст хранится в строке) и позволяющее выполнять с текстом три
           различных операции: отсортировать абзацы по количеству предложений; в каждом предложении отсортировать
           слова по длине; отсортировать лексемы в предложении по убыванию количества вхождений заданного символа,
           а в случае равенства – по алфавиту. */
        String text = "";
        try {
            text = TextProcessing.fileProcessing("Text.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        TextProcessing textObj = new TextProcessing(text);
        textObj = textObj.deleteDuplicatesOfTabs();
        System.out.println("Текст считанный из файла.\n");
        System.out.println(textObj.getText());
        textObj = textObj.sortParagraphsAscending();
        System.out.println("Текст после сортировки абзацев по количеству предложений.\n");
        System.out.println(textObj.getText());
        textObj = textObj.sortWordsByLengthAscendingInSentences();
        System.out.println("Текст после сортировки слов по длине в каждом предложении.\n");
        System.out.println(textObj.getText());
        System.out.println();
        textObj = textObj.sortWordsByNumberOfOccurrencesDescendingInSentences('a');
        System.out.println("Текст после сортировки слов в предложении по убыванию количества вхождений заданного" +
                            " символа, а в случае равенства – по алфавиту.\n");
        System.out.println(textObj.getText());
    }

    public static void task17()
    {
        /* Дана строка, содержащая текст (xml-документ). Напишите анализатор, позволяющий последовательно возвращать
           содержимое узлов xml-документа и его тип (открывающий тег, закрывающий тег, содержимое тега, тег без тела).
           Пользоваться готовыми парсерами XML для решения данной задачи нельзя. */
        AnalyzerXmlDocument xmlCodeObj = null;
        try {
            xmlCodeObj = AnalyzerXmlDocument.fileProcessing("code.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        xmlCodeObj.analyze();
    }

    public static void main(String[] args) {

        task17();
    }
}