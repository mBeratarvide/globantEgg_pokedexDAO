package dominio;

import persistencia.PokemonDAO;

public class PokemonServicio {
    private PokemonDAO pkmnDAO;

    public PokemonServicio() {
        this.pkmnDAO = new PokemonDAO();
    }

    public void crearPokemon(int nroPokedex, String nombre, double peso, double altura) throws Exception {
        try {
            // validaciones
            if(nroPokedex < 1) {
                throw new Exception("El nro es incorrecto");
            }

            if(nombre == null || nombre.isEmpty()) {
                throw new Exception("El nombre es obligatorio");
            }

            if(peso <= 0) {
                throw new Exception("El peso no puede ser mágico");
            }

            if(altura <= 0) {
                throw new Exception("La altura no puede ser cuántica");
            }

            // creo
            Pokemon pkmn = new Pokemon();
            pkmn.setNombre(nombre);
            pkmn.setNumeroPokedexNacional(nroPokedex);
            pkmn.setAltura(altura);
            pkmn.setPeso(peso);

            // guardo en db
            pkmnDAO.guardarPokemon(pkmn);

        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public void buscarPokemonPorNombre(String nombre) {
        try {
            Pokemon pkmn = pkmnDAO.getPokemonByNombre(nombre);
            System.out.println(pkmn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void buscarPokemonPorNro(int nro) {
        try {
            Pokemon pkmn = pkmnDAO.getPokemonById(nro);
            System.out.println(pkmn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
