package edu.columbia.riverLife.bll.implementation;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import edu.columbia.riverLife.bll.interfaces.SiteSamplingBo;
import edu.columbia.riverLife.dal.interfaces.SiteSamplingDao;
import edu.columbia.riverLife.domain.Fish;
import edu.columbia.riverLife.domain.FishCount;
import edu.columbia.riverLife.domain.Macroinvertebrate;
import edu.columbia.riverLife.domain.MacroinvertebrateCount;
import edu.columbia.riverLife.domain.RiverSite;
import edu.columbia.riverLife.domain.SamplingData;
import edu.columbia.riverLife.domain.SiteSampling;

@Named("siteSamplingBo")
@Transactional
public class SiteSamplingBoImpl implements SiteSamplingBo, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7995081533875268975L;

	@Inject
	private SiteSamplingDao siteSamplingDao;
	
	@Override
	public List<SiteSampling> getSiteSamplings() {
		return this.siteSamplingDao.getSiteSamplings();
	}

	@Override
	public List<Fish> getFishes() {
		return this.siteSamplingDao.getFishes();
	}

	@Override
	public List<RiverSite> getRiverSites() {
		return this.siteSamplingDao.getRiverSites();
	}

	@Override
	public List<Macroinvertebrate> getMacroinvertebrates() {
		return this.siteSamplingDao.getMacroinvertebrates();
	}

	@Override
	public List<SamplingData> getFishCountsOverTime(int riverId, int riverSiteId, int macroId) {
		return this.siteSamplingDao.getFishCountsOverTime(riverId, riverSiteId, macroId);
	}

	@Override
	public List<SamplingData> getFishCountsAlongRiver(int riverId, int year, int fishId) {
		return this.siteSamplingDao.getFishCountsAlongRiver(riverId, year, fishId);

	}
	
	@Override
	public List<SamplingData> getMacroCountsOverTime(int riverId, int riverSiteId, int macroId) {
		return this.siteSamplingDao.getMacroCountsOverTime(riverId, riverSiteId, macroId);
	}
	
	@Override
	public List<SamplingData> getMacroCountsAlongRiver(int riverId, int year, int fishId) {
		return this.siteSamplingDao.getMacroCountsAlongRiver(riverId, year, fishId);

	}
	
	@Override
	public List<SamplingData> getStudentSalinityOverTime(int riverSiteId) {
		return this.siteSamplingDao.getStudentSalinityOverTime(riverSiteId);

	}
	
	@Override
	public List<SamplingData> getStudentSalinityAlongRiver(int riverId, int year) {
		return this.siteSamplingDao.getStudentSalinityAlongRiver(riverId, year);

	}

	@Override
	public List<SamplingData> getStandardizedSalinityOverTime(int riverSiteId) {
		return this.siteSamplingDao.getStandardizedSalinityOverTime(riverSiteId);

	}

	@Override
	public List<SamplingData> getStandardizedSalinityAlongRiver(int riverId, int year) {
		return this.siteSamplingDao.getStandardizedSalinityAlongRiver(riverId, year);

	}
	
	@Override
	public List<SamplingData> getTurbidityOverTime(int riverSiteId) {
		return this.siteSamplingDao.getTurbidityOverTime(riverSiteId);

	}

	@Override
	public List<SamplingData> getTurbidityAlongRiver(int riverId, int year) {
		return this.siteSamplingDao.getTurbidityAlongRiver(riverId, year);

	}

	@Override
	public List<SamplingData> getWaterTempOverTime(int riverSiteId) {
		return this.siteSamplingDao.getWaterTempOverTime(riverSiteId);

	}

	@Override
	public List<SamplingData> getWaterTempAlongRiver(int riverId, int year) {
		return this.siteSamplingDao.getWaterTempAlongRiver(riverId, year);

	}

	@Override
	public List<SamplingData> getPhOverTime(int riverSiteId) {
		return this.siteSamplingDao.getPhOverTime(riverSiteId);

	}

	@Override
	public List<SamplingData> getPhAlongRiver(int riverId, int year) {
		return this.siteSamplingDao.getPhAlongRiver(riverId, year);

	}

	@Override
	public List<SamplingData> getDissolvedOxygenOverTime(int riverSiteId) {
		return this.siteSamplingDao.getDissolvedOxygenOverTime(riverSiteId);

	}

	@Override
	public List<SamplingData> getDissolvedOxygenAlongRiver(int riverId, int year) {
		return this.siteSamplingDao.getDissolvedOxygenAlongRiver(riverId, year);

	}

	@Override
	public List<SamplingData> getOxygenSaturationOverTime(int riverSiteId) {
		return this.siteSamplingDao.getOxygenSaturationOverTime(riverSiteId);

	}

	@Override
	public List<SamplingData> getOxygenSaturationAlongRiver(int riverId, int year) {
		return this.siteSamplingDao.getOxygenSaturationAlongRiver(riverId, year);

	}

	@Override
	public List<SamplingData> getTideDirectionOverTime(Integer selectedRiverSite) {
		return this.siteSamplingDao.getTideDirectionOverTime(selectedRiverSite);

	}

	@Override
	public List<SamplingData> getTideDirectionAlongRiver(int riverId, int year) {
		return this.siteSamplingDao.getTideDirectionAlongRiver(riverId, year);

	}

	@Override
	public List<SamplingData> getCurrentDirectionOverTime(Integer selectedRiverSite) {
		return this.siteSamplingDao.getCurrentDirectionOverTime(selectedRiverSite);

	}

	@Override
	public List<SamplingData> getCurrentDirectionAlongRiver(int riverId, int year) {
		return this.siteSamplingDao.getCurrentDirectionAlongRiver(riverId, year);

	}

	@Override
	public List<SamplingData> getAirTemperatureOverTime(Integer selectedRiverSite) {
		return this.siteSamplingDao.getAirTemperatureOverTime(selectedRiverSite);
	}

	@Override
	public List<SamplingData> getAirTemperatureAlongRiver(int riverId, int year) {
		return this.siteSamplingDao.getAirTemperatureAlongRiver(riverId, year);
	}

	@Override
	public List<SamplingData> getWindDirectionOverTime(Integer selectedRiverSite) {
		return this.siteSamplingDao.getWindDirectionOverTime(selectedRiverSite);
	}

	@Override
	public List<SamplingData> getWindDirectionAlongRiver(int riverId, int year) {
		return this.siteSamplingDao.getWindDirectionAlongRiver(riverId, year);
	}

	@Override
	public List<SamplingData> getWindSpeedOverTime(Integer selectedRiverSite) {
		return this.siteSamplingDao.getWindSpeedOverTime(selectedRiverSite);
	}

	@Override
	public List<SamplingData> getWindSpeedAlongRiver(int riverId, int year) {
		return this.siteSamplingDao.getWindSpeedAlongRiver(riverId, year);

	}

	@Override
	public List<SamplingData> getWindBeaufortRatingOverTime(Integer selectedRiverSite) {
		return this.siteSamplingDao.getWindBeaufortRatingOverTime(selectedRiverSite);

	}

	@Override
	public List<SamplingData> getWindBeaufortRatingAlongRiver(int riverId, int year) {
		return this.siteSamplingDao.getWindBeaufortRatingAlongRiver(riverId, year);

	}

	@Override
	public List<SamplingData> getSaltFrontOverTime(int riverId) {
		return this.siteSamplingDao.getSaltFrontOverTime(riverId);

	}

}
