package edu.columbia.riverLife.presentation.pf;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.LinearAxis;
import org.springframework.context.annotation.Scope;

import edu.columbia.riverLife.domain.SamplingData;

@Named(value = "chartsOverTimeBean")
@Scope("session")
public class ChartsOverTimeBean extends ChartsBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6458804257935324551L;
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
			 lineModel.setTitle("Charts Over Time");
			 lineModel.setZoom(true);

	         initLineModel();
		}
		return lineModel;
	}
	
	
	protected void initLineModel() {
		LinearAxis xAxis = new LinearAxis("Years");
		xAxis.setMin(2003);
		xAxis.setMax(2024);
		xAxis.setTickInterval("1");   // step = 1 year
		xAxis.setTickFormat("%.0f");  // format = whole numbers (no decimals)

		lineModel.getAxes().put(AxisType.X, xAxis);

		LineChartSeries series = new LineChartSeries();
		series.setLabel("Placeholder");

		// numeric years
		for (int year = 2003; year <= 2024; year++) {
			series.set(year, null);
		}
		lineModel.addSeries(series);
	}

	public void doSubmit() {
		boolean error=false;
		if (this.getSelectedRiver() == null) {
			error = true;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error:", "River is required."));
		}
		if (this.getSelectedRiverSite() == null) {
			error = true;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error:", "River site is required."));
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
				this.getFirstSelectedParam(), this.getSelectedRiverSite());
		List<SamplingData> dataDisplays2 = this.getData(this.getSecondSelectedParamGroup(), 
				this.getSecondSelectedParam(), this.getSelectedRiverSite());
		if ((dataDisplays1 == null || dataDisplays1.size() == 0) && (dataDisplays2 == null || dataDisplays2.size() == 0)) {
			initLineModel();
			return;
		}
		LineChartSeries series1=null, series2=null;
		max1=0;
		max2 =0;
		paramName1 = this.getParamName(this.getFirstSelectedParamGroup(), this.getFirstSelectedParam());
		paramName2 = this.getParamName(this.getSecondSelectedParamGroup(), this.getSecondSelectedParam());
		if (this.isListEmpty(dataDisplays1) == false) {
			series1 = this.buildLineSeries(dataDisplays1, paramName1);
			max1=this.getMax(dataDisplays1);
		}
		if (this.isListEmpty(dataDisplays2) == false) {
			series2 = this.buildLineSeries(dataDisplays2, paramName2);
			max2=this.getMax(dataDisplays2);
		}
		if (series1 == null && series2 == null) {
			this.initLineModel();
			return;
		}
		this.setAxies(series1, series2, max1, max2);
		String lineColors=this.getLineColors(series1, series2);
		
		lineModel.setSeriesColors(lineColors);

		

	}
	

	protected List<SamplingData> getData(Integer selectedGroup, Integer selectedParam, int riverSiteId) {
		
		if (selectedGroup == null || selectedParam == null)
			return null;
		
		switch (selectedGroup.intValue()) {
			case FISH_GROUP_ID:
				return this.siteSamplingBo.getFishCountsOverTime(this.getSelectedRiverSite(), selectedParam);
			case MACROINVERTIBRATE_GROUP_ID:
				return this.siteSamplingBo.getMacroCountsOverTime(this.getSelectedRiverSite(), selectedParam);
			default:
				switch (selectedParam.intValue()) {
				case AIR_TEMPERATURE_ID:
					return this.siteSamplingBo.getAirTemperatureOverTime(this.getSelectedRiverSite());
				case WIND_DIRECTION_ID:
					return this.siteSamplingBo.getWindDirectionOverTime(this.getSelectedRiverSite());
				case WIND_SPEED_MPH_ID:
					return this.siteSamplingBo.getWindSpeedOverTime(this.getSelectedRiverSite());
				case WIND_BEAUFORT_RATING_ID:
					return this.siteSamplingBo.getWindBeaufortRatingOverTime(this.getSelectedRiverSite());
				case TIDE_DIRECTION_ID:
					return this.siteSamplingBo.getTideDirectionOverTime(this.getSelectedRiverSite());
				case CURRENT_DIRECTION_ID:
					return this.siteSamplingBo.getCurrentDirectionOverTime(this.getSelectedRiverSite());
				case WATER_TEMPERATURE_ID:
					return this.siteSamplingBo.getWaterTempOverTime(this.getSelectedRiverSite());
				case STUDENT_SALINITY_ID:
					return this.siteSamplingBo.getStudentSalinityOverTime(this.getSelectedRiverSite());
				case STANDARDIZED_SALINITY_ID:
					return this.siteSamplingBo.getStandardizedSalinityOverTime(this.getSelectedRiverSite());
				case TURBIDITY_ID:
					return this.siteSamplingBo.getTurbidityOverTime(this.getSelectedRiverSite());
				case DISSOLVED_OXYGEN_ID:
					return this.siteSamplingBo.getDissolvedOxygenOverTime(this.getSelectedRiverSite());
				case PERCENT_SATURATION_OXYGEN_ID:
					return this.siteSamplingBo.getOxygenSaturationOverTime(riverSiteId);
				case PH_ID:
					return this.siteSamplingBo.getPhOverTime(riverSiteId);
					
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
	
	private void addAdditionalPlottingSpots(List<SamplingData> series) {
		int minYear = 2003;
		int maxYear = 2016;
		
		for (int i = minYear; i <= maxYear; i++) {
			if (!this.yearExistsInSeries(series, i)) {
				SamplingData newItem = new SamplingData();
				newItem.setYear(i);
				newItem.setData(null);
				series.add(newItem);
			}
			
		}
		Collections.sort(series, new Comparator<SamplingData>() {
			@Override
			public int compare(SamplingData d1, SamplingData d2) {

				return d1.getYear().compareTo(d2.getYear());
			}
		});

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
		System.out.println(year);
		return year;
	}

	private boolean yearExistsInSeries(List<SamplingData> data, int year) {
		for (SamplingData fcd : data) {
			if (fcd.getYear().intValue() == year)
				return true;
		}
		return false;
	}
	
	protected LineChartSeries buildLineSeries(List<SamplingData> samplingDatas, String name) {
		LineChartSeries series = new LineChartSeries();
		series.setLabel(name);

		Map<Integer, Double> yearToData = new HashMap<>();
		for (SamplingData fcd : samplingDatas) {
			if (fcd.getYear() != null)
				yearToData.put(fcd.getYear(), fcd.getData());
		}

		for (int year = 2003; year <= 2024; year++) {
			series.set(year, yearToData.get(year)); // numeric x-values
		}

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
	 

	 

}
