import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quiz {

    private Pergunta[] perguntas;

    public Quiz(Pergunta[] perguntas) {
        this.perguntas = perguntas;
        exibirInterface();
    }

    private void exibirInterface() {
        JFrame frame = new JFrame("Quiz Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        for (int i = 0; i < perguntas.length; i++) {
            Pergunta pergunta = perguntas[i];


            JLabel labelNumeroPergunta = new JLabel((i + 1) + "- ");
            JLabel labelPergunta = new JLabel(pergunta.getPergunta());
            panel.add(labelNumeroPergunta);
            panel.add(labelPergunta);


            ButtonGroup group = new ButtonGroup();
            for (String opcao : pergunta.getOpcoes()) {
                JRadioButton radioButton = new JRadioButton(opcao);
                group.add(radioButton);
                panel.add(radioButton);
            }

            JButton button = new JButton("Responder");
            JLabel labelResposta = new JLabel();

            final int finalI = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (java.awt.Component component : panel.getComponents()) {
                        if (component instanceof JRadioButton) {
                            JRadioButton radioButton = (JRadioButton) component;
                            if (radioButton.isSelected()) {
                                if (radioButton.getText().charAt(0) == perguntas[finalI].getRespostaCorreta()) {
                                    labelResposta.setText((finalI + 1) +"- Resposta correta! A resposta é: " + perguntas[finalI].getRespostaCorreta());
                                } else {
                                    labelResposta.setText((finalI + 1) +"- Resposta incorreta. A resposta correta é: " + perguntas[finalI].getRespostaCorreta());
                                }
                            }
                        }
                    }
                    panel.add(labelResposta);
                    frame.revalidate();
                    frame.repaint();
                }
            });
            panel.add(button);
        }
        JScrollPane scrollPane = new JScrollPane(panel);
        frame.getContentPane().add(scrollPane);
        frame.pack();
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        Pergunta[] perguntas = {
                new Pergunta("Qual é o resultado da multiplicação: 7 x 9?",
                        new String[]{"A) 45", "B) 54", "C) 63", "D) 72"}, 'C'),

                new Pergunta("Qual é a soma dos ângulos internos de um triângulo?",
                        new String[]{"A) 90 graus", "B) 180 graus", "C) 270 graus", "D) 360 graus"}, 'B'),

                new Pergunta("Qual é a raiz quadrada de 121?",
                        new String[]{"A) 9", "B) 10", "C) 11", "D) 12"}, 'C'),

                new Pergunta("Se um retângulo tem comprimento 8 e largura 5, qual é a área?",
                        new String[]{"A) 13", "B) 30", "C) 40", "D) 48"}, 'C'),

                new Pergunta("Qual é o valor de π (pi) aproximado?",
                        new String[]{"A) 2.718", "B) 3.142", "C) 4.669", "D) 5.927"}, 'B'),

                new Pergunta("Se x + 5 = 12, qual é o valor de x?",
                        new String[]{"A) 5", "B) 7", "C) 8", "D) 12"}, 'B'),

                new Pergunta("Qual é o resultado da divisão: 144 ÷ 12?",
                        new String[]{"A) 10", "B) 11", "C) 12", "D) 13"}, 'C'),

                new Pergunta("Quantos lados tem um hexágono?",
                        new String[]{"A) 5", "B) 6", "C) 7", "D) 8"}, 'B'),

                new Pergunta("Qual é o volume de um cubo com aresta de comprimento 3?",
                        new String[]{"A) 6", "B) 9", "C) 18", "D) 27"}, 'D'),

                new Pergunta("Se a = 4 e b = 7, qual é o valor de a² + b²?",
                        new String[]{"A) 33", "B) 49", "C) 53", "D) 65"}, 'B'),

                new Pergunta("Qual é a fórmula para calcular a área de um círculo?",
                        new String[]{"A) A = πr", "B) A = 2πr", "C) A = πr²", "D) A = 0.5πr²"}, 'C'),

                new Pergunta("Se um triângulo é equilátero, o que isso significa?",
                        new String[]{"A) Todos os lados são diferentes.", "B) Todos os ângulos são iguais.", "C) Um lado é maior que os outros dois.", "D) Um ângulo é maior que os outros dois."}, 'B'),

                new Pergunta("Qual é o valor de sen(90 graus)?",
                        new String[]{"A) 0", "B) 0.5", "C) 1", "D) 2"}, 'C'),

                new Pergunta("Quantos graus tem um círculo completo?",
                        new String[]{"A) 180", "B) 270", "C) 360", "D) 450"}, 'C'),

                new Pergunta("Qual é o resultado da expressão 3² - 4 x 2 + 5?",
                        new String[]{"A) 4", "B) 5", "C) 6", "D) 7"}, 'C'),
        };
        SwingUtilities.invokeLater(() -> {
            Quiz interfaceQuiz = new Quiz(perguntas);
        });
    }
}
