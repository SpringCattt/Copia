package MODELS.DAO;
import MODELS.CLASS.Espaco;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EspacoDAO {

    // ---- SELECT (one bilhete) ----
    public Espaco getEspacoById(int id) {
        String sql = "SELECT * FROM Espaco WHERE idEspaco = ?";
        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Espaco e = new Espaco();
                e.setIdEspaco(rs.getInt("IdEspaco"));
                e.setNome(rs.getString("Nome"));
                e.setAtivo(rs.getBoolean("Ativo"));
                return e;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isEspacoAtivo(int id) {
        String sql = "SELECT Ativo FROM Espaco WHERE IdEspaco = ?";

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

    // ---- SELECT (all bilhetes) ----
    public List<Espaco> getAllEspaco() {
        List<Espaco> espacos = new ArrayList<>();
        String sql = "SELECT * FROM Espaco WHERE Ativo = 1";

        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Espaco e = new Espaco();
                e.setIdEspaco(rs.getInt("IdEspaco"));
                e.setNome(rs.getString("Nome"));
                espacos.add(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return espacos;
    }

    // ---- INSERT ----
    public long insertEspaco(Espaco espaco) {
        String SQL = "INSERT INTO Espaco (Nome, Ativo) VALUES (?, ?)";
        int generatedId = -1;

        try (Connection conn = BaseDados.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, espaco.getNome());
            stmt.setBoolean(2, espaco.isAtivo());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1);
                        espaco.setIdEspaco(generatedId); // Define o ID no objeto Java
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }

    // ---- UPDATE ---- 
    public boolean updateEspaco(Espaco espaco) {
        String SQL = "UPDATE Espaco SET Nome = ?, Ativo = ? WHERE IdEspaco = ?";

        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setString(1, espaco.getNome());
            stmt.setBoolean(2, espaco.isAtivo());
            stmt.setInt(3, espaco.getIdEspaco());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}