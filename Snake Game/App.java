import javax.swing.JFrame;
//import javax.swing.JPanel;
import java.awt.Dimension;

public class App {
    public static void main(String[] args) {
        int boardWidth = 600;   // Largura da janela em pixels
        int boardHeight = 600;  // Altura da janela em pixels
  

        JFrame frame = new JFrame("Snake Game"); // Cria a janela com o título "Snake"
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha a aplicação ao fechar a janela
        frame.setResizable(false); // Impede que o usuário redimensione a janela

        Board board = new Board(boardHeight, boardHeight); // Cria um painel vazio
        board.setPreferredSize(new Dimension(boardWidth, boardHeight)); // Define o tamanho do painel
        frame.add(board); // Adiciona o painel à janela

        frame.pack(); // Ajusta o tamanho da janela para caber o conteúdo
        frame.setLocationRelativeTo(null); // Centraliza a janela na tela
        frame.setVisible(true); // Torna a janela visível

    }
}