import java.awt.Component;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.SwingUtilities;

import org.jdesktop.swingx.autocomplete.*;

public class ControllerRegistraCita implements ActionListener, PropertyChangeListener {
	public ControllerRegistraCita() {
		ControllerBaseDatos controllerBaseDatos = new ControllerBaseDatos();
        ArrayList<String> correoPacientes = controllerBaseDatos.buscaCorreoPacientes();
        String[] arregloStringCorreos = new String[correoPacientes.size()];
        for (int i = 0; i < correoPacientes.size(); i++) {
        	arregloStringCorreos[i] = correoPacientes.get(i);
        }
        JList<String[]> dataList = new JList(arregloStringCorreos); 
        AutoCompleteDecorator.decorate(dataList, FrameRegistraCita.correoAlumno, ObjectToStringConverter.DEFAULT_IMPLEMENTATION);
	}
	public void cargarHorariosDisponibles(String dataCita, String nombreNutrologa) {
		ControllerBaseDatos controllerBaseDatos = new ControllerBaseDatos();
		String correoNutrologa = controllerBaseDatos.buscaCorreoNutrologa(nombreNutrologa);
		ControllerGoogleCalendar controllerGoogleCalendar = new ControllerGoogleCalendar();
        try {
			controllerGoogleCalendar.cargaHorariosOcupados(dataCita,correoNutrologa, nombreNutrologa);
		} catch (IOException | GeneralSecurityException | ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		 
		// TODO Auto-generated method stub
		if (e.getSource() == FrameRegistraCita.comboBoxMedicos) {
			 DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			 try {
				 String dataCita = formatter.format((Date) FrameRegistraCita.date_chooser.getDate());
				 this.cargarHorariosDisponibles(dataCita, FrameRegistraCita.comboBoxMedicos.getSelectedItem().toString());
				   
			 }catch(NullPointerException e1) {
				 System.out.println(e1);
			 }
			
		}else if (e.getSource() == FrameRegistraCita.btnRegresa) {
			Component component = (Component) e.getSource();
	        JFrame frameRegistraCita = (JFrame) SwingUtilities.getRoot(component);
	        frameRegistraCita.setVisible(false);
	        FramePantallaInicial framePantallaInicial = new FramePantallaInicial();
	        framePantallaInicial.setVisible(true);
		}else if(e.getSource() == FrameRegistraCita.btnRegistrarCita) {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dataCita = formatter.format((Date) FrameRegistraCita.date_chooser.getDate());
			String horarioCita = FrameRegistraCita.comboBoxHorarios.getItemAt(FrameRegistraCita.comboBoxHorarios.getSelectedIndex());
			String dataRegistroCita = formatter.format(new Date());
			String descripcion = FrameRegistraCita.descripcionCita.getText();
			String nombreNutrologa =  FrameRegistraCita.comboBoxMedicos.getItemAt(FrameRegistraCita.comboBoxMedicos.getSelectedIndex());
			ModelCita nuevaCita = new ModelCita(dataCita, horarioCita, dataRegistroCita, descripcion);
			
			ControllerBaseDatos controllerBaseDatos = new ControllerBaseDatos();
			controllerBaseDatos.registraCita(nuevaCita, FrameRegistraCita.correoAlumno.getText(),nombreNutrologa);
			ControllerGoogleCalendar controllerGoogleCalendar = new ControllerGoogleCalendar();
			try {
				String correoNutrologa = controllerBaseDatos.buscaCorreoNutrologa(nombreNutrologa);
				controllerGoogleCalendar.registraEvento(nuevaCita, nombreNutrologa, correoNutrologa);
			} catch (GeneralSecurityException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	@Override
	public void propertyChange(PropertyChangeEvent e) {
		// TODO Auto-generated method stub
        if ("date".equals(e.getPropertyName())) {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dataCita = formatter.format((Date) e.getNewValue());
            this.cargarHorariosDisponibles(dataCita, FrameRegistraCita.comboBoxMedicos.getSelectedItem().toString());
   
        }
        System.out.println(e.getPropertyName());
	}
}
