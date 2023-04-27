import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

public class LoadPokemonType {
    private String url;
    private static String username;
    private static String password;

    public LoadPokemonType(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    public static void deleteAllData(Connection connection) throws SQLException {
        String deleteSql = "DELETE FROM Pokemon_type";
        try (PreparedStatement statement = connection.prepareStatement(deleteSql)) {
            statement.executeUpdate();
        }
    }
    public static void loadPokemonType(String csvFilePath, Connection connection) throws SQLException {
        int row = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
             PreparedStatement statement = connection.prepareStatement("Insert Into pokemon_type(pokemon_id, type_id) Values(?,?)")) {

            reader.readLine(); // skip header line
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");



                int pokemon_id=  parseInt(data[15]);
                int type_id=  parseInt(data[13]);

                statement.setInt(1, pokemon_id);
                statement.setInt(2, type_id);
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

