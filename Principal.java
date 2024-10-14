package PrimerTrimestre.AE1;

import java.io.IOException;

public class Principal {

	public static void main(String[] args) throws IOException {

		Modelo modelo = new Modelo();
		Vista vista = new Vista();
		Controlador Controlador = new Controlador(modelo, vista);

	}

}
