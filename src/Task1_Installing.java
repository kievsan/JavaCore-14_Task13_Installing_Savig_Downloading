//package knhel7.jd14.javacore14.task131;

// Задача 1: Установка
//  1. В папке Games создайте несколько директорий: src, res, savegames, temp.
//  2. В каталоге src создайте две директории: main, test.
//  3. В подкаталоге main создайте два файла: Main.java, Utils.java.
//  4. В каталог res создайте три директории: drawables, vectors, icons.
//  5. В директории temp создайте файл temp.txt.
//  Файл temp.txt будет использован для записи в него информации
//  об успешном или неуспешном создании файлов и директорий.
// https://github.com/netology-code/jd-homeworks/blob/master/files/task1/README.md

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Task1_Installing {

    public static void task1_Installing() {
        Main.SIMPLE_LOGGER.append("Do Task-1: Installing!\n");

        for (Map.Entry<String, List<String>> entry : getTaskForDirectories().entrySet()) {
            for (String newDir : entry.getValue()) {
                createFileEntity(entry.getKey(), newDir + Main.SEP, true);
            }
        }
        getTaskForFiles().forEach((superDirectory, listNewFiles) -> {
            for (String newFile: listNewFiles) {
                createFileEntity(superDirectory, newFile, false);
            }
        });
        Main.SIMPLE_LOGGER.append("Have been done Task-1!\n");
    }

    protected static boolean createFileEntity(String directory,
                                              String fileEntityName,
                                              boolean isDirectory) {
        File newFileEntity = new File(directory + fileEntityName);
        boolean isCreated = false;
        try {
            isCreated = isDirectory ? newFileEntity.mkdir() : newFileEntity.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (isCreated) {
            Main.SIMPLE_LOGGER
                    .append("\tThe '")
                    .append(newFileEntity)
                    .append(isDirectory ? Main.SEP + "' directory " : "' file ")
                    .append("has just been created.\n");
            return true;
        }
        Main.SIMPLE_LOGGER
                .append("\tFailed to create the '")
                .append(newFileEntity)
                .append(isDirectory ? Main.SEP + "' directory!." : "' file!")
                .append("\n");
        return false;
    }

    protected static LinkedHashMap<String, List<String>> getTaskForDirectories() {
        LinkedHashMap<String, List<String>> mapOfTasks = new LinkedHashMap<>();
        mapOfTasks.put(Main.SUPER_PATCH, List.of("src", "res", "savegames", "temp"));
        mapOfTasks.put(Main.SUPER_PATCH + "src" + Main.SEP, List.of("main", "test"));
        mapOfTasks.put(Main.SUPER_PATCH + "res" + Main.SEP, List.of("drawables", "vectors", "icons"));
        return mapOfTasks;
    }

    protected static LinkedHashMap<String, List<String>> getTaskForFiles() {
        LinkedHashMap<String, List<String>> mapOfTasks = new LinkedHashMap<>();
        mapOfTasks.put(Main.SUPER_PATCH + "src" + Main.SEP + "main" + Main.SEP,
                List.of("Main.java", "Utils.java"));
        mapOfTasks.put(Main.SUPER_PATCH + Main.TEMP_PATCH,
                List.of(Main.TEMP_FILE_NAME));
        return mapOfTasks;
    }

}
