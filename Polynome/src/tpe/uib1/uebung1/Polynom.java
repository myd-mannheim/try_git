package tpe.uib1.uebung1;

/**
 * Die Klasse Polynom repräsentiert ein Polynom eines bestimmten Grades.
 * 
 * @version v0.06
 * @author Yannic, Dominic, Maximilian
 * 
 */

public class Polynom {

	private double[] myCoefficients;

	public Polynom() {
		// Null Polynom heißt alle Koeffizienten sind Null nzw. es gibt keine
		// Koeffizienten
		this.myCoefficients = new double[0];
	}

	/**
	 * 
	 * @param coefficients
	 *            Koeffizienten des Polynoms. Die Anzahl der Koeffizieten
	 *            impliziert des Grad des Polynoms
	 */

	public Polynom(double... coefficients) {
		this.myCoefficients = new double[coefficients.length];
		// Koeffizienten in Attribut kopieren und runden(!)
		for (int i = 0; i < this.myCoefficients.length; i++) {
			this.myCoefficients[i] = roundDigit(coefficients[i]);
		}
	}

	public int getGrad() {
		/*
		 * Der Grad des Polynoms entspricht der Länge des Arrays minus eins
		 * (ohne den konstanten Koeffizienten). Falls jedoch im letzten Feld des
		 * Arrays (entspricht höchster Koeffizient) eine 0 steht ist der Grad
		 * des Polynoms dem nächsten höchsten Koeffizienten der nicht 0 ist.
		 */
		int i = this.myCoefficients.length - 1;

		while (this.myCoefficients[i] == 0 && i >= 0) {
			i--;
		}

		return i;
	}

	/*
	 * HINWEIS Ich hab grade gesehen, dass der Smits die Methodennamen schon
	 * vorgegeben hat Also bennent die Methoden so wie im Aufgabentext
	 * vorgegeben.
	 */

	/**
	 * Transformiert das Polynom in eine String Repräsentation
	 * 
	 * @return Polynom als String
	 */
	public String toString() {
		String polynom = "Grad " + this.getGrad() + ": ";

		for (int i = this.myCoefficients.length - 1; i >= 0; i--) {
			if (this.myCoefficients[i] != 0.0) {

				// Füge vor alle Monome außer dem ersten ein Plus hinzu wenn sie
				// auch positiv sind.
				// Ein Minus wird automatisch erzeugt
				if (i < this.myCoefficients.length - 1) {
					if (this.myCoefficients[i] > 0) {
						polynom += " + ";
					} else {
						polynom += " ";
					}
				}

				switch (i) {
				case 0:
					// -> x^0 => x soll nicht geschrieben werden
					polynom += convertDigit(this.myCoefficients[i]);
					break;
				case 1:
					// -> x^1 => nur x soll geschrieben werden
					polynom += convertDigit(this.myCoefficients[i]) + "x";
					break;
				default:
					// ansonsten x^i
					polynom += convertDigit(this.myCoefficients[i]) + "x^" + i;
				}

			}

		}

		return polynom;
	}

	private String convertDigit(double digit) {
		if ((digit) % 1 == 0.0) {
			return ((int) digit + "");
		} else {
			return digit + ""; // Digit ist schon gerundet!
		}
	}

	private double roundDigit(double digit) {
		return (Math.round(digit * 100.0)) / 100.0;
	}

	/**
	 * Berechnet die erste Ableitung des Polynoms. Das Ergebnis ist ein neues
	 * Polynom.
	 * 
	 * @return Erste Ableitung des Polynoms als neues Objekt
	 */
	public Polynom differenziere() {
		// Die Koeffizienten des abgeleitetn Polynoms werden zuächst in ein
		// Array gespeichert.
		// Das abgeleitet Polynom wird einen Grad geringer sein als das
		// Ursprüngliche
		// => Es wird also ein Arrayplatz weniger benötigt
		double coefficientsNew[] = new double[this.myCoefficients.length - 1];

		// der konstante Koeffizient fällt wird 0 -> Schleife beginnt bei Index
		// 1
		for (int i = 1; i < this.myCoefficients.length; i++) {
			// Ableitungsregel für Koeffizienten anwenden z.B. n*x^k ->
			// n*k*x^(k-1)
			coefficientsNew[i - 1] = this.myCoefficients[i] * i;
		}

		return new Polynom(coefficientsNew);
	}

	/**
	 * Berechnet das Integral eines Polynoms und gibt dieses als neues Objekt
	 * zurück.
	 * 
	 * a*x^n -> (a/n+1) * x^n+1
	 * 
	 * @return Intergral des Polynoms (Aufleitung) als neues Objekt
	 * 
	 */
	public Polynom integriere() {
		// Es wird ein Array erstellt mit einem Platz mehr, da die Funktion beim
		// aufleiten einen Grad hinzugewinnt
		double coefficentsNew[] = new double[this.myCoefficients.length + 1];

		// Start bei i = 1, da das Konstante Glied beim aufleiten immer 0 ist
		for (int i = 1; i < coefficentsNew.length; i++) {
			// Koeffizient an Arrayplatz kopieren
			coefficentsNew[i] = this.myCoefficients[i - 1];
			// Koeffizienten durch aufgeleitet grad teilen
			coefficentsNew[i] = coefficentsNew[i] / i;
		}

		return new Polynom(coefficentsNew);

	}

