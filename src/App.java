import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        String filename = "arquivo.txt";
        String url = "jdbc:mysql://localhost:3306/banco_de_dados";
        String user = "usuario";
        String password = "senha";
        String table = "tabela";

        try {
            File file = new File(filename);
            FileReader fileReader =  new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Connection connection = DriverManager.getConnection(url, user, password);
            
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String [] fields = line.split(",");
                
            }
        }
        catch{
        }
    }
}
