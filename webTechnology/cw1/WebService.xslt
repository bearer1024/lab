<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <h3>
                    <xsl:value-of select="interface/@name"/>
                </h3>
                <table border="1">
                    <tr bgcolor="green">
                        <th style="text-align:left">Method</th>
                        <th style="text-align:left">Return type</th>
                        <th style="text-align:left">Input parameters</th>
                    </tr>
                    <xsl:for-each select="interface/abstract_method">
                        <tr>
                            <td>
                                <b><xsl:value-of select="@name"/></b>
                            </td>
                            <td>
                                <xsl:value-of select="return"/>
                            </td>
                            <td>
                                <xsl:for-each select="./arguments/parameter">
                                    <xsl:value-of select="."/>:
                                    <xsl:value-of select="./@type"/>
                                    <xsl:if test="not(position()=last())">,   
                                    </xsl:if>   
                                </xsl:for-each>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>