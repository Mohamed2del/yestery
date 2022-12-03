<h1>Picture Publishing Service</h1>

<p> The client needs a website where registered and logged in users can upload pictures for
acceptance or rejection. An administrator will login and review all submissions.
Accepted pictures will be assigned a URL which can be accessed by all users (without login).
Rejected pictures have their pictures removed but the metadata remains.</p>

<h2>Database used : H2 SQL</h2>
<h2> Build through maven : </h2>
<h3>Navigate to the root of the project via command line and execute the command mvn spring-boot:run
</h3>

<h2>Swagger UI http://localhost:8080/swagger-ui/</h2>

<H3>Important note : Login API returns UserId , you must put userId at Headers for Authorization</H3>

<H2>Future Mandatory Changes in the project : </H2>
<H3>Use JWT for Authentication and Authorization</H3>
