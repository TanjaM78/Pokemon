import java.util.List;

public interface PokemonDataInterface {
    public List<Pokemon> readCSV(String csvFilePath);
    public void writeDB(List<Pokemon> pokemonList);
}




