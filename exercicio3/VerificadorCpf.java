import java.util.Scanner;

public class VerificadorCpf{
	int V[];
	
	public void verificar1(){
		int peso = 10, k = 0, a;
		this.V[9] = this.V[k] * peso;
		k++;
		peso--;
		while (peso >= 2){
			this.V[9] = this.V[9] + (this.V[k] * peso);
			k++;
			peso--;
		}
		a = this.V[9]%11;
		if (a <= 1){
			this.V[9] = 0;
		}
		else{
			this.V[9] = (11 - a);
		}
	}

	public void verificar2(){
		int peso = 11, k = 0, a;
		this.V[10] = this.V[k] * peso;
		k++;
		peso--;
		while (peso >= 2){
			this.V[10] = this.V[10] + (this.V[k] * peso);
			k++;
			peso--;
		}
		a = this.V[10]%11;
		if (a <= 1){
			this.V[10] = 0;
		}
		else{
			this.V[10] = ( 11 - a);
		}
	}


	public static void main(String [] args){
		Scanner input = new Scanner(System.in);
		VerificadorCpf X;
		X = new VerificadorCpf();
		X.V = new int[11];
		int i=0;
		System.out.println("Digite os 9 primeiros valores do cpf: ");		
		String numcpf = input.next();
		while (i<=8){
                        char caracter = numcpf.charAt(i);
			X.V[i] = Character.getNumericValue(caracter);
			i++;
		}
		X.verificar1();
		X.verificar2();
		i=0;
		numcpf= "";
		while (i<=10){
			numcpf = numcpf + X.V[i];
			i++;
		}
		System.out.println("O cpf completo e: " + numcpf);
	}
}
