//package knhel7.jd14.javacore14.task131;

// Задача 2: Сохранение
//  1. Создать три экземпляра класса GameProgress.
//  2. Сохранить сериализованные объекты GameProgress в папку savegames из предыдущей задачи.
//  3. Созданные файлы сохранений из папки savegames запаковать в архив zip.
//  4. Удалить файлы сохранений, лежащие вне архива.
// https://github.com/netology-code/jd-homeworks/blob/master/files/task2/README.md

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Task2_Saving {

    public static void task2_Saving() throws IOException {
        Logger logger = Logger.INSTANCE;
        logger.log("Do Task-2: Saving!");

//        1. Создать три экземпляра класса GameProgress:
        List<GameProgress> gameInstanceList = List.of(
                new GameProgress(90, 10, 2, 49.6),
                new GameProgress(55, 22, 4, 85.8),
                new GameProgress(31, 17, 3, 65.5));

//        2. Сохранить сериализованные объекты GameProgress в папку 'savegames':
        saveGame(gameInstanceList, Main.SAVE_PATCH, Main.SAVE_FILE);

//        3. Созданные файлы сохранений из папки savegames запаковать в архив zip:
        String[] splitFilePattern = Main.SAVE_FILE.split("[\\s,.]+");
        List<String> filesToPack = new ArrayList<>();
        List<File> filesToDelete = new ArrayList<>();
        File dir = new File(Main.SUPER_PATCH + Main.SAVE_PATCH); // определяем объект для каталога
        if (dir.exists() && dir.isDirectory()) {                        // если объект представляет каталог
            logger.log("Browse the '" + dir + "' directory and select files to add to the archive.");
            for (File file : dir.listFiles()) {             // получаем все вложенные объекты в каталоге
                if (matchFile(file, splitFilePattern)) {    // проверяем: это файл и он подходит по шаблону?
                    filesToPack.add(file.toString());
                    filesToDelete.add(file);
                    logger.log("\tFile " + file + " will be added to the archive and then deleted.");
                }
            }
        }
        if (!filesToPack.isEmpty())
            zipFiles(filesToPack, Main.SUPER_PATCH + Main.SAVE_PATCH);

//        4. Удалить файлы сохранений, лежащие вне архива:
        Main.deleteFiles(filesToDelete);

        logger.log("Have been done Task-2!");
    }

    public static void saveGame(List<GameProgress> gameInstanceList,
                                String dirName, String fileName) throws IOException {
        File dir = new File(Main.SUPER_PATCH + dirName);
        Logger logger = Logger.INSTANCE;
        logger.log("Game files are saving to directory '" + dir + "...");

        if (!dir.exists() || !dir.isDirectory()) {
            logger.log("Directory '" + dir +  "' not found... " +
                    "Game files haven't been saved!");
            return;
        }

        String[] splitFileName = fileName.split("[\\s,.]+");
        String fileNameWithoutExtension = splitFileName[0];
        String fileExtension = splitFileName[1];

        for (int i = 1; i <= gameInstanceList.size(); i++) {
            String realFileName = Main.SUPER_PATCH + dirName +
                    fileNameWithoutExtension + i +
                    "." + fileExtension;
            try (FileOutputStream fos = new FileOutputStream(realFileName);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                Object obj = gameInstanceList.get(i-1);
                oos.writeObject(obj);
                logger.log("\tGame object has just been saved to '" +
                        realFileName + "'.");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                logger.log("Exception error in ObjectOutputStream(" + realFileName +
                        "), metod 'saveGame' class 'Task2_Saving'!");
            }
        }
    }

    public static boolean matchFile(File file, String[] splitFilePattern) {
        String[] splitFileName = file.getName().split("[\\s,.]+");
        return  (file.isFile() &&                                   // проверим, является ли объект файлом
                splitFileName[0].contains(splitFilePattern[0]) &&   // и имя подходит по шаблону
                splitFileName[1].equals(splitFilePattern[1]));      // и расширения совпадают
    }

    public static void zipFiles(List<String> filesToPack,
                                String zipAbsPatch) throws IOException {
        Logger logger = Logger.INSTANCE;
        String zipFullName = zipAbsPatch + Main.ZIP_FILE;
        logger.log("The game files are archiving to file '" + zipFullName +  "'...");

        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipFullName)))
        {
            for (String fileName: filesToPack) {
                File file = new File(fileName);
                if (!file.exists() || !file.isFile()) {
                    logger.log("File '" + fileName +  "' not found... " +
                            "It hasn't been archived.");
                    continue;
                }
                try (FileInputStream fis = new FileInputStream(fileName))
                {
                    String shotFileName = fileName.substring(fileName.lastIndexOf(Main.SEP) + 1);
                    ZipEntry entry = new ZipEntry(shotFileName);
                    zout.putNextEntry(entry);
                    // считываем содержимое файла в массив byte:
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer); // добавляем содержимое к архиву
                    logger.log("\tThe game file '" + fileName + "' has been archived.");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    logger.log("Exception error in FileInputStream, " +
                            "metod 'zipFiles' class 'Task2_Saving'!");
                }
            }
            zout.closeEntry();  // закрываем текущую запись для новой записи
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            logger.log("Exception error in ZipOutputStream(" + zipFullName +
                    "), metod 'zipFiles' class 'Task2_Saving'!");
        }
    }

}
