import java.io.File;

public class Main {
    public static void main(String[] args) {

        File dir = new File("G:/JavaLearn/workWithFiles/NewDir");

        boolean created = dir.mkdir();
        if (created) {
            System.out.println("Folder has been created");
        }
        // Переименуем каталог
        File newDir = new File("G:/JavaLearn/workWithFiles/NewDir2");
        dir.renameTo(newDir);
        // Удалим каталог
        boolean deleted = newDir.delete();
        if (deleted) {
            System.out.println("Folder has been deleted");
        }
        
    }
}