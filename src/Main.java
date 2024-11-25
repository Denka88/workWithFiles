import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.print("Введите имя желаемого файла: ");

        String fileName = scan.nextLine();
        
        byte menu;
        do{
            System.out.printf("""
                    ===Главное меню===(Текущий файл: %s)
                    1.Сменить файл
                    2.Создать файл
                    3.Создать задачу
                    4.Посмотреть список задач
                    5.Удалить задачу
                    6.Завершить работу программы
                    """, fileName);
            if(fileName == ""){
                System.out.println("!!!Имя файла не введено!!!");
            }
            menu = scan.nextByte();
            switch(menu){
                case 1:
                    System.out.print("Введите имя желаемого файла: ");
                    fileName = scan.nextLine();
                    break;
                case 2:
                    createFile(fileName);
                    break;
                case 3:
                    writeFile(fileName);
                    break;
                case 4:
                    readFile(fileName);
                    break;
                case 5:
                    deleteTaskFromFile(fileName);
                    break;
                case 6:
                    System.out.print("Досвидания");
                    break;
                default:
                    System.out.println("Такого пункта в меню нет");
                    break;
            }
            
        }while (menu != 6);

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
        try (FileWriter writer = new FileWriter(fileName, true)) {
            Scanner scan = new Scanner(System.in);
            Task task = new Task();

            SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
            Date date = new Date();
            System.out.print("Введите имя задачи: ");
            task.setName(scan.nextLine());
            writer.write(task.getName() + " | ");

            System.out.print("Введите описание задачи: ");
            task.setDescription(scan.nextLine());
            writer.write(task.getDescription() + " | ");
            
            int year = date.getYear();
            int month = Calendar.getInstance().get(Calendar.MONTH);
            int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            task.setCreateDate(year, month, day);
            
            writer.write(formater.format(task.getCreateDate())+ " | ");
            System.out.print("Введите количество дней на выполнение задачи: ");
            String deadline = scan.nextLine();
            task.setDeadline(Integer.parseInt(deadline));
            System.out.println(deadline);
            writer.write(deadline);
            writer.append("\n");

            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

//    Чтение данных из файла:

    public static ArrayList<Task> readFile(String fileName) {
        ArrayList<Task> tasksList = new ArrayList<>();
        SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
        
        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader fileScanner = new BufferedReader(reader);
            while (fileScanner.ready()) {
                String line = fileScanner.readLine();
                String[] dataArray = line.split(" \\| ");
                String name = dataArray[0];
                String description = dataArray[1];
                Date createDate = formater.parse(dataArray[2]);
                int deadline = Integer.parseInt(dataArray[3]);
                Task task = new Task(name, description, createDate, deadline);
                tasksList.add(task);
            }
            fileScanner.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        System.out.print("\n");
        int i = 0;
        for (Task task : tasksList) {
            System.out.println(i++ + ". " + task.getName() + " " + task.getDescription() + " " + formater.format(task.getCreateDate()) + " " + task.getDeadline() + " дней до завершения");
        }
        System.out.print("\n");
        return tasksList;
    }
    
//    Удаление данных из файла
    public static void deleteTaskFromFile(String fileName) {
        
        if(fileName==null){
            System.out.println("Вы не указали имя файла");
        }
        
        Scanner scan = new Scanner(System.in);

        SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
        
        ArrayList<Task> tasks = readFile(fileName);
        
        System.out.print("Введите номер для удаления: ");
        int numForDelete = scan.nextInt();
        
        Task deleteTarget = null;
        for (Task task : tasks) {
            if (tasks.indexOf(task) == numForDelete) {
                deleteTarget = task;
                break;
            }
        }
        if (deleteTarget != null) {
            tasks.remove(deleteTarget);
        }
        
//        Тут запись
        
        try{
            FileWriter writer = new FileWriter(fileName);
            for (Task task : tasks) {
                writer.write(task.getName() + " | ");
                writer.write(task.getDescription() + " | ");
                writer.write(formater.format(task.getCreateDate()) + " | ");
                writer.write(String.valueOf(task.getDeadline()));
                writer.append("\n");
                writer.flush();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}