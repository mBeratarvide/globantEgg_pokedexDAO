package persistencia;

import dominio.Pokemon;

import java.util.ArrayList;

public final class PokemonDAO extends DAO {

    public void guardarPokemon(Pokemon pkmn) throws Exception {
        try {
            if (pkmn == null) {
                throw new Exception("Pokemon nulo");
            }

            String query = "INSERT INTO pokemon(numeroPokedexNacional, nombre, peso, altura)" +
                    "VALUES(?, ?, ?, ?)";

            //modificarBase(query);
            modificarBaseStmt(query, new Object[] {
                    pkmn.getNumeroPokedexNacional(),
                    pkmn.getNombre(),
                    pkmn.getPeso(),
                    pkmn.getAltura()
            });
        } catch(Exception e) {
            throw e;
        }
    }

    public void actualizarPokemon(Pokemon pkmn) throws Exception {
        try {
            if(pkmn == null) {
                throw new Exception("Pokemon nulo");
            }

            String query = "UPDATE pokemon SET numeroPokedexNacional = ?, nombre = ?, peso = ?, altura = ? WHERE idPokemon = ?";

            //modificarBase(query);
            modificarBaseStmt(query, new Object[] {
                    pkmn.getNumeroPokedexNacional(),
                    pkmn.getNombre(),
                    pkmn.getPeso(),
                    pkmn.getAltura(),
                    pkmn.getIdPokemon()
            });
        } catch (Exception e) {
            throw e;
        }
    }

    public void borrarPokemon(Pokemon pkmn) throws Exception {
        try {
            if(pkmn == null) {
                throw new Exception("Pokemon nulo");
            }

            String query = "DELETE FROM pokemon WHERE idPokemon = ?";

            //modificarBase(query);
            modificarBaseStmt(query, new Object[] { pkmn.getIdPokemon() });
        } catch (Exception e) {
            throw e;
        }
    }

    public Pokemon getPokemonById(int id) throws Exception {
        try {
            String query = "SELECT * FROM pokemon WHERE idPokemon = ?";
            //leerBase(query);
            leerBaseStmt(query, new Object[] { id });
            Pokemon pkmn = null;
            while(resultado.next()) {
                pkmn = new Pokemon(
                        resultado.getInt("idPokemon"),
                        resultado.getInt("numeroPokedexNacional"),
                        resultado.getString("nombre"),
                        resultado.getDouble("peso"),
                        resultado.getDouble("altura")
                );
            }
            return pkmn;
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public Pokemon getPokemonByNombre(String nombre) throws Exception {
        try {
            String patron = "%" + nombre + "%";
            String query = "SELECT * FROM pokemon WHERE nombre like ?";
            //leerBase(query);
            leerBaseStmt(query, new Object[] { patron });
            Pokemon pkmn = null;
            while(resultado.next()) {
                pkmn = new Pokemon(
                        resultado.getInt("idPokemon"),
                        resultado.getInt("numeroPokedexNacional"),
                        resultado.getString("nombre"),
                        resultado.getDouble("peso"),
                        resultado.getDouble("altura")
                );
            }
            return pkmn;
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public ArrayList<Pokemon> getEmAll() throws Exception {
        try {
            String query = "SELECT * FROM pokemon";
            leerBase(query);
            ArrayList<Pokemon> lesPkmn = new ArrayList<>();
            while(resultado.next()) {
                lesPkmn.add(new Pokemon(
                        resultado.getInt("idPokemon"),
                        resultado.getInt("numeroPokedexNacional"),
                        resultado.getString("nombre"),
                        resultado.getDouble("peso"),
                        resultado.getDouble("altura")
                ));
            }
            return lesPkmn;
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

}
