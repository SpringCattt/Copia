package MODELS.DAO;
import MODELS.CLASS.Lugar;
import MODELS.CLASS.NaoConsumivel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NaoConsumiveisDAO {

    private NaoConsumivel mapResultSetToNaoConsumivel(ResultSet rs) throws SQLException {
        NaoConsumivel nc = new NaoConsumivel();
        nc.setIdRecurso(rs.getInt("IdRecurso"));
        nc.setNome(rs.getString("Nome"));
        nc.setPreco(rs.getDouble("Preco"));
        nc.setQuantidade(rs.getInt("Quantidade"));
        nc.setAtivo(rs.getBoolean("Ativo")); 
        nc.setPrecoAluguer(rs.getDouble("PrecoAluguer"));
        return nc;
    }
    
    public NaoConsumivel getNaoConsumivelById(int id) {
        String sql = "SELECT r.*, nc.PrecoAluguer FROM Recurso r " +
                     "INNER JOIN NaoConsumiveis nc ON r.IdRecurso = nc.IdRecurso " +
                     "WHERE r.IdRecurso = ?";

        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToNaoConsumivel(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<NaoConsumivel> getAllNaoConsumiveis() {
        List<NaoConsumivel> lista = new ArrayList<>();
        // Adicionado o filtro "WHERE r.Ativo = 1"
        String sql = "SELECT r.*, nc.PrecoAluguer FROM Recurso r " +
                     "INNER JOIN NaoConsumiveis nc ON r.IdRecurso = nc.IdRecurso " +
                     "WHERE r.Ativo = 1"; 

        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                NaoConsumivel nc = new NaoConsumivel();
                nc.setIdRecurso(rs.getInt("IdRecurso"));
                nc.setNome(rs.getString("Nome"));
                nc.setPreco(rs.getDouble("Preco"));
                nc.setQuantidade(rs.getInt("Quantidade"));
                nc.setAtivo(rs.getBoolean("Ativo"));
                nc.setPrecoAluguer(rs.getDouble("PrecoAluguer"));
                lista.add(nc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean insertNaoConsumivel(NaoConsumivel nc) {
        String sqlFilha = "INSERT INTO NaoConsumiveis (IdRecurso, PrecoAluguer) VALUES (?, ?)";

        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlFilha)) {
            
            stmt.setInt(1, nc.getIdRecurso());
            stmt.setDouble(2, nc.getPrecoAluguer());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateNaoConsumivel(NaoConsumivel nc) {
        String sql = "UPDATE NaoConsumiveis SET PrecoAluguer = ? WHERE IdRecurso = ?";
        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, nc.getPrecoAluguer());
            stmt.setInt(2, nc.getIdRecurso());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public void eliminarRegistoFilho(int id) {
        String sql = "DELETE FROM NaoConsumiveis WHERE IdRecurso = ?";
        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}