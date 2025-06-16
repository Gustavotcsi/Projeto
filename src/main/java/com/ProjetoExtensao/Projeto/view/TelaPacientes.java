package com.ProjetoExtensao.Projeto.view;

import com.ProjetoExtensao.Projeto.infra.Cores;
import com.ProjetoExtensao.Projeto.infra.DateTimeFormatter;
import com.ProjetoExtensao.Projeto.infra.PanelsFactory;
import com.ProjetoExtensao.Projeto.models.Paciente;
import com.ProjetoExtensao.Projeto.servicos.NavigationService;
import com.ProjetoExtensao.Projeto.servicos.PacienteService;
import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Component
@NoArgsConstructor
public class TelaPacientes extends JFrame {
    @Autowired
    private PanelsFactory panelsFactory;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private NavigationService navigationService;

    private JTable tabelaPacientes;
    private DefaultTableModel modeloTabela;
    private JTextField campoNome;
    private JTextField campoCpf;

    private boolean primerioAcesso = false;
    private List<Paciente> pacientes = new ArrayList<>();

    @PostConstruct
    private void initUI() {
        setTitle("Consultar Pacientes");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(new Color(245, 247, 250));

        // Painel central
        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setBorder(new EmptyBorder(30, 50, 30, 50));
        painelCentral.setBackground(new Color(245, 247, 250));

        // Título e botão
        JPanel linhaTitulo = new JPanel(new BorderLayout());
        linhaTitulo.setBackground(new Color(245, 247, 250));

        JLabel lblTitulo = new JLabel("Consultar pacientes");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBorder(new EmptyBorder(0, 0, 0, 40));

        JPanel painelLabel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 10)); // 10px margem vertical
        painelLabel.setOpaque(false);
        painelLabel.add(lblTitulo);

        // BOTAO ADICIONAR
        JButton btnAdicionar = new JButton("Adicionar novo");
        estilizarBotao(btnAdicionar);
        btnAdicionar.setPreferredSize(new Dimension(160, 40));
        btnAdicionar.addActionListener(e -> navigationService.abrirTelaCadastroPacientes());

        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        painelBotao.setOpaque(false);
        painelBotao.add(btnAdicionar);

        linhaTitulo.add(painelLabel, BorderLayout.WEST);
        linhaTitulo.add(painelBotao, BorderLayout.EAST);

        painelCentral.add(linhaTitulo);

        // Filtros de pesquisa
        JPanel painelFiltros = new JPanel();
        painelFiltros.setLayout(new BoxLayout(painelFiltros, BoxLayout.Y_AXIS));
        painelFiltros.setBackground(new Color(255, 255, 255));
        painelFiltros.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                new EmptyBorder(20, 20, 20, 20)
        ));

        JPanel linhaFiltros1 = new JPanel(new GridLayout(1, 2, 20, 10));
        linhaFiltros1.setBackground(Color.WHITE);

        // CRIAR CAMPOS
        campoNome = criarCampoComLabel("Nome", linhaFiltros1);
        campoCpf = criarCampoComLabel("CPF", linhaFiltros1);


        JPanel linhaFiltros2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        linhaFiltros2.setBackground(Color.WHITE);

        JRadioButton rbTodos = new JRadioButton("Todos", true);
        JRadioButton rbAtivos = new JRadioButton("Ativos");
        JRadioButton rbInativos = new JRadioButton("Inativos");
        ButtonGroup grupoStatus = new ButtonGroup();
        grupoStatus.add(rbTodos);
        grupoStatus.add(rbAtivos);
        grupoStatus.add(rbInativos);
        linhaFiltros2.add(rbTodos);
        linhaFiltros2.add(rbAtivos);
        linhaFiltros2.add(rbInativos);

        // BOTAO PESQUISAR
        JButton btnPesquisar = new JButton("Pesquisar");
        estilizarBotao(btnPesquisar);
        linhaFiltros2.add(btnPesquisar);

        // LISTENER
        btnPesquisar.addActionListener(e -> {
            String nome = campoNome.getText();
            String cpf = campoCpf.getText();
            List<Paciente> pacienteList;

            if (!nome.isEmpty()) {
                pacienteList = pacienteService.findPacientesByNomeCompleto(nome);
            } else if (!cpf.isEmpty()) {
                pacienteList = pacienteService.findPacientesByCpf(cpf);
            } else {
                pacienteList = pacienteService.findAllPacientes();
            }

            preencherTabela(pacienteList);
        });

        painelFiltros.add(linhaFiltros1);
        painelFiltros.add(Box.createVerticalStrut(10));
        painelFiltros.add(linhaFiltros2);

        painelCentral.add(painelFiltros);
        painelCentral.add(Box.createVerticalStrut(20));

        // Tabela de pacientes
        String[] colunas = {"Nome", "CPF", "Data de nascimento", "Cartão SUS", "Data de entrada", "Nome da mãe"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaPacientes = new JTable(modeloTabela);
        tabelaPacientes.setRowHeight(25);
        JScrollPane scroll = new JScrollPane(tabelaPacientes);

        painelCentral.add(scroll);
        painelPrincipal.add(painelCentral, BorderLayout.CENTER);
        painelPrincipal.add(panelsFactory.getFooterPanel(), BorderLayout.SOUTH);
        painelPrincipal.add(panelsFactory.getHeaderPanel(), BorderLayout.NORTH);

        setContentPane(painelPrincipal);

        recuperarDadosPrimeiroAcesso();
    }

    private void recuperarDadosPrimeiroAcesso() {
        if (!primerioAcesso) {
            pacientes = pacienteService.findAllPacientes();

            primerioAcesso = true;

            preencherTabela(pacientes);
        } else {
            preencherTabela(pacientes);
        }
    }

    private JTextField criarCampoComLabel(String textoLabel, JPanel painelDestino) {
        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout(5, 5));
        painel.setBackground(Color.WHITE);

        JLabel label = new JLabel(textoLabel);
        JTextField campo = new JTextField();

        painel.add(label, BorderLayout.NORTH);
        painel.add(campo, BorderLayout.CENTER);
        painelDestino.add(painel);

        return campo;
    }

    private void estilizarBotao(JButton botao) {
        botao.setBackground(Cores.COR_RODAPE);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setFont(new Font("Arial", Font.BOLD, 13));
        botao.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20)); // padding interno normal
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void preencherTabela(List<Paciente> pacienteList) {
        modeloTabela.setRowCount(0);

        for (Paciente paciente : pacienteList) {
            Vector<String> linha = new Vector<>();
            linha.add(paciente.getNomeCompleto());
            linha.add(paciente.getCpf());
            linha.add(paciente.getDataNascimento().format(DateTimeFormatter.DATE_TIME_FORMATTER));
            linha.add(paciente.getCartaoSUS());
            linha.add(paciente.getDataEntrada().format(DateTimeFormatter.DATE_TIME_FORMATTER));
            linha.add(paciente.getNomeMae());

            modeloTabela.addRow(linha);
        }
    }
}
