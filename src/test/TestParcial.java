package test;

import java.util.Random;

public class TestParcial extends Thread {

	//Atributos de la clase
	private static int n = 12; 
	private static int m = 24; 
	static int[][] matriz = new int[n][m];

	//Pueden agrega los atributos que necesiten

	private int inicioN, finN;

	private int inicioM, finM;
	
	private  int  max =0;
	




	public TestParcial(int inicioN, int finN, int inicioM, int finM) {
		super();
		this.inicioN = inicioN;
		this.finN = finN;
		this.inicioM = inicioM;
		this.finM = finM;
	}
	
	
	

	public int getMax() {
		return max;
	}




	//Acá programar el run() 
	public void run() {

		//Ejercicio del examen
		
		
	
			

			for (int i = inicioN+1; i < finN ; i++) {
				
				for (int j = inicioM; j < finM; j++) {
					
					try {
						sleep(3);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if (matriz[i][j] > this.max) {

						
						this.max = matriz[i][j];
						
					}
				}
			}

			System.out.println("El máximo es: " +this.max);
			

		}

	
	///Métodos de la clase - Secuenciales
	public static void llenarMatrizConAleatorios() {
		Random random = new Random();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				matriz[i][j] = random.nextInt(100001); // Genera un número aleatorio entre 0 y 100000
			}
		}
	}


	public static void imprimirMatriz() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(matriz[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public static void encontrarMaximo() {
		int maximo = matriz[0][0];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (matriz[i][j] > maximo) {
					maximo = matriz[i][j];

				}
			}
		}

		System.out.println("El máximo es: " +maximo);
	}




	public static void main(String[] args) throws InterruptedException {

		//Carga la matriz y la muestra
		TestParcial.llenarMatrizConAleatorios();
		TestParcial.imprimirMatriz();
		//Muestra el máximo para comparar
		TestParcial.encontrarMaximo();

		//Acá deberan buscar el maximo pero trabajando con hilos
		//El maximo debe coincidir con el anterior.



		Runtime runtime = Runtime.getRuntime();
		int numeroNucleos = runtime.availableProcessors();

		

		Thread[] hilos = new Thread[numeroNucleos];

		int inicioN = 0;
		int finN = n/numeroNucleos; 

		int inicioM = 0;
		int finM = m/numeroNucleos; 


		for(int i =0; i<numeroNucleos;i++) {		

			hilos[i] = new TestParcial(inicioN,finN, inicioM, finM);


			if( (finN<=n)) {

				inicioN = inicioN + finN;

				finN = finN + finN;

			}else {
				inicioN = inicioN + finN;

				finN = n;
			}

			if( (finM<=m)) {

				inicioM = inicioM + finM;

				finM = finM + finM;

			}else {

				inicioM = inicioM + finM;

				finM = m;
			}

		}


		for(int i =0; i<numeroNucleos;i++) {

			hilos[i].start();
			
		}

		
		

		


		//-------Ayudas!!!, no hace falta que usen esto, es solo por
		//si lo necesitan -------
		///Información de la PC
		//Runtime runtime = Runtime.getRuntime();
		//int numeroNucleos = runtime.availableProcessors();
		///Crear array de hilos
		//Thread[] hilos = new Thread[5];
		//Lanzar hilos, llamado a run()
		//hilos[i].start();
		//esperar hilos
		//hilos[i].join();


	}

}
