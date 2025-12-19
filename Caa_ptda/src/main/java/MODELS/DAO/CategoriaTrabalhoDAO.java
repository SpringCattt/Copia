package MODELS.DAO;

import MODELS.CLASS.CategoriaTrabalho;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaTrabalhoDAO {

    // ---- SELECT (one) ----
    public CategoriaTrabalho getCategoriaTrabalhoById(long id) {
        String sql = "SELECT * FROM CategoriaTrabalho WHERE IdCategoria = ?";
        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                CategoriaTrabalho ct = new CategoriaTrabalho();
                ct.setIdCategoria(rs.getInt("IdCategoria"));
                ct.setNome(rs.getString("Nome"));
                return ct;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ---- SELECT (all) ----
    public List<CategoriaTrabalho> getCategoriasTrabalho() {
        List<CategoriaTrabalho> categorias = new ArrayList<>();
        String sql = "SELECT * FROM CategoriaTrabalho";

        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CategoriaTrabalho ct = new CategoriaTrabalho();
                ct.setIdCategoria(rs.getInt("IdCategoria"));
                ct.setNome(rs.getString("Nome"));
                categorias.add(ct);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categorias;
    }

    // ---- INSERT ----
    public long insertCategoriaTrabalho(CategoriaTrabalho ct) {
        String SQL = "INSERT INTO CategoriaTrabalho (Nome) VALUES (?)";
        int generatedId = -1;
        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, ct.getNome());
            System.out.printf("%s", ct.getNome());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1);
                        ct.setIdCategoria(generatedId);
                    }
                }
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }

    // ---- UPDATE ---- (Exemplo)
    public boolean updateCategoriaTrabalho(CategoriaTrabalho ct) {
        String SQL = "UPDATE CategoriaTrabalho SET Nome = ? WHERE IdCategoria = ?";
        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, ct.getNome());
            stmt.setLong(2, ct.getIdCategoria());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
