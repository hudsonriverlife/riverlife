package edu.columbia.riverLife.bll.interfaces;

import java.util.List;

import edu.columbia.riverLife.domain.River;
import edu.columbia.riverLife.domain.RiverSite;

public interface RiverBo {
	
	public List<River> getRivers();
	public List<RiverSite> getRiverSites(int riverId);
	

}
