<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : chapterFO.xsl
    Created on : February 5, 2015, 8:42 PM
    Author     : QuangTV
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <xsl:output method="xml"/>
    <xsl:param name="pathFile" select="data" />
    <xsl:param name="story" select="'story'" />
    <xsl:param name="chapterName" select="'chapterName'" />

    <xsl:param name="pText" select="."/>
    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="x" page-height="8.5in" page-width="11in"
                                       margin-top="0.5in" margin-right="0.5in" margin-left="0.5in" margin-bottom="0.5in">
                    <fo:region-body margin-top="0.5in" />
                    <fo:region-before extent="0.5in" />
                    <fo:region-after extent="0.5in" />
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="x">
                <fo:static-content flow-name="xsl-region-before">
                    <fo:block font-size="14pt" font-family="Arial" line-height="24pt"
                              background-color="cyan" space-after.optimum="15pt" text-align="center"
                            >
                        Story:  <xsl:value-of select="$story" /> -  Chapter: <xsl:value-of select="$chapterName" /> - Copyright: Group 3 - XML
                    </fo:block>
                </fo:static-content>

                <fo:flow flow-name="xsl-region-body">
                    <fo:block>
                        <fo:table border-collapse="separate" table-layout="fixed">
                            <fo:table-body>
                                <xsl:for-each select="//chapter">
                                    <xsl:if test="name = $chapterName">
                                        <fo:table-row>
                                            <fo:table-cell>
                                                <fo:block text-align="center">
                                                    <xsl:call-template name="SplitLinks">
                                                        <xsl:with-param name="pLinks" select="data"/>
                                                    </xsl:call-template>

                                                </fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </xsl:if>
                                </xsl:for-each>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>


    <xsl:template name="SplitLinks">
        <xsl:param name="pLinks"/>
        <!-- how many seperators are there in the string... -->
        <xsl:variable name="vCountSeperators" select="string-length($pLinks) - string-length(translate($pLinks,'|',''))"/>
        <xsl:choose>

            <xsl:when test="$vCountSeperators &gt;= 1">

                <fo:block text-align="center">
                    <fo:external-graphic content-height="scale-to-fit" height="500px"  content-width="500px" scaling="non-uniform">
                        <xsl:attribute name = "src">
                            <xsl:value-of select="substring-before($pLinks,'|')"/>
                        </xsl:attribute>
                    </fo:external-graphic>

                </fo:block>

                <xsl:call-template name="SplitLinks">
                    <xsl:with-param name="pLinks" select="substring-after($pLinks,'|')"/>
                </xsl:call-template>
            </xsl:when>
        </xsl:choose>

    </xsl:template>



</xsl:stylesheet>
