# JWT Manual Filter

This project was created due to the curiosity on 
how the .oauth2ResourceServer configuration in 
the FilterChain possibly works and how I would
be able to recreate this behaviour.

To explain: 

The resource server grabs the JWK Set from the Spring Authorization Server
and validates incoming Token via a self written Filter which 
is then configured to be before the BearerAuthenticationFilter.

Also both of the resource server retrieve an access token at startup.

# Authorization Server

The authorization server is done with the example from the getting started documentation
https://docs.spring.io/spring-authorization-server/docs/current/reference/html/getting-started.html

This configuration does enable the endpoints /oauth2/token, /oauth2/authorize and /oauth2/jwks
which will be eventually used in this sample.
