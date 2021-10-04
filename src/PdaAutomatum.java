import view.IoManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class PdaAutomatum {

	private IoManager ioManager;
	private ArrayList<String> q;
	private ArrayList<String> sigma;
	private ArrayList<String> r;
	private ArrayList<String> f;
	private ArrayList<String[]> transactions;
	private String initialState;
	private String initilaStackSymbol;

	private String actualState;
	private Stack<String> stack;

	public PdaAutomatum() throws IOException {

		q = new ArrayList<String>();
		sigma = new ArrayList<String>();
		r = new ArrayList<String>();
		f = new ArrayList<String>();
		transactions = new ArrayList<String[]>();
		stack = new Stack<String>();
		ioManager = new IoManager();
		initialState = ioManager.readString("Ingrese el estado inicial");
		initilaStackSymbol = ioManager.readString("Ingrese el simbolo inicial de la pila");
		addStates();
		addAlphabetLanguage();
		addAlphabetStack();
		addFinalState();
		addTransitions();
		showInfo();
	}
	
	public void addStates(){
		q = ioManager.readStrings("Ingrese un estado");
	}

	public void addAlphabetLanguage(){
		sigma = ioManager.readStrings("Ingrese un caracter del alfabeto del lenguaje");
	}

	public void addAlphabetStack(){
		r = ioManager.readStrings("Ingrese un caracter del alfabeto de la pila");
	}

	public void addFinalState (){
		f = ioManager.readStrings("Ingrese un estado final");
	}

	public void addTransitions(){
		ArrayList<String> a=ioManager.readStrings("Ingrese una transición, con el siguiente formato: q1 a S q2 A\n" +
				"donde:\n" +
				"q1: Estado de origen\n" +
				"a: simbolo del alfabeto\n" +
				"S: simbolo que sera reemplazado en la pila\n" +
				"q2: Estado siguiente\n" +
				"A: Simbolo que reemplazara en la pila");
		ArrayList<String[]>t=new ArrayList<>();
		for (String s: a) {
			t.add(s.split(" "));
		}
		transactions=t;
	}

	public void showInfo() {

		String info = "Información del autómata con pila\n"+
		"Estado inicial: " + initialState+
				"\nSímbolo inicial de la pila: " + initilaStackSymbol
		+"\nConjunto de estados: " + q.toString()
		+"\nAlfabeto del lenguaje: " + sigma.toString()
		+"\nAlfabeto de la pila: " + r.toString()
		+"\nConjunto de estados finales: " + f.toString()
		+"\nConjunto de transiciones:\n";
		for (int i = 0; i < transactions.size(); i++) {
			info += ("" + Arrays.toString(transactions.get(i)) + "\n");
		}
		ioManager.showInfo(info);
	}

	public void validateWord() {
		
		String inputChain;
		stack.clear();
		stack.push(initilaStackSymbol);
		actualState = initialState;
		// El usuario inserta la cadena
		inputChain = ioManager.readString("Inserte la cadena a probar:");

		// Empezamos a evaluar la cadena de entrada
		boolean noTransactions = true;
		int numInteraction = inputChain.length();//hacemos esto porque vamos a ir eliminando la entrada
		for (int i = 0; i < numInteraction; i++) {
			
			if (noTransactions == true) { //si existen transiciones sigue
				//vamos eliminando el elemento de la cadena tratado 
				String simboloTratado = inputChain.substring(0, 1);
				inputChain = inputChain.substring(1);
				noTransactions = false;//suponemos, a priori, que no hay transiciones
				
				for (int j = 0; j < transactions.size(); j++) {
					//si encuentra alguna transicion entra
					if (actualState.equals(transactions.get(j)[0])
							&& simboloTratado.equals(transactions.get(j)[1])
							&& stack.peek().equals(transactions.get(j)[2])) {
						
						actualState = transactions.get(j)[3];
						stack.pop();
						
						//Si la pila tiene mas de un elemento hay que meterlos uno a uno separados
						if(transactions.get(j)[4].length() > 1){
							String aux = transactions.get(j)[4];
							for(int k = 0; k < transactions.get(j)[4].length(); k++){
								String aux1 = aux.substring(aux.length()-1);
								aux = aux.substring(0,aux.length()-1);
								stack.push(aux1);
							}
						}else{//si no, no hace falta separar
							if(transactions.get(j)[4].equals("@")){
								//si es un, vacío no hacer nada
							}else{	
								stack.push(transactions.get(j)[4]);
							}
						}
						noTransactions = true;
						break;//si se encuentra la transicion pasa al siguiente simbolo de la cadena
					}
				}
			} else{// si no hay transiciones, paramos
				break;
			}
		}//END FOR (NO QUEDAN TRANSICIONES)
		isStringAccept(inputChain);
	}

	public void isStringAccept(String inputChain){
		if(f.get(0).equals("@")){//autmata vaciado de pila
			if(inputChain.isEmpty() && stack.isEmpty()){
				ioManager.showInfo("Cadena aceptada");
			}else {
				ioManager.showInfo("Cadena no aceptada!");
			}
		}else{
			for(int i = 0; i < f.size(); i++){
				if(inputChain.isEmpty() && actualState.equals(f.get(i))){
					ioManager.showInfo("Cadena aceptada!");
					break;
				}else{
					ioManager.showInfo("Cadena no aceptada!");
				}
			}
		}
	}
}