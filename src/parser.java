import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class parser {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Name of the file you want to parse:");

        String file_name = userInput.nextLine(); 
        
        if (file_name.equalsIgnoreCase("all")) {
            Scanner fileNameInput = new Scanner(new File("./data/list-all.txt"));
            String cur_file_name = fileNameInput.nextLine();
            exportFile(cur_file_name);
            while (fileNameInput.hasNextLine()) {
                cur_file_name = fileNameInput.nextLine();
                exportFile(cur_file_name);
            }
            return;
        }
        exportFile(file_name);
    }

    private static void exportFile(String file_name) throws FileNotFoundException {
        Scanner fileInput = new Scanner(new File("./data/csv/"+file_name+".csv"),"UTF-8");
        PrintWriter exportFile = new PrintWriter("./export/"+file_name+"_trimmed.csv");
        while (fileInput.hasNextLine()) {
            // System.out.println(fileInput.nextLine());
            String curLine = fileInput.nextLine();
            while (curLine.contains(",,")) {
                curLine = curLine.replace(",,",",");
            }
            exportFile.println(curLine);
        }
        exportFile.close();
        System.out.println(file_name.substring(0,file_name.indexOf("_")) + " is done exporting.");
    }
}