package CONTROLLERS;

import MODELS.CLASS.CategoriaTrabalho;
import MODELS.CLASS.Trabalhador;
import MODELS.CLASS.Credenciais;
import MODELS.CLASS.Espaco;
import MODELS.CLASS.Evento;
import MODELS.CLASS.Sala;
import MODELS.DAO.CategoriaTrabalhoDAO;
import MODELS.DAO.TrabalhadorDAO;
import MODELS.DAO.CredenciaisDAO;
import MODELS.DAO.EspacoDAO;
import MODELS.DAO.EventoDAO;
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

    public HomeController() {
        this.trabalhadorDAO = new TrabalhadorDAO();
        this.credenciaisDAO = new CredenciaisDAO();
        this.categoriaTrabalhoDAO = new CategoriaTrabalhoDAO();
        this.eventoDAO = new EventoDAO();
        this.espacoDAO = new EspacoDAO();
        this.salaDAO = new SalaDAO();
    }

    // --- CRIAR FUNCIONARIO ---
    public boolean criarFuncionario(String nome, String emailpessoal, String emailempresa,
            String password, int categoria, boolean atividade) {

        if (verificarDuplicidadeEmail(emailempresa, -1)) {
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

    // --- EDITAR FUNCIONARIO ---
    public boolean editarFuncionario(int id, String nome, String emailPessoal, String emailEmpresa,
            String password, int categoria, boolean atividade) {

        if (nome == null || nome.trim().isEmpty()
                || emailPessoal == null || emailPessoal.trim().isEmpty()
                || emailEmpresa == null || emailEmpresa.trim().isEmpty()) {
            return false;
        }

        if (verificarDuplicidadeEmail(emailEmpresa, id)) {
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

    // --- APAGAR FUNCIONARIO (SOFT DELETE) ---
    public boolean eliminarFuncionario(int id) {
        return trabalhadorDAO.desativarTrabalhador(id);
    }

    // --- BUSCAR FUNCIONARIO ---
    public java.util.List<Trabalhador> pesquisarFuncionarios(String termo) {
        return trabalhadorDAO.buscarTrabalhadores(termo);
    }

    public List<Trabalhador> obterTodosFuncionarios() {
        return trabalhadorDAO.getAllTrabalhadores();
    }

    // --- ESPAÇOS ---
    public List<Espaco> obterTodosEspacos() {
        return espacoDAO.getAllEspaco();
    }

    public Espaco procurarEspaco(int id) {
        return espacoDAO.getEspacoById(id);
    }

    public boolean temSalasVinculadas(int id) {
        return espacoDAO.isEspacoEmUso(id);
    }

    public boolean atualizarEspaco(int id, String novoNome) {
        Espaco e = new Espaco();
        e.setIdEspaco(id);
        e.setNome(novoNome);
        return espacoDAO.updateEspaco(e);
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
        if (idIgnorar == -1) {
            return credenciaisDAO.existeEmail(email);
        } else {
            return credenciaisDAO.existeEmailIgnorandoId(email, idIgnorar);
        }
    }

    public boolean verificarDuplicidadeEmail(String email) {
        return verificarDuplicidadeEmail(email, -1);
    }

    // Validação de Email Pessoal
    public boolean verificarDuplicidadeEmailPessoal(String email, int idIgnorar) {
        if (idIgnorar == -1) {
            return trabalhadorDAO.existeEmail(email);
        } else {
            return trabalhadorDAO.existeEmailIgnorandoId(email, idIgnorar);
        }
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
    
    // Método necessário para o botão editar
    public Evento buscarEventoPorId(int id) {
        return eventoDAO.getEventoById(id);
    }
    
    // Método para a barra de pesquisa
    public List<Evento> pesquisarEventos(String termo) {
        return eventoDAO.buscarEventos(termo);
    }
    //CRIAR EVENTO
    public boolean criarEvento(String nome, String dataStr, String descricao,
            int responsavelId, int salaId, String horaStr) {
        Evento e = new Evento();
        e.setNome(nome);
        e.setDescricao(descricao);
        e.setResponsavel(responsavelId);
        e.setSala(salaId);
        e.setEstado(true);
        e.setCancelado(false);

        try {
            //dd/MM/yyyy
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
            formatoData.setLenient(false); 

            if (dataStr != null && !dataStr.isEmpty()) {
                java.util.Date dataUtil = formatoData.parse(dataStr);
                e.setData(new java.sql.Date(dataUtil.getTime()));
            }

            //HH:mm
            if (horaStr != null && !horaStr.isEmpty()) {
                SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
                java.util.Date horaUtil = formatoHora.parse(horaStr);
                e.setHora(new java.sql.Time(horaUtil.getTime()));
            } else {
                e.setHora(new java.sql.Time(System.currentTimeMillis()));
            }

        } catch (ParseException ex) {
            System.err.println("Erro parsing data/hora: " + ex.getMessage());
            return false;
        }

        return eventoDAO.insertEvento(e) > 0;
    }
    
    // --- EDITAR EVENTO ---
    public boolean editarEvento(int id, String nome, String dataStr, String descricao, 
                               int responsavelId, int salaId, String horaStr) {
        Evento e = new Evento();
        e.setIdEvento(id);
        e.setNome(nome);
        e.setDescricao(descricao);
        e.setResponsavel(responsavelId);
        e.setSala(salaId);
        e.setEstado(true);
        e.setCancelado(false);

        try {
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
            formatoData.setLenient(false);

            if (dataStr != null && !dataStr.isEmpty()) {
                java.util.Date dataUtil = formatoData.parse(dataStr);
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
    
    // ELIMINAR EVENTO
    public boolean eliminarEvento(int id) {
        return eventoDAO.cancelarEvento(id);
    }

    // --- MÉTODOS ESPAÇOS E SALAS ---
    public long adicionarEspaco(String nome, boolean ativo) {
        Espaco novoEspaco = new Espaco();
        novoEspaco.setNome(nome);
        novoEspaco.setAtivo(ativo);
        return espacoDAO.insertEspaco(novoEspaco);
    }

    public boolean insertSala(String nome, int idEspaco, int qtdLugares, boolean temLugares, boolean ativo, boolean ocupada) {
        Sala s = new Sala();
        s.setNome(nome);
        s.setTipoEspaco(idEspaco);
        s.setLugares(qtdLugares);
        s.setTemLugares(temLugares);
        s.setOcupada(ocupada);
        s.setAtivo(ativo);
        long idGerado = salaDAO.insertSala(s);

        return idGerado > 0;
    }

    public boolean atualizarSala(int id, String nome, int tipo, int lugares, boolean temLugares) {
        Sala s = new Sala();
        s.setIdSala(id);
        s.setNome(nome);
        s.setTipoEspaco(tipo);
        s.setLugares(lugares);
        s.setTemLugares(temLugares);

        // 2. Chamar o método do DAO que tu criaste
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
}
