package it.pagopa.swclient.mil.debtposition.utils;

import io.quarkus.logging.Log;
import jakarta.ws.rs.WebApplicationException;

public class WebAppExcUtils {
	private WebAppExcUtils() {
	}

	public static boolean isUnauthorizedOrForbidden(Throwable failure) {
		Log.debug("Failure inspection");
		if (failure instanceof WebApplicationException webException) {
			return isUnauthorizedOrForbidden(webException);
		} else {
			Log.debugf("Other failure received", failure);
			return false;
		}
	}

	public static boolean isUnauthorizedOrForbidden(WebApplicationException webException) {
		Log.debug("WebApplicationException inspection");
		int status = webException.getResponse().getStatus();
		boolean check = status == 401 || status == 403;
		if (check) {
			Log.debug("Could it be that the access token is invalid?");
			return true;
		} else {
			Log.debugf("HTTP status other than 401 or 403 received: %d", status);
			return false;
		}
	}
}
