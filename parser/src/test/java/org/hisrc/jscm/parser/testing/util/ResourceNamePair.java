package org.hisrc.jscm.parser.testing.util;

public class ResourceNamePair {

	private final String sourceResourceName;
	private final String targetResourceName;

	public ResourceNamePair(String sourceResourceName, String targetResourceName) {
		this.sourceResourceName = sourceResourceName;
		this.targetResourceName = targetResourceName;
	}

	public String getSourceResourceName() {
		return sourceResourceName;
	}

	public String getTargetResourceName() {
		return targetResourceName;
	}

}
