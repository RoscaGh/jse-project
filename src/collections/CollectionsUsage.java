package collections;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CollectionsUsage {
    public static void main(String[] args) throws IOException {
        String outFile ="src/collections/employee-final.txt";
        String inFile="src/collections/employee-input.txt";
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
            myWriter.write(s.concat(" "+dateB.format(myFormatObj)+" | "+getAge(tt,dateB)+'\n'));
        }
        myWriter.close();

        String inFileP = new File(".").getCanonicalPath()+"\\src\\collections\\products-input.txt";
        HashMap<String, String> prod=read_product(inFileP);
//        -----------ADD new product
        prod.put("1243","Mouse A4tech G34");
        prod.put("1244","Masa calculator MX espress");
        prod.put("1245","Smartphone Xiaomi RedMi 9 note");
//        prod.put("1245","Monitor IPS LG XT345");
        prod.replace("1245","Monitor IPS LG XT345");
        for (String s : prod.keySet()) {
            System.out.println(s+" "+prod.get(s));
        }
        String outFileP = new File(".").getCanonicalPath()+"\\src\\collections\\products-output.txt";
        CreateFile(outFileP);
        myWriter = new FileWriter(outFileP);
        for (String s : prod.keySet()) {
            myWriter.write(s+'|'+prod.get(s)+'\n');
        }
        myWriter.close();

    }

    private static HashMap<String, String> read_product(String path) throws FileNotFoundException {
        File myInput = new File(path);
        Scanner myReader = new Scanner(myInput);
        HashMap<String, String> prod=new HashMap<>();
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] keys=data.split("[|]");
            prod.put(keys[0],keys[1]);
        }
        return prod;
    }

    private static int getAge(LocalDate endDate, LocalDate startDate){
        return Period.between(startDate,endDate).getYears();
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
