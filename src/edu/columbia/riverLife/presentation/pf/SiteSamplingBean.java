package edu.columbia.riverLife.presentation.pf;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.springframework.context.annotation.Scope;

import edu.columbia.riverLife.bll.interfaces.SiteSamplingBo;
import edu.columbia.riverLife.domain.Fish;
import edu.columbia.riverLife.domain.FishCount;
import edu.columbia.riverLife.domain.RiverSite;
import edu.columbia.riverLife.domain.SiteSampling;

@Named(value="siteSamplingBean")
@Scope("session")
public class SiteSamplingBean {
	@Inject
	private SiteSamplingBo siteSamplingBo;
	private List<SiteSampling> siteSamplings=null;
	private List<FishCount> fishCounts=null;
	private List<SelectItem> riverSites = null;
	private Integer selectedRiverSite;
	private List<SelectItem> fishes = null;
	private List<Fish> allFishList = null;
	private Integer selectedFish1, selectedFish2;
    private LineChartModel lineModel=null;
    private int max=200;


	public LineChartModel getLineModel() {
		
		if (lineModel == null) {
			 lineModel = new LineChartModel();
			 lineModel.setTitle("Linear Chart");
			 lineModel.setLegendPosition("e");
		     
	         lineModel.getAxes().put(AxisType.X, new CategoryAxis("Years"));
	         initLineModel();
		}
		return lineModel;
	}

	protected void initLineModel() {
        lineModel.getAxes().put(AxisType.X, new CategoryAxis("Years"));
        ChartSeries series = new ChartSeries();
        series.set("2012", null);
        series.set("2013", null);
        series.set("2014", null);
        series.set("2015", null);

        this.lineModel.addSeries(series);
	}

	public void setLineModel(LineChartModel lineModel) {
		this.lineModel = lineModel;
	}

	public List<SiteSampling> getSiteSamplings() {
		if (siteSamplings == null)
			siteSamplings = siteSamplingBo.getSiteSamplings();
		return this.siteSamplings;
		
	}

	public void setSiteSamplings(List<SiteSampling> siteSamplings) {
		this.siteSamplings = siteSamplings;
	}

	public List<SelectItem> getRiverSites() {
		if (this.riverSites == null) {
			riverSites = new ArrayList<SelectItem>();
			List<RiverSite> rss= siteSamplingBo.getRiverSites();
			for (RiverSite riverSite: rss) {
				this.riverSites.add(new SelectItem(riverSite.getId(), riverSite.getName()));
			}
		}
		return this.riverSites;
		
	}

	public List<SelectItem> getFishes() {
		if (this.fishes == null) {
			fishes = new ArrayList<SelectItem>();
			allFishList= siteSamplingBo.getFishes();
			for (Fish f: allFishList) {
				this.fishes.add(new SelectItem(f.getId(), f.getName()));
			}
		}
		return this.fishes;
		
	}
	
	
	public List<FishCount> getFishCounts() {
	//	if (fishCounts == null)
	//		fishCounts = siteSamplingBo.getFishCounts();
		return this.fishCounts;
			}

	public void setFishCounts(List<FishCount> fishCounts) {
		this.fishCounts = fishCounts;
	}
	
	protected List<FishCountDisplay> getFishCountDisplays(Integer fishId, Integer riverSiteId) {
		List<FishCountDisplay> fishCountDisplays=new ArrayList<FishCountDisplay>();
		List<FishCount> fishCounts=this.getFishCounts();
		for (FishCount fc: fishCounts) {
			if (fc.getFishId().intValue() == fishId.intValue()) {
				if (riverSiteId == null || fc.getRiverSiteId().intValue() == riverSiteId.intValue()) {
					FishCountDisplay fishCountDisplay=new FishCountDisplay();
					fishCountDisplay.setCount(fc.getCount());
					fishCountDisplay.setYear(this.getYear(fc.getDate()));
					fishCountDisplays.add(fishCountDisplay);
				}
				
			}
		}
		Iterator<FishCountDisplay> it=fishCountDisplays.iterator();
		FishCountDisplay previous=null;
		while (it.hasNext()) {
			FishCountDisplay item=it.next();
			if (previous == null) {
				previous = item;
				continue;
			}
			else if (item.getYear().intValue() == previous.getYear().intValue()) {
				previous.setCount(previous.getCount() + item.getCount());
				it.remove();
			}
			else {
				previous = item;
			}
		
		}
		return fishCountDisplays;
		
	}
	
	protected Integer getYear(Date time) {
		Calendar c=Calendar.getInstance();
		c.setTime(time);
		int year=c.get(Calendar.YEAR);
		System.out.println(year);
		return year;
	}

	public Integer getSelectedRiverSite() {
		return selectedRiverSite;
	}

	public void setSelectedRiverSite(Integer selectedRiverSite) {
		this.selectedRiverSite = selectedRiverSite;
	}

	public Integer getSelectedFish1() {
		return selectedFish1;
	}

