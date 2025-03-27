package org.financeiro.views;

import org.financeiro.controllers.LoginController;
import org.financeiro.listeners.GetActionListener;

import javax.swing.*;
import java.awt.*;

public class PainelLogin extends JPanel {

    public PainelLogin() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.PINK);

        JButton login = new JButton("Login");
        login.addActionListener(new GetActionListener(new LoginController()));

        this.add(login, BorderLayout.WEST);
    }
}
