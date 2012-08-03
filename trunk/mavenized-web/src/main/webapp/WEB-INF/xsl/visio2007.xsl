<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<xsl:stylesheet version="1.0" 
				xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
				xmlns:svg="http://www.w3.org/2000/svg" 
				xmlns:xlink="http://www.w3.org/1999/xlink" 
				xmlns:fn="xalan://com.scwcd.framework.deployment.parser.xslt.XsltFunction" 
				xmlns:l="xalan://com.scwcd.enterprise.sql.hbm.Lot" 
				exclude-result-prefixes="xsl svg xlink">

	<xsl:output omit-xml-declaration="yes" 
				version="1.0" 
				method="xml" 
				indent="no" 
				standalone="no"/>

	<xsl:variable name="xFn" select="fn:new()" />
	<xsl:param name="projectId"/>
	<xsl:param name="planId"/>

	<xsl:template match="/">
		<xsl:apply-templates select="svg:svg"/>
	</xsl:template>
	
	<xsl:template match="svg:svg">
		<xsl:element name="svg" namespace="{namespace-uri()}">
			<xsl:attribute name="id"><xsl:value-of select="fn:getFloorId($projectId, $planId)"/></xsl:attribute>
			<xsl:attribute name="version"><xsl:value-of select="'1.0'"/></xsl:attribute>
			<xsl:attribute name="zoomAndPan"><xsl:value-of select="'magnify'"/></xsl:attribute>
			<xsl:attribute name="color-interpolation-filters"><xsl:value-of select="'sRGB'"/></xsl:attribute>
			<xsl:attribute name="contentScriptType"><xsl:value-of select="'text/ecmascript'"/></xsl:attribute>
			<xsl:attribute name="contentStyleType"><xsl:value-of select="'text/css'"/></xsl:attribute>
			<xsl:attribute name="width"><xsl:value-of select="'100%'"/></xsl:attribute>
			<xsl:attribute name="height"><xsl:value-of select="'100%'"/></xsl:attribute>
			<xsl:attribute name="viewBox"><xsl:value-of select="'0 0 2400 1400'"/></xsl:attribute>
			<xsl:attribute name="class"><xsl:value-of select="fn:getCss(@class, $projectId, $planId)"/></xsl:attribute>
			<xsl:element name="style" namespace="{namespace-uri()}">
				<xsl:attribute name="type"><xsl:value-of select="svg:style/@type"/></xsl:attribute>
				<xsl:value-of select="fn:processCss(svg:style/text(), $projectId, $planId)" disable-output-escaping="yes"/>
			</xsl:element>
			<xsl:apply-templates select="svg:g"/>
		</xsl:element>
	</xsl:template>

	<xsl:template match="svg:g">
		<xsl:element name="g" namespace="{namespace-uri()}">
			<xsl:call-template name="applyGAttr"/>
			<xsl:apply-templates select="svg:rect"/>
			<xsl:apply-templates select="svg:path"/>
			<xsl:apply-templates select="svg:g"/>
		</xsl:element>
	</xsl:template>
	<xsl:template name="applyGAttr">
		<xsl:if test="@id != '' and @id != starts-with(@id, 'shape')">
			<xsl:attribute name="id"><xsl:value-of select="@id"/></xsl:attribute>
			<!--
			<xsl:choose>
				<xsl:when test="svg:g/svg:rect/@class = 'st1'">
					<xsl:attribute name="id"><xsl:value-of select="fn:getPlotId($projectId, $planId, id:new())"/></xsl:attribute>
				</xsl:when>
				<xsl:otherwise>
					<xsl:attribute name="id"><xsl:value-of select="@id"/></xsl:attribute>
				</xsl:otherwise>
			</xsl:choose>
			-->
		</xsl:if>
		<xsl:if test="@transform != ''">
			<xsl:attribute name="transform"><xsl:value-of select="@transform"/></xsl:attribute>
		</xsl:if>
	</xsl:template>

	<xsl:template match="svg:rect">
		<xsl:element name="rect" namespace="{namespace-uri()}">
			<xsl:call-template name="applyRectAttr"/>
			<xsl:if test="@class = 'st1'">
				<xsl:call-template name="applyLotAttr"/>
			</xsl:if>
		</xsl:element>
	</xsl:template>
	<xsl:template name="applyRectAttr">
		<xsl:attribute name="x"><xsl:value-of select="@x"/></xsl:attribute>
		<xsl:attribute name="y"><xsl:value-of select="@y"/></xsl:attribute>
		<xsl:attribute name="width"><xsl:value-of select="@width"/></xsl:attribute>
		<xsl:attribute name="height"><xsl:value-of select="@height"/></xsl:attribute>
		<xsl:attribute name="class"><xsl:value-of select="fn:getCss(@class, $projectId, $planId)"/></xsl:attribute>
	</xsl:template>

	<xsl:template match="svg:path">
		<xsl:element name="path" namespace="{namespace-uri()}">
			<xsl:call-template name="applyPathAttr"/>
			<xsl:if test="@class = 'st1'">
				<xsl:call-template name="applyLotAttr"/>
			</xsl:if>
		</xsl:element>
	</xsl:template>
	<xsl:template name="applyPathAttr">
		<xsl:attribute name="d"><xsl:value-of select="@d"/></xsl:attribute>
		<xsl:attribute name="class"><xsl:value-of select="fn:getCss(@class, $projectId, $planId)"/></xsl:attribute>
	</xsl:template>

	<xsl:template name="applyLotAttr">
		<xsl:variable name="lot" select="fn:generate($xFn, $projectId, $planId)" />
		<xsl:attribute name="id"><xsl:value-of select="fn:getLotId($lot)"/></xsl:attribute>
		<xsl:attribute name="l-desc"><xsl:value-of select="l:getDescription($lot)"/></xsl:attribute>
		<xsl:attribute name="l-title"><xsl:value-of select="l:getName($lot)"/></xsl:attribute>
		<xsl:attribute name="onclick"><xsl:text>javascript: selectSvgLot(evt, this);</xsl:text></xsl:attribute>
		<xsl:attribute name="onmouseout"><xsl:text>javascript: hoverSvgLot(evt, this);</xsl:text></xsl:attribute>
		<xsl:attribute name="onmouseover"><xsl:text>javascript: hoverSvgLot(evt, this);</xsl:text></xsl:attribute>
		<xsl:attribute name="style"><xsl:text>cursor: pointer;</xsl:text></xsl:attribute>
	</xsl:template>
</xsl:stylesheet>