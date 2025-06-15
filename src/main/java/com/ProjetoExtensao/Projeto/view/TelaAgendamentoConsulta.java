package com.ProjetoExtensao.Projeto.view;

import javax.swing.*;
import java.awt.*;

public class TelaAgendamentoConsulta extends JFrame {

    public TelaAgendamentoConsulta() {
        setTitle("Agendamento de Consulta");
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Título / Cabeçalho
        JLabel tituloLinha1 = new JLabel("RECANTO DO SAGRADO CORAÇÃO");
        tituloLinha1.setFont(new Font("Arial", Font.BOLD, 18));
        tituloLinha1.setBounds(20, 10, 400, 25);
        add(tituloLinha1);

        JLabel tituloLinha2 = new JLabel("ASSISTÊNCIA SOCIAL CATARINA LABOURÉ");
        tituloLinha2.setFont(new Font("Arial", Font.PLAIN, 14));
        tituloLinha2.setBounds(20, 35, 400, 20);
        add(tituloLinha2);

        JButton adminBtn = new JButton("Administrador Painel");
        JButton sairBtn = new JButton("Sair");

        int gap = 10;
        int sairBtnWidth = sairBtn.getPreferredSize().width + 20;
        int adminBtnWidth = adminBtn.getPreferredSize().width + 20;

        int sairBtnX = getWidth() - sairBtnWidth - 50;
        int adminBtnX = sairBtnX - adminBtnWidth - gap;

        adminBtn.setBounds(adminBtnX, 15, adminBtnWidth, 30);
        sairBtn.setBounds(sairBtnX, 15, sairBtnWidth, 30);

        add(adminBtn);
        add(sairBtn);

        // Título da tela
        JLabel titulo = new JLabel("Agendamento de Consulta");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setBounds(50, 80, 400, 30);
        add(titulo);

        // Botão "← Voltar para consulta"
        JButton voltarBtn = new JButton("← Voltar para consulta");
        voltarBtn.setBounds(50, 120, 180, 30);
        add(voltarBtn);

        // Ação do botão
        voltarBtn.addActionListener(e -> {
            TelaConsulta consultas = new TelaConsulta();
            consultas.setVisible(true);
            dispose(); // Fecha esta tela
        });

        // Painel Central
        JPanel painelCentral = new JPanel(null);
        painelCentral.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        painelCentral.setBounds(100, 160, 1000, 300);
        add(painelCentral);

        // Campo: Nome do Paciente
        JLabel nomeLabel = new JLabel("Nome do Paciente");
        nomeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nomeLabel.setBounds(20, 20, 200, 20);
        painelCentral.add(nomeLabel);

        JTextField nomeField = new JTextField("Digite o nome do paciente");
        nomeField.setBounds(20, 45, 950, 30);
        painelCentral.add(nomeField);

        // Campo: Médico
        JLabel medicoLabel = new JLabel("Selecionar Médico");
        medicoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        medicoLabel.setBounds(20, 90, 200, 20);
        painelCentral.add(medicoLabel);

        JComboBox<String> medicoCombo = new JComboBox<>();
        medicoCombo.addItem("Selecione o médico");
        medicoCombo.setBounds(20, 115, 450, 30);
        painelCentral.add(medicoCombo);

        // Campo: Especialidade
        JLabel espLabel = new JLabel("Selecionar Especialidade");
        espLabel.setFont(new Font("Arial", Font.BOLD, 14));
        espLabel.setBounds(500, 90, 200, 20);
        painelCentral.add(espLabel);

        JComboBox<String> espCombo = new JComboBox<>();
        espCombo.addItem("Selecione a especialidade");
        espCombo.setBounds(500, 115, 470, 30);
        painelCentral.add(espCombo);

        // Campo: Data
        JLabel dataLabel = new JLabel("Data");
        dataLabel.setFont(new Font("Arial", Font.BOLD, 14));
        dataLabel.setBounds(20, 165, 100, 20);
        painelCentral.add(dataLabel);

        JTextField dataField = new JTextField("dd/mm/aaaa");
        dataField.setBounds(20, 190, 300, 30);
        painelCentral.add(dataField);

        // Campo: Horário
        JLabel horarioLabel = new JLabel("Horário");
        horarioLabel.setFont(new Font("Arial", Font.BOLD, 14));
        horarioLabel.setBounds(350, 165, 100, 20);
        painelCentral.add(horarioLabel);

        JTextField horarioField = new JTextField("hh:mm");
        horarioField.setBounds(350, 190, 300, 30);
        painelCentral.add(horarioField);

        // Botão Agendar
        JButton agendarBtn = new JButton("Agendar");
        agendarBtn.setBounds(850, 230, 100, 30);
        painelCentral.add(agendarBtn);

        // Rodapé
        JLabel rodape = new JLabel("14/06/2025 21:10");
        rodape.setBounds(990, 520, 150, 25);
        add(rodape);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaAgendamentoConsulta().setVisible(true);
        });
    }
}