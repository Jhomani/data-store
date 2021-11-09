package handler;

import java.io.*;

public class Serialization<T> {
  private String path;
	private FileOutputStream  archivosalida;
	private FileInputStream  archivoentrada;
	private ObjectOutputStream salida;
	private ObjectInputStream entrada;

  public Serialization(String path) {
    this.path = path;
  }
	
  @SuppressWarnings("unchecked")
	public T readData() throws IOException {
		T d = null;
		
    try {
      abrirentrada();

      while(true) {
				 d = (T) entrada.readObject();
				 System.out.println(d + ">> ");
			}
    } 
    catch(EOFException e) {} 
    catch(ClassNotFoundException e) { }

    try {
      cerrarentrada();
    } catch(IOException e){}

		return d;
	}

  @SuppressWarnings("unchecked")
	public T readData(int nLine) throws IOException {
		T d = null;
		int count = 0;
		
		if(nLine > 0) {
			try {
				abrirentrada();

				while(nLine != count) {
					d = (T) entrada.readObject();
					count++;
				}
			} 
			catch(EOFException e) { System.out.println(e.getMessage()); } 
			catch(ClassNotFoundException e) { System.out.println(e.getMessage());}

			try {
				cerrarentrada();
			} catch(IOException e){}
		}

		return d;
	}
	
	public void abrirsalida() throws IOException {
		archivosalida = new FileOutputStream(path, true);
		// archivosalida = new FileOutputStream("ejemplo.txt", false);  to add more data
		salida = new ObjectOutputStream(archivosalida);
	}
	
	public void abrirentrada() throws IOException {
		archivoentrada = new FileInputStream(path);
		entrada = new ObjectInputStream(archivoentrada);
	}
	
	public void cerrarsalida() throws IOException {
		if (salida!=null) {
			archivosalida.close();
			salida.close();
		} 
	}
	
	public void cerrarentrada() throws IOException {
		if (entrada!=null) entrada.close();
	}
	
	public void saveObject(T d) throws IOException {
    abrirsalida();
		if (salida!=null) salida.writeObject(d);
    cerrarsalida();
	}
}
