package com.helpdesk.rest.client.serdes.v1_0;

import com.helpdesk.rest.client.dto.v1_0.NewUser;
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
public class NewUserSerDes {

	public static NewUser toDTO(String json) {
		NewUserJSONParser newUserJSONParser = new NewUserJSONParser();

		return newUserJSONParser.parseToDTO(json);
	}

	public static NewUser[] toDTOs(String json) {
		NewUserJSONParser newUserJSONParser = new NewUserJSONParser();

		return newUserJSONParser.parseToDTOs(json);
	}

	public static String toJSON(NewUser newUser) {
		if (newUser == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (newUser.getEmail() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"email\": ");

			sb.append("\"");

			sb.append(_escape(newUser.getEmail()));

			sb.append("\"");
		}

		if (newUser.getFirstName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"firstName\": ");

			sb.append("\"");

			sb.append(_escape(newUser.getFirstName()));

			sb.append("\"");
		}

		if (newUser.getLastName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"lastName\": ");

			sb.append("\"");

			sb.append(_escape(newUser.getLastName()));

			sb.append("\"");
		}

		if (newUser.getPassword1() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"password1\": ");

			sb.append("\"");

			sb.append(_escape(newUser.getPassword1()));

			sb.append("\"");
		}

		if (newUser.getPassword2() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"password2\": ");

			sb.append("\"");

			sb.append(_escape(newUser.getPassword2()));

			sb.append("\"");
		}

		if (newUser.getRole() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"role\": ");

			sb.append("\"");

			sb.append(_escape(newUser.getRole()));

			sb.append("\"");
		}

		if (newUser.getScreenName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"screenName\": ");

			sb.append("\"");

			sb.append(_escape(newUser.getScreenName()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		NewUserJSONParser newUserJSONParser = new NewUserJSONParser();

		return newUserJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(NewUser newUser) {
		if (newUser == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (newUser.getEmail() == null) {
			map.put("email", null);
		}
		else {
			map.put("email", String.valueOf(newUser.getEmail()));
		}

		if (newUser.getFirstName() == null) {
			map.put("firstName", null);
		}
		else {
			map.put("firstName", String.valueOf(newUser.getFirstName()));
		}

		if (newUser.getLastName() == null) {
			map.put("lastName", null);
		}
		else {
			map.put("lastName", String.valueOf(newUser.getLastName()));
		}

		if (newUser.getPassword1() == null) {
			map.put("password1", null);
		}
		else {
			map.put("password1", String.valueOf(newUser.getPassword1()));
		}

		if (newUser.getPassword2() == null) {
			map.put("password2", null);
		}
		else {
			map.put("password2", String.valueOf(newUser.getPassword2()));
		}

		if (newUser.getRole() == null) {
			map.put("role", null);
		}
		else {
			map.put("role", String.valueOf(newUser.getRole()));
		}

		if (newUser.getScreenName() == null) {
			map.put("screenName", null);
		}
		else {
			map.put("screenName", String.valueOf(newUser.getScreenName()));
		}

		return map;
	}

	public static class NewUserJSONParser extends BaseJSONParser<NewUser> {

		@Override
		protected NewUser createDTO() {
			return new NewUser();
		}

		@Override
		protected NewUser[] createDTOArray(int size) {
			return new NewUser[size];
		}

		@Override
		protected void setField(
			NewUser newUser, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "email")) {
				if (jsonParserFieldValue != null) {
					newUser.setEmail((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "firstName")) {
				if (jsonParserFieldValue != null) {
					newUser.setFirstName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "lastName")) {
				if (jsonParserFieldValue != null) {
					newUser.setLastName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "password1")) {
				if (jsonParserFieldValue != null) {
					newUser.setPassword1((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "password2")) {
				if (jsonParserFieldValue != null) {
					newUser.setPassword2((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "role")) {
				if (jsonParserFieldValue != null) {
					newUser.setRole((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "screenName")) {
				if (jsonParserFieldValue != null) {
					newUser.setScreenName((String)jsonParserFieldValue);
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