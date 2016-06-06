/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 */
package com.phoenixnap.oss.ramlapisync.plugin;

import java.io.File;
import java.time.LocalDateTime;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.Mojo;
import org.apache.maven.plugin.MojoExecution;
import org.apache.maven.plugin.testing.MojoRule;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.classworlds.ClassWorld;
import org.codehaus.plexus.classworlds.realm.ClassRealm;
import org.codehaus.plexus.component.configurator.ComponentConfigurationException;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class PojoGenerationConfigTest
{
   private static final String GOAL_NAME = "generate-springmvc-endpoints";
   private static final String DEFAULT_CONFIG = "default-config";

   @Rule
   public MojoRule mojoRule = new MojoRule();

   @Test
   public void testDefaultConfig() throws Exception {
      final SpringMvcEndpointGeneratorMojo mojo = (SpringMvcEndpointGeneratorMojo)loadMojo(DEFAULT_CONFIG, GOAL_NAME);
      final PojoGenerationConfig generationConfig = mojo.generationConfig;
      Assert.assertNotNull(generationConfig);
      Assert.assertEquals("1.6", generationConfig.getTargetVersion());
      Assert.assertFalse(generationConfig.isUseBigDecimals());
      Assert.assertFalse(generationConfig.isUseDoubleNumbers());
      Assert.assertFalse(generationConfig.isUseLongIntegers());
      Assert.assertFalse(generationConfig.isUsePrimitives());
      Assert.assertFalse(generationConfig.isUseCommonsLang3());
      Assert.assertFalse(generationConfig.isGenerateBuilders());
      Assert.assertTrue(generationConfig.isIncludeAccessors());
      Assert.assertTrue(generationConfig.isIncludeAdditionalProperties());
      Assert.assertFalse(generationConfig.isIncludeConstructors());
      Assert.assertFalse(generationConfig.isConstructorsRequiredPropertiesOnly());
      Assert.assertTrue(generationConfig.isIncludeHashcodeAndEquals());
      Assert.assertTrue(generationConfig.isIncludeToString());
      Assert.assertTrue(generationConfig.isInitializeCollections());
      mojo.execute();
   }

   private File getPomDir(final String pomDir){
      final File pom = new File("src/test/resources/" + pomDir);
      Assert.assertNotNull(pom);
      Assert.assertTrue(pom.exists());
      return pom;
   }

   private Mojo loadMojo(final String pomDir, final String goalName) throws Exception {
      final MavenProject mavenProject = getMavenProject(pomDir);
      final Mojo mojo = mojoRule.lookupConfiguredMojo(mavenProject, goalName);
      Assert.assertNotNull(mojo);
      return mojo;
   }

   private MavenProject getMavenProject(final String pomDir) throws Exception {
      final File pom = getPomDir(pomDir);

      final MavenProject mavenProject = mojoRule.readMavenProject(pom);
      final ClassWorld classWorld = new ClassWorld();
      final ClassRealm realm = classWorld.newRealm("test", getClass().getClassLoader() );
      mavenProject.setClassRealm(realm);

      return mavenProject;
   }

   @Test
   public void testBigDecimalConfig() throws Exception {
      final SpringMvcEndpointGeneratorMojo mojo =
         (SpringMvcEndpointGeneratorMojo) configureMojo("useBigDecimals", Boolean.TRUE.toString());
      final PojoGenerationConfig pojoGenerationConfig = mojo.generationConfig;
      Assert.assertNotNull(pojoGenerationConfig);
      Assert.assertTrue(pojoGenerationConfig.useBigDecimals);
   }

   @Test
   public void testLocalDateTimeConfig() throws Exception {
      final SpringMvcEndpointGeneratorMojo mojo =
         (SpringMvcEndpointGeneratorMojo) configureMojo("dateTimeType", LocalDateTime.class.getName());
      final PojoGenerationConfig pojoGenerationConfig = mojo.generationConfig;
      Assert.assertNotNull(pojoGenerationConfig);
      Assert.assertEquals(LocalDateTime.class.getName(), pojoGenerationConfig.getDateTimeType());
   }

   @Test(expected = ComponentConfigurationException.class)
   public void testUnknownParameterConfig() throws Exception {
      final SpringMvcEndpointGeneratorMojo mojo =
         (SpringMvcEndpointGeneratorMojo) configureMojo("unknown", "invalid");
   }

   private Mojo configureMojo(final String parameter, final String value) throws Exception {
      final MavenSession mavenSession = mojoRule.newMavenSession(getMavenProject(DEFAULT_CONFIG));
      final MojoExecution mojoExecution = mojoRule.newMojoExecution(GOAL_NAME);
      final Xpp3Dom configuration = new Xpp3Dom("configuration");
      final Xpp3Dom generationConfig = new Xpp3Dom("generationConfig");
      final Xpp3Dom useBigDecimal = new Xpp3Dom(parameter);
      useBigDecimal.setValue(value);
      generationConfig.addChild(useBigDecimal);

      configuration.addChild(generationConfig);
      mojoExecution.setConfiguration(configuration);

      return mojoRule.lookupConfiguredMojo(mavenSession, mojoExecution);
   }
}
