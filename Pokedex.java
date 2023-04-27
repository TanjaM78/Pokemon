import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Pokedex {
    private static final String SELECT_QUERY = "SELECT pokedex_id, Name, t1.type_name As type1, t2.type_name AS type2, total, hp, attack, defense, spk_attack, spk_defense, speed, generation, legendary, pokemon_id, attack_ability_id FROM pokedex JOIN type t1 ON pokedex.type1 = t1.type_id JOIN type t2 on pokedex.type2 = t2.type_id ORDER BY pokedex_id asc";
    List<Pokemon> pokemonList = new ArrayList<>();

    public void loadPData() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pokemontest", "tanjam", "Sugi");
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int pokedexId = resultSet.getInt("pokedex_id");
                String name = resultSet.getString("name");
                String type1 = resultSet.getString("type1");
                String type2 = resultSet.getString("type2");
                int total = resultSet.getInt("total");
                int hp = resultSet.getInt("hp");
                int attack = resultSet.getInt("attack");
                int defense = resultSet.getInt("defense");
                int spk_attack = resultSet.getInt("spk_attack");
                int spk_defense = resultSet.getInt("spk_defense");
                int speed = resultSet.getInt("speed");
                int generation = resultSet.getInt("generation");
                String legendary = resultSet.getString("legendary");
                int pokemonId = resultSet.getInt("pokemon_id");
                int attackAbilityId = resultSet.getInt("attack_ability_id");

                Pokemon pokemon = new Pokemon(pokedexId, name, type1, type2, total, hp, attack, defense, spk_attack, spk_defense, speed, generation, legendary, pokemonId, attackAbilityId);
                pokemonList.add(pokemon);

                //  System.out.println(pokemon);
                // System.out.println(pokemonList);
            }
                connection.close();

            }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}

