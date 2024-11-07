import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.print("Введите имя желаемого файла: ");

        String fileName = scan.nextLine();
        createFile(fileName);

    }

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

}