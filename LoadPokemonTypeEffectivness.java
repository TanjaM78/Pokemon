import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

public class LoadPokemonTypeEffectivness {

    private String url;
    private static String username;
    private static String password;

    public void LoadPokemonType(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static void loadPokemonType(Connection connection) throws SQLException {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/tanjam/Desktop/pokemon.csv"));
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


