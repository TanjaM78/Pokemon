import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoadAttackAbility {
    private String url;
    private String username;
    private String password;

    public LoadAttackAbility(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    public static void deleteAttackAbilityDB(Connection connection) throws SQLException {
        String deleteSql = "DELETE FROM attack_ability";
        try (PreparedStatement statement = connection.prepareStatement(deleteSql)) {
            statement.executeUpdate();
        }
    }

    public static void loadAttackAbility(String csvFilePath, Connection connection) throws SQLException {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/tanjam/Desktop/pokemon.csv"));
             PreparedStatement statement = connection.prepareStatement("Insert Into attack_ability (attack_ability_id, damage, name) Values(?,?,?)"))
        {
            reader.readLine(); // skip header line
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");

                int attack_ability_id= Integer.parseInt(data[16]);
                int damage = Integer.parseInt(data[5]);
                String name = data[0];


                statement.setInt(1, attack_ability_id);
                statement.setInt(2, damage);
                statement.setString(3, name);

                System.out.println(statement);
                statement.addBatch();
            }
            statement.executeBatch();

        }            catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
