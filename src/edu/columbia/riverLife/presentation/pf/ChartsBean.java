package edu.columbia.riverLife.presentation.pf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.context.annotation.Scope;

import edu.columbia.riverLife.bll.interfaces.RiverBo;
import edu.columbia.riverLife.bll.interfaces.SiteSamplingBo;
import edu.columbia.riverLife.domain.Fish;
import edu.columbia.riverLife.domain.Macroinvertebrate;
import edu.columbia.riverLife.domain.River;
import edu.columbia.riverLife.domain.RiverSite;

@Named(value="chartsBean")
@Scope("session")
public class ChartsBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1324036040116302738L;
	
	public static final String firstParamColor="58BA27";
	public static final String secondParamColor="FFCC33";
	public final static String FISH="Fish";
	public final static int FISH_GROUP_ID=0;
	
	public final static String MACROINVERTIBRATE="Macroinvertebrate";
	public final static int MACROINVERTIBRATE_GROUP_ID=1;

	public final static String TIDES_CURRENTS="Tides & Currents";
	public final static int TIDES_CURRENTS_GROUP_ID=2;

	public final static String WEATHER="Weather";
	public final static int WEATHER_GROUP_ID=3;

	public final static String WATER_MEASUREMENTS="Water Measurements";
	public final static int WATER_MEASUREMENTS_GROUP_ID=4;
	
	public final static String AIR_TEMPERATURE="Air temperature (C)";
	public final static int AIR_TEMPERATURE_ID=1;
	
	public final static String WIND_DIRECTION="Wind direction";
	public final static int WIND_DIRECTION_ID=2;
	
	public final static String WIND_SPEED_MPH="Wind speed (MPH)";
	public final static int WIND_SPEED_MPH_ID=3;
	
	public final static String WIND_BEAUFORT_RATING="Wind beaufort rating";
	public final static int WIND_BEAUFORT_RATING_ID=4;
	
	public final static String TIDE_DIRECTION="Tide Direction";
	public final static int TIDE_DIRECTION_ID=5;
	
	public final static String CURRENT_DIRECTION="Current Direction";
	public final static int CURRENT_DIRECTION_ID=6;
	
	public final static String WATER_TEMPERATURE="Water Temperature (C)";
	public final static int WATER_TEMPERATURE_ID=7;
	
	public final static String STUDENT_SALINITY="Student Salinity (ppm)";
	public final static int STUDENT_SALINITY_ID=8;
	
	public final static String STANDARDIZED_SALINITY="Standardized Salinity (ppm)";
	public final static int STANDARDIZED_SALINITY_ID=9;
	
	public final static String TURBIDITY="Turbidity (cm)";
	public final static int TURBIDITY_ID=10;
	
	public final static String DISSOLVED_OXYGEN="Dissolved Oxygen (mg/l)";
	public final static int DISSOLVED_OXYGEN_ID=11;
	
	public final static String PERCENT_SATURATION_OXYGEN="Saturation Of Oxygen (%)";
	public final static int PERCENT_SATURATION_OXYGEN_ID=12;
	
	public final static String PH="pH";
	public final static int PH_ID=13;
	
	@Inject
	private RiverBo riverBo;
	@Inject
	protected SiteSamplingBo siteSamplingBo;
	
	private List<SelectItem> rivers = null;
	private Integer selectedRiver;
	
	private List<SelectItem> riverSites = null;
	private Integer selectedRiverSite;
		
	
	private Integer firstSelectedParamGroup;
	private Integer secondSelectedParamGroup;
	
	private Integer firstSelectedParam;
	private Integer secondSelectedParam;

	private List<SelectItem> paramGroups;
	private List<List<SelectItem>> params;
	
	private MapModel mapModel;
    private Marker marker;
    private double lat=0.0, lon=0.0;

	private List<Fish> allFishList=null;
	private List<Macroinvertebrate> allMacroinvertebrates=null;
	private List<RiverSite> riverSiteLists;
	
	private RiverSite selectedRiverSiteFromMap;
	private String siteOrder="a";
	
	@PostConstruct
	public void init() {
		mapModel = new DefaultMapModel();
		paramGroups = new ArrayList<SelectItem> ();
		paramGroups.add(new SelectItem(FISH_GROUP_ID, FISH));
		paramGroups.add(new SelectItem(MACROINVERTIBRATE_GROUP_ID, MACROINVERTIBRATE));
	//	paramGroups.add(new SelectItem(TIDES_CURRENTS_GROUP_ID, TIDES_CURRENTS));
		paramGroups.add(new SelectItem(WEATHER_GROUP_ID, WEATHER));
		paramGroups.add(new SelectItem(WATER_MEASUREMENTS_GROUP_ID, WATER_MEASUREMENTS));

		
		List<River> riverList=this.riverBo.getRivers();
		if (riverList == null)
			return;
		rivers = new ArrayList<SelectItem>();
		for (River r: riverList) {
			this.rivers.add(new SelectItem(r.getRiverId(), r.getName()));
		}
		params = new ArrayList<List<SelectItem>>();
		List<SelectItem> fishes=new ArrayList<SelectItem>();
		allFishList= siteSamplingBo.getFishes();
		for (Fish f: allFishList) {
			fishes.add(new SelectItem(f.getId(), f.getName()));
		}
		params.add(fishes);
		List<SelectItem> macroinvertibrates=new ArrayList<SelectItem>();
		allMacroinvertebrates= siteSamplingBo.getMacroinvertebrates();
		for (Macroinvertebrate m: allMacroinvertebrates) {
			macroinvertibrates.add(new SelectItem(m.getId(), m.getName()));
		}
		params.add(macroinvertibrates);
		
		List<SelectItem> tideCurrentsParams=new ArrayList<SelectItem>();
		tideCurrentsParams.add(new SelectItem(TIDE_DIRECTION_ID, TIDE_DIRECTION));
		tideCurrentsParams.add(new SelectItem(CURRENT_DIRECTION_ID, CURRENT_DIRECTION));
		params.add(tideCurrentsParams);
		
		List<SelectItem> weatherParams=new ArrayList<SelectItem>();
		weatherParams.add(new SelectItem(AIR_TEMPERATURE_ID, AIR_TEMPERATURE));
	//	weatherParams.add(new SelectItem(WIND_DIRECTION_ID, WIND_DIRECTION));
		weatherParams.add(new SelectItem(WIND_SPEED_MPH_ID, WIND_SPEED_MPH));
	//	weatherParams.add(new SelectItem(WIND_BEAUFORT_RATING_ID, WIND_BEAUFORT_RATING));
		params.add(weatherParams);
		
		List<SelectItem> waterParams=new ArrayList<SelectItem>();
		waterParams.add(new SelectItem(WATER_TEMPERATURE_ID, WATER_TEMPERATURE));
		waterParams.add(new SelectItem(STUDENT_SALINITY_ID, STUDENT_SALINITY));
		waterParams.add(new SelectItem(STANDARDIZED_SALINITY_ID, STANDARDIZED_SALINITY));
		waterParams.add(new SelectItem(TURBIDITY_ID, TURBIDITY));
		waterParams.add(new SelectItem(DISSOLVED_OXYGEN_ID, DISSOLVED_OXYGEN));
		waterParams.add(new SelectItem(PERCENT_SATURATION_OXYGEN_ID, PERCENT_SATURATION_OXYGEN));
		waterParams.add(new SelectItem(PH_ID, PH));
		
		params.add(waterParams);
		


	 }

	protected String getLineColors(ChartSeries series1, ChartSeries series2) {
		String lineColors="";
		if (series1 == null && series2 == null)
			return lineColors;
		
		if (series1 != null && series2 != null) {
			lineColors=this.firstParamColor + "," + this.secondParamColor;
		} else if (series1 != null)
			lineColors=this.firstParamColor;
		else 
			lineColors=this.secondParamColor;
		
		return lineColors;

	}
	
	protected int calculateMax(double max) {
		double temp=max * 1.4;
		int i=(int)Math.ceil(temp);
		if (i>0 && i%6 == 0)
			return i;
		i = i+ 6 - i%6;
		return i;
		
	}
	
	protected void buildSiteList() {
		this.riverSites.clear();
		if (this.siteOrder.equalsIgnoreCase("a")){
			Collections.sort(this.riverSiteLists, new Comparator<RiverSite>() {
	
				@Override
				public int compare(RiverSite o1, RiverSite o2) {
					return o1.getName().compareTo(o2.getName());
				}
				
			});
		} else {
			Collections.sort(this.riverSiteLists, new Comparator<RiverSite>() {
				
				@Override
				public int compare(RiverSite o1, RiverSite o2) {
					return o2.getLat().compareTo(o1.getLat());
				}
				
			});
		}
		for (RiverSite rs: riverSiteLists) {
				this.riverSites.add(new SelectItem(rs.getId(), rs.getName() + " " + rs.getMile()));
		}
	}
	public void onRiverChange() {
		mapModel.getMarkers().clear();

		if (this.selectedRiver == null)
			return;
		riverSiteLists=riverBo.getRiverSites(this.selectedRiver);
		if (riverSiteLists == null || riverSiteLists.size() ==0)
			return;
		if (this.riverSites == null)
			this.riverSites = new ArrayList<SelectItem>();
		buildSiteList();
		
		double la=0.0, lo=0.0;
		for (RiverSite rs: riverSiteLists) {
			la = la + rs.getLat();
			lo = lo + rs.getLon();
			 LatLng coord = new LatLng(rs.getLat(), rs.getLon());
			 Marker m= new Marker(coord, rs.getName() + " - " + rs.getMile(), rs.getId());
			 mapModel.addOverlay(m);
		} /*
		lat = la / riverSites.size();
		lon = lo /riverSites.size();
		*/
		lat = riverSiteLists.get(0).getLat();
		lon = riverSiteLists.get(0).getLon();

	}
	
	public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
        if (marker != null) {
        	Integer id=(Integer)marker.getData();
        	if (this.riverSiteLists == null) {
        		selectedRiverSiteFromMap=null;
        		return;
        	}
        	for (RiverSite rs: riverSiteLists) {
    			if (id.intValue() == rs.getId().intValue()) {
    				this.selectedRiverSiteFromMap=rs;
    				break;
    			}
    		} 
        }
	    

    }
      
	public void doSelect() {
		Integer riverSiteId = (Integer) marker.getData();
	    this.selectedRiverSite = riverSiteId;

    }
	
	public void doSubmit() {
		
	}

	
			
	public List<SelectItem> getFirstParamList() {
		if (this.firstSelectedParamGroup != null) {
			return params.get(firstSelectedParamGroup);
		}
		return null;
	}
	
	public List<SelectItem> getSecondParamList() {
		if (this.secondSelectedParamGroup != null) {
			return params.get(secondSelectedParamGroup);
		}
		return null;
	}
	
	public List<SelectItem> getRivers() {
		return rivers;
	}

	public void setRivers(List<SelectItem> rivers) {
		this.rivers = rivers;
	}

	public Integer getSelectedRiver() {
		return selectedRiver;
	}

	public void setSelectedRiver(Integer selectedRiver) {
		this.selectedRiver = selectedRiver;
	}

	public Integer getFirstSelectedParamGroup() {
		return firstSelectedParamGroup;
	}

	public void setFirstSelectedParamGroup(Integer firstSelectedParamGroup) {
		this.firstSelectedParamGroup = firstSelectedParamGroup;
	}

	public Integer getSecondSelectedParamGroup() {
		return secondSelectedParamGroup;
	}

	public void setSecondSelectedParamGroup(Integer secondSelectedParamGroup) {
		this.secondSelectedParamGroup = secondSelectedParamGroup;
	}

	public Integer getFirstSelectedParam() {
		return firstSelectedParam;
	}

	public void setFirstSelectedParam(Integer firstSelectedParam) {
		this.firstSelectedParam = firstSelectedParam;
	}

	public Integer getSecondSelectedParam() {
		return secondSelectedParam;
	}

	public void setSecondSelectedParam(Integer secondSelectedParam) {
		this.secondSelectedParam = secondSelectedParam;
	}

	public List<SelectItem> getParamGroups() {
		return paramGroups;
	}

	public void setParamGroups(List<SelectItem> paramGroups) {
		this.paramGroups = paramGroups;
	}

	public MapModel getMapModel() {
		return mapModel;
	}

	public void setMapModel(MapModel mapModel) {
		this.mapModel = mapModel;
	}

	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}
	
	protected Integer getYear(Date time) {
		Calendar c=Calendar.getInstance();
		c.setTime(time);
		int year=c.get(Calendar.YEAR);
		System.out.println(year);
		return year;
	}

	public List<SelectItem> getRiverSites() {
		return riverSites;
	}

	public void setRiverSites(List<SelectItem> riverSites) {
		this.riverSites = riverSites;
	}

	public Integer getSelectedRiverSite() {
		return selectedRiverSite;
	}

	public void setSelectedRiverSite(Integer selectedRiverSite) {
		this.selectedRiverSite = selectedRiverSite;
	}
	
	public String getFishName(Integer id) {
		if (id == null)
			return null;
		for (Fish f: this.allFishList) {
			if (f.getId().intValue() == id.intValue())
				return f.getName();
		}
		return null;
	}
	
	public String getMacroInvertibrateName(Integer id) {
		if (id == null)
			return null;
		for (Macroinvertebrate f: this.allMacroinvertebrates) {
			if (f.getId().intValue() == id.intValue())
				return f.getName();
		}
		return null;
	}
	
	protected String getParamName(Integer selectedGroup, Integer selectedParam) {
		
		if (selectedGroup == null || selectedParam == null)
			return null;
		
		switch (selectedGroup.intValue()) {
			case FISH_GROUP_ID:
				return this.getFishName(selectedParam);
			case MACROINVERTIBRATE_GROUP_ID:
				return this.getMacroInvertibrateName(selectedParam);
			default:
				switch (selectedParam.intValue()) {
				case AIR_TEMPERATURE_ID:
					return AIR_TEMPERATURE;
				case WIND_DIRECTION_ID:
					return WIND_DIRECTION;
				case WIND_SPEED_MPH_ID:
					return WIND_SPEED_MPH;
				case WIND_BEAUFORT_RATING_ID:
					return WIND_BEAUFORT_RATING;
				case TIDE_DIRECTION_ID:
					return TIDE_DIRECTION;
				case CURRENT_DIRECTION_ID:
					return CURRENT_DIRECTION;
				case WATER_TEMPERATURE_ID:
					return WATER_TEMPERATURE;
				case STUDENT_SALINITY_ID:
					return STUDENT_SALINITY;
				case STANDARDIZED_SALINITY_ID:
					return STANDARDIZED_SALINITY;
				case TURBIDITY_ID:
					return TURBIDITY;
				case DISSOLVED_OXYGEN_ID:
					return DISSOLVED_OXYGEN;
				case PERCENT_SATURATION_OXYGEN_ID:
					return PERCENT_SATURATION_OXYGEN;
				case PH_ID:
					return PH;
				
			}
		}
		return null;
	}

	public RiverSite getSelectedRiverSiteFromMap() {
		return selectedRiverSiteFromMap;
	}

	public void setSelectedRiverSiteFromMap(RiverSite selectedRiverSiteFromMap) {
		this.selectedRiverSiteFromMap = selectedRiverSiteFromMap;
	}
	
	public String getSiteOrder() {
		return siteOrder;
	}
	
	public void setSiteOrder(String siteOrder) {
		this.siteOrder = siteOrder;
	}
	 
	
}
