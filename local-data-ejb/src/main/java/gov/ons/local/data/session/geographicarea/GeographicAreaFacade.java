package gov.ons.local.data.session.geographicarea;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import gov.ons.local.data.entity.DataResource;
import gov.ons.local.data.entity.GeographicArea;
import gov.ons.local.data.entity.GeographicLevelType;
import gov.ons.local.data.session.AbstractFacade;

public class GeographicAreaFacade extends AbstractFacade<GeographicArea>
{
	@PersistenceContext(unitName = "LocalDataEJB")
	private EntityManager em;

	private Logger logger = Logger
			.getLogger(GeographicAreaFacade.class.getSimpleName());

	public GeographicAreaFacade()
	{
		super(GeographicArea.class);
	}

	@Override
	protected EntityManager getEntityManager()
	{
		return em;
	}

	public GeographicArea findByExtCodeLevel(DataResource dataResource,
			GeographicLevelType geographicLevelType, String extCode)
	{
		logger.log(Level.INFO,
				"findByExtCodeLevel: dataResource = "
						+ dataResource.getDataResource() + " geographicLevelType = "
						+ geographicLevelType.getGeographicLevelType() + " extCode = "
						+ extCode);

		GeographicArea geographicArea = (GeographicArea) getEntityManager()
				.createNamedQuery("GeographicArea.findByExtCodeLevel")
				.setParameter("dataResource", dataResource)
				.setParameter("extCode", extCode)
				.setParameter("geographicLevelType", geographicLevelType)
				.getSingleResult();

		return geographicArea;
	}
}
