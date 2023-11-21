import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.Color;
import java.awt.Font;



public class Unidad4_IO extends JFrame implements ActionListener {
    private JLabel labelLambda, labelNiu;
    private JTextField txtLambda, txtNiu;
    private JButton btnCalcular;
    private JTextArea resultadosArea;
    private JPanel fondo;
    private JPanel arriba;
    private JPanel titulo;
    
    public Unidad4_IO() {
        setTitle("Sistema de Colas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
       
        JPanel panel = new JPanel();
        panel.setLayout(null);
        

        labelLambda = new JLabel("LAMBDA λ:");
        labelLambda.setBounds(20, 20, 80, 25);
        panel.add(labelLambda);

        txtLambda = new JTextField();
        txtLambda.setBounds(100, 20, 100, 25);
        panel.add(txtLambda);

        labelNiu = new JLabel("NIU μ:");
        labelNiu.setBounds(20, 50, 80, 25);
        panel.add(labelNiu);

        txtNiu = new JTextField();
        txtNiu.setBounds(100, 50, 100, 25);
        panel.add(txtNiu);

        btnCalcular = new JButton("Calcular Resultados");
        btnCalcular.setBounds(150, 80, 150, 25);
        panel.add(btnCalcular);
        btnCalcular.addActionListener(this);

        resultadosArea = new JTextArea();
        resultadosArea.setBounds(20, 120, 350, 130);
        resultadosArea.setEditable(false);
        panel.add(resultadosArea);
        

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCalcular) {
            double lambda = Double.parseDouble(txtLambda.getText());
            int niu = Integer.parseInt(txtNiu.getText());

            double rho = lambda / niu;
            double p0 = calcularP0(lambda, niu);
            double Lq = calcularLq(lambda, niu);
            double L = Lq + rho;
            double Wq = Lq / lambda;
            double W = Wq + (1.0 / niu);

            DecimalFormat df = new DecimalFormat("#.####");
            String resultados = "Probabilidad de sistema ocupado (ρ): " + df.format(rho) + "\n" +
                                "Probabilidad de tener que esperar (P0): " + df.format(p0) + "\n" +
                                "Número promedio de clientes en la cola (Lq): " + df.format(Lq) + "\n" +
                                "Número promedio de clientes en el sistema (L): " + df.format(L) + "\n" +
                                "Tiempo de espera promedio de clientes en la cola (Wq): " + df.format(Wq) + "\n" +
                                "Tiempo de espera promedio de clientes en el sistema (W): " + df.format(W) + "\n";

            resultadosArea.setText(resultados);
        }
    }

    private double calcularP0(double lambda, int niu) {
        double sumatoria = 0;
        for (int i = 0; i < niu; i++) {
            sumatoria += Math.pow(lambda / niu, i) / factorial(i);
        }
        return 1 / (sumatoria + (Math.pow(lambda / niu, niu) / (factorial(niu) * (1 - (lambda / niu)))));
    }

    private double calcularLq(double lambda, int niu) {
        double rho = lambda / niu;
        double p0 = calcularP0(lambda, niu);
        return (Math.pow(rho, niu) * p0) / (factorial(niu) * Math.pow(1 - rho, 2));
    }

    private double factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        new Unidad4_IO();
    }
}
