import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class reqgrid_parser {
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
        exportFile.println("?,,Deadlines,,,,,,App Fees,,Common App,,Supplements?,,Minimum Standard Test Policy?,,,Recommendations?,,,,\nCommon App Member,School Type,ED,EDII,EA,EAII,REA,RD/ Rolling,US,INTL,Personal Essay Req'd,C&G,Portfolio?,Writing,Test Policy,SAT/ACT Tests Used,INTL,TE,OE,MR,CR,Saves Forms");
        while (fileInput.hasNextLine()) {
            // System.out.println(fileInput.nextLine());
            String curLine = fileInput.nextLine();
            // while (curLine.contains(",,")) {
            //     curLine = curLine.replace(",,",",");
            // }
            if (!curLine.contains("Deadlines") && !curLine.contains("Common App Member")) {
                exportFile.println(curLine);
            }
        }
        exportFile.close();
        if (file_name.contains("_")) {
            System.out.println(file_name.substring(0,file_name.indexOf("_")) + " is done exporting.");
            return;
        }
        System.out.println(file_name + " is done exporting.");
    }
}