package edu.columbia.riverLife.dal.implementation.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import edu.columbia.riverLife.dal.implementation.hibernate.bean.RiverHbm;
import edu.columbia.riverLife.dal.implementation.hibernate.bean.RiverSiteHbm;
import edu.columbia.riverLife.dal.interfaces.RiverDao;
import edu.columbia.riverLife.domain.River;
import edu.columbia.riverLife.domain.RiverSite;

@Named(value="riverDao")
public class RiverDaoHibernateImpl  implements RiverDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8121401253933248271L;
	@Inject
    protected SessionFactory sessionFactory;
	
	@Override
	public List<River> getRivers() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(RiverHbm.class);
        List<RiverHbm> riverHbms=criteria.list();
        if (riverHbms == null)
        	return null;
        List<River> rivers=new ArrayList<River>();
        for (RiverHbm riverHbm: riverHbms) {
        	River river=new River();
        	river.setRiverId(riverHbm.getRiverId());
        	river.setName(riverHbm.getName());
        	rivers.add(river);
        }
		return rivers;
	}
	@Override
	public List<RiverSite> getRiverSites(int riverId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(RiverSiteHbm.class);
        criteria.addOrder( Order.asc("name") );
        Criteria riverCriteria = criteria.createCriteria("riverHbm");
        riverCriteria.add(Restrictions.eq("riverId", riverId));
        List<RiverSiteHbm> riverSiteHbms = criteria.list();
        if (riverSiteHbms == null)
        	return null;
        List<RiverSite> riverSites=new ArrayList<RiverSite>();
        for (RiverSiteHbm riverSiteHbm: riverSiteHbms) {
        	RiverSite riverSite = new RiverSite();
        	riverSite.setId(riverSiteHbm.getRiverSiteId());
        	riverSite.setName(riverSiteHbm.getName());
        	riverSite.setMile(riverSiteHbm.getRiverMile());
        	riverSite.setLat(riverSiteHbm.getLatitude());
        	riverSite.setLon(riverSiteHbm.getLongitude());
        	riverSite.setDescription(riverSiteHbm.getDescription());
        	riverSites.add(riverSite);
        }
        
        return riverSites;
		
	}
	
}
