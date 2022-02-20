package knhel7.jd14.javacore14.task131;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public enum Logger {
    INSTANCE;

    private static BufferedWriter logStream;

    static {
        try {
            logStream = new BufferedWriter(new FileWriter("game.log", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int num;

    public void log(String msg) throws IOException {
        logStream.write(
                "[" +
                        String.format("%1$td.%1$tm.%1$tY %tT", new Date()) +
                        ", " + ++num +
                        "] " +
                        msg + "\n"
        );
    }

    public void close() throws IOException {
        logStream.close();
    }
}
