package MODELS.DAO;
import MODELS.CLASS.Consumivel; 
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsumiveisDAO {

    private Consumivel mapResultSetToConsumivel(ResultSet rs) throws SQLException {
        Consumivel c = new Consumivel();
        // Dados da tabela Recurso (Pai)
        c.setIdRecurso(rs.getInt("IdRecurso"));
        c.setNome(rs.getString("Nome"));
        c.setPreco(rs.getDouble("Preco"));
        c.setQuantidade(rs.getInt("Quantidade"));
        c.setAtivo(rs.getBoolean("Ativo")); 
        c.setDataValidade(rs.getDate("Data_Validade")); 
        return c;
    }

    public Consumivel getConsumivelById(int id) {
        // JOIN obrigatório para trazer o Nome, Preco, etc. da tabela Recurso
        String sql = "SELECT r.*, c.Data_Validade FROM Recurso r " +
                     "INNER JOIN Consumiveis c ON r.IdRecurso = c.IdRecurso " +
                     "WHERE r.IdRecurso = ?";

        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToConsumivel(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Consumivel> getAllConsumiveis() {
        List<Consumivel> lista = new ArrayList<>();
        // Adicionado o filtro "WHERE r.Ativo = 1"
        String sql = "SELECT r.*, c.Data_Validade FROM Recurso r " +
                     "INNER JOIN Consumiveis c ON r.IdRecurso = c.IdRecurso " +
                     "WHERE r.Ativo = 1"; 

        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Consumivel c = new Consumivel();
                c.setIdRecurso(rs.getInt("IdRecurso"));
                c.setNome(rs.getString("Nome"));
                c.setPreco(rs.getDouble("Preco"));
                c.setQuantidade(rs.getInt("Quantidade"));
                c.setAtivo(rs.getBoolean("Ativo"));
                c.setDataValidade(rs.getDate("Data_Validade"));
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean insertConsumivel(Consumivel c) {
        String sqlFilha = "INSERT INTO Consumiveis (IdRecurso, Data_Validade) VALUES (?, ?)";
        
        // Como Consumivel DEPENDE de Recurso, o IdRecurso já deve existir 
        // ou ser criado via RecursoDAO antes de chamar este método.
        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlFilha)) {
            
            stmt.setInt(1, c.getIdRecurso());
            stmt.setDate(2, new java.sql.Date(c.getDataValidade().getTime()));
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateConsumivel(Consumivel c) {
        String sql = "UPDATE Consumiveis SET Data_Validade = ? WHERE IdRecurso = ?";
        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(c.getDataValidade().getTime()));
            stmt.setInt(2, c.getIdRecurso());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public void eliminarRegistoFilho(int id) {
        String sql = "DELETE FROM Consumiveis WHERE IdRecurso = ?";
        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}