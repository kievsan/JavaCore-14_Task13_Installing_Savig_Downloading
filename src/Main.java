//package knhel7.jd14.javacore14.task131;

// Task131
// Домашнее задание к занятию 1.3.
// Потоки ввода-вывода. Работа с файлами. Сериализация
// Задача 1. Установка
// Задача 2. Сохранение
// Задача 3. Загрузка
// https://github.com/netology-code/jd-homeworks/blob/master/files/README.md

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.Scanner;

public class Main {

    static final Scanner SCAN = new Scanner(System.in, "cp1251");

    static final String SEP = File.separator;
    static final String SUPER_PATCH = "E:" + SEP + SEP + "Games" + SEP;
    static final String TEMP_PATCH = "src" + SEP + "test" + SEP;
    static final String TEMP_FILE = "temp.txt";
    static final String SAVE_PATCH = "savegames" + SEP;
    static final String SAVE_FILE = "save.dat";
    static final String ZIP_PATCH = SAVE_PATCH;
    static final String ZIP_FILE = "save_level.zip";

    public static void main(String[] args) throws IOException {
        Logger logger = Logger.INSTANCE;
        logger.log("Launch the program!");

        System.out.println("\nTask 1. Starting! Press any key...");
        SCAN.nextLine();
        Task1_Installing.task1_Installing();    // Задача 1. Установка

        System.out.println("\nTask 2. Starting! Press any key...");
        SCAN.nextLine();
        Task2_Saving.task2_Saving();            // Задача 2. Сохранение

        System.out.println("\nTask 3. Starting! Press any key...");
        SCAN.nextLine();
        Task3_Downloading.task3_Downloading();  // Задача 3. Загрузка



        logger.log("Completing the program!");
        logger.close();
        SCAN.close();
    }

}
