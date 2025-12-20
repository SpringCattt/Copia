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
        String sql = "SELECT r.*, nc.PrecoAluguer FROM Recurso r " +
                     "INNER JOIN NaoConsumiveis nc ON r.IdRecurso = nc.IdRecurso";

        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapResultSetToNaoConsumivel(rs));
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
}