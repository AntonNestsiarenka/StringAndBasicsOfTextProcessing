package com.company;

import java.util.Comparator;

public class LengthComparator implements Comparator<String> {

    // Класс LengthComparator реализует интерфейс Comparator<String>. Необходим для сравнения объектов String.

    public int compare(String first, String second)
    {
        // Сравнение двух объектов String по длинне символов.
        return first.length() - second.length();
    }
}