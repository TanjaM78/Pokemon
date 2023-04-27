
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.List;

import static java.lang.Integer.parseInt;

public class PokemonDataLoader {

    public static final String INSERT_SQL = "INSERT INTO pokedex (name, type1, type2, total, hp, attack, defense, spk_attack, spk_defense, speed, generation, legendary, pokedex_id, pokemon_id, attack_ability_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public static void deleteAllData(Connection connection) throws SQLException {
        String deleteSql = "DELETE FROM pokedex";
        try (PreparedStatement statement = connection.prepareStatement(deleteSql)) {
            statement.executeUpdate();
        }
    }

    public static void loadPokemonData(String csvFilePath, Connection connection) throws SQLException {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
             PreparedStatement statement = connection.prepareStatement(INSERT_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            reader.readLine(); // Überspringe die erste Zeile
            String line;

            List<String> types = LoadType.loadType(connection);
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");

                String name = data[0];
                String type1 = data[1];
                int typeId1 = types.indexOf(type1) + 1;
                String type2 = data[2];
                int typeId2 = types.indexOf(type2) + 1;
                int total = parseInt(data[3]);
                int hp = parseInt(data[4]);
                int attack = parseInt(data[5]);
                int defense = parseInt(data[6]);
                int spk_attack = parseInt(data[7]);
                int spk_defense = parseInt(data[8]);
                int speed = parseInt(data[9]);
                String generation = data[10];
                String legendary = data[11];
                int pokedex_id = parseInt(data[12]);
                int pokemon_id = parseInt(data[13]);
                int attack_ability_id = parseInt(data[14]);

                statement.setString(1, name);
                statement.setInt(2, typeId1);
                if (typeId2 != 0) {
                    statement.setInt(3, typeId2);
                }
                statement.setInt(4, total);
                statement.setInt(5, hp);
                statement.setInt(6, attack);
                statement.setInt(7, defense);
                statement.setInt(8, spk_attack);
                statement.setInt(9, spk_defense);
                statement.setInt(10, speed);
                statement.setString(11, generation);
                statement.setString(12, legendary);
                statement.setInt(13, pokedex_id);
                statement.setInt(14, pokemon_id);
                statement.setInt(15, attack_ability_id);

                statement.addBatch();
            }

            statement.executeBatch();

        } catch (FileNotFoundException e) {
            System.err.println("Die Datei wurde nicht gefunden: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ein Fehler ist aufgetreten: " + e.getMessage());
        }
    }
}


