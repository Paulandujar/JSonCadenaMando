package org.cadenamando;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GsonDatabaseClient {

	public static void main(String[] args) {
		try{
			ActiveIngredients AI = new ActiveIngredients(null);
			Medicines M = new Medicines(AI);
			MedicinePresentations MP = new MedicinePresentations(M);
			Physiotherapies P = new Physiotherapies(MP);
			Inhalers IN = new Inhalers(P);
			Posologies Po = new Posologies(IN);
			RescueMedicinePresentations RMP = new RescueMedicinePresentations(Po);
			UserManualSteps UMS = new UserManualSteps(RMP);
			UserManualsPhysioSteps UMPS = new UserManualsPhysioSteps(UMS);
			DatabaseJSonReader dbjp = new DatabaseJSonReader(UMPS);

			RescueMedicinePresentations RMP2 = new RescueMedicinePresentations(null);
			Medicines M2 = new Medicines(RMP2);
			MedicinePresentations MP2 = new MedicinePresentations(M2);
			DatabaseJSonReader dbjp2 = new DatabaseJSonReader(MP2);
			try {
				System.out.println(dbjp.parse("src/main/resources/datos.json"));
				System.out.println(dbjp2.parse("src/main/resources/datosshort.json"));
			} finally {
			}
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}

	}

}
