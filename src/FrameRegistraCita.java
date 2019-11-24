import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.JLabel;
 

import java.util.Date;
import java.awt.TextField;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JComboBox;
 
public class FrameRegistraCita extends JFrame {

	private JPanel contentPane;
	static String fechaCita;
	static JButton btnRegistrarCita;
	static JButton btnRegresa;
	static PlaceholderTextField correoAlumno;
	static PlaceholderTextField descripcionCita;
	static JComboBox<String> comboBoxHorarios;
	static JComboBox<String> comboBoxMedicos;
	static JDateChooser date_chooser;
	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameRegistraCita frame = new FrameRegistraCita();
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
	public FrameRegistraCita() {
		setTitle("Registrar cita");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 849, 598);
		contentPane = new GradientPanel(93, 188, 210);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		date_chooser = new JDateChooser();
		date_chooser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		date_chooser.setBounds(301,101,425,44);
		date_chooser.getJCalendar().setMinSelectableDate(new Date()); 
		JTextFieldDateEditor editor = (JTextFieldDateEditor) date_chooser.getDateEditor();
		editor.setEditable(false);

		correoAlumno= new PlaceholderTextField("");
		correoAlumno.setPlaceholder("Correo del alumno");
		correoAlumno.setForeground(Color.WHITE);
		correoAlumno.setFont(new Font("Tahoma", Font.PLAIN, 26));
		correoAlumno.setColumns(10);
		correoAlumno.setBackground(new Color(93, 188, 210));
		correoAlumno.setBounds(114, 326, 614, 32);
		
		contentPane.add(correoAlumno);
		
		ControllerRegistraCita controllerRegistraCita = new ControllerRegistraCita();
		date_chooser.getDateEditor().addPropertyChangeListener(controllerRegistraCita);
		
		contentPane.add(date_chooser);
		
		btnRegistrarCita = new JButton("Registrar cita");
		btnRegistrarCita.setToolTipText("Registrar una nueva cita");
		btnRegistrarCita.setForeground(Color.WHITE);
		btnRegistrarCita.setFont(new Font("Yu Gothic", Font.PLAIN, 30));
		btnRegistrarCita.setBackground(new Color(0, 204, 255));
		btnRegistrarCita.setBounds(573, 469, 239, 57);
		btnRegistrarCita.addActionListener(controllerRegistraCita);
		btnRegistrarCita.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnRegistrarCita.setBackground(new Color(51, 102, 204));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnRegistrarCita.setBackground(new Color(0,204,255));
		    }
		});
		contentPane.add(btnRegistrarCita);
		
		btnRegresa = new JButton("Regresar");
		btnRegresa.setToolTipText("Regresar al menu");
		btnRegresa.setForeground(Color.WHITE);
		btnRegresa.setFont(new Font("Yu Gothic", Font.PLAIN, 30));
		btnRegresa.setBackground(new Color(0, 204, 255));
		btnRegresa.setBounds(15, 469, 239, 57);
		btnRegresa.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnRegresa.setBackground(new Color(51, 102, 204));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnRegresa.setBackground(new Color(0,204,255));
		    }
		});
		btnRegresa.addActionListener(controllerRegistraCita);
		contentPane.add(btnRegresa);
		
		JLabel lblInsertaLasInformaciones = new JLabel("Inserta las informaciones de la cita:");
		lblInsertaLasInformaciones.setForeground(Color.WHITE);
		lblInsertaLasInformaciones.setFont(new Font("Yu Gothic", Font.BOLD, 35));
		lblInsertaLasInformaciones.setBounds(114, 16, 653, 69);
		contentPane.add(lblInsertaLasInformaciones);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setFont(new Font("Yu Gothic", Font.PLAIN, 30));
		lblFecha.setBounds(112, 101, 101, 51);
		contentPane.add(lblFecha);
		
		
		
		descripcionCita = new PlaceholderTextField("");
		descripcionCita.setPlaceholder("Descripción de la cita");
		descripcionCita.setForeground(Color.WHITE);
		descripcionCita.setFont(new Font("Tahoma", Font.PLAIN, 26));
		descripcionCita.setColumns(10);
		descripcionCita.setBackground(new Color(93, 188, 210));
		descripcionCita.setBounds(112, 400, 614, 32);
		contentPane.add(descripcionCita);
		
		
		 
		
		JLabel lblMdico = new JLabel("M\u00E9dico:");
		lblMdico.setToolTipText("M\u00E9dico responsable por la cita");
		lblMdico.setForeground(Color.WHITE);
		lblMdico.setFont(new Font("Yu Gothic", Font.PLAIN, 30));
		lblMdico.setBounds(114, 165, 128, 51);
		contentPane.add(lblMdico);
		
		comboBoxMedicos = new JComboBox<String>();
		comboBoxMedicos.setBounds(301, 165, 425, 42);
		contentPane.add(comboBoxMedicos);
		comboBoxMedicos.addItem("Ana");
		comboBoxMedicos.addItem("Ivete");
		comboBoxMedicos.addItem("Miriam");
		comboBoxMedicos.setFont(new Font("Yu Gothic", Font.PLAIN, 30));
		comboBoxMedicos.setForeground(Color.black); 
		comboBoxMedicos.addActionListener(controllerRegistraCita);
		comboBoxHorarios = new JComboBox<String>();
		comboBoxHorarios.setForeground(Color.BLACK);
		comboBoxHorarios.setFont(new Font("Yu Gothic", Font.PLAIN, 30));
		comboBoxHorarios.setBounds(301, 235, 425, 42);
		contentPane.add(comboBoxHorarios);
		
		JLabel lblHorario = new JLabel("Horario:");
		lblHorario.setToolTipText("M\u00E9dico responsable por la cita");
		lblHorario.setForeground(Color.WHITE);
		lblHorario.setFont(new Font("Yu Gothic", Font.PLAIN, 30));
		lblHorario.setBounds(114, 232, 128, 51);
		contentPane.add(lblHorario);
	}
}
