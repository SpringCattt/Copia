package MODELS.DAO;

import MODELS.CLASS.Trabalhador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrabalhadorDAO {

    public Trabalhador getTrabalhadorById(int id) {
        String sql = "SELECT * FROM Trabalhador WHERE IdTrabalhador = ?";
        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToTrabalhador(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Trabalhador> getAllTrabalhadores() {
        List<Trabalhador> lista = new ArrayList<>();
        String sql = "SELECT * FROM Trabalhador WHERE Ativo = 1";
        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapResultSetToTrabalhador(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public long insertTrabalhador(Trabalhador t) {
        String sql = "INSERT INTO Trabalhador (Nome, Email, Categoria, Ativo) VALUES (?, ?, ?, ?)";
        long id = -1;
        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, t.getNome());
            stmt.setString(2, t.getEmailPessoal());
            stmt.setInt(3, t.getCategoria());
            stmt.setBoolean(4, t.isAtivo());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean updateTrabalhador(Trabalhador t) {
        String sql = "UPDATE Trabalhador SET Nome = ?, Email = ?, Categoria = ?, Ativo = ? WHERE IdTrabalhador = ?";
        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, t.getNome());
            stmt.setString(2, t.getEmailPessoal());
            stmt.setInt(3, t.getCategoria());
            stmt.setBoolean(4, t.isAtivo());
            stmt.setInt(5, t.getIdTrabalhador());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isTrabalhadorAtivo(int id) {
        String sql = "SELECT Ativo FROM Trabalhador WHERE IdTrabalhador = ?";
        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("Ativo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean desativarTrabalhador(int id) {
        String sql = "UPDATE Trabalhador SET Ativo = 0 WHERE IdTrabalhador = ?";
        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Trabalhador> buscarTrabalhadores(String termo) {
        List<Trabalhador> lista = new ArrayList<>();
        
        String sql = "SELECT t.* FROM Trabalhador t " +
                     "LEFT JOIN CategoriaTrabalho c ON t.Categoria = c.IdCategoria " +
                     "LEFT JOIN Credenciais cr ON t.IdTrabalhador = cr.IdTrabalhador " +
                     "WHERE (LOWER(t.Nome) LIKE LOWER(?) " +
                     "OR LOWER(t.Email) LIKE LOWER(?) " + 
                     "OR LOWER(cr.Email) LIKE LOWER(?) " + 
                     "OR LOWER(c.Nome) LIKE LOWER(?) " +
                     "OR CAST(t.IdTrabalhador AS CHAR) LIKE ?) " +
                     "AND t.Ativo = 1";
                     
        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            String pattern = "%" + termo + "%";
            stmt.setString(1, pattern);
            stmt.setString(2, pattern);
            stmt.setString(3, pattern);
            stmt.setString(4, pattern);
            stmt.setString(5, pattern);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(mapResultSetToTrabalhador(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private Trabalhador mapResultSetToTrabalhador(ResultSet rs) throws SQLException {
        Trabalhador t = new Trabalhador();
        t.setIdTrabalhador(rs.getInt("IdTrabalhador"));
        t.setNome(rs.getString("Nome"));
        
        try {
            t.setEmailPessoal(rs.getString("Email"));
        } catch (SQLException ex) {
            t.setEmailPessoal(rs.getString("EmailPessoal"));
        }
        
        t.setCategoria(rs.getInt("Categoria"));
        t.setAtivo(rs.getBoolean("Ativo"));
        return t;
    }
}