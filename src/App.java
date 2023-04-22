import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        String filename = "Local Arquivo";
        String url = "jdbc:mysql://localhost:3306/banco_de_dados";
        String user = "root";
        String password = "senha";
        String table = "table";
        List<Dados> dadosList =  new ArrayList<>();
        try {
            //Abre o arquivo
            File file = new File(filename);
            Scanner scanner =  new Scanner(file);
            //Separa os dados do arquivo por linha e depois separa os valores por =
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                String key = values[0].replaceAll("\"", "'");
                String value = values[1].replaceAll("\"", "'");
                Dados dados = new Dados(key, value);
                dadosList.add(dados);
            }
            scanner.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Class.forName("com.mysql.jdbc.Driver");
            String sql = "INSERT INTO  general_configuration (`key`, value, module) VALUES (?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    int count = 0;
                    for (Dados dados : dadosList) {
                        stmt.setString(1,dados.getKey());
                        stmt.setString(2, dados.getValue());
                        stmt.setString(3, "");
                        int rows = stmt.executeUpdate();
                        count++;
                    }
                    System.out.println(count + " Linhas inseridas");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
public static class Dados {
    private String key;
    private String value;
        public Dados(String key, String value) {
            this.key = key;
            this.value = value;
        }
        public String getKey() {return key;}
        public void setKey(String key) {
            this.key = key;
        }
        public String getValue() {return value;}
        public void setValue(String value) {
            this.value = value;
        }
    }
}
