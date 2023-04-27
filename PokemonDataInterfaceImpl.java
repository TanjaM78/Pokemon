import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

class PokemonDataInterfaceImpl implements PokemonDataInterface {
    private String url = "jdbc:mysql://localhost:3306/mydatabase";
    private String username = "username";
    private String password = "password";

    @Override
    public List<Pokemon> readCSV(String csvFilePath) {
        List<Pokemon> pokemonList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line = reader.readLine();
            while (line != null) {
                String[] data = line.split(",");

                int pokedex_id = parseInt(data[0]);
                String name = data[1];
                String type1 = data[2];
                String type2 = data[3];
                int total = parseInt(data[4]);
                int hp = parseInt(data[5]);
                int attack = parseInt(data[6]);
                int defense = parseInt(data[7]);
                int spk_attack = parseInt(data[8]);
                int spk_defense = parseInt(data[9]);
                int speed = parseInt(data[10]);
                int generation = parseInt(data[11]);
                String legendary = data[12];
                int pokemon_id= parseInt(data[13]);
                int attack_ability_id= parseInt(data[14]);

                Pokemon pokemon = new Pokemon(pokedex_id, name, type1, type2, total, hp, attack, defense, spk_attack, spk_defense, speed, generation, legendary,pokemon_id, attack_ability_id);
                pokemonList.add(pokemon);

                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pokemonList;
    }

    @Override
    public void writeDB(List<Pokemon> pokemonList) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement statement = connection.createStatement();
            for (Pokemon pokemon : pokemonList) {
                String query = "INSERT INTO pokemon ( pokedex_id, name, type1, type2, hp, attack, defense, spk_attack, spk_defense, speed, generation, legendary, pokemon_id, attack_ability_id) " +
                        "VALUES (" + pokemon.getPokedex_id() + ", '" + pokemon.getName() + "', '" +
                        pokemon.getType1() + "', '" + pokemon.getType2() + "', " + pokemon.getHp() + ", " + pokemon.getAttack() +
                        ", " + pokemon.getDefense() + ", " + pokemon.getSpk_attack() + ", " + pokemon.getSpk_defense() + ", " + pokemon.getSpeed() + ", '" + pokemon.getGeneration() + "', '" + pokemon.getLegendary() + "','"+pokemon.getPokemon_id()+ "','"+ pokemon.getAttack_abillity_id()+"')";
                statement.executeUpdate(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}