	/**
	 * Prüft ob ein Polynom gleich einem Vergleichspolynom ist
	 * 
	 * 
	 * @param referencePolynom
	 *            Polynom das auf Gleichheit geprüft werden soll
	 * @return true wenn die Polynome gleich sind
	 */
	public boolean equals(Polynom referencePolynom) {
		// Zwei gleichen Polynome gleichen sich auch im Ergenis von toString()
		if (this.toString().equals(referencePolynom.toString())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gibt den Koeffizienten zurück mit Grad x^degree
	 * 
	 * @param degree
	 *            Grad des Koeffizienten, der zurückgegeben wird
	 * @return Der angeforderte Koeffiezient
	 * @exception Exeption
	 *                Wenn der angeforderte Grad höher ist als der des Polynoms
	 *                wird eine Exception geworfen
	 */
	public double getKoeffizient(int degree) throws Exception {
		if (this.getGrad() <= degree) {
			return this.myCoefficients[degree];
		} else {
			// Wenn der angeforderte Grad höher ist als der des Polynoms wird
			// eine Exception geworfen
			throw new Exception();
		}
	}

	/**
	 * Gibt ein Array mit allen Koeffizienten zurück
	 * 
	 * @return double Array
	 */
	public double[] getKoeffizienten() {
		double[] myCoefficientsCopy = new double[this.myCoefficients.length];
		System.arraycopy(this.myCoefficients, 0, myCoefficientsCopy, 0,
				this.myCoefficients.length);
		return myCoefficientsCopy;

	}

	/**
	 * Berechnet den Wert des Polynoms mit dem Parameter x
	 * 
	 * @param x
	 *            Wert für x in dem Polynom
	 * @return double Wert des Polynoms
	 */
	public double berechne(double x) {
		double sum = 0.0;
		for (int i = 0; i < myCoefficients.length; i++) {
			sum = sum + myCoefficients[i] * Math.pow(x, i);
		}
		return sum;
	}

	/**
	 * Berechnet die Werte für veschiedene x
	 * 
	 * @param x
	 *            Werte für x
	 * @return double Array mit den Werten des Polynoms
	 */
	public double[] berechne(double... x) {
		double[] sums = new double[x.length];
		for (int i = 0; i < x.length; i++) {
			sums[i] = berechne(x[i]);
		}
		return sums;
	}

	public Polynom addiere(Polynom addPolynom) {
		return addSub(addPolynom, true);
	}

	public Polynom subtrahiere(Polynom subPolynom) {
		return addSub(subPolynom, false);
	}

	private Polynom addSub(Polynom addPolynom, boolean add) {
		double[] newCoefficients;
		double[] addPolynomCoef = addPolynom.getKoeffizienten();

		double[] longerPolynom;


		// Ermitteln welches Polynom das längere ist und Array mit dessen länge anlegen
		if (addPolynom.getGrad() > this.getGrad()) {
			newCoefficients = new double[addPolynomCoef.length];
		} else {
			newCoefficients = new double[this.myCoefficients.length];
		}
		
		//Werte des eigenen Polynoms ins Zielarray kopieren
		System.arraycopy(this.myCoefficients, 0, newCoefficients, 0, this.myCoefficients.length);
		
		for(int i=0;i<newCoefficients.length;i++){
			try{
			if(add)
				newCoefficients[i] += addPolynomCoef[i];
			else
				newCoefficients[i] -= addPolynomCoef[i];
			}catch(ArrayIndexOutOfBoundsException ex){
				//there are no more values to add/sub -> cancel the loop
				break;
			}
		}		

		return new Polynom(newCoefficients);
	}

	/*
	 * private Polynom addSub(Polynom addPolynom, boolean add) { double[]
	 * newCoefficients; double[] addPolynomCoef = addPolynom.getKoeffizienten();
	 * 
	 * double[] longerPolynom; double[] shorterPolynom;
	 * 
	 * boolean myCoefficientsIsLonger;
	 * 
	 * // Ermitteln welches Polynom das längere ist if (addPolynom.getGrad() >
	 * this.getGrad()) { longerPolynom = addPolynomCoef; shorterPolynom =
	 * this.myCoefficients; myCoefficientsIsLonger = false;
	 * 
	 * } else { longerPolynom = this.myCoefficients; shorterPolynom =
	 * addPolynomCoef; myCoefficientsIsLonger = true; } // Array für die
	 * Koeffizienten des neuen Polynoms anlegen // Länge ist die des längern
	 * Polynoms der Operation newCoefficients = new
	 * double[longerPolynom.length];
	 * 
	 * int i; for (i = 0; i < shorterPolynom.length; i++) { if (add == true) {
	 * newCoefficients[i] = shorterPolynom[i] + longerPolynom[i]; } else if
	 * (myCoefficientsIsLonger == true) { newCoefficients[i] = longerPolynom[i]
	 * - shorterPolynom[i]; } else { newCoefficients[i] = shorterPolynom[i] -
	 * longerPolynom[i]; } } // Nun müssen noch die Koeffizienten des längern
	 * Polynoms in das neue // Polynom kopiert werden // die noch nicht addiert
	 * wurden if (longerPolynom.length != shorterPolynom.length) { if (add ==
	 * true || (add == false && myCoefficientsIsLonger == true)) { for (int z =
	 * i; z < newCoefficients.length; z++) { newCoefficients[z] =
	 * longerPolynom[z]; } } else if (add == false && myCoefficientsIsLonger ==
	 * false) { for (int z = i; z < newCoefficients.length; z++) {
	 * newCoefficients[z] = -1 * longerPolynom[z]; } } }
	 * 
	 * return new Polynom(newCoefficients);
	 * 
	 * }
	 */
}
