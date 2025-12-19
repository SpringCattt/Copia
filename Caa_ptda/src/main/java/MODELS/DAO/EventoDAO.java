package MODELS.DAO;
import MODELS.CLASS.Evento; 
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO {

    private Evento mapResultSetToEvento(ResultSet rs) throws SQLException {
        Evento e = new Evento();
        e.setIdEvento(rs.getInt("IdEvento"));
        e.setNome(rs.getString("Nome"));
        e.setData(rs.getDate("Data"));
        e.setDescricao(rs.getString("Descricao"));
        e.setResponsavel(rs.getInt("Responsavel"));
        e.setSala(rs.getInt("Sala"));
        e.setHora(rs.getDate("Hora"));
        e.setEstado(rs.getBoolean("Estado"));
        e.setCancelado(rs.getBoolean("Cancelado"));
        e.setAtivo(rs.getBoolean("Ativo"));
        return e;
    }

    // ---- SELECT (um evento) ----
    public Evento getEventoById(int id) {
        String sql = "SELECT * FROM Evento WHERE IdEvento = ?";
        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToEvento(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isEventoAtivo(int id) {
        String sql = "SELECT Ativo FROM Evento WHERE IdEvento = ?";

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

    // ---- SELECT (todos os eventos) ----
    public List<Evento> getAllEventos() {
        List<Evento> eventos = new ArrayList<>();
        String sql = "SELECT * FROM Evento WHERE Ativo = 1";

        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                eventos.add(mapResultSetToEvento(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventos;
    }

    // ---- INSERT ----
    public long insertEvento(Evento evento) {
        String SQL = "INSERT INTO Evento (Nome, Data, Descricao, Responsavel, Sala, Hora, Estado, Cancelado, Ativo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        long generatedId = -1;

        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, evento.getNome());
            stmt.setDate(2, (Date) evento.getData()); // Assumindo que getData() retorna java.sql.Date
            stmt.setString(3, evento.getDescricao());
            stmt.setInt(4, evento.getResponsavel());
            stmt.setInt(5, evento.getSala());
            stmt.setDate(6, (Date) evento.getHora());
            stmt.setBoolean(7, evento.isEstado());
            stmt.setBoolean(8, evento.isCancelado());
            stmt.setBoolean(9, evento.isAtivo());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getLong(1);
                        evento.setIdEvento((int) generatedId); // Define o ID no objeto Java
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }

    // ---- UPDATE ----
    public boolean updateEvento(Evento evento) {
        String SQL = "UPDATE Evento SET Nome = ?, Data = ?, Descricao = ?, Responsavel = ?, Sala = ?, Hora = ?, Estado = ?, Cancelado = ?, Ativo = ? WHERE IdEvento = ?";

        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {

            stmt.setString(1, evento.getNome());
            stmt.setDate(2, (Date) evento.getData()); // Assumindo que getData() retorna java.sql.Date
            stmt.setString(3, evento.getDescricao());
            stmt.setInt(4, evento.getResponsavel());
            stmt.setInt(5, evento.getSala());
            stmt.setDate(6, (Date) evento.getHora());
            stmt.setBoolean(7, evento.isEstado());
            stmt.setBoolean(8, evento.isCancelado());
            stmt.setBoolean(9, evento.isAtivo());
            stmt.setInt(10, evento.getIdEvento());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ---- SOFT DELETE (Desativa a categoria sem apagar os dados) ----
    public boolean deleteEvento(long id) {
        String SQL = "UPDATE Evento SET Ativo = 0 WHERE IdEvento = ?";
        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
}