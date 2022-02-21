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
import java.util.Locale;
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
    static final String ZIP_FILE_FULLNAME = SUPER_PATCH + ZIP_PATCH + ZIP_FILE_NAME;
    static final String TEMP_FILE_FULLNAME = SUPER_PATCH + TEMP_PATCH + TEMP_FILE_NAME;

    static StringBuilder SIMPLE_LOGGER = new StringBuilder();

    public static void main(String[] args) throws IOException {
        Locale.setDefault(new Locale("ru", "RU"));
        SIMPLE_LOGGER.append("Launch the program! *****************************************\n");

        System.out.println("\nTask 1. Starting! Press Enter...");
        SCAN.nextLine();
        Task1_Installing.task1_Installing();    // Задача 1. Установка

            // log-файл создан, теперь пишем в temp.txt:
        Logger logger = Logger.INSTANCE;
                // сбросим имеющиеся логи:
        logger.log(SIMPLE_LOGGER.toString());
        logger.log("Только что были добавлены стартовые логи!");

                // или так:
//        try (FileWriter writer = new FileWriter(TEMP_FILE_FULLNAME, true)) {
//            // запись всей строки
//            writer.write(SIMPLE_LOGGER.toString());
//            // запись по символам
//            writer.append('\n');
//            writer.append('!');
//            // дозаписываем и очищаем буфер
//            writer.flush();
//            logger.log("Только что были добавлены стартовые логи!\n");
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//            logger.log("ОШИБКА при записи стартовых логов!");
//        }

        System.out.println("\nTask 2. Starting! Press Enter...");
        SCAN.nextLine();
        Task2_Saving.task2_Saving();            // Задача 2. Сохранение

        System.out.println("\nTask 3. Starting! Press Enter...");
        SCAN.nextLine();
        Task3_Downloading.task3_Downloading();  // Задача 3. Загрузка

        logger.log("Completing the program!\n");
        logger.close();
        SCAN.close();
    }

    public static void deleteFiles(List<File> filesToDelete) throws IOException {
        Logger logger = Logger.INSTANCE;
        if (!filesToDelete.isEmpty()) {
            logger.log("The game files are deleting...");
            for (File file : filesToDelete) {
                if (file.exists()) {
                    if (file.delete())
                        logger.log("\t'" + file + "' has been deleted.");
                    else
                        logger.log("\t'" + file + "' hasn't been deleted.");
                } else
                    logger.log("\t'" + file +  "' not found!");
            }
        }
    }

}
