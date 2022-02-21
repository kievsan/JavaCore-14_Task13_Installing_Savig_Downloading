//package knhel7.jd14.javacore14.task131;

// ������ 3: ��������
//  1. ���������� ���������� ������ � ����� savegames.
//  2. ���������� ���������� � �������������� ������ �� ����������������� ������ save.dat.
//  3. ������� � ������� ��������� ����������� ����.
// https://github.com/netology-code/jd-homeworks/blob/master/files/task3/README.md

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Task3_Downloading {

    public static void task3_Downloading() throws IOException {
        Logger logger = Logger.INSTANCE;
        logger.log("Do Task-3: Downloading!");

        List<File> listObjectStatusFile = openZip(Main.SUPER_PATCH + Main.ZIP_PATCH + Main.ZIP_FILE);
        logger.log("The game objects from files is being restored...'");
        for (File file : listObjectStatusFile) {
            GameProgress objectGameProgress = openProgress(file);
            System.out.println("Restored game object from the file '" + file + "'  -->  " + objectGameProgress);
            logger.log("   '" + file + "'  -->  " + objectGameProgress);
        }
        Main.deleteFiles(listObjectStatusFile);

        logger.log("Have been done Task-3!");
    }

    public static List<File> openZip(String zipFileName) throws IOException {
        Logger logger = Logger.INSTANCE;
        logger.log("The file '" + zipFileName + "' is being unzipped...'");
        String destinationDirectory = new File(zipFileName).getParent() + Main.SEP;
        List<File> listObjectStatusFile = new ArrayList<>();

        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFileName))) {
            ZipEntry entry;
            String fileName;
            File realFile;
            while ((entry = zin.getNextEntry()) != null) {
                fileName = entry.getName();     // ������� �������� �����
                realFile = new File(destinationDirectory + fileName);
                        // ����������:
                FileOutputStream fout = new FileOutputStream(realFile);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
                logger.log("\t" + fileName);
                listObjectStatusFile.add(realFile);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            logger.log("Exception error in ZipInputStream, metod 'unZip' class 'Task3_Saving'!");
        }
        return listObjectStatusFile;
    }

    public static GameProgress openProgress(File objectStatusFile) throws IOException { // restoring Object from file
        Logger logger = Logger.INSTANCE;

        GameProgress gameProgress = null;
        try (FileInputStream  fis = new FileInputStream(objectStatusFile);    // ������� ������� ����� ��� ������ �����
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();             // ������������� ������ � ������� ��� � �����
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            logger.log("Exception error in ObjectInputStream, metod 'openProgress' class 'Task3_Saving'!");
        }
        return gameProgress;
    }

}