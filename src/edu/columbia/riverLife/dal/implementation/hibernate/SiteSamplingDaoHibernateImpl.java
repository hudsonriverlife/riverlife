package edu.columbia.riverLife.dal.implementation.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import edu.columbia.riverLife.dal.implementation.hibernate.bean.FishHbm;
import edu.columbia.riverLife.dal.implementation.hibernate.bean.MacroinvertebrateHbm;
import edu.columbia.riverLife.dal.implementation.hibernate.bean.RiverSiteHbm;
import edu.columbia.riverLife.dal.implementation.hibernate.bean.SiteSamplingHbm;
import edu.columbia.riverLife.dal.interfaces.SiteSamplingDao;
import edu.columbia.riverLife.domain.Fish;
import edu.columbia.riverLife.domain.FishCount;
import edu.columbia.riverLife.domain.Macroinvertebrate;
import edu.columbia.riverLife.domain.MacroinvertebrateCount;
import edu.columbia.riverLife.domain.RiverSite;
import edu.columbia.riverLife.domain.SamplingData;
import edu.columbia.riverLife.domain.SiteSampling;

@Named(value = "siteSamplingDao")
public class SiteSamplingDaoHibernateImpl implements SiteSamplingDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4644880816395389545L;
	@Inject
	protected SessionFactory sessionFactory;

	@Override
	public List<SiteSampling> getSiteSamplings() {
		Session session = sessionFactory.getCurrentSession();

		Criteria criteria = session.createCriteria(SiteSamplingHbm.class);

		List<SiteSamplingHbm> siteSamplingHbms = criteria.list();
		List<SiteSampling> siteSamplings = new ArrayList<SiteSampling>();
		for (SiteSamplingHbm siteSamplingHbm : siteSamplingHbms) {
			SiteSampling siteSampling = new SiteSampling();
			siteSampling.setSiteSamplingId(siteSamplingHbm.getSiteSamplingId());
			siteSamplings.add(siteSampling);
		}
		return siteSamplings;

	}

	@Override
	public List<Fish> getFishes() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(FishHbm.class);
		criteria.addOrder(Order.asc("name"));
		List<FishHbm> fishHbms = criteria.list();
		List<Fish> fishes = new ArrayList<Fish>();
		if (fishHbms == null || fishHbms.isEmpty())
			return fishes;
		for (FishHbm fishHbm : fishHbms) {
			Fish fish = new Fish();
			fish.setId(fishHbm.getFishId());
			fish.setName(fishHbm.getName());
			fishes.add(fish);
		}
		return fishes;
	}

	@Override
	public List<RiverSite> getRiverSites() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(RiverSiteHbm.class);
		criteria.addOrder(Order.asc("name"));

		List<RiverSiteHbm> riverSiteHbms = criteria.list();

		List<RiverSite> riverSites = new ArrayList<RiverSite>();
		if (riverSiteHbms == null || riverSiteHbms.isEmpty())
			return riverSites;
		for (RiverSiteHbm riverSiteHbm : riverSiteHbms) {
			RiverSite riverSite = new RiverSite();
			riverSite.setId(riverSiteHbm.getRiverSiteId());
			riverSite.setName(riverSiteHbm.getName());
			System.out.println("Description:" + riverSiteHbm.getDescription());
			riverSite.setDescription(riverSiteHbm.getDescription());
			riverSites.add(riverSite);
		}
		return riverSites;
	}
	
	protected List<SamplingData> getSamplingDataFromMapList(List<Map<String, Object>> aliasToValueMapList) {
		List<SamplingData> result = new ArrayList<SamplingData>();
		if (null == aliasToValueMapList || aliasToValueMapList.size() == 0) {
			return result;
		} else {
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> map = aliasToValueMapList.get(i);
				SamplingData samplingData = new SamplingData();

				Object amountObj = map.get("amount");
				if (amountObj instanceof Number) {
					samplingData.setData(((Number) amountObj).doubleValue());
				}
				
				Date date = (Date) map.get("sampling_time");
				if (date != null) {
					samplingData.setYear(this.getYear(date));
				}

				Object rm = map.get("river_mile");
				if (rm instanceof Number) {
					samplingData.setRiverMile(((Number) rm).doubleValue());
				} else {
					samplingData.setRiverMile(null);
				}

				result.add(samplingData);

			}
		}
		return result;

	}


	protected Integer getYear(Date time) {
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		int year = c.get(Calendar.YEAR);
		System.out.println(year);
		return year;
	}

	@Override
	public List<Macroinvertebrate> getMacroinvertebrates() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MacroinvertebrateHbm.class);
		criteria.addOrder(Order.asc("name"));
		List<MacroinvertebrateHbm> macroinvertebrateHbms = criteria.list();
		List<Macroinvertebrate> macroinvertebrates = new ArrayList<Macroinvertebrate>();
		if (macroinvertebrateHbms == null || macroinvertebrateHbms.isEmpty())
			return macroinvertebrates;
		for (MacroinvertebrateHbm macroinvertebrateHbm : macroinvertebrateHbms) {
			if (macroinvertebrateHbm.getActive() != null && macroinvertebrateHbm.getActive().booleanValue() == false)
				continue;
			Macroinvertebrate macroinvertebrate = new Macroinvertebrate();
			macroinvertebrate.setId(macroinvertebrateHbm.getMacroinvertebrateId());
			macroinvertebrate.setName(macroinvertebrateHbm.getName());
			macroinvertebrates.add(macroinvertebrate);
		}
		return macroinvertebrates;
	}

	// get fish counts for a specific site and specific fish or entire river
	@Override
	public List<SamplingData> getFishCountsOverTime(int riverSiteId, int fishId) {
		Session session = sessionFactory.getCurrentSession();
		String queryName;

		if (riverSiteId == 0) {
			queryName = "edu.columbia.riverlife.dal.getFishCountsOverTimeAllSites";
		} else {
			queryName = "edu.columbia.riverlife.dal.getFishCountsOverTime";
		}

		Query query = session.getNamedQuery(queryName);
		query.setInteger("fishId", fishId);

		if (riverSiteId != 0) {
			query.setInteger("riverSiteId", riverSiteId);
		}

		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

		@SuppressWarnings("unchecked")
		List<Map<String, Object>> aliasToValueMapList = query.list();

		return this.getSamplingDataFromMapList(aliasToValueMapList);
	}
	
	@Override
	public List<SamplingData> getFishCountsAlongRiver(int riverId, int year, int fishId) {
		String queryName = "edu.columbia.riverlife.dal.getFishCountsAlongRiver";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery(queryName);
		query.setInteger("riverId", riverId);
		query.setInteger("fishId", fishId);
		query.setInteger("year", year);

		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

		@SuppressWarnings("unchecked")
		List<Map<String, Object>> aliasToValueMapList = query.list();

		return this.getSamplingDataFromMapList(aliasToValueMapList);
	}

	@Override
	public List<SamplingData> getMacroCountsOverTime(int riverSiteId, int macroId) {
		Session session = sessionFactory.getCurrentSession();
		String queryName;

		if (riverSiteId == 0) {
			queryName = "edu.columbia.riverlife.dal.getMacroCountsOverTimeAllSites";
		} else {
			queryName = "edu.columbia.riverlife.dal.getMacroCountsOverTime";
		}

		Query query = session.getNamedQuery(queryName);
		query.setInteger("macroId", macroId);

		if (riverSiteId != 0) {
			query.setInteger("riverSiteId", riverSiteId);
		}

		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

		@SuppressWarnings("unchecked")
		List<Map<String, Object>> aliasToValueMapList = query.list();

		return this.getSamplingDataFromMapList(aliasToValueMapList);
	}
	
	@Override
	public List<SamplingData> getMacroCountsAlongRiver(int riverId, int year, int macroId) {
		String queryName = "edu.columbia.riverlife.dal.getMacroCountsAlongRiver";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery(queryName);
		query.setInteger("riverId", riverId);
		query.setInteger("macroId", macroId);
		query.setInteger("year", year);

		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

		@SuppressWarnings("unchecked")
		List<Map<String, Object>> aliasToValueMapList = query.list();

		return this.getSamplingDataFromMapList(aliasToValueMapList);
	}
	
	protected List<SamplingData> getSamplingDataOverTime (int riverSiteId, String queryName) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery(queryName);
		query.setInteger("riverSiteId", riverSiteId);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

		@SuppressWarnings("unchecked")
		List<Map<String, Object>> aliasToValueMapList = query.list();

		return this.getSamplingDataFromMapList(aliasToValueMapList);
	}
	
	protected List<SamplingData> getSamplingDataAlongRiver (int riverId, int year, String queryName) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery(queryName);
		query.setInteger("riverId", riverId);
		query.setInteger("year", year);

		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

		@SuppressWarnings("unchecked")
		List<Map<String, Object>> aliasToValueMapList = query.list();

		return this.getSamplingDataFromMapList(aliasToValueMapList);
	}
	
	@Override
	public List<SamplingData> getStudentSalinityOverTime(int riverSiteId) {
		String queryName = "edu.columbia.riverlife.dal.getStudentSalinityOverTime";
		return getSamplingDataOverTime(riverSiteId, queryName);
		
	}


	@Override
	public List<SamplingData> getStudentSalinityAlongRiver(int riverId, int year) {
		String queryName = "edu.columbia.riverlife.dal.getStudentSalinityAlongRiver";
		return getSamplingDataAlongRiver(riverId, year, queryName);
	}

	
	@Override
	public List<SamplingData> getStandardizedSalinityOverTime(int riverSiteId) {
		String queryName = "edu.columbia.riverlife.dal.getStandardizedSalinityOverTime";
		return getSamplingDataOverTime(riverSiteId, queryName);

	}


	@Override
	public List<SamplingData> getStandardizedSalinityAlongRiver(int riverId, int year) {
		String queryName = "edu.columbia.riverlife.dal.getStandardizedSalinityAlongRiver";
		return getSamplingDataAlongRiver(riverId, year, queryName);
	}

	@Override
	public List<SamplingData> getTurbidityOverTime(int riverSiteId) {
		String queryName = "edu.columbia.riverlife.dal.getTurbidityOverTime";
		return getSamplingDataOverTime(riverSiteId, queryName);

	}

	@Override
	public List<SamplingData> getTurbidityAlongRiver(int riverId, int year) {
		String queryName = "edu.columbia.riverlife.dal.getTurbidityAlongRiver";
		return getSamplingDataAlongRiver(riverId, year, queryName);

	}
	


	@Override
	public List<SamplingData> getWaterTempOverTime(int riverSiteId) {
		String queryName = "edu.columbia.riverlife.dal.getWaterTempOverTime";
		return getSamplingDataOverTime(riverSiteId, queryName);
	}

	@Override
	public List<SamplingData> getWaterTempAlongRiver(int riverId, int year) {
		String queryName = "edu.columbia.riverlife.dal.getWaterTempAlongRiver";
		return getSamplingDataAlongRiver(riverId, year, queryName);

	}

	@Override
	public List<SamplingData> getPhOverTime(int riverSiteId) {
		String queryName = "edu.columbia.riverlife.dal.getPhOverTime";
		return getSamplingDataOverTime(riverSiteId, queryName);
	}

	@Override
	public List<SamplingData> getPhAlongRiver(int riverId, int year) {
		String queryName = "edu.columbia.riverlife.dal.getPhAlongRiver";
		return getSamplingDataAlongRiver(riverId, year, queryName);

	}

	@Override
	public List<SamplingData> getDissolvedOxygenOverTime(int riverSiteId) {
		String queryName = "edu.columbia.riverlife.dal.getDissolvedOxygenOverTime";
		return getSamplingDataOverTime(riverSiteId, queryName);
	}

	@Override
	public List<SamplingData> getDissolvedOxygenAlongRiver(int riverId, int year) {
		String queryName = "edu.columbia.riverlife.dal.getDissolvedOxygenAlongRiver";
		return getSamplingDataAlongRiver(riverId, year, queryName);

	}

	@Override
	public List<SamplingData> getOxygenSaturationOverTime(int riverSiteId) {
		String queryName = "edu.columbia.riverlife.dal.getOxygenSaturationOverTime";
		return getSamplingDataOverTime(riverSiteId, queryName);

	}

	@Override
	public List<SamplingData> getOxygenSaturationAlongRiver(int riverId, int year) {
		String queryName = "edu.columbia.riverlife.dal.getOxygenSaturationAlongRiver";
		return getSamplingDataAlongRiver(riverId, year, queryName);

	}

	@Override
	public List<SamplingData> getTideDirectionOverTime(Integer riverSiteId) {
		String queryName = "edu.columbia.riverlife.dal.getTideDirectionOverTime";
		return getSamplingDataOverTime(riverSiteId, queryName);
	}

	@Override
	public List<SamplingData> getTideDirectionAlongRiver(int riverId, int year) {
		String queryName = "edu.columbia.riverlife.dal.getTideDirectionAlongRiver";
		return getSamplingDataAlongRiver(riverId, year, queryName);
	}

	@Override
	public List<SamplingData> getCurrentDirectionOverTime(Integer selectedRiverSite) {
		String queryName = "edu.columbia.riverlife.dal.getCurrentDirectionOverTime";
		return getSamplingDataOverTime(selectedRiverSite, queryName);
	}

	@Override
	public List<SamplingData> getCurrentDirectionAlongRiver(int riverId, int year) {
		String queryName = "edu.columbia.riverlife.dal.getTideDirectionAlongRiver";
		return getSamplingDataAlongRiver(riverId, year, queryName);
	}

	@Override
	public List<SamplingData> getAirTemperatureOverTime(Integer selectedRiverSite) {
		String queryName = "edu.columbia.riverlife.dal.getAirTemperatureOverTime";
		return getSamplingDataOverTime(selectedRiverSite, queryName);
	}

	@Override
	public List<SamplingData> getAirTemperatureAlongRiver(int riverId, int year) {
		String queryName = "edu.columbia.riverlife.dal.getAirTemperatureAlongRiver";
		return getSamplingDataAlongRiver(riverId, year, queryName);
	}

	@Override
	public List<SamplingData> getWindDirectionOverTime(Integer selectedRiverSite) {
		return null;
		/*
		String queryName = "edu.columbia.riverlife.dal.getWindDirectionOverTime";
		return getSamplingDataOverTime(selectedRiverSite, queryName);
		*/
	}

	@Override
	public List<SamplingData> getWindDirectionAlongRiver(int riverId, int year) {
		return null;
		/*
		String queryName = "edu.columbia.riverlife.dal.getWindDirectionAlongRiver";
		return getSamplingDataAlongRiver(riverId, year, queryName);
		*/
	}

	@Override
	public List<SamplingData> getWindSpeedOverTime(Integer selectedRiverSite) {
		String queryName = "edu.columbia.riverlife.dal.getWindSpeedOverTime";
		return getSamplingDataOverTime(selectedRiverSite, queryName);
	}

	@Override
	public List<SamplingData> getWindSpeedAlongRiver(int riverId, int year) {
		String queryName = "edu.columbia.riverlife.dal.getWindSpeedAlongRiver";
		return getSamplingDataAlongRiver(riverId, year, queryName);
	}

	@Override
	public List<SamplingData> getWindBeaufortRatingOverTime(Integer selectedRiverSite) {
		return null;
		/*
		String queryName = "edu.columbia.riverlife.dal.getWindBeaufortRatingOverTime";
		return getSamplingDataOverTime(selectedRiverSite, queryName);
		*/

	}

	@Override
	public List<SamplingData> getWindBeaufortRatingAlongRiver(int riverId, int year) {
		return null;
		/*
		String queryName = "edu.columbia.riverlife.dal.getWindBeaufortRatingAlongRiver";
		return getSamplingDataAlongRiver(riverId, year, queryName);
		*/
	}

	@Override
	public List<SamplingData> getSaltFrontOverTime(int riverId) {
		String queryName = "edu.columbia.riverlife.dal.getSaltFrontOverTime";
		return getSamplingDataOverTime(riverId, queryName);
	}


}
