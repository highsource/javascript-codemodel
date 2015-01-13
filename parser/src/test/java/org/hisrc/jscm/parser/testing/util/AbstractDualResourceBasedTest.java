package org.hisrc.jscm.parser.testing.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractDualResourceBasedTest {

	protected String getSourceResourceNamePattern() {
		return getClass().getSimpleName() + "/{0,number,00}.js";
	}

	protected String getTargetResourceNamePattern() {
		return getClass().getSimpleName() + "/{0,number,00}.txt";
	}

	protected List<ResourceNamePair> getResourceNamePairs() {
		final List<ResourceNamePair> resourceNamePairs = new LinkedList<ResourceNamePair>();
		boolean sourceResourceExists = true;
		boolean targetResourceExists = true;
		int i = -1;
		String sourceResourceName;
		String targetResourceName;
		do {
			i++;
			sourceResourceName = MessageFormat.format(
					getSourceResourceNamePattern(), i);
			targetResourceName = MessageFormat.format(
					getTargetResourceNamePattern(), i);
			sourceResourceExists = isResourceExists(sourceResourceName);
			if (sourceResourceExists) {
				targetResourceExists = isResourceExists(targetResourceName);
				if (!targetResourceExists) {
					throw new AssertionError(
							MessageFormat
									.format("Source resource [{0}] does not have an accompanying target resource [{1}].",
											sourceResourceName,
											targetResourceName));
				}
				resourceNamePairs.add(new ResourceNamePair(sourceResourceName,
						targetResourceName));
			}
		} while (sourceResourceExists);
		if (i == 0)
		{
			Assert.fail("At least one test resource is expected.");
		}
		return new ArrayList<ResourceNamePair>(resourceNamePairs);
	}

	protected boolean isResourceExists(String resourceName) {
		return getResourceURL(resourceName) != null;
	}

	protected URL getResourceURL(String resourceName) {
		return getClass().getResource(resourceName);
	}

	protected String getResourceAsString(String resourceName) {
		try {
			return IOUtils.toString(getResourceURL(resourceName));
		} catch (IOException ioex) {
			throw new AssertionError(ioex);
		}
	}

	protected List<String> getResourceAsLines(String resourceName) {
		final URL resourceURL = getResourceURL(resourceName);
		InputStream is = null;
		try {
			is = resourceURL.openStream();
			return IOUtils.readLines(is);
		} catch (IOException ioex) {
			throw new AssertionError(ioex);
		} finally {
			IOUtils.closeQuietly(is);
		}
	}

	@Test
	public void check() {
		for (ResourceNamePair resourceNamePair : getResourceNamePairs()) {
			check(resourceNamePair.getSourceResourceName(),
					resourceNamePair.getTargetResourceName());
		}
	}

	protected abstract void check(String sourceResourceName,
			String targetResourceName);
}
