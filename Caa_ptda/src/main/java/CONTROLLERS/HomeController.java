package CONTROLLERS;

import MODELS.CLASS.Bilhete;
import MODELS.CLASS.CategoriaTrabalho;
import MODELS.CLASS.Cliente;
import MODELS.CLASS.Consumivel;
import MODELS.CLASS.Trabalhador;
import MODELS.CLASS.Credenciais;
import MODELS.CLASS.Espaco;
import MODELS.CLASS.Evento;
import MODELS.CLASS.EventoRecurso;
import MODELS.CLASS.Lugar;
import MODELS.CLASS.NaoConsumivel;
import MODELS.CLASS.Sala;
import MODELS.CLASS.Recurso;
import MODELS.CLASS.Tarefa;
import MODELS.CLASS.Cliente;
import MODELS.DAO.BilheteDAO;
import MODELS.DAO.CategoriaTrabalhoDAO;
import MODELS.DAO.ClienteDAO;
import MODELS.DAO.ConsumiveisDAO;
import MODELS.DAO.TrabalhadorDAO;
import MODELS.DAO.CredenciaisDAO;
import MODELS.DAO.EspacoDAO;
import MODELS.DAO.EventoDAO;
import MODELS.DAO.EventoRecursoDAO;
import MODELS.DAO.LugarDAO;
import MODELS.DAO.NaoConsumiveisDAO;
import MODELS.DAO.RecursoDAO;
import MODELS.DAO.SalaDAO;
import MODELS.DAO.TarefaDAO;
import MODELS.DAO.ClienteDAO;
import MODELS.DAO.LugarDAO;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;

public class HomeController {

    private TrabalhadorDAO trabalhadorDAO;
    private CredenciaisDAO credenciaisDAO;
    private CategoriaTrabalhoDAO categoriaTrabalhoDAO;
    private EventoDAO eventoDAO;
    private EventoRecursoDAO eventoRecursoDAO;
    private EspacoDAO espacoDAO;
    private SalaDAO salaDAO;
    private RecursoDAO recursoDAO;
    private ConsumiveisDAO consumiveisDao;
    private NaoConsumiveisDAO naoConsumiveisDao;
    private TarefaDAO tarefaDAO;
    private LugarDAO lugarDAO;
    private ClienteDAO clienteDAO;
    private BilheteDAO bilheteDAO;

