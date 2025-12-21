package MODELS.DAO;

import MODELS.CLASS.Evento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

public class EventoDAO {

    private Evento mapResultSetToEvento(ResultSet rs) throws SQLException {
        Evento e = new Evento();
        e.setIdEvento(rs.getInt("IdEvento"));
        e.setNome(rs.getString("Nome"));
        e.setData(rs.getDate("Data"));
        e.setDescricao(rs.getString("Descricao"));
        e.setResponsavel(rs.getInt("Responsavel"));
        e.setSala(rs.getInt("Sala"));

        // Agora tratamos Hora e Duracao como campos distintos
        e.setHora(rs.getTime("Hora"));
        e.setDuracao(rs.getTime("Duracao")); // <--- Correção aqui

        e.setEstado(rs.getBoolean("Estado"));
        e.setDecorreu(rs.getBoolean("Decorreu")); // Continua como boolean

        try {
            e.setAtivo(rs.getBoolean("Ativo"));
        } catch (SQLException ex) {
            e.setAtivo(true);
        }
        return e;
    }

    private Timestamp combinarFechaHora(java.util.Date date, java.util.Date time) {
        if (date == null || time == null) {
            return null;
        }

        Calendar calDate = Calendar.getInstance();
        calDate.setTime(date);

        Calendar calTime = Calendar.getInstance();
        calTime.setTime(time);

        calDate.set(Calendar.HOUR_OF_DAY, calTime.get(Calendar.HOUR_OF_DAY));
        calDate.set(Calendar.MINUTE, calTime.get(Calendar.MINUTE));
        calDate.set(Calendar.SECOND, 0);
        calDate.set(Calendar.MILLISECOND, 0);

        return new Timestamp(calDate.getTimeInMillis());
    }

    public int insertEvento(Evento e) {
        // Adicionada a coluna Duracao na SQL
        String sql = "INSERT INTO Evento (Nome, Data, Descricao, Responsavel, Sala, Estado, Ativo, Hora, Decorreu, Duracao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, e.getNome());
            stmt.setDate(2, new java.sql.Date(e.getData().getTime()));
            stmt.setString(3, e.getDescricao());
            stmt.setInt(4, e.getResponsavel());
            stmt.setInt(5, e.getSala());
            stmt.setBoolean(6, e.isEstado());
            stmt.setBoolean(7, true);

            // Hora (usando a sua função de combinar data e hora)
            if (e.getHora() != null) {
                stmt.setTimestamp(8, combinarFechaHora(e.getData(), e.getHora()));
            } else {
                stmt.setNull(8, java.sql.Types.TIMESTAMP);
            }

            stmt.setBoolean(9, e.isDecorreu()); // O seu boolean

            // Duracao (Tipo TIME)
            if (e.getDuracao() != null) {
                stmt.setTime(10, new java.sql.Time(e.getDuracao().getTime()));
            } else {
                stmt.setNull(10, java.sql.Types.TIME);
            }

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public boolean updateEvento(Evento e) {
        String sql = "UPDATE Evento SET Nome=?, Data=?, Descricao=?, Responsavel=?, Sala=?, Hora=?, Estado=?, Ativo=?, Decorreu=?, Duracao=? WHERE IdEvento=?";

        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, e.getNome());
            stmt.setDate(2, new java.sql.Date(e.getData().getTime()));
            stmt.setString(3, e.getDescricao());
            stmt.setInt(4, e.getResponsavel());
            stmt.setInt(5, e.getSala());

            if (e.getHora() != null) {
                stmt.setTimestamp(6, combinarFechaHora(e.getData(), e.getHora()));
            } else {
                stmt.setNull(6, java.sql.Types.TIMESTAMP);
            }

            stmt.setBoolean(7, e.isEstado());
            stmt.setBoolean(8, e.isAtivo());
            stmt.setBoolean(9, e.isDecorreu());

            // Novo campo Duracao
            if (e.getDuracao() != null) {
                stmt.setTime(10, new java.sql.Time(e.getDuracao().getTime()));
            } else {
                stmt.setNull(10, java.sql.Types.TIME);
            }

            stmt.setInt(11, e.getIdEvento());
            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteEvento(int id) {
        String sql = "UPDATE Evento SET Ativo = 0 WHERE IdEvento = ?";
        try {
            Connection conn = BaseDados.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                return stmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Evento> getAllEventos() {
        List<Evento> eventos = new ArrayList<>();
        String sql = "SELECT * FROM Evento WHERE Ativo = 1";
        try {
            Connection conn = BaseDados.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    eventos.add(mapResultSetToEvento(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventos;
    }

    public List<Evento> getEventosAtivosNaoDecorridos() {
        List<Evento> eventos = new ArrayList<>();
        String sql = "SELECT * FROM Evento WHERE Ativo = 1 AND Estado = 1 AND Decorreu = 0";

        try (Connection conn = BaseDados.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                eventos.add(mapResultSetToEvento(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventos;
    }

    public Evento getEventoById(int id) {
        String sql = "SELECT * FROM Evento WHERE IdEvento = ?";
        try {
            Connection conn = BaseDados.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return mapResultSetToEvento(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Evento> buscarEventos(String termo) {
        List<Evento> lista = new ArrayList<>();
        String sql = "SELECT * FROM Evento WHERE (LOWER(Nome) LIKE LOWER(?) OR LOWER(Descricao) LIKE LOWER(?)) AND Ativo = 1";
        try {
            Connection conn = BaseDados.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                String pattern = "%" + termo + "%";
                stmt.setString(1, pattern);
                stmt.setString(2, pattern);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    lista.add(mapResultSetToEvento(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

}