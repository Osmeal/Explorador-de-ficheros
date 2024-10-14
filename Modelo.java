package PrimerTrimestre.AE1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Modelo {

	private int palabraBuscada;

	Modelo() throws IOException {

	}

	/**
	 * Le enviamos un archivo, lee todo el contenido del archivo y dependiendo de
	 * los parámetros de entrada mostrará la información de los archivos, nos dirá
	 * cuántas veces se repite una palabra en los archivos o podremos sustituir la
	 * palabra por otra creando una modificación del archivo
	 * 
	 * @param archivoObtenido    Archivo que queremos visualizar
	 * @param palabraBuscada     Palabra que queremos buscar en los archivos
	 * @param palabraCambiar     Palabra que queremos sustituir por la palabra
	 *                           buscada
	 * @param respetarMayusculas Nos indica si queremos respetar las mayúsculas
	 * @param respetarAcentos    Nos indica si queremos respetar los acentos
	 *
	 * @return Devuelve un string con la información del archivo, de las palabras
	 *         encontradas o de las modificaciones realizadas
	 * @throws IOException
	 * 
	 */
	public String buscarFichero(File archivoObtenido, String palabraBuscada, String palabraCambiar,
			Boolean respetarMayusculas, Boolean respetarAcentos) throws IOException {

		String archivoDevuelto = archivoObtenido.getName() + "\n";

		for (int i = 0; i < archivoObtenido.list().length; i++) {

			File archivo = new File(archivoObtenido, archivoObtenido.list()[i]);

			if (archivo.isFile()) {
				if (palabraBuscada.equals("")) {
					archivoDevuelto += "|--" + archivo.getName() + obtenerFecha(archivo) + "\n";
				} else {
					archivoDevuelto += "|--" + archivo.getName() + obtenerMensajeBusqueda(archivo, palabraBuscada,
							palabraCambiar, respetarMayusculas, respetarAcentos) + "\n";
				}

			}

			else {

				archivoDevuelto += "|   " + archivo.getName() + "\n";
				String espacio = "|    ";
				archivoDevuelto += isFolder(archivoObtenido, archivo, espacio, palabraBuscada, palabraCambiar,
						respetarMayusculas, respetarAcentos);

			}
		}

		return archivoDevuelto;
	}

	/**
	 * Esta función nos permite explorar todos los directorios y mantener un formato
	 * que nos permita comprender de forma sencilla el orden de los archivos.
	 * 
	 * 
	 * @param archivoOriginal    Archivo raíz del primer directorio seleccionado
	 * @param archivoCarpeta     Archivo que queremos visualizar
	 * @param espacio            Espacio que deja para mostrar los archivos de forma
	 *                           más visual
	 * @param palabraBuscada     Palabra que queremos buscar en los archivos
	 * @param palabraCambiar     Palabra que queremos sustituir por la palabra
	 *                           buscada
	 * @param respetarMayusculas Nos indica si queremos respetar las mayúsculas
	 * @param respetarAcentos    Nos indica si queremos respetar los acentos
	 *
	 * @return Devuelve un string con la información del archivo, de las palabras
	 *         encontradas o de las modificaciones realizadas
	 * @throws IOException
	 * 
	 */
	private String isFolder(File archivoOriginal, File archivoCarpeta, String espacio, String palabraBuscada,
			String palabraCambiar, Boolean respetarMayusculas, Boolean respetarAcentos) throws IOException {
		String archivosDevueltos = "";
		for (int j = 0; j < archivoCarpeta.list().length; j++) {
			File archivo = new File(archivoCarpeta.getPath() + "/" + archivoCarpeta.list()[j]);
			if (archivo.isFile()) {
				if (palabraBuscada.equals("")) {
					archivosDevueltos += espacio + "|--" + archivo.getName() + obtenerFecha(archivo) + "\n";
				} else {
					archivosDevueltos += espacio + "|--" + archivo.getName() + obtenerMensajeBusqueda(archivo,
							palabraBuscada, palabraCambiar, respetarMayusculas, respetarAcentos) + "\n";
				}

			} else {
				archivosDevueltos += espacio + "|   " + archivo.getName() + "\n";

				String nuevoEspacio = espacio + "|    ";
				archivosDevueltos += isFolder(archivoOriginal, archivo, nuevoEspacio, palabraBuscada, palabraCambiar,
						respetarMayusculas, respetarAcentos);
			}

		}

		return archivosDevueltos;
	}

	/**
	 * Obtiene un archivo e indica la fecha de la última modificación y el tamaño
	 * del archivo. Aplica un formateador a la fecha para mostrarla de forma más
	 * clara.
	 *
	 * @param archivo Archivo del cual se obtiene la última fecha de modificación y
	 *                el tamaño
	 *
	 * @return String que muestra la información del archivo ya formateada
	 *
	 */
	private String obtenerFecha(File archivo) {
		Date fecha = new Date(archivo.lastModified());
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");

		String fechaFormateada = formateador.format(fecha);

		return " ( " + archivo.length() / 1024 + "KB - " + fechaFormateada + " ) ";
	}

	/**
	 * Busca una palabra en el archivo y nos indica cuántas veces se ha encontrado
	 * y, dependiendo de los parámetros, la sustituye por otra.
	 * 
	 * @param archivoObtenido        Archivo donde se busca la palabra
	 * @param palabraBuscadaObtenida Palabra que se busca en el archivo
	 * @param palabraCambiarObtenida Palabra que queremos sustituir por la palabra
	 *                               buscada
	 * @param respetarMayusculas     Nos indica si queremos respetar las mayúsculas
	 * @param respetarAcentos        Nos indica si queremos respetar los acentos
	 * 
	 * @return Devuelve un string con las palabras encontradas o las modificaciones
	 *         realizadas
	 * @throws IOException
	 * 
	 */
	private String obtenerMensajeBusqueda(File archivoObtenido, String palabraBuscadaObtenida,
			String palabraCambiarObtenida, Boolean respetarMayusculas, Boolean respetarAcentos) throws IOException {
		String mensajeBusquedaDevuelto = "";
		try {
			FileReader fr = new FileReader(archivoObtenido);
			BufferedReader br = new BufferedReader(fr);
			palabraBuscada = 0;
			String linea = br.readLine();

			while (linea != null) {
				if (!respetarMayusculas) {
					linea = linea.toLowerCase();
					palabraBuscadaObtenida = palabraBuscadaObtenida.toLowerCase();
				}
				if (!respetarAcentos) {
					linea = filtrarAcentos(linea);
					palabraBuscadaObtenida = filtrarAcentos(palabraBuscadaObtenida);
				}
				int indice = 0;

				while (indice != -1) {
					indice = linea.indexOf(palabraBuscadaObtenida, indice);
					if (indice != -1) {
						palabraBuscada++;
						indice += palabraBuscadaObtenida.length();
					}
				}

				linea = br.readLine();
			}

			mensajeBusquedaDevuelto = " ( " + palabraBuscada + " coincidencias ) ";
			if (!palabraCambiarObtenida.equals("") && palabraBuscada >= 1) {
				return modificarArchivo(archivoObtenido, palabraBuscadaObtenida, palabraCambiarObtenida, respetarMayusculas,
						respetarAcentos, palabraBuscada);
			}
			return mensajeBusquedaDevuelto;

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}

		System.out.println("Error");
		return mensajeBusquedaDevuelto;
	}

	/**
	 * Obtiene un archivo y modifica una palabra (palabraModificar) por otra
	 * (palabraNueva).
	 * 
	 * @param archivoObtenido  Archivo donde queremos modificar la palabra
	 * @param palabraModificar Palabra que queremos modificar
	 * @param palabraNueva     Palabra que queremos tener en lugar de la
	 *                         palabraModificar
	 * 
	 * @return Devuelve un String mostrando las modificaciones realizadas en el
	 *         archivo
	 * @throws IOException
	 * 
	 */
	private String modificarArchivo(File archivoObtenido, String palabraModificar, String palabraNueva,
			Boolean respetarMayusculas, Boolean respetarAcentos, int palabraBuscada) throws IOException {
		String textoModificadoDevuelto = "";
		try {
			FileReader fr = new FileReader(archivoObtenido);
			BufferedReader br = new BufferedReader(fr);
			String archivoModificado = archivoObtenido.getParentFile() + "/" + "MOD_" + archivoObtenido.getName();
			FileWriter fw = new FileWriter(archivoModificado, StandardCharsets.UTF_8);
			BufferedWriter bw = new BufferedWriter(fw);

			String linea = br.readLine();

			while (linea != null) {
				if (!respetarMayusculas) {
					linea = linea.toLowerCase();
					palabraModificar = palabraModificar.toLowerCase();
				}
				if (!respetarAcentos) {
					linea = filtrarAcentos(linea);
					palabraModificar = filtrarAcentos(palabraModificar);
				}
				System.out.println(palabraModificar + " sustituida por " + palabraNueva);
				bw.write(linea.replace(palabraModificar, palabraNueva));
				bw.newLine();

				linea = br.readLine();
			}
			textoModificadoDevuelto = " ( " + palabraBuscada + " modificaciones ) ";
			bw.close();
			fw.close();
			br.close();
			fr.close();
			return textoModificadoDevuelto;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return textoModificadoDevuelto;

	}

	/**
	 * Obtiene un string y lo modifica quitándole los tildes, diéresis y otros
	 * símbolos
	 * 
	 * @param texto String al que queremos quitar los tildes y otros símbolos
	 * @return String ya filtrado
	 * 
	 */
	private String filtrarAcentos(String texto) {
		texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
		texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		return texto;
	}

}
