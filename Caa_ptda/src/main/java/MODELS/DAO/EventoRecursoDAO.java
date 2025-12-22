package MODELS.DAO;

import MODELS.CLASS.Evento;
import MODELS.CLASS.EventoRecurso;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventoRecursoDAO {

    // MÉTODO AUXILIAR para evitar repetição de código (DRY)
    private EventoRecurso mapResultSetToEventoRecurso(ResultSet rs) throws SQLException {
        EventoRecurso er = new EventoRecurso();
        er.setIdEvento(rs.getInt("IdEvento"));
        er.setIdRecurso(rs.getInt("IdRecurso"));
        // Agora mapeamos a quantidade que foi adicionada à BD
        er.setQuantidade(rs.getInt("Quantidade"));
        return er;
    }

    public List<EventoRecurso> getRecursosByEventoId(int idEvento) {
        List<EventoRecurso> lista = new ArrayList<>();
        String sql = "SELECT * FROM EventoRecurso WHERE IdEvento = ?";
        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idEvento);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapResultSetToEventoRecurso(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean existsEventoRecurso(int idEvento, int idRecurso) {
        String sql = "SELECT 1 FROM EventoRecurso WHERE IdEvento = ? AND IdRecurso = ?";
        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idEvento);
            stmt.setInt(2, idRecurso);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertEventoRecurso(int idEvento, int idRecurso, int quantidade) {
        String sql = "INSERT INTO EventoRecurso (IdEvento, IdRecurso, Quantidade) VALUES (?, ?, ?)";
        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idEvento);
            stmt.setInt(2, idRecurso);
            stmt.setInt(3, quantidade);
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateQuantidadeRecurso(int idEvento, int idRecurso, int novaQuantidade) {
        String sql = "UPDATE EventoRecurso SET Quantidade = ? WHERE IdEvento = ? AND IdRecurso = ?";
        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, novaQuantidade);
            stmt.setInt(2, idEvento);
            stmt.setInt(3, idRecurso);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteEventoRecurso(int idEvento, int idRecurso) {
        String SQL = "DELETE FROM EventoRecurso WHERE IdEvento = ? AND IdRecurso = ?";
        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, idEvento);
            stmt.setInt(2, idRecurso);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<EventoRecurso> getEventoRecursosNaoConsumiveis() {
        List<EventoRecurso> lista = new ArrayList<>();
        // Adicionado er.Quantidade no SELECT e uso do Mapper
        String sql = "SELECT er.* FROM EventoRecurso er "
                + "INNER JOIN NaoConsumiveis nc ON er.IdRecurso = nc.IdRecurso";
        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapResultSetToEventoRecurso(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<EventoRecurso> getEventoRecursosConsumiveis() {
        List<EventoRecurso> lista = new ArrayList<>();
        // Alterado nc (NaoConsumiveis) para c (Consumiveis)
        String sql = "SELECT er.* FROM EventoRecurso er "
                + "INNER JOIN Consumiveis c ON er.IdRecurso = c.IdRecurso";

        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapResultSetToEventoRecurso(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public int getQuantidadeEmUso(int idRecurso, java.sql.Date data, java.sql.Time horaInicio, java.sql.Time duracao, int idEventoNovo) {
        // IFNULL(SUM..., 0) para evitar problemas com valores nulos
        String sql = "SELECT IFNULL(SUM(er.Quantidade), 0) FROM EventoRecurso er "
                + "JOIN Evento e ON er.IdEvento = e.IdEvento "
                + "WHERE er.IdRecurso = ? AND e.Data = ? AND e.Ativo = 1 AND e.Cancelado = 0 AND e.IdEvento != ? "
                + "AND SUBTIME(TIME(e.Hora), '01:00:00') < ADDTIME(ADDTIME(?, IFNULL(?, '00:00:00')), '01:00:00') "
                + "AND ADDTIME(ADDTIME(TIME(e.Hora), IFNULL(e.Duracao, '00:00:00')), '01:00:00') > SUBTIME(?, '01:00:00')";

        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idRecurso);
            stmt.setDate(2, data);
            stmt.setInt(3, idEventoNovo);
            stmt.setTime(4, horaInicio);
            stmt.setTime(5, duracao);
            stmt.setTime(6, horaInicio);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getQuantidadeNoEvento(int idEvento, int idRecurso) {
        String sql = "SELECT Quantidade FROM EventoRecurso WHERE IdEvento = ? AND IdRecurso = ?";
        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idEvento);
            stmt.setInt(2, idRecurso);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("Quantidade");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<EventoRecurso> getConsumiveisDeEventosAlugados() {
        List<EventoRecurso> lista = new ArrayList<>();
        
        String sql = "SELECT er.* FROM EventoRecurso er "
                + "INNER JOIN Consumiveis c ON er.IdRecurso = c.IdRecurso "
                + "INNER JOIN Evento e ON er.IdEvento = e.IdEvento "
                + "WHERE e.Alugado = 1 AND e.Ativo = 1";

        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapResultSetToEventoRecurso(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
