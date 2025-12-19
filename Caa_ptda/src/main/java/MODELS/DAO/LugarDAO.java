package MODELS.DAO;
import MODELS.CLASS.Lugar;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LugarDAO {

    private Lugar mapResultSetToLugar(ResultSet rs) throws SQLException {
        Lugar l = new Lugar();
        l.setIdLugar(rs.getInt("IdLugar"));
        l.setNumero(rs.getString("Numero"));
        l.setIdSala(rs.getInt("IdSala"));
        l.setAtivo(rs.getBoolean("Ativo"));
        return l;
    }

    // ---- SELECT (um lugar) ----
    public Lugar getLugarById(int id) {
        String sql = "SELECT * FROM Lugar WHERE IdLugar = ?";
        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToLugar(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isLugarAtivo(int id) {
        String sql = "SELECT Ativo FROM Lugar WHERE IdLugar = ?";

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

    // ---- SELECT (todos os lugares) ----
    public List<Lugar> getAllLugares() {
        List<Lugar> lugares = new ArrayList<>();
        String sql = "SELECT * FROM Lugar WHERE Ativo = 1";

        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lugares.add(mapResultSetToLugar(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lugares;
    }

    // ---- INSERT ----
    public long insertLugar(Lugar lugar) {
        String SQL = "INSERT INTO Lugar (Numero, IdSala, Ativo) VALUES (?, ?, ?)";
        long generatedId = -1;

        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, lugar.getNumero());
            stmt.setInt(2, lugar.getIdSala()); // IdSala é uma chave estrangeira
            stmt.setBoolean(3, lugar.isAtivo());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getLong(1);
                        lugar.setIdLugar((int) generatedId); // Define o ID no objeto Java
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }

    // ---- UPDATE ----
    public boolean updateLugar(Lugar lugar) {
        String SQL = "UPDATE Lugar SET Numero = ?, IdSala = ?, Ativo = ? WHERE IdLugar = ?";

        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setString(1, lugar.getNumero());
            stmt.setInt(2, lugar.getIdSala());
            stmt.setBoolean(3, lugar.isAtivo());
            stmt.setInt(4, lugar.getIdLugar());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ---- SOFT DELETE (Desativa a categoria sem apagar os dados) ----
    public boolean deleteLugar(long id) {
        String SQL = "UPDATE Lugar SET Ativo = 0 WHERE IdLugar = ?";
        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
}