import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.Calendar.CalendarList;
import com.google.api.services.calendar.Calendar.Freebusy;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.AclRule;
import com.google.api.services.calendar.model.AclRule.Scope;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import com.google.api.services.calendar.model.FreeBusyGroup;
import com.google.api.services.calendar.model.FreeBusyRequest;
import com.google.api.services.calendar.model.FreeBusyRequestItem;
import com.google.api.services.calendar.model.FreeBusyResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class ControllerGoogleCalendar {
    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
    private static String CREDENTIALS_FILE_PATH = "credentials.json";
    private static String user;
    private static int puertoSocket;

    /**
     * Creates an authorized Credential object.
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = ControllerGoogleCalendar.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8889).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize(user);
    }

    public void presentaFechaEventos() throws IOException, GeneralSecurityException, ParseException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
       
        
        // List the next 10 events from the primary calendar.
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("primary")
                .setMaxResults(10)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            System.out.println("Upcoming events");
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
                DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
                String dtStr = (start.toString());
                System.out.printf("%s (%s)\n", event.getSummary(), start);
            }
        }
    }
    public void cargaHorariosOcupados(String fechaCita, String correoNutrologa, String nombreNutrologa) throws IOException, GeneralSecurityException, ParseException {
        FrameRegistraCita.comboBoxHorarios.removeAllItems();
    	// Build a new authorized API client service.
    	puertoSocket = 8888;
        if (nombreNutrologa == "Ana") {
        	CREDENTIALS_FILE_PATH = "credentialsAna.json";
        }else {
        	CREDENTIALS_FILE_PATH = "credentials.json";
        }
        this.user = nombreNutrologa;
    	final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    	Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build(); 
    	String dIn = fechaCita + " 09:00:00";
        String dIne = fechaCita + " 16:00:00";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        
        Date d = null;
		d = df.parse(dIn);
        DateTime startTime = new DateTime(d, TimeZone.getTimeZone("America/Mexico_City"));
        Date de = null;
		de = df.parse(dIne);
        DateTime endTime = new DateTime(de,TimeZone.getTimeZone("America/Mexico_City"));
        FreeBusyRequest req = new FreeBusyRequest();
        req.setTimeMin(startTime);
        req.setTimeMax(endTime);
        req.setTimeZone("America/Mexico_City");
        FreeBusyRequestItem c = new FreeBusyRequestItem();
        c.setId(correoNutrologa);
        List<FreeBusyRequestItem> listaItensReq = new ArrayList<FreeBusyRequestItem>();
        listaItensReq.add(c);
        req.setItems(listaItensReq);
        Freebusy.Query fbq = service.freebusy().query(req);
        FreeBusyResponse fbresponse = fbq.execute();
        String intervalosOcupados = fbresponse.getCalendars().toString();
        System.out.println(intervalosOcupados);
        intervalosOcupados = intervalosOcupados.substring(intervalosOcupados.indexOf("[") + 1);
        intervalosOcupados = intervalosOcupados.substring(0, intervalosOcupados.indexOf("]"));
        
        ArrayList<String> horariosDisponibles = new ArrayList<String>();
        horariosDisponibles.add("9:00");
        horariosDisponibles.add("10:00");
        horariosDisponibles.add("11:00");
        horariosDisponibles.add("12:00");
        horariosDisponibles.add("13:00");
        horariosDisponibles.add("14:00");
        horariosDisponibles.add("15:00"); 
      
	   
	    if (!intervalosOcupados.isEmpty()) {
	    	String[] horarios = intervalosOcupados.split(",");
	        for (int i = 0; i < horarios.length; i++) {
	        	horarios[i] = horarios[i].replace("{", "");
	        	horarios[i] = horarios[i].replace("}", "");
	        	horarios[i] = horarios[i].replace("\"start\":\"", "");
	        	horarios[i] = horarios[i].replace("\"end\":\"", "");
	        	horarios[i] = horarios[i].replace("\"", "");
	        	horarios[i] = horarios[i].substring(horarios[i].indexOf("T") + 1);
	        	horarios[i] = horarios[i].substring(0, horarios[i].indexOf("."));
	        	System.out.println(horarios[i]);
	        }
	        for (int i = 0; i < horarios.length; i+=2) {
	        	String start = horarios[i+1];
	        	String end = horarios[i];
	        	String horaInicio = start.substring(0, start.indexOf(":"));
	        	String horaFinal = end.substring(0, end.indexOf(":"));
	        	String minutosHoraFinal = end.substring(end.indexOf(":"));
	        			
	        	minutosHoraFinal = minutosHoraFinal.replace(":00", "");
	        	minutosHoraFinal = minutosHoraFinal.replace(":", "");
	        	if (horaInicio == horaFinal) {
	        		horaInicio += ":00";
	        		horariosDisponibles.remove(horaInicio);
	        	}else {
	        		int intHoraInicio = Integer.parseInt(horaInicio);
	        		int intHoraFinal = Integer.parseInt(horaFinal);
	        		if (!minutosHoraFinal.isEmpty()){
	        			intHoraFinal = intHoraFinal + 1;
	        		}
	        		System.out.println("hora final " + intHoraFinal);
	        		for(int j = intHoraInicio; j < intHoraFinal; j++ ) {
	        			String horaARemover = String.valueOf(j);
	        			
	        			horaARemover += ":00";
	        			
	        			horariosDisponibles.remove(horaARemover);
	        		}		
	        	}
	        }
        }
	    if (FrameRegistraCita.comboBoxHorarios.getItemCount() == 0){
	        for (int i = 0; i < horariosDisponibles.size(); i++) {
	        	FrameRegistraCita.comboBoxHorarios.addItem(horariosDisponibles.get(i));
	        }
	    }
    }

	public void registraEvento(ModelCita nuevaCita, String nombreNutrologa, String correoNutrologa) throws GeneralSecurityException, IOException {
		// TODO Auto-generated method stub
		if (nombreNutrologa == "Ana") {
        	CREDENTIALS_FILE_PATH = "credentialsAna.json";
        	puertoSocket = 8889;
        }else {
        	CREDENTIALS_FILE_PATH = "credentials.json";
        	puertoSocket = 8888;
        }
        this.user = nombreNutrologa;
    	final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    	try {
			Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
			        .setApplicationName(APPLICATION_NAME)
			        .build();
			
			
	    	if (nuevaCita.horarioCita == "9:00") {
	    		nuevaCita.horarioCita = "09:00";
	    	}
	    	
	    	Event event = new Event()
	    		    .setSummary("Cita")
	    		    .setLocation("Universidad Iberoamericana Ciudad de Mexico")
	    		    .setDescription(nuevaCita.descripcion);
	    	
	    	DateTime startDateTime = new DateTime(nuevaCita.fechaCita + "T" + nuevaCita.horarioCita + ":00-06:00");
	    	EventDateTime start = new EventDateTime()
	    			.setDateTime(startDateTime)
	    		    .setTimeZone("America/Mexico_City");
	    	event.setStart(start);
	    	
	    	String strHoraFinalCita = calculaHoraFinalCita(nuevaCita);
	    	
	    	DateTime endDateTime = new DateTime(nuevaCita.fechaCita + "T" + strHoraFinalCita + ":00" + "-06:00");
	    	EventDateTime end = new EventDateTime()
	    		    .setDateTime(endDateTime)
	    		    .setTimeZone("America/Mexico_City");
	    	event.setEnd(end);
	
	       	String calendarId = correoNutrologa;
	    	event = service.events().insert(calendarId, event).execute();
	    	ControllerEmail.sendMail(FrameRegistraCita.correoAlumno.getText(), nombreNutrologa, nuevaCita.horarioCita, nuevaCita.fechaCita, nuevaCita.descripcion);
	    	if (nuevaCita.horarioCita != "09:00") {
	    		FrameRegistraCita.comboBoxHorarios.removeItem(nuevaCita.horarioCita);
	    	}else {
	    		FrameRegistraCita.comboBoxHorarios.removeItem(nuevaCita.horarioCita.substring(1, nuevaCita.horarioCita.length()));
	    	}
	    }catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
	}

	private String calculaHoraFinalCita(ModelCita nuevaCita) {
		// TODO Auto-generated method stub
		String strHoraInicioCita = nuevaCita.horarioCita.substring(0,nuevaCita.horarioCita.indexOf(":"));
    	int intInicioHoraCita = Integer.parseInt(strHoraInicioCita);
    	intInicioHoraCita = intInicioHoraCita + 1;
    	String strHoraFinalCita = String.valueOf(intInicioHoraCita);
    	strHoraFinalCita += ":00";
    	return strHoraFinalCita;
	 
	}
}