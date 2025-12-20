package MODELS.DAO;

import MODELS.CLASS.Evento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO {

    // --- MAPPER ---
    private Evento mapResultSetToEvento(ResultSet rs) throws SQLException {
        Evento e = new Evento();
        e.setIdEvento(rs.getInt("IdEvento"));
        e.setNome(rs.getString("Nome"));
        e.setData(rs.getDate("Data"));
        e.setDescricao(rs.getString("Descricao"));
        e.setResponsavel(rs.getInt("Responsavel"));
        e.setSala(rs.getInt("Sala"));
        e.setHora(rs.getTime("Hora")); // Usamos getTime para horas
        e.setEstado(rs.getBoolean("Estado"));
        e.setCancelado(rs.getBoolean("Cancelado"));
        return e;
    }

    // --- INSERT ---
    public int insertEvento(Evento e) {
        String sql = "INSERT INTO Evento (Nome, Data, Descricao, Responsavel, Sala, Estado, Cancelado, Hora) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, e.getNome());
            // CORRECCIÓN DE FECHA:
            stmt.setDate(2, new java.sql.Date(e.getData().getTime())); 
            stmt.setString(3, e.getDescricao());
            stmt.setInt(4, e.getResponsavel());
            stmt.setInt(5, e.getSala());
            stmt.setBoolean(6, e.isEstado());
            stmt.setBoolean(7, e.isCancelado());
            // CORRECCIÓN DE HORA:
            if (e.getHora() != null) {
                stmt.setTime(8, new java.sql.Time(e.getHora().getTime()));
            } else {
                stmt.setTime(8, null);
            }

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    // --- UPDATE ---
    public boolean updateEvento(Evento e) {
        String sql = "UPDATE Evento SET Nome=?, Data=?, Descricao=?, Responsavel=?, Sala=?, Hora=?, Estado=?, Cancelado=? WHERE IdEvento=?";
        
        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, e.getNome());
            stmt.setDate(2, new java.sql.Date(e.getData().getTime()));
            stmt.setString(3, e.getDescricao());
            stmt.setInt(4, e.getResponsavel());
            stmt.setInt(5, e.getSala());
            if (e.getHora() != null) {
                stmt.setTime(6, new java.sql.Time(e.getHora().getTime()));
            } else {
                stmt.setTime(6, null);
            }
            stmt.setBoolean(7, e.isEstado());
            stmt.setBoolean(8, e.isCancelado());
            stmt.setInt(9, e.getIdEvento());

            return stmt.executeUpdate() > 0;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // --- CANCELAR ---
    public boolean cancelarEvento(int id) {
        String sql = "UPDATE Evento SET Cancelado = 1 WHERE IdEvento = ?";
        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // --- SELECT ALL ---
    public List<Evento> getAllEventos() {
        List<Evento> eventos = new ArrayList<>();
        String sql = "SELECT * FROM Evento WHERE Cancelado = 0"; 

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

    // --- SELECT BY ID ---
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
    
    // --- BUSCAR ---
    public List<Evento> buscarEventos(String termo) {
        List<Evento> lista = new ArrayList<>();
        String sql = "SELECT * FROM Evento WHERE (LOWER(Nome) LIKE LOWER(?) OR LOWER(Descricao) LIKE LOWER(?)) AND Cancelado = 0";
        
        try (Connection conn = BaseDados.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            String pattern = "%" + termo + "%";
            stmt.setString(1, pattern);
            stmt.setString(2, pattern);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                lista.add(mapResultSetToEvento(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}