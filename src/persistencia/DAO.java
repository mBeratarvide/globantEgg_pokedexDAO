package persistencia;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public abstract class DAO {

    protected Connection conexion = null;
    protected ResultSet resultado = null;
    protected Statement sentencia = null;
    protected PreparedStatement stmt = null;

    // TODO: modificar a un dotEnv
    private final String USUARIO = obtenerCredenciales("usuario_db");
    private final String CLAVE = obtenerCredenciales("clave_db");
    private final String BASE = obtenerCredenciales("db");
    private final String DRIVER = "com.mysql.jdbc.Driver";
    
    protected void conectarBase() throws ClassNotFoundException, SQLException {
        try {
            Class.forName(DRIVER);
            String urldb = "jdbc:mysql://localhost:3306/" + BASE + "?useSSL=false";
            conexion = DriverManager.getConnection(urldb, USUARIO, CLAVE);
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        }
    }

    protected void desconectarBase() throws Exception {
        try {
            if(resultado != null) {
                resultado.close();
            }
            if(sentencia != null) {
                sentencia.close();
            }
            if(conexion != null) {
                conexion.close();
            }
            if(stmt != null) {
                stmt.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void leerBaseStmt(String query, Object[] parametros) throws Exception {
        try {
            conectarBase();
            stmt = conexion.prepareStatement(query);
            prepararStatement(parametros);
            resultado = stmt.executeQuery();
        } catch(Exception e) {
            throw e;
        }
    }

    public void modificarBaseStmt(String query, Object[] parametros) throws Exception {
        try {
            conectarBase();
            stmt = conexion.prepareStatement(query);
            prepararStatement(parametros);
            stmt.executeUpdate();
            conexion.commit();
        } catch(Exception e) {
            conexion.rollback();
            throw e;
        } finally {
            desconectarBase();
        }
    }

    protected void modificarBase(String query) throws Exception {
        try {
            conectarBase();
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(query);
            conexion.commit();
        } catch(SQLException | ClassNotFoundException ex) {
            conexion.rollback();
            throw ex;
        } finally {
            desconectarBase();
        }
    }

    protected void leerBase(String query) throws Exception {
        try {
            conectarBase();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(query);
        } catch (Exception e) {
            throw e;
        }
    }

    private void prepararStatement(Object[] parametros) throws SQLException {
        int i = 1;
        for (Object parametro : parametros) {
            if (parametro instanceof String) {
                stmt.setString(i, (String) parametro);
            } else if (parametro instanceof Integer) {
                stmt.setInt(i, (Integer) parametro);
            } else if (parametro instanceof Double) {
                stmt.setDouble(i, (Double) parametro);
            } else if (parametro instanceof Boolean) {
                stmt.setBoolean(i, (Boolean) parametro);
            }
            // TODO: uno para DATE :B
            i++;
        }
    }

    private String obtenerCredenciales(String variable) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/recursos/credenciales")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(variable);
    }

}
