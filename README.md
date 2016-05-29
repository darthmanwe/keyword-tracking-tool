[![Build Status](https://travis-ci.org/salvatorenovelli/keyword-tracking-tool.svg?branch=master)](https://travis-ci.org/salvatorenovelli/keyword-tracking-tool)
# Keyword Tracking Tool
This tool make use of the [Search Console API](https://developers.google.com/webmaster-tools/) to retrieve position index for kewyword.


____

## Usage:
At the current state this is more of a tool to investigate the usage of the google API. To use it you will need to:
- clone the repository
- add your `auth.json` to the root resource folder `src/main/resources` [How to get auth.json](https://developers.google.com/identity/protocols/OAuth2ServiceAccount)
- edit `src/main/resources/config/application.yml` and replace `www.example.com` with your website root
- package it with `mvn clean package`
- and run `java -jar target/keyword-tracking-tool-<version>.jar`

After running it, you should be able to retrieve keyword-position data just by browsing to `http://localhost:8080/keyword-position?query=test+query` (for example)
The returned data will be JSON representation of the [SearchAnalyticsQueryResponse](https://developers.google.com/resources/api-libraries/documentation/webmasters/v3/java/latest/com/google/api/services/webmasters/model/SearchAnalyticsQueryResponse.html)
