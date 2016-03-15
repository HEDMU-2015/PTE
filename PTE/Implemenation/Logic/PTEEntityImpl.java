package Logic;

import java.util.ArrayList;
import java.util.List;

abstract class PTEEntityImpl implements PTEEntity {

	private List<PTEEntity> afhaengendeEntiteter = new ArrayList<PTEEntity>();

	@Override
	public void tilfoejAfhaengigEntitet(PTEEntity entity) {
		afhaengendeEntiteter.add(entity);
	}

	@Override
	public List<Tilstand> getAfhaengigheder() {
		List<Tilstand> afhaengigheder = new ArrayList<Tilstand>();
		afhaengigheder.add(getEgenAfhaengighed());
		for (PTEEntity afhaengighed : afhaengendeEntiteter) {
			afhaengigheder.addAll(afhaengighed.getAfhaengigheder());
		}
		return afhaengigheder;
	}

	protected abstract Tilstand getEgenAfhaengighed();
	
}
