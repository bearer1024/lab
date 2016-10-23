<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<body>
				<h3>
					<xsl:value-of select="course/teacher/name/text()"/>
				</h3>
				<h4>Student</h4>
				<table border="1">
					<tr bgcolor="yellow">
						<th style="text-align:left">Name</th>
						<th style="text-align:left">ID</th>
						<th style="text-align:right">CW1</th>
						<th style="text-align:right">Project</th>
						<th style="text-align:right">Final</th>
					</tr>
					<xsl:for-each select="course/student">
						<tr>
							<td>
								<xsl:value-of select="name"/>
							</td>
							<td>
								<xsl:value-of select="@id"/>
							</td>
							<td>
								<xsl:value-of select="cw1"/>
							</td>
							<td>
								<xsl:value-of select="project"/>
							</td>
							<td>
								<xsl:value-of select="final"/>
							</td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>