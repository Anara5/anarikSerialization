import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) throws IOException {

        GameProgress game1 = new GameProgress(5, 1, 8, 2.0);
        GameProgress game2 = new GameProgress(9, 8, 3, 19.0);
        GameProgress game3 = new GameProgress(30, 1, 1, 8.0);

        saveGame("/Users/anarazhunusova/Desktop/Games/savegames/game1.dat", game1);
        saveGame("/Users/anarazhunusova/Desktop/Games/savegames/game2.dat", game2);
        saveGame("/Users/anarazhunusova/Desktop/Games/savegames/game3.dat", game3);

        // zipping the list of files
        zipFiles(new File("/Users/anarazhunusova/Desktop/Games/savegames"));

        // testing the method deserialize file to see information about objects
        /*ReadGame readGame = new ReadGame();
        try {
            GameProgress games1 = readGame.deserialization("/Users/anarazhunusova/Desktop/Games/savegames/game1.dat");
            System.out.println("Read games data: \n" + games1);
        } catch (InvalidObjectException e) {
            e.printStackTrace();
        }*/

        // deleting files outside of "zip.zip" folder
        deleteFile("/Users/anarazhunusova/Desktop/Games/savegames/game1.dat");
        deleteFile("/Users/anarazhunusova/Desktop/Games/savegames/game2.dat");
        deleteFile("/Users/anarazhunusova/Desktop/Games/savegames/game3.dat");
    }

    // creating a file in folder "savegames" for each game
    public static void saveGame(String way, GameProgress game) {
        try (FileOutputStream fos = new FileOutputStream(way);
        ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // zipping files
    public static void zipFiles(File path) throws IOException {
        File[] files = path.listFiles();
        assert files != null;
        if (files.length == 0)
            throw new IllegalArgumentException("No files in path " + path.getAbsolutePath());
        FileOutputStream fos = new FileOutputStream("/Users/anarazhunusova/Desktop/Games/savegames/zip.zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);

        for (File zipThis : files) {
            FileInputStream fis = new FileInputStream(zipThis);
            ZipEntry entry = new ZipEntry(zipThis.getName());
            zipOut.putNextEntry(entry);
            byte[] buffer = new byte[fis.available()];
            int length;
            while ((length = fis.read(buffer)) >= 0) {
                zipOut.write(buffer, 0, length);
            }
            fis.close();
        }
        zipOut.close();
        fos.close();
    }

    // deleting files outside of "zip.zip"
    public static void deleteFile(String way) {
        File file = new File(way);
        if (file.exists()) {
            System.out.println(file.delete() + " File is deleted");
        } else {
            System.out.println("File doesn't exist");
        }
    }
}