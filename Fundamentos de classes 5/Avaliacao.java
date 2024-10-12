public class Avaliacao {
    private String titulo;
    private double nota;

    Estudante estudante;

    public Avaliacao(String titulo, double nota){
        this.titulo = titulo;
        this.nota = nota;
    }
    public String getTitulo(){
        return titulo;
    }
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    public double getNota(){
        return nota;
    }
    public void setNota(double nota){
        this.nota = nota;
    }
}
