<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                version="1.0">
  <xsl:output method="xml" indent="yes"/>

  <!-- o template abaixo copia o XML inteiro   -->
  <!-- @* - seleciona todos os atributos -->
  <!-- node() - seleciona todos os nodes  -->
  <xsl:template match="@*|node()">
    <xsl:copy>
      <xsl:apply-templates select="@*|node()"/>
    </xsl:copy>
  </xsl:template>
  
  
  <!-- o template abaixo adiciona o elemento tokenUsuario se o Header já existe  -->
  <xsl:template match="*:Header">
    <xsl:copy>

      <!-- adicione elemento abaixo ao header -->
      <aut:tokenUsuario xmlns:aut="http://caelum.com.br/autorizacao">TOKEN123</aut:tokenUsuario>
	  
      <xsl:apply-templates select="@*|node()"/>

    </xsl:copy>
  </xsl:template>


 <!-- o template abaixo adiciona um Header e o elemento tokenUsuario se nao existe o Header ainda  -->
  <xsl:template match="*:Envelope[not(*:Header)]">
      <xsl:copy>
      <soapenv:Header >
        <aut:tokenUsuario xmlns:aut="http://caelum.com.br/autorizacao">TOKEN123</aut:tokenUsuario>
      </soapenv:Header>
        <xsl:apply-templates select="@*|node()" />
    </xsl:copy>
  </xsl:template>

</xsl:stylesheet>