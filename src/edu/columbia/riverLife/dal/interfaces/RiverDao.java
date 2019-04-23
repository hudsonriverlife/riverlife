package edu.columbia.riverLife.dal.interfaces;

import java.util.List;

import edu.columbia.riverLife.domain.River;
import edu.columbia.riverLife.domain.RiverSite;

public interface RiverDao {

	List<River> getRivers();

	List<RiverSite> getRiverSites(int riverId);

}
