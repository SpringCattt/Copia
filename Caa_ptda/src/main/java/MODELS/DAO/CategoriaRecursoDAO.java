package MODELS.DAO;

import MODELS.DAO.BaseDados;
import MODELS.CLASS.CategoriaRecurso;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRecursoDAO {

    // Método auxiliar para mapear o ResultSet para o Objeto de Categoria
    private CategoriaRecurso mapResultSetToCategoria(ResultSet rs) throws SQLException {
        CategoriaRecurso cr = new CategoriaRecurso();
        cr.setIdCategoriaRecurso(rs.getInt("IdCategoria"));
        cr.setNome(rs.getString("Nome"));
        cr.setAtivo(rs.getBoolean("Ativo"));
        return cr;
    }

    // ---- SELECT (one) ----
    public CategoriaRecurso getCategoriaRecursoById(long id) {
        String sql = "SELECT * FROM CategoriaRecurso WHERE IdCategoria = ?";
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

    // ---- SELECT (Todas as categorias ativas) ----
    public List<CategoriaRecurso> getAllCategoriasRecurso() {
        List<CategoriaRecurso> categorias = new ArrayList<>();
        // Filtro de Soft Delete aplicado aqui
        String sql = "SELECT * FROM CategoriaRecurso WHERE Ativo = 1";

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
    public long insertCategoriaRecurso(CategoriaRecurso cr) {
        // Inserção garantindo que a categoria nasce como Ativa
        String SQL = "INSERT INTO CategoriaRecurso (Nome, Ativo) VALUES (?, 1)";
        long generatedId = -1;
        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cr.getNome());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getLong(1);
                        cr.setIdCategoria(generatedId);
                    }
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return generatedId;
    }
    
    // ---- UPDATE ----
    public boolean updateCategoriaRecurso(CategoriaRecurso cr) {
        // Atualização inclui o estado Ativo para permitir reativação via código
        String SQL = "UPDATE CategoriaRecurso SET Nome = ?, Ativo = ? WHERE IdCategoria = ?";
        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, cr.getNome());
            stmt.setBoolean(2, cr.isAtivo());
            stmt.setLong(3, cr.getIdCategoriaRecurso());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    // ---- SOFT DELETE (Desativa a categoria sem apagar os dados) ----
    public boolean deleteCategoriaRecurso(long id) {
        String SQL = "UPDATE CategoriaRecurso SET Ativo = 0 WHERE IdCategoria = ?";
        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
}