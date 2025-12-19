package MODELS.DAO;
import MODELS.CLASS.Bilhete;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BilheteDAO {

    // ---- SELECT (one bilhete) ----
    public Bilhete getBilheteById(int id) {
        String sql = "SELECT * FROM Bilhete WHERE idBilhete = ?";
        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Bilhete b = new Bilhete();
                b.setIdBilhete(rs.getInt("IdBilhete"));
                b.setIdEvento(rs.getInt("IdEvento"));
                b.setLugar(rs.getInt("Lugar"));
                b.setIdCliente(rs.getInt("IdCliente"));
                b.setPreco(rs.getInt("Preco"));
                b.setAtivo(rs.getBoolean("Ativo"));
                return b;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isBilheteAtivo(int id) {
        String sql = "SELECT Ativo FROM Bilhete WHERE IdBilhete = ?";

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
    public List<Bilhete> getAllBilhetes() {
        List<Bilhete> bilhetes = new ArrayList<>();
        String sql = "SELECT * FROM Bilhete WHERE Ativo = 1";

        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Bilhete b = new Bilhete();
                b.setIdBilhete(rs.getInt("IdBilhete"));
                b.setIdEvento(rs.getInt("IdEvento"));
                b.setLugar(rs.getInt("IdLugar"));
                b.setIdCliente(rs.getInt("IdCliente"));
                b.setPreco(rs.getInt("Preco"));
                bilhetes.add(b);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bilhetes;
    }

    // ---- INSERT ----
    public long insertBilhete(Bilhete bilhete) {
        String SQL = "INSERT INTO Bilhete (IdLugar, IdCliente, IdEvento, Preco, Ativo) VALUES (?, ?, ?, ?, ?)";
        int generatedId = -1;

        try (Connection conn = BaseDados.getConnection();
             // Chave para obter o ID gerado (RETURN_GENERATED_KEYS)
            PreparedStatement stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, bilhete.getLugar());
            stmt.setLong(2, bilhete.getIdCliente());
            stmt.setLong(3, bilhete.getIdEvento());
            stmt.setDouble(4, bilhete.getPreco());
            stmt.setBoolean(5, bilhete.isAtivo());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1);
                        bilhete.setIdBilhete(generatedId); // Define o ID no objeto Java
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }

    // ---- UPDATE ---- 
    public boolean updateBilhete(Bilhete bilhete) {
        String SQL = "UPDATE Bilhete SET IdLugar = ?, IdCliente = ?, IdEvento = ?, Preco = ?, Ativo = ? WHERE IdBilhete = ?";

        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setLong(1, bilhete.getLugar());
            stmt.setLong(2, bilhete.getIdCliente());
            stmt.setLong(3, bilhete.getIdEvento());
            stmt.setDouble(4, bilhete.getPreco());
            stmt.setBoolean(5, bilhete.isAtivo());
            stmt.setLong(6, bilhete.getIdBilhete());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ---- SOFT DELETE (Desativa a categoria sem apagar os dados) ----
    public boolean deleteBilhete(long id) {
        String SQL = "UPDATE Bilhete SET Ativo = 0 WHERE IdBilhete = ?";
        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
}
