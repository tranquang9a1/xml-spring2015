<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : account.xsl
    Created on : January 5, 2015, 1:47 PM
    Author     : QuangTV
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" omit-xml-declaration="yes"/>
    <xsl:param name="chapter" select="'chapter'" />
    <xsl:variable name="whitespace" select="'&#09;&#10;&#13; '" />
    <xsl:template match="/">
        <table border="1px">

            <xsl:for-each select="//chapter">
                <xsl:if test="name = $chapter">
                    <tr>
                        <xsl:call-template name="SplitString">
                            <xsl:with-param name="String" select="data"/>
                        </xsl:call-template>
                    </tr>
                </xsl:if>

            </xsl:for-each>
        </table>
    </xsl:template>


    <xsl:template name="SplitString">
        <xsl:param name="String"/>
        <!-- how many seperators are there in the string... -->
        <xsl:variable name="countSeperators" select="string-length($String) - string-length(translate($String,'|',''))"/>
        <xsl:variable name="count" select="1"/>
        <xsl:choose>

            <xsl:when test="$countSeperators &gt;= 1">
              <img height="400px" width="200px">
                  <xsl:attribute name="src" >
                          <xsl:value-of select="substring-before($String,'|')" />
                  </xsl:attribute>
              </img>

                <xsl:call-template name="SplitString">
                    <xsl:with-param name="String" select="substring-after($String,'|')"/>
                </xsl:call-template>
            </xsl:when>
        </xsl:choose>

    </xsl:template>



</xsl:stylesheet>
