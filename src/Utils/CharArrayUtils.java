package Utils;
import static Utils.OtherUtils.*;

public class CharArrayUtils {

    /* Различные методы для работы с массивами char-ов. */

    public static char[] resize1DArray(char[] array, int n)
    {
        /* Изменение размера одномерного массива char - ов на +-n от текущего размера с последующим копированием
           в новый массив. */
        char[] newArray = new char[array.length + n];
        int range = array.length;
        if (n < 0)
            range = array.length + n;
        for (int i = 0; i < range; i++)
        {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static char[] camelCaseToSnakeCase(char[] array)
    {
        /* Переводит массив char - ов из регистра CamelCase в регистр SnakeCase. Возвращает новый массив char - ов в
           регистре SnakeCase. */
        char[] newArray = new char[array.length];
        int i = 0;
        int j = 0;
        if (array.length != 0)
        {
            if (isCapitalLetter(array[i]))
            {
                newArray[i++] = (char)(array[j++] - 'A' + 'a');
            }
        }
        for (; i < array.length; i++)
        {
            if (isCapitalLetter(array[i]))
            {
                newArray = resize1DArray(newArray, 1);
                newArray[j++] = '_';
                newArray[j++] = (char)(array[i] - 'A' + 'a');
            }
            else {
                newArray[j++] = array[i];
            }
        }
        return newArray;
    }

    public static boolean equal(char[] str1, char[] str2)
    {
        // Сравнивает два символьных массива и возвращает результат сравнения.
        for (int i = 0; i < str1.length; i++) {
            if (str1[i] != str2[i]) {
                return false;
            }
        }
        return true;
    }

    public static char[] replaceWords(char[] array, String wordToReplace, String replacement)
    {
        /* Ищет в массиве char - ов все слова wordToReplace и заменяет их на слово replacement. Возвращает новый
           символьный массив после внесения всех замен. */
        if (wordToReplace.length() == 0)
        {
            return array;
        }
        char[] wordForSearch = stringToCharArray(wordToReplace);
        char[] rep = stringToCharArray(replacement);
        char[] newArray = new char[array.length];
        for (int i = 0, j = 0; i < array.length; i++)
        {
            if (array[i] == wordForSearch[0])
            {
                char[] tempWord = new char[wordForSearch.length];
                for (int k = 0; k < wordForSearch.length && i + k < array.length; k++) {
                    tempWord[k] = array[i + k];
                }
                if (equal(tempWord, wordForSearch))
                {
                    newArray = resize1DArray(newArray, rep.length - wordForSearch.length);
                    for (char symbol : rep)
                    {
                        newArray[j++] = symbol;
                    }
                    i += wordForSearch.length - 1;
                }
                else
                {
                    newArray[j++] = array[i];
                }
            }
            else {
                newArray[j++] = array[i];
            }
        }
        return newArray;
    }

    public static int countOfDigitsIn1DArray(char[] str)
    {
        // Возвращает количество цифр в символьном массиве.
        int count = 0;
        for (char symbol : str)
        {
            if (isDigit(symbol))
                count++;
        }
        return count;
    }

    public static int countOfNumbersIn1DArray(char[] str)
    {
        // Возвращает количество чисел в символьном массиве.
        int count = 0;
        int i = 0;
        while (i < str.length)
        {
            if (isDigit(str[i]))
            {
                count++;
                int offset = 0;
                for (int j = i; j < str.length && isDigit(str[j]); j++)
                {
                    offset++;
                }
                i += offset;
            }
            else
            {
                i++;
            }
        }
        return count;
    }

    public static char[] strip(char[] str)
    {
        // Удаляет из символьного массива все пробелы с конца. Возвращает новый символьный массив без пробелов на конце.
        int countOfTab = 0;
        int i = str.length - 1;
        while (str[i--] == ' ' && i >= 0)
        {
            countOfTab++;
        }
        return resize1DArray(str, -countOfTab);
    }

    public static char[] replacingDuplicateCharactersWithAUnique(char[] str, String duplicate)
    {
        /* Заменяет все подряд идущие дубликаты (duplicate) одним уникальным duplicate. Возвращает новый символьный
           массив после внесения всех замен. */
        char[] newStr = replaceWords(str, new String(duplicate + duplicate), duplicate);
        int currentSizeNewStr = newStr.length;
        int size = - 1;
        while (currentSizeNewStr != size)
        {
            size = newStr.length;
            newStr = replaceWords(newStr, new String(duplicate + duplicate), duplicate);
            currentSizeNewStr = newStr.length;
        }
        return newStr;
    }

    public static void printCharArray(char[] array)
    {
        // Вывод в консоль массива символов.
        for (char str : array)
        {
            System.out.print(str);
        }
        System.out.println();
    }
}