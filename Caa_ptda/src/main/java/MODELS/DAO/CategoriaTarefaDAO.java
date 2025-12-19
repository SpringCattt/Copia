package MODELS.DAO;

import MODELS.CLASS.CategoriaTarefa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaTarefaDAO {

    // Método auxiliar para mapear o ResultSet para o Objeto
    private CategoriaTarefa mapResultSetToCategoria(ResultSet rs) throws SQLException {
        CategoriaTarefa ct = new CategoriaTarefa();
        ct.setIdCategoriaTarefa(rs.getInt("IdTarefa")); // IdTarefa conforme o dump SQL
        ct.setNome(rs.getString("Nome"));
        ct.setAtivo(rs.getBoolean("Ativo"));
        return ct;
    }

    // ---- SELECT (one) ----
    public CategoriaTarefa getCategoriaTarefaById(long id) {
        // Geralmente, ao procurar por ID específico, não filtramos por Ativo 
        // para permitir que o sistema encontre registos históricos se necessário.
        String sql = "SELECT * FROM CategoriaTarefa WHERE IdTarefa = ?";
        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCategoria(rs);
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    // ---- SELECT (apenas as ativas) ----
    public List<CategoriaTarefa> getAllCategoriasTarefa() {
        List<CategoriaTarefa> categorias = new ArrayList<>();
        // Filtro essencial para o Soft Delete funcionar na UI
        String sql = "SELECT * FROM CategoriaTarefa WHERE Ativo = 1";

        try (Connection conn = BaseDados.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql); 
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                categorias.add(mapResultSetToCategoria(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return categorias;
    }
    
    // ---- INSERT ----
    public long insertCategoriaTarefa(CategoriaTarefa ct) {
        // Definimos explicitamente Ativo como 1 (true) na criação
        String SQL = "INSERT INTO CategoriaTarefa (Nome, Ativo) VALUES (?, 1)";
        long generatedId = -1;
        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, ct.getNome());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getLong(1);
                        ct.setIdCategoriaTarefa((int) generatedId);
                    }
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return generatedId;
    }
    
    // ---- UPDATE ----
    public boolean updateCategoriaTarefa(CategoriaTarefa ct) {
        // Incluímos o campo Ativo no update caso queira reativar/desativar por aqui
        String SQL = "UPDATE CategoriaTarefa SET Nome = ?, Ativo = ? WHERE IdTarefa = ?";
        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, ct.getNome());
            stmt.setBoolean(2, ct.isAtivo());
            stmt.setLong(3, ct.getIdCategoriaTarefa());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    // ---- SOFT DELETE (Método novo e específico) ----
    public boolean deleteCategoriaTarefa(long id) {
        String SQL = "UPDATE CategoriaTarefa SET Ativo = 0 WHERE IdTarefa = ?";
        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
}