package book.servicewrapper;

import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectEntryServiceWrapper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;

import java.io.Serializable;
import java.util.Map;
import java.util.regex.Pattern;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jatin
 */
@Component(immediate = true, property = {}, service = ServiceWrapper.class)
public class BookServiceWrapper extends ObjectEntryServiceWrapper {

	private static final Log _log = LogFactoryUtil.getLog(BookServiceWrapper.class);

	public BookServiceWrapper() {
		super(null);
	}

	@Override
	public ObjectEntry addObjectEntry(long groupId, long objectDefinitionId, Map<String, Serializable> values,
			ServiceContext serviceContext) throws PortalException {
		long targetObjectDefinitionId = 53415;
		
		//Check Condition For particular Object Entry data printed in Console
		if (objectDefinitionId == targetObjectDefinitionId) {
			ObjectEntry objectEntry = super.addObjectEntry(groupId, objectDefinitionId, values, serviceContext);
			_log.info("Book Service Wrapper  Detail : " + objectEntry.getValues().toString());
			return objectEntry;
		} else {
			return super.addObjectEntry(groupId, objectDefinitionId, values, serviceContext);
		}

	}

}