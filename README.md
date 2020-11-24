<p align="center">
    <h2>Currency Calculator</h2>
Simple Android calculator that supports currency conversion
<li>Currencies are fetched from fixer.io</li>
<li>Supports only basic arithmetic operations</li>
</p>

![](Gui.png)

## Documentation


### ApiKey
In order to get an API key you need to sign up on fixer.io
 - [Currency Converter Api](https://fixer.io/)
 
 To use your key simply replace <b>your_api</b> with the API key given by fixer.io inside <b>AsyncReq.java</b>
```java
	String Api_Key = "your_api";
	URL url = new URL("http://data.fixer.io/api/latest?access_key=" + Api_Key);
```

### How to use
This Project is meant to be opened with <b>Android Studio</b>. Simply Build the project, 
select a physical or virtual device and the click on Run 'app' button.

### Limitations
Currently the API request to get the currencies <b>is done only once</b> when you start the application.
In case you start the app without internet access you need to restart it after enabling internet
in order to be able to select currencies.