import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Lines{
    static public void deleteLines(int n, int m,String path)throws IOException{
        File file = new File(path);
        File nfile = new File("src/new.txt");
        FileWriter newFile = new FileWriter(nfile);
        List<String> lines = Files.readAllLines(Paths.get(path));

        for (int i = 0; i < lines.size();i++){
            if ( i < n-1 || i > m-1)
                newFile.write(lines.get(i)+"\n");
            else
                System.out.println(lines.get(i));
        }
        newFile.close();
        file.delete();
        nfile.renameTo(new File("src/try.txt"));
    }

    public static void main(String[] args) throws IOException {
        boolean flag = true;
        do {
            Scanner in = new Scanner(System.in);
            System.out.print("Enter n:");
            int n = in.nextInt();
            System.out.print("Enter m:");
            int m = in.nextInt();
            System.out.print("Enter path (like src/try.txt):");
            String path = in.next();
            if (n >= 1 && m >= 1 && n <= m) {
                deleteLines(n, m, path);
                flag = false;
            }
            else
                System.out.println("Enter correct n and m");
        } while (flag);
    }
}
