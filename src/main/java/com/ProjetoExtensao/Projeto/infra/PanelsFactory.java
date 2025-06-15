package com.ProjetoExtensao.Projeto.infra;

import com.ProjetoExtensao.Projeto.infra.IconManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@AllArgsConstructor
public class PanelsFactory extends JFrame {
    private IconManager iconManager;

    public JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout(10, 10));
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(new EmptyBorder(15, 25, 15, 25));

        ImageIcon logoIcon = iconManager.createScaledIcon("/assets/logo.png", 50, 50);
        JLabel logoLabel = new JLabel(logoIcon);

        JPanel companyInfoPanel = new JPanel();
        companyInfoPanel.setLayout(new BoxLayout(companyInfoPanel, BoxLayout.Y_AXIS));
        companyInfoPanel.setOpaque(false);

        JLabel nameLabel = new JLabel("RECANTO DO SAGRADO CORAÇÃO");
        nameLabel.setForeground(Cores.COR_RODAPE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel subtitleLabel = new JLabel("ASSISTÊNCIA SOCIAL CATARINA LABOURÉ");
        subtitleLabel.setForeground(Color.LIGHT_GRAY);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 10));

        companyInfoPanel.add(nameLabel);
        companyInfoPanel.add(subtitleLabel);

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        leftPanel.setOpaque(false);
        leftPanel.add(logoLabel);
        leftPanel.add(companyInfoPanel);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonsPanel.setOpaque(false);

        JButton adminButton = new JButton("Administrador Painel", iconManager.createIcon("/assets/admin.png"));
        JButton exitButton = new JButton("Sair", iconManager.createIcon("/assets/exit.png"));
        // JButton refreshButton = new JButton(createIcon("/assets/refresh.png"));

        for (JButton btn : new JButton[]{adminButton, exitButton}) {
            btn.setBackground(new Color(Cores.COR_RODAPE.getRGB()));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setBorder(new EmptyBorder(8, 12, 8, 12));
//            if (btn == refreshButton) {
//                btn.setBorder(new EmptyBorder(8, 8, 8, 8));
//            }
        }

        buttonsPanel.add(adminButton);
        buttonsPanel.add(exitButton);
        // buttonsPanel.add(refreshButton);

        headerPanel.add(leftPanel, BorderLayout.WEST);
        headerPanel.add(buttonsPanel, BorderLayout.EAST);

        return headerPanel;
    }

    public JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setBackground(Cores.COR_RODAPE);
        footerPanel.setPreferredSize(new Dimension(getWidth(), 40));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        JLabel dateTimeLabel = new JLabel(sdf.format(new Date()));
        dateTimeLabel.setForeground(Color.WHITE);
        dateTimeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        dateTimeLabel.setBorder(new EmptyBorder(0, 0, 0, 25));

        footerPanel.add(dateTimeLabel);

        return footerPanel;
    }
}