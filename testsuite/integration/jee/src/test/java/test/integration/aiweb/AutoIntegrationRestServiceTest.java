package test.integration.aiweb;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import junit.framework.Assert;

import org.jboss.arquillian.container.test.api.*;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class AutoIntegrationRestServiceTest {

	private static String contextRoot = "autobind";
	private static final String SERVER = "jboss_managed";

	@Deployment(testable = false)
	@TargetsContainer(SERVER)
	public static Archive<?> createDeployment() {

		File earDir = new File("../../../AIWeb-ear/target/");
		File earFile = null;
		EnterpriseArchive archive = null;

		for (File item : earDir.listFiles()) {
			if (item.isFile() && item.getName().endsWith(".ear")) {
				earFile = item;
				try {
					archive = ShrinkWrap.createFromZipFile(
							EnterpriseArchive.class, earFile);
				} catch (Throwable ex) {
					ex.printStackTrace();
				}
			}
		}

		if (earFile == null) {
			System.err.println("\nearFile is " + earFile + " and does not exist.\n");
		}
		return archive;
	}

	@Deployment(name = "warTest")
	@TargetsContainer(SERVER)
	public static Archive<?> createTestArchive() {
		WebArchive archive = ShrinkWrap
				.create(WebArchive.class, "AIWebWarTest.war")
				.addAsWebInfResource("META-INF/beans.xml")
				.addClass(AutoIntegrationRestServiceTest.class);		
		return archive;
	}

	@Test
	@RunAsClient
	public void getConfigFile() throws IOException {

		String url = "http://localhost:8080/" + contextRoot + "/C825474982kkkk";		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");

		Assert.assertEquals(
				"The HTTP request has received non-OK server response code.",
				200, con.getResponseCode());

		BufferedReader in = null;
		in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		con.disconnect();
		in.close();

		System.out.println("\n\n\t" + response.toString() + "\n");
	}
}
