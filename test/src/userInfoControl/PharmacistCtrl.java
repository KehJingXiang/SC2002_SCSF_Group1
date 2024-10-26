package userInfoControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import userInfo.Appointment;
import userInfo.MedicalRecord;
import userInfo.AppointmentOutcomeRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PharmacistCtrl implements AppointmentOutcomeRecordCtrl, GetOperationInput{
	private List<Appointment> appointments = new ArrayList<>();
	private List<AppointmentOutcomeRecord> appointmentsOutcomeRecord = new ArrayList<>();


	public PharmacistCtrl() {
		try (BufferedReader br = new BufferedReader(new FileReader("./Appointment_List.csv"))) 
		{		    
			String line;
    		while ((line = br.readLine()) != null) 
    		{
		        // Split the line into columns using the delimiter
		        String[] data = line.split(",");
		        {
		        	//Appointment appointment = new Appointment(data[0], data[1], data[2], data[3], data[4]);
		        	//AppointmentOutcomeRecord appointmentOutcomeRecord = new AppointmentOutcomeRecord();
		        	//this.appointments.add(appointment);
		        	//this.rows.add(counter);
		        }
		        //this.counter++;
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	public void viewAppointmentOutcome() {		
		System.out.println();
		System.out.println("=========================================================");
		System.out.println("This is the list of appointments with unfulfilled medication prescriptions orders.");
		
		String filePath = "./Appointment_List.csv"; // Update this path
        String prescriptionStatusColumn = "Prescription Status"; // Column name for Prescription Status
        int prescriptionStatusIndex = -1; // To be set based on header search

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Read header to find Prescription Status index
            String headerLine = br.readLine();
            if (headerLine == null) {
                System.out.println("Empty CSV file.");
                return;
            }
            String[] headers = headerLine.split(",");
            for (int i = 0; i < headers.length; i++) {
                if (headers[i].trim().equalsIgnoreCase(prescriptionStatusColumn)) {
                    prescriptionStatusIndex = i;
                    break;
                }
            }
            
            if (prescriptionStatusIndex == -1) {
                System.out.println("Prescription Status column not found.");
                return;
            }

            // Process each row and filter out those with an empty Prescription Status
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                if (prescriptionStatusIndex < columns.length && !columns[prescriptionStatusIndex].trim().isEmpty()) {
                    System.out.println("Appointment: " + line);
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		System.out.println("=========================================================");
		System.out.println();
	}
	
	public void updateAppointmentOutcomeRecord() {
	}
	public void viewMedicationInventory() {
	}
	public void submitReplenishRequest() {
	}
	
	public void getOperationInput(int input) {
		Scanner sc = new Scanner(System.in);
		switch(input) {
		case 1:
			viewAppointmentOutcome();
			System.out.print("Press <Enter> to continue:");
			// Dummy scanner to let the system stop for user to check information
			sc.nextLine();
			break;
		case 2:
			updateAppointmentOutcomeRecord();
			break;
		case 3:
			viewMedicationInventory();
			break;
		case 4:
			submitReplenishRequest();
			System.out.print("Press <Enter> to continue:");
			sc.nextLine();
			break;
		}
	}
}