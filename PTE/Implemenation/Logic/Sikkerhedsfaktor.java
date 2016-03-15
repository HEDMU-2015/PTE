package Logic;

interface Sikkerhedsfaktor extends PTEEntity {

	void setSikkerhedsfaktor(double sikkerhedsfaktor);
	double getSikkerhedsfaktor();
	boolean erSikkerhedsfaktorForLavt();
	public void nulstil();

}
