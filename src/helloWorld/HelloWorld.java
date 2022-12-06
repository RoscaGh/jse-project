package helloWorld;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HelloWorld {
    public HelloWorld() {
    }

    public static void main() throws IOException {
        System.out.print("Enter your name ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();
        System.out.println("Hello "+name+"!");
        //String name = reader.readLine();
        //System.out.println("Hello "+name+"!");
        System.out.print("Enter your name ");
    }
}
