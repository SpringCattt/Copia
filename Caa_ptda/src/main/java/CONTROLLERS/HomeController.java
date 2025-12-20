package CONTROLLERS;

import MODELS.CLASS.CategoriaTrabalho;
import MODELS.CLASS.Consumivel;
import MODELS.CLASS.Trabalhador;
import MODELS.CLASS.Credenciais;
import MODELS.CLASS.Espaco;
import MODELS.CLASS.Evento;
import MODELS.CLASS.NaoConsumivel;
import MODELS.CLASS.Sala;
import MODELS.CLASS.Recurso;
import MODELS.DAO.CategoriaTrabalhoDAO;
import MODELS.DAO.ConsumiveisDAO;
import MODELS.DAO.TrabalhadorDAO;
import MODELS.DAO.CredenciaisDAO;
import MODELS.DAO.EspacoDAO;
import MODELS.DAO.EventoDAO;
import MODELS.DAO.NaoConsumiveisDAO;
import MODELS.DAO.RecursoDAO;
import MODELS.DAO.SalaDAO;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class HomeController {
    private TrabalhadorDAO trabalhadorDAO;
    private CredenciaisDAO credenciaisDAO;
    private CategoriaTrabalhoDAO categoriaTrabalhoDAO;
    private EventoDAO eventoDAO;
    private EspacoDAO espacoDAO;
    private SalaDAO salaDAO;
    private RecursoDAO recursoDao;
    private ConsumiveisDAO consumiveisDao;
    private NaoConsumiveisDAO naoConsumiveisDao;

    public HomeController() {
        this.trabalhadorDAO = new TrabalhadorDAO();
        this.credenciaisDAO = new CredenciaisDAO();
        this.categoriaTrabalhoDAO = new CategoriaTrabalhoDAO();
        this.eventoDAO = new EventoDAO();
        this.espacoDAO = new EspacoDAO();
        this.salaDAO = new SalaDAO();
        this.recursoDao = new RecursoDAO();
        this.consumiveisDao = new ConsumiveisDAO();
        this.naoConsumiveisDao = new NaoConsumiveisDAO();
    }

    // --- FUNCIONÁRIOS ---
    public boolean criarFuncionario(String nome, String emailpessoal, String emailempresa,
            String password, int categoria, boolean atividade) {
        if (verificarDuplicidadeEmail(emailempresa, -1)) return false;
        if (verificarDuplicidadeEmailPessoal(emailpessoal, -1)) return false;

        Trabalhador t = new Trabalhador();
        t.setNome(nome);
        t.setEmailPessoal(emailpessoal);
        t.setCategoria(categoria);
        t.setAtivo(atividade);

        long idGerado = trabalhadorDAO.insertTrabalhador(t);
        if (idGerado <= 0) return false;

        Credenciais c = new Credenciais();
        c.setIdTrabalhador((int) idGerado);
        c.setEmail(emailempresa);
        c.setPassword(password);

        return credenciaisDAO.insertCredenciais(c);
    }

    public boolean editarFuncionario(int id, String nome, String emailPessoal, String emailEmpresa,
            String password, int categoria, boolean atividade) {
        if (nome == null || nome.trim().isEmpty() || emailPessoal == null || emailEmpresa == null) return false;
        
        if (verificarDuplicidadeEmail(emailEmpresa, id)) return false;
        if (verificarDuplicidadeEmailPessoal(emailPessoal, id)) return false;

        Trabalhador t = new Trabalhador();
        t.setIdTrabalhador(id);
        t.setNome(nome);
        t.setEmailPessoal(emailPessoal);
        t.setCategoria(categoria);
        t.setAtivo(atividade);

        if (!trabalhadorDAO.updateTrabalhador(t)) return false;

        boolean atualizarPass = (password != null && !password.trim().isEmpty());
        return credenciaisDAO.updateCredenciais(id, emailEmpresa, password, atualizarPass);
    }

    public boolean eliminarFuncionario(int id) {
        return trabalhadorDAO.desativarTrabalhador(id);
    }

    public List<Trabalhador> pesquisarFuncionarios(String termo) {
        return trabalhadorDAO.buscarTrabalhadores(termo);
    }

    public List<Trabalhador> obterTodosFuncionarios() {
        return trabalhadorDAO.getAllTrabalhadores();
    }

    public List<CategoriaTrabalho> getCategoriasTrabalho() {
        return categoriaTrabalhoDAO.getCategoriasTrabalho();
    }

    public Trabalhador buscarTrabalhadorPorId(int id) {
        return trabalhadorDAO.getTrabalhadorById(id);
    }

    public Credenciais buscarCredenciaisPorId(int id) {
        return credenciaisDAO.getCredenciaisPorIdTrabalhador(id);
    }

    // --- VERIFICAÇÕES ---
    public boolean verificarDuplicidadeEmail(String email, int idIgnorar) {
        boolean existeEmCredenciais;
        boolean existeEmTrabalhador;
        if (idIgnorar == -1) {
            existeEmCredenciais = credenciaisDAO.existeEmail(email);
            existeEmTrabalhador = trabalhadorDAO.existeEmail(email);
        } else {
            existeEmCredenciais = credenciaisDAO.existeEmailIgnorandoId(email, idIgnorar);
            existeEmTrabalhador = trabalhadorDAO.existeEmailIgnorandoId(email, idIgnorar);
        }
        return existeEmCredenciais || existeEmTrabalhador;
    }

    public boolean verificarDuplicidadeEmail(String email) {
        return verificarDuplicidadeEmail(email, -1);
    }

    public boolean verificarDuplicidadeEmailPessoal(String email, int idIgnorar) {
        return verificarDuplicidadeEmail(email, idIgnorar);
    }

    public Integer efetuarLogin(String email, String password) {
        return credenciaisDAO.validarLogin(email, password);
    }

    public boolean verificarAtividadeTrabalhador(int id) {
        return trabalhadorDAO.isTrabalhadorAtivo(id);
    }

    public Trabalhador procurarTrabalhador(int id) {
        return trabalhadorDAO.getTrabalhadorById(id);
    }

    // --- EVENTOS ---
    public List<Evento> obterTodosEventos() {
        return eventoDAO.getAllEventos();
    }
    
    public Evento buscarEventoPorId(int id) {
        return eventoDAO.getEventoById(id);
    }
    
    public List<Evento> pesquisarEventos(String termo) {
        return eventoDAO.buscarEventos(termo);
    }

    public boolean criarEvento(String nome, String dataStr, String descricao,
            int responsavelId, int salaId, String horaStr) {
        Evento e = new Evento();
        e.setNome(nome);
        e.setDescricao(descricao);
        e.setResponsavel(responsavelId);
        e.setSala(salaId);
        e.setEstado(true);
        e.setAtivo(true); // USAMOS ATIVO

        try {
            SimpleDateFormat formatoUsuario = new SimpleDateFormat("dd/MM/yyyy");
            formatoUsuario.setLenient(false);

            if (dataStr != null && !dataStr.isEmpty()) {
                java.util.Date dataUtil = formatoUsuario.parse(dataStr);
                e.setData(new java.sql.Date(dataUtil.getTime()));
            }

            if (horaStr != null && !horaStr.isEmpty()) {
                SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
                java.util.Date horaUtil = formatoHora.parse(horaStr);
                e.setHora(new java.sql.Time(horaUtil.getTime()));
            } else {
                e.setHora(new java.sql.Time(System.currentTimeMillis()));
            }
        } catch (ParseException ex) {
            return false;
        }
        return eventoDAO.insertEvento(e) > 0;
    }
    
    public boolean editarEvento(int id, String nome, String dataStr, String descricao, 
                               int responsavelId, int salaId, String horaStr) {
        Evento e = new Evento();
        e.setIdEvento(id);
        e.setNome(nome);
        e.setDescricao(descricao);
        e.setResponsavel(responsavelId);
        e.setSala(salaId);
        e.setEstado(true);
        e.setAtivo(true);

        try {
            SimpleDateFormat formatoUsuario = new SimpleDateFormat("dd/MM/yyyy"); 
            formatoUsuario.setLenient(false);

            if (dataStr != null && !dataStr.isEmpty()) {
                java.util.Date dataUtil = formatoUsuario.parse(dataStr);
                e.setData(new java.sql.Date(dataUtil.getTime()));
            }
            if (horaStr != null && !horaStr.isEmpty()) {
                SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
                java.util.Date horaUtil = formatoHora.parse(horaStr);
                e.setHora(new java.sql.Time(horaUtil.getTime()));
            } else {
                e.setHora(new java.sql.Time(System.currentTimeMillis()));
            }
        } catch (ParseException ex) {
            return false;
        }
        return eventoDAO.updateEvento(e);
    }
    
    public boolean eliminarEvento(int id) {
        return eventoDAO.deleteEvento(id);
    }

    // --- ESPAÇOS E SALAS ---
    public List<Espaco> obterTodosEspacos() {
        return espacoDAO.getAllEspaco();
    }

    public Espaco procurarEspaco(int id) {
        return espacoDAO.getEspacoById(id);
    }

    public boolean temSalasVinculadas(int id) {
        return espacoDAO.isEspacoEmUso(id);
    }
    
    public boolean desativarEspaco(int id) {
        return espacoDAO.deleteEspaco(id);
    }

    public long adicionarEspaco(String nome, boolean ativo) {
        Espaco novoEspaco = new Espaco();
        novoEspaco.setNome(nome);
        novoEspaco.setAtivo(ativo);
        return espacoDAO.insertEspaco(novoEspaco);
    }

    public boolean atualizarEspaco(int id, String novoNome) {
        Espaco e = new Espaco();
        e.setIdEspaco(id);
        e.setNome(novoNome);
        return espacoDAO.updateEspaco(e);
    }
    
    public List<Espaco> pesquisarEspacos(String termo) {
        return espacoDAO.buscarEspacos(termo);
    }

    public boolean insertSala(String nome, int idEspaco, int qtdLugares, boolean temLugares, boolean ativo, boolean ocupada) {
        Sala s = new Sala();
        s.setNome(nome);
        s.setTipoEspaco(idEspaco);
        s.setLugares(qtdLugares);
        s.setTemLugares(temLugares);
        s.setOcupada(ocupada);
        s.setAtivo(ativo);
        return salaDAO.insertSala(s) > 0;
    }

    public boolean atualizarSala(int id, String nome, int tipo, int lugares, boolean temLugares) {
        Sala s = new Sala();
        s.setIdSala(id);
        s.setNome(nome);
        s.setTipoEspaco(tipo);
        s.setLugares(lugares);
        s.setTemLugares(temLugares);
        return salaDAO.updateSala(s);
    }

    public boolean podeSerEliminada(int id) {
        return salaDAO.temEventosAssociados(id);
    }

    public boolean eliminarSala(int id) {
        return salaDAO.deleteSala(id);
    }

    public Sala getSalaPorId(int id) {
        if (id <= 0) return null;
        return salaDAO.getSalaById(id);
    }
    
    public Sala buscarSalaPorId(int id) {
        return salaDAO.getSalaById(id);
    }

    public List<Sala> listarTodasSalasAtivas() {
        return salaDAO.getAllSalas();
    }
    
    public List<Sala> pesquisarSalas(String termo) {
        return salaDAO.buscarSalas(termo);
    }

    // ==========================================
    // --- RECURSOS (MANTIDOS) ---
    // ==========================================
    public boolean editarRecurso(Recurso r, int tipoNovo) {
        RecursoDAO rDao = new RecursoDAO();
        ConsumiveisDAO cDao = new ConsumiveisDAO();
        NaoConsumiveisDAO ncDao = new NaoConsumiveisDAO();

        if (!rDao.updateRecurso(r)) return false;

        if (tipoNovo == 0) { 
            Consumivel c = (Consumivel) r;
            if (!cDao.updateConsumivel(c)) {
                ncDao.eliminarRegistoFilho(c.getIdRecurso()); 
                return cDao.insertConsumivel(c);              
            }
            return true;
        } else { 
            NaoConsumivel nc = (NaoConsumivel) r;
            if (!ncDao.updateNaoConsumivel(nc)) {
                cDao.eliminarRegistoFilho(nc.getIdRecurso()); 
                return ncDao.insertNaoConsumivel(nc);         
            }
            return true;
        }
    }
    
    public boolean criarConsumivel(Consumivel c) {
        long id = recursoDao.insertRecurso(c);
        if (id > 0) {
            c.setIdRecurso((int) id);
            return consumiveisDao.insertConsumivel(c);
        }
        return false;
    }

    public boolean criarNaoConsumivel(NaoConsumivel nc) {
        long id = recursoDao.insertRecurso(nc);
        if (id > 0) {
            nc.setIdRecurso((int) id);
            return naoConsumiveisDao.insertNaoConsumivel(nc);
        }
        return false;
    }

    public boolean editarConsumivel(Consumivel c) {
        if (recursoDao.updateRecurso(c)) {
            return consumiveisDao.updateConsumivel(c);
        }
        return false;
    }

    public boolean editarNaoConsumivel(NaoConsumivel nc) {
        if (recursoDao.updateRecurso(nc)) {
            return naoConsumiveisDao.updateNaoConsumivel(nc);
        }
        return false;
    }
}