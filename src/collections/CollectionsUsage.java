package collections;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class CollectionsUsage {
    public static void main(String[] args) throws IOException {
//        String outFile = new File(".").getCanonicalPath()+"\\src\\collections\\employee-input.txt";
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
}
