import javax.swing.*;

public class Ahorcador {

	String palabra = "";
	String s = "";
	char[] p;
	char[] pfinal;
	int comp = 0;
	String pala = "";
	boolean ejecutando = true;

	public void setPalabra() {

		boolean r = true;

		while (r) {
			try {

				s = JOptionPane.showInputDialog(null, "Ingresa palabra:");
				p = new char[s.length()];
				pfinal = new char[s.length()];

				for (int i=0; i<s.length(); i++) {
					p[i] = s.charAt(i);
				}
				for (int j=0; j<s.length(); j++) {
					pfinal[j] = '_';
				}
				
				r = !r;

			} catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Ingra una cadena de texto.");
			}
		}
	}

	public void setIndice(int index, char c) {

		pfinal[index] = c;

	}

	public boolean comprobarPalabra() {

		comp = 0;

		for (int i=0; i<p.length; i++) {
			if(pfinal[i] == p[i]) comp++;
		}

		if(comp == p.length) {
			return true;
		} else {
			return false;
		}
	}

	public void ingresarPalabra(int index) {

		char temp = JOptionPane.showInputDialog(null, "Ingresa letra para la posicion: " + index ).charAt(0);
		pfinal[index-1] = temp;

	}

	public String showPalabra() {
		pala = "";

		for (int i=0; i<p.length; i++) {
			pala += pfinal[i];
		}

		return pala;
	}
	
	public void init() {

		setPalabra();

		while(!comprobarPalabra()) {
			JOptionPane.showMessageDialog(null, showPalabra() + " Total de letras: " + p.length);
			int indexP = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa posicion que quieres ingresar: 1 - " + (p.length) ));
			if(indexP >= 1 && indexP <= p.length) {

				ingresarPalabra(indexP);

			} else {
				indexP = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa posicion que quieres ingresar: 1 - " + (p.length) ));
			}
		}

		JOptionPane.showMessageDialog(null, "Felicidades, ganaste :v");
	}
}