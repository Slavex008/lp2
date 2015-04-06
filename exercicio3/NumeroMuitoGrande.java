import java.util.Scanner;

public class NumeroMuitoGrande {
    int V1[], V2[], SOM[], MUL[], tamanho1, tamanho2;
    
    public void mult(){
        int i=0, x=0, l=0, j=0, mult[];
        mult = new int [30];
        String k="";
        //calculo da multiplicacao
        while (i<= (this.tamanho1 - 1)){
            l = i;
            while (j <= (tamanho2 - 1)){
                mult[l] = mult[l] + (this.V1[i] * this.V2[j]);
                if ((mult[l] >= 10) && (l<=28)){
                    mult[l+1] = mult[l]/10;
                    mult[l] = mult[l]%10;            
                }
                else{
                    if((l==29) && (mult[i] >= 10)){
                        x=1;
                    }
                }
                this.MUL[j] = mult[l] + this.MUL[j]; 
                if ((this.MUL[j] >= 10) && (j<=28)){
                    this.MUL[i] = this.MUL[j] - 10;
                    this.MUL[j+1] = this.MUL[j+1] + 1;
                }
                else{
                    if((j==29) && (this.MUL[j] >= 10)){
                        x=1;
                    }
                }
                l++;
            
            }
        i++;
        }
        i=0;
        //saida da multiplicacao
        if (x!=1){
            while (i<=29){
                k = k + this.MUL[i];
                i++;
            }
            System.out.println("A multiplicacao é: " + k);
        }
        else{
            System.out.println("Mais de 30 casas! Valor não será imprimido!");
        }

    }
    public void soma(){
        int i=0, x=0;
        String k="";
        //calculo da soma
        while (i<=29){
            this.SOM[i] = this.SOM[i] + this.V1[i] + this.V2[i];
            if ((this.SOM[i] >= 10) && (i<=28)){
                this.SOM[i] = this.SOM[i] - 10;
                this.SOM[i+1] = this.SOM[i+1] + 1;
            }
            else{
                if((i==29) && (this.SOM[i] >= 10)){
                    x=1;
                }
            }
            i++;
        }
        i=0;
        //saida da soma
        if (x!=1){
            while (i<=29){
                k = k + this.SOM[i];
                i++;
            }
            System.out.println("A soma é: " + k);
        }
        else{
            System.out.println("Mais de 30 casas! Valor não será imprimido!");
        }
    }
    
    public static void main(String[] args) {
        NumeroMuitoGrande X;
        Scanner input;
        int i=0, j=0;
		input = new Scanner(System.in);
        X = new NumeroMuitoGrande();
        X.V1 = new int[30];
        X.V2 = new int[30];
        X.SOM = new int[30];
        X.MUL = new int[30]; 
        //zerar V1
        while (i <= 29){
            X.V1[i] = 0;        
            i++;
        }
        //zerar V2
        while (i <= 29){
            X.V2[i] = 0;        
            i++;
        }
        //zerar MUL
        while (i <= 29){
            X.MUL[i] = 0;        
            i++;
        }
        //zerar SOM
        while (i <= 29){
            X.SOM[i] = 0;
            i++;
        }
        
        
        //ler primeiro valor
        System.out.println("Digite o valor 1 (máximo de 30 digitos): ");
        String valor = input.next();
	X.tamanho1 = valor.length();        
        i = X.tamanho1 - 1;
	while ((i >= 0) && (j <= i)){
            char caracter = valor.charAt(i);
            X.V1[j] = Character.getNumericValue(caracter);
            i--;
            j++;
        }        
		valor="";
        i=0;
        //ler segundo valor
        System.out.println("Digite o valor 2 (máximo de 30 digitos): ");
        valor = input.next();
	X.tamanho2 = valor.length();
        i = X.tamanho2 - 1;
        while ((i >= 0) && (j <= i)){
            char caracter = valor.charAt(i);
            X.V2[j] = Character.getNumericValue(caracter);
            i--;
            j++;
                    
        }
        i=0;
        X.soma();
        X.mult();
    }
    
}
