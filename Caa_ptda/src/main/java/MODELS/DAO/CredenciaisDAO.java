package MODELS.DAO;

import MODELS.CLASS.Credenciais;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CredenciaisDAO {

    public Credenciais getCredenciaisById(int idTrabalhador) {
        String sql = "SELECT * FROM Credenciais WHERE IdTrabalhador = ?";

        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTrabalhador);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Credenciais c = new Credenciais();
                c.setIdTrabalhador(idTrabalhador);
                c.setEmail(rs.getString("Email"));
                c.setPassword(rs.getString("Password"));
                return c;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean existeEmail(String email) {
        String SQL = "SELECT COUNT(*) FROM Credenciais WHERE Email = ?";

        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Se o contador for maior que 0, o email existe
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean existeEmailIgnorandoId(String email, int idIgnorar) {
        String sql = "SELECT IdTrabalhador FROM Credenciais WHERE Email = ? AND IdTrabalhador != ?";
        try (java.sql.Connection conn = BaseDados.getConnection();
             java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setInt(2, idIgnorar);
            java.sql.ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Integer validarLogin(String email, String password) {
        String SQL = "SELECT IdTrabalhador FROM Credenciais WHERE Email = ? AND Password = ?";

        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Retorna o ID encontrado
                    return rs.getInt("IdTrabalhador");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Se não encontrar ou der erro, retorna null
        return null;
    }

    public boolean insertCredenciais(Credenciais credenciais) {
        String SQL = "INSERT INTO Credenciais (IdTrabalhador, Email, Password) VALUES (?, ?, ?)";

        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setInt(1, credenciais.getIdTrabalhador()); // FK obrigatória
            stmt.setString(2, credenciais.getEmail());
            stmt.setString(3, credenciais.getPassword());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

   public boolean updateCredenciais(int idTrabalhador, String novoEmail, String novaPassword, boolean atualizarSenha) {
        String sql;
        if (atualizarSenha) {
            sql = "UPDATE Credenciais SET Email = ?, Password = ? WHERE IdTrabalhador = ?";
        } else {
            sql = "UPDATE Credenciais SET Email = ? WHERE IdTrabalhador = ?";
        }

        try (java.sql.Connection conn = BaseDados.getConnection();
             java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novoEmail);
            if (atualizarSenha) {
                stmt.setString(2, novaPassword);
                stmt.setInt(3, idTrabalhador);
            } else {
                stmt.setInt(2, idTrabalhador);
            }
            return stmt.executeUpdate() > 0;
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
   
   public MODELS.CLASS.Credenciais getCredenciaisPorIdTrabalhador(int idTrabalhador) {
        String sql = "SELECT * FROM Credenciais WHERE IdTrabalhador = ?";
        try (java.sql.Connection conn = BaseDados.getConnection();
             java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idTrabalhador);
            java.sql.ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                MODELS.CLASS.Credenciais c = new MODELS.CLASS.Credenciais();
                c.setIdTrabalhador(rs.getInt("IdTrabalhador"));
                c.setEmail(rs.getString("Email"));
                c.setPassword(rs.getString("Password"));
                return c;
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
   
    public Credenciais getCredenciaisByEmail(String email) {
        String sql = "SELECT * FROM Credenciais WHERE Email = ?";

        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Credenciais c = new Credenciais();
                    c.setIdTrabalhador(rs.getInt("IdTrabalhador"));
                    c.setEmail(rs.getString("Email"));
                    c.setPassword(rs.getString("Password"));
                    return c;
                }
            }
        } catch (SQLException e) {
            // Reportar falhas de SQL no relatório de testes (Ponto 3)
            e.printStackTrace();
        }
        return null;
    }
}
