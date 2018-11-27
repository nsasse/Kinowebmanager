package de.cofinpro.controller.dao.impl;

import java.util.ArrayList;
import de.cofinpro.controller.dao.ProgrammDAO;
import de.cofinpro.modul.Programm;

public class ProgrammDaoStaticImpl implements ProgrammDAO{
	
	private static ArrayList<Programm> programm = new ArrayList<Programm>();
	
	public ProgrammDaoStaticImpl() {
	super();
	}

	
	public final ArrayList<Programm> getProgramm() {
		return programm;
	}
	
	
	public final void setProgramm(final ArrayList<Programm> programmListe) {
		for (int i = 0; i < programmListe.size(); i++) {
			programm.add(programmListe.get(i));
		}
	}

	
	public final Programm getProgrammEintrag(final int id) {
		Programm programmEintrag = new Programm();
		programmEintrag = programm.get(id);
		return programmEintrag;
	}

	
	public final void updateProgrammEintrag(final Programm programm) {
		// TODO Auto-generated method stub
		//System.out.println
	}

	
	public final void deleteProgrammEintrag(final Programm programm) {
		// TODO Auto-generated method stub
		//System.out.println
		
	}


	
	
}
