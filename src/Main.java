import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File("test.txt");

        System.out.println("Имя файла: " + file.getName());
        System.out.println("Родительская папка: " + file.getParent());

        try {
            boolean created = file.createNewFile();
            if (created) {
                System.out.println("Файл создан");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        try(FileWriter writer = new FileWriter( file, true))
        {
            Scanner scan = new Scanner(System.in);
            // запись всей строки
            System.out.println("Введите текст: ");
            String text = scan.nextLine();
            writer.write(text);
            // запись по символам
            writer.append('\n' + "");

            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

        try(FileReader reader = new FileReader("test.txt"))
        {
            char[] buf = new char[256];
            int c;
            while((c = reader.read(buf))>0){
                if(c < 256){
                    buf = Arrays.copyOf(buf, c);
                }
                System.out.print(buf);
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

}