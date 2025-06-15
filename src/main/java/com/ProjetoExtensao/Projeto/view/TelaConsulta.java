package com.ProjetoExtensao.Projeto.view;
import javax.swing.*;
import java.awt.*;

public class TelaConsulta extends JFrame {

    public TelaConsulta() {
        setTitle("Consultas");

        int width = 1200;
        int height = 600;
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Títulos no topo
        JLabel tituloLinha1 = new JLabel("RECANTO DO SAGRADO CORAÇÃO");
        tituloLinha1.setFont(new Font("Arial", Font.BOLD, 18));
        tituloLinha1.setBounds(20, 10, 400, 25);
        add(tituloLinha1);

        JLabel tituloLinha2 = new JLabel("ASSISTÊNCIA SOCIAL CATARINA LABOURÉ");
        tituloLinha2.setFont(new Font("Arial", Font.PLAIN, 14));
        tituloLinha2.setBounds(20, 35, 400, 20);
        add(tituloLinha2);

        // Botões topo direito
        JButton adminBtn = new JButton("Administrador Painel");
        JButton sairBtn = new JButton("Sair");

        int gap = 10;
        int sairBtnWidth = sairBtn.getPreferredSize().width + 20;
        int adminBtnWidth = adminBtn.getPreferredSize().width + 20;

        int sairBtnX = width - sairBtnWidth - 20;
        int adminBtnX = sairBtnX - adminBtnWidth - gap;

        adminBtn.setBounds(adminBtnX, 15, adminBtnWidth, 30);
        sairBtn.setBounds(sairBtnX, 15, sairBtnWidth, 30);

        add(adminBtn);
        add(sairBtn);

        // Botão Nova Consulta e Voltar
        // Título da tela
        JLabel titulo = new JLabel("Consultas");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBounds(50, 80, 200, 30);
        add(titulo);

// Botão "Nova Consulta"
        JButton novaConsultaBtn = new JButton("Nova Consulta");
        novaConsultaBtn.setBounds(250, 80, 150, 30); // ao lado do título
        add(novaConsultaBtn);

// Ação do botão: abrir tela de agendamento
        novaConsultaBtn.addActionListener(e -> {
            TelaAgendamentoConsulta agendamento = new TelaAgendamentoConsulta();
            agendamento.setVisible(true);
            dispose(); // fecha a tela atual, ou use setVisible(false) para só esconder
        });


        JButton voltarBtn = new JButton("← Voltar para o painel");
        voltarBtn.setBounds(width - 250, 80, 200, 30);
        add(voltarBtn);

        // PAINEL CENTRAL (Pai)
        JPanel painelCentral = new JPanel(null);
        painelCentral.setBounds(50, 130, 1100, 400);
        painelCentral.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(painelCentral);

        // ===== PAINEL DE FILTROS =====
        JPanel painelFiltro = new JPanel(null);
        painelFiltro.setBounds(10, 10, 1080, 70);
        painelFiltro.setBorder(BorderFactory.createTitledBorder("Parâmetros de Pesquisa"));
        painelCentral.add(painelFiltro);

        JLabel pacienteLabel = new JLabel("Paciente:");
        pacienteLabel.setBounds(20, 20, 60, 20);
        painelFiltro.add(pacienteLabel);

        JTextField pacienteField = new JTextField();
        pacienteField.setBounds(80, 20, 300, 25);
        painelFiltro.add(pacienteField);

        JLabel medicoLabel = new JLabel("Médico:");
        medicoLabel.setBounds(400, 20, 60, 20);
        painelFiltro.add(medicoLabel);

        JTextField medicoField = new JTextField();
        medicoField.setBounds(460, 20, 250, 25);
        painelFiltro.add(medicoField);

        JButton pesquisarBtn = new JButton("Pesquisar");
        pesquisarBtn.setBounds(730, 20, 120, 25);
        painelFiltro.add(pesquisarBtn);

        // ===== PAINEL DE DETALHES =====
        JPanel painelDetalhes = new JPanel(null);
        painelDetalhes.setBounds(10, 90, 1080, 290);
        painelDetalhes.setBorder(BorderFactory.createTitledBorder("Detalhes da Consulta"));
        painelCentral.add(painelDetalhes);

        JTextField consultaNum = new JTextField("Número da consulta");
        consultaNum.setBounds(20, 30, 180, 25);
        painelDetalhes.add(consultaNum);

        JTextField dataConsulta = new JTextField("Data da Consulta: dd/mm/aa");
        dataConsulta.setBounds(220, 30, 200, 25);
        painelDetalhes.add(dataConsulta);

        JTextField horaConsulta = new JTextField("Hora da Consulta: hh:mm");
        horaConsulta.setBounds(440, 30, 180, 25);
        painelDetalhes.add(horaConsulta);

        JTextField pacienteDetalhe = new JTextField("Paciente");
        pacienteDetalhe.setBounds(20, 70, 300, 25);
        painelDetalhes.add(pacienteDetalhe);

        JTextField medicoDetalhe = new JTextField("Médico");
        medicoDetalhe.setBounds(20, 110, 300, 25);
        painelDetalhes.add(medicoDetalhe);

        JTextField especialidade = new JTextField("Especialidade");
        especialidade.setBounds(340, 110, 300, 25);
        painelDetalhes.add(especialidade);

        JTextField triagem = new JTextField("Queixas do paciente");
        triagem.setBounds(20, 150, 620, 25);
        painelDetalhes.add(triagem);

        JTextField medicacao = new JTextField("Medicação receitada");
        medicacao.setBounds(20, 190, 620, 25);
        painelDetalhes.add(medicacao);

        JLabel altaLabel = new JLabel("Alta:");
        altaLabel.setBounds(20, 230, 40, 25);
        painelDetalhes.add(altaLabel);

        JRadioButton altaSim = new JRadioButton("Sim");
        altaSim.setBounds(70, 230, 60, 25);
        painelDetalhes.add(altaSim);

        JRadioButton altaNao = new JRadioButton("Não");
        altaNao.setBounds(140, 230, 60, 25);
        painelDetalhes.add(altaNao);

        ButtonGroup altaGroup = new ButtonGroup();
        altaGroup.add(altaSim);
        altaGroup.add(altaNao);

        // Rodapé com data
        JLabel rodape = new JLabel("14/06/2025 21:10");
        rodape.setBounds(width - 180, height - 70, 150, 25);
        add(rodape);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaConsulta().setVisible(true);
        });
    }
}