package Logic;

import java.util.ArrayList;
import java.util.List;

import Exceptions.UdefineretProfilException;

/**
 * @author Tsvetelin Tsonev - <tsvetelin.tsonev@yahoo.co.uk>
 *
 */
public class PTEControllerImpl implements PTEController {
	
	private Vinkel vinkel;
	private Vaegt vaegt;
	private DimensionerendeKraft dimensionerendeKraft;
	private Normalkraft normalKraft;
	private Tyngdekraft tyngdeKraft;
	private Forskydningskraft forskydningsKraft;
	private List<Observer> observers;
	private LogicFactory logicFactory;
	
	public PTEControllerImpl() {
		logicFactory = new LogicFactoryImpl();
		vinkel = logicFactory.createVinkel();
		vinkel.setProfil(Profil.UDEFINERET);
		vaegt = logicFactory.createVaegt();
		tyngdeKraft = logicFactory.craeteTyngdeKraft();
		dimensionerendeKraft = logicFactory.craeteDimensionerendeKraft(vaegt, tyngdeKraft);
		normalKraft = logicFactory.createNormalKraft(dimensionerendeKraft, vinkel);
		forskydningsKraft = logicFactory.createForskydningskraft(vinkel, dimensionerendeKraft);
		observers = new ArrayList<Logic.Observer>();
	}
	
	@Override
	public void vaelgProfil(Profil profil) {
		this.vinkel.setProfil(profil);
	}

	@Override
	public void tilmeldObserver(Observer observer) {
		this.observers.add(observer);
	}

	@Override
	public void notifyObservers(List<Tilstand> tilstande) {
		for(Logic.Observer o : observers) {
			o.update(tilstande);
		}
	}

	@Override
	public double getForskydningkraft() {
		double fN = 0;
		try {
			fN = this.forskydningsKraft.getForskydningskraft();
		} catch (UdefineretProfilException e) {
			//TODO
		}
		return fN;
	}

	@Override
	public void setForskydningskraft(double forskydningskraft) {
		this.forskydningsKraft.setForskydningskraft(forskydningskraft);
	}

	@Override
	public double getTyngdekraft() {
		return this.tyngdeKraft.getTyngdekraft();
	}

	@Override
	public void setTyngdekraft(double tyngdekraft) {
		this.setTyngdekraft(tyngdekraft);
		
	}

	@Override
	public void setVinkel(double vinkel) {
		this.vinkel.setVinkel(vinkel);
		List<Tilstand> tilstande = new ArrayList<Tilstand>();
		tilstande.add(Tilstand.VINKEL);
		tilstande.add(Tilstand.NORMALKRAFT);
		tilstande.add(Tilstand.FORSKYDNINGSKRAFT);
		this.notifyObservers(tilstande);
	}

	@Override
	public double getVinkel() {
		return this.vinkel.getVinkel();
	}

	@Override
	public void setProfil(Profil profil) {
		this.vinkel.setProfil(profil);
	}

	@Override
	public Profil getProfil() {
		return this.vinkel.getProfil();
	}

	@Override
	public double getVaegt() {
		return this.vaegt.getVaegt();
	}

	@Override
	public void setVaegt(double vaegt) {
		this.vaegt.setVaegt(vaegt);
	}

	@Override
	public double getNormalkraft() {
		return this.normalKraft.getNormalkraft();
	}

	@Override
	public void setNormalkraft(double normalkraft) {
		this.normalKraft.setNormalkraft(normalkraft);
	}

	@Override
	public double getDimensionerendeKraft() {
		return this.dimensionerendeKraft.getDimensionerendeKraft();
	}

	@Override
	public void setDimensioneredndeKraft(double dimensioneredndeKraft) {
		this.dimensionerendeKraft.setDimensionerendeKraft(dimensioneredndeKraft);
		List<Tilstand> tilstande = new ArrayList<Tilstand>();
		tilstande.add(Tilstand.VAEGT);
		tilstande.add(Tilstand.TYNGDEKRAFT);
		tilstande.add(Tilstand.DIMENSIONERENDE_KRAFT);
		tilstande.add(Tilstand.NORMALKRAFT);
		tilstande.add(Tilstand.FORSKYDNINGSKRAFT);
		this.notifyObservers(tilstande);
	}

	@Override
	public void nulstil() {
		this.dimensionerendeKraft.nulstil();
		this.forskydningsKraft.nulstil();
		this.normalKraft.nulstil();
		this.tyngdeKraft.nulstil();
		this.vaegt.nulstil();
		this.vinkel.nulstil();
	}

}
