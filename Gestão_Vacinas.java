import java.util.Scanner;

public class Gestão_Vacinas {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        usuario u1 = new usuario();
        vacinas v1 = new vacinas("Gripe", "1 vez ao ano");
        
        System.out.println("Insira as informações do usuário: ");
        
        u1.setNome("Nome: " + sc.nextLine());

        System.out.print("Idade: ");
        u1.setIdade(sc.nextInt());
        sc.nextLine();

        u1.setCpf("CPF: " + sc.nextLine());
        u1.setEndereco("Endereco: " + sc.nextLine());
        u1.setVacinas("Vacinas: " + sc.nextLine());
        
        System.out.println();
        System.out.println("Usuário:");
        System.out.println(u1.getNome());
        System.out.println(u1.getIdade());
        System.out.println(u1.getCpf());
        System.out.println(u1.getEndereco());
        System.out.println(u1.getVacinas());
        System.out.println();
        System.out.println("Vacinas:");
        System.out.println(v1.getNome());
        System.out.println(v1.getFrequencia());
    }
    public static void test(){
        System.out.println("Isto é um teste");
    }
}
