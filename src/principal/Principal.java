package principal;

import dominio.Lector;
import dominio.PokemonServicio;

public class Principal {
    public static void main(String[] args) throws Exception {

        PokemonServicio ps = new PokemonServicio();
        System.out.println("Invente un pokemon");

        System.out.println("Como se llama?");
        String nombre = Lector.leer().nextLine();

        System.out.println("Cual es su nro en la pokedex nacional?");
        int nro = Lector.leer().nextInt();
        Lector.leer().nextLine();

        System.out.println("Cuanto pesa?");
        double peso = Lector.leer().nextDouble();
        Lector.leer().nextLine();

        System.out.println("Que tan alto es?");
        double altura = Lector.leer().nextDouble();
        Lector.leer().nextLine();

        ps.crearPokemon(nro, nombre, peso, altura);

        System.out.println("Y ahora lo vamos a buscar a la db");

        ps.buscarPokemonPorNombre(nombre);

    }
}
