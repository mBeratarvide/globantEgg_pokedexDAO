package dominio;

import java.util.Objects;

public class Pokemon {
    private int idPokemon;
    private int numeroPokedexNacional;
    private String nombre;
    private double peso;
    private double altura;

    public Pokemon() {    }

    public Pokemon(int idPokemon, int numeroPokedexNacional, String nombre, double peso, double altura) {
        this.idPokemon = idPokemon;
        this.numeroPokedexNacional = numeroPokedexNacional;
        this.nombre = nombre;
        this.peso = peso;
        this.altura = altura;
    }

    public int getIdPokemon() {
        return idPokemon;
    }

    public void setIdPokemon(int idPokemon) {
        this.idPokemon = idPokemon;
    }

    public int getNumeroPokedexNacional() {
        return numeroPokedexNacional;
    }

    public void setNumeroPokedexNacional(int numeroPokedexNacional) {
        this.numeroPokedexNacional = numeroPokedexNacional;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "idPokemon=" + idPokemon +
                ", numeroPokedexNacional=" + numeroPokedexNacional +
                ", nombre='" + nombre + '\'' +
                ", peso=" + peso +
                ", altura=" + altura +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return idPokemon == pokemon.idPokemon && numeroPokedexNacional == pokemon.numeroPokedexNacional && Double.compare(peso, pokemon.peso) == 0 && Double.compare(altura, pokemon.altura) == 0 && Objects.equals(nombre, pokemon.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPokemon, numeroPokedexNacional, nombre, peso, altura);
    }
}
