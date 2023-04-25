import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        String filename = "C:\\Users\\lukin\\OneDrive\\Área de Trabalho\\Arquivo\\custom1.csv";
        File file = new File();
        List<Dados> dadosList = file.verificarRegras(filename);
        OpenBank open = new OpenBank();
        open.openBank(dadosList);
    }
}
