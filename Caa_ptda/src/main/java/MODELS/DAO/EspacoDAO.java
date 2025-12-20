package MODELS.DAO;

import MODELS.CLASS.Espaco;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EspacoDAO {

    private Espaco mapResultSetToEspaco(ResultSet rs) throws SQLException {
        Espaco e = new Espaco();
        e.setIdEspaco(rs.getInt("IdEspaco"));
        e.setNome(rs.getString("Nome"));
        e.setAtivo(rs.getBoolean("Ativo"));
        return e;
    }

    public Espaco getEspacoById(int id) {
        String sql = "SELECT * FROM Espaco WHERE idEspaco = ?";
        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return mapResultSetToEspaco(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Espaco> getAllEspaco() {
        List<Espaco> espacos = new ArrayList<>();
        String sql = "SELECT * FROM Espaco WHERE Ativo = 1";
        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) espacos.add(mapResultSetToEspaco(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return espacos;
    }

    public long insertEspaco(Espaco espaco) {
        String SQL = "INSERT INTO Espaco (Nome, Ativo) VALUES (?, ?)";
        long generatedId = -1;
        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, espaco.getNome());
            stmt.setBoolean(2, espaco.isAtivo());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) generatedId = rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }

    public boolean updateEspaco(Espaco espaco) {
        String SQL = "UPDATE Espaco SET Nome = ? WHERE IdEspaco = ?";
        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, espaco.getNome());
            stmt.setInt(2, espaco.getIdEspaco());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteEspaco(int id) {
        String SQL = "UPDATE Espaco SET Ativo = 0 WHERE IdEspaco = ?";
        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isEspacoEmUso(int idEspaco) {
        String SQL = "SELECT COUNT(*) FROM Sala WHERE TipoEspaco = ? AND Ativo = 1";
        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, idEspaco);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Espaco> buscarEspacos(String termo) {
        List<Espaco> lista = new ArrayList<>();
        String sql = "SELECT * FROM Espaco WHERE LOWER(Nome) LIKE LOWER(?) AND Ativo = 1";
        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + termo + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(mapResultSetToEspaco(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}