import java.util.Scanner;

public class Gestão_Vacinas {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        usuario u1 = new usuario();
        vacinas v1 = new vacinas("Gripe", "1 vez ao ano");
        
        System.out.println("Cadastro de usuário: ");

        System.out.print("Nome: ");
        u1.setNome(sc.nextLine());

        System.out.print("Idade: ");
        u1.setIdade(sc.nextInt());
        sc.nextLine(); // Consumir a quebra de linha pendente

        System.out.print("CPF: ");
        u1.setCpf(sc.nextLine());

        System.out.print("Endereço: ");
        u1.setEndereco(sc.nextLine());

        System.out.print("Vacinas: ");
        u1.setVacinas(sc.nextLine());

        
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
}
