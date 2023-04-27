import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static java.lang.Integer.parseInt;

public class LoadType {

    private String url;
    private String username;
    private String password;

    public LoadType(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static void deleteAllData(Connection connection) throws SQLException {
        String deleteSql = "DELETE FROM Type";
        try (PreparedStatement statement = connection.prepareStatement(deleteSql)) {
            statement.executeUpdate();
        }
    }

    public static List<String> loadType(Connection connection) throws SQLException {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/tanjam/Desktop/pokemon.csv"))) {
            deleteAllData(connection);

            // Erstellen eines HashSet, um alle einzigartigen Werte aus den Spalten Type1 und Type2 zu sammeln
            HashSet<String> typeNames = new HashSet<>();

            reader.readLine(); // Überspringen der Header-Zeile
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                typeNames.add(data[1]); // Type1 hinzufügen
                typeNames.add(data[2]); // Type2 hinzufügen
            }

            typeNames.remove("");
            List<String> typeNamesList = typeNames.stream().toList();
            // Statement vorbereiten
            PreparedStatement statement = connection.prepareStatement("INSERT INTO type(Type_Name, Type_id) VALUES (?, ?)");

            int typeId = 1;
            // Werte in die Datenbank übertragen
            for (String typeName : typeNames) {
                statement.setString(1, typeName);
                statement.setInt(2, typeId);
                statement.executeUpdate();
                typeId++;
            }
            // Ressourcen freigeben
            statement.close();

            return typeNamesList;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
