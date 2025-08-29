import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.text.DecimalFormat;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Principal {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        // Inserir todos os funcionários ---
        System.out.println("--- Inserindo funcionários ---");
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
        System.out.println("Funcionários inseridos.\n");

        // Remover o funcionário “João” da lista ---
        System.out.println("--- Removendo o funcionário João ---");
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));
        System.out.println("Funcionário 'João' removido.\n");

        // Imprimir todos os funcionários com formatação ---
        System.out.println("--- Imprimindo todos os funcionários ---");
        // A formatação já está sendo feita pelo método toString() que criamos na classe Funcionario.
        for (Funcionario f : funcionarios) {
            System.out.println(f);
        }
        System.out.println();

        // Aumento de 10% no salário ---
        System.out.println("aumento de 10% no salário ---");
        for (Funcionario f : funcionarios) {
            BigDecimal novoSalario = f.getSalario().multiply(new BigDecimal("1.10"));
            f.setSalario(novoSalario);
        }
        System.out.println("Aumento aplicado. Veja a lista atualizada:");
        funcionarios.forEach(System.out::println);
        System.out.println();

        // --- Agrupar os funcionários por função em um MAP ---
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // --- Imprimir os funcionários, agrupados por função ---
        System.out.println("--- Imprimindo funcionários agrupados por função ---");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(System.out::println);
            System.out.println();
        });

        // --- Imprimir os funcionários que fazem aniversário no mês 10 e 12 ---
        System.out.println("--- Aniversariantes de Outubro (10) e Dezembro (12) ---");
        funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)
                .forEach(System.out::println);
        System.out.println();

        // --- Imprimir o funcionário com a maior idade ---
        System.out.println("--- Funcionário com maior idade ---");
        Funcionario maisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElse(null);

        if (maisVelho != null) {
            int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
            System.out.println("Nome: " + maisVelho.getNome() + ", Idade: " + idade + " anos.\n");
        }

        // --- Imprimir a lista de funcionários por ordem alfabética ---
        System.out.println("--- Lista de funcionários em ordem alfabética ---");
        funcionarios.stream()
                .sorted(Comparator.comparing(Pessoa::getNome))
                .forEach(System.out::println);
        System.out.println();

        // --- 3.11 – Imprimir o total dos salários dos funcionários ---
        System.out.println("--- Total dos salários ---");
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        DecimalFormat df = new DecimalFormat("#,##0.00");
        System.out.println("Total: R$ " + df.format(totalSalarios) + "\n");

        // --- Imprimir quantos salários mínimos ganha cada funcionário ---
        System.out.println("--- Salários mínimos por funcionário ---");
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        for (Funcionario f : funcionarios) {
            BigDecimal salariosMinimos = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.println(f.getNome() + " ganha " + salariosMinimos + " salários mínimos.");
        }
    }
}