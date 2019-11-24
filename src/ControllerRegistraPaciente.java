import java.awt.Component;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
 
public class ControllerRegistraPaciente implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		 
		// TODO Auto-generated method stub
		if (e.getSource() == FrameRegistraPaciente.btnRegistraPaciente) {
			ModelPaciente nuevoPaciente = new ModelPaciente(FrameRegistraPaciente.txtNombrePaciente.getText(), FrameRegistraPaciente.txtApellidoMaterno.getText(), FrameRegistraPaciente.txtApellidoPaterno.getText(), FrameRegistraPaciente.txtCorreo.getText());
			ControllerBaseDatos controllerBaseDatos = new ControllerBaseDatos();
			try {
				controllerBaseDatos.insertaPaciente(nuevoPaciente);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if (e.getSource() == FrameRegistraPaciente.btnRegresa) {
			Component component = (Component) e.getSource();
	        JFrame frameRegistraCita = (JFrame) SwingUtilities.getRoot(component);
	        frameRegistraCita.setVisible(false);
	        FramePantallaInicial framePantallaInicial = new FramePantallaInicial();
	        framePantallaInicial.setVisible(true);
		}
	}
	
}
