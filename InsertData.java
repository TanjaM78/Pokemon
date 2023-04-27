import java.sql.*;

public class InsertData {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mydatabase?useSSL=false&allowPublicKeyRetrieval=true";
        String username = "myusername";
        String password = "mypassword";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement()) {

            String copyQuery = "INSERT INTO pokemon (pokedex_id) SELECT pokedex_id FROM pokedex";
            int rowsCopied = stmt.executeUpdate(copyQuery);
            System.out.println(rowsCopied + " rows copied from pokemon to pokedex");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement()) {

            String copyQuery = "INSERT INTO pokemon (attack_ability_id) SELECT attack_ability_id FROM attack_ability";
            int rowsCopied = stmt.executeUpdate(copyQuery);
            System.out.println(rowsCopied + " rows copied from attack_ability:" +
                    " to pokemon");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


