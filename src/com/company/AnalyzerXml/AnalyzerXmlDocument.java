package com.company.AnalyzerXml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalyzerXmlDocument {

    /* Анализатор узлов xml-документа. Для создания экземпляра использовать static метод fileProcessing, который
       считает xml - код из файла, обработает его и вернет созданный экземпляр. */

    private String codeXml = "";

    private AnalyzerXmlDocument(String text)
    {
        codeXml = text;
    }

    public String getCodeXml()
    {
        // Возвращает xml код.
        return codeXml;
    }

    public static AnalyzerXmlDocument fileProcessing(String fileName) throws IOException {
        /* Считывает код xml из файла, после каждой считанной строки вставляется \n. Возвращает экземпляр
           AnalyzerXmlDocument. */
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
            text = text.concat(currentLine.trim() + "\n");
        }
        scan.close();
        reader.close();
        return new AnalyzerXmlDocument(text);
    }

    public void analyze()
    {
        /* Ищет в строке codeXml группы совпадений: одиночный открывающийся тег, одиночный закрывающийся тег, тег без
           тела, открывающийся тег + содержимое тега + закрывающийся тег. Выводит в консоль последовательно тип тега,
           сам тег, содержимое тега (если имеется). */
        Pattern pattern = Pattern.compile("((?<openingTag><[^/][^>]*[^/]>)(?<tagContent>.*)(?<closingTag></[^>]*[^/]>))" +
                            "|(?<onlyOpeningTag><[^/][^>]*[^/]>)|(?<onlyClosingTag></[^>]*[^/]>)|(?<noBodyTag><[^>]*/>)");
        Matcher matcher = pattern.matcher(codeXml);
        while (matcher.find()) {
            if (matcher.group("openingTag") != null && matcher.group("closingTag") != null) {
                System.out.println("Opening tag is: " + matcher.group("openingTag") + ". Tag content is: "
                        + matcher.group("tagContent") + ". Closing tag is: " + matcher.group("closingTag"));
            }else if (matcher.group("onlyOpeningTag") != null) {
                System.out.println("Opening tag is: " + matcher.group("onlyOpeningTag"));
            } else if (matcher.group("onlyClosingTag") != null) {
                System.out.println("Closing tag is: " + matcher.group("onlyClosingTag"));
            } else if (matcher.group("noBodyTag") != null) {
                System.out.println("No body tag is: " + matcher.group("noBodyTag"));
            }
        }
    }
}