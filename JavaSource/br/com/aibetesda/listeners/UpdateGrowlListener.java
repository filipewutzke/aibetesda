package br.com.aibetesda.listeners;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class UpdateGrowlListener implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void beforePhase(PhaseEvent event) {
		event.getFacesContext().getPartialViewContext().getRenderIds().add("growl");
	}

	@Override
	public void afterPhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

}
