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
    <xsl:variable name="whitespace" select="'&#09;&#10;&#13;%0A '" />
    <xsl:template match="/">
        <table border="1px">

            <xsl:for-each select="//chapter">
                <xsl:if test="name = $chapter">
                    <tr>
                        <xsl:call-template name="SplitLinks">
                            <xsl:with-param name="pLinks" select="data"/>
                        </xsl:call-template>
                    </tr>
                </xsl:if>

            </xsl:for-each>
        </table>
    </xsl:template>


    <xsl:template name="SplitLinks">
        <xsl:param name="pLinks"/>
        <!-- how many seperators are there in the string... -->
        <xsl:variable name="vCountSeperators" select="string-length($pLinks) - string-length(translate($pLinks,'|',''))"/>
        <xsl:variable name="count" select="1"/>
        <xsl:choose>

            <xsl:when test="$vCountSeperators &gt;= 1">
              <img height="400px" width="200px">
                  <xsl:attribute name="src" >
                      <xsl:call-template name="string-rtrim">
                          <xsl:with-param name="string" select="substring-before($pLinks,'|')" />
                      </xsl:call-template>
                  </xsl:attribute>
              </img>

                <xsl:call-template name="SplitLinks">
                    <xsl:with-param name="pLinks" select="substring-after($pLinks,'|')"/>
                </xsl:call-template>
            </xsl:when>
        </xsl:choose>

    </xsl:template>

    <xsl:template name="string-rtrim">
        <xsl:param name="string" />
        <xsl:param name="trim" select="$whitespace" />

        <xsl:variable name="length" select="string-length($string)" />

        <xsl:if test="$length &gt; 0">
            <xsl:choose>
                <xsl:when test="contains($trim, substring($string, $length, 1))">
                    <xsl:call-template name="string-rtrim">
                        <xsl:with-param name="string" select="substring($string, 1, $length - 1)" />
                        <xsl:with-param name="trim"   select="$trim" />
                    </xsl:call-template>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:value-of select="$string" />
                </xsl:otherwise>
            </xsl:choose>
        </xsl:if>
    </xsl:template>

</xsl:stylesheet>
