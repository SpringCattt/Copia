package MODELS.DAO;

import MODELS.CLASS.Tarefa; // Assumindo esta classe existe
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {

    private Tarefa mapResultSetToTarefa(ResultSet rs) throws SQLException {
        Tarefa t = new Tarefa();
        t.setIdTarefa(rs.getInt("IdTarefa"));
        t.setTitulo(rs.getString("Titulo"));
        t.setDescricao(rs.getString("Descricao"));
        t.setIdTrabalhador(rs.getInt("TrabalhadorId")); // Chave estrangeira
        t.setEvento(rs.getInt("Evento")); // Chave estrangeira
        t.setEstado(rs.getBoolean("Estado")); // Mapeado para boolean
        t.setAtivo(rs.getBoolean("Ativo"));
        return t;
    }

    // ---- SELECT (uma tarefa) ----
    public Tarefa getTarefaById(int id) {
        String sql = "SELECT * FROM Tarefa WHERE IdTarefa = ?";
        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToTarefa(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Tarefa> getTarefasByTrabalhador(int idTrabalhador) {
        List<Tarefa> tarefas = new ArrayList<>();
        // Filtra apenas tarefas ativas associadas ao ID do trabalhador
        String sql = "SELECT * FROM Tarefa WHERE TrabalhadorId = ? AND Ativo = 1";

        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTrabalhador);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Adiciona cada tarefa encontrada à lista
                    tarefas.add(mapResultSetToTarefa(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tarefas;
    }

    public List<Tarefa> getTarefasConcluidasByTrabalhador(int idTrabalhador) {
        List<Tarefa> tarefas = new ArrayList<>();
        // Adicionada a condição Estado = 1 (Concluídas)
        String sql = "SELECT * FROM Tarefa WHERE TrabalhadorId = ? AND Ativo = 1 AND Estado = 1";

        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTrabalhador);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    tarefas.add(mapResultSetToTarefa(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tarefas;
    }
    
    public List<Tarefa> getTarefasPendentesByTrabalhador(int idTrabalhador) {
        List<Tarefa> tarefas = new ArrayList<>();
        
        String sql = "SELECT * FROM Tarefa WHERE TrabalhadorId = ? AND Ativo = 1 AND Estado = 0";

        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTrabalhador);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    tarefas.add(mapResultSetToTarefa(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tarefas;
    }

    public boolean isTarefaAtivo(int id) {
        String sql = "SELECT Ativo FROM Tarefa WHERE IdTarefa = ?";

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

    // ---- SELECT (todas as tarefas) ----
    public List<Tarefa> getAllTarefas() {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM Tarefa WHERE Ativo = 1";

        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                tarefas.add(mapResultSetToTarefa(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tarefas;
    }

    // ---- INSERT ----
    public long insertTarefa(Tarefa tarefa) {
        String SQL = "INSERT INTO Tarefa (Titulo, Descricao, TrabalhadorId, Evento, Estado, Ativo) VALUES (?, ?, ?, ?, ?, ?)";
        long generatedId = -1;

        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getDescricao());
            stmt.setInt(3, tarefa.getIdTrabalhador());
            stmt.setInt(4, tarefa.getEvento());
            stmt.setBoolean(5, tarefa.isEstado());
            stmt.setBoolean(6, tarefa.isAtivo());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getLong(1);
                        tarefa.setIdTarefa((int) generatedId);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }

    // ---- UPDATE ----
    public boolean updateTarefa(Tarefa tarefa) {
        String SQL = "UPDATE Tarefa SET Titulo = ?, Descricao = ?, TrabalhadorId = ?, Evento = ?, Estado = ?, Ativo = ? WHERE IdTarefa = ?";

        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getDescricao());
            stmt.setInt(3, tarefa.getIdTrabalhador());
            stmt.setInt(4, tarefa.getEvento());
            stmt.setBoolean(5, tarefa.isEstado());
            stmt.setBoolean(7, tarefa.isAtivo());
            stmt.setInt(8, tarefa.getIdTarefa());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ---- SOFT DELETE (Desativa a categoria sem apagar os dados) ----
    public boolean deleteTarefa(long id) {
        String SQL = "UPDATE Tarefa SET Ativo = 0 WHERE IdTarefa = ?";
        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
