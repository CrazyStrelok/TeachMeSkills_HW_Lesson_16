package com.teachmeskills.lesson_4.com.teachmeskills.lesson_16;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) throws FileNotFoundException {
        IParser parser = new Parser();
        Scanner s = new Scanner(System.in);

        System.out.println("Введите расположение файлов:");
        String path = s.nextLine();
        System.out.println("Введите число документов для обработки:");

        int N = s.nextInt();

        parser.parse("C:\\Games\\testDocs", 10);
    }
}


