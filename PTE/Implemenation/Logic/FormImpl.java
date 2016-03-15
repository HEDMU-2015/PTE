package Logic;

public class FormImpl extends PTEEntityImpl implements Form {

	private ProfilType profilType = ProfilType.UDEFINERET;

	@Override
	public void setProfilType(ProfilType profilType) {
		this.profilType = profilType;
	}

	@Override
	public ProfilType getProfilType() {
		return profilType;
	}

	@Override
	public void nulstil() {
		profilType = ProfilType.UDEFINERET;
	}

	@Override
	protected Tilstand getEgenAfhaengighed() {
		return Tilstand.PROFIL_TYPE;
	}

}
