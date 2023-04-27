public class Pokemon {
    private int pokedex_id;
    private String name;
    private String type1;
    private String type2;
    private int total;
    private int hp;
    private int attack;
    private int defense;
    private int spk_attack;
    private int spk_defense;
    private int speed;
    private int generation;
    private String legendary;
    private int pokemon_id;
    private int attack_abillity_id;



    public int getPokedex_id() {
        return pokedex_id;
    }

    public void setPokedex_id(int pokedex_id) {
        this.pokedex_id = pokedex_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int level) {
        this.total = total;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpk_attack() {
        return spk_attack;
    }

    public void setSpk_attack(int spk_attack) {
        this.spk_attack = spk_attack;
    }

    public int getSpk_defense() {
        return spk_defense;
    }

    public void setSpk_defense(int spk_defense) {
        this.spk_defense = spk_defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public String getLegendary() {
        return legendary;
    }

    public void setLegendary(String legendary) {
        this.legendary = legendary;
    }

    public int getPokemon_id() {
        return pokemon_id;
    }

    public void setPokemon_id(int pokemon_id) {
        this.pokemon_id = pokemon_id;
    }

    public int getAttack_abillity_id() {
        return attack_abillity_id;
    }

    public void setAttack_abillity_id(int attack_abillity_id) {
        this.attack_abillity_id = attack_abillity_id;
    }

    public Pokemon(int pokedex_id, String name, String type1, String type2, int total, int hp, int attack, int defense, int spk_attack, int spk_defense, int speed, int generation, String legendary, int pokemon_id, int attack_abillity_id) {
        this.pokedex_id = pokedex_id;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.total = total;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.spk_attack = spk_attack;
        this.spk_defense = spk_defense;
        this.speed = speed;
        this.generation = generation;
        this.legendary = legendary;
        this.pokemon_id = pokemon_id;
        this.attack_abillity_id = attack_abillity_id;
    }


    @Override
    public String toString() {
        return "Pokemon{" +
                "pokedex_id=" + pokedex_id +
                ", name='" + name + '\'' +
                ", type1='" + type1 + '\'' +
                ", type2='" + type2 + '\'' +
                ", total=" + total +
                ", hp=" + hp +
                ", attack=" + attack +
                ", defense=" + defense +
                ", spk_attack=" + spk_attack +
                ", spk_defense=" + spk_defense +
                ", speed=" + speed +
                ", generation=" + generation +
                ", legendary='" + legendary + '\'' +
                ", pokemon_id=" + pokemon_id +
                ", attack_abillity_id=" + attack_abillity_id +
                '}';
    }


}


