package MODELS.DAO;

import MODELS.CLASS.Trabalhador;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrabalhadorDAO {

    private Trabalhador mapResultSetToTrabalhador(ResultSet rs) throws SQLException {
        Trabalhador t = new Trabalhador();
        t.setIdTrabalhador(rs.getInt("IdTrabalhador"));
        t.setNome(rs.getString("Nome"));
        t.setCategoria(rs.getInt("Categoria"));
        t.setEmailPessoal(rs.getString("Email"));
        t.setAtivo(rs.getBoolean("Ativo"));
        return t;
    }

    // ---- SELECT (um trabalhador) ----
    public Trabalhador getTrabalhadorById(int id) {
        String sql = "SELECT * FROM Trabalhador WHERE IdTrabalhador = ?";
        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

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

    public boolean isTrabalhadorAtivo(int id) {
        String sql = "SELECT Ativo FROM Trabalhador WHERE IdTrabalhador = ?";

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

    // ---- SELECT (todos os trabalhadores) ----
    public List<Trabalhador> getAllTrabalhadores() {
        List<Trabalhador> trabalhadores = new ArrayList<>();
        String sql = "SELECT * FROM Trabalhador WHERE Ativo = 1";

        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                trabalhadores.add(mapResultSetToTrabalhador(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trabalhadores;
    }

    // ---- INSERT ----
    public long insertTrabalhador(Trabalhador trabalhador) {
        String SQL = "INSERT INTO Trabalhador (Nome, Categoria, Email, Ativo) VALUES (?, ?, ?, ?)";
        long generatedId = -1;

        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, trabalhador.getNome());
            stmt.setInt(2, trabalhador.getCategoria());
            stmt.setString(3, trabalhador.getEmailPessoal());
            stmt.setBoolean(4, trabalhador.isAtivo());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getLong(1);
                        trabalhador.setIdTrabalhador((int) generatedId); // Define o ID no objeto Java
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }

    // ---- UPDATE ----
    public boolean updateTrabalhador(Trabalhador trabalhador) {
        String SQL = "UPDATE Trabalhador SET Nome = ?, Categoria = ?, Email = ?, Ativo = ? WHERE IdTrabalhador = ?";

        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setString(1, trabalhador.getNome());
            stmt.setInt(2, trabalhador.getCategoria());
            stmt.setString(3, trabalhador.getEmailPessoal());
            stmt.setBoolean(4, trabalhador.isAtivo());
            stmt.setInt(5, trabalhador.getIdTrabalhador());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ---- SOFT DELETE (Desativa a categoria sem apagar os dados) ----
    public boolean deleteTrabalhador(long id) {
        String SQL = "UPDATE Trabalhador SET Ativo = 0 WHERE IdTrabalhador = ?";
        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
}
