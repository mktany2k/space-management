<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:camel="http://camel.apache.org/schema/spring"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.5.xsd 
       					   http://camel.apache.org/schema/spring http://camel.apache.org/schema/spring/camel-spring.xsd">

	<#list projects as project><!-- Processor for ${project.name} - ${project.description} -->
	<bean id="processor-${project.projectId}" class="com.scwcd.enterprise.listener.svg.SvgDeploymentProcessor">
		<property name="parser" ref="parser-${project.projectId}"/>
		<property name="projectId" value="${project.projectId}"/>
		<property name="projectPath" value="${projectPath}"/>
		<property name="hashtable">
			<map>
			<#list project.plans as plan>
				<entry key="${plan.filename}" value="${plan.planId}"/>
			</#list>
			</map>
		</property>
	</bean>
	<bean id="parser-${project.projectId}" class="${project.parser}"/>

	</#list><camel:camelContext id="camelContext">
		<#list projects as project>
    	<camel:endpoint id="svgFileListenerEndPoint-${project.projectId}" uri="file://${projectPath}\dat\${project.projectId}\input?delay=5000&amp;move=../processed/${r"${date:now:yyyyMMddHHmmssSSS}-${file:onlyname}"}&amp;moveFailed=../error/${r"${date:now:yyyyMMddHHmmssSSS}-${file:onlyname}"}"/>
		</#list>

		<#list projects as project>
		<!-- Listener route for ${project.name} -->
        <camel:route id="svgFileListenerRoute-${project.projectId}">
            <camel:from ref="svgFileListenerEndPoint-${project.projectId}"/>
            <camel:process ref="processor-${project.projectId}"/>
        </camel:route>

		</#list>
    </camel:camelContext>
</beans>