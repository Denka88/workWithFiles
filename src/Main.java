import java.io.FileWriter;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.print("Введите имя желаемого файла: ");

        String fileName = scan.nextLine();
        createFile(fileName);
        writeFile(fileName);

    }
// Создание файла:

    public static void createFile(String fileName) {

        File newFile = new File(fileName);

        if(newFile.exists()){
            System.out.println("Файл с таким именем существует");
        }else{
            try {
                newFile.createNewFile();
                System.out.println("Файл успешно создан");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
// Запись данных в файл:

    public static void writeFile(String fileName) {
        try(FileWriter writer = new FileWriter(fileName, true))
        {
            // запись всей строки
            Scanner scan = new Scanner(System.in);
            String text = scan.nextLine();
            writer.write(text);

            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}