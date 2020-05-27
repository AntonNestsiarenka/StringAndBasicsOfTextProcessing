package Utils;

public class OtherUtils {

    public static boolean isCapitalLetter(char letter)
    {
        // Является ли символ большой буквой.
        return letter >= 'A' && letter <= 'Z';
    }

    public static boolean isLowerCaseLetter(char letter)
    {
        // Является ли символ строчной буквой.
        return letter >= 'a' && letter <= 'z';
    }

    public static boolean isDigit(char symbol)
    {
        // Является ли символ цифрой.
        return symbol >= '0' && symbol <= '9';
    }

    public static void printStringArray(String[] array)
    {
        // Вывод в консоль массива символов.
        for (String str : array)
        {
            System.out.println(str);
        }
        System.out.println();
    }

    public static char[] stringToCharArray(String str)
    {
        // Возвращает массив char-ов из объекта класса String.
        char[] array = new char[str.length()];
        for (int i = 0; i < str.length(); i++)
        {
            array[i] = str.charAt(i);
        }
        return array;
    }

    public static String charArrayToString(char[] str)
    {
        // Возвращает объект типа String из массива char - ов.
        String newStr = new String(str);
        return newStr;
    }

    public static int maxCountDuplicates(String line, char symbol)
    {
        // Возвращает максимальное количество подряд идущих символов symbol в строке.
        int maxCountDuplicates = 0;
        int count = 0;
        for (int i = 0; i < line.length(); i++)
        {
            if (line.charAt(i) == symbol)
            {
                count++;
            }
            else
            {
                if (count > maxCountDuplicates)
                {
                    maxCountDuplicates = count;
                }
                count = 0;
            }
        }
        if (count > maxCountDuplicates) {
            maxCountDuplicates = count;
        }
        return maxCountDuplicates;
    }

    public static String insertSymbolToString(String line, char symbolAfterWhichToInsert, char symbolForInsert)
    {
        /* Ищет в строке все символы symbolAfterWhichToInsert(символ после которого вставлять) и вставляет после них
           symbolForInsert - символ для вставки. Возвращает новую строку после всех изменений. */
        StringBuilder newStr = new StringBuilder();
        for (int i = 0; i < line.length(); i++)
        {
            if (line.charAt(i) == symbolAfterWhichToInsert)
            {
                newStr.append(line.charAt(i));
                newStr.append(symbolForInsert);
            }
            else
            {
                newStr.append(line.charAt(i));
            }
        }
        return newStr.toString();
    }

    public static boolean isPalindrom(String str)
    {
        // Является ли строка палиндромом.
        str = str.toLowerCase();
        for (int i = 0, j = str.length() - 1; i < str.length() / 2; i++, j--) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    public static int countSymbol(String str, char symbol)
    {
        // Возвращает количество вхождений символа symbol в строку.
        int count = 0;
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) == symbol)
            {
                count++;
            }
        }
        return count;
    }

    public static String doubleEachSymbol(String str)
    {
        // Добавляет к каждому символу строки две его копии.
        String newStr = "";
        for (int i = 0; i < str.length(); i++)
        {
            StringBuilder temp = new StringBuilder();
            for (int j = 0; j < 2; j++)
            {
                temp.append(str.charAt(i));
            }
            newStr = newStr.concat(temp.toString());
        }
        return newStr;
    }

    public static boolean isSymbolInString(String str, char symbol)
    {
        // Определяет наличие символа symbol в строке.
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) == symbol)
            {
                return true;
            }
        }
        return false;
    }

    public static String removeReplaysOfSymbolsAndAllTabs(String str)
    {
        // Удаляет из строки все повторяющиеся символы и все пробелы. Возвращает новую строку.
        StringBuilder newStr = new StringBuilder();
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) != ' ')
            {
                if (!isSymbolInString(newStr.toString(), str.charAt(i)))
                {
                    newStr.append(str.charAt(i));
                }
            }
        }
        return newStr.toString();
    }

    public static String wordWithMaxLength(String str)
    {
        /* Возвращает максимально длинное слово в строке. Если таких слов несколько, возвращает первое,
           которое нашлось. */
        str = str.trim();
        int size = -1;
        while (str.length() != size) {
            size = str.length();
            str = str.replace("  ", " ");
        }
        String[] allWords = str.split(" ");
        String maxLengthWord = "";
        int maxLength = -1;
        for (String word : allWords)
        {
            if (word.length() > maxLength)
            {
                maxLengthWord = word;
                maxLength = word.length();
            }
        }
        return maxLengthWord;
    }

    public static Pair countLowerCaseAndUpperCaseLetters(String str)
    {
        /* Считает в строке все заглавные и прописные буквы. Возвращает объект - Pair, который хранит количество
           заглавных и прописных букв. */
        Pair counts = new Pair<Integer, Integer>(0, 0);
        for (int i = 0; i < str.length(); i++)
        {
            if (isCapitalLetter(str.charAt(i)))
            {
                int value = (int)counts.getFirst() + 1;
                counts.setFirst(value);
            }
            else if (isLowerCaseLetter(str.charAt(i)))
            {
                int value = (int)counts.getSecond() + 1;
                counts.setSecond(value);
            }
        }
        return counts;
    }

    public static int countOfSentencesInLine(String str)
    {
        // Возвращает количество предложений в строке.
        int count = 0;
        for (int i = 1; i < str.length(); i++)
        {
            if (str.charAt(i) == '.' || str.charAt(i) == '!' || str.charAt(i) == '?')
            {
                if (str.charAt(i - 1) != '.' && str.charAt(i - 1) != '!' && str.charAt(i - 1) != '?')
                {
                    count++;
                }
            }
        }
        return count;
    }
}