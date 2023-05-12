package com.potato.passwordcracking.view;

import com.potato.passwordcracking.PasswordCrackingApplication;
import com.potato.passwordcracking.constant.CrackingStrategy;
import com.potato.passwordcracking.constant.HashingAlgorithm;
import com.potato.passwordcracking.controller.PasswordCrackingController;
import com.potato.passwordcracking.model.PasswordCrackingRequest;
import com.potato.passwordcracking.model.PasswordCrackingResponse;
import com.potato.passwordcracking.service.ResponseOutputService;
import com.potato.passwordcracking.settings.SettingsManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PasswordCrackingView extends JFrame {

    private final SettingsManager settings;
    private final PasswordCrackingController controller = new PasswordCrackingController();
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
    private JTextField passwordsPathTextField;
    private JButton crackPasswordsButton;
    private JPanel settingsAndLogPanel;
    private JButton settingsButton;
    private JPanel settingsPanel;
    private JLabel outputLabel;

    public PasswordCrackingView(SettingsManager settings) {
        this.settings = settings;
    }

    public void run() {

        setTitle("Password cracking application.");
        setContentPane(primaryPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        //set up hashing algorithm button group
        ButtonGroup hashRadioButtonGroup = new ButtonGroup();
        hashRadioButtonGroup.add(md5RadioButton);
        hashRadioButtonGroup.add(sha1RadioButton);
        hashRadioButtonGroup.add(sha256RadioButton);

        ButtonGroup strategyRadioButtonGroup = new ButtonGroup();
        strategyRadioButtonGroup.add(bruteForceRadioButton);
        strategyRadioButtonGroup.add(dictionaryAttackRadioButton);

        //button action listeners
        crackPasswordsButton.addActionListener((event) -> {
            crackPasswords();
        });

        settingsButton.addActionListener((event) -> {
            setResponse("Settings clicked!");
        });
    }

    private void crackPasswords() {
        PasswordCrackingResponse response = controller.crackPasswords(buildRequest());
        try {
            String outputPath = ResponseOutputService.write(response);
            setResponse("Output file name: " + outputPath);
        } catch (IOException e) {
            setResponse(e.getMessage());
        }
    }

    private PasswordCrackingRequest buildRequest() {
        PasswordCrackingRequest request = new PasswordCrackingRequest();

        HashingAlgorithm algorithm = null;
        CrackingStrategy strategy = null;
        List<String> hashedPasswords = null;

        if (md5RadioButton.isSelected()) {
            algorithm = HashingAlgorithm.MD5;
        }

        if (sha1RadioButton.isSelected()) {
            algorithm = HashingAlgorithm.SHA1;
        }

        if (sha256RadioButton.isSelected()) {
            algorithm = HashingAlgorithm.SHA256;
        }

        if (bruteForceRadioButton.isSelected()) {
            strategy = CrackingStrategy.BRUTE_FORCE;
        }

        if (dictionaryAttackRadioButton.isSelected()) {
            strategy = CrackingStrategy.DICTIIONARY;
        }

        hashedPasswords = readHashedPasswordsFile();
        if (hashedPasswords == null) {
            setResponse("Error: Failed to read hashed passwords file.");
            return null;
        }

        request.setAlgorithm(algorithm);
        request.setStrategy(strategy);
        request.setHashedPasswords(hashedPasswords);

        return request;
    }

    private List<String> readHashedPasswordsFile() {
        return readHashedPasswordsFile(PasswordCrackingApplication.APPLICATION_DIRECTORY + "/resources/" + passwordsPathTextField.getText());
    }

    private List<String> readHashedPasswordsFile(String filePath) {

        if (filePath == null) {
            return new ArrayList<>();
        }

        File file = new File(filePath);

        if (!file.exists()) {
            return new ArrayList<>();
        }
        try {
            List<String> fileData = new ArrayList<>();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while (bufferedReader.ready()) {
                fileData.add(bufferedReader.readLine());
            }
            bufferedReader.close();
            return fileData;
        } catch (IOException e) {
            return null;
        }

    }

    private void setResponse(String message) {
        outputLabel.setText(message);
        update(getGraphics());
    }
}
