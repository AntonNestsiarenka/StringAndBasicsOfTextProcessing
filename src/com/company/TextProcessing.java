package com.company;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextProcessing {

    /* Обработчик текста.
       После каждой операции по обработке текста методы возвращают новый экземпляр TextProcessing. */

    private String text = "";

    public TextProcessing(String text)
    {
        this.text = text;
    }

    public String getText()
    {
        // Возвращает строку с текстом.
        return text;
    }

    public static String fileProcessing(String fileName) throws IOException {
        /* Считывает текст из файла, после каждой считанной строки вставляется \n. Если считанная строка пустая, то
           добавляет специальный символ разделения абзацев \u2029. Возвращает строку с текстом. */
        FileReader reader = null;
        try {
            reader = new FileReader(fileName);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String text = "";
        Scanner scan = new Scanner(reader);
        while (scan.hasNextLine())
        {
            String currentLine = scan.nextLine();
            if (currentLine.length() == 0)
            {
                text = text.concat("\u2029");
            }
            else
            {
                text = text.concat(currentLine + "\n");
            }
        }
        scan.close();
        reader.close();
        return text;
    }

    public TextProcessing sortParagraphsAscending()
    {
        /* Сортирует абзацы по возрастанию количества предложений в нем. Возвращает новый объект TextProcessing с
           отсортированным текстом. */
        String[] paragraphs = splitByParagraph();
        int countsOfSentence[] = new int[paragraphs.length];
        for (int i = 0; i < paragraphs.length; i++)
        {
            Pattern pattern = Pattern.compile("[^.!?][.!?]");
            Matcher matcher = pattern.matcher(paragraphs[i]);
            int countOfSentence = 0;
            while (matcher.find())
            {
                countOfSentence++;
            }
            countsOfSentence[i] = countOfSentence;
        }
        for (int i = 0; i < countsOfSentence.length; i++)
        {
            for (int j = 0; j < countsOfSentence.length - 1 - i; j++)
            {
                if (countsOfSentence[j] > countsOfSentence[j + 1])
                {
                    int temp1 = countsOfSentence[j];
                    countsOfSentence[j] = countsOfSentence[j + 1];
                    countsOfSentence[j + 1] = temp1;
                    String temp2 = paragraphs[j];
                    paragraphs[j] = paragraphs[j + 1];
                    paragraphs[j + 1] = temp2;
                }
            }
        }
        return new TextProcessing(String.join("\u2029", paragraphs));
    }

    public TextProcessing sortWordsByLengthAscendingInSentences()
    {
        /* Сортирует слова в предложениях по возрастанию длинны слова. Возвращает новый объект TextProcessing с
           отсортированными словами в предложениях. */
        ArrayList<String> sortedSentences = new ArrayList<>();
        Pattern pattern = Pattern.compile("[^.!?]+?[.!?]+");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find())
        {
            String sentence = matcher.group();
            ArrayList<String> allPunctuationInSentence = getAllPunctuations(sentence);
            ArrayList<String> allWordsInSentence = getAllWords(sentence);
            allWordsInSentence.sort(new LengthComparator());
            for (int i = 1 + allWordsInSentence.size() - allPunctuationInSentence.size(), j = 0; j < allPunctuationInSentence.size(); j++, i++)
                allWordsInSentence.add(i + j, allPunctuationInSentence.get(j));
            sortedSentences.add(String.join("", allWordsInSentence));
        }
        return new TextProcessing(String.join("", sortedSentences));
    }

    public TextProcessing sortWordsByNumberOfOccurrencesDescendingInSentences(char symbol)
    {
        /* Сортирует слова в предложении по убыванию количества вхождений заданного символа. Если количество вхождений
           у слов одинаковое, то сортирует слова по алфавиту. */
        ArrayList<String> sortedSentences = new ArrayList<>();
        Pattern patternForSearchSentences = Pattern.compile("[^.!?]+?[.!?]+");
        String pattern = String.valueOf(symbol) + "+";
        Pattern patternForSearchSymbolInWord = Pattern.compile(pattern);
        Matcher matcherForSearchSentences = patternForSearchSentences.matcher(text);
        while (matcherForSearchSentences.find()) {
            String sentence = matcherForSearchSentences.group();
            ArrayList<String> allPunctuationInSentence = getAllPunctuations(sentence);
            ArrayList<String> allWordsInSentence = getAllWords(sentence);
            ArrayList<Integer> numbersOfOcurrences = new ArrayList<>();
            for (String word : allWordsInSentence)
            {
                Matcher matcherForSearchSymbol = patternForSearchSymbolInWord.matcher(word);
                int count = 0;
                while (matcherForSearchSymbol.find())
                {
                    count++;
                }
                numbersOfOcurrences.add(count);
            }
            for (int k = 0; k < numbersOfOcurrences.size(); k++)
            {
                for (int j = 0; j < numbersOfOcurrences.size() - 1 - k; j++)
                {
                    if (numbersOfOcurrences.get(j) < numbersOfOcurrences.get(j + 1))
                    {
                        int temp1 = numbersOfOcurrences.get(j);
                        numbersOfOcurrences.set(j, numbersOfOcurrences.get(j + 1));
                        numbersOfOcurrences.set(j + 1, temp1);
                        String temp2 = allWordsInSentence.get(j);
                        allWordsInSentence.set(j, allWordsInSentence.get(j + 1));
                        allWordsInSentence.set(j + 1, temp2);
                    }
                    else if (numbersOfOcurrences.get(j) == numbersOfOcurrences.get(j + 1))
                    {
                        if (allWordsInSentence.get(j).toLowerCase().compareTo(allWordsInSentence.get(j + 1).toLowerCase()) > 0)
                        {
                            String temp = allWordsInSentence.get(j);
                            allWordsInSentence.set(j, allWordsInSentence.get(j + 1));
                            allWordsInSentence.set(j + 1, temp);
                        }
                    }
                }
            }
            for (int i = 1 + allWordsInSentence.size() - allPunctuationInSentence.size(), j = 0; j < allPunctuationInSentence.size(); j++, i++)
                allWordsInSentence.add(i + j, allPunctuationInSentence.get(j));
            sortedSentences.add(String.join("", allWordsInSentence));
        }
        return new TextProcessing(String.join("", sortedSentences));
    }

    public TextProcessing deleteDuplicatesOfTabs()
    {
        // Удаляет все дубликаты пробельных символов. Возвращает новый объект TextProcessing с текстом.
        String text = this.text;
        int size = -1;
        while (text.length() != size) {
            size = text.length();
            text = text.replace("  ", " ");
        }
        return new TextProcessing(text);
    }

    private String[] splitByParagraph()
    {
        // Разбивает текст на абзацы. Возвращает массив строк - абзацев.
        Pattern pattern = Pattern.compile("\\u2029");
        String[] paragraphs = pattern.split(text, 0);
        return paragraphs;
    }

    private ArrayList<String> getAllWords(String text)
    {
        // Возвращает список всех слов в строке.
        ArrayList<String> words = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find())
        {
            words.add(matcher.group());
        }
        return words;
    }

    private ArrayList<String> getAllPunctuations(String text)
    {
        // Возвращает список всех знаков пунктуации в строке.
        ArrayList<String> punctuation = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\W+");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find())
        {
            punctuation.add(matcher.group());
        }
        return punctuation;
    }
}