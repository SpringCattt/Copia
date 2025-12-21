package MODELS.DAO;

import MODELS.CLASS.EventoRecurso;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventoRecursoDAO {

    // ---- SELECT (Lista de IDs de Recursos por Evento) ----
    public List<Integer> getRecursosIdByEventoId(int idEvento) {
        List<Integer> recursosIds = new ArrayList<>();
        // Selecionamos apenas o IdRecurso
        String sql = "SELECT IdRecurso FROM EventoRecurso WHERE IdEvento = ?";

        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEvento);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                recursosIds.add(rs.getInt("IdRecurso"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recursosIds;
    }

    // ---- CHECK/EXISTS ----
    public boolean existsEventoRecurso(int idEvento, int idRecurso) {
        String sql = "SELECT 1 FROM EventoRecurso WHERE IdEvento = ? AND IdRecurso = ?";
        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEvento);
            stmt.setInt(2, idRecurso);
            ResultSet rs = stmt.executeQuery();

            // Se rs.next() for true, a linha existe
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ---- INSERT ----
    public boolean insertEventoRecurso(int idEvento, int idRecurso) {
        String SQL = "INSERT INTO EventoRecurso (IdEvento, IdRecurso) VALUES (?, ?)";

        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setInt(1, idEvento);
            stmt.setInt(2, idRecurso);

            // A inserção é bem-sucedida se afetar 1 linha
            return stmt.executeUpdate() == 1;

        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("ERRO: A ligação Evento-Recurso já existe ou chave(s) estrangeira(s) inválida(s).");
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ---- DELETE ----
    public boolean deleteEventoRecurso(int idEvento, int idRecurso) {
        String SQL = "DELETE FROM EventoRecurso WHERE IdEvento = ? AND IdRecurso = ?";

        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setInt(1, idEvento);
            stmt.setInt(2, idRecurso);

            // Retorna true se 1 ou mais linhas foram afetadas (removidas)
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<EventoRecurso> getEventoRecursosNaoConsumiveis() {
        List<EventoRecurso> lista = new ArrayList<>();

        // O JOIN garante que só trazemos recursos que existem na tabela NaoConsumiveis
        String sql = "SELECT er.IdEvento, er.IdRecurso "
                + "FROM EventoRecurso er "
                + "INNER JOIN NaoConsumiveis nc ON er.IdRecurso = nc.IdRecurso";

        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EventoRecurso er = new EventoRecurso();
                er.setIdEvento(rs.getInt("IdEvento"));
                er.setIdRecurso(rs.getInt("IdRecurso"));

                lista.add(er);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar Eventos-Recursos Não Consumíveis: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }
}
