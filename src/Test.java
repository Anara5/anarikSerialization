import java.io.*;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Test {
    public static void main(String[] args) {

        GameProgress game1 = new GameProgress(20, 3, 5, 10.0);
        GameProgress game2 = new GameProgress(8, 8, 3, 15.0);
        GameProgress game3 = new GameProgress(2, 10, 9, 2.0);

        saveGame("game1.dat", game1);
        saveGame("game2.dat", game2);
        saveGame("game3.dat", game3);

        showGame("game1.dat");
        showGame("game2.dat");
        showGame("game3.dat");

        // putting a list of files for zipping
        String [] files = {"/Users/anarazhunusova/Desktop/Games/savegames/game1.dat",
                "/Users/anarazhunusova/Desktop/Games/savegames/game2.dat",
                "/Users/anarazhunusova/Desktop/Games/savegames/game3.dat"};
        // zipping the list of files
        zipFiles("zip.zip", files);

        // deleting files
        deleteFile("game1.dat");
        deleteFile("game2.dat");
        deleteFile("game3.dat");
    }

    // creating a file save.dat in folder "savegames" and saving our games there
    public static void saveGame(String file, GameProgress game) {
        try (FileOutputStream fos = new FileOutputStream("/Users/anarazhunusova/Desktop/Games/savegames/" + file);
        ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // just testing a method to show data from game files
    public static void showGame(String file) {
        try (FileInputStream fis = new FileInputStream("/Users/anarazhunusova/Desktop/Games/savegames/" + file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
             ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // zipping files
    public static void zipFiles(String zip, String[] files) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("/Users/anarazhunusova/Desktop/Games/savegames/" + zip));
        FileInputStream fis = new FileInputStream("/Users/anarazhunusova/Desktop/Games/savegames/" + Arrays.toString(files))) {
            ZipEntry entry = new ZipEntry("/Users/anarazhunusova/Desktop/Games/savegames/" + Arrays.toString(files));
            zout.putNextEntry(entry);
            byte [] buffer = new byte[fis.available()]; fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // deleting files for example outside of zip.zip
    public static void deleteFile(String file) {
        File dir = new File("/Users/anarazhunusova/Desktop/Games/savegames/" + file);
        if (dir.delete()) {
            System.out.println(file + " is deleted");
        }
    }
}