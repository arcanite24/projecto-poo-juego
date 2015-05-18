import javax.swing.*;

public class Ejercicios {
	
	public void promedio() {

		int limite = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa cuantos numeros quieres promediar:"));
		float n[] = new float[limite];
		float r = 0;

		for (int i=0; i<limite; i++) {
			n[i] = Float.parseFloat(JOptionPane.showInputDialog(null, "Ingresa numero " + (i+1)));
			r += n[i];
		}

		JOptionPane.showMessageDialog(null, "El proemdio de " + limite + " numeros es: " + (r/limite));

	}

	public void promedioPar() {

		int limite = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa cuantos numeros quieres promediar:"));
		float n[] = new float[limite];
		float r = 0;

		for (int i=0; i<limite; i++) {
			
			n[i] = Float.parseFloat(JOptionPane.showInputDialog(null, "Ingresa numero " + (i+1)));
			if(n[i] % 2 == 0) r += n[i];
		}

		JOptionPane.showMessageDialog(null, "El proemdio de " + limite + " numeros pares es: " + (r/limite));

	}

	public void calificaciones() {

		int n[] = new int[10];
		float r = 0;
		String rstr = "";

		for (int i=0; i<10; i++) {
			n[i] = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa calificacion " + (i+1)));
			r += n[i];
			rstr += n[i] + ",";
		}

		JOptionPane.showMessageDialog(null, "Calificaciones: " + rstr);
		JOptionPane.showMessageDialog(null, "Promedio: " + (r / 10));

		int imayor = n[0];
		int imenor = n[0];

		for (int j=0; j<10; j++) {
			if (n[j] > imayor) {
				imayor = n[j];
			}
		}
		for (int k=0; k<10; k++) {
			if (n[k] < imenor) {
				imenor = n[k];
			}
		}

		JOptionPane.showMessageDialog(null, "El numero menor es: " + imenor + ", el numero mayor es: " + imayor);

		String mayorPromedio = "";

		for (int l=0; l<10; l++) {
			if (n[l] > (r/10)) {
				mayorPromedio += n[l] + ",";
			}
		}

		JOptionPane.showMessageDialog(null, "Los numeros mayores al promedio son: " + mayorPromedio);

		int reprobados = 0;
		int aprobados = 0;

		for (int l=0; l<10; l++) {
			if (n[l] > 6) {
				aprobados++;
			} else {
				reprobados++;
			}
		}		

		JOptionPane.showMessageDialog(null, "Aprobados: " + aprobados + ", Reprobados: " + reprobados);

	}
}