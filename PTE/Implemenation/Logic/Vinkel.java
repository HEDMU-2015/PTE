package Logic;

interface Vinkel extends PTEEntity {

	public void setVinkel(double vinkel);

	public double getVinkel();

	public void setProfil(Profil profil);

	public Profil getProfil();

	public void setLaengdeRetning(LaengdeRetning laengdeRetning);

	public LaengdeRetning getLaengdeRetning();

	public void nulstil();

}
