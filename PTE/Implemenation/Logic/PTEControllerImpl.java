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
		notifyObservers(vinkel.getAfhaengigheder());
	}

	@Override
	public void tilmeldObserver(Observer observer) {
		this.observers.add(observer);
	}

	@Override
	public void notifyObservers(List<Tilstand> tilstande) {
		for (Logic.Observer o : observers) {
			o.update(tilstande);
		}
	}

	@Override
	public double getForskydningkraft() {
		double fN = 0;
		try {
			fN = this.forskydningsKraft.getForskydningskraft();
		} catch (UdefineretProfilException e) {
			// TODO
		}
		return fN;
	}

	@Override
	public void setForskydningskraft(double forskydningskraft) {
		this.forskydningsKraft.setForskydningskraft(forskydningskraft);
		notifyObservers(forskydningsKraft.getAfhaengigheder());
	}

	@Override
	public double getTyngdekraft() {
		return this.tyngdeKraft.getTyngdekraft();
	}

	@Override
	public void setTyngdekraft(double tyngdekraft) {
		this.setTyngdekraft(tyngdekraft);
		notifyObservers(tyngdeKraft.getAfhaengigheder());

	}

	@Override
	public void setVinkel(double vinkel) {
		this.vinkel.setVinkel(vinkel);
		notifyObservers(this.vinkel.getAfhaengigheder());
	}

	@Override
	public double getVinkel() {
		return this.vinkel.getVinkel();
	}

	@Override
	public void setProfil(Profil profil) {
		this.vinkel.setProfil(profil);
		notifyObservers(vinkel.getAfhaengigheder());
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
		notifyObservers(this.vaegt.getAfhaengigheder());
	}

	@Override
	public double getNormalkraft() {
		double normalkraft = 0;
		try {
			normalkraft = this.normalKraft.getNormalkraft();
		} catch (UdefineretProfilException e) {
			// Det sker aldrig
		}
		return normalkraft;
	}

	@Override
	public void setNormalkraft(double normalkraft) {
		this.normalKraft.setNormalkraft(normalkraft);
		notifyObservers(normalKraft.getAfhaengigheder());
	}

	@Override
	public double getDimensionerendeKraft() {
		return this.dimensionerendeKraft.getDimensionerendeKraft();
	}

	@Override
	public void setDimensioneredndeKraft(double dimensionerendeKraft) {
		this.dimensionerendeKraft.setDimensionerendeKraft(dimensionerendeKraft);
		vaegt.setVaegt(this.dimensionerendeKraft.dimensionerendeKraftTilVaegt());
		notifyObservers(this.vaegt.getAfhaengigheder());
	}

	@Override
	public void nulstil() {
		List<Tilstand> tilstande = new ArrayList<Tilstand>();

		this.vaegt.nulstil();
		tilstande.addAll(vaegt.getAfhaengigheder());

		this.vinkel.nulstil();
		tilstande.addAll(vinkel.getAfhaengigheder());

		this.tyngdeKraft.nulstil();
		tilstande.addAll(tyngdeKraft.getAfhaengigheder());

		this.dimensionerendeKraft.nulstil();
		tilstande.addAll(dimensionerendeKraft.getAfhaengigheder());

		this.normalKraft.nulstil();
		tilstande.addAll(normalKraft.getAfhaengigheder());

		this.forskydningsKraft.nulstil();
		tilstande.addAll(forskydningsKraft.getAfhaengigheder());

		notifyObservers(tilstande);
	}

}
