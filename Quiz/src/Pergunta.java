class Pergunta {

    private String pergunta;
    private String[] opcoes;
    private char respostaCorreta;

    public Pergunta(String pergunta, String[] opcoes, char respostaCorreta) {
        this.pergunta = pergunta;
        this.opcoes = opcoes;
        this.respostaCorreta = respostaCorreta;
    }

    public String getPergunta() {
        return pergunta;
    }

    public String[] getOpcoes() {
        return opcoes;
    }

    public char getRespostaCorreta() {
        return respostaCorreta;
    }
}