	public void setSelectedFish1(Integer selectedFish) {
		this.selectedFish1 = selectedFish;
	}
	
	public Integer getSelectedFish2() {
		return selectedFish2;
	}

	public void setSelectedFish2(Integer selectedFish) {
		this.selectedFish2 = selectedFish;
	}
	
	
	public void doSubmit() {
		createLineCharModel();
	}
	
	 protected List<FishCountDisplay> buildFishCountDisplays(Integer fishId) {
		 List<FishCountDisplay> fishCountDisplays=this.getFishCountDisplays(fishId,
				 this.selectedRiverSite);
		// Sorting
		 Collections.sort(fishCountDisplays, new Comparator<FishCountDisplay>() {
		         @Override
		         public int compare(FishCountDisplay d1, FishCountDisplay d2)
		         {

		             return  d1.getYear().compareTo(d2.getYear());
		         }
		     });
		 
		 return fishCountDisplays;
	 }
	 
	 protected void buildLineChartSeries( List<FishCountDisplay> fishCountDisplays, String name ) {
		 
		  LineChartSeries series = new LineChartSeries();
	      series.setLabel(name);
	        
		 for (FishCountDisplay fcd: fishCountDisplays) {
			 Integer count=fcd.getCount();
			 if (count != null && count.intValue() > max)
				 max = count.intValue();
			 series.set(fcd.getYear().toString(),count);
		 }
		 this.lineModel.addSeries(series);
		
	 }

	 private boolean isListEmpty(List<FishCountDisplay> list) {
		 if (list == null || list.isEmpty())
			 return true;
		 else
			 return false;
	 }
	 
	private void createLineCharModel() {
		this.lineModel.clear();
		max = 0;
		List<FishCountDisplay> series1=null, series2=null;
		if (this.selectedFish1 != null) {
			series1 = buildFishCountDisplays(selectedFish1);
		}
		if (this.selectedFish2 != null) {
			series2=buildFishCountDisplays(selectedFish2);
		}
		
		if (this.isListEmpty(series1) == false && this.isListEmpty(series2) )
			this.buildLineChartSeries(series1, this.getFishName(this.getSelectedFish1()));
		else if (this.isListEmpty(series2) == false && this.isListEmpty(series1) )
			this.buildLineChartSeries(series2, this.getFishName(this.getSelectedFish2()));
		else if (this.isListEmpty(series1) == false && this.isListEmpty(series2) == false) {
			if (series1.size() != series2.size()) {
				addAdditionalPlottingSpots(series1, series2);
			}
			this.buildLineChartSeries(series1, this.getFishName(this.getSelectedFish1()));
			this.buildLineChartSeries(series2, this.getFishName(this.getSelectedFish2()));
		}
		else {
			this.initLineModel();
		}
		
		Axis yAxis = lineModel.getAxis(AxisType.Y);
		yAxis.setMin(0);
		int i=1;
		while (true) {
			if (max > i++ * 10)
				continue;
			max = i * 10;
			break;
		}
		yAxis.setMax(max);

	}


	private void addAdditionalPlottingSpots(List<FishCountDisplay> series1, List<FishCountDisplay> series2) {
		int minYear=Integer.MAX_VALUE;
		int maxYear = 0;
		for (FishCountDisplay fcd: series1) {
			int year=fcd.getYear();
			if (year < minYear)
				minYear = year;
			if (year > maxYear)
				maxYear = year;
		}
		for (FishCountDisplay fcd: series2) {
			int year=fcd.getYear();
			if (year < minYear)
				minYear = year;
			if (year > maxYear)
				maxYear = year;
		}
		for (int i=minYear; i<=maxYear; i++) {
			if (!this.yearExistsInSeries(series1, i)) {
				FishCountDisplay newItem=new FishCountDisplay();
				newItem.setYear(i);
				newItem.setCount(null);
				series1.add(newItem);
			}
			if (!this.yearExistsInSeries(series2, i)) {
				FishCountDisplay newItem=new FishCountDisplay();
				newItem.setYear(i);
				newItem.setCount(null);
				series2.add(newItem);
			}
		}
		 Collections.sort(series1, new Comparator<FishCountDisplay>() {
	         @Override
	         public int compare(FishCountDisplay d1, FishCountDisplay d2)
	         {

	             return  d1.getYear().compareTo(d2.getYear());
	         }
	     });
		 Collections.sort(series2, new Comparator<FishCountDisplay>() {
	         @Override
	         public int compare(FishCountDisplay d1, FishCountDisplay d2)
	         {

	             return  d1.getYear().compareTo(d2.getYear());
	         }
	     });
		
	}
	
	private boolean yearExistsInSeries(List<FishCountDisplay> fcds, int year) {
		for (FishCountDisplay fcd: fcds) {
			if (fcd.getYear().intValue() == year)
				return true;
		}
		return false;
	}
	
	protected String getFishName(int fishId) {
		for (Fish f: allFishList) {
			if (f.getId().intValue() == fishId) 
				return f.getName();
		}
		return null;
	}

}
