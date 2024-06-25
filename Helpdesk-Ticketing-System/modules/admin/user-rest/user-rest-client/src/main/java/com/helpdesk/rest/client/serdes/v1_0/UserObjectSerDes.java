package com.helpdesk.rest.client.serdes.v1_0;

import com.helpdesk.rest.client.dto.v1_0.UserObject;
import com.helpdesk.rest.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author Jatin
 * @generated
 */
@Generated("")
public class UserObjectSerDes {

	public static UserObject toDTO(String json) {
		UserObjectJSONParser userObjectJSONParser = new UserObjectJSONParser();

		return userObjectJSONParser.parseToDTO(json);
	}

	public static UserObject[] toDTOs(String json) {
		UserObjectJSONParser userObjectJSONParser = new UserObjectJSONParser();

		return userObjectJSONParser.parseToDTOs(json);
	}

	public static String toJSON(UserObject userObject) {
		if (userObject == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (userObject.getEmail() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"email\": ");

			sb.append("\"");

			sb.append(_escape(userObject.getEmail()));

			sb.append("\"");
		}

		if (userObject.getFirstName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"firstName\": ");

			sb.append("\"");

			sb.append(_escape(userObject.getFirstName()));

			sb.append("\"");
		}

		if (userObject.getLastName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"lastName\": ");

			sb.append("\"");

			sb.append(_escape(userObject.getLastName()));

			sb.append("\"");
		}

		if (userObject.getScreenName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"screenName\": ");

			sb.append("\"");

			sb.append(_escape(userObject.getScreenName()));

			sb.append("\"");
		}

		if (userObject.getStatusCode() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"statusCode\": ");

			sb.append(userObject.getStatusCode());
		}

		if (userObject.getStatusMessage() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"statusMessage\": ");

			sb.append("\"");

			sb.append(_escape(userObject.getStatusMessage()));

			sb.append("\"");
		}

		if (userObject.getUserId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"userId\": ");

			sb.append(userObject.getUserId());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		UserObjectJSONParser userObjectJSONParser = new UserObjectJSONParser();

		return userObjectJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(UserObject userObject) {
		if (userObject == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (userObject.getEmail() == null) {
			map.put("email", null);
		}
		else {
			map.put("email", String.valueOf(userObject.getEmail()));
		}

		if (userObject.getFirstName() == null) {
			map.put("firstName", null);
		}
		else {
			map.put("firstName", String.valueOf(userObject.getFirstName()));
		}

		if (userObject.getLastName() == null) {
			map.put("lastName", null);
		}
		else {
			map.put("lastName", String.valueOf(userObject.getLastName()));
		}

		if (userObject.getScreenName() == null) {
			map.put("screenName", null);
		}
		else {
			map.put("screenName", String.valueOf(userObject.getScreenName()));
		}

		if (userObject.getStatusCode() == null) {
			map.put("statusCode", null);
		}
		else {
			map.put("statusCode", String.valueOf(userObject.getStatusCode()));
		}

		if (userObject.getStatusMessage() == null) {
			map.put("statusMessage", null);
		}
		else {
			map.put(
				"statusMessage", String.valueOf(userObject.getStatusMessage()));
		}

		if (userObject.getUserId() == null) {
			map.put("userId", null);
		}
		else {
			map.put("userId", String.valueOf(userObject.getUserId()));
		}

		return map;
	}

	public static class UserObjectJSONParser
		extends BaseJSONParser<UserObject> {

		@Override
		protected UserObject createDTO() {
			return new UserObject();
		}

		@Override
		protected UserObject[] createDTOArray(int size) {
			return new UserObject[size];
		}

		@Override
		protected void setField(
			UserObject userObject, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "email")) {
				if (jsonParserFieldValue != null) {
					userObject.setEmail((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "firstName")) {
				if (jsonParserFieldValue != null) {
					userObject.setFirstName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "lastName")) {
				if (jsonParserFieldValue != null) {
					userObject.setLastName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "screenName")) {
				if (jsonParserFieldValue != null) {
					userObject.setScreenName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "statusCode")) {
				if (jsonParserFieldValue != null) {
					userObject.setStatusCode(
						Integer.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "statusMessage")) {
				if (jsonParserFieldValue != null) {
					userObject.setStatusMessage((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "userId")) {
				if (jsonParserFieldValue != null) {
					userObject.setUserId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\": ");

			Object value = entry.getValue();

			Class<?> valueClass = value.getClass();

			if (value instanceof Map) {
				sb.append(_toJSON((Map)value));
			}
			else if (valueClass.isArray()) {
				Object[] values = (Object[])value;

				sb.append("[");

				for (int i = 0; i < values.length; i++) {
					sb.append("\"");
					sb.append(_escape(values[i]));
					sb.append("\"");

					if ((i + 1) < values.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(entry.getValue()));
				sb.append("\"");
			}
			else {
				sb.append(String.valueOf(entry.getValue()));
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}