    public HomeController() {
        this.trabalhadorDAO = new TrabalhadorDAO();
        this.credenciaisDAO = new CredenciaisDAO();
        this.categoriaTrabalhoDAO = new CategoriaTrabalhoDAO();
        this.eventoDAO = new EventoDAO();
        this.eventoRecursoDAO = new EventoRecursoDAO();
        this.espacoDAO = new EspacoDAO();
        this.salaDAO = new SalaDAO();
        this.recursoDAO = new RecursoDAO();
        this.consumiveisDao = new ConsumiveisDAO();
        this.naoConsumiveisDao = new NaoConsumiveisDAO();
        this.tarefaDAO = new TarefaDAO();
        this.lugarDAO = new LugarDAO();
        this.clienteDAO = new ClienteDAO();
        this.bilheteDAO = new BilheteDAO();
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
        Credenciais c = credenciaisDAO.getCredenciaisByEmail(email);

        if (c != null) {
            // Como estão no mesmo pacote, basta chamar o nome da classe
            if (PasswordUtil.verificarPassword(password, c.getPassword())) { 
                return c.getIdTrabalhador();
            }
        }
        return null; 
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

    public boolean criarEvento(String nome, String dataStr, String descricao, int responsavelId, int salaId, String horaStr, String duracaoStr, double preco, double precoBilhete, boolean alugado) {
        Evento e = new Evento();
        e.setNome(nome);
        e.setDescricao(descricao);
        e.setResponsavel(responsavelId);
        e.setSala(salaId);
        e.setEstado(true);
        e.setAtivo(true);
        e.setPreco(preco);
        e.setPrecoBilhete(precoBilhete);
        e.setAlugado(alugado);

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

            // Converter Duração
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

    public boolean editarEvento(int id, String nome, String dataStr, String descricao, int responsavelId, int salaId, String horaStr, String duracaoStr, double preco, double precoBilhete, boolean alugado) {
        Evento e = new Evento();
        e.setIdEvento(id);
        e.setNome(nome);
        e.setDescricao(descricao);
        e.setResponsavel(responsavelId);
        e.setSala(salaId);
        e.setEstado(true);
        e.setAtivo(true);
        e.setPreco(preco);
        e.setPrecoBilhete(precoBilhete);
        e.setAlugado(alugado);

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
            if (e.isCancelado()) {
                continue;
            }

            if (e.getData() != null && e.getHora() != null) {
                java.util.Calendar fimEvento = java.util.Calendar.getInstance();
                fimEvento.setTime(e.getData());

                java.util.Calendar horaInicio = java.util.Calendar.getInstance();
                horaInicio.setTime(e.getHora());

                fimEvento.set(java.util.Calendar.HOUR_OF_DAY, horaInicio.get(java.util.Calendar.HOUR_OF_DAY));
                fimEvento.set(java.util.Calendar.MINUTE, horaInicio.get(java.util.Calendar.MINUTE));
                fimEvento.set(java.util.Calendar.SECOND, 0);

                if (e.getDuracao() != null) {
                    java.util.Calendar duracao = java.util.Calendar.getInstance();
                    duracao.setTime(e.getDuracao());

                    fimEvento.add(java.util.Calendar.HOUR_OF_DAY, duracao.get(java.util.Calendar.HOUR_OF_DAY));
                    fimEvento.add(java.util.Calendar.MINUTE, duracao.get(java.util.Calendar.MINUTE));
                }

                if (fimEvento.before(agora)) {
                    eventoDAO.marcarComoDecorrido(e.getIdEvento());
                }
            }
        }
    }

    public boolean eliminarEvento(int id) {
        return eventoDAO.deleteEvento(id);
    }

    public boolean cancelarEvento(int id) {
        return eventoDAO.cancelarEvento(id);
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

    public List<Lugar> listarLugaresPorSala(int idSala, int idEvento) {
        return lugarDAO.getLugaresLivresbySala(idSala, idEvento);
    }

    public List<Cliente> listarTodosClientes() {
        return clienteDAO.getAllClientes();
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
        return recursoDAO.deleteRecurso(id);
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
        long id = recursoDAO.insertRecurso(c);
        if (id > 0) {
            c.setIdRecurso((int) id);
            return consumiveisDao.insertConsumivel(c);
        }
        return false;
    }

    public boolean criarNaoConsumivel(NaoConsumivel nc) {
        long id = recursoDAO.insertRecurso(nc);
        if (id > 0) {
            nc.setIdRecurso((int) id);
            return naoConsumiveisDao.insertNaoConsumivel(nc);
        }
        return false;
    }

    public boolean editarConsumivel(Consumivel c) {
        if (recursoDAO.updateRecurso(c)) {
            return consumiveisDao.updateConsumivel(c);
        }
        return false;
    }

    public boolean editarNaoConsumivel(NaoConsumivel nc) {
        if (recursoDAO.updateRecurso(nc)) {
            return naoConsumiveisDao.updateNaoConsumivel(nc);
        }
        return false;
    }

    public List<EventoRecurso> listarEventoRecursoNaoConsumiveis() {
        return eventoRecursoDAO.getEventoRecursosNaoConsumiveis();
    }

    public List<EventoRecurso> listarEventoRecursoConsumiveis() {
        return eventoRecursoDAO.getEventoRecursosConsumiveis();
    }

    public List<Recurso> listarTodosOsRecursosSemConsiderarAtivo() {
        return recursoDAO.getAllRecursosWithoutAtivo();
    }

    public String identificarTipoRecurso(int id) {
        if (id <= 0) {
            return "ID Inválido";
        }
        return recursoDAO.getTipoRecursoById(id);
    }

    public List<Evento> listarTodosEventosAtivos() {
        return eventoDAO.getAllEventos();
    }

    public List<Recurso> listarTodosRecursosAtivas() {
        return recursoDAO.getAllRecursos();
    }

    public int obterQuantidadeRecursoNoEvento(int idEvento, int idRecurso) {
        EventoRecursoDAO dao = new EventoRecursoDAO();
        List<MODELS.CLASS.EventoRecurso> lista = dao.getRecursosByEventoId(idEvento);

        for (MODELS.CLASS.EventoRecurso er : lista) {
            if (er.getIdRecurso() == idRecurso) {
                return er.getQuantidade();
            }
        }
        return 0;
    }

    public boolean associarRecursoAEvento(int idEvento, int idRecurso, int qtPedida) {
        Evento ev = eventoDAO.getEventoById(idEvento);
        Recurso rec = recursoDAO.getRecursoById(idRecurso);
        if (ev == null || rec == null) {
            return false;
        }

        String tipo = recursoDAO.getTipoRecursoById(idRecurso);
        int qtAntiga = eventoRecursoDAO.getQuantidadeNoEvento(idEvento, idRecurso);

        // --- NOVA LÓGICA: SE QUANTIDADE FOR ZERO, ELIMINA ---
        if (qtPedida == 0) {
            if (qtAntiga > 0) {
                boolean removido = eventoRecursoDAO.deleteEventoRecurso(idEvento, idRecurso);
                if (removido && tipo.equalsIgnoreCase("Consumível")) {
                    // Devolve tudo ao stock principal
                    rec.setQuantidade(rec.getQuantidade() + qtAntiga);
                    recursoDAO.updateRecurso(rec);
                }
                return removido;
            }
            return true; // Já era zero e continua zero, não faz nada mas retorna sucesso
        }

        // --- LÓGICA DE ATUALIZAÇÃO / INSERÇÃO (Existente) ---
        if (tipo.equalsIgnoreCase("Consumível")) {
            int diferencaAjuste = qtPedida - qtAntiga;

            if (diferencaAjuste <= rec.getQuantidade()) {
                boolean sucesso;
                if (qtAntiga > 0) {
                    sucesso = eventoRecursoDAO.updateQuantidadeRecurso(idEvento, idRecurso, qtPedida);
                } else {
                    sucesso = eventoRecursoDAO.insertEventoRecurso(idEvento, idRecurso, qtPedida);
                }

                if (sucesso) {
                    rec.setQuantidade(rec.getQuantidade() - diferencaAjuste);
                    recursoDAO.updateRecurso(rec);
                    return true;
                }
            }
        } else {
            // Lógica para Não Consumíveis (Horário)
            int emUsoOutros = eventoRecursoDAO.getQuantidadeEmUso(
                    idRecurso,
                    new java.sql.Date(ev.getData().getTime()),
                    new java.sql.Time(ev.getHora().getTime()),
                    ev.getDuracao(),
                    idEvento
            );

            if (qtPedida <= (rec.getQuantidade() - emUsoOutros)) {
                if (qtAntiga > 0) {
                    return eventoRecursoDAO.updateQuantidadeRecurso(idEvento, idRecurso, qtPedida);
                } else {
                    return eventoRecursoDAO.insertEventoRecurso(idEvento, idRecurso, qtPedida);
                }
            }
        }
        return false;
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

    public String criarCliente(String nome, String email, int telefone) {
        MODELS.DAO.ClienteDAO clienteDAO = new MODELS.DAO.ClienteDAO();

        if (clienteDAO.existeEmail(email)) {
            return "Já existe um cliente com este email.";
        }
        if (clienteDAO.existeTelefone(telefone)) {
            return "Já existe um cliente com este número de telefone.";
        }

        MODELS.CLASS.Cliente c = new MODELS.CLASS.Cliente();
        c.setNome(nome);
        c.setEmail(email);
        c.setNumeroTelefone(telefone);
        c.setAtivo(true);

        long id = clienteDAO.insertCliente(c);
        return (id > 0) ? "Sucesso" : "Erro ao criar cliente.";
    }

    // --- ATUALIZAR MÉTODO DE INSERÇÃO ---
    public boolean insertSala(String nome, int idEspaco, int qtdLugares, boolean temLugares, boolean ativo, boolean ocupada) {
        Sala s = new Sala();
        s.setNome(nome);
        s.setTipoEspaco(idEspaco);
        s.setLugares(qtdLugares);
        s.setTemLugares(temLugares);
        s.setOcupada(ocupada);
        s.setAtivo(ativo);

        int idGerado = (int) salaDAO.insertSala(s);

        // Se a sala foi criada e tem lugares, gera os números
        if (idGerado > 0 && temLugares) {
            gerarNumerosDeLugares(idGerado, 1, qtdLugares);
            return true;
        }
        return idGerado > 0;
    }

    public boolean atualizarSala(int id, String nome, int tipo, int lugaresNovos, boolean temLugares) {
        Sala s = new Sala();
        s.setIdSala(id);
        s.setNome(nome);
        s.setTipoEspaco(tipo);
        s.setLugares(lugaresNovos);
        s.setTemLugares(temLugares);

        boolean sucesso = salaDAO.updateSala(s);

        if (sucesso) {
            List<MODELS.CLASS.Lugar> todosOsLugares = lugarDAO.getTodosLugaresPorSalaInclusiveInativos(id);
            int totalExistenteNoBanco = todosOsLugares.size();

            if (lugaresNovos > totalExistenteNoBanco) {
                lugarDAO.setAtividadeLote(id, true, totalExistenteNoBanco);
                gerarNumerosDeLugares(id, totalExistenteNoBanco + 1, lugaresNovos);

            } else if (lugaresNovos <= totalExistenteNoBanco) {

                lugarDAO.setAtividadePorIntervalo(id, true, 1, lugaresNovos);

                if (totalExistenteNoBanco > lugaresNovos) {
                    lugarDAO.setAtividadePorIntervalo(id, false, lugaresNovos + 1, totalExistenteNoBanco);
                }
            }
        }
        return sucesso;
    }

    // --- MÉTODO AUXILIAR DE GERAÇÃO (Apenas números) ---
    private void gerarNumerosDeLugares(int idSala, int inicio, int fim) {
        for (int i = inicio; i <= fim; i++) {
            MODELS.CLASS.Lugar l = new MODELS.CLASS.Lugar();
            l.setNumero(String.valueOf(i)); // Transforma o número em String (ex: "1", "2"...)
            l.setIdSala(idSala);
            l.setAtivo(true);
            lugarDAO.insertLugar(l);
        }
    }

    private void gerarLugaresParaSala(int idSala, int inicio, int fim) {
        for (int i = inicio; i <= fim; i++) {
            MODELS.CLASS.Lugar l = new MODELS.CLASS.Lugar();
            l.setNumero("L" + i); // Ex: L1, L2, L3...
            l.setIdSala(idSala);
            l.setAtivo(true);
            lugarDAO.insertLugar(l);
        }
    }

    public long efetuarVenda(int idLugar, int idCliente, int idEvento, double preco) {
        Bilhete novoBilhete = new Bilhete();
        novoBilhete.setLugar(idLugar);
        novoBilhete.setIdCliente(idCliente);
        novoBilhete.setIdEvento(idEvento);
        novoBilhete.setPreco(preco);
        novoBilhete.setAtivo(true);

        return bilheteDAO.insertBilhete(novoBilhete);
    }

    public List<Evento> listarEventosProprios() {
        return eventoDAO.getEventosNaoAlugados();
    }

    public List<Evento> listarEventosAlugados() {
        return eventoDAO.getEventosAlugados();
    }

    public List<Bilhete> listarTodosBilhetes() {
        try {
            return bilheteDAO.getAllBilhetes();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<EventoRecurso> listarConsumiveisEventosAlugados() {
        try {
            return eventoRecursoDAO.getConsumiveisDeEventosAlugados();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Recurso buscarPorId(int id) {
        if (id <= 0) {
            return null;
        }
        return recursoDAO.getRecursoById(id);
    }
}
