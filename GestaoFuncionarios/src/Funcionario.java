import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcaoo; // Usei "funcaoo" para evitar conflito com a palavra reservada "function" em alguns contextos

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcaoo) {
        super(nome, dataNascimento); // Chama o construtor da classe pai (Pessoa)
        this.salario = salario;
        this.funcaoo = funcaoo;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcaoo;
    }

    public void setFuncao(String funcaoo) {
        this.funcaoo = funcaoo;
    }

    // Sobrescrevendo o método toString para facilitar a impressão
    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormat numberFormatter = new DecimalFormat("#,##0.00");

        return "Funcionario {" +
                "nome='" + getNome() + '\'' +
                ", dataNascimento=" + getDataNascimento().format(dateFormatter) +
                ", salario=" + numberFormatter.format(salario) +
                ", função='" + funcaoo + '\'' +
                '}';
    }
}