import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static void main(String[] args) {

        openZip("g:/Мой диск/Games/savegames/zipGame.zip");
        openProgress("g:/Мой диск/Games/savegames/save1.dat");
    }

    private static void openProgress(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) { // откроем входной поток для чтения файла
            System.out.println(ois.readObject()); // десериализуем объект и печатает
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void openZip(String zipName) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipName))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName(); // получим название файла
                FileOutputStream fout = new FileOutputStream(name); // распаковка
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}