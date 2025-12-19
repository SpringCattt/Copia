/*package TESTES;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import MODELS.CLASS.*;
import MODELS.DAO.BaseDados;
import MODELS.DAO.*;

public class TesteBD {

    // Instanciação dos DAOs
    private static CategoriaTrabalhoDAO ctDAO = new CategoriaTrabalhoDAO();
    private static EspacoDAO eDAO = new EspacoDAO();
    private static CategoriaRecursoDAO crDAO = new CategoriaRecursoDAO();
    private static CategoriaTarefaDAO catTarefaDAO = new CategoriaTarefaDAO();
    private static ClienteDAO cDAO = new ClienteDAO();
    private static TrabalhadorDAO tDAO = new TrabalhadorDAO();
    private static CredenciaisDAO credDAO = new CredenciaisDAO();
    private static SalaDAO sDAO = new SalaDAO();
    private static RecursoDAO rDAO = new RecursoDAO();
    private static EventoDAO evDAO = new EventoDAO();
    private static LugarDAO lDAO = new LugarDAO();
    private static TarefaDAO tarDAO = new TarefaDAO();
    private static EventoRecursoDAO erDAO = new EventoRecursoDAO();
    private static BilheteDAO bDAO = new BilheteDAO();

    public static void main(String[] args) {
        System.out.println("--- INÍCIO DOS TESTES DE INTEGRAÇÃO DAO ---");

        try (Connection conn = BaseDados.getConnection()) {
            System.out.println("Conexão com o Base de Dados estabelecida com sucesso.");

            // A execução sequencial garante que as FKs existam antes de serem referenciadas

            // Nível 0: Sem dependências
            long idCatTrabalho = testCategoriaTrabalhoDAO();
            long idEspaco = testEspacoDAO();
            long idCatRecurso = testCategoriaRecursoDAO();
            long idCatTarefa = testCategoriaTarefaDAO();
            long idCliente = testClienteDAO();

            // Nível 1: Depende apenas do Nível 0
            int idTrabalhador = testTrabalhadorDAO((int) idCatTrabalho);
            int idSala = testSalaDAO((int) idEspaco);
            int idRecurso = testRecursoDAO((int) idCatRecurso);

            // Nível 2: Depende do Nível 1
            testCredenciaisDAO(idTrabalhador);
            int idEvento = testEventoDAO(idTrabalhador, idSala);
            int idLugar = testLugarDAO(idSala);

            // Nível 3: Depende do Nível 2
            testTarefaDAO(idTrabalhador, idEvento, (int) idCatTarefa);
            testEventoRecursoDAO(idEvento, idRecurso);
            testBilheteDAO(idLugar, (int) idCliente, idEvento);

            System.out.println("\n--- TODOS OS TESTES CONCLUÍDOS ---");

        } catch (SQLException e) {
            System.err.println("Erro fatal na Conexão com o Base de Dados: Verifique as credenciais ou o estado do servidor.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erro durante a execução dos testes: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // =================================================================
    // MÉTODOS DE TESTE POR DAO (Implementação do CRUD simulado)
    // =================================================================

    // --- CategoriaTrabalho ---
    private static long testCategoriaTrabalhoDAO() {
        long generatedId = -1;
        System.out.println("\n--- Teste: CategoriaTrabalhoDAO (Nível 0) ---");
        try {
            CategoriaTrabalho ct = new CategoriaTrabalho();
            ct.setNome("Gestor de Projetos");
            generatedId = ctDAO.insertCategoriaTrabalho(ct);
            System.out.println("  INSERT CategoriaTrabalho: ID gerado -> " + generatedId);
            CategoriaTrabalho retrieved = ctDAO.getCategoriaTrabalhoById(generatedId);
            System.out.println("  SELECT CategoriaTrabalho: " + retrieved);
            retrieved.setNome("Coordenador Principal");
            boolean updated = ctDAO.updateCategoriaTrabalho(retrieved);
            System.out.println("  UPDATE CategoriaTrabalho: Sucesso? " + updated);
        } catch (Exception e) { System.err.println("  ERRO no CategoriaTrabalhoDAO: " + e.getMessage()); }
        return generatedId;
    }

    // --- Espaco ---
    private static long testEspacoDAO() {
        long generatedId = -1;
        System.out.println("\n--- Teste: EspacoDAO (Nível 0) ---");
        try {
            Espaco e = new Espaco();
            e.setNome("Area de Espetáculos");
            generatedId = eDAO.insertEspaco(e);
            System.out.println("  INSERT Espaco: ID gerado -> " + generatedId);
            Espaco retrieved = eDAO.getEspacoById((int) generatedId);
            System.out.println("  SELECT Espaco: " + retrieved);
        } catch (Exception e) { System.err.println("  ERRO no EspacoDAO: " + e.getMessage()); }
        return generatedId;
    }

    // --- CategoriaRecurso ---
    private static long testCategoriaRecursoDAO() {
        long generatedId = -1;
        System.out.println("\n--- Teste: CategoriaRecursoDAO (Nível 0) ---");
        try {
            // Nota: O DAO usa a classe Recurso.
            Recurso r = new Recurso();
            r.setNome("Tecnologia de Som");
            generatedId = crDAO.insertCategoriaRecurso(r);
            System.out.println("  INSERT CategoriaRecurso: ID gerado -> " + generatedId);
        } catch (Exception e) { System.err.println("  ERRO no CategoriaRecursoDAO: " + e.getMessage()); }
        return generatedId;
    }

    // --- CategoriaTarefa ---
    private static long testCategoriaTarefaDAO() {
        long generatedId = -1;
        System.out.println("\n--- Teste: CategoriaTarefaDAO (Nível 0) ---");
        try {
            CategoriaTarefa ct = new CategoriaTarefa();
            ct.setNome("Logística");
            generatedId = catTarefaDAO.insertCategoriaTarefa(ct);
            System.out.println("  INSERT CategoriaTarefa: ID gerado -> " + generatedId);
        } catch (Exception e) { System.err.println("  ERRO no CategoriaTarefaDAO: " + e.getMessage()); }
        return generatedId;
    }

    // --- Cliente ---
    private static long testClienteDAO() {
        long generatedId = -1;
        System.out.println("\n--- Teste: ClienteDAO (Nível 0) ---");
        try {
            Cliente c = new Cliente();
            c.setNome("Carlos Pereira");
            c.setEmail("carlos@client.pt");
            c.setNumeroTelefone(912345678);
            generatedId = cDAO.insertCliente(c);
            System.out.println("  INSERT Cliente: ID gerado -> " + generatedId);
        } catch (Exception e) { System.err.println("  ERRO no ClienteDAO: " + e.getMessage()); }
        return generatedId;
    }

    // --- Trabalhador ---
    private static int testTrabalhadorDAO(int idCategoria) {
        long generatedId = -1;
        System.out.println("\n--- Teste: TrabalhadorDAO (Nível 1) ---");
        try {
            Trabalhador t = new Trabalhador();
            t.setNome("Diana Operacional");
            t.setCategoria(idCategoria);
            t.setEmailPessoal("diana@empresa.pt");
            t.setAtivo(true);
            generatedId = tDAO.insertTrabalhador(t);
            System.out.println("  INSERT Trabalhador: ID gerado -> " + generatedId);
        } catch (Exception e) { System.err.println("  ERRO no TrabalhadorDAO: " + e.getMessage()); }
        return (int) generatedId;
    }

    // --- Credenciais ---
    private static void testCredenciaisDAO(int idTrabalhador) {
        System.out.println("\n--- Teste: CredenciaisDAO (Nível 2) ---");
        try {
            Credenciais c = new Credenciais();
            c.setIdTrabalhador(idTrabalhador);
            c.setEmail("auth.diana@empresa.pt");
            c.setPassword("diana123");
            boolean inserted = credDAO.insertCredenciais(c);
            System.out.println("  INSERT Credenciais: Sucesso? " + inserted);
        } catch (Exception e) { System.err.println("  ERRO no CredenciaisDAO: " + e.getMessage()); }
    }

    // --- Sala ---
    private static int testSalaDAO(int idTipoEspaco) {
        long generatedId = -1;
        System.out.println("\n--- Teste: SalaDAO (Nível 1) ---");
        try {
            Sala s = new Sala();
            s.setNome("Palco Central");
            s.setTipoEspaco(idTipoEspaco);
            s.setLugares(0);
            s.setOcupada(false);
            s.setTemLugares(false);
            s.setEstado(true);
            generatedId = sDAO.insertSala(s);
            System.out.println("  INSERT Sala: ID gerado -> " + generatedId);
        } catch (Exception e) { System.err.println("  ERRO no SalaDAO: " + e.getMessage()); }
        return (int) generatedId;
    }

    // --- Recurso ---
    private static int testRecursoDAO(int idCategoria) {
        long generatedId = -1;
        System.out.println("\n--- Teste: RecursoDAO (Nível 1) ---");
        try {
            Recurso r = new Recurso();
            r.setNome("Mesa de Mistura");
            r.setPreco(50.00);
            r.setCategoria(idCategoria);
            r.setQuantidade(1);
            generatedId = rDAO.insertRecurso(r);
            System.out.println("  INSERT Recurso: ID gerado -> " + generatedId);
        } catch (Exception e) { System.err.println("  ERRO no RecursoDAO: " + e.getMessage()); }
        return (int) generatedId;
    }

    // --- Evento ---
    private static int testEventoDAO(int idTrabalhador, int idSala) {
        long generatedId = -1;
        System.out.println("\n--- Teste: EventoDAO (Nível 2) ---");
        try {
            Evento e = new Evento();
            e.setNome("Concerto Rock");
            e.setData(new Date(System.currentTimeMillis() + 86400000)); // Amanhã
            e.setDescricao("Banda de Rock ao vivo");
            e.setResponsavel(idTrabalhador);
            e.setSala(idSala);
            e.setHora(new Date(System.currentTimeMillis() + 86400000 + 36000000)); // 10h depois
            e.setEstado(true);
            e.setDecorreu(false);
            generatedId = evDAO.insertEvento(e);
            System.out.println("  INSERT Evento: ID gerado -> " + generatedId);
        } catch (Exception e) { System.err.println("  ERRO no EventoDAO: " + e.getMessage()); }
        return (int) generatedId;
    }

    // --- Lugar ---
    private static int testLugarDAO(int idSala) {
        long generatedId = -1;
        System.out.println("\n--- Teste: LugarDAO (Nível 2) ---");
        try {
            Lugar l = new Lugar();
            l.setNumero("P01");
            l.setIdSala(idSala);
            generatedId = lDAO.insertLugar(l);
            System.out.println("  INSERT Lugar: ID gerado -> " + generatedId);
        } catch (Exception e) { System.err.println("  ERRO no LugarDAO: " + e.getMessage()); }
        return (int) generatedId;
    }

    // --- Tarefa ---
    private static void testTarefaDAO(int idTrabalhador, int idEvento, int idCategoria) {
        System.out.println("\n--- Teste: TarefaDAO (Nível 3) ---");
        try {
            Tarefa t = new Tarefa();
            t.setTitulo("Testar Equipamento");
            t.setDescricao("Verificar cabos e microfones");
            t.setIdTrabalhador(idTrabalhador);
            t.setEvento(idEvento);
            t.setEstado(false);
            t.setCategoria(idCategoria);
            long generatedId = tarDAO.insertTarefa(t);
            System.out.println("  INSERT Tarefa: ID gerado -> " + generatedId);
        } catch (Exception e) { System.err.println("  ERRO no TarefaDAO: " + e.getMessage()); }
    }

    // --- EventoRecurso ---
    private static void testEventoRecursoDAO(int idEvento, int idRecurso) {
        System.out.println("\n--- Teste: EventoRecursoDAO (Nível 3) ---");
        try {
            boolean inserted = erDAO.insertEventoRecurso(idEvento, idRecurso);
            System.out.println("  INSERT EventoRecurso: Sucesso? " + inserted);
        } catch (Exception e) { System.err.println("  ERRO no EventoRecursoDAO: " + e.getMessage()); }
    }

    // --- Bilhete ---
    private static void testBilheteDAO(int idLugar, int idCliente, int idEvento) {
        System.out.println("\n--- Teste: BilheteDAO (Nível 3) ---");
        try {
            Bilhete b = new Bilhete();
            b.setLugar(idLugar);
            b.setIdCliente(idCliente);
            b.setIdEvento(idEvento);
            b.setPreco(18.50);
            long generatedId = bDAO.insertBilhete(b);
            System.out.println("  INSERT Bilhete: ID gerado -> " + generatedId);
        } catch (Exception e) { System.err.println("  ERRO no BilheteDAO: " + e.getMessage()); }
    }
}*/