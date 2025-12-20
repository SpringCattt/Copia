package MODELS.DAO;

import MODELS.CLASS.Sala; // Assumindo esta classe existe
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaDAO {

    private Sala mapResultSetToSala(ResultSet rs) throws SQLException {
        Sala s = new Sala();
        s.setIdSala(rs.getInt("IdSala"));
        s.setNome(rs.getString("Nome"));
        s.setTipoEspaco(rs.getInt("TipoEspaco"));
        s.setLugares(rs.getInt("Lugares"));
        s.setOcupada(rs.getBoolean("Ocupada")); // Mapeado para boolean
        s.setTemLugares(rs.getBoolean("TemLugares")); // Mapeado para boolean
        s.setAtivo(rs.getBoolean("Ativo"));
        return s;
    }

    // ---- SELECT (uma sala) ----
    public Sala getSalaById(int id) {
        String sql = "SELECT * FROM Sala WHERE IdSala = ?";
        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToSala(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isSalaAtivo(int id) {
        String sql = "SELECT Ativo FROM Sala WHERE IdSala = ?";

        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean("Ativo");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ---- SELECT (todas as salas) ----
    public List<Sala> getAllSalas() {
        List<Sala> salas = new ArrayList<>();
        String sql = "SELECT * FROM Sala WHERE Ativo = 1";

        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                salas.add(mapResultSetToSala(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salas;
    }

    // ---- INSERT ----
    public long insertSala(Sala sala) {
        String SQL = "INSERT INTO Sala (Nome, TipoEspaco, Lugares, Ocupada, TemLugares, Ativo) VALUES (?, ?, ?, ?, ?, ?)";
        long generatedId = -1;

        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, sala.getNome());
            stmt.setInt(2, sala.getTipoEspaco());
            stmt.setInt(3, sala.getLugares());
            stmt.setBoolean(4, sala.isOcupada());
            stmt.setBoolean(5, sala.isTemLugares());
            stmt.setBoolean(6, sala.isAtivo());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getLong(1);
                        sala.setIdSala((int) generatedId);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }

    // ---- UPDATE ----
    public boolean updateSala(Sala sala) {
        String SQL = "UPDATE Sala SET Nome = ?, TipoEspaco = ?, Lugares = ?, TemLugares = ? WHERE IdSala = ?";

        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setString(1, sala.getNome());
            stmt.setInt(2, sala.getTipoEspaco());
            stmt.setInt(3, sala.getLugares());
            stmt.setBoolean(4, sala.isTemLugares());
            stmt.setInt(5, sala.getIdSala());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ---- SOFT DELETE (Desativa a categoria sem apagar os dados) ----
    public boolean deleteSala(long id) {
        String SQL = "UPDATE Sala SET Ativo = 0 WHERE IdSala = ?";
        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean temEventosAssociados(int idSala) {
        String SQL = "SELECT COUNT(*) FROM Evento WHERE Sala = ?";

        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setInt(1, idSala);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
