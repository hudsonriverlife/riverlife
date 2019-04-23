package edu.columbia.riverLife.bll.implementation;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import edu.columbia.riverLife.bll.interfaces.RiverBo;
import edu.columbia.riverLife.dal.interfaces.RiverDao;
import edu.columbia.riverLife.dal.interfaces.SiteSamplingDao;
import edu.columbia.riverLife.domain.River;
import edu.columbia.riverLife.domain.RiverSite;

@Named("riverBo")
@Transactional
public class RiverBoImpl  implements RiverBo, Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 353584117072866591L;
	@Inject
	private RiverDao riverDao;
	
	@Override
	public List<River> getRivers() {
		return riverDao.getRivers();
	}

	@Override
	public List<RiverSite> getRiverSites(int riverId) {
		return riverDao.getRiverSites(riverId);
	}

}
