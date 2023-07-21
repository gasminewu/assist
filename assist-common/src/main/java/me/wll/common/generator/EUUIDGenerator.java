package me.wll.common.generator;

import java.io.Serializable;

import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerator;

import me.wll.common.utils.UuidUtils;

public class EUUIDGenerator extends UUIDGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws MappingException {
		Serializable id = session.getEntityPersister(null, object).getClassMetadata().getIdentifier(object, session);
		if (id == null || "".equals(id)) {
			id = UuidUtils.uuid();
		}
		return id;
	}

}

