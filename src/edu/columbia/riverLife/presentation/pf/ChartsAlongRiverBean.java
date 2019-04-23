package edu.columbia.riverLife.presentation.pf;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.LinearAxis;
import org.springframework.context.annotation.Scope;

import edu.columbia.riverLife.domain.SamplingData;

@Named(value = "chartsAlongRiverBean")
@Scope("session")
public class ChartsAlongRiverBean extends ChartsBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8493071164858968933L;
	private String selectedYear;
	private LineChartModel lineModel = null;
	private double max1=0,max2=0;
	private String paramName1;
	private String paramName2;
	
	@PostConstruct
	public void init() {
		super.init();

	}
	public LineChartModel getLineModel() {
		
		if (lineModel == null) {
			 lineModel = new LineChartModel();
			 lineModel.setLegendPosition("n");
			 lineModel.setTitle("Charts Along River");
			 lineModel.setZoom(true);

	         initLineModel();
		}
		return lineModel;
	}
	
	
	protected void initLineModel() {
		ChartSeries series = new ChartSeries();
		series.set(1.0, null);
		lineModel.addSeries(series);
	}

	public void doSubmit() {
		boolean error=false;
		if (this.getSelectedRiver() == null) {
			error = true;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error:", "River is required."));
		}
		
		if (this.getFirstSelectedParam() == null && this.getSecondSelectedParam() == null) {
			error = true;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error:", "At least one parameter is required."));
		
		}
			
		if (error)
			return;
		this.getLineModel().clear();
		List<SamplingData> dataDisplays1 = this.getData(this.getFirstSelectedParamGroup(), 
				this.getFirstSelectedParam());
		List<SamplingData> dataDisplays2 = this.getData(this.getSecondSelectedParamGroup(), 
				this.getSecondSelectedParam());
		if ((dataDisplays1 == null || dataDisplays1.size() == 0) && (dataDisplays2 == null || dataDisplays2.size() == 0)) {
			initLineModel();
			return;
		}
		List<Double> combinedRiverMiles=this.getCombinedRiverMiles(dataDisplays1, dataDisplays2);
		LineChartSeries series1=null, series2=null;
		max1=0;
		max2 =0;
		paramName1 = this.getParamName(this.getFirstSelectedParamGroup(), this.getFirstSelectedParam());
		paramName2 = this.getParamName(this.getSecondSelectedParamGroup(), this.getSecondSelectedParam());
		if (this.isListEmpty(dataDisplays1) == false) {
			series1 = this.buildLineSeries(dataDisplays1, combinedRiverMiles, paramName1);
			max1=this.getMax(dataDisplays1);
		}
		if (this.isListEmpty(dataDisplays2) == false) {
			series2 = this.buildLineSeries(dataDisplays2, combinedRiverMiles, paramName2);
			max2=this.getMax(dataDisplays2);
		}
		if (series1 == null && series2 == null) {
			this.initLineModel();
			return;
		}
		this.setAxies(series1, series2, max1, max2);
		Axis xAxis=lineModel.getAxis(AxisType.X);
		xAxis.setLabel("River Miles");
		
		String lineColors=this.getLineColors(series1, series2);
		
		lineModel.setSeriesColors(lineColors);
	}
	
	private void addToCombinedList(Double d, List<Double> list) {
		for (Double dd: list) {
			if (this.sameDouble(d, dd))
				return;
		}
		list.add(d);
	}
	
	private List<Double> getCombinedRiverMiles(List<SamplingData> series1, List<SamplingData> series2) {
		List<Double> combinedPlots=new ArrayList<Double> ();
		if (series1 != null) {
			for (SamplingData sd: series1) {
				this.addToCombinedList(sd.getRiverMile(), combinedPlots);
			}
		}
		if (series2 != null) {
			for (SamplingData sd: series2) {
				this.addToCombinedList(sd.getRiverMile(), combinedPlots);
			}
		}
		Collections.sort(combinedPlots);
		return combinedPlots;
	}
	
	private boolean sameDouble(double d1, double d2) {
		if (Math.abs(d1 - d2) < 0.0001)
			return true;
		else
			return false;
	}
	protected List<SamplingData> getData(Integer selectedGroup, Integer selectedParam) {
		if (this.getSelectedRiver() == null)
			return null;
		if (this.getSelectedYear() == null)
			return null;
		
		int year = Integer.parseInt(this.getSelectedYear());
		int riverId = this.getSelectedRiver().intValue();
		if (selectedGroup == null || selectedParam == null)
			return null;
		
		switch (selectedGroup.intValue()) {
			case FISH_GROUP_ID:
				return this.siteSamplingBo.getFishCountsAlongRiver(riverId, year, selectedParam);
			case MACROINVERTIBRATE_GROUP_ID:
				return this.siteSamplingBo.getMacroCountsAlongRiver(riverId, year, selectedParam);
			
			default:
				switch (selectedParam.intValue()) {
				case AIR_TEMPERATURE_ID:
					return this.siteSamplingBo.getAirTemperatureAlongRiver(riverId, year);
				case WIND_DIRECTION_ID:
					return this.siteSamplingBo.getWindDirectionAlongRiver(riverId, year);
				case WIND_SPEED_MPH_ID:
					return this.siteSamplingBo.getWindSpeedAlongRiver(riverId, year);
				case WIND_BEAUFORT_RATING_ID:
					return this.siteSamplingBo.getWindBeaufortRatingAlongRiver(riverId, year);
				case TIDE_DIRECTION_ID:
					return this.siteSamplingBo.getTideDirectionAlongRiver(riverId, year);
				case CURRENT_DIRECTION_ID:
					return this.siteSamplingBo.getCurrentDirectionAlongRiver(riverId, year);
				case WATER_TEMPERATURE_ID:
					return this.siteSamplingBo.getWaterTempAlongRiver(riverId, year);
				case STUDENT_SALINITY_ID:
					return this.siteSamplingBo.getStudentSalinityAlongRiver(riverId, year);
				case STANDARDIZED_SALINITY_ID:
					return this.siteSamplingBo.getStandardizedSalinityAlongRiver(riverId, year);
				case TURBIDITY_ID:
					return this.siteSamplingBo.getTurbidityAlongRiver(riverId, year);
				case DISSOLVED_OXYGEN_ID:
					return this.siteSamplingBo.getDissolvedOxygenAlongRiver(riverId, year);
				case PERCENT_SATURATION_OXYGEN_ID:
					return this.siteSamplingBo.getOxygenSaturationAlongRiver(riverId, year);
				case PH_ID:
					return this.siteSamplingBo.getPhAlongRiver(riverId, year);
				
			}
		}
		return null;
	}



	private double getMax(List<SamplingData> dataDisplays) {
		double max=0;
		for (SamplingData dd : dataDisplays) {
			if (dd.getData() != null && dd.getData().doubleValue() > max)
				max = dd.getData();
		}
		return max;
	}
	

	private boolean isListEmpty(List<SamplingData> list) {
		if (list == null || list.isEmpty())
			return true;
		else
			return false;
	}


	protected Integer getYear(Date time) {
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		int year = c.get(Calendar.YEAR);
		//System.out.println(year);
		return year;
	}

	private SamplingData getSamplingDataForRiverMile(List<SamplingData> samplingDatas, Double mile) {
		for (SamplingData sd: samplingDatas) {
			if (this.sameDouble(sd.getRiverMile(), mile)) 
				return sd;
		}
		return null;
	}
	 protected LineChartSeries buildLineSeries(List<SamplingData> samplingDatas, List<Double> combinedMiles, String name ) {
		 if (samplingDatas == null)
			 return null;
		  LineChartSeries series = new LineChartSeries();
	      series.setLabel(name);
	      double maxMile = 0;
	      for (int i=0; i < combinedMiles.size(); i++) {
	    	 Double mile = combinedMiles.get(i);
	    	 if (mile != null && mile.doubleValue() > maxMile)
	    		 maxMile = mile.doubleValue();
	    	 SamplingData sd=this.getSamplingDataForRiverMile(samplingDatas, mile);
	    	 if (sd != null) {
	    		 Double data=sd.getData();
				 series.set(mile,data);
	    	 }
	    	 else {
	    		 series.set(mile,  null);
	    	 } 
	     }
	      series.set(Math.floor(maxMile) + 1, null);
		 return series;
		 
	 }

	 private void setAxies(LineChartSeries series1, LineChartSeries series2, double max1, double max2) {
			
		 if (series1 != null) {
			 Axis yAxis = lineModel.getAxis(AxisType.Y);
			 yAxis.setMin(0);
			 yAxis.setMax(calculateMax(max1));
			 yAxis.setLabel(paramName1);
			 lineModel.addSeries(series1);
		 }
		
		if (series2 == null)
			return;
		
		series2.setYaxis(AxisType.Y2);
		Axis y2Axis = new LinearAxis(paramName2);
        y2Axis.setMin(0);
        y2Axis.setMax(calculateMax(max2));
         
        lineModel.getAxes().put(AxisType.Y2, y2Axis);
		lineModel.addSeries(series2);

	 }

	public String getSelectedYear() {
		return selectedYear;
	}
	public void setSelectedYear(String selectedYear) {
		this.selectedYear = selectedYear;
	}
	
	
	

}
