package com.scwcd.framework.deployment.xslt;

import com.scwcd.enterprise.sql.dao.DAOLot;
import com.scwcd.enterprise.sql.dao.LotParameter;
import com.scwcd.enterprise.sql.hbm.Lot;
import com.scwcd.enterprise.sql.hbm.LotKey;
import com.scwcd.framework.deployment.core.WebContextManager;
import com.scwcd.framework.sql.core.DAOFactory;
import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class XsltFunction {

    private int generatedId;
    private static final String LOT_DESCRIPTION = "Information not available";
    private Set<Lot> queried;

    public Lot generate(final int projectId, final int planId) {
        final DAOFactory factory = DAOFactory.getInstance();
        final DAOLot dao = (DAOLot) factory.getInstance(DAOLot.class);

        if (queried == null) {
            final List<Lot> _queried = dao.doList(new LotParameter(projectId, planId));
            queried = new HashSet<>(_queried);
        }

        final Lot lot = new Lot();
        lot.setLotKey(new LotKey(projectId, planId, ++generatedId));
        if (!queried.contains(lot)) {
            queried.add(lot);

            lot.setName(getLotId(lot));
            lot.setDescription(LOT_DESCRIPTION);

            final Date now = new Date();
            lot.setDtCreated(now);
            lot.setDtModified(now);
            lot.setUpdatedBy("SYSTEM");
            dao.doInsert(lot);

            final String projectPath = WebContextManager.getWebContext().getProjectPath() + File.separator
                    + "dat" + File.separator
                    + projectId + File.separator
                    + planId;
            final File file = new File(projectPath + File.separator + lot.getLotKey().getLotId());
            file.mkdirs();
        } else {
            for (Lot _lot : queried) {
                if (_lot.equals(lot)) {
                    return _lot;
                }
            }
        }

        return lot;
    }

    public static String processCss(final String css, final int projectId, final int planId) {
        return "<![CDATA[" + css.replaceAll("\\.st(\\d+)", ".css-" + projectId + "-" + planId + "-$1") + "]]>";
    }

    public static String getFloorId(final int projectId, final int planId) {
        return "floor-" + projectId + "-" + planId;
    }

    public static String getLotId(final int projectId, final int planId, final int lotId) {
        return "lot-" + projectId + "-" + planId + "-" + lotId;
    }

    public static String getLotId(final Lot lot) {
        return "lot-" + lot.getLotKey().getProjectId() + "-" + lot.getLotKey().getPlanId() + "-" + lot.getLotKey().getLotId();
    }

    public static String getCss(final String cssClass, final int projectId, final int planId) {
        return "css-" + projectId + "-" + planId + "-" + cssClass.substring(2);
    }
}