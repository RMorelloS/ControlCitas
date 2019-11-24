import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameRegistraPaciente extends JFrame {
	static JButton btnRegresa;
	static JButton btnRegistraPaciente;
	private JPanel contentPane;
	static PlaceholderTextField txtNombrePaciente;
	static PlaceholderTextField txtApellidoPaterno;
	static PlaceholderTextField txtApellidoMaterno;
	static PlaceholderTextField txtCorreo;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameRegistraPaciente frame = new FrameRegistraPaciente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 */
	public FrameRegistraPaciente() {
		setTitle("Registrar paciente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 695, 416);
		contentPane = new GradientPanel(93, 188, 210);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegistrarPaciente = new JLabel("Inserta las informaciones del paciente:");
		lblRegistrarPaciente.setForeground(Color.WHITE);
		lblRegistrarPaciente.setFont(new Font("Yu Gothic", Font.BOLD, 29));
		lblRegistrarPaciente.setBounds(43, 16, 653, 69);
		contentPane.add(lblRegistrarPaciente);
		
	    txtNombrePaciente = new PlaceholderTextField("");
	    txtNombrePaciente.setForeground(Color.WHITE);
	    txtNombrePaciente.setColumns(10);
	    txtNombrePaciente.setPlaceholder("Nombre");
	    txtNombrePaciente.setFont(new Font("Tahoma", Font.PLAIN, 26));
	    txtNombrePaciente.setBackground(new Color(93,188,210));
	    txtNombrePaciente.setBounds(43, 86, 614, 32);
		contentPane.add(txtNombrePaciente);
		
	    txtApellidoPaterno = new PlaceholderTextField("");
		txtApellidoPaterno.setForeground(Color.WHITE);
		txtApellidoPaterno.setColumns(10);
		txtApellidoPaterno.setPlaceholder("Apellido paterno");
		txtApellidoPaterno.setFont(new Font("Tahoma", Font.PLAIN, 26));
		txtApellidoPaterno.setBackground(new Color(93,188,210));
		txtApellidoPaterno.setColumns(10);
		txtApellidoPaterno.setBounds(43, 134, 614, 32);
		contentPane.add(txtApellidoPaterno);
		
	    txtApellidoMaterno = new PlaceholderTextField("");
		txtApellidoMaterno.setForeground(Color.WHITE);
		txtApellidoMaterno.setColumns(10);
		txtApellidoMaterno.setFont(new Font("Tahoma", Font.PLAIN, 26));
		txtApellidoMaterno.setPlaceholder("Apellido materno");
		txtApellidoMaterno.setBackground(new Color(93,188,210));
		txtApellidoMaterno.setColumns(10);
		txtApellidoMaterno.setBounds(43, 182, 614, 32);
		contentPane.add(txtApellidoMaterno);
		
		txtCorreo = new PlaceholderTextField("");
		txtCorreo.setForeground(Color.WHITE);
		txtCorreo.setColumns(100);
		txtCorreo.setPlaceholder("Correo");
		txtCorreo.setFont(new Font("Tahoma", Font.PLAIN, 26));
		txtCorreo.setColumns(10);
		txtCorreo.setBackground(new Color(93,188,210));
		txtCorreo.setBounds(43, 230, 614, 32);
		contentPane.add(txtCorreo);
		
		btnRegistraPaciente = new JButton("Registrar");
		
		btnRegistraPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnRegistraPaciente.setBackground(new Color(51, 102, 204));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnRegistraPaciente.setBackground(new Color(0,204,255));
		    }
		});
		ControllerRegistraPaciente controllerRegistraPaciente = new ControllerRegistraPaciente();
		btnRegistraPaciente.addActionListener(controllerRegistraPaciente);
		btnRegistraPaciente.setToolTipText("Finalizar");
		btnRegistraPaciente.setForeground(Color.WHITE);
		btnRegistraPaciente.setFont(new Font("Yu Gothic", Font.PLAIN, 30));
		btnRegistraPaciente.setBackground(new Color(0, 204, 255));
		btnRegistraPaciente.setBounds(474, 299, 183, 40);
		contentPane.add(btnRegistraPaciente);
		
		
		
		btnRegresa = new JButton("Regresar");
		btnRegresa.addActionListener(controllerRegistraPaciente);
		
		btnRegresa.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnRegresa.setBackground(new Color(51, 102, 204));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnRegresa.setBackground(new Color(0,204,255));
		    }
		});
		
		btnRegresa.setToolTipText("Regresar al menu");
		btnRegresa.setForeground(Color.WHITE);
		btnRegresa.setFont(new Font("Yu Gothic", Font.PLAIN, 30));
		btnRegresa.setBackground(new Color(0, 204, 255));
		btnRegresa.setBounds(43, 299, 183, 40);
		contentPane.add(btnRegresa);
	}
}
