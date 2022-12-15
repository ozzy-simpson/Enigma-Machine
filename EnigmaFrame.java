import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EnigmaFrame extends JFrame {

    private JComboBox<Integer> iRotor;
    private JComboBox<Integer> mRotor;
    private JComboBox<Integer> oRotor;

    private final Integer[] rotorOptions = {
        1, 2, 3, 4, 5
    };

    private JTextField startPositions;

    private JTextArea input;
    private JTextArea output;

    private JButton bEncrypt;
    private JButton bDecrypt;

    public EnigmaFrame(){
        super();
        this.setTitle("Enigma GUI");

        // Create the rotor selectors
        iRotor = new JComboBox<Integer>(rotorOptions); //dropdown options of inner rotor options
        mRotor = new JComboBox<Integer>(rotorOptions); //dropdown options of middle rotor options
        oRotor = new JComboBox<Integer>(rotorOptions); //dropdown options of outer rotor options

        // Create the start position field
        startPositions = new JTextField("###", 3);

        // Create the input and output text areas and labels
        JLabel inputLabel = new JLabel("Input");
        inputLabel.setPreferredSize(new Dimension(50,25));
        input = new JTextArea(5, 20);
        input.setLineWrap(true);
        input.setMargin(new Insets(10,10,10,10));

        JLabel outputLabel = new JLabel("Output");
        outputLabel.setPreferredSize(new Dimension(50,25));
        output = new JTextArea(5, 20);
        output.setLineWrap(true);
        output.setMargin(new Insets(10,10,10,10));
        output.setEditable(false);

        // Create the buttons
        bEncrypt = new JButton("Encrypt");
        bEncrypt.addActionListener(new ActionListener() {
                //implement the one method here
                //shares the name space with the whole class
                public void actionPerformed(ActionEvent e) {
                    //get the input from the text area
                    String inputText = input.getText();
                    //get the rotor settings from the dropdowns
                    int inner = (int) iRotor.getSelectedItem();
                    int middle = (int) mRotor.getSelectedItem();
                    int outer = (int) oRotor.getSelectedItem();
                    //get the start positions from the text field
                    String start = startPositions.getText();

                    // validate settings and input
                    if (!validateSettingsInput(start, inner, middle, outer, inputText)) {
                        return;
                    }

                    //create an enigma object
                    Enigma enigma = new Enigma(inner, middle, outer, start);
                    //encrypt the input
                    String outputText = enigma.encrypt(inputText);
                    //set the output text area to the encrypted text
                    output.setText(outputText);
                }
            });
        bDecrypt = new JButton("Decrypt");
        bDecrypt.addActionListener(new ActionListener() {
                //implement the one method here
                //shares the name space with the whole class
                public void actionPerformed(ActionEvent e) {
                    //get the input from the text area
                    String inputText = input.getText();
                    //get the rotor settings from the dropdowns
                    int inner = (int) iRotor.getSelectedItem();
                    int middle = (int) mRotor.getSelectedItem();
                    int outer = (int) oRotor.getSelectedItem();
                    //get the start positions from the text field
                    String start = startPositions.getText();

                    // validate settings and input
                    if (!validateSettingsInput(start, inner, middle, outer, inputText)) {
                        return;
                    }

                    //create an enigma object
                    Enigma enigma = new Enigma(inner, middle, outer, start);
                    //encrypt the input
                    String outputText = enigma.decrypt(inputText);
                    //set the output text area to the encrypted text
                    output.setText(outputText);
                }
            });

        // Create the tabbed layout
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setForeground(Color.black);

        // Create layout for input/output panel
        JPanel ioPanel = new JPanel();
        ioPanel.setLayout(new BoxLayout(ioPanel, BoxLayout.PAGE_AXIS));
        ioPanel.add(Box.createRigidArea(new Dimension(0,5)));

        // Input panel
        JPanel iPanel = new JPanel();
        iPanel.setLayout(new BoxLayout(iPanel, BoxLayout.LINE_AXIS));
        iPanel.add(Box.createRigidArea(new Dimension(5,0)));
        iPanel.add(inputLabel);
        iPanel.add(input);
        iPanel.add(Box.createRigidArea(new Dimension(5,0)));

        // Button panel
        JPanel bPanel = new JPanel();
        bPanel.setLayout(new FlowLayout());
        bPanel.add(bEncrypt);
        bPanel.add(bDecrypt);
        
        // Output panel
        JPanel oPanel = new JPanel();
        oPanel.setLayout(new BoxLayout(oPanel, BoxLayout.LINE_AXIS));
        oPanel.add(Box.createRigidArea(new Dimension(5,0)));
        oPanel.add(outputLabel);
        oPanel.add(output);
        oPanel.add(Box.createRigidArea(new Dimension(5,0)));

        ioPanel.add(iPanel);
        ioPanel.add(bPanel);
        ioPanel.add(oPanel);
        ioPanel.add(Box.createRigidArea(new Dimension(0,5)));

        // Create layout for settings panel
        JPanel sPanel = new JPanel();
        sPanel.setLayout(new FlowLayout());
        sPanel.add(new JLabel("Inner:"));
        sPanel.add(iRotor);
        sPanel.add(new JLabel("Middle:"));
        sPanel.add(mRotor);
        sPanel.add(new JLabel("Outer:"));
        sPanel.add(oRotor);
        sPanel.add(new JLabel("Start Positions:"));
        sPanel.add(startPositions);

        // Add panels to tabbed layout
        tabbedPane.addTab("Encrypt/Decrypt", ioPanel);
        tabbedPane.addTab("Settings", sPanel);

        this.add(tabbedPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

    }

    private Boolean validateSettingsInput(String start, int inner, int middle, int outer, String input) {
        // Verify that start positions are valid
        if (start.length() != 3) {
            output.setText("ERROR: Start positions must be 3 characters long");
            return false;
        }
        for (int i = 0; i < start.length(); i++) {
            if ((start.charAt(i) < 'A' || start.charAt(i) > 'Z') && start.charAt(i) != '#') {
                output.setText("ERROR: Start position characters must be uppercase letters or #");
                return false;
            }
        }

        // Verify that input is valid
        for (int i = 0; i < input.length(); i++) {
            if ((input.charAt(i) < 'A' || input.charAt(i) > 'Z') && input.charAt(i) != '#') {
                output.setText("ERROR: Input characters must be uppercase letters or #");
                return false;
            }
        }

        return true;
    }

}
