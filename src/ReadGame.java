import java.io.*;

public class ReadGame {
    // this method created to test reading files data
    public GameProgress deserialization(String way) throws InvalidObjectException {
        File des1 = new File(way);
        ObjectInputStream ois = null;
        try {
            FileInputStream fis = new FileInputStream(des1);
            ois = new ObjectInputStream(fis);
            return (GameProgress) ois.readObject();
        } catch (ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                assert ois != null;
                ois.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        throw new InvalidObjectException("Object fail");
    }
}
