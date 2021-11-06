package handler;

import java.io.Serializable;

public class Persona implements Serializable {
	String nombre;
	String apellido;
	String ci;
	
	public Persona(String c, String n, String a) {
		this.ci = c;
		this.nombre=n;
		this.apellido=a;
	}
}

