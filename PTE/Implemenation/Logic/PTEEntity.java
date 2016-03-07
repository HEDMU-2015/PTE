package Logic;

import java.util.List;

interface PTEEntity {

	List<Tilstand> getAfhaengigheder();

	void tilfoejAfhaengigEntitet(PTEEntity entity);

}
