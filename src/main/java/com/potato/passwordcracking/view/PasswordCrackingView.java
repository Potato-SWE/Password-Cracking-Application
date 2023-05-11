package com.potato.passwordcracking.view;

import javax.swing.*;

public class PasswordCrackingView extends JFrame {

    private JPanel primaryPanel;
    private JPanel titlePanel;
    private JPanel optionsPanel;
    private JRadioButton md5RadioButton;
    private JRadioButton sha1RadioButton;
    private JRadioButton sha256RadioButton;
    private JRadioButton bruteForceRadioButton;
    private JRadioButton dictionaryAttackRadioButton;
    private JPanel setupPanel;
    private JPanel algorithmPanel;
    private JPanel crackingServicePanel;
    private JLabel titleLabel;
    private JLabel setupLabel;
    private JTextField dictionaryPath;
    private JCheckBox settingsOverride;
    private JButton crackPasswordsButton;
    private JPanel settingsAndLogPanel;
    private JButton settingsButton;
    private JPanel settingsPanel;
    private JLabel outputLabel;

    public void run() {

        setTitle("Password cracking application.");
        setContentPane(primaryPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        //set up hashing algorithm button group
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(md5RadioButton);
        buttonGroup.add(sha1RadioButton);
        buttonGroup.add(sha256RadioButton);

        //button action listeners
        crackPasswordsButton.addActionListener((event) -> {
            crackPasswords();
        });

        settingsButton.addActionListener((event) -> {
            setResponse("Settings clicked!");
        });
    }

    private void crackPasswords() {
        setResponse("Crack passwords button clicked!");
    }

    private void setResponse(String message) {
        outputLabel.setText(message);
        update(getGraphics());
    }
}
