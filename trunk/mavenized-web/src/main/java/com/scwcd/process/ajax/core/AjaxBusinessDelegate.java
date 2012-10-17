package com.scwcd.process.ajax.core;


import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.scwcd.component.view.core.Block;
import com.scwcd.component.view.core.Metadata;
import com.scwcd.component.view.core.View;
import com.scwcd.enterprise.sql.hbm.Lot;
import com.scwcd.enterprise.sql.hbm.Plan;
import com.scwcd.enterprise.sql.hbm.Project;
import com.scwcd.framework.business.core.AbstractBusinessDelegate;
import com.scwcd.framework.business.core.AbstractBusinessService;
import com.scwcd.framework.command.core.ApplicationSession;


public class AjaxBusinessDelegate extends AbstractBusinessDelegate {

	private final Set<Integer> planIds = new HashSet<>();

	protected AjaxBusinessDelegate(final ApplicationSession session, final AbstractBusinessService<?> service) {
		super(session, service);
	}

	protected AjaxBusinessDelegate(final ApplicationSession session) {
		this(session, null);
	}

	void setParameters(String[] parameters) {
		if (parameters == null) {
            parameters = new String[0];
        }
        
		for (final String planId : parameters) {
            planIds.add(Integer.parseInt(planId));
        }

		final ApplicationSession appSession = getSession();
		final Set<Integer> _planIds = appSession.getPlanIds();
		_planIds.addAll(planIds);
		_planIds.retainAll(planIds);
	}

	Set<Plan> getPlans() {
		final ApplicationSession appSession = getSession();
		final Set<Integer> _planIds = appSession.getPlanIds();

		final Project project = (Project) appSession.getProject();
		final Set<Plan> plans = project.getPlans();
		final Plan[] planArr = plans.toArray(new Plan[plans.size()]);
		
		final Set<Plan> _plans = new HashSet<>();
		for (int i = 0; i < planArr.length; i++) {
			for (int j : _planIds) {
				if (j == planArr[i].getPlanId()) {
					_plans.add(planArr[i]);
					break;
				}
			}
		}
		return _plans;
	}

	byte[] getView(final int viewId) {
		final ServiceSelectView service = (ServiceSelectView) getService();
		service.setViewId(viewId);
		
		service.perform();
		
		final View view = service.getOutput();
		final Metadata metadata = view.getMetadata();
		final List<Block> blocks = metadata.getBlocks();
		
		final StringBuilder viewDetails = new StringBuilder();

		// start of JSON
		viewDetails.append("{ ");

		// start of view blocks
		viewDetails.append(" \"vid\": ").append(view.getViewId());

		// start of array blocks
		viewDetails.append(", \"bd\": [ ");
		Iterator<Block> it = blocks.iterator();
		while (it.hasNext()) {
			final Block block = it.next();

			viewDetails.append("{ \"bid\": ").append(block.getId());
			viewDetails.append(", \"n\": \"").append(block.getName());
			viewDetails.append("\", \"d\": \"").append(block.getDescription());
			viewDetails.append("\", \"v\": \"").append(block.getValue());
			viewDetails.append("\", \"c\": \"").append(block.getColor());
			viewDetails.append("\"} ");

			if (it.hasNext()) {
				viewDetails.append(", ");
			}
		}
		viewDetails.append("] ");

		// end of JSON
		viewDetails.append("}");

		return viewDetails.toString().getBytes();
	}

	Lot getLot(final int projectId, final int planId, final int lotId) {
		final ServiceSelectLot service = (ServiceSelectLot) getService();
		service.setProjectId(projectId);
		service.setPlanId(planId);
		service.setLotId(lotId);
		
		service.perform();
		
		return service.getOutput();
	}

	Map<String, String> updateLot(final String lotName, final String lotDescription, final Date dtModified, final String updatedBy) {
		final ApplicationSession session = getSession();
		final Lot lot = (Lot) session.removeCache();
		lot.setName(lotName);
		lot.setDescription(lotDescription);
		lot.setDtModified(dtModified);
		lot.setUpdatedBy(updatedBy);

		final ServiceUpdateLot service = (ServiceUpdateLot) getService();
		service.setLot(lot);

		service.perform();

		return service.getRcCodes();
	}
}