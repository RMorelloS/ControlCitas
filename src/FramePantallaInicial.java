
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.net.URL;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class FramePantallaInicial extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static JButton btnRegistrarPaciente;
	static JButton btnRegistrarCita;
	/**
	 * Create the frame.
	 */
	public FramePantallaInicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 600);
		contentPane = new GradientPanel(93, 188, 210);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnRegistrarPaciente = new JButton("Registrar paciente");
		btnRegistrarPaciente.setToolTipText("Agregar un nuevo paciente");
		btnRegistrarPaciente.setFont(new Font("Yu Gothic", Font.PLAIN, 30));
		btnRegistrarPaciente.setForeground(new Color(255, 255, 255));
		btnRegistrarPaciente.setBackground(new Color(0, 204, 255));
		ControllerPantallaInicial controllerInicial = new ControllerPantallaInicial();
		btnRegistrarPaciente.addActionListener(controllerInicial);
		btnRegistrarPaciente.setBounds(189, 293, 343, 67);
		contentPane.add(btnRegistrarPaciente);
		
		btnRegistrarPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnRegistrarPaciente.setBackground(new Color(51, 102, 204));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnRegistrarPaciente.setBackground(new Color(0,204,255));
		    }
		});
		
	    btnRegistrarCita = new JButton("Registrar cita");
		btnRegistrarCita.setToolTipText("Marcar una cita con un paciente");
		btnRegistrarCita.setFont(new Font("Yu Gothic", Font.PLAIN, 30));
		btnRegistrarCita.setForeground(new Color(255, 255, 255));
		btnRegistrarCita.setBackground(new Color(0, 204, 255));
		btnRegistrarCita.setBounds(189, 401, 343, 67);
		btnRegistrarCita.addActionListener(controllerInicial);
		contentPane.add(btnRegistrarCita);
		

		btnRegistrarCita.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnRegistrarCita.setBackground(new Color(51, 102, 204));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnRegistrarCita.setBackground(new Color(0,204,255));
		    }
		});
		
		URL url = this.getClass().getResource("Images\\ibero-logo.png");
		ImageIcon icon = new ImageIcon(url);
		JLabel lblLogoIbero = new JLabel(icon);
		lblLogoIbero.setBounds(216, 16, 303, 115);
		contentPane.add(lblLogoIbero);
		
		
		URL url_doctor = this.getClass().getResource("Images\\Doctor.png");
		ImageIcon icon_doctor = new ImageIcon(url_doctor);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 153, 204, 150));
		
		panel.setBounds(52, 151, 632, 341);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("CONTROL DE CITAS");
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Yu Gothic", Font.BOLD, 47));
		
		JLabel lblDoctor = new JLabel(icon_doctor);
		lblDoctor.setToolTipText("");
		lblDoctor.setBounds(0, 0, 734, 544);
		contentPane.add(lblDoctor);

	}
}
