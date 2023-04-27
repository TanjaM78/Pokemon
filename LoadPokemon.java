import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

public class LoadPokemon {
    private String url;
    private String username;
    private String password;

    public LoadPokemon(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static void deletePokemonDB(Connection connection) throws SQLException {
        String deleteSql = "DELETE FROM pokemon";
        try (PreparedStatement statement = connection.prepareStatement(deleteSql)) {
            statement.executeUpdate();
        }
    }

    // Restlicher Code der Klasse
    public static void loadPokemon(Connection connection) throws SQLException {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/tanjam/Desktop/pokemon.csv"));
             PreparedStatement statement = connection.prepareStatement("INSERT INTO pokemon (pokemon_id, pokedex_id, name, attack_ability_id) VALUES (?,?,?,?)")) {
            reader.readLine(); // skip header line
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                int pokemon_id = parseInt(data[15]);
                int pokedex_id = parseInt(data[14]);
                int attack_ability_id = parseInt(data[16]);
                String name = data[0];
                int attack_ability = parseInt(data[14]);
                statement.setInt(1, pokemon_id);
                statement.setInt(2, pokedex_id);
                statement.setString(3, name);
                statement.setInt(4, attack_ability_id);

                System.out.println(statement);
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}



