package knhel7.jd14.javacore14.task131;

// ������ 3: ��������
//  1. ���������� ���������� ������ � ����� savegames.
//  2. ���������� ���������� � �������������� ������ �� ����������������� ������ save.dat.
//  3. ������� � ������� ��������� ����������� ����.
// https://github.com/netology-code/jd-homeworks/blob/master/files/task3/README.md

import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.file.Path;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Task3_Downloading {

    public static void task3_Downloading() throws IOException {
        Logger logger = Logger.INSTANCE;
        logger.log("Do Task-3: Downloading!");

        unZip(Main.SUPER_PATCH + Main.ZIP_PATCH + Main.ZIP_FILE);

        logger.log("Have been done Task-3!");
    }

    public static void unZip(String zipFileName) throws IOException {
        Logger logger = Logger.INSTANCE;
        logger.log("The file '" + zipFileName + "' is being unzipped...'");
        String destinationDirectory = new File(zipFileName).getParent() + Main.SEP;

        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFileName)))
        {
            ZipEntry entry;
            String fileName;
            while ((entry = zin.getNextEntry()) != null) {
                fileName = entry.getName(); // ������� �������� �����
                // ����������:
                FileOutputStream fout = new FileOutputStream(destinationDirectory + fileName);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
                logger.log(fileName);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            logger.log("Exception error in ZipInputStream, " +
                    "metod 'unZip' class 'Task3_Saving'!");
        }

    }

}
