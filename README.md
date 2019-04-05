# Java Coding Challenge - Word-Art API

Recently I was given a Java Coding Challenge which let me demonstrate my ability
and as talking point in a further interview. The challenge required an API
to be build that uses HTTP to allow customers to request and fetch their images
using the word art library. A basic maven build script was supplied that can build
a WAR file and run the application with the jetty plugin.

# Requirements

Your API, which you are free to design as you wish [1], must be able to accept a
string of text, along with formatting parameters, and be able to serve up the
generated images as PNG or JPEG images.

1 - Information about the formatting parameters can be found in the word art
    generator API javadocs

# ImageMagick

The given conversion code relies on the popular imagemagick library being installed
and available on your system's path.  You can check it is installed correctly by
running `which convert`.
Reference: https://www.imagemagick.org

# Solution

One endpoint has been created which accepts a string of text along with the formatting
parameters, font, fontColour, effects & format and will download the image in the
format specified in the request. The application is using the Spring Boot framework. There is
some test coverage that has been completed but there is still work to do in regards to this.

# API

Definition:
GET /wordart?text=Hello%20World&format=PNG&fontColour=RED&font=ARIAL&effects=SHADOW_HARD

### Parameters:
text: free text up to 20 characters long.
      Alphanumeric characters are only allowed with the following additional characters: !%$'()*? space.
format: PNG, JPEG, TIFF & ASCII.
font: ARIAL or FREE_MONO.
effects: SHADOW_HARD, SHADOW_REFLECT, GRADIENT, OUTLINE.

### Example Request:
GET /wordart?text=Hello World&format=PNG&fontColour=RED&font=ARIAL&effects=SHADOW_HARD

### Example Response:
A download of the image that was generated.

### Running
The application runs on port 8080. It runs on the Jetty plugin and builds
a WAR file.
Alternatively, you can run through IDE using the Application.java as the main class.
There is also a Postman collection which you might also find useful.

# Code Features

- Single GET endpoint allowing you to alter four different parameters to alter the image returned.
- Validation on incoming parameters/payload.
- Initial start on JUnit/Integration testing.
- Compressing the outgoing payload with GZIP.
- Initial strategy to handle exceptions so there is an consistent responses across all errors.
- Use of builder design pattern

# Possible Improvements

- Loading in available formats, fonts, colours and effects so they are not hard-coded.
- Test Coverage needs improving