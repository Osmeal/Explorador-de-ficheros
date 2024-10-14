package PrimerTrimestre.AE1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Controlador {

	private Modelo modelo;
	private Vista vista;
	private ActionListener actionListener_btnBuscar, actionListener_btnModificar, actionListener_btnFichero;
	private File archivo;

	public Controlador(Modelo modelo, Vista vista) {
		// TODO Auto-generated constructor stub
		this.modelo = modelo;
		this.vista = vista;

		control();
	}

	public void control() {

		actionListener_btnBuscar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (archivo != null) {

					try {
						vista.getTxtAreaFichers().setText(modelo.buscarFichero(archivo, vista.getTxtBuscar().getText(), "",
								vista.getChboxMayusculas().isSelected(), vista.getChboxAcentos().isSelected()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Tienes que seleccionar un directorio primero", "SELECCIONA UN DIRECTORIO",
							JOptionPane.INFORMATION_MESSAGE);

				}
			}
		};
		vista.getBtnBuscar().addActionListener(actionListener_btnBuscar);

		actionListener_btnModificar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (archivo != null) {

					try {
						vista.getTxtAreaFichers()
								.setText(modelo.buscarFichero(archivo, vista.getTxtBuscar().getText(),
										vista.getTxtModificar().getText(), vista.getChboxMayusculas().isSelected(),
										vista.getChboxAcentos().isSelected()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Tienes que seleccionar un directorio primero", "SELECCIONA UN DIRECTORIO",
							JOptionPane.INFORMATION_MESSAGE);

				}
			}
		};
		vista.getBtnModificar().addActionListener(actionListener_btnModificar);

		actionListener_btnFichero = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fc.showOpenDialog(vista);
				archivo = fc.getSelectedFile();

				if (archivo != null) {
					vista.getTxtFichero().setText(archivo.getAbsolutePath());

					try {
						vista.getTxtAreaFichers().setText(modelo.buscarFichero(archivo, "", "",
								vista.getChboxMayusculas().isSelected(), vista.getChboxAcentos().isSelected()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		};
		vista.getBtnFichero().addActionListener(actionListener_btnFichero);

	}
}
