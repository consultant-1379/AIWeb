package test.integration.ejb;

import javax.ejb.EJB;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import test.integration.Artifact;

import com.ericsson.nms.security.api.Service;
import com.ericsson.nms.security.ejb.ServiceBean;
import com.ericsson.nms.security.ejb.ServiceLocal;
import com.ericsson.nms.security.impl.DateResolverBean;

/*
 * Arquillian test - Injecting EJB, Creating Archives and Libraries
 */

@RunWith(Arquillian.class)
public class ServiceBeanTest {

    @EJB
    private ServiceLocal serviceBean;

    @Deployment(name = "ServiceBeanEar")
    public static Archive<?> createTestArchive() {
	final MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class)
		.loadMetadataFromPom("pom.xml");
	final EnterpriseArchive archive = ShrinkWrap
		.create(EnterpriseArchive.class, ServiceBeanTest.class.getSimpleName() + ".ear")
		.addAsModule(createModuleArchive())
		.addAsLibraries(resolver.artifact(Artifact.COM_ERICSSON_OSS_ITPF_SDK___SERVICES_CORE_JAR).resolveAsFiles())
		.addAsLibraries(resolver.artifact(Artifact.COM_ERICSSON_OSS_ITPF_SDK___CONFIG_API_JAR).resolveAsFiles())
		.addAsLibraries(resolver.artifact(Artifact.COM_ERICSSON_OSS_ITPF_SDK___CONFIG_CORE_JAR).resolveAsFiles())
		.addAsLibraries(resolver.artifact(Artifact.COM_ERICSSON_OSS_ITPF_SDK___CONFIG_CACHE_NON_CDI_JAR).resolveAsFiles())		
		.addAsManifestResource("test-channels-jms.xml")
		.addAsLibrary(createLibraryArchive());
	return archive;
    }

    private static Archive<?> createModuleArchive() {
	return ShrinkWrap.create(JavaArchive.class, "service-bean-test-ejb.jar")
		.addAsResource("META-INF/beans.xml", "META-INF/beans.xml")
		.addPackage(ServiceLocal.class.getPackage().getName()).addClass(ServiceLocal.class)
		.addPackage(ServiceBean.class.getPackage().getName()).addClass(ServiceBean.class);
    }

    private static Archive<?> createLibraryArchive() {
	return ShrinkWrap.create(JavaArchive.class, "service-bean-test-lib.jar")
		.addAsResource("META-INF/beans.xml", "META-INF/beans.xml")
		.addPackage(Service.class.getPackage().getName()).addClass(ServiceBeanTest.class)
		.addPackage(DateResolverBean.class.getPackage().getName()).addClass(DateResolverBean.class);		
    }

    /*
     * To Test serviceBean
     */
    @Test
    public void test() {
    	Assert.assertNotNull("ServiceBean should not be null", this.serviceBean);
    	Assert.assertNotNull("Resolved date should not be null", this.serviceBean.resolveDate());
    }

}
