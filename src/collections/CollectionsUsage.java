package collections;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CollectionsUsage {
    public static void main(String[] args) throws IOException {
        String outFile = new File(".").getCanonicalPath()+"\\src\\collections\\employee-final.txt";
        String inFile=new File(".").getCanonicalPath()+"\\src\\collections\\employee-input.txt";
        ArrayList<String> empl=read_employee(inFile);
        System.out.println("--------Lista initiala");
        empl.forEach(System.out::println);
        System.out.println("-----------Lista sortata");
        empl.sort(Comparator.naturalOrder());
        empl.forEach(System.out::println);
        Collection<String> name_empl = read_only_name1(empl);
        System.out.println("---------------Only distinct name ArrayList.contains()");
        name_empl.forEach(System.out::println);
        name_empl = new HashSet<>(read_only_name2(empl));
        System.out.println("---------------Only distinct name HashSet");
        name_empl.forEach(System.out::println);
        CreateFile(outFile);
        FileWriter myWriter = new FileWriter(outFile);
        LocalDate tt=LocalDateTime.now().toLocalDate();
        for (String s : empl) {
            LocalDate dateB=generate_db();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            myWriter.write(s.concat(" "+dateB.format(myFormatObj)+" | "+difference(tt,dateB)+'\n'));
        }
        myWriter.close();
    }
    private static int difference(LocalDate a, LocalDate b){
        return LocalDate.ofEpochDay(a.toEpochDay()-b.toEpochDay()).getYear()-1970;
    }
    private static LocalDate generate_db() {
        return LocalDate.ofYearDay(1970+(int)(Math.random()*30),(int)(Math.random()*365));
    }

    private static ArrayList<String> read_employee(String path) throws FileNotFoundException {
        File myInput = new File(path);
        Scanner myReader = new Scanner(myInput);
        ArrayList<String> empl=new ArrayList<>();
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            empl.add(data);
        }
        return empl;
    }
    private static Collection<String> read_only_name1(ArrayList<String> empl){
        ArrayList<String> nameEm=new ArrayList<>();
        String name;
        for (String s : empl) {
            name=s.split(" ")[0];
            if (!nameEm.contains(name)){
                nameEm.add(name);
            }
        }
        return nameEm;
    }
    private static Collection<String> read_only_name2(ArrayList<String> empl){
        Collection<String> nameEm=new HashSet<>();
        String name;
        for (String s : empl) {
            name=s.split(" ")[0];
                nameEm.add(name);
        }
        return nameEm;
    }
    private static void CreateFile(String path) {
        try {
            File myObj = new File(path);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
