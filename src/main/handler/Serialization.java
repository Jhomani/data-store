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

  public void setPath(String path) {
    this.path = path;
  }
	
  @SuppressWarnings("unchecked")
	public T readData() throws IOException {
		T d = null;
		
    try {
      openInput();

      while(true) {
				 d = (T) entrada.readObject();
				 System.out.println(d + ">> ");
			}
    } 
    catch(EOFException e) {} 
    catch(ClassNotFoundException e) { }

    try {
      closeInput();
    } catch(IOException e){}

		return d;
	}

  @SuppressWarnings("unchecked")
	public T readData(int nLine) throws IOException {
		T d = null;
		int count = 0;
		
		if(nLine > 0) {
			try {
				openInput();

				while(nLine != count) {
					d = (T) entrada.readObject();
					count++;
				}
			} 
			catch(EOFException e) { System.out.println(e.getMessage()); } 
			catch(ClassNotFoundException e) { System.out.println(e.getMessage());}

			try {
				closeInput();
			} catch(IOException e){}
		}

		return d;
	}
	
	public void openOutput() throws IOException {
		archivosalida = new FileOutputStream(path, true);
		salida = new ObjectOutputStream(archivosalida);
	}
	
	public void openInput() throws IOException {
		archivoentrada = new FileInputStream(path);
		entrada = new ObjectInputStream(archivoentrada);
	}
	
	public void closeOutput() throws IOException {
		if (salida!=null) {
			archivosalida.close();
			salida.close();
		} 
	}
	
	public void closeInput() throws IOException {
		if (entrada!=null) entrada.close();
	}
	
	public void saveObject(T d) throws IOException {
    openOutput();
		if (salida!=null) salida.writeObject(d);
    closeOutput();
	}
}
