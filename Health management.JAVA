import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Class representing a patient
class Patient {
    private String id;
    private String name;
    private int age;
    private String disease;

    public Patient(String id, String name, int age, String disease) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.disease = disease;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDisease() {
        return disease;
    }

    @Override
    public String toString() {
        return "Patient ID: " + id + "\nName: " + name + "\nAge: " + age + "\nDisease: " + disease + "\n";
    }
}

// Class representing the healthcare management system
public class HealthcareManagementSystem extends JFrame {
    private ArrayList<Patient> patients;
    private JTextArea displayArea;

    public HealthcareManagementSystem() {
        patients = new ArrayList<>();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Healthcare Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        JLabel idLabel = new JLabel("Patient ID:");
        JTextField idField = new JTextField();
        JLabel nameLabel = new JLabel("Patient Name:");
        JTextField nameField = new JTextField();
        JLabel ageLabel = new JLabel("Patient Age:");
        JTextField ageField = new JTextField();
        JLabel diseaseLabel = new JLabel("Patient Disease:");
        JTextField diseaseField = new JTextField();

        JButton addButton = new JButton("Add Patient");
        JButton searchButton = new JButton("Search Patient");
        JButton deleteButton = new JButton("Delete Patient");
        JButton displayButton = new JButton("Display All Records");

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        panel.add(idLabel);
        panel.add(idField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(diseaseLabel);
        panel.add(diseaseField);
        panel.add(addButton);
        panel.add(searchButton);
        panel.add(deleteButton);
        panel.add(displayButton);

        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Add patient action
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String disease = diseaseField.getText();
                addPatient(id, name, age, disease);
                clearFields(idField, nameField, ageField, diseaseField);
            }
        });

        // Search patient action
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                Patient patient = searchPatient(id);
                if (patient != null) {
                    displayArea.setText(patient.toString());
                } else {
                    displayArea.setText("Patient not found.");
                }
            }
        });

        // Delete patient action
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                if (deletePatient(id)) {
                    displayArea.setText("Patient deleted successfully.");
                } else {
                    displayArea.setText("Patient not found.");
                }
            }
        });

        // Display all records action
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayAllRecords();
            }
        });
    }

    // Add a new patient
    public void addPatient(String id, String name, int age, String disease) {
        Patient newPatient = new Patient(id, name, age, disease);
        patients.add(newPatient);
        JOptionPane.showMessageDialog(this, "Patient added successfully.");
    }

    // Search for a patient by ID
    public Patient searchPatient(String id) {
        for (Patient patient : patients) {
            if (patient.getId().equals(id)) {
                return patient;
            }
        }
        return null;
    }

    // Delete a patient by ID
    public boolean deletePatient(String id) {
        for (Patient patient : patients) {
            if (patient.getId().equals(id)) {
                patients.remove(patient);
                return true;
            }
        }
        return false;
    }

    // Display all patients' records
    public void displayAllRecords() {
        StringBuilder records = new StringBuilder();
        for (Patient patient : patients) {
            records.append(patient.toString()).append("\n");
        }
        displayArea.setText(records.toString());
    }

    private void clearFields(JTextField idField, JTextField nameField, JTextField ageField, JTextField diseaseField) {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        diseaseField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HealthcareManagementSystem().setVisible(true);
            }
        });
    }
}
