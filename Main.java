
import java.sql.*;
import java.util.List;

public class Main {

    private static final String CSV_FILE_PATH = "C:/Users/tanjam/Desktop/pokemon.csv";

    public static void main(String[] args) throws SQLException {
        // Pfad zur CSV-Datei

        // Verbindung zur Datenbank herstellen
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pokemontest", "tanjam", "Sugi");
        try {
            PokemonDataLoader.deleteAllData(connection);
            PokemonDataLoader.loadPokemonData(CSV_FILE_PATH, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Pokedex pokedex = new Pokedex();
        pokedex.loadPData();


        PokemonGame game = new PokemonGame();
        game.choosePokemon(pokedex);
        game.battle();
        connection.close();
    }






