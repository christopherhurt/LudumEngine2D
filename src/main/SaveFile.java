package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SaveFile {
    
    private File file;
    
    public SaveFile(String relativePath) {
        file = new File(System.getProperty("user.dir") + "/" + relativePath);
    }
    
    public List<String> readLines() {
        List<String> lines = new ArrayList<>();
        Scanner sc = null;
        
        try{
            sc = new Scanner(file);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        
        while(sc.hasNext()) {
            String line = sc.nextLine();
            lines.add(line);
        }
        
        sc.close();
        return lines;
    }
    
    public void writeLines(List<String> lines) {
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            
            for(String line : lines) {
                bw.write(line + "\n");
            }
            
            bw.flush();
            bw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
