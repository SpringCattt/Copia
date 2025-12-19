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


    public boolean criarFuncionario(
            String nome,
            String emailpessoal,
            String emailempresa,
            String password,
            int categoria,
            boolean atividade
    ) {
        Trabalhador t = new Trabalhador();
        t.setNome(nome);
        t.setEmailPessoal(emailpessoal);
        t.setCategoria(categoria);
        t.setAtivo(atividade); 

        long idGerado = trabalhadorDAO.insertTrabalhador(t);

        if (idGerado <= 0) {
            System.err.println("Erro ao inserir trabalhador");
            return false;
        }

        // Criar Credenciais
        Credenciais c = new Credenciais();
        c.setIdTrabalhador((int) idGerado);
        c.setEmail(emailempresa);
        c.setPassword(password);

        boolean credOk = credenciaisDAO.insertCredenciais(c);

        if (!credOk) {
            System.err.println("Erro ao inserir credenciais");
            return false;
        }

        System.out.println("Funcionário criado com sucesso (ID " + idGerado + ")");
        return true;
    }

    public List<Trabalhador> obterTodosFuncionarios() {
        return trabalhadorDAO.getAllTrabalhadores();
    }

    public List<CategoriaTrabalho> getCategoriasTrabalho() {
        return categoriaTrabalhoDAO.getCategoriasTrabalho();
    }

    // LOGIN E ACESSO

    public boolean verificarDuplicidadeEmail(String email) {
        return credenciaisDAO.existeEmail(email);
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

    // EVENTOS (nao fiz ainda)

    public List<Evento> obterTodosEventos() {
        return eventoDAO.getAllEventos();
    }

    public boolean criarEvento(
            String nome, 
            String dataStr, 
            String descricao,                   
            int responsavelId, 
            int salaId, 
            String horaStr
    ) {
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
            System.err.println("Erro: A data deve ser no formato dd-mm-aaaa");
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        long id = eventoDAO.insertEvento(e);
        
        if (id > 0) {
            System.out.println("Evento criado com sucesso! ID: " + id);
            return true;
        } else {
            System.err.println("Erro ao criar evento na BD.");
            return false;
        }
    }
}