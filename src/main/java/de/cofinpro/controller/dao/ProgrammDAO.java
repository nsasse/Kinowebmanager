package de.cofinpro.controller.dao;

import java.util.ArrayList;
import de.cofinpro.modul.Programm;

public interface ProgrammDAO {

	ArrayList<Programm> getProgramm();
	Programm getProgrammEintrag(int id);
	void updateProgrammEintrag(Programm programm);
	void deleteProgrammEintrag(Programm programm);
	void setProgramm(ArrayList<Programm> programmListe);
	
}
