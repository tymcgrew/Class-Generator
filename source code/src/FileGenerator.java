import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileGenerator extends JFrame {

	private JPanel contentPane;
	private JTextField txtClassName;
	private JTextField txtEnumName;
	private JButton btnAddInterface;
	private JTextField txtInterfaceName;
	private JTextArea textArea;
	private JSpinner classPropertiesCount;
	private JTextField txtNumberOfProperties;
	private JTextField txtNumberOfTypesinstances;
	private JTextField txtName;
	private JSpinner enumPropertiesCount;
	private JSpinner enumTypesCount;
	private JButton btnReset;
	private JButton btnDone;

	private static HashMap<String, Integer> classes; 
	private static HashMap<String, String> enums;
	private static ArrayList<String> interfaces;
	private JButton btnGenerateExample;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				classes = new HashMap<String, Integer>();
				enums = new HashMap<String, String>();
				interfaces = new ArrayList<String>();
				try {
					FileGenerator frame = new FileGenerator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FileGenerator() {
		setBackground(Color.DARK_GRAY);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Tahoma", Font.BOLD, 24));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{107, 257, 183, 220, 0};
		gbl_contentPane.rowHeights = new int[]{0, 64, 64, 64, 128, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JButton btnAddClass = new JButton("Add Class");
		btnAddClass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!txtClassName.getText().equals(""))
					classes.put(txtClassName.getText(), (Integer)classPropertiesCount.getValue());
				textArea.setText(writeText());
				System.out.println("Class Added");
			}


		});
		btnAddClass.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAddClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtName.setText("Name");
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.insets = new Insets(0, 0, 5, 5);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 1;
		gbc_txtName.gridy = 0;
		contentPane.add(txtName, gbc_txtName);
		txtName.setColumns(10);

		txtNumberOfProperties = new JTextField();
		txtNumberOfProperties.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNumberOfProperties.setEditable(false);
		txtNumberOfProperties.setText("Number of Properties");
		GridBagConstraints gbc_txtNumberOfProperties = new GridBagConstraints();
		gbc_txtNumberOfProperties.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNumberOfProperties.insets = new Insets(0, 0, 5, 5);
		gbc_txtNumberOfProperties.gridx = 2;
		gbc_txtNumberOfProperties.gridy = 0;
		contentPane.add(txtNumberOfProperties, gbc_txtNumberOfProperties);
		txtNumberOfProperties.setColumns(10);

		txtNumberOfTypesinstances = new JTextField();
		txtNumberOfTypesinstances.setEditable(false);
		txtNumberOfTypesinstances.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNumberOfTypesinstances.setText("Number of Types/Instances");
		GridBagConstraints gbc_txtNumberOfTypesinstances = new GridBagConstraints();
		gbc_txtNumberOfTypesinstances.insets = new Insets(0, 0, 5, 0);
		gbc_txtNumberOfTypesinstances.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNumberOfTypesinstances.gridx = 3;
		gbc_txtNumberOfTypesinstances.gridy = 0;
		contentPane.add(txtNumberOfTypesinstances, gbc_txtNumberOfTypesinstances);
		txtNumberOfTypesinstances.setColumns(10);
		GridBagConstraints gbc_btnAddClass = new GridBagConstraints();
		gbc_btnAddClass.fill = GridBagConstraints.BOTH;
		gbc_btnAddClass.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddClass.gridx = 0;
		gbc_btnAddClass.gridy = 1;
		contentPane.add(btnAddClass, gbc_btnAddClass);

		txtClassName = new JTextField();
		txtClassName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_txtClassName = new GridBagConstraints();
		gbc_txtClassName.fill = GridBagConstraints.BOTH;
		gbc_txtClassName.insets = new Insets(0, 0, 5, 5);
		gbc_txtClassName.gridx = 1;
		gbc_txtClassName.gridy = 1;
		contentPane.add(txtClassName, gbc_txtClassName);
		txtClassName.setColumns(10);

		classPropertiesCount = new JSpinner();
		classPropertiesCount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_classPropertiesCount = new GridBagConstraints();
		gbc_classPropertiesCount.fill = GridBagConstraints.BOTH;
		gbc_classPropertiesCount.insets = new Insets(0, 0, 5, 5);
		gbc_classPropertiesCount.gridx = 2;
		gbc_classPropertiesCount.gridy = 1;
		contentPane.add(classPropertiesCount, gbc_classPropertiesCount);

		JButton btnAddEnum = new JButton("Add Enum");
		btnAddEnum.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!txtEnumName.getText().equals(""))
					enums.put(txtEnumName.getText(), (int)enumPropertiesCount.getValue() + "-" + (int)enumTypesCount.getValue());
				textArea.setText(writeText());
				System.out.println("Enum added");
			}
		});
		btnAddEnum.setFont(new Font("Tahoma", Font.BOLD, 20));
		GridBagConstraints gbc_btnAddEnum = new GridBagConstraints();
		gbc_btnAddEnum.fill = GridBagConstraints.BOTH;
		gbc_btnAddEnum.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddEnum.gridx = 0;
		gbc_btnAddEnum.gridy = 2;
		contentPane.add(btnAddEnum, gbc_btnAddEnum);

		txtEnumName = new JTextField();
		txtEnumName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_txtEnterEnumName = new GridBagConstraints();
		gbc_txtEnterEnumName.insets = new Insets(0, 0, 5, 5);
		gbc_txtEnterEnumName.fill = GridBagConstraints.BOTH;
		gbc_txtEnterEnumName.gridx = 1;
		gbc_txtEnterEnumName.gridy = 2;
		contentPane.add(txtEnumName, gbc_txtEnterEnumName);
		txtEnumName.setColumns(10);

		enumPropertiesCount = new JSpinner();
		enumPropertiesCount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_enumPropertiesCount = new GridBagConstraints();
		gbc_enumPropertiesCount.fill = GridBagConstraints.BOTH;
		gbc_enumPropertiesCount.insets = new Insets(0, 0, 5, 5);
		gbc_enumPropertiesCount.gridx = 2;
		gbc_enumPropertiesCount.gridy = 2;
		contentPane.add(enumPropertiesCount, gbc_enumPropertiesCount);

		enumTypesCount = new JSpinner();
		enumTypesCount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_enumTypesCount = new GridBagConstraints();
		gbc_enumTypesCount.fill = GridBagConstraints.BOTH;
		gbc_enumTypesCount.insets = new Insets(0, 0, 5, 0);
		gbc_enumTypesCount.gridx = 3;
		gbc_enumTypesCount.gridy = 2;
		contentPane.add(enumTypesCount, gbc_enumTypesCount);

		btnAddInterface = new JButton("Add Interface");
		btnAddInterface.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!txtInterfaceName.getText().equals(""))
					interfaces.add(txtInterfaceName.getText());
				textArea.setText(writeText());
				System.out.println("Interface added");
			}
		});
		btnAddInterface.setFont(new Font("Tahoma", Font.BOLD, 20));
		GridBagConstraints gbc_btnAddInterface = new GridBagConstraints();
		gbc_btnAddInterface.fill = GridBagConstraints.BOTH;
		gbc_btnAddInterface.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddInterface.gridx = 0;
		gbc_btnAddInterface.gridy = 3;
		contentPane.add(btnAddInterface, gbc_btnAddInterface);

		txtInterfaceName = new JTextField();
		txtInterfaceName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_txtInterfaceName = new GridBagConstraints();
		gbc_txtInterfaceName.insets = new Insets(0, 0, 5, 5);
		gbc_txtInterfaceName.fill = GridBagConstraints.BOTH;
		gbc_txtInterfaceName.gridx = 1;
		gbc_txtInterfaceName.gridy = 3;
		contentPane.add(txtInterfaceName, gbc_txtInterfaceName);
		txtInterfaceName.setColumns(10);

		btnGenerateExample = new JButton("Generate Example");
		btnGenerateExample.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
				PrintWriter pw = new PrintWriter("input.txt");
				pw.print("**** Data with dashes needs to be filled in manually and you may need to add lines, see example file for reference\r\n" + 
						"Class:\r\n" + 
						"    Name:   Car\r\n" + 
						"    Abstract:   false\r\n" + 
						"    Extends:    Rectangle\r\n" + 
						"    Implements: IMoveable, IDrawable\r\n" + 
						"    CloneMethod:    true\r\n" + 
						"    EmptyConstructor:   true\r\n" + 
						"    WorkhorseConstructor:   true\r\n" + 
						"    CopyConstructor:    true\r\n" + 
						"    Property:\r\n" + 
						"        Name:   make\r\n" + 
						"        Type:   String\r\n" + 
						"        Scope:  private\r\n" + 
						"        Getter: true\r\n" + 
						"        GetterScope:    public\r\n" + 
						"        Setter: true\r\n" + 
						"        SetterScope:    private\r\n" + 
						"    Property:\r\n" + 
						"        Name:   model\r\n" + 
						"        Type:   String\r\n" + 
						"        Scope:  private\r\n" + 
						"        Getter: true\r\n" + 
						"        GetterScope:    public\r\n" + 
						"        Setter: true\r\n" + 
						"        SetterScope:    private\r\n" + 
						"    Property:\r\n" + 
						"        Name:   year\r\n" + 
						"        Type:   String\r\n" + 
						"        Scope:  private\r\n" + 
						"        Getter: true\r\n" + 
						"        GetterScope:    public\r\n" + 
						"        Setter: true\r\n" + 
						"        SetterScope:    private\r\n" + 
						"End Class:\r\n" + 
						"Interface:\r\n" + 
						"    Name:   IDrawable\r\n" + 
						"    Method: public void draw(Graphics g);\r\n" + 
						"End Interface:\r\n" + 
						"Interface:\r\n" + 
						"    Name:   IMoveable\r\n" + 
						"    Method: public void move();\r\n" + 
						"    Method: public void stop();\r\n" + 
						"    Method: public int speedUp();\r\n" + 
						"    Method: public int slowDown();\r\n" + 
						"End Interface:\r\n" + 
						"Enumeration:\r\n" + 
						"    Name:   ShipType\r\n" + 
						"    Property:\r\n" + 
						"        Name:   friendlyName\r\n" + 
						"        Type:   String\r\n" + 
						"        Getter: true\r\n" + 
						"        GetterScope:    public\r\n" + 
						"    Property:\r\n" + 
						"        Name:   maxAllowedPeople\r\n" + 
						"        Type:   int\r\n" + 
						"        Getter: true\r\n" + 
						"        GetterScope:    public\r\n" + 
						"    Property:\r\n" + 
						"        Name:   damage\r\n" + 
						"        Type:   int\r\n" + 
						"        Getter: true\r\n" + 
						"        GetterScope:    public\r\n" + 
						"    Property:\r\n" + 
						"        Name:   weight\r\n" + 
						"        Type:   int\r\n" + 
						"        Getter: true\r\n" + 
						"        GetterScope:    public\r\n" + 
						"    Type:\r\n" + 
						"        Name:   FIRE_SHIP\r\n" + 
						"        DataTypes: String,int,int,int\r\n" + 
						"        Data:   Fire_Ship\r\n" + 
						"        Data:   70\r\n" + 
						"        Data:   500\r\n" + 
						"        Data:   20000\r\n" + 
						"    EndType:\r\n" + 
						"    Type:\r\n" + 
						"        Name:   CUTTER\r\n" + 
						"        DataTypes: String,int,int,int\r\n" + 
						"        Data:   Cutter\r\n" + 
						"        Data:   10\r\n" + 
						"        Data:   5\r\n" + 
						"        Data:   600\r\n" + 
						"    EndType:\r\n" + 
						"    Type:\r\n" + 
						"        Name:   TRIREME\r\n" + 
						"        DataTypes: String,int,int,int\r\n" + 
						"        Data:   Trireme\r\n" + 
						"        Data:   140\r\n" + 
						"        Data:   800\r\n" + 
						"        Data:   21000\r\n" + 
						"    EndType:\r\n" + 
						"End Enumeration:); ");
				pw.close();
				} catch (Exception f) {f.printStackTrace();}
				System.exit(0);
			}
		});
		btnGenerateExample.setFont(new Font("Tahoma", Font.ITALIC, 20));
		GridBagConstraints gbc_btnGenerateExample = new GridBagConstraints();
		gbc_btnGenerateExample.fill = GridBagConstraints.BOTH;
		gbc_btnGenerateExample.insets = new Insets(0, 0, 5, 0);
		gbc_btnGenerateExample.gridx = 3;
		gbc_btnGenerateExample.gridy = 3;
		contentPane.add(btnGenerateExample, gbc_btnGenerateExample);

		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
		textArea.setEditable(false);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridheight = 2;
		gbc_textArea.insets = new Insets(0, 0, 0, 5);
		gbc_textArea.gridwidth = 3;
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 4;
		contentPane.add(textArea, gbc_textArea);

		btnReset = new JButton("Reset");
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				classes.clear();
				enums.clear();
				interfaces.clear();
				textArea.setText(writeText());
				System.out.println("Reset");
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 24));
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.fill = GridBagConstraints.BOTH;
		gbc_btnReset.insets = new Insets(0, 0, 5, 0);
		gbc_btnReset.gridx = 3;
		gbc_btnReset.gridy = 4;
		contentPane.add(btnReset, gbc_btnReset);

		btnDone = new JButton("Done");
		btnDone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				writeFile();
				System.exit(0);
			}
		});
		btnDone.setFont(new Font("Tahoma", Font.BOLD, 24));
		GridBagConstraints gbc_btnDone = new GridBagConstraints();
		gbc_btnDone.fill = GridBagConstraints.BOTH;
		gbc_btnDone.gridx = 3;
		gbc_btnDone.gridy = 5;
		contentPane.add(btnDone, gbc_btnDone);
	}

	protected void writeFile() {
		PrintWriter pw = null;

		try {
			pw = new PrintWriter("input.txt");

			pw.println("**** Data with dashes needs to be filled in manually and you may need to add lines, see example file for reference");
			writeClasses(pw);
			writeInterfaces(pw);
			writeEnums(pw);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {pw.close();} catch(Exception e) {}
		}

	}

	private void writeClasses(PrintWriter pw) {
		for (String key : classes.keySet()) {
			pw.println("Class:");
			pw.println("\tName:\t" + key);
			pw.println("\tAbstract:\tfalse");
			pw.println("\tExtends:\t");
			pw.println("\tImplements:\t");
			pw.println("\tCloneMethod:\ttrue");
			pw.println("\tEmptyConstructor:\ttrue");
			pw.println("\tWorkhorseConstructor:\ttrue");
			pw.println("\tCopyConstructor:\ttrue");
			for (int i = 0; i < classes.get(key); i++) {
				pw.println("\tProperty:");
				pw.println("\t\tName:\t--propertyName--");
				pw.println("\t\tType:\t--propertyType--");
				pw.println("\t\tScope:\tprivate");
				pw.println("\t\tGetter:\ttrue");
				pw.println("\t\tGetterScope:\tpublic");
				pw.println("\t\tSetter:\ttrue");
				pw.println("\t\tSetterScope:\tprivate");
			}
			pw.println("End Class:");
		}

	}

	private void writeEnums(PrintWriter pw) {
		for (String key : enums.keySet()) {
			pw.println("Enumeration:");
			pw.println("\tName:\t" + key);
			for (int i = 0; i < Integer.parseInt(enums.get(key).split("-")[0]); i++) {
				pw.println("\tProperty:");
				pw.println("\t\tName:\t--propertyName--");
				pw.println("\t\tType:\t--propertyType--");
				pw.println("\t\tGetter:\ttrue");
				pw.println("\t\tGetterScope:\tpublic");
			}
			for (int i = 0; i < Integer.parseInt(enums.get(key).split("-")[1]); i++) {
				pw.println("\tType:");
				pw.println("\t\tName:\t" + key);
				pw.println("\t\tDataTypes:\t--String,int--");
				pw.println("\t\tData:\t--Name--");
				pw.println("\t\tData:\t--500--");
				pw.println("\tEndType:");
			}
			pw.println("End Enumeration:");
		}

	}

	private void writeInterfaces(PrintWriter pw) {
		for (String value : interfaces) {
			pw.println("Interface:");
			pw.println("\tName:\t" + value);
			pw.println("\tMethod:\t--public void method()--");
			pw.println("End Interface:");
		}

	}


	protected String writeText() {
		String newline = System.getProperty("line.separator");
		String text = "";
		for (String key : classes.keySet()) {
			text = text + "class: " + key + ", " + classes.get(key) + " properties" + newline;
		}
		for (String key : enums.keySet()) {
			text = text + "enum: " + key + ", " + enums.get(key).split("-")[0] + " properties, " + enums.get(key).split("-")[1] + " types/instances" + newline;
		}
		for (String value : interfaces) {
			text = text + "interface: " + value + newline;
		}
		return text;
	}

}

