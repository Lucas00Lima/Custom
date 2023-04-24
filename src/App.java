import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) throws Exception {
        String filename = "localArquivo";
        String url = "jdbc:mysql://localhost:3306/Banco";
        String user = "root";
        String password = "Senha";
        String table = "table";
        List<Dados> dadosList =  new ArrayList<>();
        try {
            File file = new File(filename);
            Scanner scanner =  new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String module = "";
                String value = "";
                        if(line.contains("availableSaleTypes")) {
                            Pattern pattern = Pattern.compile("\\[(.*?)\\]");
                            Matcher matcher = pattern.matcher(line);
                                if(matcher.find()) {
                                    value = matcher.group(1);
                                }
                                line =  line.replaceFirst(Pattern.quote(value), "");
                                String[] values = line.split(",");
                                module = values[2].replaceAll("\"", "'");
                        }else {
                            String[] values = line.split(",");
                            value = values[1].replaceAll("\"", "'");
                            module = values[2].replaceAll("\"", "'");
                        }
                String[] values = line.split(",");
                String key = values[0].replaceAll("\"", "'");
                String arrayString = Arrays.toString(values);
                System.out.println(arrayString);
                Dados dados = new Dados(key, value, module);
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
                        stmt.setString(3, dados.getModule());
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
    private String module;
        public Dados(String key, String value, String module) {
            this.key = key;
            this.value = value;
            this.module = module;
        }
        public String getKey() {return key;}
        public void setKey(String key) {
            this.key = key;
        }
        public String getValue() {return value;}
        public void setValue(String value) {
            this.value = value;
        }
        public String getModule() {return module;}
        public void setModule(String module) {this.module = module;}
    }
}
