package string;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StringUsage {
    public static void main(String[] args) throws IOException {
        int i=1;
        try {
            String outFile = new File(".").getCanonicalPath()+"\\src\\string\\resources\\output.txt";
            String inFile=new File(".").getCanonicalPath()+"\\src\\string\\resources\\input.txt";
            CreateFile(outFile);
            File myObj = new File(inFile);
            FileWriter myWriter = new FileWriter(outFile);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                //sarcini
                myWriter.write("Fraza("+(i++)+")"+'\n');
                myWriter.write(data.toUpperCase()+'\n');
                myWriter.write(data.toLowerCase()+'\n');
                myWriter.write("Lungimea fraze: "+data.length()+'\n');
                myWriter.write("Numarul de vocale: "+nr_vocale(data)+'\n');
                myWriter.write("Numarul de consoane: "+nr_consoane(data)+'\n');
                myWriter.write("Numarul de cuvinte: "+nr_cuvinte(data)+'\n');
                String[] cuv=cuvinte_max(data);
                myWriter.write("Lungimea cuvintului max - "+cuv[0].length()+": ");
                int j=0;
                while(cuv[j]!=null){
                    myWriter.write(cuv[j++]);
                    if(cuv[j]!=null) myWriter.write(", ");
                }
                myWriter.write('\n');
                cuv=cuvinte_min(data);
                myWriter.write("Lungimea cuvintului min - "+cuv[0].length()+": ");
                j=0;
                while(cuv[j]!=null){
                    myWriter.write(cuv[j++]);
                    if(cuv[j]!=null) myWriter.write(", ");
                }
                myWriter.write('\n');
                cuv=cuvinte_duble(data);
                myWriter.write("Cuvintele duble sunt: ");
                j=0;
                while(cuv[j]!=null){
                    myWriter.write(cuv[j++]);
                    if(cuv[j]!=null) myWriter.write(", ");
                }
                myWriter.write('\n');

            }
            myReader.close();
            myWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
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
    private static int nr_vocale(String data){
        String vocale="aeiouăîâ";
        int n=0;
        data=data.toLowerCase();
        for(int i = 0; i < data.length(); i++)
        {
            if(vocale.indexOf(data.charAt(i))>=0) n++;
        }
        return n;
    }
    private static int nr_consoane(String data){
        String consoane="bcdfhjklmnpqrsștțvwxzy";
        int n=0;
        data=data.toLowerCase();
        for(int i = 0; i < data.length(); i++)
        {
            if(consoane.indexOf(data.charAt(i))>=0) n++;
        }
        return n;
    }
    private static int nr_cuvinte(String data){
        String[] result = data.split(" ");
        return result.length;
    }
    private static String[] cuvinte_max(String data){
        String specialChars2 = "[,.?!]";
        data=data.replaceAll(specialChars2,"");
        String[] result = data.split(" ");
        String[] res=new String[10];
        int max=0;
        for (String str : result) {
            if(str.length()>max) max=str.length();
        }
        int j=0;
        for (String str : result) {
            if(str.length()==max)
                res[j++]=str;
        }
        return res;
    }

    private static String[] cuvinte_min(String data){
        String specialChars2 = "[,.?!]";
        data=data.replaceAll(specialChars2,"");
        String[] result = data.split(" ");
        String[] res=new String[10];
        int min=Integer.MAX_VALUE;
        for (String str : result) {
            if(str.length()<min) min=str.length();
        }
        int j=0;
        for (String str : result) {
            if(str.length()==min)
                res[j++]=str;
        }
        return res;
    }
    private static String[] cuvinte_duble(String data){
        String specialChars2 = "[,.?!]";
        data=data.replaceAll(specialChars2,"");
        data=data.toLowerCase();
        String[] result = data.split(" ");
        String[] res=new String[10];
        String str;
        int i=0,j,k=0;
        boolean sch;
        for (int y=0;y<result.length;y++) {
            j=i+1;
            str=result[y];
            sch=false;
            while(j<result.length) {
                if (str.equals(result[j])&&!str.equals("&@s")) {
                    if(!sch) res[k++] = str;
                    sch=true;
                    result[j] = "&@s";
                }
                j++;
            }
            i++;
        }
        return res;
    }
} //END
