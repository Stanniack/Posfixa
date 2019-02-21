

import java.util.Stack;

/**
 * @author 12161003882
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
// TODO code application logic here
        Stack<Character> pilhaOperadores = new Stack<>();
        String equacao = "(2+4*3+(2/4))";
        StringBuilder resultado = new StringBuilder();
        boolean parenteses = false;


        for (int i = 0; i < equacao.length(); i++) {

            // Se o char achado for um operando, concatena no resultado
            if ((equacao.charAt(i) >= '0' && equacao.charAt(i) <= '9')
                    || (equacao.charAt(i) >= 'A' && equacao.charAt(i) <= 'Z')
                    || (equacao.charAt(i) >= 'a' && equacao.charAt(i) <= 'z')) {

                resultado.append(equacao.charAt(i));

                // gambiarra
                if (pilhaOperadores.size() > 1) {
                    if(parenteses == false){
                        resultado.append(pilhaOperadores.lastElement());
                        pilhaOperadores.pop();
                    }

                }

                // Senão, é operador
            } else {
                // Se a pilha estiver vazia
                if (pilhaOperadores.isEmpty() && (equacao.charAt(i) != '(' && equacao.charAt(i) != ')')) {
                    pilhaOperadores.push(equacao.charAt(i));

                } else {
                    // Lógica da prioridade

                    if(equacao.charAt(i) == '('){
                        parenteses = true;
                        pilhaOperadores.push(equacao.charAt(i));

                    }else if (equacao.charAt(i) == ')') {
                        while (!pilhaOperadores.lastElement().equals('(')) {
                            resultado.append(pilhaOperadores.lastElement());
                            pilhaOperadores.pop();
                        }

                        pilhaOperadores.pop();
                        parenteses = false;

                    // Se o topo de pilha for um '(', apenas empilhe o operador encontrado
                    }else if (pilhaOperadores.lastElement().equals('(')){
                        pilhaOperadores.push(equacao.charAt(i));

                    }else if (prioridade(pilhaOperadores.lastElement()) == prioridade(equacao.charAt(i))
                            || prioridade(pilhaOperadores.lastElement()) > prioridade(equacao.charAt(i))) {

                        resultado.append(pilhaOperadores.lastElement());
                        pilhaOperadores.pop();
                        pilhaOperadores.push(equacao.charAt(i));

                    }else if (prioridade(pilhaOperadores.lastElement()) < prioridade(equacao.charAt(i))) {
                        // tratar isso daqui, pode estar errado
                        pilhaOperadores.push(equacao.charAt(i));
                    }


                }
            }
        } // for

        while (!pilhaOperadores.isEmpty()) {
            resultado.append(pilhaOperadores.lastElement());
            pilhaOperadores.pop();
        }

        System.out.println(resultado);
        System.out.println(pilhaOperadores);
    }


    public static int prioridade(Character c) {
        int prioridade = 0;

        if (c.equals('+') || c.equals('-')) {
            prioridade = 1;
        } else if (c.equals('*') || c.equals('/')) {
            prioridade = 2;
        }else if (c.equals('^')) {
            prioridade = 2;
        }

        return prioridade;
    }

}