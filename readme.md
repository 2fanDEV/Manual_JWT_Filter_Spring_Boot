# JWT Manual Filter

This project was created due to the curiosity on 
how the .oauth2ResourceServer configuration in 
the FilterChain possibly works. 

To explain: 

The resource server grabs the JWK Set from the Spring Authorization Server
and validates incoming Token via a self written Filter which 
is then configured to be before the BearerAuthenticationFilter.