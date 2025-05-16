
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class OnlineReservationSystem {
    private static HashMap<String, String> users = new HashMap<>();
    private static Map<String, String> trainMap = new HashMap<>();
    private static File dataFile = new File("reservations.txt");

    public static void main(String[] args) {
        users.put("admin", "1234");
        trainMap.put("12345", "Chennai Express");
        trainMap.put("67890", "Madurai Superfast");

        showLoginForm();
    }

    private static void showLoginForm() {
        JFrame frame = new JFrame("Login");
        JLabel userLabel = new JLabel("Username:");
        JLabel passLabel = new JLabel("Password:");
        JTextField userText = new JTextField();
        JPasswordField passText = new JPasswordField();
        JButton loginButton = new JButton("Login");

        frame.setSize(300, 180);
        frame.setLayout(null);
        userLabel.setBounds(20, 20, 80, 25);
        passLabel.setBounds(20, 60, 80, 25);
        userText.setBounds(100, 20, 160, 25);
        passText.setBounds(100, 60, 160, 25);
        loginButton.setBounds(100, 100, 80, 25);

        loginButton.addActionListener(e -> {
            String user = userText.getText();
            String pass = new String(passText.getPassword());
            if (users.containsKey(user) && users.get(user).equals(pass)) {
                frame.dispose();
                showMainMenu();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid login");
            }
        });

        frame.add(userLabel); frame.add(userText);
        frame.add(passLabel); frame.add(passText);
        frame.add(loginButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void showMainMenu() {
        JFrame frame = new JFrame("Reservation System");
        JButton reserveBtn = new JButton("Reserve Ticket");
        JButton cancelBtn = new JButton("Cancel Ticket");

        frame.setSize(300, 150);
        frame.setLayout(new FlowLayout());
        frame.add(reserveBtn);
        frame.add(cancelBtn);

        reserveBtn.addActionListener(e -> showReservationForm());
        cancelBtn.addActionListener(e -> showCancellationForm());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void showReservationForm() {
        JFrame frame = new JFrame("Reserve Ticket");
        frame.setSize(350, 400);
        frame.setLayout(null);

        JLabel nameLbl = new JLabel("Name:");
        JLabel trainNumLbl = new JLabel("Train Number:");
        JLabel trainNameLbl = new JLabel("Train Name:");
        JLabel classLbl = new JLabel("Class:");
        JLabel dateLbl = new JLabel("Date (YYYY-MM-DD):");
        JLabel fromLbl = new JLabel("From:");
        JLabel toLbl = new JLabel("To:");

        JTextField nameTxt = new JTextField();
        JTextField trainNumTxt = new JTextField();
        JTextField trainNameTxt = new JTextField();
        trainNameTxt.setEditable(false);
        JTextField classTxt = new JTextField();
        JTextField dateTxt = new JTextField();
        JTextField fromTxt = new JTextField();
        JTextField toTxt = new JTextField();

        JButton reserveBtn = new JButton("Reserve");

        nameLbl.setBounds(20, 20, 120, 25);
        nameTxt.setBounds(150, 20, 150, 25);
        trainNumLbl.setBounds(20, 60, 120, 25);
        trainNumTxt.setBounds(150, 60, 150, 25);
        trainNameLbl.setBounds(20, 100, 120, 25);
        trainNameTxt.setBounds(150, 100, 150, 25);
        classLbl.setBounds(20, 140, 120, 25);
        classTxt.setBounds(150, 140, 150, 25);
        dateLbl.setBounds(20, 180, 120, 25);
        dateTxt.setBounds(150, 180, 150, 25);
        fromLbl.setBounds(20, 220, 120, 25);
        fromTxt.setBounds(150, 220, 150, 25);
        toLbl.setBounds(20, 260, 120, 25);
        toTxt.setBounds(150, 260, 150, 25);
        reserveBtn.setBounds(120, 300, 100, 30);

        trainNumTxt.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String num = trainNumTxt.getText();
                trainNameTxt.setText(trainMap.getOrDefault(num, ""));
            }
        });

        reserveBtn.addActionListener(e -> {
            String pnr = UUID.randomUUID().toString().substring(0, 8);
            String record = pnr + "," + nameTxt.getText() + "," + trainNumTxt.getText() + "," +
                    trainNameTxt.getText() + "," + classTxt.getText() + "," + dateTxt.getText() + "," +
                    fromTxt.getText() + "," + toTxt.getText();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile, true))) {
                writer.write(record);
                writer.newLine();
                JOptionPane.showMessageDialog(frame, "Reservation successful! PNR: " + pnr);
                frame.dispose();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error saving reservation.");
            }
        });

        frame.add(nameLbl); frame.add(nameTxt);
        frame.add(trainNumLbl); frame.add(trainNumTxt);
        frame.add(trainNameLbl); frame.add(trainNameTxt);
        frame.add(classLbl); frame.add(classTxt);
        frame.add(dateLbl); frame.add(dateTxt);
        frame.add(fromLbl); frame.add(fromTxt);
        frame.add(toLbl); frame.add(toTxt);
        frame.add(reserveBtn);
        frame.setVisible(true);
    }

    private static void showCancellationForm() {
        JFrame frame = new JFrame("Cancel Ticket");
        frame.setSize(300, 150);
        frame.setLayout(new FlowLayout());

        JTextField pnrField = new JTextField(15);
        JButton searchBtn = new JButton("Cancel Ticket");

        frame.add(new JLabel("Enter PNR:"));
        frame.add(pnrField);
        frame.add(searchBtn);

        searchBtn.addActionListener(e -> {
            String pnr = pnrField.getText();
            List<String> updated = new ArrayList<>();
            boolean found = false;

            try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith(pnr + ",")) {
                        found = true;
                        int confirm = JOptionPane.showConfirmDialog(frame, "Confirm cancellation:
" + line);
                        if (confirm != JOptionPane.YES_OPTION) {
                            updated.add(line);
                        }
                    } else {
                        updated.add(line);
                    }
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error reading data.");
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile))) {
                for (String s : updated) {
                    writer.write(s);
                    writer.newLine();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error writing data.");
            }

            if (found) {
                JOptionPane.showMessageDialog(frame, "Cancellation complete (if confirmed).");
            } else {
                JOptionPane.showMessageDialog(frame, "PNR not found.");
            }
        });

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
