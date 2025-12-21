package MODELS.DAO;

import MODELS.CLASS.Recurso;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecursoDAO {

    private Recurso mapResultSetToRecurso(ResultSet rs) throws SQLException {
        Recurso r = new Recurso();
        r.setIdRecurso(rs.getInt("IdRecurso"));
        r.setNome(rs.getString("Nome"));
        r.setPreco(rs.getDouble("Preco"));
        r.setQuantidade(rs.getInt("Quantidade"));
        r.setAtivo(rs.getBoolean("Ativo"));
        return r;
    }

    // ---- SELECT (um recurso) ----
    public Recurso getRecursoById(int id) {
        String sql = "SELECT * FROM Recurso WHERE IdRecurso = ?";
        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToRecurso(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isRecursoAtivo(int id) {
        String sql = "SELECT Ativo FROM Recurso WHERE IdRecurso = ?";

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

    // ---- SELECT (todos os recursos) ----
    public List<Recurso> getAllRecursosWithoutAtivo() {
        List<Recurso> recursos = new ArrayList<>();
        String sql = "SELECT * FROM Recurso";

        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                recursos.add(mapResultSetToRecurso(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recursos;
    }

    // ---- SELECT (todos os recursos) ----
    public List<Recurso> getAllRecursos() {
        List<Recurso> recursos = new ArrayList<>();
        String sql = "SELECT * FROM Recurso WHERE Ativo = 1";

        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                recursos.add(mapResultSetToRecurso(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recursos;
    }

    // ---- INSERT ----
    public long insertRecurso(Recurso recurso) {
        String SQL = "INSERT INTO Recurso (Nome, Preco, Quantidade, Ativo) VALUES (?, ?, ?, ?)";
        long generatedId = -1;

        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, recurso.getNome());
            stmt.setDouble(2, recurso.getPreco());
            stmt.setInt(3, recurso.getQuantidade());
            stmt.setBoolean(4, recurso.isAtivo());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getLong(1);
                        recurso.setIdRecurso((int) generatedId);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }

    public boolean updateRecurso(Recurso recurso) {
        // Ordem: Nome(1), Preco(2), Quantidade(3), Ativo(4), WHERE IdRecurso(5)
        String SQL = "UPDATE Recurso SET Nome = ?, Preco = ?, Quantidade = ?, Ativo = ? WHERE IdRecurso = ?";

        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setString(1, recurso.getNome());
            stmt.setDouble(2, recurso.getPreco());
            stmt.setInt(3, recurso.getQuantidade());
            stmt.setBoolean(4, recurso.isAtivo()); // Aqui deve ir a variável 'ativoAtual'
            stmt.setInt(5, recurso.getIdRecurso());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ---- SOFT DELETE (Desativa a categoria sem apagar os dados) ----
    public boolean deleteRecurso(long id) {
        String SQL = "UPDATE Recurso SET Ativo = 0 WHERE IdRecurso = ?";
        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getTipoRecursoById(int id) {
        String sqlCons = "SELECT COUNT(*) FROM Consumiveis WHERE IdRecurso = ?";
        String sqlNaoCons = "SELECT COUNT(*) FROM NaoConsumiveis WHERE IdRecurso = ?";

        try (Connection conn = BaseDados.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(sqlCons)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    return "Consumível";
                }
            }

            try (PreparedStatement stmt = conn.prepareStatement(sqlNaoCons)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    return "Não Consumível";
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Desconhecido";
    }
}
