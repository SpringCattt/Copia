package CONTROLLERS;

import MODELS.CLASS.CategoriaTrabalho;
import MODELS.CLASS.Trabalhador;
import MODELS.CLASS.Credenciais;
import MODELS.CLASS.Evento;
import MODELS.DAO.CategoriaTrabalhoDAO;
import MODELS.DAO.TrabalhadorDAO;
import MODELS.DAO.CredenciaisDAO;
import MODELS.DAO.EventoDAO;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class HomeController {

    private TrabalhadorDAO trabalhadorDAO;
    private CredenciaisDAO credenciaisDAO;
    private CategoriaTrabalhoDAO categoriaTrabalhoDAO;
    private EventoDAO eventoDAO;

    public HomeController() {
        this.trabalhadorDAO = new TrabalhadorDAO();
        this.credenciaisDAO = new CredenciaisDAO();
        this.categoriaTrabalhoDAO = new CategoriaTrabalhoDAO();
        this.eventoDAO = new EventoDAO();
    }

    // --- CRIAR FUNCIONARIO ---
    public boolean criarFuncionario(String nome, String emailpessoal, String emailempresa, 
                                    String password, int categoria, boolean atividade) {
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

    // --- EDITAR FUNCIONARIO ---
    public boolean editarFuncionario(int id, String nome, String emailPessoal, String emailEmpresa, 
                                     String password, int categoria, boolean atividade) {
        
        //Se algum campo obrigatório estiver vazio, cancela.
        if (nome == null || nome.trim().isEmpty() || 
            emailPessoal == null || emailPessoal.trim().isEmpty() || 
            emailEmpresa == null || emailEmpresa.trim().isEmpty()) {
            return false; 
        }

        Trabalhador t = new Trabalhador();
        t.setIdTrabalhador(id);
        t.setNome(nome);
        t.setEmailPessoal(emailPessoal);
        t.setCategoria(categoria);
        t.setAtivo(atividade);

        if (!trabalhadorDAO.updateTrabalhador(t)) return false;

        // 4. Atualizar Credenciais (Email Trabalho e Password)
        // Nota: A password permitimos que seja vazia (significa "não alterar")
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

    // --- UTILS TRABALHADOR ---
    public List<CategoriaTrabalho> getCategoriasTrabalho() {
        return categoriaTrabalhoDAO.getCategoriasTrabalho();
    }
    
    public Trabalhador buscarTrabalhadorPorId(int id) {
        return trabalhadorDAO.getTrabalhadorById(id);
    }

    public Credenciais buscarCredenciaisPorId(int id) {
        return credenciaisDAO.getCredenciaisPorIdTrabalhador(id);
    }
    
    // --- VERIFICAÇOES ---
    public boolean verificarDuplicidadeEmail(String email, int idIgnorar) {
        //Modo CRIAR
        if (idIgnorar == -1) {
            return credenciaisDAO.existeEmail(email);
        } 
        //Modo EDITAR
        else {
            return credenciaisDAO.existeEmailIgnorandoId(email, idIgnorar);
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
            SimpleDateFormat formatoUsuario = new SimpleDateFormat("dd-mm-aaaa"); 
            formatoUsuario.setLenient(false);

            if (dataStr != null && !dataStr.isEmpty()) {
                String dataCorrigida = dataStr.replace("/", "-");
                java.util.Date dataUtil = formatoUsuario.parse(dataCorrigida);
                e.setData(new java.sql.Date(dataUtil.getTime()));
            }

            if (horaStr != null) {
                 e.setHora(new java.sql.Date(System.currentTimeMillis())); 
            }
            
        } catch (ParseException ex) {
            return false;
        }

        return eventoDAO.insertEvento(e) > 0;
    }
}