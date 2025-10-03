package edu.columbia.riverLife.dal.interfaces;

import java.util.List;

import edu.columbia.riverLife.domain.Fish;
import edu.columbia.riverLife.domain.FishCount;
import edu.columbia.riverLife.domain.Macroinvertebrate;
import edu.columbia.riverLife.domain.MacroinvertebrateCount;
import edu.columbia.riverLife.domain.RiverSite;
import edu.columbia.riverLife.domain.SamplingData;
import edu.columbia.riverLife.domain.SiteSampling;

public interface SiteSamplingDao {
	public List<SiteSampling> getSiteSamplings();

	public List<Fish> getFishes();	
	public List<Macroinvertebrate> getMacroinvertebrates();
	

	public List<RiverSite> getRiverSites();
	
	public List<SamplingData> getFishCountsOverTime(int riverId, int riverSiteId, int fishId);
	public List<SamplingData> getFishCountsAlongRiver(int riverId, int year, int fishId);

	public List<SamplingData> getMacroCountsOverTime(int riverId, int riverSiteId, int macroId);
	public List<SamplingData> getMacroCountsAlongRiver(int riverId, int year, int macroId);

	public List<SamplingData> getStudentSalinityOverTime(int riverSiteId);
	public List<SamplingData> getStudentSalinityAlongRiver(int riverId, int year);

	public List<SamplingData> getStandardizedSalinityOverTime(int riverSiteId);
	public List<SamplingData> getStandardizedSalinityAlongRiver(int riverId, int year);

	public List<SamplingData> getTurbidityOverTime(int riverSiteId);
	public List<SamplingData> getTurbidityAlongRiver(int riverId, int year);

	public List<SamplingData> getWaterTempOverTime(int riverSiteId);
	public List<SamplingData> getWaterTempAlongRiver(int riverId, int year);

	public List<SamplingData> getPhOverTime(int riverSiteId);
	public List<SamplingData> getPhAlongRiver(int riverId, int year);

	public List<SamplingData> getDissolvedOxygenOverTime(int riverSiteId);
	public List<SamplingData> getDissolvedOxygenAlongRiver(int riverId, int year);

	public List<SamplingData> getOxygenSaturationOverTime(int riverSiteId);
	public List<SamplingData> getOxygenSaturationAlongRiver(int riverId, int year);

	public List<SamplingData> getTideDirectionOverTime(Integer selectedRiverSite);
	public List<SamplingData> getTideDirectionAlongRiver(int riverId, int year);

	public List<SamplingData> getCurrentDirectionOverTime(Integer selectedRiverSite);
	public List<SamplingData> getCurrentDirectionAlongRiver(int riverId, int year);

	public List<SamplingData> getAirTemperatureOverTime(Integer selectedRiverSite);
	public List<SamplingData> getAirTemperatureAlongRiver(int riverId, int year);

	public List<SamplingData> getWindDirectionOverTime(Integer selectedRiverSite);
	public List<SamplingData> getWindDirectionAlongRiver(int riverId, int year);

	public List<SamplingData> getWindSpeedOverTime(Integer selectedRiverSite);
	public List<SamplingData> getWindSpeedAlongRiver(int riverId, int year);

	public List<SamplingData> getWindBeaufortRatingOverTime(Integer selectedRiverSite);
	public List<SamplingData> getWindBeaufortRatingAlongRiver(int riverId, int year);

	public List<SamplingData> getSaltFrontOverTime(int riverId);

}
