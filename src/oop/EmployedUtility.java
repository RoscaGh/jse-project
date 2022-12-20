package oop;

import java.io.*;
import java.util.*;

public class EmployedUtility {
    public static void main(String[] args) throws IOException {
        String path = "src/oop/employee.txt";
        ArrayList<Employed> employeds = readFile(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
            menu();
            String selector = reader.readLine();
                switch (selector) {
                    case "1" -> {
                        System.out.println("-------------Initial list of employee --------------");
                        employeds.forEach(System.out::println);
                    }
                    case "2" -> {
                        ArrayList<Employed> sortedEmployeds = new ArrayList<>(employeds);
                        sortedEmployeds.sort(Comparator.comparing(Employed::getName));
                        System.out.println("----------Sorted by name employee ---------------------");
                        sortedEmployeds.forEach(System.out::println);
                    }
                    case "3" -> {
                        double amountSal = getTotalSalary(employeds);
                        System.out.println("-----------Salary ----------------");
                        System.out.println("Total month salary for company = " + amountSal);
                        for (Employed employed : employeds) {
                            System.out.print(employed.getName() + "|");
                            System.out.printf("%2.2f%s", (employed.getSalary() * 100) / amountSal, "%");
                            System.out.println();
                        }
                    }
                    case "4" -> modifySalary(employeds, path);
                    case "5" -> {
                        System.out.println("Enter lastFirstName");
                        String lFN = reader.readLine();
                        ArrayList<Employed> finderLFN = getEmployedDetail(employeds, lFN);
                        if (finderLFN.size() > 0) {
                            System.out.println("Result of request is");
                            finderLFN.forEach(System.out::println);
                        } else System.out.println("Emplyeds with Last First Name" + lFN + " not found");
                    }
                }
            System.out.println("Press Enter key");
            reader.read();
        }
    }

    private static ArrayList<Employed> getEmployedDetail(ArrayList<Employed> employeds, String lFN) {
        ArrayList<Employed> results=new ArrayList<>();
        for (Employed employed : employeds) {
            if(employed.getName().toLowerCase().contains(lFN.toLowerCase())) results.add(employed);
        }
        return results;
    }

    private static void menu() {
        System.out.println("1.....List of employees data");
        System.out.println("2.....Order by FirstLastName");
        System.out.println("3.....Total sum of month money");
        System.out.println("4.....Change employee’s salary by its PersonalCode");
        System.out.println("5.....See all Employee details by employees FirstLastName");
    }

    private static void modifySalary(ArrayList<Employed> employeds, String outFile) throws IOException {
        int pos = -1;
        int i = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter employed PersonalCode");
        String s=reader.readLine();
        for (Employed employed : employeds) {
            if (employed.getPersonal_code().equals(s)) {
                pos = i;
                break;
            }
            i++;
        }
        if(pos>=0) {
            System.out.println(employeds.get(pos).toLine());
            System.out.println("Enter new salary ");
            s=reader.readLine();
            employeds.get(pos).setSalary(Double.parseDouble(s));
            FileWriter myWriter = new FileWriter(outFile);
            for (Employed employed : employeds) {
                myWriter.write(employed.toLine() + '\n');
            }
            System.out.println("Check output file for changes");
            myWriter.close();
        }
        else{
            System.out.println("Employed with PersonalCode "+s+ " not found");
        }
    }

    private static ArrayList<Employed> readFile(String filePath) {
        ArrayList<Employed> empl = new ArrayList<>();
        File file = new File(filePath);
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        Scanner scanner = new Scanner(fileInputStream);
        scanner.nextLine(); //skip header
        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split("[|]");

            switch (line[2]) {
                case "1" -> {
                    Director director = new Director(line[0], line[1], line[3], Double.parseDouble(line[4]));
                    empl.add(director);
                }
                case "2" -> {
                    Manager manager = new Manager(line[0], line[1], Integer.parseInt(line[3]), Double.parseDouble(line[4]));
                    empl.add(manager);
                }
                case "3" -> {
                    Programmer programmer = new Programmer(line[0], line[1], line[3], Double.parseDouble(line[4]));
                    empl.add(programmer);
                }
            }
        }

        return empl;
    }

    public static double getTotalSalary(ArrayList<Employed> employeds) {
        double total = 0;
        for (Employed employed : employeds) {
            total += employed.getSalary();
        }
        return total;
    }
}
