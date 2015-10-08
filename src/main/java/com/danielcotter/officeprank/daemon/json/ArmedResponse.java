package com.danielcotter.officeprank.daemon.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArmedResponse {

	Boolean armed;

	/**
	 * @return the armed
	 */
	public Boolean getArmed() {
		return armed;
	}

	/**
	 * @param armed
	 *            the armed to set
	 */
	public void setArmed(Boolean armed) {
		this.armed = armed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((armed == null) ? 0 : armed.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ArmedResponse))
			return false;
		ArmedResponse other = (ArmedResponse) obj;
		if (armed == null) {
			if (other.armed != null)
				return false;
		} else if (!armed.equals(other.armed))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ArmedResponse [armed=" + armed + "]";
	}
}
