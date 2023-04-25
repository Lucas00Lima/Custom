import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OpenBank {
    String url = "jdbc:mysql://localhost:3306/db105";
    String user = "root";
    String password = "@soma+";
    String table = "general_configuration";
    public void openBank(List<Dados> dadosList) throws ClassNotFoundException {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Class.forName("com.mysql.jdbc.Driver");
            String sql = "INSERT INTO " + table + " (`key`, value, module) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                int count = 0;
                for (Dados dados : dadosList) {
                    stmt.setString(1, dados.getKey());
                    stmt.setString(2, dados.getValue());
                    stmt.setString(3, dados.getModule());
                    stmt.executeUpdate();
                    count++;
                }
                System.out.println(count + " Linhas inseridas");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
