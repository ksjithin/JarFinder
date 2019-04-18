/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jarfinder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author k.s
 */
public class JarFinder {

    PrintWriter writer;

    public void findFile(String ext, File file) {
        try {
            writer = new PrintWriter(new FileOutputStream("result.txt", true));
            File[] list = file.listFiles();
            if (list != null) {
                for (File fil : list) {
                    if (fil.isDirectory()) {
                        findFile(ext, fil);
                    } else if (ext.equalsIgnoreCase(FilenameUtils.getExtension(fil.getAbsolutePath()))) {
                        System.out.println(fil.getAbsolutePath());
                        writer.println("<classpathentry kind=\"lib\" path=\""+fil.getAbsolutePath()+"\"/>");
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JarFinder.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            writer.flush();
            writer.close();
        }
    }

    public static void main(String[] args) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("result.txt");
            writer.print("");
            writer.close();
            JarFinder ff = new JarFinder();
            Scanner scan = new Scanner(System.in);
            String ext = "jar";
            System.out.println("Enter the directory where to search ");
            String directory = scan.next();
            ff.findFile(ext, new File(directory));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JarFinder.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            writer.close();
        }
    }

}
