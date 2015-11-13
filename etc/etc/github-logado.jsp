<html>
  <body>
      Código de Autorização: ${authToken}
      <br>
      AccessToken (Authorization:Bearer): ${accessToken}
      <br><br>
      Cole no campo abaixo o AccessToken para testar o request autorizado.
      <br><br>
      <form action="githubemails">
          <label for="token">AccessToken:</label>
          <input type="text" name="accessToken">
          <input type="submit" value="Enviar Request ao Github">
      </form>
      <br><br>
      ${responseBody}
  </body>
</html>