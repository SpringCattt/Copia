package CONTROLLERS;

import MODELS.CLASS.CategoriaTrabalho;
import MODELS.CLASS.Consumivel;
import MODELS.CLASS.Trabalhador;
import MODELS.CLASS.Credenciais;
import MODELS.CLASS.Espaco;
import MODELS.CLASS.Evento;
import MODELS.CLASS.EventoRecurso;
import MODELS.CLASS.NaoConsumivel;
import MODELS.CLASS.Sala;
import MODELS.CLASS.Recurso;
import MODELS.CLASS.Tarefa;
import MODELS.DAO.CategoriaTrabalhoDAO;
import MODELS.DAO.ConsumiveisDAO;
import MODELS.DAO.TrabalhadorDAO;
import MODELS.DAO.CredenciaisDAO;
import MODELS.DAO.EspacoDAO;
import MODELS.DAO.EventoDAO;
import MODELS.DAO.EventoRecursoDAO;
import MODELS.DAO.NaoConsumiveisDAO;
import MODELS.DAO.RecursoDAO;
import MODELS.DAO.SalaDAO;
import MODELS.DAO.TarefaDAO;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class HomeController {

    private TrabalhadorDAO trabalhadorDAO;
    private CredenciaisDAO credenciaisDAO;
    private CategoriaTrabalhoDAO categoriaTrabalhoDAO;
    private EventoDAO eventoDAO;
    private EventoRecursoDAO eventoRecursoDAO;
    private EspacoDAO espacoDAO;
    private SalaDAO salaDAO;
    private RecursoDAO recursoDao;
    private ConsumiveisDAO consumiveisDao;
    private NaoConsumiveisDAO naoConsumiveisDao;
    private TarefaDAO tarefaDAO;

    public HomeController() {
        this.trabalhadorDAO = new TrabalhadorDAO();
        this.credenciaisDAO = new CredenciaisDAO();
        this.categoriaTrabalhoDAO = new CategoriaTrabalhoDAO();
        this.eventoDAO = new EventoDAO();
        this.eventoRecursoDAO = new EventoRecursoDAO();
        this.espacoDAO = new EspacoDAO();
        this.salaDAO = new SalaDAO();
        this.recursoDao = new RecursoDAO();
        this.consumiveisDao = new ConsumiveisDAO();
        this.naoConsumiveisDao = new NaoConsumiveisDAO();
        this.tarefaDAO = new TarefaDAO();
    }

    // --- FUNCIONÁRIOS ---
    public boolean criarFuncionario(String nome, String emailpessoal, String emailempresa,
            String password, int categoria, boolean atividade) {
        if (verificarDuplicidadeEmail(emailempresa, -1)) {
            return false;
        }
        if (verificarDuplicidadeEmailPessoal(emailpessoal, -1)) {
            return false;
        }

        Trabalhador t = new Trabalhador();
        t.setNome(nome);
        t.setEmailPessoal(emailpessoal);
        t.setCategoria(categoria);
        t.setAtivo(atividade);

        long idGerado = trabalhadorDAO.insertTrabalhador(t);
        if (idGerado <= 0) {
            return false;
        }

        Credenciais c = new Credenciais();
        c.setIdTrabalhador((int) idGerado);
        c.setEmail(emailempresa);
        c.setPassword(password);

        return credenciaisDAO.insertCredenciais(c);
    }

    public boolean editarFuncionario(int id, String nome, String emailPessoal, String emailEmpresa,
            String password, int categoria, boolean atividade) {
        if (nome == null || nome.trim().isEmpty() || emailPessoal == null || emailEmpresa == null) {
            return false;
        }

        if (verificarDuplicidadeEmail(emailEmpresa, id)) {
            return false;
        }
        if (verificarDuplicidadeEmailPessoal(emailPessoal, id)) {
            return false;
        }

        Trabalhador t = new Trabalhador();
        t.setIdTrabalhador(id);
        t.setNome(nome);
        t.setEmailPessoal(emailPessoal);
        t.setCategoria(categoria);
        t.setAtivo(atividade);

        if (!trabalhadorDAO.updateTrabalhador(t)) {
            return false;
        }

        boolean atualizarPass = (password != null && !password.trim().isEmpty());
        return credenciaisDAO.updateCredenciais(id, emailEmpresa, password, atualizarPass);
    }

    public boolean eliminarFuncionario(int id) {
        return trabalhadorDAO.desativarTrabalhador(id);
    }

    public List<Trabalhador> pesquisarFuncionarios(String termo) {
        return trabalhadorDAO.buscarTrabalhadores(termo);
    }

    public List<Tarefa> listarTarefasPorTrabalhador(int idTrabalhador) {
        return tarefaDAO.getTarefasByTrabalhador(idTrabalhador);
    }

    public List<Tarefa> listarTarefasConcluidas(int idTrabalhador) {
        return tarefaDAO.getTarefasConcluidasByTrabalhador(idTrabalhador);
    }

    public List<Tarefa> listarTarefasPendentes(int idTrabalhador) {
        return tarefaDAO.getTarefasPendentesByTrabalhador(idTrabalhador);
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

    public List<Trabalhador> obterTrabalhadorEspecifico(String cat) {
        return trabalhadorDAO.getTrabalhadoresPorCategoria(cat);
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
        int responsavelId, int salaId, String horaStr, String duracaoStr) { // Novo parâmetro
        Evento e = new Evento();
        e.setNome(nome);
        e.setDescricao(descricao);
        e.setResponsavel(responsavelId);
        e.setSala(salaId);
        e.setEstado(true);
        e.setAtivo(true);

        try {
            SimpleDateFormat formatoUsuario = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
            formatoUsuario.setLenient(false);

            // Converter Data
            if (dataStr != null && !dataStr.isEmpty()) {
                java.util.Date dataUtil = formatoUsuario.parse(dataStr);
                e.setData(new java.sql.Date(dataUtil.getTime()));
            }

            // Converter Hora de Início
            if (horaStr != null && !horaStr.isEmpty()) {
                java.util.Date horaUtil = formatoHora.parse(horaStr);
                e.setHora(new java.sql.Time(horaUtil.getTime()));
            }

            // NOVO: Converter Duração
            if (duracaoStr != null && !duracaoStr.isEmpty()) {
                java.util.Date duracaoUtil = formatoHora.parse(duracaoStr);
                e.setDuracao(new java.sql.Time(duracaoUtil.getTime()));
            }

        } catch (ParseException ex) {
            ex.printStackTrace();
            return false;
        }
        return eventoDAO.insertEvento(e) > 0;
    }
    
    public boolean editarEvento(int id, String nome, String dataStr, String descricao,
        int responsavelId, int salaId, String horaStr, String duracaoStr) { // Novo parâmetro
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
            SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
            formatoUsuario.setLenient(false);

            // Data
            if (dataStr != null && !dataStr.isEmpty()) {
                java.util.Date dataUtil = formatoUsuario.parse(dataStr);
                e.setData(new java.sql.Date(dataUtil.getTime()));
            }

            // Hora de Início
            if (horaStr != null && !horaStr.isEmpty()) {
                java.util.Date horaUtil = formatoHora.parse(horaStr);
                e.setHora(new java.sql.Time(horaUtil.getTime()));
            }

            // NOVO: Duração
            if (duracaoStr != null && !duracaoStr.isEmpty()) {
                java.util.Date duracaoUtil = formatoHora.parse(duracaoStr);
                e.setDuracao(new java.sql.Time(duracaoUtil.getTime()));
            }

        } catch (ParseException ex) {
            ex.printStackTrace();
            return false;
        }
        return eventoDAO.updateEvento(e);
    }
    
    public boolean verificarDisponibilidadeSala(int idSala, String dataStr, String horaStr, String duracaoStr, int idEventoIgnorar) {
        try {
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
            
            java.util.Date d = formatoData.parse(dataStr);
            java.sql.Date dataSql = new java.sql.Date(d.getTime());
            
            java.util.Date h = formatoHora.parse(horaStr);
            java.sql.Time horaSql = new java.sql.Time(h.getTime());            

            java.util.Date dur = formatoHora.parse(duracaoStr);
            java.sql.Time durSql = new java.sql.Time(dur.getTime());
            
            boolean tieneConflicto = eventoDAO.verificarSobreposicao(idSala, dataSql, horaSql, durSql, idEventoIgnorar);
            
            return !tieneConflicto;
            
        } catch (ParseException e) {
            e.printStackTrace();
            return false; 
        }
    }
    
    public void verificarEAtualizarEventosPassados() {
        List<Evento> eventos = eventoDAO.getEventosAtivosNaoDecorridos();
        java.util.Calendar agora = java.util.Calendar.getInstance();
        
        for (Evento e : eventos) {
            if (e.isCancelado()) continue; 
            
            if (e.getData() != null && e.getHora() != null) {
                java.util.Calendar fimEvento = java.util.Calendar.getInstance();
                fimEvento.setTime(e.getData());
                
                java.util.Calendar horaInicio = java.util.Calendar.getInstance();
                horaInicio.setTime(e.getHora());
                
                fimEvento.set(java.util.Calendar.HOUR_OF_DAY, horaInicio.get(java.util.Calendar.HOUR_OF_DAY));
                fimEvento.set(java.util.Calendar.MINUTE, horaInicio.get(java.util.Calendar.MINUTE));
                fimEvento.set(java.util.Calendar.SECOND, 0);
                
                // Sumar duración si existe
                if (e.getDuracao() != null) {
                    java.util.Calendar duracao = java.util.Calendar.getInstance();
                    duracao.setTime(e.getDuracao());
                    
                    fimEvento.add(java.util.Calendar.HOUR_OF_DAY, duracao.get(java.util.Calendar.HOUR_OF_DAY));
                    fimEvento.add(java.util.Calendar.MINUTE, duracao.get(java.util.Calendar.MINUTE));
                }
                
                // Si la hora de fin es ANTES de AHORA, marcamos como decorrido
                if (fimEvento.before(agora)) {
                    eventoDAO.marcarComoDecorrido(e.getIdEvento());
                }
            }
        }
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

    public List<Evento> listarEventosDisponiveis() {
        return eventoDAO.getEventosAtivosNaoDecorridos();
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
        if (id <= 0) {
            return null;
        }
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
    // --- RECURSOS---
    // ==========================================
    public List<Consumivel> listarConsumiveis() {
        return consumiveisDao.getAllConsumiveis();
    }

    public List<NaoConsumivel> listarNaoConsumiveis() {
        return naoConsumiveisDao.getAllNaoConsumiveis();
    }

    public boolean eliminarRecurso(long id) {
        // Aqui podes adicionar lógica: ex: verificar se está em uso
        return recursoDao.deleteRecurso(id);
    }

    public Consumivel buscarConsumivelPorId(int id) {
        return consumiveisDao.getConsumivelById(id);
    }

    public NaoConsumivel buscarNaoConsumivelPorId(int id) {
        return naoConsumiveisDao.getNaoConsumivelById(id);
    }

    public boolean editarRecurso(Recurso r, int tipoNovo) {
        RecursoDAO rDao = new RecursoDAO();
        ConsumiveisDAO cDao = new ConsumiveisDAO();
        NaoConsumiveisDAO ncDao = new NaoConsumiveisDAO();

        if (!rDao.updateRecurso(r)) {
            return false;
        }

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

    public List<EventoRecurso> listarEventoRecursoNaoConsumiveis() {
        return eventoRecursoDAO.getEventoRecursosNaoConsumiveis();
    }

    public List<Recurso> listarTodosOsRecursosSemConsiderarAtivo() {
        return recursoDao.getAllRecursosWithoutAtivo();
    }

    public String identificarTipoRecurso(int id) {
        if (id <= 0) {
            return "ID Inválido";
        }
        return recursoDao.getTipoRecursoById(id);
    }

    public long criarTarefa(String titulo, String descricao, int idTrabalhador, int idEvento) {

        Tarefa novaTarefa = new Tarefa();
        novaTarefa.setTitulo(titulo);
        novaTarefa.setDescricao(descricao);
        novaTarefa.setIdTrabalhador(idTrabalhador);
        novaTarefa.setEvento(idEvento);
        novaTarefa.setEstado(false);
        novaTarefa.setAtivo(true);

        return tarefaDAO.insertTarefa(novaTarefa);
    }

    public boolean marcarComoConcluida(int idTarefa) {
        return tarefaDAO.concluirTarefa(idTarefa);
    }

}
