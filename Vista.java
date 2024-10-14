package PrimerTrimestre.AE1;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Vista extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTextField txtModificar;
	private JButton btnFichero;
	private JButton btnBuscar;
	private JButton btnModificar;
	private JCheckBox chboxMayusculas;
	private JCheckBox chboxAcentos;
	private JTextArea txtAreaFicheros;
	private JScrollPane scrollPane;
	private JTextField txtFichero;

	public Vista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 701, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		chboxMayusculas = new JCheckBox("Respetar mayúsculas");
		chboxMayusculas.setBounds(21, 125, 163, 23);
		contentPane.add(chboxMayusculas);

		chboxAcentos = new JCheckBox("Respetar acentos");
		chboxAcentos.setBounds(21, 151, 138, 23);
		contentPane.add(chboxAcentos);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(20, 77, 86, 20);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		txtModificar = new JTextField();
		txtModificar.setBounds(135, 77, 86, 20);
		contentPane.add(txtModificar);
		txtModificar.setColumns(10);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(37, 195, 89, 23);
		contentPane.add(btnBuscar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModificar.setBounds(37, 229, 89, 23);
		contentPane.add(btnModificar);

		JLabel lblNewLabel = new JLabel("Texto a buscar");
		lblNewLabel.setBounds(20, 52, 86, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Texto a modificar");
		lblNewLabel_1.setBounds(135, 52, 121, 14);
		contentPane.add(lblNewLabel_1);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(190, 125, 426, 193);
		contentPane.add(scrollPane);

		txtAreaFicheros = new JTextArea();
		scrollPane.setViewportView(txtAreaFicheros);

		btnFichero = new JButton("Seleccionar archivo");
		btnFichero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFichero.setBounds(524, 76, 151, 23);
		contentPane.add(btnFichero);

		txtFichero = new JTextField();
		txtFichero.setEditable(false);
		txtFichero.setBounds(265, 77, 249, 20);
		contentPane.add(txtFichero);
		txtFichero.setColumns(10);

		this.setVisible(true);
	}

	public JButton getBtnFichero() {
		return btnFichero;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public JButton getBtnModificar() {
		return btnModificar;
	}

	public JTextField getTxtBuscar() {
		return txtBuscar;
	}

	public JTextField getTxtModificar() {
		return txtModificar;
	}

	public JCheckBox getChboxMayusculas() {
		return chboxMayusculas;
	}

	public JCheckBox getChboxAcentos() {
		return chboxAcentos;
	}

	public JTextArea getTxtAreaFichers() {
		return txtAreaFicheros;
	}

	public JTextField getTxtFichero() {
		return txtFichero;
	}
}
